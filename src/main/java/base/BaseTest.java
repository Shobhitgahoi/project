package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class BaseTest {
	public AppiumDriver<MobileElement> driver;
	public Properties properties;
	public String projectpath=System.getProperty("user.dir");

	
	@BeforeMethod
	public void setup() throws MalformedURLException, Exception {
		properties=setDesiredCapabilitiesForAndroid();
		
		DesiredCapabilities capabilities=new DesiredCapabilities();
		capabilities.setCapability("platformName",properties.get("platformName"));
		capabilities.setCapability("platformVersion",properties.get("platformVersion"));
		capabilities.setCapability("deviceName", properties.get("deviceName"));
		capabilities.setCapability("automationName", properties.get("automationName"));
		capabilities.setCapability("app", properties.get("app"));
		URL url=new URL("http://0.0.0.0:4723/wd/hub");
		driver=new AppiumDriver<MobileElement>(url,capabilities);
	
	}
	
	public Properties setDesiredCapabilitiesForAndroid() {
		properties=new Properties();
		try {
			FileInputStream fis=new FileInputStream(projectpath+"\\src\\main\\resources\\TestSetup.properties");
			properties.load(fis);
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		return properties;
	
	}
	
	@AfterMethod
	public void tearDown() {
		if(driver!=null) {
			driver.quit();
		}
	}
	
	
	
}
