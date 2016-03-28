package com.github.bmsantos.injectables.acceptance.tests;

import com.github.bmsantos.core.cola.story.annotations.ColaInjected;
import com.github.bmsantos.core.cola.story.annotations.Features;
import com.github.bmsantos.core.cola.story.annotations.Given;
import com.github.bmsantos.core.cola.story.annotations.Then;
import com.github.bmsantos.core.cola.story.annotations.When;
import com.github.bmsantos.injectables.BaseColaTest;
import com.github.bmsantos.injectables.page.objects.LoginPage;
import com.github.bmsantos.injectables.page.objects.WelcomePage;
import com.google.inject.Inject;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;

@Features("LoginTest")
@ColaInjected
public class LoginTest extends BaseColaTest {

    private LoginPage loginPage;
    private WelcomePage welcomePage;
    private String user, password;

    @Inject
    public WebDriver inDriver;

    @Before
    public void setUp() {
        initDriver(inDriver);
    }

    @After
    public void tearDown() {
        quitDriver(inDriver);
    }

    @Given("a valid user credential")
    public void givenAValidUserCredential() {
        user = "user";
        password = "password";
    }

    @When("the user logins")
    public void loginUser() {
        loginPage = new LoginPage(getDriver());
        welcomePage = loginPage.load().login(user, password);
    }

    @Then("the welcome greeting will be displayed")
    public void verifyGreetingIsDisplayed() {
        assertThat(welcomePage.isVisible()).isTrue();
    }
}