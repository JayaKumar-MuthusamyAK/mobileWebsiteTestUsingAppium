package com.makemytrip.testscripts;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.makemytrip.testbase.TestBase;

public class ApplicationDependKeywords extends TestBase{

	
public String verifyValidation(String objectkeys, String data) throws Exception {

		
		if (objectkeys.contains("|")) {
			int countnum = StringUtils.countMatches(objectkeys, "|");
			for (int i = 0; i <= countnum; i++) {
				if (getWebElements(objectkeys.split("\\|")[i]).size() != 0) {
					System.out.println(objectkeys.split("\\|")[i]);
					log(objectkeys.split("\\|")[i]);
					// getWebElement(name.split("\\|")[i]);
					Thread.sleep(3000);
					//System.out.println(getWebElement(objectkeys.split("\\|")[i]).getText());
					if(data.contains("@")){
						
							System.out.println("Logged in");
							getWebElement("homepage.menuoption").click();
							System.out.println(getWebElement("homepage.loginusername").getText());
							Assert.assertEquals(getWebElement("homepage.loginusername").getText(), data);
							break;
						
					}
					else if(getWebElement(objectkeys.split("\\|")[i]).getText().equals(data)){
						System.out.println("Not Logged");
						//Keywords.xls.setCellData("Test Data", "Expected_Result", TestUtils.getNum(testCasesName, Keywords.xls,"Expected_Result"), getWebElement(name.split("\\|")[i]).getText());
						//Keywords.xls.setCellDataInparticularCell(testCasesName, "Test Data", "Expected_Result", getWebElement(name.split("\\|")[i]).getText());
						Assert.assertEquals(getWebElement(objectkeys.split("\\|")[i]).getText(), data);
						break;
					}
				}
			}
		}

		// Please enter a valid Email Id
		return "Pass";
	}

	public String chooseCityinList(String objectkeys, String datakeys) throws Exception {
		
		
		int citiestot = getWebElements(objectkeys.split("\\|")[0]).size();
		
		/*for(int i=0; i<citiestot; i++){
			System.out.println(getWebElements(objectkeys.split("\\|")[0]).get(i).getText());
			log(getWebElements(objectkeys.split("\\|")[0]).get(i).getText());
		}*/
		getWebElement(objectkeys.split("\\|")[1]).sendKeys(datakeys);
		Thread.sleep(2000);
		getWebElement(objectkeys.split("\\|")[0]).click();
		
		return "Pass";
	}

	

	public String verifyLogin(String objectkeys) throws Exception {
		
		
					if(getWebElements(objectkeys.split("\\|")[0]).size()==1){
						getWebElement(objectkeys.split("\\|")[0]).click();
						getWebElement(objectkeys.split("\\|")[1]).click();
						
						System.out.println(getWebElement(objectkeys.split("\\|")[2]).getText());
						if(getWebElement(objectkeys.split("\\|")[2]).getText().equals("Logout")){
							System.out.println("Logged in successfully");
							log("Login User Mail ID is :"+objectkeys.split("|")[3]);
							
					}
					
				}
					else{
						System.out.println("Not Logged in");
					}
		
		return "Pass";
	}

	public String verifyTotal(String objectkeys) throws Exception {
		
		int actualcount=0;
		int no_ofFlighttotal= getWebElements(objectkeys.split("\\|")[0]).size();
		int no_offlightinsidecount= getWebElements(objectkeys.split("\\|")[1]).size();
		for(int i=0;i<no_offlightinsidecount;i++){
			//System.out.println(getWebElements(objectkeys.split("\\|")[1]).get(i).getText());
			actualcount+=Integer.parseInt(getWebElements(objectkeys.split("\\|")[1]).get(i).getText());
		}
		System.out.println(actualcount);
		System.out.println(no_ofFlighttotal);
		actualcount+=no_ofFlighttotal;
		System.out.println(getWebElement(objectkeys.split("\\|")[2]).getText());
		Assert.assertTrue(actualcount==Integer.parseInt(getWebElement(objectkeys.split("\\|")[2]).getText()), "Flight count is matching");
		return "Pass";
	}
	
}
