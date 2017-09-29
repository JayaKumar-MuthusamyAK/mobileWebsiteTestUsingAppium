package com.makemytrip.testcasespractice;

import java.io.File;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.AndroidServerFlag;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

	public class AppiumServerStartStop {

		static WebDriver driver;
	    static String Appium_Node_Path="C:\\Program Files\\nodejs\\node.exe";
	    static String Appium_JS_Path="C:\\Program Files (x86)\\Appium\\node_modules\\appium\\bin\\appium.js";
	    static AppiumDriverLocalService service;
	    static String service_url;
	    @Test
	    public static void appiumStart() throws Exception{
	    	service = AppiumDriverLocalService
	    			.buildService(new AppiumServiceBuilder().usingAnyFreePort()
	    					.usingDriverExecutable(new File(Appium_Node_Path))
	    					.withAppiumJS(new File(Appium_JS_Path)));
	    	service.start();
	    	service_url = service.getUrl().toString();
	    	System.out.println("Appium Service Address : - " + service_url);
	    	
	    	

	    	// Created object of DesiredCapabilities class.
	    	DesiredCapabilities capabilities = new DesiredCapabilities();

	    	// Set android deviceName desired capabilitcy. Set your device name.
	    	capabilities.setCapability("deviceName", "c8c5d8f6");

	    	// Set BROWSER_NAME desired capability. It's Android in our case here.
	    	capabilities.setCapability(CapabilityType.BROWSER_NAME, "Android");

	    	// Set android VERSION desired capability. Set your mobile device's OS
	    	// version.
	    	capabilities.setCapability("platformVersion", "5.1.1");

	    	// Set android platformName desired capability. It's Android in our case
	    	// here.
	    	capabilities.setCapability("platformName", "Android");

	    	// Set android appPackage desired capability. It is
	    	// com.android.calculator2 for calculator application.
	    	// Set your application's appPackage if you are using any other app.
	    	capabilities.setCapability("appPackage", "com.android.calculator2");

	    	// Set android appActivity desired capability. It is
	    	// com.android.calculator2.Calculator for calculator application.
	    	// Set your application's appPackage if you are using any other app.
	    	capabilities.setCapability("appActivity",
	    			"com.android.calculator2.Calculator");

	    	// Created object of RemoteWebDriver will all set capabilities.
	    	// Set appium server address and port number in URL string.
	    	// It will launch calculator app in android device.
	    	driver = new RemoteWebDriver(new URL(service_url),
	    			capabilities);
	        service.start();
	        Thread.sleep(25000);
	        service_url = service.getUrl().toString();
	    }

	    public static void appiumStop() throws Exception{
	        service.stop();

	    }
	}