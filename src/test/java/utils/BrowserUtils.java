package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import base.BaseTest;

public class BrowserUtils extends BaseTest {
    public static void switchToNewWindow(WebDriver driver){
        for (String each : driver.getWindowHandles()) {
            if (!each.equals(driver.getWindowHandle()))
                driver.switchTo().window(each);
        }
    }
    public static void selectByText(WebElement element, String textToSelect) {
        Select select = new Select(element);
        select.selectByVisibleText(textToSelect);
    }

    public static void selectByIndex(WebElement element, int index) {
        Select select = new Select(element);
        select.selectByIndex(index);
    }

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}