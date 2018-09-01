package com.yash.training.tcvm.service;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ProductFactoryTest {

	private ProductFactory productFactory = new ProductFactory();

	@Test
	public void shouldReturnTeaObjectIfProductSelectedIsTea() {

		String input = "tea";

		Product actual = productFactory.getProduct(input);

		assertThat(actual, instanceOf(ProductTeaImpl.class));

	}

	@Test
	public void shouldReturnCoffeeObjectIfProductSelectedIsCoffee() {

		String input = "coffee";

		Product actual = productFactory.getProduct(input);

		assertThat(actual, instanceOf(ProductCoffeeImpl.class));
	}

	@Test
	public void shouldReturnBlackTeaObjectIfProductSelectedIsBlackTea() {

		String input = "black tea";

		Product actual = productFactory.getProduct(input); 

		assertThat(actual, instanceOf(ProductBlackTeaImpl.class));
	}

	@Test
	public void shouldReturnBlackCoffeeObjectIfProductSelectedIsBlackCoffee() {

		String input = "black coffee";

		Product actual = productFactory.getProduct(input); 

		assertThat(actual, instanceOf(ProductBlackCoffeeImpl.class));
	}
}
