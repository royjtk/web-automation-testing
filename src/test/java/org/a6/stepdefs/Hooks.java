package org.a6.stepdefs;

import io.cucumber.java.After;
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
        this.sharedDrivers = sharedDrivers;    }    @After(order = 1) // Higher priority (lower number) runs first
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            WebDriver driver = sharedDrivers.getDriver();
            // Take screenshot if scenario fails and driver is available
            if (driver != null) {
                try {
                    final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                    scenario.attach(screenshot, "image/png", "Screenshot of failure");
                } catch (Exception e) {
                    System.err.println("Failed to take screenshot: " + e.getMessage());
                }
            } else {
                System.err.println("WebDriver was null, could not take screenshot");
            }
        }
    }
}
