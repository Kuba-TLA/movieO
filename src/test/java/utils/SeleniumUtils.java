package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class SeleniumUtils {
    private WebDriver driver;
    private Actions actions;

    private WebDriverWait wait;


    public void waitUtils(WebDriver driver) {
        int seconds = 20; // Your int value representing seconds
        Duration duration = Duration.ofSeconds(seconds);

        this.driver = driver;
        this.wait = new WebDriverWait(driver, duration);
    }

    // Wait for an element to be visible
    public void waitForElementToBeVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOfElementLocated((By) element));
    }

    // Wait for an element to be clickable
    public void waitForElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable((By) element));
    }

    // Wait for an element to be present
    public void waitForElementToBePresent(WebElement element) {
        wait.until(ExpectedConditions.presenceOfElementLocated((By) element));
    }

    // Wait for a custom condition (example: wait for a specific text to appear)
    public void waitForCustomCondition(ExpectedCondition<Boolean> condition) {
        wait.until(condition);
    }


    // Action Methods
    public void actionsUtils(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
    }

    // Action Click Method
    public void click(WebElement element) {
        actions.click(element).perform();
    }

    // Action Double Click Method
    public void doubleClick(WebElement element) {
        actions.doubleClick(element).perform();
    }


    // Action HoverOver Method
    public void hoverOver(WebElement element) {
        actions.moveToElement(element).perform();
    }

    // Action DragAndDrop Method
    public void dragAndDrop(WebElement source, WebElement target) {
        actions.dragAndDrop(source, target).perform();
    }


    // Method to select an option by visible text
    public static void selectByVisibleText(WebElement dropdownElement, String visibleText) {
        Select select = new Select(dropdownElement);
        select.selectByVisibleText(visibleText);
    }

    // Method to select an option by value attribute
    public static void selectByValue(WebElement dropdownElement, String value) {
        Select select = new Select(dropdownElement);
        select.selectByValue(value);
    }

    // Method to select an option by index
    public static void selectByIndex(WebElement dropdownElement, int index) {
        Select select = new Select(dropdownElement);
        select.selectByIndex(index);
    }

    public static String switchToWindowAndVerifyTitle(WebDriver driver, ExtentManager extentManager) {
        String currentWindowID = driver.getWindowHandle();
        String title = "Verified";

        Set<String> allWindowIDs = driver.getWindowHandles();
        for (String each : allWindowIDs) {
            if (!each.equals(currentWindowID)) {
                driver.switchTo().window(each);
                title = driver.getTitle();
                extentManager.logScreenshot();
                driver.close();
            }
        }

        driver.switchTo().window(currentWindowID);
        return title;

    }

    public static void switchToNextWindow(WebDriver driver) {
        String currentWindowId = driver.getWindowHandle();
        Set<String> allWindowIDs = driver.getWindowHandles();

        for (String eachWindow : allWindowIDs) {
            if (!eachWindow.equalsIgnoreCase(currentWindowId)) {
                driver.switchTo().window(eachWindow);
            }
        }
    }
}
