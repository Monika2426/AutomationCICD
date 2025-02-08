package udemycourse.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import udemycourse.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent{
	
	WebDriver driver;
	
	/*whenever someone creates object for this class to access the methods of this class, the constructor of this class 
	 * will be called and gets the driver scope.
	 */
	public ProductCatalogue(WebDriver driver) { 
		//initialization
		super(driver); //sending driver to parent class 'AbstractComponent'
		this.driver=driver;
		PageFactory.initElements(driver, this); 
	}
	
	//Page factory
	//List<WebElement> items=driver.findElements(By.cssSelector("div.mb-3"));
	
	@FindBy(css="div.mb-3")
	List<WebElement> items;
	
	By itemsBy=By.cssSelector("div.mb-3");
	By addtocart=By.cssSelector("button:last-of-type");
	By toast=By.cssSelector("div#toast-container");
	
	@FindBy(css=".ng-animating")
	WebElement animating;
	
	public List<WebElement> getItemsList() {
		waitfortheElementToAppear(itemsBy);
		return items;
	}
	
	public WebElement getProductByName(String productname)
	{
		WebElement prod=getItemsList().stream().filter(item->item.findElement(By.cssSelector("b")).
				getText().equals(productname)).findFirst().orElse(null);
		return prod;
	}
	
	public void addItemToCart(String productname) {
		WebElement prod=getProductByName(productname);
		prod.findElement(addtocart).click();//clicking on 'add to cart'
		waitfortheElementToAppear(toast);
		waitForTheElementToDisappear(animating);
		
	}
}
