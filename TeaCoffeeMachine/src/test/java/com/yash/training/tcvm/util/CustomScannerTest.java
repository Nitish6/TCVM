package com.yash.training.tcvm.util;


import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import org.junit.Test;

import com.yash.training.tvcm.util.CustomScanner;

public class CustomScannerTest {

	@Test
	public void getInputDouble() {

		String input = "5.5";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);

		CustomScanner obj = new CustomScanner(new Scanner(System.in));
		Double s = obj.getInputDouble();

		assertEquals(new Double(5.5), s);

	}

	@Test
	public void getInputInteger() {

		String input = "5";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);

		CustomScanner obj = new CustomScanner(new Scanner(System.in));
		Integer s = obj.getInputInteger();

		assertEquals(new Integer(5), s);

	}
}


