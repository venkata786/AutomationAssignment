package WebBasePackage;


import java.io.File;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager extends WebBase {
	public static ExtentReports report;
	public static ExtentHtmlReporter htmlreporter;
	public static String directory = System.getProperty("user.dir") + "/reports/";

	public static ExtentReports getInstance() {
		String filename = getReporterName();
		new File(directory).mkdir();
		String path = directory + filename;

		htmlreporter = new ExtentHtmlReporter(path);
		String css = ".r-img {width: 100%;}";
	    htmlreporter.config().setCSS(css);
		htmlreporter.config().setEncoding("utf-8");
		htmlreporter.config().setDocumentTitle("Automation Reports");
		htmlreporter.config().setReportName("Web Automation");
		htmlreporter.config().setTheme(Theme.DARK);

		report = new ExtentReports();
		report.setSystemInfo("ExecutionBrowser", mProperty.getProperty("browser"));
		report.attachReporter(htmlreporter);

		return report;

	}

	public static String getReporterName() {
		Date date = new Date();
		String filename = mProperty.getProperty("browser") + " - " + date.toString().replace(":", "_").replace(" ", "_") + ".html";
		return filename;

	}
}
