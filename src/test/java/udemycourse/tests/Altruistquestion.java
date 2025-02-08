package udemycourse.tests;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Altruistquestion {
	WebDriver driver;
	
	@BeforeTest
	public void setUp() {
		driver = new ChromeDriver();
		
	}

	@Test
	public void matchTest() {
		/*
		 * Write a Test Suite that does the following: 1. Opens a webpage
		 * www.google.com/finance on a chrome browser 2. Verifies the page is loaded by
		 * asserting the page title 
		 * 3. Retrieves the stock symbols listed under the
		 * section “You may be interested in info” (please note, this is a sample of
		 * what to look for on the above browser link and the stock data will differ day
		 * to day) 4. Compare the stock symbols retrieved from (3) with
		 * expectedStockSymbols 
		 * 5. Print all stock symbols that are in (3) but not in expectedStockSymbols 
		 * 6. Print all stock symbols that are in expectedStockSymbols but not in (3) 
		 * When you submit your solution, make sure
		 * to include: TestNG file to run the test
		 */
		driver.get("https://www.google.com/finance");
		String expectedTitle=driver.getTitle();
		Assert.assertEquals("Google Finance - Stock Market Prices, Real-time Quotes & Business News", expectedTitle);
		
		
		List<String> expectedStockSymbols = Arrays.asList("NFLX","MSFT","TSLA");
		
		// retrieves the stock symbols
		List<WebElement> actualStockSymbols=driver.findElements(By.cssSelector("div.H8Ch1 div.COaKTb"));
		
		//Compare the stock symbols retrieved from (actualStockSymbols) with expectedStockSymbols
		System.out.println("Stock symbols in the actual list but not in the expected:");
		for(WebElement element:actualStockSymbols) {
			String stockSymbol=element.getText().trim();
			if(!expectedStockSymbols.contains(stockSymbol)) {
				System.out.println(stockSymbol);				
			}
			else if(expectedStockSymbols.contains(stockSymbol)) {
					System.out.println("Stock symbols in the expected list but not in the page:");
					System.out.println(stockSymbol);
				
			}
			
		}
			
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
