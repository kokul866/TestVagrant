package com.qa.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.qa.base.DriverManager;
import com.qa.base.TestBase;
import com.qa.constants.Constants;

public class TestUtility extends TestBase {
	public static Workbook book;
	public static Sheet sheet;
	public static Actions actions;
	public static Select select;
	public static Alert alert;
	static int timeout = Constants.EXPLICIT_WAIT;
	public static JavascriptExecutor javaScript;

	// Explicit Wait to Click on WebElement.
	public static void clickOn(WebElement element) {
		new WebDriverWait(DriverManager.getDriver(), timeout).until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}

	// Explicit Wait to Send Data to WebElement.
	public static void sendKeys(WebElement element, String value) {
		new WebDriverWait(DriverManager.getDriver(), timeout).until(ExpectedConditions.visibilityOf(element));
		element.sendKeys(value);
	}

	// GetText of a WebElement
	public static String getText(WebElement element) {
		new WebDriverWait(DriverManager.getDriver(), timeout).until(ExpectedConditions.visibilityOf(element));
		return element.getText();
	}

	// Explicit Wait for Element To Be Visible.
	public static void waitForElementToBeVisible(By locator) {
		new WebDriverWait(DriverManager.getDriver(), timeout)
				.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	// To Handle Multiple Windows or Switch Between Multiple Windows.
	public void switchWindow(String firstWindow, String secondWindow) {
		Set<String> windowHandles = DriverManager.getDriver().getWindowHandles();
		for (String windows : windowHandles) {
			if (!windows.equals(firstWindow) && !windows.equals(secondWindow)) {
				driver.switchTo().window(windows);
			}
		}
	}
	
	// To Take Screenshot at End Of Test.
		public static void takeScreenshotAtEndOfTest() throws IOException {
			File scrFile = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
			String currentDir = System.getProperty("user.dir");
			FileUtils.copyFile(scrFile, new File(currentDir + "/Screenshots/" + System.currentTimeMillis() + ".png"));
		}

	// To Check Element is Displayed or No.
	public static void isElementDisplayed(WebElement element) {
		boolean elementDisplayed = element.isDisplayed();
		if (elementDisplayed) {
			System.out.println("Element is Displayed");
		} else {
			System.out.println("Element is not Displayed");
		}
	}

	// To Check Element is Enabled or No.
	public static void isElementEnabled(WebElement element) {
		boolean elementEnabled = element.isEnabled();
		if (elementEnabled) {
			System.out.println("Element is Enabled");
		} else {
			System.out.println("Element is not Enabled");
		}
	}

	// To Select a value from Drop Down by using SelectByVisibleText Method.
	public static void selectValueFromDropDownByText(WebElement element, String value) {
		select = new Select(element);
		select.selectByVisibleText(value);
	}

	// To Select a value from Drop Down by using SelectByIndex Method.
	public static void selectValueFromDropDownByIndex(WebElement element, int value) {
		select = new Select(element);
		select.selectByIndex(value);
	}

	// To Select a value from Drop Down by using SelectByValue Method.
	public static void selectValueFromDropDownByValue(WebElement element, String value) {
		select = new Select(element);
		select.selectByValue(value);
	}

	public static void switchToFrame(WebElement framelement) {
		try {
			driver.switchTo().frame(framelement);
			System.out.println("Navigated to Frame with Name ::: " + framelement);
		} catch (NoSuchFrameException e) {
			System.out.println("Unable to Locate Frame with Name ::: " + framelement + e.getStackTrace());
		} catch (Exception e) {
			System.out.println("Unable to Navigate to Frame with Name ::: " + framelement + e.getStackTrace());
		}
	}
	
	// To Print all Values and Select a Required Value from Drop Down.
	public static void selectDropDownValue(String xpathValue, String value) {
		List<WebElement> list = driver.findElements(By.xpath(xpathValue));
		System.out.println(list.size());

		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getText());
			if (list.get(i).getText().equals(value)) {
				list.get(i).click();
				break;
			}
		}
	}

	// To Validate Drop Down Values.
	public static List<String> dropDownValuesValidation(WebElement element) {
		Select select = new Select(element);
		List<WebElement> dropDownValues = select.getOptions();

		List<String> toolsDropDownValues = new ArrayList<String>();

		for (WebElement listOfDropDownValues : dropDownValues) {
			toolsDropDownValues.add(listOfDropDownValues.getText());
		}
		return toolsDropDownValues;
	}

	// To Select Radio Button.
	public void selectRadioButton(List<WebElement> element, String value) {
		for (WebElement elements : element) {
			if (elements.getText().equalsIgnoreCase(value)) {
				elements.click();
				break;
			}
		}
	}

	// To Accept Alert Pop-Up.
	public static void acceptAlertPopup() throws InterruptedException {
		try {
			alert = driver.switchTo().alert();
			System.out.println(alert.getText());
			alert.accept();
			System.out.println("Alert Accepted Successfully");
		} catch (Exception e) {
			System.out.println("Something Went Wrong ==>> Please Check ::: " + e.getMessage());
		}
	}

	// To Dismiss Alert Pop-Up.
	public static void dismissAlertPopup() throws InterruptedException {
		try {
			alert = driver.switchTo().alert();
			System.out.println(alert.getText());
			alert.dismiss();
			System.out.println("Alert Dismissed Successfully");
		} catch (Exception e) {
			System.out.println("Something Went Wrong ==>> Please Check ::: " + e.getMessage());
		}
	}

	// To Match Value with List of Elements and Click on it.
	public void clickOnMatchingValue(List<WebElement> listOfElements, String valueToBeMatched) {
		for (WebElement element : listOfElements) {
			if (element.getText().equalsIgnoreCase(valueToBeMatched)) {
				element.click();
				return;
			}
		}
	}

	// To Click on Element using Actions Class.
	public void clickOnElementUsingActions(WebElement element) {
		actions = new Actions(driver);
		actions.moveToElement(element).click().perform();
	}

	// To Mouse Hover and Click or Select an Element using Actions Class.
	public static void moveToElement(WebElement element) {
		actions = new Actions(DriverManager.getDriver());
		actions.moveToElement(element).build().perform();
	}

	// To Perform Drag and Drop action using Actions Class - 1.
	public static void dragAndDrop_1(WebElement sourceElement, WebElement destinationElement) {
		actions = new Actions(DriverManager.getDriver());
		actions.dragAndDrop(sourceElement, destinationElement).pause(Duration.ofSeconds(2)).release().build().perform();
	}

	// To Perform Drag and Drop action using Actions Class - 2.
	public static void dragAndDrop_2(WebElement sourceElement, WebElement destinationElement) {
		actions = new Actions(DriverManager.getDriver());
		actions.clickAndHold(sourceElement).pause(Duration.ofSeconds(2)).moveToElement(destinationElement)
				.pause(Duration.ofSeconds(2)).release().build().perform();
	}

	// To Perform Right Click action using Actions Class.
	public static void rightClick(WebElement element) {
		actions = new Actions(DriverManager.getDriver());
		actions.contextClick(element).build().perform();
	}

	// To perform Double Click action using Actions Class.
	public static void doubleClick(WebElement element) {
		actions = new Actions(DriverManager.getDriver());
		actions.doubleClick(element).build().perform();
	}

	// Extent Report - 1.
	public static String getSystemDate() {
		DateFormat dateFormat = new SimpleDateFormat("_ddMMyyyy_HHmmss");
		Date date = new Date();
		return dateFormat.format(date);
	}

	// Extent Reports Screesnshots
	public static String getScreenshot(String screenshotName) throws IOException {
		String dateName = new SimpleDateFormat("_ddMMyyyy_HHmmss").format(new Date());
		TakesScreenshot ts = ((TakesScreenshot) DriverManager.getDriver());
		File source = ts.getScreenshotAs(OutputType.FILE);

		String destination = System.getProperty("user.dir") + "//FailedTestsScreenshots//" + screenshotName + dateName
				+ ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}

	// DataProvider Utility is used for getting Data from Excel ==>> Should be used
	// with @DataProvider.
	public static Object[][] getTestData(String sheetName) {
		FileInputStream file = null;
		try {
			file = new FileInputStream(Constants.testdatapath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
		} catch (IOException e) {
			e.printStackTrace();
		}

		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
			}
		}
		return data;
	}
	
	public static void clickElementByJavaScript(WebElement element) {
		javaScript = ((JavascriptExecutor) DriverManager.getDriver());
		javaScript.executeScript("arguments[0].click();", element);
	}
	
	public static void scrollIntoElementByJavaScript(WebElement element) {
		javaScript = ((JavascriptExecutor) DriverManager.getDriver());
		javaScript.executeScript("arguments[0].scrollIntoView(true);", element);
	}

//		public Object[][] gettestdata(String sheetname) {
//			FileInputStream file = null;
//			try {
//				file = new FileInputStream(Constants.testdatapath);
//			} catch (FileNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			XSSFWorkbook book = null;
//			try {
//				book = new XSSFWorkbook(file);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			XSSFSheet sheet=book.getSheet(sheetname);
//			Object[][] data=new String[sheet.getLastRowNum()][sheet.getRow(1).getLastCellNum()];
//			for(int i=0;i<sheet.getLastRowNum();i++)
//			{
//				for(int j=0;j<sheet.getRow(1).getLastCellNum();j++)
//				{
//					data[i][j]=sheet.getRow(i+1).getCell(j).toString();
//				}
//			}
//			return data;
//			
//		}

}
