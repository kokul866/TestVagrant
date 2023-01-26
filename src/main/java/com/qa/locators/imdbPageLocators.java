package com.qa.locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.qa.base.TestBase;

public class imdbPageLocators extends TestBase {
	public imdbPageLocators() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "#suggestion-search")
	protected WebElement imdbSearch;

	@FindBy(xpath = "//a[@data-testid='search-result--const']")
	protected WebElement imdbSearchResult;

	@FindBy(xpath = "//a[text()='Release date']/following-sibling::div")
	protected WebElement imdbReleaseDate;

	@FindBy(xpath = "//button[text()='Country of origin']/following-sibling::div")
	protected WebElement imdbCountry;

}
