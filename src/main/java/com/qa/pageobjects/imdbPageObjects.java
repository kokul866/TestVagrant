package com.qa.pageobjects;

import org.openqa.selenium.By;
import org.testng.Assert;

import com.qa.base.TestBase;
import com.qa.constants.Constants;
import com.qa.locators.imdbPageLocators;
import com.qa.utilities.TestUtility;

public class imdbPageObjects extends imdbPageLocators {

	public void imdbSearch(String searchText) throws InterruptedException {
		TestUtility.sendKeys(imdbSearch, searchText);
	}

	public void clickOnSearchResult() throws InterruptedException {
		TestUtility.clickOn(imdbSearchResult);
	}

	public String getReleaseDate() {
		String text = TestUtility.getText(imdbReleaseDate);
		String ReleaseDate = text.split("\\(")[0];
		ReleaseDate = ReleaseDate.split(",")[0].split(" ")[1].trim() + " " + ReleaseDate.split(" ")[0].trim() + " "+ ReleaseDate.split(",")[1].trim();
//		String Month =ReleaseDate.split(" ")[0].trim();
//		String Year = ReleaseDate.split(",")[1].trim();
//		String Date = ReleaseDate.split(",")[0].split(" ")[1].trim();
//		ReleaseDate = Date +" "+ Month +" "+ Year;
		return ReleaseDate;
		// return TestUtility.getText(imdbReleaseDate);

	}

	public String getCountry() {
		return TestUtility.getText(imdbCountry);
	}
}
