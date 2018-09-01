package com.yash.training.tcvm.dao;

import java.util.HashMap;
import java.util.Map;

public class WasteMaterial {

	public Map<String, Double> getTeaWastage() {

		Map<String, Double> wasteTeaIngredients = new HashMap<>();
		wasteTeaIngredients.put("tea", 1d);
		wasteTeaIngredients.put("water", 5d);
		wasteTeaIngredients.put("milk", 4d);
		wasteTeaIngredients.put("sugar", 2d);

		return wasteTeaIngredients;
	}
	
	public Map<String, Double> getCoffeeWastage() {

		Map<String, Double> wasteCoffeeIngredients = new HashMap<>();
		wasteCoffeeIngredients.put("coffee", 1d);
		wasteCoffeeIngredients.put("water", 3d);
		wasteCoffeeIngredients.put("milk", 8d);
		wasteCoffeeIngredients.put("sugar", 2d);

		return wasteCoffeeIngredients;
	}
	
	public Map<String, Double> getBlackTeaWastage() {

		Map<String, Double> wasteBlackTeaIngredients = new HashMap<>();
		wasteBlackTeaIngredients.put("tea", 0d);
		wasteBlackTeaIngredients.put("water", 12d);
		wasteBlackTeaIngredients.put("milk", 0d);
		wasteBlackTeaIngredients.put("sugar", 2d);

		return wasteBlackTeaIngredients;
	}
	
	public Map<String, Double> getBlackCoffeeWastage() {

		Map<String, Double> wasteBlackCoffeeIngredients = new HashMap<>();
		wasteBlackCoffeeIngredients.put("coffee", 0d);
		wasteBlackCoffeeIngredients.put("water", 12d);
		wasteBlackCoffeeIngredients.put("milk", 0d);
		wasteBlackCoffeeIngredients.put("sugar", 2d);

		return wasteBlackCoffeeIngredients;
	}
}
