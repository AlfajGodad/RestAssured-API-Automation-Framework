package api.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {

    private static ExtentReports extent;

    public static ExtentReports getReportInstance() {

        if (extent == null) {

            String timestamp =
                    new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss")
                            .format(new Date());

            String reportPath =
                    System.getProperty("user.dir")
                    + "/reports/API_Test_Report_"
                    + timestamp
                    + ".html";

            ExtentSparkReporter spark =
                    new ExtentSparkReporter(reportPath);

            spark.config().setDocumentTitle(
                    "REST API Automation Report");

            spark.config().setReportName(
                    "Petstore API Test Results");

            extent = new ExtentReports();

            extent.attachReporter(spark);

            extent.setSystemInfo(
                    "Framework", "REST Assured");

            extent.setSystemInfo(
                    "Language", "Java");

            extent.setSystemInfo(
                    "Test Framework", "TestNG");

            extent.setSystemInfo(
                    "Environment", "QA");
        }

        return extent;
    }
}