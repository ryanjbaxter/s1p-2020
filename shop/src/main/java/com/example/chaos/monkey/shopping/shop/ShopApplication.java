package com.example.chaos.monkey.shopping.shop;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Olga Maciaszek-Sharma
 */
@SpringBootApplication
@EnableFeignClients
//@LoadBalancerClient(name = "fashion-bestseller", configuration = CustomLoadBalancerConfiguration.class)
public class ShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopApplication.class, args);
	}
}
