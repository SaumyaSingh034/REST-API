package com.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase {
	
	public static Properties prop;
	
	public TestBase()
	{
		prop = new Properties();
		FileInputStream fis;
		try {
			fis = new FileInputStream("D:\\Selenium\\REST_API\\Rest_API\\src\\main\\java\\com\\qa\\config\\config.properties");
			prop.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
		e.printStackTrace();
		}
		
	}

}
