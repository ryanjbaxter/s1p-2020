package com.example.chaos.monkey.shopping;

import feign.Logger;

import org.springframework.context.annotation.Bean;

/**
 * @author Olga Maciaszek-Sharma
 */
public class FeignFullLoggingConfiguration {


	@Bean
	public Logger.Level feignLoggerLevel() {
		return Logger.Level.FULL;
	}
}
