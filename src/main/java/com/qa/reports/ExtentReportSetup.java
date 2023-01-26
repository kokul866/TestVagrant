package com.qa.reports;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.qa.base.TestBase;
import com.qa.constants.Constants;

public class ExtentReportSetup extends TestBase {
	public static ExtentReports extent;
	public static ExtentTest extentTest;
	public static ExtentSparkReporter sparkReport;

	public static ExtentReports extentReportSetup() {

		try {
			FileUtils.deleteDirectory(Constants.reportFolder);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sparkReport = new ExtentSparkReporter(Constants.reportspath);

		extent = new ExtentReports();
		extent.attachReporter(sparkReport);

		sparkReport.config().setTheme(Theme.DARK);
		sparkReport.config().setReportName("Test Automation Report");
		sparkReport.config().setDocumentTitle("Test Automation Report");

		return extent;
	}
}
