package com.example.chaos.monkey.shopping.domain;

import org.springframework.cloud.bus.event.RemoteApplicationEvent;

/**
 * @author Ryan Baxter
 */
public class CBFailureEvent extends RemoteApplicationEvent {

	public CBFailureEvent() {
	}

	public CBFailureEvent(Object source, String originService) {
		super(source, originService);
	}
}
