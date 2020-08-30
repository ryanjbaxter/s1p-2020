package com.example.chaos.monkey.shopping.shop;

import java.util.List;

import com.example.chaos.monkey.shopping.CustomFeignClientConfiguration;
import com.example.chaos.monkey.shopping.FeignFullLoggingConfiguration;
import com.example.chaos.monkey.shopping.domain.Product;

import org.springframework.cloud.openfeign.CollectionFormat;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Olga Maciaszek-Sharma
 */
@FeignClient(value = "fashion-bestseller", path = "/fashion/bestseller"
		,	configuration = FeignFullLoggingConfiguration.class
//		, configuration = CustomFeignClientConfiguration.class
)
public interface FashionBestsellerClient {


	@GetMapping
	List<Product> getBestsellingProducts();

//	@CollectionFormat(feign.CollectionFormat.CSV)
	@GetMapping(path = "/page")
	String getPageInfo(Pageable page);

	@GetMapping("/month")
	List<Product> getBestsellersOfTheMonth();
}
