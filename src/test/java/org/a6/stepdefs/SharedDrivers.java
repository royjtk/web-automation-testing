package org.a6.stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class is used to share the WebDriver instance between step definition
 * classes using Cucumber's dependency injection with PicoContainer
 */
public class SharedDrivers {
    private WebDriver driver;

    // Public constructor for dependency injection
    public SharedDrivers() {
        // Empty constructor for PicoContainer
    }    @Before
    public void setup() {
        if (driver == null) {
            // Menekan warning CDP
            Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);

            // Menggunakan WebDriverManager untuk mengelola driver secara otomatis
            io.github.bonigarcia.wdm.WebDriverManager.chromedriver().setup();            
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");

            // Add additional options to help with stability
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-gpu");
            options.addArguments("--ignore-certificate-errors");
            options.addArguments("disable-password-manager-reauthentication");
            options.setExperimentalOption("prefs", Map.of(
                "credentials_enable_service", false,
                "profile.password_manager_enabled", false,
                "profile.default_content_setting_values.notifications", 2,
                "safebrowsing.enabled", false
            ));

            driver = new ChromeDriver(options);
            driver.manage().window().maximize();

            // Set implicit wait to improve element finding
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        }
    }

    @After(order = 10) // Lower priority (higher number) runs after screenshot is taken
    public void tearDown(Scenario scenario) {
        // Take screenshot if scenario fails
        if (driver != null && scenario.isFailed()) {
            try {
                final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "Screenshot of failure");
            } catch (Exception e) {
                System.err.println("Failed to take screenshot: " + e.getMessage());
            }
        }

        // Quit the driver
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    public WebDriver getDriver() {
        return driver;
    }
}
