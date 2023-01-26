package com.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;

import com.qa.constants.Constants;
import com.qa.locators.imdbPageLocators;
import com.qa.locators.wikiPageLocators;
import com.qa.pageobjects.imdbPageObjects;
import com.qa.pageobjects.wikiPageObjects;
import com.qa.utilities.WebEventListener;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
	public static WebDriver driver;
	public static DesiredCapabilities cap;
	public static Properties property;
	public static ChromeOptions chromeOptions;
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	public imdbPageLocators imdbpage;
	public imdbPageObjects imdbPO;
	public wikiPageLocators wikipage;
	public wikiPageObjects wikiPO;

	public TestBase() {
		try {
			property = new Properties();
			FileInputStream inputStream = new FileInputStream(Constants.configpath);
			property.load(inputStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void initialization() {
		String broswerName = property.getProperty("browser");
		if (broswerName.equals("Chrome")) {
			chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--incognito");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(chromeOptions);
		} else if (broswerName.equals("Edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else if (broswerName.equals("Firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}

		else {
			System.out.println("Path of Driver Executable is not Set for any Browser");
		}
		DriverManager.setWebDriver(driver);
		e_driver = new EventFiringWebDriver(driver);
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Constants.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT, TimeUnit.SECONDS);
	}

	public void initializeRemote() throws MalformedURLException {
		cap = DesiredCapabilities.chrome();
		URL url = new URL("http://localhost:4444/");
		driver = new RemoteWebDriver(url, cap);
		e_driver = new EventFiringWebDriver(driver);
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		DriverManager.setWebDriver(driver);
		driver.manage().timeouts().pageLoadTimeout(Constants.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT, TimeUnit.SECONDS);
		driver.get(property.getProperty("imdbUrl"));
	}

	@BeforeMethod(alwaysRun = true)
	public void setUp() throws MalformedURLException {
		if (property.getProperty("ENV").equals("Local"))
			initialization();
		else if (property.getProperty("ENV").equals("Remote"))
			initializeRemote();
		imdbpage = new imdbPageLocators();
		imdbPO = new imdbPageObjects();
		wikipage = new wikiPageLocators();
		wikiPO = new wikiPageObjects();
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() throws IOException {
		driver.close();
	}

//	@AfterSuite(alwaysRun = true)
//	public void quit() throws IOException {
//		driver.quit();
//	}
}
