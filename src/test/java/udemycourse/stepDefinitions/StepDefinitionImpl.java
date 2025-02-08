package udemycourse.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import udemycourse.TestComponents.BaseTest;
import udemycourse.pageobjects.CartPage;
import udemycourse.pageobjects.CheckoutPage;
import udemycourse.pageobjects.ConfirmationPage;
import udemycourse.pageobjects.LandingPage;
import udemycourse.pageobjects.ProductCatalogue;

public class StepDefinitionImpl extends BaseTest {

	public LandingPage landingPage;
	public ProductCatalogue productcatalogue;
	public CartPage cartpageobj;
	public CheckoutPage checkoutpageobj;
	public ConfirmationPage confirmobj;

	@Given("I landed on Ecommerce Page")
	public void landingPage() throws IOException {
		landingPage = launchApplication();
	}

	@Given("^logged in with username (.+) and (.+) <password>$")

	public void login(String username,String password) {
		productcatalogue=landingpage.loginApplication(username, password);
	}
	
	@When ("^I add product (.+) to the cart$")
	public void addingProducttoCart(String productname) {
		List<WebElement> itemsList=productcatalogue.getItemsList();
		productcatalogue.addItemToCart(productname);		
	}
	
	@And ("^Checkout (.+) and submit the order$")
	public void submittheOrder(String productname) {
		cartpageobj=productcatalogue.clickOnCart();
		boolean flag=cartpageobj.getProductNameFromCart(productname);
		Assert.assertTrue(flag);		
		checkoutpageobj=cartpageobj.gotoCheckout();
		checkoutpageobj.getCountryName("India");	
		confirmobj=checkoutpageobj.submitOrder();
	}
	
	@Then ("{string} message is displayed on ConfirmationPage")
	public void confirmationmessage(String string) {
		String confirmMessage=confirmobj.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.close();
		
	}
	
	@Then ("{string1} message is displayed")
	public void displayingMessage(String string1) {
		Assert.assertEquals(string1, landingpage.getErrorMessage());
		driver.close();
		Assert.assertEquals("Incorrect email or password.", landingpage.getErrorMessage());
		
	}
}


