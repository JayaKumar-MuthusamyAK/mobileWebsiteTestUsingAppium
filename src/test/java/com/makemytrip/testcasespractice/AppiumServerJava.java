package com.makemytrip.testcasespractice;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.github.genium_framework.appium.support.server.AppiumServer;
import com.github.genium_framework.server.ServerArguments;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
 
public class AppiumServerJava {
	
	static DesiredCapabilities cap = new DesiredCapabilities();
	static AppiumDriver<MobileElement> driver;
	AppiumServer _appiumServer =null;
	public void startServer1() {
		
		
		ServerArguments serverArguments = new ServerArguments();
		serverArguments.setArgument("--address", "0.0.0.0");
		serverArguments.setArgument("--chromedriver-port", 9516);
		serverArguments.setArgument("--bootstrap-port", 4723);
		serverArguments.setArgument("--no-reset", true);
		serverArguments.setArgument("--local-timezone", true);

	   // _appiumServer = new AppiumServer(new File("C:\\Program Files\\nodejs\\node.exe"),
		//		new File("C:\\Users\\admin\\AppData\\Roaming\\npm\\node_modules\\appium\\lib\\main.js"));
	  //  AppiumServer _appiumServer = new AppiumServer(new File("C:\\Program Files\\nodejs\\node.exe",serverArguments);
		//C:\Users\admin\AppData\Local\Programs\appium-desktop\\Appium.exe
		_appiumServer = new AppiumServer(new File("C:\\Users\\admin\\AppData\\Local\\Programs\\appium-desktop\\Appium.exe"),serverArguments);

		_appiumServer.startServer();
	}
	
	public void stopServer() {
		//Runtime runtime = Runtime.getRuntime();
		try {
			_appiumServer.stopServer();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) throws MalformedURLException {
		AppiumServerJava appiumServer = new AppiumServerJava();
		appiumServer.startServer1();
 
		
		cap.setCapability("platformName", Platform.ANDROID);
		cap.setCapability("platformVerion","23"); 
		cap.setCapability("deviceName", "Nexus7");
		cap.setCapability("appPackage", "com.android.dialer");
		cap.setCapability("appActivity", "com.android.dialer.DialtactsActivity");
		//driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), cap);	
		//driver.quit();
		
		appiumServer.stopServer();
	}
}