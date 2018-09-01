package com.yash.training.tcvm.controller;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.yash.training.tcvm.domain.Container;
import com.yash.training.tcvm.service.MaterialManager;
import com.yash.training.tcvm.service.ProductBlackCoffeeImpl;
import com.yash.training.tcvm.service.ProductBlackTeaImpl;
import com.yash.training.tcvm.service.ProductCoffeeImpl;
import com.yash.training.tcvm.service.ProductFactory;
import com.yash.training.tcvm.service.ProductTeaImpl;
import com.yash.training.tvcm.util.CustomScanner;


@RunWith(MockitoJUnitRunner.class)
public class MachineMenuTest {

	@InjectMocks
	private MachineMenu menu;

	@Mock
	private CustomScanner scanner;

	@Mock
	private MaterialManager materialManager;

	@Mock
	private ProductFactory productFactory;

	@Mock
	private ProductTeaImpl teaImpl;

	@Mock
	private ProductCoffeeImpl coffeeImpl;

	@Mock
	private ProductBlackTeaImpl blackteaImpl;

	@Mock
	private ProductBlackCoffeeImpl blackCoffeeImpl;

	private Container containerCapacity;

	@Test
	public void shouldCheckTeaMaterialAvailability(){

		when(materialManager.initializeContainerCapacity()).thenReturn(shouldinitializeContainer());
		when(scanner.getInputInteger()).thenReturn(1).thenReturn(1).thenReturn(9);	
		when(productFactory.getProduct("Tea")).thenReturn(teaImpl);
		when(teaImpl.checkProductMaterialsQuantityAvailability(1)).thenReturn(true);
		when(teaImpl.getProductCost(1)).thenReturn(10d);

		menu.selectOption();

		verify(materialManager).initializeContainerCapacity();
		verify(scanner, atLeast(3)).getInputInteger();
		verify(productFactory).getProduct("Tea");
		verify(teaImpl).checkProductMaterialsQuantityAvailability(1);
		verify(teaImpl).getProductCost(1);

	}

	@Test
	public void shouldCheckCoffeeMaterialAvailability(){

		when(materialManager.initializeContainerCapacity()).thenReturn(shouldinitializeContainer());
		when(scanner.getInputInteger()).thenReturn(2).thenReturn(1).thenReturn(9);	
		when(productFactory.getProduct("Coffee")).thenReturn(coffeeImpl);
		when(coffeeImpl.checkProductMaterialsQuantityAvailability(1)).thenReturn(true);
		when(coffeeImpl.getProductCost(1)).thenReturn(10d);

		menu.selectOption();

		verify(materialManager).initializeContainerCapacity();
		verify(scanner, atLeast(3)).getInputInteger();
		verify(productFactory).getProduct("Coffee");
		verify(coffeeImpl).checkProductMaterialsQuantityAvailability(1);
		verify(coffeeImpl).getProductCost(1);
	}

	@Test
	public void shouldCheckBlackTeaMaterialAvailability(){

		when(materialManager.initializeContainerCapacity()).thenReturn(shouldinitializeContainer());
		when(scanner.getInputInteger()).thenReturn(4).thenReturn(1).thenReturn(9);	
		when(productFactory.getProduct("Black Tea")).thenReturn(blackteaImpl);
		when(blackteaImpl.checkProductMaterialsQuantityAvailability(1)).thenReturn(true);
		when(blackteaImpl.getProductCost(1)).thenReturn(10d);

		menu.selectOption();

		verify(materialManager).initializeContainerCapacity();
		verify(scanner, atLeast(3)).getInputInteger();
		verify(productFactory).getProduct("Black Tea");
		verify(blackteaImpl).checkProductMaterialsQuantityAvailability(1);
		verify(blackteaImpl).getProductCost(1);

	}

	@Test
	public void shouldCheckBlackCoffeeMaterialAvailability(){

		when(materialManager.initializeContainerCapacity()).thenReturn(shouldinitializeContainer());
		when(scanner.getInputInteger()).thenReturn(3).thenReturn(1).thenReturn(9);	
		when(productFactory.getProduct("Black Coffee")).thenReturn(blackCoffeeImpl);
		when(blackCoffeeImpl.checkProductMaterialsQuantityAvailability(1)).thenReturn(true);
		when(blackCoffeeImpl.getProductCost(1)).thenReturn(10d);

		menu.selectOption();

		verify(materialManager).initializeContainerCapacity();
		verify(scanner, atLeast(3)).getInputInteger();
		verify(productFactory).getProduct("Black Coffee");
		verify(blackCoffeeImpl).checkProductMaterialsQuantityAvailability(1);
		verify(blackCoffeeImpl).getProductCost(1);

	}

