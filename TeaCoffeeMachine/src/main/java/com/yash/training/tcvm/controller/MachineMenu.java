package com.yash.training.tcvm.controller;

import java.util.Map;

import com.yash.training.tcvm.CustomException.InvalidInputException;
import com.yash.training.tcvm.domain.Container;
import com.yash.training.tcvm.domain.ProductParameters;
import com.yash.training.tcvm.service.MaterialManager;
import com.yash.training.tcvm.service.Product;
import com.yash.training.tcvm.service.ProductFactory;
import com.yash.training.tvcm.util.CustomScanner;

public class MachineMenu {

	private ProductFactory productFactory;
	private Container containerQuantity;
	private MaterialManager materialManager;
	private ProductParameters productParameters;
	private CustomScanner scanner;

	private static final String PRODUCT_TEA = "Tea";
	private static final String PRODUCT_COFFEE = "Coffee";
	private static final String PRODUCT_BLACK_TEA = "Black Tea";
	private static final String PRODUCT_BLACK_COFFEE = "Black Coffee";

	public MachineMenu(){

		this.productFactory = new ProductFactory();
		this.scanner = new CustomScanner();
		this.materialManager = new MaterialManager();
	}

	public void selectOption(){

		containerQuantity = materialManager.initializeContainerCapacity();

		Boolean flag = true;

		while (flag) {

			System.out.println(" 1. Tea \n "
					+ "2. Coffee \n "
					+ "3. Black Coffee \n "
					+ "4. Black Tea \n "
					+ "5. Refill Container \n "
					+ "6. Check Total Sale \n "
					+ "7. Container Status \n "
					+ "8. Reset Status \n "
					+ "9. Exit" );

			System.out.println();
			System.out.println("Please select");
			Integer choice = scanner.getInputInteger();

			switch (choice) { 

			case 1:
				dispenseProduct(PRODUCT_TEA);

				break; 

			case 2:
				dispenseProduct(PRODUCT_COFFEE);

				break;

			case 3:
				dispenseProduct(PRODUCT_BLACK_COFFEE);

				break;

			case 4:
				dispenseProduct(PRODUCT_BLACK_TEA);

				break;

			case 5:	
				try {
					refillContainer();
				} catch (InvalidInputException e) {
					e.printStackTrace();
				}

				break;

			case 6:
				Map<String, Double> totalSale = materialManager.getTotalSale();
				productParameters = ProductParameters.getInstance();

				System.out.println("Total Tea count :    " + productParameters.getTeaCount() + "    Total Tea Sale :  " + totalSale.get("Tea Sale"));
				System.out.println("Total Coffee count :   " + productParameters.getCoffeeCount() + "  Total Coffee Sale: " + totalSale.get("Coffee Sale"));
				System.out.println("Total Black Tea count :  " + productParameters.getBlackTeaCount() + "  Total Black Coffee Sale: " + totalSale.get("Black Tea"));
				System.out.println("Total Black Coffee count : " + productParameters.getBlackCoffeeCount() + "  Total Black Tea Sale: " + totalSale.get("Black Coffee"));

				break;

			case 7:

				System.out.println("Container status : ");
				getContainerQuantityLeft();

				System.out.println();
				System.out.println("Material Wastage Status:");

				getWasteMaterialQuantity();

				break;

			case 8: 

				materialManager.initializeContainerCapacity();
				System.out.println("Container has been successfully filled");

				break; 

			default:

				System.out.println("Successfully Exited");
				flag = false;
				break;

			}
		}
	}

	public void dispenseProduct(String product){

		System.out.println("Please enter product count : ");

		Integer drinkCount = scanner.getInputInteger();

		Product selectedProduct = productFactory.getProduct(product);

		selectedProduct.checkProductMaterialsQuantityAvailability(drinkCount);

		Double totalOrderCost = selectedProduct.getProductCost(drinkCount);

		System.out.println("Order placed successfully " + "Please Pay : " + totalOrderCost);
	} 

	public void getContainerQuantityLeft(){ 

		System.out.println("Tea Container Quantity : " + containerQuantity.getTeaContainer());
		System.out.println("Coffee Container Quantity : " + containerQuantity.getCoffeeContainer());
		System.out.println("Sugar Container Quantity : " + containerQuantity.getSugarContainer());
		System.out.println("Milk Container Quantity : " + containerQuantity.getMilkContainer());
		System.out.println("Water Container Quantity : " + containerQuantity.getWaterContainer());

	}

	public void getWasteMaterialQuantity() {

		productParameters = ProductParameters.getInstance();

		System.out.println("Total Tea Wastage : " + productParameters.getTeaWaste());
		System.out.println("Total Coffee Wastage : " + productParameters.getCoffeeWaste());
		System.out.println("Total Sugar Wastage : " + productParameters.getSugarWaste());
		System.out.println("Total Milk Wastage : " + productParameters.getMilkWaste());
		System.out.println("Total Water Wastage : " + productParameters.getWaterWaste());
	}


	public void refillContainer() throws InvalidInputException{

		System.out.println("Please select container type: ");
		System.out.println(
				"1. Tea Container\n"
						+ "2. Coffee Container\n"
						+ "3. Milk Container\n"	
						+ "4. Sugar Container\n"
						+ "5. Water Container\n");

		Integer	containerChoice = scanner.getInputInteger();

		if(containerChoice<=5 && containerChoice>0){

			System.out.println("Please enter refill quantity");

			Double refillQuantity = scanner.getInputDouble(); 

			Boolean refillStatus = materialManager.refillSelectedContainer(containerChoice, refillQuantity);

			if(refillStatus) {
				System.out.println("Container Refilled Successfully!");
			} else {
				throw new InvalidInputException("Entered quantity is not valid. Please select again");
			}

		}else{		
			throw new InvalidInputException("select valid container to refill");
		}
	}
}
