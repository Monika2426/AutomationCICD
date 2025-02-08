package udemycourse.TestComponents;

import java.io.IOException;

import org.checkerframework.checker.units.qual.t;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import udemycourse.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener {
	//ITestListener is an interface which have unimplemented methods

	ExtentTest test;
	ExtentReports extent=ExtentReporterNG.getReportObject();
	//to avoid concurrency between tests when testNG XML file runs with attribute parallel="tests", we make the tests Thread safe using ThreadLocal
	ThreadLocal<ExtentTest> extentTest=new ThreadLocal<ExtentTest>();//Thread safe
	//now in place of test we call as extentTest.get
	
	@Override
	public void onTestStart(ITestResult result) {
		// Before running any TestNG test, your control reaches this block and executes the code in this block
		//put extent report test code here as we need that before every test initiation
		//result holds all the information about the test case
		test=extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);//creates unique thread id for every test
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// Control comes to this and execute the code in this block after any test success
		//test.log(Status.PASS, "Test is passed");
		extentTest.get().log(Status.PASS, "Test is Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// Control comes to this and execute the code in this block after any test failure
		//whenever test fails capture the screenshot, write the code for screenshot
		//test.fail(result.getThrowable());
		extentTest.get().fail(result.getThrowable());
		// take screenshot and attach to report
		//to get the driver scope from result, as result holds all the info of the test case
		try {
			driver=(WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		String filepath=null;
		try {
			filepath = getScreenshot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//test.addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());//2nd argument is how the screenshot name has to show in the report
		extentTest.get().addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extent.flush();
	}

}
