package com.github.bmsantos.injectables;

import com.github.bmsantos.core.cola.story.annotations.IdeEnabler;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.assertj.core.api.Assertions.fail;

public abstract class BaseColaTest {

    public static String baseUrl = "http://localhost:8080";
    protected WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    protected WebDriver initDriver() {
        return initDriver(null);
    }

    protected WebDriver initDriver(final WebDriver inDriver) {
        if (inDriver == null) {
            createDriver();
        } else {
            driver = inDriver;
        }
        return driver;
    }

    protected void createDriver() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, SECONDS);
    }

    protected void quitDriver() {
        quitDriver(null);
    }

    protected void quitDriver(final WebDriver inDriver) {
        if (inDriver == null && driver != null) {
            driver.quit();
        }
    }

    @IdeEnabler
    @Test
    public void thisTestWillNotExecute() {
        fail("This test failed because it is not supposed to be executed!");
    }
}
