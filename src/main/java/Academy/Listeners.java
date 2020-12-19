package Academy;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import resources.Base;
import resources.ExtentReporterNg;

import java.io.IOException;

public class Listeners extends Base implements ITestListener {

    ExtentReports report = ExtentReporterNg.getReportObject();
    ExtentTest test;

    ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

    @Override
    public void onTestStart(ITestResult iTestResult) {
        String methodName = iTestResult.getMethod().getMethodName();
        test = report.createTest(methodName);
        extentTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        //test.log(Status.PASS, "Test passed");
        extentTest.get().log(Status.PASS, "Test passed");
        //test.pass()
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        WebDriver driver = null;
        String methodName = iTestResult.getMethod().getMethodName();
        try {
            driver = (WebDriver) iTestResult.getTestClass().getRealClass().getDeclaredField("driver").get(iTestResult.getInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String destinationPath = getScreenshot(methodName, driver);
            extentTest.get().addScreenCaptureFromPath(destinationPath);

        } catch (IOException e) {
            e.printStackTrace();
        }
        //test.log(Status.FAIL, "Test Failed");
        extentTest.get().fail(iTestResult.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

        report.flush();

    }
}
