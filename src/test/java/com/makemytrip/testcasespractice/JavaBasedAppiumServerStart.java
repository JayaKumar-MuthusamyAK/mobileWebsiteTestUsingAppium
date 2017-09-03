package com.makemytrip.testcasespractice;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.Executor;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;

public class JavaBasedAppiumServerStart {
	static AndroidDriver driver;
	public static void main(String[] args) throws ExecuteException, IOException {
		
		
		CommandLine command = new CommandLine("cmd");
		command.addArgument("c/");
		command.addArgument("C:\\Program Files\\nodejs\\node.exe");
		command.addArgument("C:\\Program Files (x86)\\Appium\\node_modules\\appium\\bin\\appium.js");
		command.addArgument("--address");
		command.addArgument("0.0.0.0");
		command.addArgument("--port");
		command.addArgument("4723");
		command.addArgument("--no-reset");
		/*"C:\Users\admin>"+"C:\Program Files\nodejs\node.exe" "C:\Program Files (x86)\Appium\node_modules\appium\bin\appium.js" 
		"--address 127.0.0.1 --chromedriver-port 9516 --bootstrap-port 4723 --selendroid-port 8082 --no-reset --local-timezone"*/
		DefaultExecuteResultHandler executeresult = new DefaultExecuteResultHandler();
		
		Executor test = new DefaultExecutor();
		test.setExitValue(1);
		test.execute(command, executeresult);
		File appDir = new File("src");
	       File app = new File(appDir, "app-debug.apk");
		DesiredCapabilities cap = new DesiredCapabilities();
	       cap.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
	       cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Nexus7");
	       cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "100");
	       cap.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
	      
	}

}
