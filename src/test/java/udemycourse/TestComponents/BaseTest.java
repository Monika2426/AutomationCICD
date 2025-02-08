package udemycourse.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import udemycourse.pageobjects.LandingPage;


public class BaseTest {
	
	public WebDriver driver;
	public LandingPage landingpage;
	
	public WebDriver initializeDriver() throws IOException {
		
		//using Properties class we are getting data from Global.properties class file
		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\udemycourse\\resources\\GlobalData.properities");
		prop.load(fis);//the argument is expecting input stream as type not path
		String browserName=prop.getProperty("browser");
		
		if(browserName.contains("chrome")) {
			ChromeOptions options=new ChromeOptions();//to run in headless mode
			if(browserName.contains("chromeheadless")) {
				options.addArguments("headless");
			}
			
			driver=new ChromeDriver(options);	
			driver.manage().window().setSize(new Dimension(1440, 900)); //to avoid element not visible exception, we maximize the screen
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
			//Firefox driver
		}
		else if(browserName.equalsIgnoreCase("edge")) {
			driver=new EdgeDriver();
						
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		return driver;
		
	}
	
	public List<HashMap<String, String>> getJSONDataToMap(String filepath) throws IOException {
		
		//Step-1:read JSON to string
		String JSONContent=FileUtils.readFileToString(new File(filepath), StandardCharsets.UTF_8);
		
		/*Another way to read JSON to String
		Path path=Path.of("C://Users//monie//eclipse-workspace//SeleniumFramework//src//test//java//udemycourse//data//PurchaseOrder.json");
		String JSONContent=Files.readString(path); //this method reads data from JSON and stores the data in string variable
*/		
		/*Step-2: To convert String to HashMap- we need jackson databind
		We need Jackson data bind which has Object Mapper class in order to convert string to Hashmap, this class has 'readValue' method which takes 2 parameters 
			○ The string variable which holds the data of JSON should be as first parameter.
			○ The second parameter will, create Hashmap for the data in the JSON and put that in a list and return that  list .

		 */
		ObjectMapper mapper=new ObjectMapper();
		List<HashMap<String, String>> data=mapper.readValue(JSONContent, new TypeReference<List<HashMap<String,String>>>(){		
		});
		return data;	
	}
	
	//to get screenshot of the test whenever it fails
	public String getScreenshot(String testCaseName,WebDriver driver) throws IOException {
		TakesScreenshot ts=(TakesScreenshot)driver;
		File src=ts.getScreenshotAs(OutputType.FILE);
		File des=new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
		FileUtils.copyFile(src, des);
		return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
	}
	
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException {
		driver=initializeDriver();
		//driver should be passed while creating object to give that scope of driver to that class
		landingpage=new LandingPage(driver);
		landingpage.navigateToURl();
		return landingpage;
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		driver.quit();
		
	}

}
