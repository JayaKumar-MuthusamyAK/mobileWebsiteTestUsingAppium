package com.makemytrip.testscripts;

import java.awt.Desktop.Action;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.makemytrip.testbase.TestBase;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidElement;

public class GeneralKeywords extends TestBase {

	public String startAppium() throws MalformedURLException, InterruptedException {
		log("Appium and Emulator device is Started..");
		startAppiumServer();
		return "Pass";
	}

	public String input(String objectkeys, String string) throws Exception {
		log("Entering the data to given element (Text Box");
		getWebElement(objectkeys).sendKeys(string);
		return "Pass";
	}

	public String click(String objectkeys) throws Exception {
		log("Clicking the given link or element");
		getWebElement(objectkeys).click();
		return "Pass";
	}

	public String verifyTitle(String datakeys) {
		// System.out.println(driver.getTitle());
		log("Verifing the title :"+driver.getTitle());
		Assert.assertEquals(driver.getTitle(), datakeys);
		return "Pass";
	}

	public String navigateURL(String objectKeys) {

		// System.out.println(prop.getProperty(objectKeys));
		log("Navigating to this URL :"+prop.getProperty(objectKeys));
		driver.get(prop.getProperty(objectKeys));
		return "Pass";
	}

	public String randomInput(String objectkeys, String string) throws Exception {

		log("Random Input generating for string and entering the values into the given element");
		if (string.contains("@")) {
			Random random = new Random();
			int r = random.nextInt(10000);
			String var1 = string.split("@")[0];
			String var2 = string.split("@")[1];
			String var3 = var1 + r + "@" + var2;
			log("Generated Email Address is :" + var3);

			getWebElement(objectkeys).sendKeys(var3);
		} else {
			getWebElement(objectkeys).sendKeys(string);
		}

		return "Pass";
	}


