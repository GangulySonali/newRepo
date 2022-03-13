/* ReadConfig is an utility class which will read the inputs required for executing the tests ,from config.properties
 * file .By creating an object of ReadConfig class we can access those variable/inputs  */


package com.aircanada.utility;

import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {

	Properties pro;
	String path=System.getProperty("user.dir")+"//configurations//config.properties";
	
	public ReadConfig() 
	{
		try {
			FileInputStream file=new FileInputStream(path);
			pro=new Properties();
			pro.load(file);
		}catch(Exception e)
		{
			System.out.println(e.getMessage()+"File not found");
			System.out.println(e.getCause());
		}
	}

	public  String getUrl()
	{
		return pro.getProperty("baseUrl");
	}

	public String getCountryName()
	{
		return pro.getProperty("country");
	}


}
