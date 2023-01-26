package com.qa.constants;

import java.io.File;

import com.qa.utilities.TestUtility;

public class Constants {

	public static final int PAGE_LOAD_TIMEOUT = 20;
	public static final int IMPLICIT_WAIT = 5;
	public static final int EXPLICIT_WAIT = 5;

	public static final String projectpath = System.getProperty("user.dir");
	public static final String configpath = projectpath+ "\\src\\main\\java\\com\\qa\\config\\Configuration.properties";
	public static final String testdatapath = projectpath + "\\src\\main\\java\\com\\qa\\testdata\\movieList.xlsx";
	public static final File reportFolder = new File(projectpath + "\\ExtentResults");
	public static final String reportspath = projectpath + "\\ExtentResults\\TestReport.html";

}
