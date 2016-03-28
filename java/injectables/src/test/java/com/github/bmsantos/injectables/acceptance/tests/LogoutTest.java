package com.github.bmsantos.injectables.acceptance.tests;

import com.github.bmsantos.core.cola.story.annotations.ColaInjectable;
import com.github.bmsantos.core.cola.story.annotations.DependsOn;
import com.github.bmsantos.core.cola.story.annotations.Features;
import com.github.bmsantos.core.cola.story.annotations.Given;
import com.github.bmsantos.core.cola.story.annotations.Then;
import com.github.bmsantos.core.cola.story.annotations.When;
import com.github.bmsantos.injectables.BaseColaTest;
import com.github.bmsantos.injectables.page.objects.GreetingPage;
import com.github.bmsantos.injectables.page.objects.LoginPage;
import com.github.bmsantos.injectables.page.objects.WelcomePage;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;

@Features("LogoutTest")
public class LogoutTest extends BaseColaTest {

    @ColaInjectable
    public WebDriver outDriver;

    private LoginPage loginPage;
    private WelcomePage welcomePage;
    private GreetingPage greetingPage;

    @Before
    public void setUp() {
        outDriver = initDriver();
    }

    @After
    public void tearDown() {
        quitDriver();
    }

    @Given("a user is logged in")
    @DependsOn(LoginTest.class)
    public void givenAUserIsLoggedIn() {
        welcomePage = new WelcomePage(getDriver());
        welcomePage.isVisible();
    }

    @When("the user logs out")
    public void logoutUser() {
        greetingPage = welcomePage.seeGreeting();
        loginPage = greetingPage.logOut();
    }

    @Then("the welcome greeting will be displayed")
    public void verifyGreetingIsDisplayed() {
        assertThat(loginPage.isVisible()).isTrue();
    }
}