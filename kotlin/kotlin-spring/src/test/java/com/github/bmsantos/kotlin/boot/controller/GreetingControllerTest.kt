package com.github.bmsantos.kotlin.boot.controller

import cola.ide.BaseColaTest
import com.github.bmsantos.core.cola.story.annotations.Assigned
import com.github.bmsantos.core.cola.story.annotations.Features
import com.github.bmsantos.core.cola.story.annotations.Then
import com.github.bmsantos.core.cola.story.annotations.When
import org.hamcrest.CoreMatchers.containsString
import org.junit.Before
import org.junit.runner.RunWith
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.mock.web.MockServletContext
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup

@RunWith(SpringJUnit4ClassRunner::class)
@SpringApplicationConfiguration(classes = arrayOf(MockServletContext::class))
@WebAppConfiguration
@Features("greeting-controller")
public class GreetingControllerTest : BaseColaTest() {

    private var mvc:MockMvc? = null
    private var result:ResultActions? = null

    @Before
    public fun init() {
        mvc = standaloneSetup(GreetingController()).build()
    }

    @When("a greeting request is submitted")
    public fun submitGreeting() {
        result = mvc?.perform(get("/greeting"))
    }

    @When("a personalized greeting request is submitted")
    public fun submitPersonalizedGreeting() {
        result = mvc?.perform(get("/greeting?name=Einstein"))
    }

    @Then("it will display <greeting>")
    public fun verifyGreeting(@Assigned("greeting") greeting:String) {
        result?.andExpect(content().string(containsString(greeting)))
    }
}