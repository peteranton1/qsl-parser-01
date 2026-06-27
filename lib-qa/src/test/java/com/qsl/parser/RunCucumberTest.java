package com.qsl.parser;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;
import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber") // Tells JUnit to use the Cucumber engine
@SelectClasspathResource("features") // Path to your feature files
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.qsl.parser") // Path to your glue/steps
public class RunCucumberTest {
}
