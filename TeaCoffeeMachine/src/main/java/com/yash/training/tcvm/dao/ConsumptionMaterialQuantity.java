package com.yash.training.tcvm.dao;

import java.util.HashMap;
import java.util.Map;

public class ConsumptionMaterialQuantity {

	public  Map<String, Double> teaConsumption(){

		Map<String, Double> teaIngredients = new HashMap<>();
		teaIngredients.put("tea", 5d);
		teaIngredients.put("water", 60d);
		teaIngredients.put("milk", 40d);
		teaIngredients.put("sugar", 15d);

		return teaIngredients;
	} 

	public  Map<String, Double> coffeeConsumption(){

		Map<String, Double> coffeeIngredients = new HashMap<>();
		coffeeIngredients.put("coffee", 4d);
		coffeeIngredients.put("water", 20d);
		coffeeIngredients.put("milk", 80d);
		coffeeIngredients.put("sugar", 15d);

		return coffeeIngredients;
	} 

	public  Map<String, Double> blackTeaConsumption(){

		Map<String, Double> blackTeaIngredients = new HashMap<>();
		blackTeaIngredients.put("tea", 3d);
		blackTeaIngredients.put("water", 100d);
		blackTeaIngredients.put("milk", 0d);
		blackTeaIngredients.put("sugar", 15d);

		return blackTeaIngredients;
	} 

	public  Map<String, Double> blackCoffeeConsumption(){

		Map<String, Double> blackCoffeeIngredients = new HashMap<>();
		blackCoffeeIngredients.put("coffee", 3d);
		blackCoffeeIngredients.put("water", 100d);
		blackCoffeeIngredients.put("milk", 0d);
		blackCoffeeIngredients.put("sugar", 15d);

		return blackCoffeeIngredients;
	} 
}
