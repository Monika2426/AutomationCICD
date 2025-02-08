package udemycourse.pageobjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import udemycourse.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent{
	
	WebDriver driver;
	
	/*whenever someone creates object for this class to access the methods of this class, the constructor of this class 
	 * will be called and gets the driver scope.
	 */
	public CheckoutPage(WebDriver driver) { 
		//initialization
		super(driver); //sending driver to parent class 'AbstractComponent'
		this.driver=driver;
		PageFactory.initElements(driver, this); 
	}
	
	//Page factory
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement selectCountrydropdown;
	
	@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
	WebElement selectCountry;
	
	By dropdownCountryList=By.cssSelector("section.ta-results");
	
	@FindBy(css=".btnn")
	WebElement placeOrder;
	
	
	public void getCountryName(String countryname)
	{
		selectCountrydropdown.sendKeys(countryname);
		waitfortheElementToAppear(dropdownCountryList);
		selectCountry.click();
		
				
	}
	
	public ConfirmationPage submitOrder() {
		javascriptExecutorcode(placeOrder);
		ConfirmationPage confirmpageobj=new ConfirmationPage(driver);
		return confirmpageobj;
		
		
	}
	
	
	

	
			
	
	
}
