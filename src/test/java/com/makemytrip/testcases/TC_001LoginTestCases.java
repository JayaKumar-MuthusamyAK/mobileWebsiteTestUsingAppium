package com.makemytrip.testcases;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.makemytrip.testbase.TestBase;
import com.makemytrip.testscripts.Keywords;
import com.makemytrip.testutils.TestUtils;

import io.appium.java_client.MobileElement;

public class TC_001LoginTestCases extends TestBase {

	
	@Test(dataProvider="getData")
	public void LoginPageTestCases(Hashtable<String, String> data) throws Exception{
		
		if(!TestUtils.isTestcasesExecutable("LoginPageTestCases", Keywords.xls))
			throw new SkipException("User is set this test cases runmode is No");
		
		if(!data.get("Runmode").equals("Y")){
			//Keywords.xls.setCellData("Test Data", "Expected_Result", TestUtils.getNum("loginTestwithDifferentscenarios", Keywords.xls,"Expected_Result"), "-");
			Keywords.xls.setCellDataInparticularCell("LoginPageTestCases", "Test Data", "Expected_Result", "-");
			throw new SkipException("User is set this test case data runmode is No");
		}
			
		Keywords keywords=Keywords.getInstance();
		keywords.executeKeywords("LoginPageTestCases", data);
	}
	
	@DataProvider
	public Object[][] getData(){
		
		return TestUtils.getData("LoginPageTestCases", Keywords.xls);
		
	}
	
	
}
