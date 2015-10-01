package com.github.bmsantos.selenium;

import com.github.bmsantos.core.cola.story.annotations.IdeEnabler;
import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.assertj.core.api.Assertions.fail;

public abstract class BaseColaTest {

    public static String baseUrl = "http://google.com";
    private static WebDriver driver;

    public BaseColaTest() {
        if (driver == null) {
            createDriver();
        }
    }

    @AfterClass
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    private void createDriver() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, SECONDS);
    }

    @IdeEnabler
    @Test
    public void thisTestWillNotExecute() {
        fail("This test failed because it is not supposed to be executed!");
    }
}
