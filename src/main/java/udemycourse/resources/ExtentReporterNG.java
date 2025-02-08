package udemycourse.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	public static ExtentReports getReportObject() {
		String path=System.getProperty("user.dir")+"\\reports\\index.html";
		ExtentSparkReporter reporter=new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Test Results");//we can set or change report name
		reporter.config().setDocumentTitle("Test Results");
		
		ExtentReports extent=new ExtentReports();//this is a main class which consolidates all our tests
		extent.attachReporter(reporter);//we need to attach the report created by spark to extent object
		extent.setSystemInfo("Tester", "Monika");
		extent.createTest("Demo");
		return extent;
		
	}

}
