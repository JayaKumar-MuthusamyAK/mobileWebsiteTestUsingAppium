package com.makemytrip.testbase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;

import com.makemytrip.testscripts.Keywords;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class TestBase {

	public static AndroidDriver<WebElement> driver;
	public static DesiredCapabilities cap;
	public Runtime runtime = Runtime.getRuntime();
	static AppiumDriverLocalService service;
	static String service_url;
	public static final Logger log = Logger.getLogger(TestBase.class.getName());
	// Start Appium server and Open given emulator device

	public static Properties prop;
	public static FileInputStream fileinputstream;

	public String startAppiumServer() throws MalformedURLException {

		openEmulator();

		try {
			runtime.exec("cmd /c start appium");
			Thread.sleep(25000);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}

		// openEmulator();
		service = AppiumDriverLocalService
				.buildService(
						new AppiumServiceBuilder()
								.usingDriverExecutable(new File("C:\\Program Files\\nodejs\\node.exe"))
								.withAppiumJS(new File(
										"C:\\Program Files (x86)\\Appium\\node_modules\\appium\\bin\\appium.js"))
								.withIPAddress("127.0.0.1").usingPort(4723)
								.withLogFile(new File("AppiumServerLogs.txt")));
		service_url = service.getUrl().toString();

		selectBrowser();

		return "Pass";

	}

	public void openEmulator() {
		try {
			runtime.exec("cmd /c start emulator @Nexus7");
			Thread.sleep(20000);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void selectBrowser() throws MalformedURLException {
		cap = new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.BROWSER_NAME, BrowserType.CHROME);
		cap.setCapability(MobileCapabilityType.PLATFORM, Platform.ANDROID);
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Nexus7");
		// cap.setCapability(MobileCapabilityType.VERSION, "6.0");
		// cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "100");

		URL url = new URL(service_url);

		driver = new AndroidDriver<WebElement>(url, cap);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

	}

	public void loadproperties() throws IOException {
		prop = new Properties();
		try {

			fileinputstream = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\main\\java\\com\\makemytrip\\config\\confiq.properties");
			prop.load(fileinputstream);

			fileinputstream = new FileInputStream(System.getProperty("user.dir")
					+ "\\src\\main\\java\\com\\makemytrip\\pagelocator\\homepage.properties");
			prop.load(fileinputstream);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static WebElement getLocator(String locator) throws Exception {
		String[] split = locator.split(":");
		String locatorType = split[0];
		String locatorValue = split[1];

		if (locatorType.toLowerCase().equals("id"))
			return driver.findElement(By.id(locatorValue));
		else if (locatorType.toLowerCase().equals("name"))
			return driver.findElement(By.name(locatorValue));
		else if ((locatorType.toLowerCase().equals("classname")) || (locatorType.toLowerCase().equals("class")))
			return driver.findElement(By.className(locatorValue));
		else if ((locatorType.toLowerCase().equals("tagname")) || (locatorType.toLowerCase().equals("tag")))
			return driver.findElement(By.className(locatorValue));
		else if ((locatorType.toLowerCase().equals("linktext")) || (locatorType.toLowerCase().equals("link")))
			return driver.findElement(By.linkText(locatorValue));
		else if (locatorType.toLowerCase().equals("partiallinktext"))
			return driver.findElement(By.partialLinkText(locatorValue));
		else if ((locatorType.toLowerCase().equals("cssselector")) || (locatorType.toLowerCase().equals("css")))
			return driver.findElement(By.cssSelector(locatorValue));
		else if (locatorType.toLowerCase().equals("xpath"))
			return driver.findElement(By.xpath(locatorValue));
		else
			throw new Exception("Unknown locator type '" + locatorType + "'");
	}

	public static List<WebElement> getLocators(String locator) throws Exception {

		String[] split = locator.split(":");
		String locatorType = split[0];
		String locatorValue = split[1];

		if (locatorType.toLowerCase().equals("id"))
			return driver.findElements(By.id(locatorValue));
		else if (locatorType.toLowerCase().equals("name"))
			return driver.findElements(By.name(locatorValue));
		else if ((locatorType.toLowerCase().equals("classname")) || (locatorType.toLowerCase().equals("class")))
			return driver.findElements(By.className(locatorValue));
		else if ((locatorType.toLowerCase().equals("tagname")) || (locatorType.toLowerCase().equals("tag")))
			return driver.findElements(By.className(locatorValue));
		else if ((locatorType.toLowerCase().equals("linktext")) || (locatorType.toLowerCase().equals("link")))
			return driver.findElements(By.linkText(locatorValue));
		else if (locatorType.toLowerCase().equals("partiallinktext"))
			return driver.findElements(By.partialLinkText(locatorValue));
		else if ((locatorType.toLowerCase().equals("cssselector")) || (locatorType.toLowerCase().equals("css")))
			return driver.findElements(By.cssSelector(locatorValue));
		else if (locatorType.toLowerCase().equals("xpath"))
			return driver.findElements(By.xpath(locatorValue));
		else
			throw new Exception("Unknown locator type '" + locatorType + "'");
	}

	public WebElement getWebElement(String locator) throws Exception {
		return getLocator(prop.getProperty(locator));

	}

	public List<WebElement> getWebElements(String locators) throws Exception {

		return getLocators(prop.getProperty(locators));

	}

	@AfterMethod
	public void afterExecution(ITestResult result) throws InterruptedException {
		getStatus(result);

	}

	public void getStatus(ITestResult result) throws InterruptedException {

		if (result.getStatus() == ITestResult.SUCCESS) {

			// Keywords.xls.setCellData("Test Data", "Status",
			// TestUtils.getNum(result.getMethod().getMethodName(),
			// Keywords.xls,"Status"), "PASS");
			Keywords.xls.setCellDataInparticularCell(result.getMethod().getMethodName(), "Test Data", "Status", "PASS");
			// extenttest.log(LogStatus.PASS, "test is pass"+result.getName());
		} else if (result.getStatus() == ITestResult.FAILURE) {
			// Keywords.xls.setCellData("Test Data", "Status",
			// TestUtils.getNum(result.getMethod().getMethodName(),
			// Keywords.xls,"Status"), "FAIL");
			Keywords.xls.setCellDataInparticularCell(result.getMethod().getMethodName(), "Test Data", "Status", "FAIL");
			stopAppiumServer();
			// extenttest.log(LogStatus.ERROR, result.getName()+"test is
			// failed"+result.getThrowable());
			// extenttest.log(LogStatus.FAIL, result.getName()+"test is
			// failed"+extenttest.addScreenCapture(catureScreen("")));
		} else if (result.getStatus() == ITestResult.SKIP) {
			// Keywords.xls.setCellData("Test Data", "Status",
			// TestUtils.getNum(result.getMethod().getMethodName(),
			// Keywords.xls,"Status"), "SKIP");
			Keywords.xls.setCellDataInparticularCell(result.getMethod().getMethodName(), "Test Data", "Status", "SKIP");
			// extenttest.log(LogStatus.SKIP, result.getName()+"test is
			// skip"+result.getThrowable());
		} else if (result.getStatus() == ITestResult.STARTED) {
			// extenttest.log(LogStatus.INFO, result.getName()+"Test is
			// Started");
		}

	}

	public String stopAppiumServer() throws InterruptedException {

		// Runtime runtime = Runtime.getRuntime();
		try {
			runtime.exec("cmd /c start adb -s emulator-5554 emu kill");
			runtime.exec("taskkill /F /IM node.exe");
			runtime.exec("taskkill /F /IM cmd.exe");
			Thread.sleep(20000);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "Pass";
	}

	public void log(String data) {

		log.info(data);
		Reporter.log(data);
	}

}
