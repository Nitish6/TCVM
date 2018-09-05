package com.yash.training.tcvm.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.yash.training.tcvm.dao.ConsumptionMaterialQuantity;
import com.yash.training.tcvm.dao.ProductCost;
import com.yash.training.tcvm.dao.WasteMaterial;
import com.yash.training.tcvm.domain.Container;
import com.yash.training.tcvm.domain.ProductParameters;


@RunWith(MockitoJUnitRunner.class)
public class ProductCoffeeImplTest {

	@InjectMocks
	private	ProductCoffeeImpl coffeeImpl;

	@Mock
	private MaterialManager materialManager;

	@Mock
	private ConsumptionMaterialQuantity consumption;

	@Mock
	private Container containerQuantity;

	@Mock
	private ProductCost productCost;

	@Mock
	private ProductParameters productParameters;

	@Mock
	private WasteMaterial wasteMaterial;

	@Before
	public void shouldinitializeContainer(){

		containerQuantity = Container.getCapacity();
		containerQuantity.setCoffeeContainer(2000d);
		containerQuantity.setTeaContainer(2000d);
		containerQuantity.setMilkContainer(10000d);
		containerQuantity.setSugarContainer(8000d); 
		containerQuantity.setWaterContainer(15000d);
	}


	@Test
	public void shouldReturnTrueIfEnoughMaterialPresent(){

		Map<String, Double> ingredients = new HashMap<>();
		ingredients.put("coffee", 4d);
		ingredients.put("water", 20d); 
		ingredients.put("milk", 80d);
		ingredients.put("sugar", 15d);

		Map<String, Double> materialQuantityLeft = new HashMap<>();

		materialQuantityLeft.put("teaQuantityLeft", 2000d);
		materialQuantityLeft.put("milkQuantityLeft", 9060d);
		materialQuantityLeft.put("sugarQuantityLeft", 770d);
		materialQuantityLeft.put("waterQuantityLeft", 1460d);
		materialQuantityLeft.put("coffeeQuantityLeft", 1992d);

		when(consumption.coffeeConsumption()).thenReturn(ingredients);
		when(materialManager.getMaterialQuantiyLeft(containerQuantity, ingredients, 2)).thenReturn(materialQuantityLeft);

		Boolean actualAvailabilityStatus = coffeeImpl.checkProductMaterialsQuantityAvailability(2);

		verify(consumption).coffeeConsumption();
		verify(materialManager).getMaterialQuantiyLeft(containerQuantity, ingredients, 2);

		Assert.assertEquals(true, actualAvailabilityStatus);
	}

	@Test(expected = RuntimeException.class)
	public void shouldThrowExceptionIfSufficientMaterialNotPresent() {

		Map<String, Double> ingredients = new HashMap<>();
		ingredients.put("coffee", 5d);
		ingredients.put("water", 60d);
		ingredients.put("milk", 40d);
		ingredients.put("sugar", 15d);

		Map<String, Double> materialQuantityLeft = new HashMap<>();

		materialQuantityLeft.put("teaQuantityLeft", 1d);
		materialQuantityLeft.put("milkQuantityLeft", 9d);
		materialQuantityLeft.put("sugarQuantityLeft", 1d);
		materialQuantityLeft.put("waterQuantityLeft", 1d);
		materialQuantityLeft.put("coffeeQuantityLeft", 2d);

		when(consumption.coffeeConsumption()).thenReturn(ingredients);
		when(materialManager.getMaterialQuantiyLeft(containerQuantity, ingredients, 8)).thenReturn(materialQuantityLeft);

		coffeeImpl.checkProductMaterialsQuantityAvailability(8);
	}

	@Test
	public void shouldReturnProductCostIfContentAvailable(){

		Integer drinkCount = 2;

		Map<String, Double> cost = new HashMap<>();
		cost.put("tea", 10d);
		cost.put("black tea", 5d);
		cost.put("coffee", 15d);
		cost.put("black coffee", 10d);

		when(productCost.getProductCost()).thenReturn(cost);
		when(wasteMaterial.getCoffeeWastage()).thenReturn(getCoffeeWaste());

		Double actualCost = coffeeImpl.getProductCost(drinkCount);

		verify(productCost).getProductCost();
		verify(wasteMaterial, Mockito.atLeast(3)).getCoffeeWastage();

		assertEquals((Double)30.0, actualCost);
	}

	@Test
	public void shouldReturnCountTotalCostTotalWastageOfCoffee(){

		Integer drinkCount = 2;
		Double currentOrderCost = 30.0;

		when(wasteMaterial.getCoffeeWastage()).thenReturn(getCoffeeWaste());

		ProductParameters actual = coffeeImpl.getProductParameters(drinkCount, currentOrderCost);

		verify(wasteMaterial, Mockito.atLeast(4)).getCoffeeWastage();

		assertEquals((Integer)2, actual.getCoffeeCount());
		assertEquals(30.0, actual.getTotalCoffeeCost(), 0);

	}

	private Map<String, Double> getCoffeeWaste(){

		Map<String, Double> wasteCoffeeIngredients = new HashMap<>();

		wasteCoffeeIngredients.put("coffee", 1.0);
		wasteCoffeeIngredients.put("water", 3.0);
		wasteCoffeeIngredients.put("milk", 8.0);
		wasteCoffeeIngredients.put("sugar", 2.0);

		return wasteCoffeeIngredients;
	}
}
