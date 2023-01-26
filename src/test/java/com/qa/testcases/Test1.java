package com.qa.testcases;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.qa.base.DriverManager;
import com.qa.base.TestBase;
import com.qa.utilities.TestUtility;

public class Test1 extends TestBase {

	@Test(dataProvider = "testdata")
	public void CompareMovieReleaseDateandCountry(String movieName) throws InterruptedException, IOException {
		imdbPO.loadImdbUrl();
		imdbPO.imdbSearch(movieName);
		imdbPO.clickOnSearchResult();
		String imdbReleaseDate = imdbPO.getReleaseDate();
		String imdbCountry = imdbPO.getCountry();
		DriverManager.getDriver().get(property.getProperty("wikiUrl"));
		wikiPO.loadWikiUrl();
		wikiPO.wikiSearch(movieName);
		wikiPO.clickOnSearchResult();
		String wikiReleaseDate = wikiPO.getReleaseDate();
		String wikiCountry = wikiPO.getCountry();
		Assert.assertEquals(imdbCountry, wikiCountry);
		Assert.assertEquals(imdbReleaseDate, wikiReleaseDate);
	}

	@DataProvider(name = "testdata")
	public Object[][] data() throws IOException {
		Object[][] data = TestUtility.getTestData("Sheet1");
		return data;
	}

}
