package com.qa.locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.TestBase;

public class wikiPageLocators extends TestBase {
	public wikiPageLocators() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@name='search']")
	protected WebElement wikiSearch;

	@FindBy(xpath = "//li[@role='option']")
	protected WebElement wikiSearchResult;

	@FindBy(xpath = "//div[text()='Release date']/parent::th/following-sibling::td")
	protected WebElement wikiReleaseDate;

	@FindBy(xpath = "//th[text()='Country']/following-sibling::td")
	protected WebElement wikiCountry;

}
