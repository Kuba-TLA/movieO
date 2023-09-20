package tests;

import base.BaseTest;
import org.testng.annotations.Test;

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
