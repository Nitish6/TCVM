package com.yash.training.tcvm.domain;

public class Container {

	private Double teaContainer;
	private Double coffeeContainer;
	private Double sugarContainer;
	private Double waterContainer;
	private Double milkContainer;

	/*private Double teaWaste;
	private Double coffeeWaste;
	private Double sugarWaste;
	private Double waterWaste;
	private Double milkWaste;*/

	private static Container containerQuantity;

	private Container() {

	}

	public static Container getCapacity() {
		if (containerQuantity == null) {
			containerQuantity = new Container();
		}
		return containerQuantity;
	}

	public Double getTeaContainer() {
		return teaContainer;
	}

	public void setTeaContainer(Double teaContainer) {
		this.teaContainer = teaContainer;
	}

	public Double getCoffeeContainer() {
		return coffeeContainer;
	}

	public void setCoffeeContainer(Double coffeeContainer) {
		this.coffeeContainer = coffeeContainer;
	}

	public Double getSugarContainer() {
		return sugarContainer;
	}

	public void setSugarContainer(Double sugarContainer) {
		this.sugarContainer = sugarContainer;
	}

	public Double getWaterContainer() {
		return waterContainer;
	}

	public void setWaterContainer(Double waterContainer) {
		this.waterContainer = waterContainer;
	}

	/*public Double getTeaWaste() {
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
	}*/

	public Double getMilkContainer() {
		return milkContainer;
	}

	public void setMilkContainer(Double milkContainer) {
		this.milkContainer = milkContainer;
	}
}
