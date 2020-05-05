package com.EmployeesAPI.Base;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestBase {
	public static RequestSpecification httpRequest;
	public static Response response;
	
	public String empID = "50812"; // for update and delete request
	
	public Logger logger;
	
	@BeforeClass
	public void satup()
	{
		logger = Logger.getLogger("EmployeeRestAPI");
		PropertyConfigurator.configure("Log4j.properties");
		logger.setLevel(Level.DEBUG);
	}
		

}
