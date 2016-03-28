package com.github.bmsantos.injectables.page.objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.openqa.selenium.support.PageFactory.initElements;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class WelcomePage extends PageObject<WelcomePage> {

    @FindBy(xpath="//a[@href='/hello']")
    private WebElement greetingLink;


    public WelcomePage(final WebDriver driver) {
        super(driver);
        initElements(driver, this);
    }

    @Override
    public WelcomePage load() {
        load("/");
        return this;
    }

    @Override
    public void verifyLoadedConditions() {
        waitFor(visibilityOf(greetingLink));
    }

    public boolean isVisible() {
        return isDisplayed(greetingLink);
    }

    public GreetingPage seeGreeting() {
        greetingLink.click();
        return new GreetingPage(driver);
    }
}