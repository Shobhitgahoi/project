package pages;

import java.util.concurrent.TimeUnit;
import org.testng.*;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.offset.PointOption;

/**
 * @author Shobhit Gahoi
 *
 * 09-Oct-2020
 */
/**
 * @author Shobhit Gahoi
 *
 * 09-Oct-2020
 */
public class BasePage<T extends BasePage<T>> {
	private AppiumDriver driver;
	
	public BasePage(AppiumDriver driver) {
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(getDriver()),this);
	}

	public AppiumDriver getDriver() {
		return this.driver;
	}
	
	/**
	 * @author Shobhit Gahoi
	 *This method is for click on a Element after locating it
	 *
	 * @return void
	 * @tag  @param mobileElement
	 */
	public void clickAfterFindingElement(MobileElement mobileElement) {
		for(int i=0;i<5;i++) {
			try {
				//This method is used to  click on the MobileElement when found
				fastClick(mobileElement,5);
				break;
			}catch(Exception e) {
				scrollDown(3);
			}
		}
	}
	
	
	//Getting the text from a mobile element
	public String getTextAfterFindingElement(MobileElement mobileElement) {
		String element = null;
		for(int i=0;i<5;i++) {
			try {
				//Fetching a text of an element
			element=getElementText(mobileElement,5);
			}catch(Exception e) {
				scrollDown(3);
				element=getElementText(mobileElement,5);
			}
		}
		return element;
		
	}
	
	
	/**
	 * @author Shobhit Gahoi
	 *
	 *	This method is for set the implicit wait
	 * @return void
	 * @tag  @param mobileElement
	 * @tag  @throws Exception
	 */
	public void waitForElementToBeDisplayed(MobileElement mobileElement) throws Exception {
		
		getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(10000);
		mobileElement.isDisplayed();
	}
	
	private String getElementText(MobileElement mobileElement,int timeOutInSeconds) {
		return mobileElement.getText();
	}
	
	//setting values on text field
	public void setvalues(MobileElement mobileElement, String value) {
		mobileElement.sendKeys(value);
	}
	
	// Clicking on a particular element
	private void fastClick(MobileElement mobileElement,int timeOutInSeconds) {
		mobileElement.click();
	}
	
	
	/**
	 * @author Shobhit Gahoi
	 *
	 * Method for Scrolling 
	 * @return void
	 * @tag  @param times
	 */
	public void scrollDown(int times) {
		for(int i=0;i< times;i++) {
			org.openqa.selenium.Dimension dim= getDriver().manage().window().getSize();
			int height=dim.getHeight();
			int width=dim.getWidth();
			int x=width/2;
			int starty=(int)(height*0.85);
			int endy=(int)(height*0.5);
			TouchAction action= new TouchAction(getDriver());
			action.press(PointOption.point(x,starty));
			action.moveTo(PointOption.point(x, endy));
			action.release().perform();
	}
	}

	
	/**
	 * @author Shobhit Gahoi
	 *
	 * Rotation methods 1. Rotating Landscape 2. Rotating Portrait
	 * @return void
	 * @tag  @param times
	 */
	
	public T rotateToLandscape() {
		try {
			getDriver().rotate(ScreenOrientation.LANDSCAPE);
		}catch(Exception e) {
			Assert.fail("Received error while trying to rotate the app to Landscape");
		}
		return (T)this;
	}
	
	
	public T rotateToPortrait() {
		try {
			getDriver().rotate(ScreenOrientation.PORTRAIT);
		}catch(Exception e) {
			Assert.fail("Received error while trying to rotate the app to Portrait");
		}
		return (T)this;
	}
}
