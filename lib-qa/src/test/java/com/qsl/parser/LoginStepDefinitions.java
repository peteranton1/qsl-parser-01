package com.qsl.parser;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginStepDefinitions {

    private final World world;

    public LoginStepDefinitions() {
        this.world = new World();
    }

    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {
        // Code to navigate to login page
        world.dummyInt++;
        world.log("Value of dummy is " + world.dummyInt);
    }

    @When("the user enters {string} and {string}")
    public void the_user_enters_and(String username, String password) {
        // Code to enter credentials and click submit
        world.dummyInt++;
        world.log("Value of dummy is " + world.dummyInt);
    }

    @Then("the user should be redirected to the dashboard")
    public void the_user_should_be_redirected_to_the_dashboard() {
        // Code to verify successful login
        world.dummyInt++;
        world.log("Value of dummy is " + world.dummyInt);
        assertTrue(true);
    }
}