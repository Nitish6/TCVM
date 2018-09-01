package com.yash.training.tcvm.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.yash.training.tcvm.dao.ContainerInitialCapacity;
import com.yash.training.tcvm.domain.Container;
import com.yash.training.tcvm.domain.ProductParameters;

public class MaterialManager {

	private Container containerCapacity;

	public Map<String, Double> getMaterialQuantiyLeft(Container containerCapacity, Map<String, Double> ingredients,	Integer drinkCount) {

		Map<String, Double> materialQuantityLeft = new HashMap<>();

		materialQuantityLeft.put("teaQuantityLeft", containerCapacity.getTeaContainer()	- drinkCount * Optional.ofNullable(ingredients.get("tea")).orElse(0.0));
		materialQuantityLeft.put("milkQuantityLeft", containerCapacity.getMilkContainer() - drinkCount * Optional.ofNullable(ingredients.get("milk")).orElse(0.0));
		materialQuantityLeft.put("sugarQuantityLeft", containerCapacity.getSugarContainer() - drinkCount * Optional.ofNullable(ingredients.get("sugar")).orElse(0.0));
		materialQuantityLeft.put("waterQuantityLeft", containerCapacity.getWaterContainer() - drinkCount * Optional.ofNullable(ingredients.get("water")).orElse(0.0));
		materialQuantityLeft.put("coffeeQuantityLeft", containerCapacity.getCoffeeContainer() - drinkCount * Optional.ofNullable(ingredients.get("coffee")).orElse(0.0));

		return materialQuantityLeft;
	}

	public Container containerMaterialsLeft(Map<String, Double> quantityLeft) {

		containerCapacity = Container.getCapacity();
		containerCapacity.setTeaContainer(quantityLeft.get("teaQuantityLeft"));
		containerCapacity.setSugarContainer(quantityLeft.get("sugarQuantityLeft"));
		containerCapacity.setMilkContainer(quantityLeft.get("milkQuantityLeft"));
		containerCapacity.setWaterContainer(quantityLeft.get("waterQuantityLeft"));
		containerCapacity.setCoffeeContainer(quantityLeft.get("coffeeQuantityLeft"));

		return containerCapacity;
	}

	public Map<String, Double> getTotalSale() {

		ProductParameters cost = ProductParameters.getInstance();

		Map<String, Double> totalSale = new HashMap<>();
		totalSale.put("Tea Sale", Optional.ofNullable(cost.getTotalTeaCost()).orElse(0.0));
		totalSale.put("Coffee Sale", Optional.ofNullable(cost.getTotalCoffeeCost()).orElse(0.0));
		totalSale.put("Black Tea", Optional.ofNullable(cost.getTotalBlackTeaCost()).orElse(0.0));
		totalSale.put("Black Coffee", Optional.ofNullable(cost.getTotalBlackCoffeeCost()).orElse(0.0));
		totalSale.put("Total Sale", totalSale.get("Tea Sale") + totalSale.get("Coffee Sale") + totalSale.get("Black Tea") + totalSale.get("Black Coffee"));

		return totalSale;

	}

	public Container initializeContainerCapacity() {

		containerCapacity = Container.getCapacity();

		containerCapacity.setCoffeeContainer(ContainerInitialCapacity.coffeeContainerCapacity);
		containerCapacity.setTeaContainer(ContainerInitialCapacity.teaContainerCapacity);
		containerCapacity.setMilkContainer(ContainerInitialCapacity.milkContainerCapacity);
		containerCapacity.setSugarContainer(ContainerInitialCapacity.sugarContainerCapacity);
		containerCapacity.setWaterContainer(ContainerInitialCapacity.waterContainerCapacity);

		return containerCapacity;
	}

	public Boolean refillSelectedContainer(Integer containerChoice, Double refillQuantity) {

		Boolean refillStatus = true;
		Container containerQuantity = Container.getCapacity();

		if (containerChoice == 1) {
			if ((containerQuantity.getTeaContainer() + refillQuantity) > ContainerInitialCapacity.teaContainerCapacity) {
				refillStatus = false;
			} else {
				containerQuantity.setTeaContainer(containerQuantity.getTeaContainer() + refillQuantity);
			}

		} else if (containerChoice == 2) {
			if ((containerQuantity.getCoffeeContainer() + refillQuantity) > ContainerInitialCapacity.coffeeContainerCapacity) {
				refillStatus = false;
			} else {
				containerQuantity.setCoffeeContainer(containerQuantity.getCoffeeContainer() + refillQuantity);
			}

		} else if (containerChoice == 3) {
			if ((containerQuantity.getMilkContainer() + refillQuantity) > ContainerInitialCapacity.milkContainerCapacity) {
				refillStatus = false;
			} else {
				containerQuantity.setMilkContainer(containerQuantity.getMilkContainer() + refillQuantity);
			}
		} else if (containerChoice == 4) {
			if ((containerQuantity.getSugarContainer() + refillQuantity) > ContainerInitialCapacity.sugarContainerCapacity) {
				refillStatus = false;
			} else {
				containerQuantity.setCoffeeContainer(containerQuantity.getSugarContainer() + refillQuantity);
			}
		} else if (containerChoice == 5) {
			if ((containerQuantity.getWaterContainer() + refillQuantity) > ContainerInitialCapacity.waterContainerCapacity) {
				refillStatus = false;
			} else {
				containerQuantity.setWaterContainer(containerQuantity.getWaterContainer() + refillQuantity);
			}
		} 

		return refillStatus;
	}
}
