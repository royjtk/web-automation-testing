package org.a6.stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class is used to share the WebDriver instance between step definition classes
 * using Cucumber's dependency injection with PicoContainer
 */
public class SharedDrivers {
    private WebDriver driver;    @Before
    public void setup() {
        if (driver == null) {
            // Menekan warning CDP
            Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
            
            // Opsi 2: Setup manual path ChromeDriver
            System.setProperty("webdriver.chrome.driver", "C:\\WebDrivers\\chromedriver-win64\\chromedriver.exe");
            
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            
            // Add additional options to help with stability
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-gpu");
            options.addArguments("--ignore-certificate-errors");
            
            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
            
            // Set implicit wait to improve element finding
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        }
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    public WebDriver getDriver() {
        return driver;
    }
}
