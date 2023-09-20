package tests;

import base.BaseTest;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import utils.BrowserUtils;
import utils.JsonUtil;
import utils.SeleniumUtils;

import java.util.List;

public class HomeTest extends BaseTest {
    @Test
    public void test01() {
        System.out.println(getDriver().getTitle());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        logInfo("Test logging info");
    }



}
