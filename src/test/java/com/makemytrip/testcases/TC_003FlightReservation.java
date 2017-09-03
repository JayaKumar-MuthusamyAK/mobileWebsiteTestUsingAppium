package com.makemytrip.testcases;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.makemytrip.testscripts.Keywords;
import com.makemytrip.testutils.TestUtils;

public class TC_003FlightReservation {
	
	@Test
	public void flightReseverationpage() throws Exception{
		
		if(!TestUtils.isTestcasesExecutable("flightReservationpage", Keywords.xls))
			throw new SkipException("This test case execution runmode is no");
		Keywords k = Keywords.getInstance();
		k.executeKeywords("flightReservationpage", null);
		
	}

}
