package utils;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.Select;
import base.BaseTest;

public class BrowserUtils extends BaseTest {
    public void clearCache() {
        driver.manage().deleteAllCookies();
    }

    public void openInIncognitoMode() {
        if (driver instanceof ChromeDriver) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--incognito");
            driver = new ChromeDriver(options);
        } else if (driver instanceof FirefoxDriver) {
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("-private");
            driver = new FirefoxDriver(options);
        } else {
            throw new UnsupportedOperationException("Incognito mode is not supported in this browser");
        }
    }

    public void resizeWindow(int width, int height) {
        driver.manage().window().setSize(new Dimension(width, height));
    }

    public void maximizeWindow() {
        driver.manage().window().maximize();
    }

    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public void navigateBack() {
        driver.navigate().back();
    }

    public void navigateForward() {
        driver.navigate().forward();
    }

    public void refreshPage() {
        driver.navigate().refresh();
    }
}