package com.makemytrip.testcasespractice;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Annotations {

	
	@BeforeMethod
	public void initializeSetup(){
		System.out.println("Initialization for Appium");
	}
	
	@Test
	public void testCase1(){
		System.out.println("Test case NO 1");
	}
	
	@Test
	public void testCase2(){
		System.out.println("Test case NO 2");
	}
	
	@AfterMethod
	public void endDriver(){
		System.out.println("Ending the Test");
	}
}
