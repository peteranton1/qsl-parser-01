package com.qsl.parser;

import com.qsl.parser.tree.TreeNode;
import com.write.java.JavaWriter;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

public class ParserStepDefinitions {

    private final World world;
    private final ParserRunner parserRunner;
    private final JavaWriter javaWriter;

    public ParserStepDefinitions() {
        this.world = new World();
        this.parserRunner = new ParserRunner();
        this.javaWriter = new JavaWriter();
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
        world.actual = parserRunner.runParser(world.content);
    }

    @When("I submit the parse to the java writer")
    public void iSubmitTheParseToTheJavaWriter() {
        world.log("iSubmitTheParseToTheJavaWriter " + ++world.dummyInt);
        int indent = 1;
        TreeNode root = parserRunner.getTreeNode(world.content);
        world.actual = javaWriter.writeProg(root, indent);
    }

    @Then("I should see compiles to the following:")
    public void iShouldSeeCompilesToTheFollowing(String expected) {
        world.expected = expected;
        world.log("iShouldSeeCompilesToTheFollowing " + ++world.dummyInt);
        world.log("Actual:\n " + world.actual);
        world.log("Expected:\n " + world.expected);
        Assertions.assertEquals(expected, world.actual);
    }

    @Then("I should see transpiles to java:")
    public void iShouldSeeTranspilesToJava(String expected) {
        world.expected = expected;
        world.log("iShouldSeeTranspilesToJava " + ++world.dummyInt);
        world.log("Actual:\n " + world.actual);
        world.log("Expected:\n " + world.expected);
        Assertions.assertEquals(expected, world.actual);
    }

    @When("I submit the content to the lexer")
    public void iSubmitTheContentToTheLexer() {
        world.log("iSubmitTheContentToTheLexer " + ++world.dummyInt);
        world.actual = parserRunner.runLexer(world.content);
    }
}
