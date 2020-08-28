package com.example.chaos.monkey.shopping.domain;

import org.springframework.cloud.bus.event.RemoteApplicationEvent;
import org.springframework.cloud.bus.jackson.RemoteApplicationEventScan;

/**
 * @author Ryan Baxter
 */
public class UpdateDealsEvent extends RemoteApplicationEvent {

	public UpdateDealsEvent(){}

	public UpdateDealsEvent(Object source, String originService) {
		super(source, originService);
	}

	public UpdateDealsEvent(Object source, String originService, String destinationService) {
		super(source, originService, destinationService);
	}
}
