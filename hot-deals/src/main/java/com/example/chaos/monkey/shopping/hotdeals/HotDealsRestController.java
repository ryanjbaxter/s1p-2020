package com.example.chaos.monkey.shopping.hotdeals;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.example.chaos.monkey.shopping.domain.Product;
import com.example.chaos.monkey.shopping.domain.ProductBuilder;
import com.example.chaos.monkey.shopping.domain.ProductCategory;
import com.example.chaos.monkey.shopping.domain.UpdateDealsEvent;

import org.springframework.context.ApplicationListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Benjamin Wilms
 */
@RestController
public class HotDealsRestController implements ApplicationListener<UpdateDealsEvent> {

	private List<Product> deals;

	private boolean listSwitch = false;

	public HotDealsRestController() {
		deals = new ArrayList<Product>();
		AtomicLong aLong = new AtomicLong(7);
		ProductBuilder productBuilder = new ProductBuilder();
		deals.add(productBuilder.setCategory(ProductCategory.FASHION).setId(aLong.getAndIncrement())
				.setName("Thermal Winter Warm Hot Heat Socks").createProduct());
		deals.add(productBuilder.setCategory(ProductCategory.TOYS).setId(aLong.getAndIncrement())
				.setName("RC Quadcopter Drone with 2.0MP Camera Live").createProduct());
		deals.add(productBuilder.setCategory(ProductCategory.BOOKS).setId(aLong.getAndIncrement())
				.setName("Spring Boot 2: Moderne Softwareentwicklung mit Spring 5").createProduct());
		deals.add(productBuilder.setCategory(ProductCategory.FASHION).setId(aLong.getAndIncrement())
				.setName("Winter Gloves").createProduct());
		deals.add(productBuilder.setCategory(ProductCategory.TOYS).setId(aLong.getAndIncrement())
				.setName("Legos").createProduct());
		deals.add(productBuilder.setCategory(ProductCategory.BOOKS).setId(aLong.getAndIncrement())
				.setName("Ship It").createProduct());
	}

	@GetMapping("/hotdeals")
	public List<Product> getHotDeals() {
		if(listSwitch) {
			return deals.subList(0, 3);
		} else {
			return deals.subList(3, 6);
		}
	}

	public void onApplicationEvent(UpdateDealsEvent updateDealsEvent) {
		listSwitch = !listSwitch;
	}
}
