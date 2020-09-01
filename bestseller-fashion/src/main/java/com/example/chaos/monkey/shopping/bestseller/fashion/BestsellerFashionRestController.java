package com.example.chaos.monkey.shopping.bestseller.fashion;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.example.chaos.monkey.shopping.domain.Product;
import com.example.chaos.monkey.shopping.domain.ProductBuilder;
import com.example.chaos.monkey.shopping.domain.ProductCategory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Benjamin Wilms
 */
@RestController
@RequestMapping("/fashion")
public class BestsellerFashionRestController {

	@GetMapping("/bestseller")
	public List<Product> getBestsellerProducts() {
		return getBestsellingProducts();
	}

	@GetMapping("/bestseller/page")
	public Page<Product> getPagedBestsellerProducts(Pageable pageable) {
		return new PageImpl<>(getBestsellingProducts(), pageable, getBestsellerProducts()
				.size());
	}


	private List<Product> getBestsellingProducts() {
		AtomicLong aLong = new AtomicLong(4);

		ProductBuilder productBuilder = new ProductBuilder();

		Product product1 = productBuilder.setCategory(ProductCategory.FASHION)
				.setId(aLong.getAndIncrement())
				.setName("Bob Mailor Slim Jeans")
				.createProduct();

		Product product2 = productBuilder.setCategory(ProductCategory.FASHION)
				.setId(aLong.getAndIncrement())
				.setName("Lewi's Jeanshose 511 " +
						"Slim Fit")
				.createProduct();

		Product product3 = productBuilder.setCategory(ProductCategory.FASHION)
				.setId(aLong.getAndIncrement())
				.setName("Urban Classics T-Shirt " +
						"Shaped Long Tee")
				.createProduct();
		return Arrays.asList(product1, product2, product3);
	}

	@GetMapping("/bestseller/month")
	Product bestsellerOftTheMonth() {
		return new ProductBuilder()
				.setCategory(ProductCategory.FASHION)
				.setId(1L)
				.setName("")
				.createProduct();
	}

}
