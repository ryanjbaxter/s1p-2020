package com.example.chaos.monkey.shopping.shop;

import java.util.List;

import com.example.chaos.monkey.shopping.domain.Product;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Olga Maciaszek-Sharma
 */
@AutoConfigureStubRunner(ids = {
		"com.example.chaos.monkey.shopping:bestseller-fashion"},
		stubsMode = StubRunnerProperties.StubsMode.LOCAL
//		, repositoryRoot = "stubs://file://home/olga/IdeaProjects/s1p-2020-olga/shop/src/test/resources/contractsAtRuntime"
//		, generateStubs = true
)
@SpringBootTest
class ShopNonExistentEndpointIntegrationTests {
	@Autowired
	FashionBestsellerClient fashionBestsellerClient;

	@Test
	void shouldGetFashionBestsellersOfTheWeek() {
		List<Product> fashionBestsellers = fashionBestsellerClient
				.getBestsellingProductsOfTheWeek();

		assertThat(fashionBestsellers).isNotEmpty();
	}
}

