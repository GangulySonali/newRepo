/*Page Object Class for Select Flight page, where interactive elements of this page and their associated actions 
 * are created, and can be called from test class(i.e., SearchFlightTest.java)*/
 



package com.aircanada.pageObjects;



import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aircanada.testCases.BaseClass;

public class SelectFlightPage extends BaseClass{

	WebDriver driver;

	//-------------------Object Repository-----------------------------

	@FindBy(xpath="//span[@class='hidden-xs']")
	private WebElement confirmDate;

	@FindBy(xpath="//div[contains(@class,'cabin-class-title')]")
	private List<WebElement> textsInTabs;

	@FindBy(xpath="//title[normalize-space()='Air Canada - Select flights']")
	WebElement displayText;

	//---------------driver object initiation-------------------------

	public SelectFlightPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	//-------------------------Actions--------------------------

	public void verifying_SelectFlightPage()
	{
		new WebDriverWait(driver,Duration.ofSeconds(60))
		.until(ExpectedConditions.titleIs("Air Canada - Select flights"));
		//Assert.assertEquals(driver.getTitle(), "Air Canada - Select flights");
		//logger.info("Page title verified for Select Flight page and navigation is correct -----");

	}

	public void verifying_departureDate(WebDriver driver,int timeout,String month_depart) throws IOException
	{
		Boolean data = new WebDriverWait(driver,Duration.ofSeconds(timeout)).until(ExpectedConditions.textToBePresentInElement(confirmDate, month_depart));
		if (data==true)
			logger.info("Departure date is verified---------------------");
		else 
			BaseClass.captureScreen(driver, "verifying_departureDate");

	}
	
	public void verifying_tabs() throws IOException

	{
		ArrayList<String>cabin2=new ArrayList<String>();
		ArrayList<String>cabin1=new ArrayList<String>(Arrays.asList("Economy","Premium Economy","Business Class"));

		for(WebElement cab:textsInTabs)
			cabin2.add(cab.getText());
		if(cabin2.equals(cabin1))
			logger.info("Expected Tabs are found-------");
		else 
			BaseClass.captureScreen(driver, "verifying_departureDate");


	}

}