	public String waitForElementPresent(String objectkeys) throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		log("Waiting for the given element present");
		if (objectkeys.contains("|")) {
			int countnum = StringUtils.countMatches(objectkeys, "|");
			for (int i = 0; i <= countnum; i++) {
				if (getWebElements(objectkeys.split("\\|")[i]).size() != 0) {
					wait.until(ExpectedConditions.visibilityOf(getWebElement(objectkeys.split("\\|")[i])));
					break;
				}
			}
		} else {
			wait.until(ExpectedConditions.visibilityOf(getWebElement(objectkeys)));
		}
		return "Pass";
	}

	public String waitForTextPresent(String objectkeys) throws Exception {

		log("Waiting for given text present ");
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.visibilityOf(getWebElement(objectkeys)));
		return "Pass";
	}

	public String datePicker(String datakeys) throws Exception {

		log("selecting the date into the date picker");
		int monthyeartot = getWebElements("homepage.checkin.datepicker.monthyear").size();
		// System.out.println(monthyeartot);
		Actions act = new Actions(driver);
		for (int count = 0; count < monthyeartot; count++) {
			// System.out.println(getWebElements("homepage.checkin.datepicker.monthyear").get(count).getText());
			act.moveToElement(getWebElements("homepage.checkin.datepicker.monthyear").get(count)).build().perform();
			if (getWebElements("homepage.checkin.datepicker.monthyear").get(count).getText().equals("November 2017")) {
				break;
			}
		}

		driver.findElement(By.xpath("//td[@data-date='" + datakeys + "']")).click();
		return "Pass";
	}

	public String numberOfPassengerSelect(String datakeys) throws Exception {

		
		int adult = Integer.parseInt(datakeys.split("\\|")[0]);
		System.out.println(adult);
		int child = Integer.parseInt(datakeys.split("\\|")[1]);
		int infants = Integer.parseInt(datakeys.split("\\|")[2]);
		log("Selecting the Number of passenger element adult count is "+adult+" Child Count is "+child+" Infants Count is "+infants);
		commanPassengermethodchooser("homepage.adultcount", "homepage.adult.addbtn", adult);
		commanPassengermethodchooser("homepage.childrencount", "homepage.children.addbtn", child);
		commanPassengermethodchooser("homepage.infantscount", "homepage.infants.addbtn", infants);

		return "Pass";
	}

	public void commanPassengermethodchooser(String xpath1, String xpath2, int adult)
			throws NumberFormatException, Exception {
		int actualcount = Integer.parseInt(getWebElement(xpath1).getText());
		System.out.println(actualcount);
		while (actualcount != adult) {
			getWebElement(xpath2).click();
			actualcount++;
		}
	}

	public String selectClass(String objectKeys, String datakeys) throws Exception {

		int size = getWebElements(objectKeys).size();
		log("Selecting the given class"+datakeys);
		for (int i = 0; i < size; i++) {
			if (getWebElements(objectKeys).get(i).getText().equals(datakeys)) {
				System.out.println(getWebElements(objectKeys).get(i).getText());
				getWebElements(objectKeys).get(i).click();
			}
		}

		return "Pass";
	}

	public String selectflight(String objectkeys, int selectflightno) throws Exception {

		Actions act = new Actions(driver);
		int noOfflightavailable = getWebElements(objectkeys.split("\\|")[0]).size();

		log("Selecting the given flight no");
		for (int i = 0; i < noOfflightavailable; i++) {

			if (selectflightno == i) {
				act.moveToElement(getWebElements(objectkeys.split("\\|")[0]).get(i)).build().perform();
				System.out.println(getWebElements(objectkeys.split("\\|")[0]).get(i).getText());
				System.out.println(getWebElements(objectkeys.split("\\|")[1]).get(i).getText());
				System.out.println(getWebElements(objectkeys.split("\\|")[2]).get(i).getText());
				getWebElements(objectkeys.split("\\|")[0]).get(i).click();
				break;
			}

		}

		return "Pass";
	}

	public String verifyCalculation(String objectkeys) throws NumberFormatException, Exception {

		log("Verifying the price");
		int departureprice = Integer.parseInt(getWebElement(objectkeys.split("\\|")[0]).getText().replace(",", ""));
		int returnprice = Integer.parseInt(getWebElement(objectkeys.split("\\|")[1]).getText().replace(",", ""));
		int totalprice = Integer.parseInt(getWebElement(objectkeys.split("\\|")[2]).getText().replace(",", ""));
		int verifytotal = departureprice + returnprice;
		System.out.println(totalprice + "-" + verifytotal);
		Assert.assertEquals(verifytotal, totalprice);
		return "Pass";
	}

	public String verifyFarepageCalcultation(String objectkeys) throws NumberFormatException, Exception {
		log("Verifying the Fare price It is matching with Expected result");
		int finalprice = 0;
		int expectedPrice = 0;
		Thread.sleep(10000);
		if (objectkeys.contains("|")) {
			int countnum = StringUtils.countMatches(objectkeys, "|");
			for (int i = 0; i <= countnum; i++) {
				if (i == countnum) {
					System.out.println(
							"Last total:" + getWebElement(objectkeys.split("\\|")[i]).getText().replace(",", ""));
					expectedPrice = Integer
							.parseInt(getWebElement(objectkeys.split("\\|")[i]).getText().replace(",", ""));
					break;
				} else {
					System.out.println(getWebElement(objectkeys.split("\\|")[i]).getText().replace(",", ""));
					finalprice += Integer
							.parseInt(getWebElement(objectkeys.split("\\|")[i]).getText().replace(",", ""));
				}
			}
			Assert.assertTrue(finalprice == expectedPrice, "Price is Matching with expected result");
		}

		return "Pass";
	}

	public String checkAlertnotificationmsg(String objectkeys) throws Exception {
		log("Checking the Alert is present or Not If it is present will close");
		Thread.sleep(10000);
		if (getWebElement(objectkeys).isDisplayed()) {
			getWebElement(objectkeys).click();
		}
		return "Pass";
	}

	public String verifyPassengerCount(String objectkeys) throws Exception {
		log("Verifying the passenger Count");
		int footercount = Integer.parseInt(getWebElement(objectkeys.split("\\|")[0]).getText().trim());
		int headercount = Integer
				.parseInt(getWebElement(objectkeys.split("\\|")[1]).getText().replace("Travellers", "").trim());
		log("Footer Travellers count is " + footercount);
		log("Header Travellers count is " + headercount);
		Assert.assertTrue(footercount == headercount, "Travellers Count is matching...");
		return "Pass";
	}

	public String stopAppium() throws InterruptedException {

		// Runtime runtime = Runtime.getRuntime();
		/*if(driver!=null){
			driver.quit();
		}*/
		log("Stoping the Appium Server");
		//stopAppiumServer();
		//Thread.sleep(10000);
		return "Pass";
	}

	public String waitForAlert() throws InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(driver, 45);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@class='optIn-popup_reminder']")));
		if(driver.findElements(By.xpath("//a[@class='optIn-popup_reminder']")).size()!=0){
			driver.findElement(By.xpath("//a[@class='optIn-popup_reminder']")).click();
		}
		return "Pass";
	}

}
