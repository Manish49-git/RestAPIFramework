package com.EmployeesAPI.Util;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Listners extends TestListenerAdapter{

	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	public void onStart (ITestContext testContext)
	{
        System.out.println("=====>> Test Started <<======");
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/Reports/myReport.html");
		htmlReporter.config().setDocumentTitle("Automation Report");
		htmlReporter.config().setReportName("Rest API Testing Report");
		htmlReporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Project Name", "API Automation Project");
		extent.setSystemInfo("Host Name", "Local Host");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("user", "Manish");		
	}
	

	public void onTestSuccess(ITestResult testResult)
	{
		test = extent.createTest(testResult.getName());
		test.log(Status.PASS, "Test Case Pass is --" +testResult.getName());
	}
	
	public void  onTestFailure(ITestResult testResult)
	{
		test = extent.createTest(testResult.getName());
		test.log(Status.FAIL, "Test Case Failed is --" +testResult.getName());
		test.log(Status.FAIL, "Test Case Failed is --" +testResult.getThrowable());
	}
	
	public void onTestSkipped(ITestResult testResult)
	{
		test = extent.createTest(testResult.getName());
		test.log(Status.SKIP, "Test Case Skipped is --" +testResult.getName());
	}
	
	public void onTestFlush(ITestContext testContext)
	{
		extent.flush();
	}

}
