package udemycourse.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import udemycourse.TestComponents.BaseTest;
import udemycourse.TestComponents.Retry;
import udemycourse.pageobjects.CartPage;
import udemycourse.pageobjects.ProductCatalogue;
import java.io.IOException;
import java.util.List;
import org.openqa.selenium.WebElement;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups= {"ErrorHandling"},retryAnalyzer = Retry.class)
	public void loginFailCheck() throws IOException {

		ProductCatalogue productcatalogue = landingpage.loginApplication("anshika@gmail.com", "Iamng@000");
		Assert.assertEquals("Incorrect email or password.", landingpage.getErrorMessage());
	}

	@Test
	public void productErrorValidation() throws IOException {

		String productname = "BANARSI SAREE";

		ProductCatalogue productcatalogue = landingpage.loginApplication("anshika@gmail.com", "Iamking@000");
		List<WebElement> itemsList = productcatalogue.getItemsList();
		productcatalogue.addItemToCart(productname);

		CartPage cartpageobj = productcatalogue.clickOnCart();
		boolean flag = cartpageobj.getProductNameFromCart("BANARSI SAREEEEEEE");
		Assert.assertFalse(flag);

	}

}
