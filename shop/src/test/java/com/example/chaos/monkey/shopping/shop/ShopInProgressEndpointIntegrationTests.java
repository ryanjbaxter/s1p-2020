//package com.example.chaos.monkey.shopping.shop;
//
//import java.util.List;
//
//import com.example.chaos.monkey.shopping.domain.Product;
//import org.junit.jupiter.api.Test;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
//import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
///**
// * @author Olga Maciaszek-Sharma
// */
//@AutoConfigureStubRunner(ids = {"com.example.chaos.monkey.shopping:bestseller-fashion"},
//		stubsMode = StubRunnerProperties.StubsMode.LOCAL)
//@SpringBootTest
//public class ShopInProgressEndpointIntegrationTests {
//
//	@Autowired
//	FashionBestsellerClient fashionBestsellerClient;
//
//	@Test
//	void shouldGetFashionBestsellersOfTheYear() {
//		List<Product> fashionBestsellers = fashionBestsellerClient
//				.getBestsellingProductsOfTheYear();
//
//		assertThat(fashionBestsellers).isNotEmpty();
//	}
//}
