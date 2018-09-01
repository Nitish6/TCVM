package com.yash.training.tcvm.dao;

import java.util.HashMap;
import java.util.Map;

public class ProductCost {

	public  Map<String, Double> getProductCost() {

		Map<String, Double> productCost = new HashMap<>();
		productCost.put("tea", 10d);
		productCost.put("black tea", 5d);
		productCost.put("coffee", 15d);
		productCost.put("black coffee", 10d);

		return productCost; 
	}
}