	@Test
	public void shouldRefillIfSelectedValidContainer(){

		when(materialManager.initializeContainerCapacity()).thenReturn(shouldinitializeContainer());
		when(scanner.getInputInteger()).thenReturn(5).thenReturn(1).thenReturn(9);
		when(scanner.getInputDouble()).thenReturn(500.0);
		when(materialManager.refillSelectedContainer(1, 500.0)).thenReturn(true);

		menu.selectOption();;

		verify(materialManager).initializeContainerCapacity();
		verify(scanner, atLeast(3)).getInputInteger();
		verify(scanner).getInputDouble();
		verify(materialManager).refillSelectedContainer(1, 500.0);
	}

	@Test
	public void shouldThrowExceptionIfInvalidContainerSelected(){

		when(materialManager.initializeContainerCapacity()).thenReturn(shouldinitializeContainer());
		when(scanner.getInputInteger()).thenReturn(5).thenReturn(8).thenReturn(9);

		menu.selectOption();

		verify(materialManager).initializeContainerCapacity();
		verify(scanner, atLeast(3)).getInputInteger();
	}

	@Test
	public void shouldNotAcceptIfInvalidRefillQuantityEntered(){

		when(materialManager.initializeContainerCapacity()).thenReturn(shouldinitializeContainer());
		when(scanner.getInputInteger()).thenReturn(5).thenReturn(1).thenReturn(9);
		when(scanner.getInputDouble()).thenReturn(500.0);
		when(materialManager.refillSelectedContainer(1, 500.0)).thenReturn(false);

		menu.selectOption();

		verify(materialManager).initializeContainerCapacity();
		verify(scanner, atLeast(3)).getInputInteger();
		verify(scanner).getInputDouble();
		verify(materialManager).refillSelectedContainer(1, 500.0);
	}

	@Test
	public void shouldCheckTotalSale(){

		Map<String, Double> totalSale = new HashMap<>();
		totalSale.put("Tea Sale", 10d);
		totalSale.put("Coffee Sale", 0d);
		totalSale.put("Black Tea", 0d);
		totalSale.put("Black Coffee", 0d);
		totalSale.put("Total Sale",  totalSale.get("Tea Sale") + totalSale.get("Coffee Sale") + totalSale.get("Black Tea") + totalSale.get("Black Coffee"));

		when(materialManager.initializeContainerCapacity()).thenReturn(shouldinitializeContainer());
		when(scanner.getInputInteger()).thenReturn(6).thenReturn(9);
		when(materialManager.getTotalSale()).thenReturn(totalSale);

		menu.selectOption();

		verify(materialManager).initializeContainerCapacity();
		verify(scanner, atLeast(2)).getInputInteger();
		verify(materialManager).getTotalSale();
	}

	@Test
	public void shouldCheckContainerStatus(){

		when(materialManager.initializeContainerCapacity()).thenReturn(shouldinitializeContainer());
		when(scanner.getInputInteger()).thenReturn(7).thenReturn(9);

		menu.selectOption();

		verify(materialManager).initializeContainerCapacity();
		verify(scanner, atLeast(2)).getInputInteger();
	}

	@Test
	public void shouldResetContainer(){

		when(materialManager.initializeContainerCapacity()).thenReturn(shouldinitializeContainer());
		when(scanner.getInputInteger()).thenReturn(8).thenReturn(9);

		menu.selectOption();

		verify(materialManager, atLeast(2)).initializeContainerCapacity();
		verify(scanner, atLeast(2)).getInputInteger();

	}

	@Test
	public void shouldExitFromMachine(){

		when(materialManager.initializeContainerCapacity()).thenReturn(shouldinitializeContainer());
		when(scanner.getInputInteger()).thenReturn(9);

		menu.selectOption();

		verify(materialManager).initializeContainerCapacity();
		verify(scanner).getInputInteger();

	}

	private Container shouldinitializeContainer(){

		containerCapacity = Container.getCapacity();
		containerCapacity.setCoffeeContainer(2000d);
		containerCapacity.setTeaContainer(2000d);
		containerCapacity.setMilkContainer(10000d);
		containerCapacity.setSugarContainer(8000d); 
		containerCapacity.setWaterContainer(15000d);

		return containerCapacity;
	}

}
