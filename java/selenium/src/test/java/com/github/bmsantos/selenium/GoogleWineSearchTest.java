package com.github.bmsantos.selenium;

import com.github.bmsantos.core.cola.story.annotations.*;
import org.junit.Before;
import org.openqa.selenium.WebElement;

import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.By.xpath;
import static org.openqa.selenium.Keys.RETURN;

@Features("google-wine-search")
public class GoogleWineSearchTest extends BaseColaTest {

    public static final String ENTRY_PREFIX = "//cite[normalize-space(.) = 'https://en.wikipedia.org/wiki/";
    public static final String SEARCH_ELEMENT = "//input[@title='Search']";

    private String beverage;

    @Before
    public void setUp() {
        getDriver().get(baseUrl);
    }

    @Given("a <beverage>")
    public void chooseBeverage(@Projection("beverage") final String beverage) {
        this.beverage = beverage;
    }

    @When("a google search is performed")
    public void performGoogleSearch() {
        getDriver().get(baseUrl);
        final WebElement input = getDriver().findElement(xpath(SEARCH_ELEMENT));
        input.sendKeys(beverage + RETURN);
    }

    @Then("the <beverage> wikipedia entry will be present")
    public void verifyBeverageIsPresentInWikipedia(@Projection("beverage") final String beverage) {
        final WebElement wikipediaEntry = getDriver().findElement(
          xpath(ENTRY_PREFIX + beverage.replace(" ", "_") + "']"));

        assertThat(wikipediaEntry).isNotNull();
    }
}