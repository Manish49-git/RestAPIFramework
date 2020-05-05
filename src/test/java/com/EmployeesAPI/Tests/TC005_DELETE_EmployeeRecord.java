package com.EmployeesAPI.Tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.EmployeesAPI.Base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;

public class TC005_DELETE_EmployeeRecord extends TestBase{
	
	@BeforeClass
	void deleteEmployee() throws InterruptedException

	{
			logger.info("<<*********TC005_DeletingEmployeesRecord Started**********>>");

			RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
	
			//Request object create
			httpRequest = RestAssured.given();
			
			//Response object
			response = httpRequest.request(Method.GET,"/employees");
		
			//Get the JsonPath object instance from the response interface
			JsonPath jsonPathEveluator = response.jsonPath();
			
			// Capture ID
			String empID = jsonPathEveluator.get("[1].id");
			logger.info("EmpID is >> "+empID);
			response = httpRequest.request(Method.DELETE,"/delete/"+empID);
	
			Thread.sleep(5000);
			
	}
	@Test
	void checkResponseBody()
	{
		String responseBody = response.getBody().asString();
		logger.info(responseBody);
		Assert.assertEquals(responseBody.contains("Successfully Deleted record"),true);
	}
			
}


