package com.example.chaos.monkey.shopping.gateway;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import com.example.chaos.monkey.shopping.domain.Product;
import com.example.chaos.monkey.shopping.domain.ProductCategory;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.bus.BusProperties;
import com.example.chaos.monkey.shopping.domain.CBFailureEvent;
import org.springframework.cloud.bus.jackson.RemoteApplicationEventScan;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.rewrite.MessageBodyDecoder;
import org.springframework.cloud.gateway.filter.factory.rewrite.MessageBodyEncoder;
import org.springframework.cloud.gateway.filter.factory.rewrite.ModifyResponseBodyGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.factory.rewrite.RewriteFunction;
import org.springframework.cloud.gateway.handler.predicate.BetweenRoutePredicateFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.HttpMessageReader;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;

@SpringBootApplication
@RestController
@EnableDiscoveryClient
public class GatewayApplication {

	private static final Log LOG = LogFactory
			.getLog(GatewayApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	@Bean
	public AddProductFilterFactory addProductFilterFactory(ServerCodecConfigurer codecConfigurer, Set<MessageBodyDecoder> bodyDecoders,
			Set<MessageBodyEncoder> bodyEncoders, BetweenRoutePredicateFactory betweenRoutePredicateFactory) {
		return new AddProductFilterFactory(codecConfigurer
				.getReaders(), bodyDecoders, bodyEncoders, betweenRoutePredicateFactory);
	}

	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder, AddProductFilterFactory addProductFilterFactory) {
		GatewayFilter addProductFilter = addProductFilterFactory.apply();
		return builder.routes()
				.route(p -> p.path("/hotdeals**").filters(f ->
						f.circuitBreaker(c -> c.setName("hostdeals").setFallbackUri("forward:/fallback"))
								.filter(addProductFilter)).uri("lb://hotdeals"))
				.route(p -> p.path("/fashion/**").filters(f ->
						f.circuitBreaker(c -> c.setName("fashion").setFallbackUri("forward:/fallback")))
						.uri("lb://fashion-bestseller"))
				.route(p -> p.path("/toys/**").filters(f ->
						f.circuitBreaker(c -> c.setName("toys").setFallbackUri("forward:/fallback")))
						.uri("lb://toys-bestseller"))
				.build();
	}

	@GetMapping("/fallback")
	public ResponseEntity<List<Product>> fallback() {
		System.out.println("fallback enabled");
		HttpHeaders headers = new HttpHeaders();
		headers.add("fallback", "true");
		return ResponseEntity.ok().headers(headers).body(Collections.emptyList());
	}


	@Bean
	public Customizer<ReactiveResilience4JCircuitBreakerFactory> toysCustomizer(ApplicationEventPublisher applicationEventPublisher, BusProperties busProperties) {
		return factory -> {
			factory.configure(builder -> {
				builder
						.timeLimiterConfig(TimeLimiterConfig.ofDefaults())
						.circuitBreakerConfig(CircuitBreakerConfig.custom()
								.failureRateThreshold(1) //If the failure rate is greater than 1% open the CB
								.minimumNumberOfCalls(1) //Only 1 call needs to be made before the failure rate threshold is computer
								.waitDurationInOpenState(Duration
										.ofSeconds(5)) //Wait five seconds before transitioning the CB from open to half opened to allow calls to go through
								.build());

			}, "toys");
			factory.addCircuitBreakerCustomizer(
					circuitBreaker -> circuitBreaker.getEventPublisher()
							.onError(event -> {
								LOG.warn(circuitBreaker.getMetrics().getFailureRate());
								LOG.warn(circuitBreaker.getMetrics().getNumberOfFailedCalls());
								applicationEventPublisher
										.publishEvent(new CBFailureEvent(event, busProperties.getId()));
								LOG.warn("Toys circuit breaker had an error " + circuitBreaker.getState(), event
										.getThrowable());
							})
							.onSuccess(event -> {
								LOG.info("No error here");
							}), "toys");
		};
	}

	class AddProductFilterFactory extends ModifyResponseBodyGatewayFilterFactory {
		private BetweenRoutePredicateFactory betweenRoutePredicateFactory;

		AddProductFilterFactory(List<HttpMessageReader<?>> messageReaders, Set<MessageBodyDecoder> messageBodyDecoders, Set<MessageBodyEncoder> messageBodyEncoders, BetweenRoutePredicateFactory betweenRoutePredicateFactory) {
			super(messageReaders, messageBodyDecoders, messageBodyEncoders);
			this.betweenRoutePredicateFactory = betweenRoutePredicateFactory;
		}


		public GatewayFilter apply() {
			Config config = new Config();
			config.setInClass(List.class);
			config.setOutClass(List.class);
			config.setRewriteFunction(new AddProductRewriteFunction(betweenRoutePredicateFactory));
			return apply(config);
		}
	}

	class AddProductRewriteFunction implements RewriteFunction<List, List> {

		private Predicate<ServerWebExchange> between;

		public AddProductRewriteFunction(BetweenRoutePredicateFactory betweenRoutePredicateFactory) {
			ZonedDateTime now = ZonedDateTime.now();
			this.between = betweenRoutePredicateFactory
					.apply(new BetweenRoutePredicateFactory.Config().setDatetime1(now)
							.setDatetime2(now.plusMinutes(1)));
		}

		@Override
		public Publisher<List> apply(ServerWebExchange exchange, List products) {
			if (between.test(exchange)) {
				Product secret = new Product();
				secret.setId(123);
				secret.setCategory(ProductCategory.TOYS);
				secret.setName("XBOX");
				products.add(secret);
				return Mono.just(products);
			}
			return Mono.just(products);
		}
	}

	@Configuration
	@RemoteApplicationEventScan(basePackageClasses = {CBFailureEvent.class})
	class BusConfiguration {

	}
}

