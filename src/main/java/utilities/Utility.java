package utilities;

import org.testng.Assert;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import common.LoggerAgent;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.functions.ExpectedCondition;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class Utility {

		private AndroidDriver<MobileElement> driver=null;

		public Utility(AndroidDriver<MobileElement> driver) {
			// TODO Auto-generated constructor stub
			this.driver = driver;
		}

		public Utility() {
			// TODO Auto-generated constructor stub
		}

		private Properties prop = new Properties();
	
			
			public static String captureScreenshot(AppiumDriver<MobileElement> driver, String screenshotFolder) throws Exception
			{
				String path = null;
				try{
				
				String tm = Long.toString(System.currentTimeMillis());
				//take the screenshot of the entire results page and save it to a png file
				TakesScreenshot scr= (TakesScreenshot)driver;
				File scrFile = scr.getScreenshotAs(OutputType.FILE);

				path = screenshotFolder + File.separator + "screenshot" + tm +".png";
				
					FileUtils.copyFile(scrFile, new File(path));
					//System.out.println("ScreenShot Taken");
					//return path;
			    }catch(Exception e){
			    	System.out.println(e);
			    }
				
				System.out.println("The Screen Shot Path is: "+path);
				return path;
			}			

		
}
