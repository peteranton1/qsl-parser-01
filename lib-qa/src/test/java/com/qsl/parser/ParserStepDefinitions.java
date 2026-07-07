package com.qsl.parser;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

public class ParserStepDefinitions {

    private final World world;
    private final ParserRunner underTest;

    public ParserStepDefinitions() {
        this.world = new World();
        this.underTest = new ParserRunner();
    }

    @Given("^I parse the following content:")
    public void parse_the_following_content(String content) {
        world.content = content;
        world.log("parse_the_following_content " + ++world.dummyInt);
        world.log("Content:\n " + world.content);
    }

    @When("I submit the content to the parser")
    public void iSubmitTheContentToTheParser() {
        world.log("iSubmitTheContentToTheParser " + ++world.dummyInt);
        world.actual = underTest.runParser(world.content);
    }

    @Then("I should see compiles to the following:")
    public void iShouldSeeCompilesToTheFollowing(String expected) {
        world.expected = expected;
        world.log("iShouldSeeCompilesToTheFollowing " + ++world.dummyInt);
        world.log("Actual:\n " + world.actual);
        world.log("Expected:\n " + world.expected);
        Assertions.assertEquals(expected, world.actual);
    }

    @When("I submit the content to the lexer")
    public void iSubmitTheContentToTheLexer() {
        world.log("iSubmitTheContentToTheLexer " + ++world.dummyInt);
        world.actual = underTest.runLexer(world.content);
    }
}
