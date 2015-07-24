package com.github.bmsantos.cola.kotlin

import java.util.ArrayList
import com.github.bmsantos.core.cola.story.annotations.Given
import com.github.bmsantos.core.cola.story.annotations.When
import com.github.bmsantos.core.cola.story.annotations.Then
import org.junit.Assert.assertThat
import org.hamcrest.Matchers.contains
import cola.ide.BaseColaTest

/**
 * Created with IntelliJ IDEA.
 * Date: 11/29/14
 * Time: 10:48 PM
 */
class KotlinColaTest : BaseColaTest() {

    private val stories: String = """
       Feature: Introduce drinking
        Scenario: Should get happy
         Given a beer to enjoy
         When mixed with 13 other alcoholic drinks
         Then one will be drunk!

        Scenario: Should be energetic
         Given a juice to enjoy
         When mixed with 10 other redbulls
         Then one will be really energetic!
    """

    private val executionOrder = ArrayList<String>();

    @Given("a (juice|beer) to enjoy")
    public fun givenADrink() {
        executionOrder.add(Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @When("""mixed with \d+ other (redbulls|alcoholic drinks)""")
    public fun whenMixed() {
        executionOrder.add(Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Then("one will be (drunk|really energetic)!")
    public fun thenWillBePissed() {
        assertThat(executionOrder, contains("givenADrink", "whenMixed"));
    }
}