package com.makemytrip.testcases;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.makemytrip.testbase.TestBase;
import com.makemytrip.testscripts.Keywords;
import com.makemytrip.testutils.TestUtils;

public class TC_005FlightListingPagefilterValidationTestCase extends TestBase{

	@Test
	public void flightListingPagefilterValidation() throws Exception{
		
		if(!TestUtils.isTestcasesExecutable("flightListingPagefilterValidation", Keywords.xls))
			throw new SkipException("This test case run mode is no");
		Keywords k = Keywords.getInstance();
		k.executeKeywords("flightListingPagefilterValidation", null);
	}
}
