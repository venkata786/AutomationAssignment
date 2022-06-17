package Listeners;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import WebBasePackage.ExtentManager;
import WebBasePackage.WebBase;


public class TestListener extends WebBase implements ITestListener {

	private static ExtentReports extent;
	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

	public TestListener() {
		super();
		extent = ExtentManager.getInstance();
	}

	public void onTestStart(ITestResult result) {


		ExtentTest test = extent.createTest(result.getClass().getName() + "::" + result.getMethod().getMethodName());

		extentTest.set(test);

	}

	public void onTestSuccess(ITestResult result) {

		String log = "<b>Test Method: " + result.getMethod().getMethodName() + "Successful</b>";
		Markup m = MarkupHelper.createLabel(log, ExtentColor.GREEN);
		extentTest.get().log(Status.PASS, m);

		extent.flush();

	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
		extentTest.get().fail("<details><summary><b><font color=red>" + "Exception Occured, Click to see detials:"
				+ "</font></b></summary>+" + exceptionMessage.replaceAll(",", "<br>") + "</detials>\n");

		String path = takeScreenShot(result.getMethod().getMethodName());

		try {
			extentTest.get().fail("<b><font color=red>" + "Screenshot of failure" + "</font> </b>",
					MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		} catch (IOException e) {
			extentTest.get().fail("Test Fail, Cannot attach screenshot");

		}

		String log = "<b>Test Method: " + result.getMethod().getMethodName() + "Failed</b>";
		Markup m = MarkupHelper.createLabel(log, ExtentColor.RED);
		extentTest.get().log(Status.FAIL, m);

		extent.flush();

	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		String log = "<b>Test Method: " + result.getMethod().getMethodName() + "Skipped</b>";
		Markup m = MarkupHelper.createLabel(log, ExtentColor.YELLOW);
		extentTest.get().log(Status.SKIP, m);

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	public void onFinish(ITestContext context) {

		
		extent.flush();

	}

	public String takeScreenShot(String methodName) {

		String fileName = getScreenshotName(methodName);
		String directory = System.getProperty("user.dir") + "/Screenshots/";
		new File(directory).mkdir();
		String path = directory + fileName;

		try {
			File Screenshot = ((TakesScreenshot)mDriver).getScreenshotAs(OutputType.FILE);
			FileHandler.copy(Screenshot, new File(path));
		} catch (Exception e) {
			e.printStackTrace();

		}
		return path;
	}

	public static String getScreenshotName(String methodName) {
		Date date = new Date();
		String filename = methodName + "_" + date.toString().replace(":", "_").replace(" ", "_") + ".png";
		return filename;
	}

	public static void deleteFilesInFolder() {

		List<File> directory = new ArrayList<File>();

		File reports = new File(System.getProperty("user.dir") + File.separator + "reports");

		File screenshots = new File(System.getProperty("user.dir") + File.separator + "Screenshots");

		if (Objects.nonNull(reports.listFiles()) && Objects.nonNull(screenshots.listFiles())) {

			directory.add(screenshots);
			directory.add(reports);

			for (File dirs : directory) {
				for (File file : dirs.listFiles())
					if (!file.isDirectory())
						file.delete();
			}
		}

	}

}
