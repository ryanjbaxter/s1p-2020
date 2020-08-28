//package com.example.chaos.monkey.shopping;
//
//import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
//import org.springframework.context.ConfigurableApplicationContext;
//import org.springframework.context.annotation.Bean;
//
///**
// * @author Olga Maciaszek-Sharma
// */
//public class CustomLoadBalancerConfiguration {
//
//	@Bean
//	ServiceInstanceListSupplier discoveryClientServiceInstanceListSupplier(ConfigurableApplicationContext context) {
//		return ServiceInstanceListSupplier.builder()
//				.withBlockingDiscoveryClient()
//				.withHealthChecks()
//				.build(context);
//	}
//
//}
