
/*Page Object class of Search Flight page ,where Site Edition selected as English Canada and can be called from
 * test class (i.e., SearchFlightTest.java)
 */



package com.aircanada.pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aircanada.testBase.BaseClass;

public class SelectSitePage extends BaseClass{

	private WebDriver driver;

	//----------------------Object Repository------------------------


	@FindBy(xpath="(//div [@class='abc-ripple-wrapper'])[1]")
	private WebElement clickOnSiteEditor;

	@FindBy(id="siteEditionDropdownSelectedOption")
	private WebElement clickOnSearch;

	@FindBy(id="siteEditionDropdownOptionSearchField")
	private WebElement textBox;

	@FindBy(id="siteEditionDropdownOption-CA")
	private WebElement clickOnCountry; 

	@FindBy(id="siteLanguageDropdownSelectedOption")
	private WebElement clickOnLanguage;

	@FindBy(id="siteLanguageDropdownOption-en")
	private WebElement selectLanguage; 

	@FindBy(id="acEditionSelectorConfirmButton")
	private WebElement clickOnConfirm;

	//-----------------------Initiation of driver object-------------------

	public SelectSitePage(WebDriver driver) 
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	//-----------------------------Actions--------------------------------


	public void select_siteEditor(WebDriver driver,int timeout)
	{
		new WebDriverWait(driver,Duration.ofSeconds(timeout))
		.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div [@class='abc-ripple-wrapper'])[1]"))).click();
		new WebDriverWait(driver,Duration.ofSeconds(timeout)).until(ExpectedConditions.elementToBeClickable(By.id("siteEditionDropdownSelectedOption"))).click();

	}

	public void set_country()
	{
		textBox.sendKeys(rc.getCountryName());
		clickOnCountry.click();
	}

	public void select_language()
	{
		clickOnLanguage.click();
		
		selectLanguage.click();
	

	}

	public SearchFlightPage select_confirm()
	{
		clickOnConfirm.click();
		SearchFlightPage sfpage= new SearchFlightPage(driver);
		return sfpage;
	}

}

