package com.github.bmsantos.cdi;

import com.github.bmsantos.core.cola.story.annotations.Feature;
import com.github.bmsantos.core.cola.story.annotations.Given;
import com.github.bmsantos.core.cola.story.annotations.Then;
import com.github.bmsantos.core.cola.story.annotations.When;
import org.jglue.cdiunit.CdiRunner;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.assertj.core.api.StrictAssertions.assertThat;

@RunWith(CdiRunner.class)
public class StarshipTest {

  @Feature
  private final String warpTravel =
  "Feature: Warp Travel\n"
    + "As a Starship commander\n"
    + "I want to switch to warp mode\n"
    + "So that I do not have to call bridge\n"
    + "\n"
    + "Scenario: Should start engine\n"
    + "Given a stabilized Starship\n"
    + "When the Warp Engine is initiated\n"
    + "Then the Starship will warp";

  @Inject
  private Starship starship;

  @Given("a stabilized Starship")
  public void givenAStarship() {
    assertThat(starship.isRunning()).isFalse();
  }

  @When("the Warp Engine is initiated")
  public void initiateWarpEngine() {
    starship.start();
  }

  @Then("the Starship will warp")
  public void verifyStarshipIsWarping() {
    assertThat(starship.isRunning()).isTrue();
  }
}
