package WebBasePackage;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HelperMethods extends WebBase {

	private WebDriverWait mWait;

	public void ScrollIntoView(WebDriver driver, WebElement element) {

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

	}

	public void mouseHover(WebDriver driver, WebElement element) {
		Actions actions = new Actions(driver);
		actions.moveToElement(element).perform();
	}

	public void MoveToElement(WebDriver driver, WebElement element) {
		Actions actions = new Actions(driver);
		actions.moveToElement(visibilityOfElement(driver, element)).click().build().perform();
	}

	public WebElement visibilityOfElement(WebDriver driver, WebElement element) {

		mWait = new WebDriverWait(driver, 90);
		mWait.until(ExpectedConditions.elementToBeClickable(element));

		return element.isDisplayed() ? element : visibilityOfElement(driver, element);

	}
}
