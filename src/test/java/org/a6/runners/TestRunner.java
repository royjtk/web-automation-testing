package org.a6.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"org.a6.stepdefs"},
    plugin = {
        "pretty",
        "html:target/cucumber-reports/cucumber-pretty.html",
        "json:target/cucumber.json",
        "junit:target/cucumber-reports/cucumber-results.xml",
        // "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm" // Plugin Allure
        "rerun:target/failed_scenarios.txt"
    },
    monochrome = true,
    dryRun = false,
    tags = "@login or @logout or @dashboard"
)
public class TestRunner {
    // The class body remains empty
}
