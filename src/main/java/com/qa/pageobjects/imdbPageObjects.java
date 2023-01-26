package com.qa.pageobjects;

import com.qa.base.DriverManager;
import com.qa.locators.imdbPageLocators;
import com.qa.utilities.TestUtility;

public class imdbPageObjects extends imdbPageLocators {
	
	public void loadImdbUrl() {
		DriverManager.getDriver().get(property.getProperty("imdbUrl"));
	}

	public void imdbSearch(String searchText) throws InterruptedException {
		TestUtility.sendKeys(imdbSearch, searchText);
	}

	public void clickOnSearchResult() throws InterruptedException {
		TestUtility.clickOn(imdbSearchResult);
	}

	public String getReleaseDate() {
		String text = TestUtility.getText(imdbReleaseDate);
		String releaseDate = text.split("\\(")[0];
		releaseDate = releaseDate.split(",")[0].split(" ")[1].trim() + " " + releaseDate.split(" ")[0].trim() + " "+ releaseDate.split(",")[1].trim();
//		String Month =ReleaseDate.split(" ")[0].trim();
//		String Year = ReleaseDate.split(",")[1].trim();
//		String Date = ReleaseDate.split(",")[0].split(" ")[1].trim();
//		ReleaseDate = Date +" "+ Month +" "+ Year;
		return releaseDate;

	}

	public String getCountry() {
		return TestUtility.getText(imdbCountry);
	}
}
