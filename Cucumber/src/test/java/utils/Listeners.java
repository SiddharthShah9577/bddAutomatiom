package utils;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.apache.commons.io.FileUtils;

public class Listeners extends CommonFunctions implements ITestListener {
	@Override
	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub
		getDriver().quit();
		
	}

	@Override
	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub
		launchBrowser();
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailure(ITestResult arg0) {
		// TODO Auto-generated method stub
		try {
			TakesScreenshot scrShot = ((TakesScreenshot) getDriver());
			File source = scrShot.getScreenshotAs(OutputType.FILE);
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH_mm_ss");
			LocalDateTime now = LocalDateTime.now();
			FileUtils.copyFile(source, new File("./Screenshots/ScreenShot"+dtf.format(now)+".png"));
			System.out.println("The Screenshot is taken");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestStart(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestSuccess(ITestResult arg0) {
		// TODO Auto-generated method stub

	}
}
