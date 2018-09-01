package com.yash.training.tcvm.service;

public class ProductFactory {

	private static final String PRODUCT_TEA = "Tea";
	private static final String PRODUCT_COFFEE = "Coffee";
	private static final String PRODUCT_BLACK_TEA = "Black Tea";

	public Product getProduct(String productChoice) {

		if (productChoice.equalsIgnoreCase(PRODUCT_TEA)){
			return new ProductTeaImpl();

		}else if (productChoice.equalsIgnoreCase(PRODUCT_COFFEE)){
			return new ProductCoffeeImpl();

		} else if(productChoice.equalsIgnoreCase(PRODUCT_BLACK_TEA)){
			return new ProductBlackTeaImpl();

		}else{
			return new ProductBlackCoffeeImpl();
		}
	} 
}
