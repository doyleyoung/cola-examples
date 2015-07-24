package com.github.bmsantos.spring.test.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.annotation.Resource;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import cola.ide.BaseColaTest;

import com.github.bmsantos.core.cola.story.annotations.Given;
import com.github.bmsantos.core.cola.story.annotations.Then;
import com.github.bmsantos.core.cola.story.annotations.When;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { WebAppContext.class })
public class HelloWorldControllerTest extends BaseColaTest {

    private final String stories =
        "Feature: Introduce REST endpoint\n"
            + "Scenario: Should say hello\n"
            + "Given a web endpoint\n"
            + "When hit by a get request\n"
            + "Then the HTTP status will be OK\n"
            + "And the body will say hello world";

    @Resource
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    private ResultActions result;

    @Given("a web endpoint")
    public void given() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @When("hit by a get request")
    public void when() throws Exception {
        result = mockMvc.perform(get("/helloWorld"));
    }

    @Then("the HTTP status will be OK")
    public void thenOk() throws Exception {
        result.andExpect(status().isOk());
    }

    @Then("the body will say hello world")
    public void thenHello() throws Exception {
        result.andExpect(content().string("Hello World!"));
    }
}