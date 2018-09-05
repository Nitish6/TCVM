package com.yash.training.tcvm.service;

import java.util.Map;
import java.util.Optional;

import com.yash.training.tcvm.CustomException.InvalidInputException;
import com.yash.training.tcvm.dao.ConsumptionMaterialQuantity;
import com.yash.training.tcvm.dao.ProductCost;
import com.yash.training.tcvm.dao.WasteMaterial;
import com.yash.training.tcvm.domain.Container;
import com.yash.training.tcvm.domain.ProductParameters;

public class ProductTeaImpl implements Product {

	private ProductParameters productParameters ;

	private ConsumptionMaterialQuantity consume;

	private MaterialManager materialManager;

	private ProductCost productCost;

	private WasteMaterial wasteMaterial; 

	public ProductTeaImpl(){  
		this.consume = new ConsumptionMaterialQuantity();
		this.productParameters = ProductParameters.getInstance();
		this.materialManager = new MaterialManager();
		this.productCost = new ProductCost();
		this.wasteMaterial = new WasteMaterial();
	} 

	public ProductTeaImpl(ProductParameters productParameters, ConsumptionMaterialQuantity consume, 
			MaterialManager materialManager, ProductCost productCost, WasteMaterial wasteMaterial) {
		
		this.productParameters = productParameters;
		this.consume = consume;
		this.materialManager = materialManager;
		this.productCost = productCost;
		this.wasteMaterial = wasteMaterial;
	}

	public Boolean checkProductMaterialsQuantityAvailability(Integer drinkCount){

		Container containerCapacity = Container.getCapacity();

		Map<String, Double> ingredients = consume.teaConsumption();

		Map<String, Double> quantityLeft = materialManager.getMaterialQuantiyLeft(containerCapacity, ingredients, drinkCount);

		if (quantityLeft.get("teaQuantityLeft") > drinkCount * ingredients.get("tea") 
				&& quantityLeft.get("milkQuantityLeft") > drinkCount * ingredients.get("milk") 
				&& quantityLeft.get("sugarQuantityLeft") > drinkCount * ingredients.get("sugar") 
				&& quantityLeft.get("waterQuantityLeft") > drinkCount * ingredients.get("water")) {

			containerCapacity.setTeaContainer(quantityLeft.get("teaQuantityLeft"));
			containerCapacity.setSugarContainer(quantityLeft.get("sugarQuantityLeft"));
			containerCapacity.setMilkContainer(quantityLeft.get("milkQuantityLeft"));
			containerCapacity.setWaterContainer(quantityLeft.get("waterQuantityLeft"));

			return true;

		} else{
			throw new InvalidInputException("invalid input");
		}
	}

	public Double getProductCost(Integer drinkCount){

		Double teaUnitCost = productCost.getProductCost().get("tea");
		Double totalCurrentOrderCost = drinkCount * teaUnitCost;
		getProductParameters(drinkCount, totalCurrentOrderCost);

		return totalCurrentOrderCost; 
	}

	public ProductParameters getProductParameters(Integer drinkCount,  Double totalCurrentOrderCost){

		productParameters = ProductParameters.getInstance();
		productParameters.setTeaCount(Optional.ofNullable(productParameters.getTeaCount()).orElse(0) + drinkCount);
		productParameters.setTotalTeaCost(Optional.ofNullable(productParameters.getTotalTeaCost()).orElse(0.0) + totalCurrentOrderCost);

		productParameters.setTotalCoffeeCost(Optional.ofNullable(productParameters.getTotalCoffeeCost()).orElse(0.0));
		productParameters.setTotalBlackTeaCost(Optional.ofNullable(productParameters.getTotalBlackTeaCost()).orElse(0.0));
		productParameters.setTotalBlackCoffeeCost(Optional.ofNullable(productParameters.getTotalBlackCoffeeCost()).orElse(0.0));

		productParameters.setTeaWaste(Optional.ofNullable(productParameters.getTeaWaste()).orElse(0.0) + (drinkCount * wasteMaterial.getTeaWastage().get("tea")));
		productParameters.setCoffeeWaste(Optional.ofNullable(productParameters.getCoffeeWaste()).orElse(0.0));
		productParameters.setWaterWaste(Optional.ofNullable(productParameters.getWaterWaste()).orElse(0.0) + (drinkCount * wasteMaterial.getTeaWastage().get("water")));
		productParameters.setMilkWaste(Optional.ofNullable(productParameters.getMilkWaste()).orElse(0.0) + (drinkCount * wasteMaterial.getTeaWastage().get("milk")));
		productParameters.setSugarWaste(Optional.ofNullable(productParameters.getSugarWaste()).orElse(0.0) + (drinkCount * wasteMaterial.getTeaWastage().get("sugar")));

		return productParameters; 
	} 

}
