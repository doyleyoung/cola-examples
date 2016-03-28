package com.github.bmsantos.injectables.page.objects;

import com.github.bmsantos.injectables.BaseColaTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.PageFactory.initElements;

public abstract class PageObject<T> {

    private static final int DEFAULT_TIMEOUT = 2;

    public WebDriver driver;

    public PageObject(final WebDriver driver) {
        this.driver = driver;
    }

    public <T extends PageObject> T ensurePageIsLoaded(final T page) {
        page.verifyLoadedConditions();
        return page;
    }

    public abstract T load();

    public void load(final String path) {
        driver.get(BaseColaTest.baseUrl + path);
        initElements(driver, this);
        verifyLoadedConditions();
    }

    public abstract void verifyLoadedConditions();

    protected boolean isDisplayed(final WebElement element) {
        try {
            return element.isDisplayed();
        } catch (final Exception e) {
        }
        return false;
    }

    protected boolean waitFor(final ExpectedCondition<?> condition) {
        try {
            new WebDriverWait(driver, DEFAULT_TIMEOUT).until(condition);
        } catch (final Exception e) {
            return false;
        }
        return true;
    }
}
