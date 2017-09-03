package com.makemytrip.testscripts;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Hashtable;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;

import com.makemytrip.excelreader.Xls_Reader;
import com.makemytrip.testbase.TestBase;

public class Keywords extends TestBase {
	
	public static final Logger log = Logger.getLogger(TestBase.class.getName());

	public static Xls_Reader xls = new Xls_Reader(
			System.getProperty("user.dir") + "/src/main/java/com/makemytrip/testdata/TestSuite1.xlsx");
	public static Keywords keywords = null;
	
	public void executeKeywords(String testcases, Hashtable<String, String> data) throws Exception {

		loadproperties();
		
		log("Execute test suite name is :"+testcases);
		for (int rNum = 2; rNum <= xls.getRowCount("Test Steps"); rNum++) {

			String keyword = null;
			String objectkeys = null;
			String datakeys = null;
			if (testcases.equals(xls.getCellData("Test Steps", "TCID", rNum))) {
				keyword = xls.getCellData("Test Steps", "Keyword", rNum);
				objectkeys = xls.getCellData("Test Steps", "Object", rNum);
				datakeys = xls.getCellData("Test Steps", "Data", rNum);

				System.out.println(keyword + "--" + objectkeys + "--" + datakeys);
				log(keyword + "--" + objectkeys + "--" + datakeys);
				String result = null;
				
				ApplicationDependKeywords appKeywords = new ApplicationDependKeywords();
				GeneralKeywords genKeywords= new GeneralKeywords();
				switch (keyword) {
				case "startAppium":
					result = genKeywords.startAppium();
					break;
				case "navigateURL":
					result = genKeywords.navigateURL(objectkeys);
					break;
				case "verifyTitle":
					result = genKeywords.verifyTitle(datakeys);
					break;
				case "click":
					result = genKeywords.click(objectkeys);
					break;
				case "input":
					result = genKeywords.input(objectkeys,data.get(datakeys));
					break;
				case "directInput":
					result = genKeywords.directInput(objectkeys,datakeys);
					break;
				
				case "randomInput":
					result = genKeywords.randomInput(objectkeys,datakeys);
					break;
				case "waitForElementPresent":
					result = genKeywords.waitForElementPresent(objectkeys);
					break;
				case "waitForTextPresent":
					result = genKeywords.waitForTextPresent(objectkeys);
					break;
				case "verifyTotal":
					result=appKeywords.verifyTotal(objectkeys);
					break;
					//*******************Generic Keywords*********************//
				case "verifyValidation":
					result = appKeywords.verifyValidation(objectkeys,data.get(datakeys));
					break;
				case "chooseCityinList":
					result = appKeywords.chooseCityinList(objectkeys,datakeys);
					break;
				case "verifyLogin":
					result = appKeywords.verifyLogin(objectkeys);
					break;
				case "datePicker":
					result = genKeywords.datePicker(datakeys);
					break;
				case "numberOfPassengerSelect":
					result = genKeywords.numberOfPassengerSelect(datakeys);
					break;
				case "selectClass":
					result =genKeywords.selectClass(objectkeys,datakeys);
					break;
				case "selectflight":
					result=genKeywords.selectflight(objectkeys,Integer.parseInt(datakeys));
					break;
				case "verifyCalculation":
					result = genKeywords.verifyCalculation(objectkeys);
					break;
				case "verifyFarepageCalcultation":
					result = genKeywords.verifyFarepageCalcultation(objectkeys);
					break;
				case "checkAlertnotificationmsg":
					result = genKeywords.checkAlertnotificationmsg(objectkeys);
					break;
				case "verifyPassengerCount":
					result = genKeywords.verifyPassengerCount(objectkeys);
					break;
				case "stopAppium":
					result = genKeywords.stopAppium();
					break;
				default:
					System.out.println("Not Matching Keyword :"+keyword);
					break;
				}
			}
		}

	}

	
	
	public static Keywords getInstance(){
		
		if (keywords == null) {
			try {
				keywords = new Keywords();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		
	}
		return keywords;
	
	}
}
