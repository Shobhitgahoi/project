package tests;


import org.testng.annotations.Test;

import utilities.Utility;
import com.relevantcodes.extentreports.LogStatus;

import base.BaseTest;
import pages.LoginPage;

public class AmazonShoppingTest extends BaseTest{
	
	/*Test for login, 
	 * adding product and 
	 * validation
	 * @author Shobhit Gahoi
	*/
	@Test(priority=1,enabled=true)
	public void validateAmazonProductAdded() throws Exception {
		new LoginPage(driver)
		.selectSigninButton()
		.setEmailAddress()
		.selectContinue()
		.setPassword()
		.selectSignIn()
		.searchProduct()
		.selectProduct()
		.selectSearchedProduct()
		.validateProductScreenValuesVsCheckout()
		.rotateToPortrait();
		testReporter.log(LogStatus.PASS, "Product Shipped Successfully...",  testReporter.addScreenCapture(Utility.captureScreenshot(driver, screenShotPath)));

	}

	
}
