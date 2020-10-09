package pages;

import org.testng.Assert;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class ProductSearchPage extends BasePage<ProductSearchPage> {
	ExcelDataReader excelreader=new ExcelDataReader();
	
	@AndroidFindBy(id="rs_search_src_text")
	private MobileElement SEARCH_PRODUCT_FIELD;
	
	@AndroidFindBy(id="iss_search_dropdown_item_text_layout")
	private MobileElement SELECT_PRODUCT;
	
	@AndroidFindBy(xpath="//*[contains(@text,'Sony')]")
	private MobileElement SELECT_SEARCHED_PRODUCT;
	
	@AndroidFindBy(xpath="//android.view.View[contains(@text,'Sony')]")
	private MobileElement PRODUCT_NAME;
	
	@AndroidFindBy(id="ourPrice_availability")
	private MobileElement PRODUCT_PRICE;
	
	@AndroidFindBy(xpath="//*[@text='Add to Cart']")
	private MobileElement ADD_TO_CART_BTN;
	
	@AndroidFindBy(accessibility="Cart")
	private MobileElement PROCEED_TO_CHECKOUT;
	
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'Sony')]")
	private MobileElement CHECKOUT_PRODUCT_NAME;
	
	@AndroidFindBy(xpath="//android.view.View[contains(@text,'$')]")
	private MobileElement CHECKOUT_PRODUCT_PRICE;
	
	//android.view.View[@content-desc="Sony X750H 65-inch 4K Ultra HD LED TV -2020 Model"]/android.widget.TextView
	public ProductSearchPage(AppiumDriver driver) {
		super(driver);
	}
	
	
	/**
	 * @author Shobhit Gahoi
	 *
	 *	Search the product
	 * @return ProductSearchPage
	 * @tag  @return
	 * @tag  @throws Exception
	 */
	public ProductSearchPage searchProduct() throws Exception {
		waitForElementToBeDisplayed(SEARCH_PRODUCT_FIELD);
		clickAfterFindingElement(SEARCH_PRODUCT_FIELD);
		String searchproduct=excelreader.readingData("sheet1",1, 3);
		setvalues(SEARCH_PRODUCT_FIELD, searchproduct);
		return this;
	}

	
	/**
	 * @author Shobhit Gahoi
	 *
	 *	Select the product
	 * @return ProductSearchPage
	 * @tag  @return
	 */
	public ProductSearchPage selectProduct() {
		clickAfterFindingElement(SELECT_PRODUCT);
		return this;
	}
	
	
	/**
	 * @author Shobhit Gahoi
	 *
	 *
	 * @return ProductSearchPage
	 * @tag  @return
	 * @tag  @throws Exception
	 */
	public ProductSearchPage selectSearchedProduct() throws Exception {
		waitForElementToBeDisplayed(SELECT_SEARCHED_PRODUCT);
		clickAfterFindingElement(SELECT_SEARCHED_PRODUCT);
		return this;
	}
	
	
	/**
	 * @author Shobhit Gahoi
	 *
	 *	Validate the product value vs Check out product
	 * @return ProductSearchPage
	 * @tag  @return
	 * @tag  @throws Exception
	 */
	public ProductSearchPage validateProductScreenValuesVsCheckout()throws Exception {
		String productname=getTextAfterFindingElement(PRODUCT_NAME);
		System.out.println(productname);
		waitForElementToBeDisplayed(ADD_TO_CART_BTN);
		clickAfterFindingElement(ADD_TO_CART_BTN);
		waitForElementToBeDisplayed(PROCEED_TO_CHECKOUT);
		clickAfterFindingElement(PROCEED_TO_CHECKOUT);
		waitForElementToBeDisplayed(CHECKOUT_PRODUCT_NAME);
		Assert.assertEquals(getTextAfterFindingElement(CHECKOUT_PRODUCT_NAME), productname);
		return this;
	}
	
}
	