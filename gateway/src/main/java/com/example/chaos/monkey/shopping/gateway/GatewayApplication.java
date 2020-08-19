package com.example.chaos.monkey.shopping.gateway;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

import com.example.chaos.monkey.shopping.domain.Product;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreaker;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.loadbalancer.core.DiscoveryClientServiceInstanceListSupplier;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
	public RouteLocator routes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p -> p.path("/hotdeals**").filters(f ->
						f.circuitBreaker(c -> c.setName("hostdeals").setFallbackUri("forward:/fallback"))).uri("lb://hotdeals"))
				.route(p -> p.path("/fashion/**").filters(f ->
						f.circuitBreaker(c -> c.setName("fashion").setFallbackUri("forward:/fallback"))).uri("lb://fashion-bestseller"))
				.route(p -> p.path("/toys/**").filters(f ->
						f.circuitBreaker(c -> c.setName("toys").setFallbackUri("forward:/fallback"))).uri("lb://toys-bestseller"))
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
	public Customizer<ReactiveResilience4JCircuitBreakerFactory> toysCustomizer() {
		return factory -> {
			factory.configure(builder -> {
				builder
						.timeLimiterConfig(TimeLimiterConfig.ofDefaults())
						.circuitBreakerConfig(CircuitBreakerConfig.custom()
								.failureRateThreshold(1) //If the failure rate is greater than 1% open the CB
								.minimumNumberOfCalls(1) //Only 1 call needs to be made before the failure rate threshold is computer
								.waitDurationInOpenState(Duration.ofSeconds(5)) //Wait five seconds before transitioning the CB from open to half opened to allow calls to go through
								.build());

			}, "toys");
			factory.addCircuitBreakerCustomizer(
					circuitBreaker -> circuitBreaker.getEventPublisher()
							.onError(event -> {
								LOG.warn(circuitBreaker.getMetrics().getFailureRate());
								LOG.warn(circuitBreaker.getMetrics().getNumberOfFailedCalls());
								LOG.warn("Toys circuit breaker had an error " + circuitBreaker.getState(), event.getThrowable());
							})
							.onSuccess(event -> {
								LOG.info("No error here");
							}), "toys");
		};
	}

}
