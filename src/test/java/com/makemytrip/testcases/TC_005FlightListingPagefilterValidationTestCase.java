package com.makemytrip.testcases;

import org.apache.log4j.Logger;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.makemytrip.testbase.TestBase;
import com.makemytrip.testscripts.Keywords;
import com.makemytrip.testutils.TestUtils;

public class TC_005FlightListingPagefilterValidationTestCase extends TestBase{

	public static final Logger log = Logger.getLogger(TestBase.class.getName());
	@Test
	public void flightListingPagefilterValidation() throws Exception{
		
		if(!TestUtils.isTestcasesExecutable("flightListingPagefilterValidation", Keywords.xls))
			throw new SkipException("This test case run mode is no");
		Keywords k = Keywords.getInstance();
		k.executeKeywords("flightListingPagefilterValidation", null);
	}
}
