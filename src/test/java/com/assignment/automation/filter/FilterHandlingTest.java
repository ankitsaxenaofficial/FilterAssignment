package com.assignment.automation.filter;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FilterHandlingTest {
	
	WebDriver driver;
	@BeforeTest
	public void setDriver() throws InterruptedException {
		
		driver = new ChromeDriver();
		driver.get("https://www.t-mobile.com/tablets");		
		driver.manage().window().maximize();
		Thread.sleep(5000);
	}
	
	@Test
	public void filterCriteria() throws InterruptedException {
		
		PageObjects obj = new PageObjects(driver);
		
		// Only "New" to be selected from "Deals" Category
		obj.selectFilter("Deals", "New");
		
		//No option provided for "Deals" Category
		obj.selectFilter("Deals");
		
		//Select "all" the options inside "Brands" category
		obj.selectFilter("Brands","all");
		
		//Select "Android, iPadOS" from the "Operating System" Category
		obj.selectFilter("Operating System", "Android", "iPadOS");
		
		//Now again select nothing from the Brands category
		obj.selectFilter("Brands");
		
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
