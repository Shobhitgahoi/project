package pages;

import java.io.IOException;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.HidesKeyboard;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class LoginPage extends BasePage<LoginPage> {
	
	ExcelDataReader excelreader=new ExcelDataReader();
	@AndroidFindBy(id="sign_in_button")
	private MobileElement ALLOW_SIGNIN;
	
	
	@AndroidFindBy(className="android.widget.EditText")
	private MobileElement EMAIL_ADDRESS;
	
	@AndroidFindBy(xpath="//*[@text='Amazon password']")
	private MobileElement PASSWORD_FIELD;
	
	@AndroidFindBy(xpath="//*[@text='Continue']")
	private MobileElement CONTINUE_BTN;
	
	@AndroidFindBy(className="android.widget.Button")
	private MobileElement SIGN_IN_BTN;
	
	
	public LoginPage(AppiumDriver driver) {
		super(driver);
	}
	
	//region login methods
	public LoginPage selectSigninButton() {
		clickAfterFindingElement(ALLOW_SIGNIN);
		return this;
	}
	
	public LoginPage setEmailAddress() throws Exception {
		waitForElementToBeDisplayed(EMAIL_ADDRESS);
		clickAfterFindingElement(EMAIL_ADDRESS);
		String emailaddress=excelreader.readingData("sheet1",1, 0);
		setvalues(EMAIL_ADDRESS, emailaddress);
		return this;
	}
	
	public LoginPage selectContinue() {
		clickAfterFindingElement(CONTINUE_BTN);
		return this;
	}
	
	public LoginPage setPassword() throws Exception {
		waitForElementToBeDisplayed(PASSWORD_FIELD);
		String password=excelreader.readingData("sheet1",1, 1);
		setvalues(PASSWORD_FIELD, password);
		return this;
	}
	
	public ProductSearchPage selectSignIn() {
		clickAfterFindingElement(SIGN_IN_BTN);
		return new ProductSearchPage(getDriver());
	}
	
	//endregion login methods

}
