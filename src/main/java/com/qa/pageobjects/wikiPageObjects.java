package com.qa.pageobjects;

import com.qa.locators.wikiPageLocators;
import com.qa.utilities.TestUtility;

public class wikiPageObjects extends wikiPageLocators {

	public void wikiSearch(String searchText) throws InterruptedException {
		TestUtility.sendKeys(wikiSearch, searchText);
	}

	public void clickOnSearchResultwiki() throws InterruptedException {
		TestUtility.clickOn(wikiSearchResult);
	}

	public String getReleaseDatewiki() {
		return TestUtility.getText(wikiReleaseDate);

	}

	public String getCountrywiki() {
		return TestUtility.getText(wikiCountry);

	}
}
