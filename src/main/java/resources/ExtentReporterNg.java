package resources;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNg {

    public static ExtentReports extentReports;

    public static ExtentReports getReportObject() {
        String path = System.getProperty("user.dir") + "\\reports\\index.html";

        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("RahulShetty academy Automation");
        reporter.config().setDocumentTitle("Test Result");
        extentReports = new ExtentReports();
        extentReports.attachReporter(reporter);
        return extentReports;
    }
}
