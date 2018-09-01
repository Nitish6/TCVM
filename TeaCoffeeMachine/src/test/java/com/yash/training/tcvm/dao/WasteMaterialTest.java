package com.yash.training.tcvm.dao;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Test;

public class WasteMaterialTest {

	WasteMaterial wasteMaterial = new WasteMaterial();

	@Test
	public void shouldCheckTeaIngredientsWaste(){

		Map<String, Double> wasteTeaIngredients = wasteMaterial.getTeaWastage();

		assertEquals(1, wasteTeaIngredients.get("tea"), 0);
		assertEquals(5, wasteTeaIngredients.get("water"), 0);
		assertEquals(4, wasteTeaIngredients.get("milk"), 0);
		assertEquals(2, wasteTeaIngredients.get("sugar"), 0);
	}

	@Test
	public void shouldCheckCoffeeIngredientsWaste(){

		Map<String, Double> wasteCoffeeIngredients = wasteMaterial.getCoffeeWastage();

		assertEquals(1, wasteCoffeeIngredients.get("coffee"), 0);
		assertEquals(3, wasteCoffeeIngredients.get("water"), 0);
		assertEquals(8, wasteCoffeeIngredients.get("milk"), 0);
		assertEquals(2, wasteCoffeeIngredients.get("sugar"), 0);
	}

	@Test
	public void shouldCheckBlackTeaIngredientsWaste(){

		Map<String, Double> wasteBlackTeaIngredients = wasteMaterial.getBlackTeaWastage();

		assertEquals(0, wasteBlackTeaIngredients.get("tea"), 0);
		assertEquals(12, wasteBlackTeaIngredients.get("water"), 0);
		assertEquals(0, wasteBlackTeaIngredients.get("milk"), 0);
		assertEquals(2, wasteBlackTeaIngredients.get("sugar"), 0);
	}

	@Test
	public void shouldCheckBlackCoffeeIngredientsWaste(){

		Map<String, Double> wasteTeaIngredients = wasteMaterial.getBlackCoffeeWastage();

		assertEquals(0, wasteTeaIngredients.get("coffee"), 0);
		assertEquals(12, wasteTeaIngredients.get("water"), 0);
		assertEquals(0, wasteTeaIngredients.get("milk"), 0);
		assertEquals(2, wasteTeaIngredients.get("sugar"), 0);
	}

}
