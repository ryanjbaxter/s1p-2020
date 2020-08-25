package org.example.chaos.monkey.shopping.twillio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.chaos.monkey.shopping.domain.CBFailureEvent;
import org.springframework.context.ApplicationListener;

/**
 * @author Ryan Baxter
 */
@SpringBootApplication
public class TwillioNotifierApplication implements ApplicationListener<CBFailureEvent> {

	public static void main(String[] args) {
		SpringApplication.run(TwillioNotifierApplication.class, args);
	}

	public void onApplicationEvent(CBFailureEvent cbFailureEvent) {
		System.out.println("CB Failure");
	}
}
