package com.example.chaos.monkey.shopping.shop;

import java.util.List;

import com.example.chaos.monkey.shopping.domain.Product;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Olga Maciaszek-Sharma
 */
@FeignClient("fashion-bestseller")
public interface FashionBestsellerClient {


	@GetMapping("/fashion/bestseller")
	List<Product> getBestsellingProducts();
}
