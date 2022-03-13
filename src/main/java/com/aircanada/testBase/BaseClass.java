/* BaseClass has been created to store the common methods and inherited by test class(i.e.,SearchFlightTest class)
 *  to launch the browser,open the url at the beginning and close the browser at the end of the test execution
 *  
 */



package com.aircanada.testBase;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.aircanada.utility.ReadConfig;

public class BaseClass {

	public static WebDriver driver=null;
	public static Logger logger=LogManager.getLogger(BaseClass.class);
	public static ReadConfig rc=new  ReadConfig();

	@Parameters("browser")
	@BeforeClass
	public void setUp(String br)
	{
		if(br.equalsIgnoreCase("chrome"))
		{
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		}
		else if(br.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}
		else if(br.equalsIgnoreCase("ie"))
		{
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
		}
		logger.info("Browser opened--------------------");


		//Launching URL
		driver.get(rc.getUrl());
		driver.manage().window().maximize();
		logger.info("URL opened--------------------");
		if(driver.getTitle().equals("404"))
		{

			driver.findElement(By.linkText("Homepage")).click();
			logger.info("page not found------------------");

		}
		else
		{
            if(driver.getTitle().contains("Air Canada"))
			logger.info("Title verified and desired page opened---------------------");
		}
	}

	@AfterClass
	public void tearDown()
	{
		driver.quit();
		logger.info("Browsr closed-------------------------");
	}
	
	public static void captureScreen(WebDriver driver ,String tname) throws IOException
	{
		TakesScreenshot ts= (TakesScreenshot)driver;
		File srs = ts.getScreenshotAs(OutputType.FILE);
		File trg=new File(System.getProperty("user.dir")+"/screenShots/"+tname+".png");
		FileUtils.copyFile(srs, trg);
		logger.info("Screenshot taken---------------");
		
	}

}

