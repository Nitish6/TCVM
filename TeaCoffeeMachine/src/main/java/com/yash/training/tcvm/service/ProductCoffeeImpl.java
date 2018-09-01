package com.yash.training.tcvm.service;

import java.util.Map;
import java.util.Optional;

import com.yash.training.tcvm.dao.ConsumptionMaterialQuantity;
import com.yash.training.tcvm.dao.ProductCost;
import com.yash.training.tcvm.dao.WasteMaterial;
import com.yash.training.tcvm.domain.Container;
import com.yash.training.tcvm.domain.ProductParameters;

public class ProductCoffeeImpl implements Product {

	private ProductParameters productParameters ;

	private ConsumptionMaterialQuantity consume;

	private MaterialManager materialManager;

	private ProductCost productCost;

	private WasteMaterial wasteMaterial; 

	public ProductCoffeeImpl(){  
		this.consume = new ConsumptionMaterialQuantity();
		this.productParameters = ProductParameters.getInstance();
		this.materialManager = new MaterialManager();
		this.productCost = new ProductCost();
	} 

	public ProductCoffeeImpl(ProductParameters productParameters, ConsumptionMaterialQuantity consume, MaterialManager materialManager, ProductCost productCost) {
		this.productParameters = productParameters;
		this.consume = consume;
		this.materialManager = materialManager;
		this.productCost = productCost;
	}

	public Boolean checkProductMaterialsQuantityAvailability(Integer drinkCount){

		Container containerCapacity = Container.getCapacity();

		Map<String, Double> ingredients = consume.coffeeConsumption();

		Map<String, Double> quantityLeft = materialManager.getMaterialQuantiyLeft(containerCapacity, ingredients, drinkCount);

		if (quantityLeft.get("coffeeQuantityLeft") > ingredients.get("coffee") 
				&& quantityLeft.get("milkQuantityLeft") > ingredients.get("milk") 
				&& quantityLeft.get("sugarQuantityLeft") > ingredients.get("sugar") 
				&& quantityLeft.get("waterQuantityLeft") > ingredients.get("water")) {

			containerCapacity.setTeaContainer(quantityLeft.get("teaQuantityLeft"));
			containerCapacity.setCoffeeContainer(quantityLeft.get("coffeeQuantityLeft"));
			containerCapacity.setSugarContainer(quantityLeft.get("sugarQuantityLeft"));
			containerCapacity.setMilkContainer(quantityLeft.get("milkQuantityLeft"));
			containerCapacity.setWaterContainer(quantityLeft.get("waterQuantityLeft"));

			return true;

		} else{
			throw new RuntimeException("not enough material present. Please reorder");
		}
	}

	public Double getProductCost(Integer drinkCount){

		Double coffeeUnitCost = productCost.getProductCost().get("coffee");
		Double totalCurrentOrderCost = drinkCount * coffeeUnitCost;
		getProductParameters(drinkCount, totalCurrentOrderCost);

		return totalCurrentOrderCost; 
	}

	public ProductParameters getProductParameters(Integer drinkCount,  Double totalCurrentOrderCost){

		wasteMaterial = new WasteMaterial();

		productParameters.setCoffeeCount(Optional.ofNullable(productParameters.getCoffeeCount()).orElse(0) + drinkCount);
		productParameters.setTotalCoffeeCost(Optional.ofNullable(productParameters.getTotalCoffeeCost()).orElse(0.0) + totalCurrentOrderCost);

		productParameters.setTotalTeaCost(Optional.ofNullable(productParameters.getTotalTeaCost()).orElse(0.0));
		productParameters.setTotalBlackTeaCost(Optional.ofNullable(productParameters.getTotalBlackTeaCost()).orElse(0.0));
		productParameters.setTotalBlackCoffeeCost(Optional.ofNullable(productParameters.getTotalBlackCoffeeCost()).orElse(0.0));

		productParameters.setCoffeeWaste(Optional.ofNullable(productParameters.getCoffeeWaste()).orElse(0.0) + (drinkCount * wasteMaterial.getCoffeeWastage().get("coffee")));
		productParameters.setTeaWaste(Optional.ofNullable(productParameters.getTeaWaste()).orElse(0.0));
		productParameters.setWaterWaste(Optional.ofNullable(productParameters.getWaterWaste()).orElse(0.0) + (drinkCount * wasteMaterial.getCoffeeWastage().get("water")));
		productParameters.setMilkWaste(Optional.ofNullable(productParameters.getMilkWaste()).orElse(0.0) + (drinkCount * wasteMaterial.getCoffeeWastage().get("milk")));
		productParameters.setSugarWaste(Optional.ofNullable(productParameters.getSugarWaste()).orElse(0.0) + (drinkCount * wasteMaterial.getCoffeeWastage().get("sugar")));

		return productParameters; 
	} 

}
