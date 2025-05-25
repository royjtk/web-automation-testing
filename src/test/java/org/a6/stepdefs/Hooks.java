package org.a6.stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

/**
 * Hooks class to handle actions before and after scenarios
 */
public class Hooks {
    private final SharedDrivers sharedDrivers;

    public Hooks(SharedDrivers sharedDrivers) {
        this.sharedDrivers = sharedDrivers;
    }

    @After
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            WebDriver driver = sharedDrivers.getDriver();
            // Take screenshot if scenario fails
            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Screenshot of failure");
        }
    }
}
