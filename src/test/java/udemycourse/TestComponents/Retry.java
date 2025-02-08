package udemycourse.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

	@Override
	public boolean retry(ITestResult result) {
		// Write your code
		//this method returns true if the failed test needs to be rerun , we can set the max reruns
		int count=0;
		int maxtry=2;
		if(count<maxtry) { //as max try is 2 , the failed tests can ran atmost 2 times
			count++;
			return true; //the test reruns only when this method returns true
		}
		return false;
	}

}
