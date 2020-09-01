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
@SpringBootTest
@AutoConfigureStubRunner(ids = {"com.example.chaos.monkey.shopping:bestseller-toys:+:stubs"},
		stubsMode = StubRunnerProperties.StubsMode.LOCAL)
public class ToysBestsellersIntegrationTests {

	@Autowired
	ToysBestsellerClient toysBestsellerClient;

	@Test
	void shouldFetchBestsellingToys() {
		List<Product> bestsellingToys = toysBestsellerClient.getBestsellerProducts();

		assertThat(bestsellingToys).isNotEmpty();
	}
}
