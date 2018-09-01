package com.yash.training.tcvm.service;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.yash.training.tcvm.domain.Container;
import com.yash.training.tcvm.domain.ProductParameters;

public class MaterialManagerTest {

	private MaterialManager materialManager = new MaterialManager();

	private Container containerQuantity;

	private ProductParameters cost; 

	public void initializeContainer(){

		containerQuantity = Container.getCapacity();
		containerQuantity.setCoffeeContainer(2000d);
		containerQuantity.setTeaContainer(2000d);
		containerQuantity.setMilkContainer(10000d);
		containerQuantity.setSugarContainer(8000d); 
		containerQuantity.setWaterContainer(15000d);
	}

	@Test
	public void shouldCheckMaterialQuantityLeft(){

		Integer drinkCount = 2;

		initializeContainer();
		Map<String, Double> teaIngredients = new HashMap<>();
		teaIngredients.put("tea", 5d);
		teaIngredients.put("water", 60d);
		teaIngredients.put("milk", 40d);
		teaIngredients.put("sugar", 15d);

		Map<String, Double> expectedMaterialQuantityLeft = materialManager.getMaterialQuantiyLeft(containerQuantity, teaIngredients, drinkCount);

		assertEquals(5, expectedMaterialQuantityLeft.size());
		assertEquals(1990d, expectedMaterialQuantityLeft.get("teaQuantityLeft"), 0);
		assertEquals(9920d, expectedMaterialQuantityLeft.get("milkQuantityLeft"), 0);
		assertEquals(7970d, expectedMaterialQuantityLeft.get("sugarQuantityLeft"), 0);
		assertEquals(14880d, expectedMaterialQuantityLeft.get("waterQuantityLeft"), 0);
		assertEquals(2000d, expectedMaterialQuantityLeft.get("coffeeQuantityLeft"), 0);
	}

	@Test
	public void shouldReturnRemainingContainerMaterial(){

		Map<String, Double> materialQuantityLeft = new HashMap<>();
		materialQuantityLeft.put("teaQuantityLeft", 1990d);
		materialQuantityLeft.put("milkQuantityLeft", 9920d);
		materialQuantityLeft.put("sugarQuantityLeft", 7970d);
		materialQuantityLeft.put("waterQuantityLeft", 14880d);
		materialQuantityLeft.put("coffeeQuantityLeft", 2000d);

		Container containerQuantityRemaining = materialManager.containerMaterialsLeft(materialQuantityLeft);

		assertEquals(1990d, containerQuantityRemaining.getTeaContainer(), 0);
		assertEquals(9920d, containerQuantityRemaining.getMilkContainer(), 0);
		assertEquals(7970d, containerQuantityRemaining.getSugarContainer(), 0);
		assertEquals(14880d, containerQuantityRemaining.getWaterContainer(), 0);
		assertEquals(2000d, containerQuantityRemaining.getCoffeeContainer(), 0);
	}

	@Test
	public void shouldInitialContainerCapacityToFull(){

		Container actualContainerCapacity = materialManager.initializeContainerCapacity();

		assertEquals(2000d, actualContainerCapacity.getTeaContainer(), 0);
		assertEquals(2000d, actualContainerCapacity.getCoffeeContainer(), 0);
		assertEquals(10000d, actualContainerCapacity.getMilkContainer(), 0);
		assertEquals(8000d, actualContainerCapacity.getSugarContainer(), 0);
		assertEquals(15000d, actualContainerCapacity.getWaterContainer(), 0);

	}

	@Test
	public void shouldReturnTotalSale(){

		cost = ProductParameters.getInstance();
		cost.setTotalTeaCost(10d);
		cost.setTotalBlackTeaCost(5d);
		cost.setTotalCoffeeCost(15d);
		cost.setTotalBlackCoffeeCost(10d); 

		Map<String, Double> expectedCost = materialManager.getTotalSale();

		assertEquals(40d, expectedCost.get("Total Sale"), 0);
		assertEquals(5, expectedCost.size());

	}

	@Test
	public void shouldReturnTrueIfTeaContainerSelectedAndEnteredValidQuantity(){

		containerQuantity = Container.getCapacity();
		containerQuantity.setTeaContainer(1990d);

		Boolean actualStatus = materialManager.refillSelectedContainer(1, 2.0);

		assertEquals(true, actualStatus);

	}

	@Test
	public void shouldReturnFalseIfTeaContainerSelectedAndEnteredInValidQuantity(){

		containerQuantity = Container.getCapacity();
		containerQuantity.setTeaContainer(1990d);

		Boolean actualStatus = materialManager.refillSelectedContainer(1, 12.0);

		assertEquals(false, actualStatus);

	}

	@Test
	public void shouldReturnTrueIfCoffeeContainerSelectedAndEnteredValidQuantity(){

		containerQuantity = Container.getCapacity();
		containerQuantity.setCoffeeContainer(1995d);

		Boolean actualStatus = materialManager.refillSelectedContainer(2, 2.0);

		assertEquals(true, actualStatus);

	}

	@Test
	public void shouldReturnFalseIfCoffeeContainerSelectedAndEnteredInValidQuantity(){

		containerQuantity = Container.getCapacity();
		containerQuantity.setCoffeeContainer(1995d);

		Boolean actualStatus = materialManager.refillSelectedContainer(2, 10.0);

		assertEquals(false, actualStatus);

	}

	@Test
	public void shouldReturnTrueIfMilkContainerSelectedAndEnteredValidQuantity(){

		containerQuantity = Container.getCapacity();
		containerQuantity.setMilkContainer(9000.0);

		Boolean actualStatus = materialManager.refillSelectedContainer(3, 500.0);

		assertEquals(true, actualStatus);

	}

	@Test
	public void shouldReturnFalseIfMilkContainerSelectedAndEnteredInValidQuantity(){

		containerQuantity = Container.getCapacity();
		containerQuantity.setMilkContainer(9000.0);

		Boolean actualStatus = materialManager.refillSelectedContainer(3, 1500.0);

		assertEquals(false, actualStatus);

	}

	@Test
	public void shouldReturnTrueIfSugarContainerSelectedAndEnteredValidQuantity(){

		containerQuantity = Container.getCapacity();
		containerQuantity.setSugarContainer(7000.0);

		Boolean actualStatus = materialManager.refillSelectedContainer(4, 100.0);

		assertEquals(true, actualStatus);

	}

	@Test
	public void shouldReturnFalseIfSugarContainerSelectedAndEnteredInValidQuantity(){

		containerQuantity = Container.getCapacity();
		containerQuantity.setSugarContainer(7000.0);

		Boolean actualStatus = materialManager.refillSelectedContainer(4, 1500.0);

		assertEquals(false, actualStatus);

	}

	@Test
	public void shouldReturnTrueIfWaterContainerSelectedAndEnteredValidQuantity(){

		containerQuantity = Container.getCapacity();
		containerQuantity.setWaterContainer(12000.0);

		Boolean actualStatus = materialManager.refillSelectedContainer(5, 2000.0);

		assertEquals(true, actualStatus);

	}

	@Test
	public void shouldReturnFalseIfWaterContainerSelectedAndEnteredInValidQuantity(){

		containerQuantity = Container.getCapacity();
		containerQuantity.setWaterContainer(12000.0);

		Boolean actualStatus = materialManager.refillSelectedContainer(5, 4000.0);

		assertEquals(false, actualStatus);

	}
}
