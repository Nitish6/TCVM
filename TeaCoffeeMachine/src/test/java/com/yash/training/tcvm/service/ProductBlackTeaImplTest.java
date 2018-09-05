package com.yash.training.tcvm.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

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
public class ProductBlackTeaImplTest {

	@InjectMocks
	private	ProductBlackTeaImpl blackTeaImpl;

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
		ingredients.put("tea", 4d);
		ingredients.put("water", 100d); 
		ingredients.put("milk", 0d);
		ingredients.put("sugar", 15d);

		Map<String, Double> materialQuantityLeft = new HashMap<>();

		materialQuantityLeft.put("teaQuantityLeft", 1992d);
		materialQuantityLeft.put("milkQuantityLeft", 10000d);
		materialQuantityLeft.put("sugarQuantityLeft", 770d);
		materialQuantityLeft.put("waterQuantityLeft", 1300d);
		materialQuantityLeft.put("coffeeQuantityLeft", 2000d);

		when(consumption.blackTeaConsumption()).thenReturn(ingredients);
		when(materialManager.getMaterialQuantiyLeft(containerQuantity, ingredients, 2)).thenReturn(materialQuantityLeft);

		Boolean actualAvailabilityStatus = blackTeaImpl.checkProductMaterialsQuantityAvailability(2);

		verify(consumption).blackTeaConsumption();
		verify(materialManager).getMaterialQuantiyLeft(containerQuantity, ingredients, 2);

		assertEquals(true, actualAvailabilityStatus);
	}

	@Test(expected = RuntimeException.class)
	public void shouldThrowExceptionIfSufficientMaterialNotPresent(){

		Map<String, Double> ingredients = new HashMap<>();
		ingredients.put("tea", 5d);
		ingredients.put("water", 60d);
		ingredients.put("milk", 40d);
		ingredients.put("sugar", 15d);

		Map<String, Double> materialQuantityLeft = new HashMap<>();

		materialQuantityLeft.put("teaQuantityLeft", 1d);
		materialQuantityLeft.put("milkQuantityLeft", 9d);
		materialQuantityLeft.put("sugarQuantityLeft", 1d);
		materialQuantityLeft.put("waterQuantityLeft", 1d);
		materialQuantityLeft.put("coffeeQuantityLeft", 2d);

		when(consumption.blackTeaConsumption()).thenReturn(ingredients);
		when(materialManager.getMaterialQuantiyLeft(containerQuantity, ingredients, 8)).thenReturn(materialQuantityLeft);

		blackTeaImpl.checkProductMaterialsQuantityAvailability(8);
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
		when(wasteMaterial.getBlackTeaWastage()).thenReturn(getWasteBlackTea());

		Double actualCost = blackTeaImpl.getProductCost(drinkCount);

		verify(productCost).getProductCost();
		verify(wasteMaterial, Mockito.atLeast(3)).getBlackTeaWastage();

		assertEquals((Double)10.0, actualCost);

	}

	@Test
	public void shouldReturnCountTotalCostTotalWastageOfBlackTea(){

		Integer drinkCount = 2;
		Double currentOrderCost = 10.0;

		productParameters = ProductParameters.getInstance();
		productParameters.setWaterWaste(0.0);
		productParameters.setTotalBlackTeaCost(0.0);

		when(wasteMaterial.getBlackTeaWastage()).thenReturn(getWasteBlackTea());

		ProductParameters actual = blackTeaImpl.getProductParameters(drinkCount, currentOrderCost);

		verify(wasteMaterial, Mockito.atLeast(3)).getBlackTeaWastage();

		assertEquals((Integer)2, actual.getBlackTeaCount());
		assertEquals(24.0, actual.getWaterWaste(), 0);
		assertEquals(10.0, actual.getTotalBlackTeaCost(), 0);
	}

	private Map<String, Double> getWasteBlackTea(){

		Map<String, Double> wasteBlackTeaIngredients = new HashMap<>();

		wasteBlackTeaIngredients.put("tea", 0.0);
		wasteBlackTeaIngredients.put("water", 12.0);
		wasteBlackTeaIngredients.put("sugar", 2.0);

		return wasteBlackTeaIngredients;
	}
}
