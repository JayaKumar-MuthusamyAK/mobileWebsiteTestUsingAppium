package com.makemytrip.testcases;

import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.makemytrip.testscripts.Keywords;
import com.makemytrip.testutils.TestUtils;

public class TC_002SignUpPageTestCases {
	
	@Test(dataProvider="getData")
	public void SignUpPageTestCase(Hashtable<String, String> data) throws Exception{
		
		if(!TestUtils.isTestcasesExecutable("SignUpPageTestCase", Keywords.xls))
			throw new SkipException("User is set this test cases runmode is No");
		
		if(!data.get("Runmode").equals("Y")){
			//Keywords.xls.setCellData("Test Data", "Expected_Result", TestUtils.getNum("loginTestwithDifferentscenarios", Keywords.xls,"Expected_Result"), "-");
			Keywords.xls.setCellDataInparticularCell("SignUpPageTestCase", "Test Data", "Expected_Result", "-");
			throw new SkipException("User is set this test case data runmode is No");
		}
			
	
		Keywords keywords=Keywords.getInstance();
		keywords.executeKeywords("SignUpPageTestCase", data);
	}
	
	@DataProvider
	public Object[][] getData(){
		
		return TestUtils.getData("SignUpPageTestCase", Keywords.xls);
		
	}

}
