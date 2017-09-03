package com.makemytrip.testcasespractice;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class SampleTest {
	
	AppiumDriverLocalService service;
	String appiumURL;
	WebDriver driver;
	
	@BeforeTest
	public void startServer1() throws MalformedURLException{
		
		service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder().usingPort(4723).usingDriverExecutable(new File("C:/Program Files (x86)/Appium/node.exe")).
				withAppiumJS(new File("C:/Program Files (x86)/Appium/node_modules/appium/bin/appium.js")).withIPAddress("0.0.0.0"));
		//AppiumServiceBuilder builder = new AppiumServiceBuilder()
                //.withAppiumJS(new File("C:/Program Files (x86)/Appium/node_modules/appium/lib/server/main.js"));
		//service = builder.build();
        service.start();
		appiumURL = service.getUrl().toString();
		
		//service.start();
		System.out.println(appiumURL);
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", "Nexus7");
		capabilities.setCapability("platformName", "23");
		capabilities.setCapability("appPackage", "com.android.calculator2");
		capabilities.setCapability("appActivity", "com.android.calculator2.Calculator");
		
		driver = new AndroidDriver<>(new URL(appiumURL), capabilities);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		
	}
	
	@Test
	public void Sum() {
		System.out.println("Calculate sum of two numbers");
		// Locate elements to enter data and click +/= buttons
		driver.findElement(By.xpath("//*[@text='1']")).click();
		driver.findElement(By.xpath("//*[@text='+']")).click();
	}
	
	@AfterTest
	public void endTest(){
		
		driver.quit();
		service.stop();
	}

}
