package tests;

import java.io.IOException;

import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;

public class AmazonShoppingTest extends BaseTest{
	//Main test for login, adding product and validation
	@Test
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
		
	}

	
}
