package udemycourse.pageobjects;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import udemycourse.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent{
	
	WebDriver driver;
	
	/*whenever someone creates object for this class to access the methods of this class, the constructor of this class 
	 * will be called and gets the driver scope.
	 */
	public CartPage(WebDriver driver) { 
		//initialization
		super(driver); //sending driver to parent class 'AbstractComponent'
		this.driver=driver;
		PageFactory.initElements(driver, this); 
	}
	
	//Page factory
	
	@FindBy(css="li.items h3")
	List<WebElement> itemsofCart;
	
	@FindBy(xpath="//button[text()='Checkout']")
	WebElement checkoutButton;
	
	
	public boolean getProductNameFromCart(String productname)
	{
		boolean flag=itemsofCart.stream().anyMatch(item->item.getText().equalsIgnoreCase(productname));
		return flag;
	}
	
	public CheckoutPage gotoCheckout() {
		checkoutButton.click();
		CheckoutPage checkoutpageobj=new CheckoutPage(driver);
		return checkoutpageobj;
		
	}
	
			
	
	
}
