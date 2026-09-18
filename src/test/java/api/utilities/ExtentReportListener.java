package api.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class ExtentReportListener implements ITestListener {

    private static final ExtentReports extent =
            ExtentReportManager.getReportInstance();

    private static final ThreadLocal<ExtentTest> test =
            new ThreadLocal<>();

    private static final Logger logger =
            LogManager.getLogger(ExtentReportListener.class);


    // ==================== TEST START ====================

    @Override
    public void onTestStart(ITestResult result) {

        String testName =
                result.getMethod().getMethodName();

        Object[] parameters =
                result.getParameters();

        /*
         * DataDrivenTests parameters:
         *
         * 0 = id
         * 1 = username
         * 2 = firstName
         * 3 = lastName
         * ...
         *
         * This allows Extent Report to display:
         *
         * testUserLifecycle [user201]
         * testUserLifecycle [user202]
         * testUserLifecycle [user203]
         */

        if (parameters != null &&
                parameters.length > 1) {

            String username =
                    String.valueOf(parameters[1]);

            testName =
                    testName + " [" + username + "]";
        }

        ExtentTest extentTest =
                extent.createTest(testName);

        test.set(extentTest);

        logger.info(
                "Starting test: {}",
                testName);
    }


    // ==================== TEST PASS ====================

    @Override
    public void onTestSuccess(ITestResult result) {

        logger.info(
                "Test passed: {}",
                result.getMethod().getMethodName());

        if (test.get() != null) {
            test.get().pass("Test Passed");
            test.remove();
        }
    }


    // ==================== TEST FAILURE ====================

    @Override
    public void onTestFailure(ITestResult result) {

        logger.error(
                "Test failed: {}",
                result.getMethod().getMethodName(),
                result.getThrowable());

        if (test.get() != null) {

            test.get().fail("Test Failed");

            if (result.getThrowable() != null) {
                test.get().fail(result.getThrowable());
            }

            test.remove();
        }
    }


    // ==================== TEST SKIPPED ====================

    @Override
    public void onTestSkipped(ITestResult result) {

        logger.warn(
                "Test skipped: {}",
                result.getMethod().getMethodName());

        if (test.get() != null) {
            test.get().skip("Test Skipped");
            test.remove();
        }
    }


    // ==================== FINISH ====================

    @Override
    public void onFinish(ITestContext context) {

        logger.info(
                "Test execution completed. Generating Extent Report.");

        extent.flush();

        logger.info(
                "Extent Report generated successfully.");
    }
}