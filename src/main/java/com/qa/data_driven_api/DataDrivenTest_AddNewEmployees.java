package com.qa.data_driven_api;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.junit.runner.Request;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DataDrivenTest_AddNewEmployees {
	@SuppressWarnings("unchecked")
	@Test(dataProvider ="empdataprovider" )
	void postNewEmployee(String eName, String eSalary, String eAge) {

		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		RequestSpecification httpRequest= RestAssured.given();
		//Here we created data while we can send along with the post requests
		JSONObject requestparam = new JSONObject();
		requestparam.put("name", eName);
		requestparam.put("salary", eSalary);
		requestparam.put("age", eAge);
		
		//Add a header starting the Request body is a JSON
		httpRequest.header("Content-Type","application/json");
		
		//Add the json to the body of the request
		httpRequest.body(requestparam.toJSONString());
		
		//POST REQUEST
		
		Response response= httpRequest.request(Method.POST,"/create");
		
		//capture response body to perform validation
		String responseBody= response.getBody().asString();
		System.out.println("Response body"+responseBody);
		
		  Assert.assertEquals(responseBody.contains(eName),true);
		  Assert.assertEquals(responseBody.contains(eSalary),true);
		  Assert.assertEquals(responseBody.contains(eAge),true);
		 
		
		int statuscode= response.getStatusCode();
		Assert.assertEquals(statuscode, 200);
		
	}
	@DataProvider(name="empdataprovider")
	String [][] getEmpData() throws IOException{
	String path= System.getProperty("user.dir")+"\\src\\main\\java\\com\\qa\\data_driven_api\\employee.xlsx";
    System.out.println("Path===>"+path); 
	int rowcount=XLUtils.getRowCount(path, "employee");
    int colcount=XLUtils.getCellCount(path, "employee",1);
    System.out.println("rowcount==>"+rowcount +"   "+"colcount===>"+colcount);
     
	String empData[][]= new String[rowcount][colcount];
	
	for(int i=1;i<=rowcount;i++)
	{
		for(int j=0;j<colcount;j++) 
		{
			empData[i-1][j]=XLUtils.getCellData(path,"employee", i, j);
		}
	}
		return empData;
      }
}