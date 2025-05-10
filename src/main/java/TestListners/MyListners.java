package TestListners;

import GenericUtilies.GenericMethods.extentReportHelper.ExtentReporterHelper;
import com.aventstack.extentreports.ExtentReports;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;

import static java.util.logging.FileHandler.*;

public class MyListners implements ITestListener {

    ExtentReports extentReports;
    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getName();
        extentReports.createTest(testName);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("test runns successfully");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("test is failed");

    }

    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {
        ExtentReporterHelper.generateExtentReport();
    }

    @Override
    public void onFinish(ITestContext context) {

    }
}
