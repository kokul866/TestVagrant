package com.qa.pageobjects;

import com.qa.base.DriverManager;
import com.qa.locators.wikiPageLocators;
import com.qa.utilities.TestUtility;

public class wikiPageObjects extends wikiPageLocators {
	
	public void loadWikiUrl() {
		DriverManager.getDriver().get(property.getProperty("wikiUrl"));
	}


	public void wikiSearch(String searchText) throws InterruptedException {
		TestUtility.sendKeys(wikiSearch, searchText);
	}

	public void clickOnSearchResult() throws InterruptedException {
		TestUtility.clickOn(wikiSearchResult);
	}

	public String getReleaseDate() {
		return TestUtility.getText(wikiReleaseDate);

	}

	public String getCountry() {
		return TestUtility.getText(wikiCountry);

	}
}
