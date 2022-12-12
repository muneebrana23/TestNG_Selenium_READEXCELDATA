package com.testng;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Guru99 {
	
	public WebDriver driver;
	 @BeforeTest
	    public void setUp() {
	        System.out.println("Start test");
	        driver = new ChromeDriver();
	        driver.manage().window().maximize();	 
	    }

	  	@Test(dataProvider ="excel-data", dataProviderClass=ExcelDataProvider.class)
	  	public void search(String keyWord1, String keyWord2, String Keyword3, String Hello){
	  	  driver.get("https://www.bing.com");
	        	WebElement txtBox = driver.findElement(By.id("sb_form_q"));
	        	  txtBox.sendKeys(keyWord1," ",keyWord2," "+ Keyword3, " " + Hello);
	        	  Reporter.log("Keyword entered is : " +keyWord1+ " " +keyWord2);
	        	  txtBox.sendKeys(Keys.ENTER);
	        	  Reporter.log("Search results are displayed.");
	  	}	 
}
