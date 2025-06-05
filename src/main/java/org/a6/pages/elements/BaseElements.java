package org.a6.pages.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Base class for all Page Element classes
 * Only contains the Page Factory elements and initialization
 */
public abstract class BaseElements {
    protected WebDriver driver;
    protected WebDriverWait wait;
    
    public BaseElements(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Initialize elements with PageFactory
        PageFactory.initElements(driver, this);
    }
    
    /**
     * Creates a longer wait for operations that need more time
     * @return WebDriverWait with longer timeout
     */
    protected WebDriverWait getLongWait() {
        return new WebDriverWait(driver, Duration.ofSeconds(30));
    }
    
    /**
     * Creates a shorter wait for operations that need less time
     * @return WebDriverWait with shorter timeout
     */
    protected WebDriverWait getShortWait() {
        return new WebDriverWait(driver, Duration.ofSeconds(5));
    }
}
