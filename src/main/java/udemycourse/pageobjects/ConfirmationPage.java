package udemycourse.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import udemycourse.AbstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent{
	
	WebDriver driver;
	
	/*whenever someone creates object for this class to access the methods of this class, the constructor of this class 
	 * will be called and gets the driver scope.
	 */
	public ConfirmationPage(WebDriver driver) { 
		//initialization
		super(driver); //sending driver to parent class 'AbstractComponent'
		this.driver=driver;
		PageFactory.initElements(driver, this); 
	}
	
	//Page factory
	
	@FindBy(xpath="//h1[@class='hero-primary']")
	WebElement confirmMessage;
	
	public String getConfirmationMessage()
	{
		
		waitForTheWebElementToappear(confirmMessage);
		return confirmMessage.getText();
		
				
	}
	
	
	
	
	

	
			
	
	
}
