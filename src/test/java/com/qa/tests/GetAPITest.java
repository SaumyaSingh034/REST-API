package com.qa.tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.utilities.TestUtility;

public class GetAPITest extends TestBase {
	TestBase testBase;
	String baseUrl;
	String serviceUrl;
	String url;
	RestClient restClient;
	CloseableHttpResponse closeableHttpResponse;

	@BeforeMethod
	public void setUp() throws ClientProtocolException, IOException {
		testBase = new TestBase();
		baseUrl = prop.getProperty("URL");
		serviceUrl = prop.getProperty("serviceUrl");

		url = baseUrl + serviceUrl;

	}

	@Test(priority=1)
	public void invokeGetMethodwithoutHeaders() throws ClientProtocolException, IOException {
		System.out.println(url);
		restClient = new RestClient();
		closeableHttpResponse = restClient.get(url);

		// a. Status Code
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code is :: " + statusCode);

		Assert.assertEquals(statusCode, REST_STATUS_CODE200);

		// Single Value extraction
		// b. JSON Response
		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("JSON Response Body  :: " + responseJson);

		String perPageValue = TestUtility.getValueByJPath(responseJson, "/per_page");
		Assert.assertEquals(Integer.parseInt(perPageValue), 6);

		String totalValue = TestUtility.getValueByJPath(responseJson, "/total");
		Assert.assertEquals(Integer.parseInt(totalValue), 12);

		// get the value from JSON ARRAY
		String lastName = TestUtility.getValueByJPath(responseJson, "/data[0]/last_name");
		String id = TestUtility.getValueByJPath(responseJson, "/data[0]/id");
		String avatar = TestUtility.getValueByJPath(responseJson, "/data[0]/avatar");
		String firstName = TestUtility.getValueByJPath(responseJson, "/data[0]/first_name");
		String email = TestUtility.getValueByJPath(responseJson, "/data[0]/email");
		
		System.out.println("First Name :: "+firstName+" LastName ::"+lastName);
		System.out.println("ID :: "+id+" Avatarr ::"+avatar);
		System.out.println("email :: "+email+"  ::");

		// c. Headers
		Header[] headerArray = closeableHttpResponse.getAllHeaders();
		HashMap<String, String> allHeaders = new HashMap<String, String>();
		for (Header header : headerArray) {
			allHeaders.put(header.getName(), header.getValue());
		}

		System.out.println("Headers of the response are : " + allHeaders);
	}
	
	
	@Test(priority=2)
	public void invokeGetMethodwithHeaders() throws ClientProtocolException, IOException {
		System.out.println(url);
		restClient = new RestClient();
		HashMap<String, String> headermap = new HashMap<String, String>();
		headermap.put("Content-Type", "application/json");
		closeableHttpResponse = restClient.get(url, headermap);

		// a. Status Code
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code is :: " + statusCode);

		Assert.assertEquals(statusCode, REST_STATUS_CODE200);

		// Single Value extraction
		// b. JSON Response
		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("JSON Response Body  :: " + responseJson);

		String perPageValue = TestUtility.getValueByJPath(responseJson, "/per_page");
		Assert.assertEquals(Integer.parseInt(perPageValue), 6);

		String totalValue = TestUtility.getValueByJPath(responseJson, "/total");
		Assert.assertEquals(Integer.parseInt(totalValue), 12);

		// get the value from JSON ARRAY
		String lastName = TestUtility.getValueByJPath(responseJson, "/data[0]/last_name");
		String id = TestUtility.getValueByJPath(responseJson, "/data[0]/id");
		String avatar = TestUtility.getValueByJPath(responseJson, "/data[0]/avatar");
		String firstName = TestUtility.getValueByJPath(responseJson, "/data[0]/first_name");
		String email = TestUtility.getValueByJPath(responseJson, "/data[0]/email");
		
		System.out.println("First Name :: "+firstName+" LastName ::"+lastName);
		System.out.println("ID :: "+id+" Avatarr ::"+avatar);
		System.out.println("email :: "+email+"  ::");

		// c. Headers
		Header[] headerArray = closeableHttpResponse.getAllHeaders();
		HashMap<String, String> allHeaders = new HashMap<String, String>();
		for (Header header : headerArray) {
			allHeaders.put(header.getName(), header.getValue());
		}

		System.out.println("Headers of the response are : " + allHeaders);
	}

}
