package com.github.bmsantos.injectables.page.objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.openqa.selenium.support.PageFactory.initElements;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class GreetingPage extends PageObject<GreetingPage> {

    @FindBy(id="signOut")
    private WebElement logoutButton;

    public GreetingPage(final WebDriver driver) {
        super(driver);
        initElements(driver, this);
    }

    @Override
    public GreetingPage load() {
        load("/hello");
        return this;
    }

    @Override
    public void verifyLoadedConditions() {
        waitFor(visibilityOf(logoutButton));
    }

    public boolean isVisible() {
        return isDisplayed(logoutButton);
    }

    public LoginPage logOut() {
        logoutButton.click();
        return new LoginPage(driver);
    }
}