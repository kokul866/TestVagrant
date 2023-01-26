package com.qa.testcases;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.base.DriverManager;
import com.qa.base.TestBase;
import com.qa.locators.imdbPageLocators;
import com.qa.locators.wikiPageLocators;
import com.qa.pageobjects.imdbPageObjects;
import com.qa.pageobjects.wikiPageObjects;
import com.qa.utilities.TestUtility;

public class Test1 extends TestBase {

	@Test(enabled = true, dataProvider = "testdata")
	public void CompareMovieReleaseDateandCountry(String movieName) throws InterruptedException, IOException {
		imdbPO.imdbSearch(movieName);
		imdbPO.clickOnSearchResult();
		String imdbReleaseDate = imdbPO.getReleaseDate();
		String imdbCountry = imdbPO.getCountry();
		DriverManager.getDriver().get(property.getProperty("wikiUrl"));
		wikiPO.wikiSearch(movieName);
		wikiPO.clickOnSearchResultwiki();
		String wikiReleaseDate = wikiPO.getReleaseDatewiki();
		String wikiCountry = wikiPO.getCountrywiki();
		Assert.assertEquals(imdbCountry, wikiCountry);
		Assert.assertEquals(imdbReleaseDate, wikiReleaseDate);
	}

	@DataProvider(name = "testdata")
	public Object[][] data() throws IOException {
		Object[][] data = TestUtility.getTestData("Sheet1");
		return data;
	}

}
