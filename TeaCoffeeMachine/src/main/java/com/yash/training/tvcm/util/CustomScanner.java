package com.yash.training.tvcm.util;

import java.util.Scanner;

public class CustomScanner {

	public final Scanner scanner;

	public CustomScanner() {

		this(new Scanner(System.in));
	}

	public CustomScanner(Scanner scanner) {

		this.scanner = scanner;
	}

	public Integer getInputInteger() {

		return scanner.nextInt();
	}

	public Double getInputDouble() {

		return scanner.nextDouble();
	}

}
