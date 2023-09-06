package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Set;

public class SeleniumUtils {

    public static String switchToWindowAndVerifyTitle(WebDriver driver, ExtentManager extentManager){
        String currentWindowID = driver.getWindowHandle();
        String title = "Verified";

        Set<String> allWindowIDs = driver.getWindowHandles();
        for(String each: allWindowIDs){
            if (!each.equals(currentWindowID)){
                driver.switchTo().window(each);
                title = driver.getTitle();
                extentManager.logScreenshot();
                driver.close();
            }
        }

        driver.switchTo().window(currentWindowID);
        return title;

    }
    public static void switchToNextWindow(WebDriver driver){
        String currentWindowId = driver.getWindowHandle();
        Set<String> allWindowIDs = driver.getWindowHandles();

        for (String eachWindow : allWindowIDs){
            if(!eachWindow.equalsIgnoreCase(currentWindowId)){
                driver.switchTo().window(eachWindow);
            }
        }
    }
}
