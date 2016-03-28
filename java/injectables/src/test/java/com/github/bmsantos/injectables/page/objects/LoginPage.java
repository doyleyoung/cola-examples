package com.github.bmsantos.injectables.page.objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.openqa.selenium.support.PageFactory.initElements;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class LoginPage extends PageObject<LoginPage> {

    @FindBy(id="username")
    private WebElement usernameInput;

    @FindBy(id="password")
    private WebElement passwordInput;

    @FindBy(id="submit")
    private WebElement submitButton;

    public LoginPage(final WebDriver driver) {
        super(driver);
        initElements(driver, this);
    }

    @Override
    public LoginPage load() {
        load("/login");
        return this;
    }

    @Override
    public void verifyLoadedConditions() {
        waitFor(elementToBeClickable(submitButton));
        waitFor(visibilityOf(usernameInput));
        waitFor(visibilityOf(passwordInput));
    }

    public boolean isVisible() {
        return isDisplayed(submitButton);
    }

    public WelcomePage login(final String username, final String password) {
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        submitButton.submit();
        return new WelcomePage(driver);
    }
}