package org.example.chaos.monkey.shopping.hotdeals.updater;

import com.example.chaos.monkey.shopping.domain.UpdateDealsEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.bus.BusProperties;
import org.springframework.cloud.bus.jackson.RemoteApplicationEventScan;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ryan Baxter
 */
@SpringBootApplication
@RestController
public class HotDealsUpdaterApplication {

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	@Autowired
	private BusProperties busProperties;

	public static void main(String[] args) {
		SpringApplication.run(HotDealsUpdaterApplication.class, args);
	}

	@PostMapping("/update")
	public void update() {
		applicationEventPublisher.publishEvent(new UpdateDealsEvent("", busProperties.getId()));
	}

	@Configuration
	@RemoteApplicationEventScan(basePackageClasses = {UpdateDealsEvent.class})
	class BusConfiguration {

	}
}
