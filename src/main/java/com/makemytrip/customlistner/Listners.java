package com.makemytrip.customlistner;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.makemytrip.testbase.TestBase;

public class Listners extends TestBase implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		log("Automation is Start Test Name is :"+result.getName());
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		if(result.isSuccess()){
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
			
			String methodName = result.getName();
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			
			
			try{
				String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath()+"//src//main//java//com//makemytrip//successScreenshots";
				File destFile = new File((String) reportDirectory +methodName +"-"+formater.format(calendar.getTime())+".png");
				FileUtils.copyFile(scrFile, destFile);
				Reporter.log("<a href='"+destFile.getAbsolutePath()+"'><img src='"+destFile.getAbsolutePath()+"'height ='100' widght ='100'/> </a>" );
			}
			catch(IOException e){
				System.out.println(e.getMessage());
			}
			}
		
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		if(!result.isSuccess()){
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
			
			String methodName = result.getName();
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			
			
			try{
				String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath()+"//src//main//java//com//makemytrip//failureScreenshots//";
				File destFile = new File((String) reportDirectory +methodName +"-"+formater.format(calendar.getTime())+".png");
				FileUtils.copyFile(scrFile, destFile);
				Reporter.log("<a href='"+destFile.getAbsolutePath()+"'><img src='"+destFile.getAbsolutePath()+"'height ='100' widght ='100'/> </a>" );
			}
			catch(IOException e){
				System.out.println(e.getMessage());
			}
			}
		
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

}
