package org.gradle.cola.tests;

import static org.junit.Assert.assertEquals;

import com.github.bmsantos.core.cola.story.annotations.Feature;
import com.github.bmsantos.core.cola.story.annotations.Given;
import com.github.bmsantos.core.cola.story.annotations.Then;
import com.github.bmsantos.core.cola.story.annotations.When;

public class PersonTest extends BaseTest {

    @Feature
    private final String personFeature =
        "Feature: Introduce Larry\n"
            + "Scenario: Should create larry\n"
            + "Given a new Person\n"
            + "When the name is set to Larry\n"
            + "Then the person name will be Larry";

    private Person person;

    @Given("a new Person")
    public void given() {
        person = new Person();
    }

    @When("the name is set to Larry")
    public void when() {
        person.setName("Larry");
    }

    @Then("the person name will be Larry")
    public void then() {
        assertEquals("Larry", person.getName());
    }
}
