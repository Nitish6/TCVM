package com.yash.training.tcvm.service;

import com.yash.training.tcvm.domain.ProductParameters;

public interface Product {

	public Double getProductCost(Integer drinkCount);
	public Boolean checkProductMaterialsQuantityAvailability(Integer drinkCount) ;
	public ProductParameters getProductParameters(Integer drinkCount,  Double totalCurrentOrderCost);
}
