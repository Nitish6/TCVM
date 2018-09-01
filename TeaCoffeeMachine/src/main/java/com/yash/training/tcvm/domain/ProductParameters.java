package com.yash.training.tcvm.domain;

public class ProductParameters {

	private Integer teaCount;
	private Integer coffeeCount;
	private Integer blackCoffeeCount;
	private Integer blackTeaCount;

	private Double totalTeaCost;
	private Double totalCoffeeCost;
	private Double totalBlackCoffeeCost;
	private Double totalBlackTeaCost;

	private Double teaWaste;
	private Double coffeeWaste;
	private Double sugarWaste;
	private Double waterWaste;
	private Double milkWaste;

	private static ProductParameters productParameters;

	private ProductParameters() {

	}

	public static ProductParameters getInstance() {
		if (productParameters == null) {
			productParameters = new ProductParameters();
		}
		return productParameters;
	}

	public Double getTeaWaste() {
		return teaWaste;
	}

	public void setTeaWaste(Double teaWaste) {
		this.teaWaste = teaWaste;
	}

	public Double getCoffeeWaste() {
		return coffeeWaste;
	}

	public void setCoffeeWaste(Double coffeeWaste) {
		this.coffeeWaste = coffeeWaste;
	}

	public Double getSugarWaste() {
		return sugarWaste;
	}

	public void setSugarWaste(Double sugarWaste) {
		this.sugarWaste = sugarWaste;
	}

	public Double getWaterWaste() {
		return waterWaste;
	}

	public void setWaterWaste(Double waterWaste) {
		this.waterWaste = waterWaste;
	}

	public Double getMilkWaste() {
		return milkWaste;
	}

	public void setMilkWaste(Double milkWaste) {
		this.milkWaste = milkWaste;
	}

	public Integer getTeaCount() {
		return teaCount;
	}

	public void setTeaCount(Integer teaCount) {
		this.teaCount = teaCount;
	}

	public Integer getCoffeeCount() {
		return coffeeCount;
	}

	public void setCoffeeCount(Integer coffeeCount) {
		this.coffeeCount = coffeeCount;
	}

	public Integer getBlackCoffeeCount() {
		return blackCoffeeCount;
	}

	public void setBlackCoffeeCount(Integer blackCoffeeCount) {
		this.blackCoffeeCount = blackCoffeeCount;
	}

	public Integer getBlackTeaCount() {
		return blackTeaCount;
	}

	public void setBlackTeaCount(Integer blackTeaCount) { 
		this.blackTeaCount = blackTeaCount;
	}

	public Double getTotalTeaCost() {
		return totalTeaCost;
	}

	public void setTotalTeaCost(Double totalTeaCost) {
		this.totalTeaCost = totalTeaCost;
	}

	public Double getTotalCoffeeCost() {
		return totalCoffeeCost;
	}

	public void setTotalCoffeeCost(Double totalCoffeeCost) {
		this.totalCoffeeCost = totalCoffeeCost;
	}

	public Double getTotalBlackCoffeeCost() {
		return totalBlackCoffeeCost;
	}

	public void setTotalBlackCoffeeCost(Double totalBlackCoffeeCost) {
		this.totalBlackCoffeeCost = totalBlackCoffeeCost;
	}

	public Double getTotalBlackTeaCost() {
		return totalBlackTeaCost;
	}

	public void setTotalBlackTeaCost(Double totalBlackTeaCost) {
		this.totalBlackTeaCost = totalBlackTeaCost;
	}
}
