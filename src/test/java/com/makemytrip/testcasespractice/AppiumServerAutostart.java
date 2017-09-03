package com.makemytrip.testcasespractice;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;

import org.apache.commons.exec.CommandLine;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
public class AppiumServerAutostart {
	
	WebDriver driver;
	   static AppiumDriverLocalService service;
	   static String service_url;
	   static Runtime runtime = Runtime.getRuntime();
	   @BeforeClass
	   public static void appiumServer() throws Exception {
	   
/*		service = AppiumDriverLocalService
				.buildService(
						new AppiumServiceBuilder()
								.withAppiumJS(new File(
										"C\\Users\\admin\\AppData\\Roaming\\npm\\node_modules\\appium\\lib\\main.js"))
								.usingPort(4723).withIPAddress("127.0.0.1"));*/
	     
		  // Runtime.getRuntime().exec("appium");
		   Runtime runtime = Runtime.getRuntime();
			try {
				runtime.exec("cmd /c start appium");
				Thread.sleep(20000);
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}
		  startadbDevice();
		  service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder().usingDriverExecutable(new 
		   File("C:\\Program Files\\nodejs\\node.exe")).withAppiumJS(new File("C:\\Program Files (x86)\\Appium\\node_modules\\appium\\bin\\appium.js"))
		   .withIPAddress("127.0.0.1")
		   .usingPort(4723)
		   .withLogFile(new File("AppiumServerLogs.txt")
		   ));
	       service_url = service.getUrl().toString();
	      // service.start();
	      // service.start();
	   }

	   public static void startadbDevice() {
		   
			try {
				runtime.exec("cmd /c start emulator @Nexus7");
				Thread.sleep(20000);
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}
		   
		
	}

	@BeforeMethod
	   public void testCaseSetUp() throws MalformedURLException {
	      
	       DesiredCapabilities cap = new DesiredCapabilities();
	       cap.setCapability(MobileCapabilityType.BROWSER_NAME, BrowserType.CHROME);
	       cap.setCapability(MobileCapabilityType.PLATFORM, Platform.ANDROID);
	       cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
	       cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Nexus7");
	      // cap.setCapability(MobileCapabilityType.VERSION, "6.0");
	      // cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "100");
	       
	       
	       URL url = new URL(service_url);
			
	      driver = new AndroidDriver<MobileElement>(url,cap);
	      driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	       //fresh installs the app everytime if fullReset to True
	       
	       //cap.setCapability("fullReset", true);
	      
	   }

	   @Test
	   public void testHelloWorld() throws Exception {
	       driver.get("http://makemytrip.com");
	       System.out.println(driver.getTitle());
	       
	   }

	   @AfterMethod
	   public void testCaseTearDown() {
	       if (driver != null)
	          // driver.quit();
	    	   System.out.println("");
	   }

	@AfterClass
	   public  static  void stopServer(){
		
	       //service.stop();
	   }
}
