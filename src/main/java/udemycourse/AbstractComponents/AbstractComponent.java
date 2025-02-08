package udemycourse.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import udemycourse.pageobjects.CartPage;
import udemycourse.pageobjects.OrderPage;

public class AbstractComponent {
	
	WebDriver driver;
	
	public AbstractComponent(WebDriver driver) { //it is created to catch the driver coming from child class
		this.driver=driver; //to share the driver to remaining methods, we created a class variable and assigned driver which came from child class  to it
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="button[routerlink*='cart']")
	WebElement cartMenu;
	
	@FindBy(css="button[routerlink*='myorders']")
	WebElement orderMenu;
	
	

	public void waitfortheElementToAppear(By element) {
		WebDriverWait obj=new WebDriverWait(driver, Duration.ofSeconds(10));
		obj.until(ExpectedConditions.visibilityOfElementLocated(element));
		
	}
	
	public void waitForTheElementToDisappear(WebElement ele) {
		WebDriverWait obj=new WebDriverWait(driver, Duration.ofSeconds(10));
		obj.until(ExpectedConditions.invisibilityOf(ele));
		
	}
	
	public void waitForTheWebElementToappear(WebElement ele) {
		WebDriverWait obj=new WebDriverWait(driver, Duration.ofSeconds(10));
		obj.until(ExpectedConditions.visibilityOf(ele));
		
	}
	
	//clicking on cart
	public CartPage clickOnCart() {
		cartMenu.click();
		CartPage cartpageobj=new CartPage(driver);
		return cartpageobj;
		
	}
		
	//clicking on orders
	public OrderPage clickOnOrder() {
		orderMenu.click();
		OrderPage orderpageobj=new OrderPage(driver);
		return orderpageobj;
		
	}
		
	
	//Javascript executor code to operate on a web element
	public void javascriptExecutorcode(WebElement e) {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].click(0);", e);
		
		
	}
	
	

}
