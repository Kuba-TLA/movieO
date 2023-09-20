package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import tests.ConfigReader;
import utils.ExtentManager;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class BaseTest extends ExtentManager{
    public static WebDriver driver;
    protected static ExtentManager reportManager;
    private static final String configFilePath = "configuration.properties";

    @BeforeMethod(alwaysRun = true)
    public void baseSetUp(Method method) {
        getDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(ConfigReader.readProperty(configFilePath, "url"));
    }

    @AfterMethod(alwaysRun = true)
    public void baseTearDown(ITestResult result) {
        driver.quit();
    }

    public static void initializeDriver() {
        switch (ConfigReader.readProperty(configFilePath, "browser")) {
            case "chrome":
                ChromeOptions options = new ChromeOptions();
                options.setAcceptInsecureCerts(true);
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(options);
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "ie":
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
        }
    }

    public static WebDriver getDriver(){
        if (driver == null){
            initializeDriver();
        }
        return driver;
    }
}