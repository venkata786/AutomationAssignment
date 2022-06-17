package Pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import WebBasePackage.HelperMethods;
import WebBasePackage.WebBase;

public class HomePage extends WebBase {
	HelperMethods helper;

	public HomePage() {
		PageFactory.initElements(mDriver, this);
		helper = new HelperMethods();
	}

	@FindBy(xpath = ".//ul[@class='sf-menu clearfix menu-content sf-js-enabled sf-arrows']/li/a")
	List<WebElement> mMenuItems;

	@FindBy(xpath = ".//div[@class='product-container']")
	List<WebElement> components;

	@FindBy(xpath = ".//div[@class='product-container']")
	WebElement component;

	@FindBy(xpath = ".//div[@class='bottom-pagination-content clearfix']/form[@class='compare-form']")
	WebElement mDress;

	@FindBy(xpath = "/ancestor::div[@id='columns']//div[@class='product-container']")
	WebElement mDresswithHighestPrice;

	@FindBy(xpath = ".//span[@class='heading-counter']")
	WebElement textmsg;

	@FindBy(id = "summary_products_quantity")
	WebElement NumberOfItemsInCart;

	@FindBy(id = "layer_cart_product_price")
	WebElement mPriceOfTheDress;

	@FindBy(css = "#add_to_cart")
	WebElement mAddToCart;

	@FindBy(xpath = ".//a[@class='btn btn-default button button-medium']/span")
	WebElement mProductAddedSuccessMessage;

	@FindBy(xpath = ".//div[@class='right-block']/div[@itemprop='offers']/span[@itemprop='price'][@class='price product-price']")
	List<WebElement> mItemPrices;

	public void clickmenu(String menuItem) {

		for (WebElement menu : mMenuItems) {

			if (menu.getText().equalsIgnoreCase(menuItem)) {
				menu.click();
				break;
			}
		}

	}

	public void addToCart(String message) {

		helper.ScrollIntoView(mDriver, mDress);

		double HighestValue = Double.parseDouble(getHighestPrice());

		for (WebElement price : components) {

			String prices = price.findElement(By.xpath(
					"div[@class='right-block']/div[@itemprop='offers']/span[@itemprop='price'][@class='price product-price']"))
					.getText();

			if (getPriceValue(prices) >= HighestValue) {

				WebElement ele = price.findElement(
						By.xpath("div[@class='right-block']/div[@class='button-container']/a[@title='Add to cart']"));
				helper.mouseHover(mDriver, price);
				ele.click();

				Assert.assertTrue(prices.equals(helper.visibilityOfElement(mDriver, mPriceOfTheDress).getText()));

				mProductAddedSuccessMessage.click();

				break;

			}
		}

	}

	public double getPriceValue(String value) {

		String actualValue = value.substring(1);

		return Double.parseDouble(actualValue);

	}

	public String getHighestPrice() {
		List<Object> prices = new ArrayList<Object>();
		for (WebElement price : mItemPrices) {

			prices.add(getPriceValue(price.getText()));

		}
		Collections.sort(prices, Collections.reverseOrder());

		return prices.get(0).toString();
	}

}
