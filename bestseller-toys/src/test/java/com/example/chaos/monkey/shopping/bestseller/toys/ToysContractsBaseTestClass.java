package com.example.chaos.monkey.shopping.bestseller.toys;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;

/**
 * @author Olga Maciaszek-Sharma
 */
public class ToysContractsBaseTestClass {

	@Before
	public void setUp() {
		RestAssuredMockMvc.standaloneSetup(new BestsellerToysRestController());
	}
}
