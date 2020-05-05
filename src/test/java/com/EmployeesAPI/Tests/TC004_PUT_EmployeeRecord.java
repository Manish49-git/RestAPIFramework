package com.EmployeesAPI.Tests;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.EmployeesAPI.Base.TestBase;
import com.EmployeesAPI.Util.RestUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC004_PUT_EmployeeRecord extends TestBase{
	 String empName = RestUtils.empName();
	 String empSal = RestUtils.empSal();
	 String empAge = RestUtils.empAge();
	
	@BeforeClass
	void UpdateEmployees() throws InterruptedException

	{
		
				logger.info("<<*********TC004_UpdatingEmployeesRecord Started**********>>");
		
				RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
				
				//Request object create
				 httpRequest = RestAssured.given();
				
				 // Created data send along with request
				JSONObject requestParams = new JSONObject();
				requestParams.put("name", empName);
				requestParams.put("salary", empSal);
				requestParams.put("age", empAge);
				
				//Add header stating the request body is in Json 
				httpRequest.header("Content-Type","application/json;charset=utf-8");
				
				// Add the Json to the body of the request
				httpRequest.body(requestParams.toJSONString());
				
				//Response object
				response = httpRequest.request(Method.PUT,"/update/"+empID);
				
				Thread.sleep(5000);
	}
				// Capture response body for validation
				@Test
				void checkResponseBody()
				{
				String responseBody = response.getBody().asString();
				logger.info(responseBody);
				Assert.assertEquals(responseBody.contains(empName),true);
				Assert.assertEquals(responseBody.contains(empSal),true);
				Assert.assertEquals(responseBody.contains(empAge),true);
				}
				
				@Test
				void checkStatusCode()				
				{
				
				int statusCode = response.getStatusCode();
				Assert.assertEquals(statusCode, 200);
				}
				
				@Test
				void checkStatusLine()
				{
					String StatusLine = response.getStatusLine();
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
					logger.info("Server Type is  ==> "+serverName);		

					Assert.assertEquals(serverName,"nginx/1.16.0");				
				}

				@AfterClass
				void tearDown ()
				{
					logger.info("<<*********TC004_GetAllEmployees Ended**********>>");
				}
							

}
