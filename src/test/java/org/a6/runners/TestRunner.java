package org.a6.runners;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.*;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features") // Folder feature di src/test/resources/features
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "org.a6.stepdefs") // sesuaikan dengan package step defs kamu
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty, io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm")
@ConfigurationParameter(key = FILTER_TAGS_PROPERTY_NAME, value = "@login or @logout or @dashboard")
@ConfigurationParameter(key = "cucumber.junit-platform.naming-strategy", value = "long")
@ConfigurationParameter(key = "cucumber.execution.dry-run", value = "false")
@ConfigurationParameter(key = "cucumber.execution.monochrome", value = "true")
public class TestRunner {
    // kosong, tidak perlu method apapun
}
