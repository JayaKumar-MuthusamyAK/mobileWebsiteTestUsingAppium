package com.makemytrip.testcasespractice;

import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.appium.java_client.android.AndroidDriver;

public class Test {

	
	private AndroidDriver driver;

	@BeforeClass
	public void setUp() throws Exception {
	    DesiredCapabilities capabilities = new DesiredCapabilities();
	    capabilities.setCapability("deviceName", "MOTO G 2");
	    capabilities.setCapability("platformName", "Android");
	    capabilities.setCapability(CapabilityType.BROWSER_NAME, "Chrome");
	    capabilities.setCapability("platformVersion", "5.0.2");
	    capabilities.setCapability("appPackage", "com.android.chrome");
	    capabilities.setCapability("appActivity","com.google.android.apps.chrome.ChromeTabbedActivity");
	    driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
	}

	@org.testng.annotations.Test
	public void testcase_001() throws Exception{
	    driver.get("http://www.google.com");

	    WebElement keyword = driver.findElementByName("q");
	    keyword.sendKeys("appium");
	    driver.findElement(By.id("btnK")).click();
	    Thread.sleep(5000);
	 }

	@AfterClass
	public void tearDown() throws Exception {
	    driver.quit();
	}
}
