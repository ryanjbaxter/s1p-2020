package com.example.chaos.monkey.shopping.shop;

import java.util.List;

import com.example.chaos.monkey.shopping.domain.Product;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Olga Maciaszek-Sharma
 */
@FeignClient(value = "fashion-bestseller", path = "/fashion/bestseller"
//		, configuration = CustomFeignClientConfiguration.class
)
public interface FashionBestsellerClient {


	@GetMapping
	List<Product> getBestsellingProducts();

	@GetMapping("/month")
	List<Product> getBestsellersOfTheMonth();
}
