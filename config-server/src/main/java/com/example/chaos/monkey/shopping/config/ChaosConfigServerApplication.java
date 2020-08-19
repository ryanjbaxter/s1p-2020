package com.example.chaos.monkey.shopping.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.environment.Environment;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.config.server.environment.EnvironmentRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.MapPropertySource;
import org.springframework.cloud.config.environment.PropertySource;

/**
 * @author Ryan Baxter
 */
@EnableConfigServer
@SpringBootApplication
public class ChaosConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChaosConfigServerApplication.class, args);
	}

	@Bean
	public EnvironmentRepository myEnvironmentRepository() {
		return new CustomEnvironmentRepository();
	}

}

class CustomEnvironmentRepository implements EnvironmentRepository {

	public Environment findOne(String application, String profile, String label) {
		return findOne(application, profile, label, false);
	}

	public Environment findOne(String application, String profile, String label, boolean includeOrigin) {
		Map<String, Object> myMap = new HashMap<String, Object>();
		myMap.put("hello", "world");
		myMap.put("foo", "bar");
		myMap.put("chaos.monkey.enabled", true);
		PropertySource propertySource = new PropertySource("custom", myMap);
		Environment env = new Environment(application, profile);
		env.add(propertySource);
		return env;
	}
}
