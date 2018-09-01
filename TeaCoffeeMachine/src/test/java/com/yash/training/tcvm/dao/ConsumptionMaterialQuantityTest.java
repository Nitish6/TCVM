package com.yash.training.tcvm.dao;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Test;


public class ConsumptionMaterialQuantityTest {

	private	ConsumptionMaterialQuantity consumptionMaterial = new ConsumptionMaterialQuantity();

	@Test
	public void shouldReturnQuantityOfTeaMaterials(){

		Map<String, Double> teaIngredients = consumptionMaterial.teaConsumption();

		assertEquals(5d, teaIngredients.get("tea"), 0);
		assertEquals(60d, teaIngredients.get("water"), 0);
		assertEquals(40d, teaIngredients.get("milk"), 0);
		assertEquals(15d, teaIngredients.get("sugar"), 0);
	}

	@Test
	public void shouldReturnQuantityOfCoffeeMaterials(){

		Map<String, Double> coffeeIngredients = consumptionMaterial.coffeeConsumption();

		assertEquals(4d, coffeeIngredients.get("coffee"), 0);
		assertEquals(20d, coffeeIngredients.get("water"), 0);
		assertEquals(80d, coffeeIngredients.get("milk"), 0);
		assertEquals(15d, coffeeIngredients.get("sugar"), 0);
	}

	@Test
	public void shouldReturnQuantityOfBlackCoffeeMaterials(){

		Map<String, Double> blackCoffeeIngredients = consumptionMaterial.blackCoffeeConsumption();

		assertEquals(3d, blackCoffeeIngredients.get("coffee"), 0);
		assertEquals(100d, blackCoffeeIngredients.get("water"), 0);
		assertEquals(15d, blackCoffeeIngredients.get("sugar"), 0);
	}

	@Test
	public void shouldReturnQuantityOfBlackTeaMaterials(){

		Map<String, Double> blackTeaIngredients = consumptionMaterial.blackTeaConsumption();

		assertEquals(3d, blackTeaIngredients.get("tea"), 0);
		assertEquals(100d, blackTeaIngredients.get("water"), 0);
		assertEquals(15d, blackTeaIngredients.get("sugar"), 0);
	}
}
