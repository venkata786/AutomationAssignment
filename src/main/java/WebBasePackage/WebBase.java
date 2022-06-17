package WebBasePackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebBase {

	public static Properties mProperty;
	public static WebDriver mDriver;

	public WebBase() {

		mProperty = new Properties();
		readProperties("Application");

	}

	public static Properties readProperties(String fileName) {
		try {
			FileInputStream filereader = new FileInputStream(
					System.getProperty("user.dir") + File.separator + "src/test/resources/" + fileName + ".properties");
			mProperty.load(filereader);
		} catch (FileNotFoundException e) {

			e.getMessage();
		} catch (IOException e) {
			throw new IllegalStateException("Unable to read properties file in path=/Application.properties");
		}

		return mProperty;
	}

	public static void initialize() {
		String browserName = mProperty.getProperty("browser");

		if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + File.separator + "Browsers/chrome/chromedriver.exe");
			mDriver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + File.separator + "Browsers/FirefoxDriver/geckodriver");
			mDriver = new FirefoxDriver();
		}

		mDriver.manage().window().maximize();
		mDriver.manage().deleteAllCookies();
		mDriver.manage().timeouts().implicitlyWait(GlobalConstants.IMPLICIT_TIMEOUT, TimeUnit.SECONDS);
		mDriver.manage().timeouts().pageLoadTimeout(GlobalConstants.PAGELOAD_TIMEOUT, TimeUnit.SECONDS);

		mDriver.get(mProperty.getProperty("url"));
	}
}