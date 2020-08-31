package com.example.chaos.monkey.shopping;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.codec.Decoder;
import feign.jackson.JacksonDecoder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

/**
 * @author Olga Maciaszek-Sharma
 */
@Import(FeignFullLoggingConfiguration.class)
public class CustomFeignClientConfiguration {

	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	static {
		OBJECT_MAPPER.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
	}

	@Bean
	public Decoder decoder() {
		return new JacksonDecoder(OBJECT_MAPPER);
	}

}
