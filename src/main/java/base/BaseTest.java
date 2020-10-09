package base;


import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import common.*;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import pages.ExcelDataReader;

@Listeners(common.MyTestListener.class)
public class BaseTest {
	public AppiumDriver<MobileElement> driver;
	public String projectpath=System.getProperty("user.dir");
	public static String screenShotPath=System.getProperty("user.dir")+PropertyExecutor.getProperty("ScreenShot_Path");
	public static ExtentReports reporter = null;
	public static ExtentTest testReporter = null;
	String filePath=PropertyExecutor.getProperty("FILE_PATH")+PropertyExecutor.getProperty("FILE_NAME");
	String sheetName=PropertyExecutor.getProperty("SHEET_NAME");
	
	/**
	 * @author Shobhit Gahoi
	 *
	 *
	 * @return void
	 * @tag  @throws MalformedURLException
	 * @tag  @throws Exception
	 */
	@BeforeClass
	public void setup() throws MalformedURLException, Exception {
		DesiredCapabilities capabilities=new DesiredCapabilities();
		capabilities.setCapability("platformName",PropertyExecutor.getProperty("platformName"));
		capabilities.setCapability("platformVersion",PropertyExecutor.getProperty("platformVersion"));
		capabilities.setCapability("deviceName", PropertyExecutor.getProperty("deviceName"));
		capabilities.setCapability("automationName", PropertyExecutor.getProperty("automationName"));
		capabilities.setCapability("app", projectpath+PropertyExecutor.getProperty("app"));
		URL url=new URL((String) PropertyExecutor.getProperty("URL"));
		driver=new AppiumDriver<MobileElement>(url,capabilities);
		reporter = ReportFactory.getReporter("Test Reports");
	}
	

	
	/**
	 * @author Shobhit Gahoi
	 *
	 *
	 * @return void
	 * @tag  @param m
	 */
	@BeforeMethod
	public void beforeMethod(Method m)  {
		
		testReporter=reporter.startTest(m.getName());
	}
	
	
	

/**
 * @author Shobhit Gahoi
 *
 *
 * @return void
 * @tag  @param result
 * @tag  @throws FileNotFoundException
 */
@AfterMethod
public void afterMethod(ITestResult result) throws FileNotFoundException {
	String methodName=result.getMethod().getMethodName();

	long time=(result.getEndMillis() - result.getStartMillis())/1000;
	String timeOfExecution=""+time+" Sec";
	
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MMM/YYYY HH:mm:ss");  
	   LocalDateTime now = LocalDateTime.now();  
	   String executionTime=dtf.format(now);  
	   LoggerAgent.LogInfo(executionTime);
	   
	int rowNum=ExcelDataReader.getLastRowIndex(filePath, sheetName);
	
	String[] arr=new String[4];
	arr[0]=methodName;
	
	arr[2]=timeOfExecution;
	
	
	if(result.getStatus()==ITestResult.SUCCESS) {
	arr[1]="Pass";
	} else if(result.getStatus()==ITestResult.FAILURE) {
		arr[1]="Fail";
		
	} else if(result.getStatus()==ITestResult.SKIP) {
		arr[1]="Skip";
	}
	
	ExcelDataReader.writeIntoCell(filePath, sheetName, rowNum+1, 0, arr);
	
	reporter.endTest(testReporter);
	}
	
	
	/**
	 * @author Shobhit Gahoi
	 * @return void
	 * @tag  
	 */
	@AfterClass
	public void tearDown() {
		LoggerAgent.LogInfo("Ending execution!!");
		reporter.close();
		if(driver!=null) {
			driver.quit();
		}
	}
	
	
	
}
