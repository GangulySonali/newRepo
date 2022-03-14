/* This is Page Object Design pattern ,where all the identified elements and their associated actions 
 * are combined in one class.An Object Repository is created in this pattern and the action methods are utilized or 
 * called from test class(i.e., SearchFlightTest.java)
 */


package com.aircanada.pageObjects;



import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aircanada.testCases.BaseClass;

public class SearchFlightPage extends BaseClass{

	private WebDriver driver;

	//----------------------Object Repository------------------------

	@FindBy(id="bkmg-tab-button-flight")
	private WebElement selectFlight;

	@FindBy(id="bkmgFlights_tripTypeSelector")
	private WebElement selectTrip;

	@FindBy(xpath="//div[contains(text(),'Round-trip')]")
	private WebElement selRounTrip;

	@FindBy(id="bkmgFlights_origin_trip_1")
	private WebElement clickFrom;

	@FindBy(xpath="//ul[@id='bkmgFlights_origin_trip_1OptionsPanel']//span[@class='location-city-name ng-star-inserted']")
	private List <WebElement> allOrigins;

	@FindBy(xpath="//ul[@id='bkmgFlights_destination_trip_1OptionsPanel']//span[@aria-hidden='true']")
	private List<WebElement> allDestinations;

	@FindBy(id="bkmgFlights_destination_trip_1")
	private WebElement clickDest;

	@FindBy(id="bkmgFlights_travelDates_1-showCalendar")
	private WebElement selectCal;

	@FindBy(xpath="//abc-calendar-month[@class='ng-star-inserted']")
	private List<WebElement> monthYear_depart;

	@FindBy(id="bkmgFlights_travelDates_1_nextMonth")
	private WebElement selectNextMonth;


	@FindBy(xpath="//input[@id='bkmgFlights_travelDates_1-formfield-2']")
	private WebElement returnDate;

	@FindBy(id="bkmgFlights_travelDates_1_confirmDates")
	private WebElement selectConfirm;

	@FindBy(id="bkmgFlights_findButton")
	private WebElement findFlight;



	//-----------------------Initiation of driver object-------------------

	public SearchFlightPage(WebDriver driver)
	{
		this. driver=driver;
		PageFactory.initElements(driver, this);
	}



	//-----------------------------Actions--------------------------------

	public void select_FlightTab()
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(50));

		try{
			wait.until(ExpectedConditions.elementToBeClickable(By.id("bkmg-tab-button-flight"))).click();
		}catch(Exception e)

		{
			wait.until(ExpectedConditions.elementToBeClickable(By.id("bkmg-tab-button-flight"))).click();
		}
	}

	public void select_RoundTrip()
	{
		selectTrip.click();
		selRounTrip.click();
	}

	public void select_Origin(WebDriver driver,int timeout,String selectOrigin)
	{
		clickFrom.clear();
		clickFrom.sendKeys(selectOrigin);

		for(WebElement origin:allOrigins)
		{
			if(origin.getText().contains(selectOrigin))
			{
				new WebDriverWait(driver,Duration.ofSeconds(timeout)).
				until(ExpectedConditions.elementToBeClickable(origin)).click();
			}
			else

				logger.info("origin not found---------------");
		}
	}

	public void select_Destination(WebDriver driver,int timeout,String selDestination)
	{
		clickDest.clear();
		clickDest.sendKeys(selDestination);

		for(WebElement destination:allDestinations)
		{
			if(destination.getText().equalsIgnoreCase(selDestination))
			{
				new WebDriverWait(driver,Duration.ofSeconds(timeout)).
				until(ExpectedConditions.elementToBeClickable(destination)).click();
			}
			else
				logger.info("Destination not found---------------");
		}
	}

	public void select_DepertureDate(String month_depart,String dateDepart)

	{
		selectCal.click();

		for(WebElement my:monthYear_depart)
		{
			if(my.getText().contains(month_depart))
				break;
			else 
				selectNextMonth.click();
		}
		driver.findElement(By.xpath("//td[@class='abc-calendar-day-wrapper ng-star-inserted' and @aria-hidden='false' and contains(@data-date,"+dateDepart+")]")).click();

	}

	public void select_returnDate(String date_Return) throws InterruptedException
	{
		returnDate.clear();
		returnDate.sendKeys(date_Return);
		returnDate.sendKeys(Keys.ENTER);
		selectConfirm.click();
		Thread.sleep(3000);
	}

	public SelectFlightPage find_Flight() throws InterruptedException
	{
		findFlight.click();

		SelectFlightPage sfObject=new SelectFlightPage(driver);
		return sfObject;
	}

}






