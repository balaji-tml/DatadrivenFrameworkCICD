package com.bit.listeners;

import com.aventstack.extentreports.Status;
import com.bit.base.BaseTest;
import com.bit.utilities.ExtentManager;
import com.bit.utilities.TestUtil;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.SkipException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import static com.bit.utilities.ExtentTestManager.getTest;
import static com.bit.utilities.ExtentTestManager.startTest;

public class CustomListener extends BaseTest implements ITestListener {
    String screenshotName = null;
    public String meaasgeBody;
    private static String getTestMethodName(ITestResult iTestResult) {
        log.info("In getTestMethodName()");
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }
    @Override
    public void onTestStart(ITestResult result) {
        log.info("In onTestStart()");
        ITestListener.super.onTestStart(result);
        test = startTest(result.getName(), "");
        if(!TestUtil.isTestRunnable(result.getName(),excel))
        {
            throw  new SkipException("Skipping the test "+result.getName().toUpperCase() + " as the RunMode is No");
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("In onTestSuccess()");
//        screenshotName = "Img_"+new SimpleDateFormat("yyyyMMddHHmm'.jpg'").format(new Date());
        screenshotName = "Img_"+ new SimpleDateFormat("yyyyMMddHHmmss'.jpg'", Locale.getDefault()).format(new Date());
        ITestListener.super.onTestSuccess(result);
        TestUtil.captureScreenshot(userDir + "/target/"+screenshotName);
        //Get driver from BaseTest and assign to local webdriver variable.
        Object testClass = result.getInstance();
        WebDriver driver = ((BaseTest) testClass).getDriver();
        //Take base64Screenshot screenshot for extent reports
        String base64Screenshot =
                "data:image/png;base64," + ((TakesScreenshot) Objects.requireNonNull(driver)).getScreenshotAs(OutputType.BASE64);
        //ExtentReports log and screenshot operations for failed tests.
        getTest().log(Status.PASS, "Test Pass",
                getTest().addScreenCaptureFromBase64String(base64Screenshot).getModel().getMedia().get(0));
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.info("In onTestFailure()");
        ITestListener.super.onTestFailure(result);
        TestUtil.captureScreenshot(userDir + "/target/"+screenshotName);
        //Get driver from BaseTest and assign to local webdriver variable.
        Object testClass = result.getInstance();
        WebDriver driver = ((BaseTest) testClass).getDriver();
        //Take base64Screenshot screenshot for extent reports
        String base64Screenshot =
                "data:image/png;base64," + ((TakesScreenshot) Objects.requireNonNull(driver)).getScreenshotAs(OutputType.BASE64);
        //ExtentReports log and screenshot operations for failed tests.
        getTest().log(Status.FAIL, "Test Failed",
                getTest().addScreenCaptureFromBase64String(base64Screenshot).getModel().getMedia().get(0));
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.info("In onTestSkipped()");
        ITestListener.super.onTestSkipped(result);
        getTest().log(Status.SKIP, result.getName().toUpperCase(Locale.ROOT)+ "Skipped Test as RunMode is No");
        ExtentManager.extentReports.flush();
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        log.info("In onTestFailedButWithinSuccessPercentage()");
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        log.info("In onTestFailedWithTimeout()");
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public void onStart(ITestContext context) {
        log.info("In onStart()");
        ITestListener.super.onStart(context);
        context.setAttribute("WebDriver", this.driver);
    }

    @Override
    public void onFinish(ITestContext context) {
        log.info("In onFinish()");
        ITestListener.super.onFinish(context);
        ExtentManager.extentReports.flush();

    }
}