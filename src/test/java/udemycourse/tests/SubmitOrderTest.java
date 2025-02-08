package udemycourse.tests;


import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import udemycourse.TestComponents.BaseTest;
import udemycourse.pageobjects.CartPage;
import udemycourse.pageobjects.CheckoutPage;
import udemycourse.pageobjects.ConfirmationPage;
import udemycourse.pageobjects.OrderPage;
import udemycourse.pageobjects.ProductCatalogue;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.WebElement;


public class SubmitOrderTest extends BaseTest {
	String productname="BANARSI SAREE";	

	@Test(dataProvider = "getData",groups= {"Purchase"})
	public void submitOrder(HashMap<String, String> input) throws IOException {
	//	public void submitOrder(String email, String password) throws IOException
			
		//ProductCatalogue productcatalogue=landingpage.loginApplication(email, password);
		ProductCatalogue productcatalogue=landingpage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> itemsList=productcatalogue.getItemsList();
		productcatalogue.addItemToCart(input.get("product"));
		
		CartPage cartpageobj=productcatalogue.clickOnCart();
		boolean flag=cartpageobj.getProductNameFromCart(input.get("product"));
		Assert.assertTrue(flag);		
		CheckoutPage checkoutpageobj=cartpageobj.gotoCheckout();
		checkoutpageobj.getCountryName("India");
		
		ConfirmationPage confirmobj=checkoutpageobj.submitOrder();
		String confirmMessage=confirmobj.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}
	
	@Test(dependsOnMethods = {"submitOrder"})
	public void orderHistoryTest() {
		//checks whether order is placed
		ProductCatalogue productcatalogue=landingpage.loginApplication("Udemy1@gmail.com", "Udemy@1234");
		OrderPage orderpageobj=productcatalogue.clickOnOrder();
		Assert.assertTrue(orderpageobj.verifyOrderDisplay(productname));
				
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		/*HashMap<String, String> map=new HashMap<String, String>();
		map.put("email", "Udemy1@gmail.com");
		map.put("password", "Udemy@1234");
		map.put("product", "BANARSI SAREE");
		
		HashMap<String, String> map1=new HashMap<String, String>();
		map1.put("email", "anshika@gmail.com");
		map1.put("password", "Iamking@000");
		map1.put("product", "IPHONE 13 PRO");
		return new Object[][] {{map},{map1}};*/
		
		List<HashMap<String, String>> data=getJSONDataToMap(System.getProperty("user.dir")+"//src//test//java//udemycourse//data//PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
	/*@DataProvider
	public Object[][] getData() {
		return new Object[][] {{"Udemy1@gmail.com","Udemy@1234","BANARSI SAREE"},{"anshika@gmail.com","Iamking@000","IPHONE 13 PRO"}};
	}*/
	
	

}
