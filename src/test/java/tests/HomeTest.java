package tests;

import base.BaseTest;
import org.testng.annotations.Test;

public class HomeTest extends BaseTest {
    @Test
    public void test01() {
        System.out.println(getDriver().getTitle());

        logInfo("Test logging info");
    }
}
