package com.qa.tests;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;

public class GetAPITest extends TestBase{
	TestBase testBase;
	String baseUrl;
	String serviceUrl;
	String url;
	RestClient restClient;
	
	@BeforeMethod
	public void setUp() throws ClientProtocolException, IOException
	{
		testBase = new TestBase();
		 baseUrl = prop.getProperty("URL");
		 serviceUrl = prop.getProperty("serviceUrl");
		
		 url = baseUrl + serviceUrl;
	
		
		
	}
	
	@Test
	public void invokeGetMethod() throws ClientProtocolException, IOException
	{
		System.out.println(url);
		 restClient = new RestClient();
		restClient.get(url);
	}

}
