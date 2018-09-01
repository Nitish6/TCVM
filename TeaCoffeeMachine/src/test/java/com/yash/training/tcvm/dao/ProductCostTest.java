package com.yash.training.tcvm.dao;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Test;

public class ProductCostTest {

	ProductCost productCost = new ProductCost();

	@Test
	public void shouldReturnPerUnitProductCost(){

		Map<String, Double> perUnitProductCost = productCost.getProductCost();

		assertEquals(10d, perUnitProductCost.get("tea"), 0);
		assertEquals(5d, perUnitProductCost.get("black tea"), 0);
		assertEquals(15d, perUnitProductCost.get("coffee"), 0);
		assertEquals(10d, perUnitProductCost.get("black coffee"), 0);
	}

}
