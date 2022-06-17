package Tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.HomePage;
import WebBasePackage.WebBase;

public class AddCartTest extends WebBase {

	private HomePage homepage;

	public AddCartTest() {
		super();

	}

	@BeforeMethod
	public void setUp() {
		initialize();
		homepage = new HomePage();

	}

	@Test(priority = 1)
	public void verifyAddToCartTest() {

		homepage.clickmenu("Dresses");
		homepage.addToCart(mProperty.getProperty("shoppingcartmessage"));

	}

	@AfterMethod
	public void tearDown() {
		mDriver.quit();
	}

}
