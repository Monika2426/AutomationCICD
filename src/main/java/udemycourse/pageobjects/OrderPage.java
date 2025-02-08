package udemycourse.pageobjects;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import udemycourse.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent{
	
	WebDriver driver;
	
	/*whenever someone creates object for this class to access the methods of this class, the constructor of this class 
	 * will be called and gets the driver scope.
	 */
	public OrderPage(WebDriver driver) { 
		//initialization
		super(driver); //sending driver to parent class 'AbstractComponent'
		this.driver=driver;
		PageFactory.initElements(driver, this); 
	}
	
	//Page factory
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> productNames;
	
	
	
	public boolean verifyOrderDisplay(String productname)
	{
		boolean flag=productNames.stream().anyMatch(item->item.getText().equalsIgnoreCase(productname));
		return flag;
	}
	
	
	
			
	
	
}
