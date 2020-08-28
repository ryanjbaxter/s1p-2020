package com.example.chaos.monkey.shopping.shop;

import java.util.List;

import com.example.chaos.monkey.shopping.domain.Product;

import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Olga Maciaszek-Sharma
 */
@RestController
@RequestMapping("/shop")
@Import(FeignClientsConfiguration.class)
class ShopController {

	private final FashionBestsellerClient fashionBestsellerClient;

	ShopController(FashionBestsellerClient fashionBestsellerClient) {
		this.fashionBestsellerClient = fashionBestsellerClient;
	}


	@GetMapping("/fashion/bestsellers")
	List<Product> listBestsellers() {
		return fashionBestsellerClient.getBestsellingProducts();
	}

	@GetMapping("/fashion/bestsellers/month")
	List<Product> getBestsellerOfTheMonth() {
		return fashionBestsellerClient.getBestsellersOfTheMonth();
	}
}
