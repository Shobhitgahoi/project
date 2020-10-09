package common;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class MyTestListener implements ITestListener{

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		LoggerAgent.LogInfo("The test case is starting..." +result.getName());
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		LoggerAgent.LogInfo(result.getMethod().getMethodName());
    	LoggerAgent.LogInfo((result.getEndMillis() - result.getStartMillis())/1000 + " seconds");
	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		LoggerAgent.LogInfo(result.getTestContext().getName());
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		LoggerAgent.LogInfo(result.getTestContext().getName());
		   LoggerAgent.LogInfo(result.getMethod().getMethodName());
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		LoggerAgent.LogInfo("The test case is starting..." +context.getName());
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		 LoggerAgent.LogInfo("The test case is finishing..." +context.getName());
	}

}