package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;

public class ExtentManager {
    protected ExtentReports extentReports;
    protected ExtentTest extentTest;

    @BeforeSuite(alwaysRun = true)
    public void createReport(){
        extentReports = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter("report.html");

        spark.config().setTheme(Theme.DARK);
        spark.config().setDocumentTitle("My Report");
        spark.config().setReportName("MovieO Report");
        extentReports.attachReporter(spark);
    }
    @AfterSuite(alwaysRun = true)
    public void closeReporter(){
        extentReports.flush();
    }

    @BeforeMethod(alwaysRun = true)
    public ExtentTest createTestReport(Method method){
        extentTest = extentReports.createTest(getCustomTestName(method));
        logTestGroups(method);
        return extentTest;
    }

    @AfterMethod(alwaysRun = true)
    public void closeTestReport(ITestResult result){
        if(result.getStatus() == ITestResult.SUCCESS){
            extentTest.pass("TEST PASSED");
        }else if (result.getStatus() == ITestResult.FAILURE){
            extentTest.fail("TEST FAILED");
            //logScreenshot("Failed");
            extentTest.fail(result.getThrowable());
        }else if(result.getStatus() == ITestResult.SKIP){
            extentTest.skip("TEST SKIPPED");
            extentTest.fail(result.getThrowable());
        }
    }

    public void logTestGroups(Method method){
        Test testClass = method.getAnnotation(Test.class);
        for(String e: testClass.groups()){
            extentTest.assignCategory(e);
        }
    }
    public String getCustomTestName(Method method){
        Test testClass = method.getAnnotation(Test.class);
        if(testClass.testName().length() > 0)
            return testClass.testName();
        return method.getName();
    }
    public String getCustomTestDescription(Method method){
        Test testClass = method.getAnnotation(Test.class);
        if(testClass.description().length() > 0)
            return testClass.description();
        return method.getName();
    }
//    public void logScreenshot(String title){
//        extentTest.info(title, MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.takeScreenshot(driver)).build());
//    }

//    public void logScreenshot() {
//        if (ConfigReader.readProperty("configuration.properties","takeScreenshots").equalsIgnoreCase("true"))
//        extentTest.info(MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.takeScreenshot(driver)).build());
//    }
    public void logInfo(String info){
        extentTest.info(info);
    }
    public void logLocatorInfo(String msg, WebElement element){
        String str = element.toString();
        extentTest.info(msg + "->" + str.substring(str.indexOf("->") + 2, str.length() -1));
    }
}
