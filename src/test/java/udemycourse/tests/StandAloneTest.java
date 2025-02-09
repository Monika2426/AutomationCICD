package udemycourse.tests;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		
//comments
		String productname="BANARSI SAREE";
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client/");
		
		driver.findElement(By.id("userEmail")).sendKeys("Udemy1@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Udemy@1234");
		driver.findElement(By.name("login")).click();
	
		WebDriverWait obj=new WebDriverWait(driver, Duration.ofSeconds(10));
		obj.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.mb-3")));
		List<WebElement> items=driver.findElements(By.cssSelector("div.mb-3"));
		//we got all the product list and want to get text of every item name  in that list
		WebElement prod=items.stream().filter(item->item.findElement(By.cssSelector("b")).
				getText().equals(productname)).findFirst().orElse(null);
		prod.findElement(By.cssSelector("button:last-of-type")).click();
			
		obj.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#toast-container")));
		obj.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();
		
		//get the list of items from cart
		List<WebElement> cartItems=driver.findElements(By.cssSelector("li.items h3"));
		//check each item of the list  and see whether it's matching Banarsi saree or not
		boolean flag=cartItems.stream().anyMatch(item->item.getText().equalsIgnoreCase(productname));//anyMatch gives true or false
		Assert.assertTrue(flag);
		
		driver.findElement(By.xpath("//button[text()='Checkout']")).click();
		driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("India");
		obj.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("section.ta-results")));
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
	
		WebElement e = driver.findElement(By.cssSelector(".btnn"));

		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].click(0);", e);
		
		obj.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h1[@class='hero-primary']"))));
		String confirmMessage=driver.findElement(By.xpath("//h1[@class='hero-primary']")).getText();
		System.out.println(confirmMessage);
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.quit();
		
		

	}

}
