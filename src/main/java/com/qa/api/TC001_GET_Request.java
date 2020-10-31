package com.qa.api;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC001_GET_Request {
	@Test
	 void getweatherDetails()
	 {
	  //Specify base URI
	  RestAssured.baseURI="http://restapi.demoqa.com/utilities/weather/city";
	  
	  //Request object
	  RequestSpecification httpRequest=RestAssured.given();
	  
	  //Response object
	  Response response=httpRequest.request(Method.GET,"/Hyderabad");
	  JsonPath jsonPath=response.jsonPath();
	  
	  //print response in console window
	  
	  String responseBody=response.getBody().asString();
	  System.out.println("Response Body is:" +responseBody);
	  Assert.assertEquals(responseBody.contains("Hyderabad"),true);
	  
	  //status code validation
	  int statusCode=response.getStatusCode();
	  System.out.println("Status code is: "+statusCode);
	  Assert.assertEquals(statusCode, 200);
	  
	  //status line verification
	  String statusLine=response.getStatusLine();
	  System.out.println("Status line is:"+statusLine);
	  Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	  System.out.println(jsonPath.get("City"));
	  System.out.println(jsonPath.get("Temparature"));
	  System.out.println(jsonPath.get("Humidity"));
	  System.out.println(jsonPath.get("WeatherDescription"));
	  System.out.println(jsonPath.get("WindSpeed"));
	  System.out.println(jsonPath.get("WindDirectionDegree"));
	  Assert.assertEquals(jsonPath.get("Tempareture"), "39 degree celsius");	  
	 }
}
