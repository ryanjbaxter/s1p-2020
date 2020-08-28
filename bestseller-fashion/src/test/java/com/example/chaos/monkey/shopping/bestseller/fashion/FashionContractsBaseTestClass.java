package com.example.chaos.monkey.shopping.bestseller.fashion;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;

/**
 * @author Olga Maciaszek-Sharma
 */
public class FashionContractsBaseTestClass {

	@Before
	public void setUp() {
		RestAssuredMockMvc.standaloneSetup(new BestsellerFashionRestController());
	}
}
