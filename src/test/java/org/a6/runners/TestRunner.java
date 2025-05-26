package org.a6.runners;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.FILTER_TAGS_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features") // This should match the folder in src/test/resources
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "org.a6.stepdefs")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty, html:target/cucumber-reports/cucumber-pretty.html, json:target/cucumber.json, junit:target/cucumber-reports/cucumber-results.xml, rerun:target/failed_scenarios.txt")
@ConfigurationParameter(key = FILTER_TAGS_PROPERTY_NAME, value = "@login or @logout or @dashboard")
@ConfigurationParameter(key = "cucumber.junit-platform.naming-strategy", value = "long")
@ConfigurationParameter(key = "cucumber.execution.dry-run", value = "false")
@ConfigurationParameter(key = "cucumber.execution.monochrome", value = "true")
public class TestRunner {
    // The class body remains empty
}
