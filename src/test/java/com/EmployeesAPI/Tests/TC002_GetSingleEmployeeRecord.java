package com.EmployeesAPI.Tests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.EmployeesAPI.Base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC002_GetSingleEmployeeRecord extends TestBase{

	@BeforeClass
	void getAllEmployees () throws InterruptedException 

	{
		logger.info("<<*********TC002_GetSingleEmployeesRecord Started**********>>");
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET, "/employee/"+empID);		
		Thread.sleep(3000);
	}
	
	@Test
	void checkResponseBody()
	{
		logger.info("<<*********Checking Response Body**********>>");
		String responseBody = response.getBody().asString();
		logger.info("Response Body ==> "+responseBody);
		//Assert.assertEquals(responseBody.contains(empID),true);
	}
	
	@Test
	void checkStatusCode()
	{
		logger.info("<<*********Checking Status Code**********>>");
		int statusCode = response.getStatusCode();	
		Assert.assertEquals(statusCode,200);
	}
	@Test
	void checkResponseTime()
	{
		logger.info("<<*********Checking Response Time**********>>");
		long ResponseTime = response.getTime();
		logger.info("Response Time is  ==> "+ResponseTime);		

		if (ResponseTime > 3000)
			logger.warn("Response time is more than 3000");

		Assert.assertTrue(ResponseTime <3000);
	}

	@Test
	void checkStatusLine()
	{
		logger.info("<<*********Checking Status Line**********>>");
		String StatusLine = response.getStatusLine();
		logger.info("Status Line is  ==> "+StatusLine);		

		Assert.assertEquals(StatusLine,"HTTP/1.1 200 OK");
	}

	@Test
	void checkContentType()
	{
		logger.info("<<*********Checking Content Type**********>>");
		String contentType = response.header("content-type");
		logger.info("Content Type is  ==> "+contentType);		

		Assert.assertEquals(contentType,"application/json;charset=utf-8");	

	}

	@Test
	void checkContentEncoding()
	{
		logger.info("<<*********Checking Content Encoding**********>>");
		String contentEncoding  = response.header("content-encoding");
		logger.info("Content Encoding is  ==> "+contentEncoding);		

		Assert.assertEquals(contentEncoding,"gzip");				
	}
	@Test
	void checkServer()
	{
		logger.info("<<*********Checking Server**********>>");
		String serverName  = response.header("server");
		logger.info("Content Encoding is  ==> "+serverName);		

		Assert.assertEquals(serverName,"nginx/1.16.0");				
	}

	@AfterClass
	void tearDown ()
	{
		logger.info("<<*********TC001_GetAllEmployees Ended**********>>");
	}

}
