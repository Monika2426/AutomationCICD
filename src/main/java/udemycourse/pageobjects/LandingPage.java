package udemycourse.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import udemycourse.AbstractComponents.AbstractComponent;


public class LandingPage extends AbstractComponent {

	WebDriver driver;
	
	//constructor to get driver
	public LandingPage(WebDriver driver) {
		//initialization
		super(driver);//sending driver to parent class 'AbstractComponent'
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	//Page object model-traditional
	//WebElement email=driver.findElement(By.id("userEmail"));
	
	//Page factory
	@FindBy(id="userEmail")
	WebElement emaillogin;
	
	@FindBy(id="userPassword")
	WebElement passwordlogin;
	
	@FindBy(name="login")
	WebElement login;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	
	public ProductCatalogue loginApplication(String email,String password) {
		emaillogin.sendKeys(email);
		passwordlogin.sendKeys(password);
		login.click();
		ProductCatalogue productcatalogue=new ProductCatalogue(driver);
		return productcatalogue;
		
	}
	
	public String getErrorMessage() {
		waitForTheWebElementToappear(errorMessage);
		return errorMessage.getText();
		
	}
	
	public void navigateToURl() {
		driver.get("https://rahulshettyacademy.com/client/");
	}
}
