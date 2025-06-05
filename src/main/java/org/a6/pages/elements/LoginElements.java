package org.a6.pages.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * Class containing all Page Factory elements for the Login page
 */
public class LoginElements extends BaseElements {    // Page Factory annotations with improved locators based on actual HTML
    @FindBy(how = How.NAME, using = "username")
    public WebElement usernameField;

    @FindBy(how = How.NAME, using = "password")
    public WebElement passwordField;

    @FindBy(how = How.XPATH, using = "//button[@type='submit' and contains(text(), 'Login')]")
    public WebElement loginButton;

    @FindBy(how = How.XPATH, using = "//div[contains(@class, 'bg-[#ffecec]')]//p[contains(text(), 'Incorrect') or contains(text(), 'password') or contains(text(), 'invalid')]")
    public WebElement errorMessage;

    @FindBy(how = How.XPATH, using = "//button[@type='button' and .//svg[contains(@class, 'lucide-eye')]]")
    public WebElement passwordToggleButton;    // Alternative locators for robustness
    @FindBy(how = How.XPATH, using = "//input[@class and contains(@class, 'rounded-md') and contains(@placeholder, 'admin')]")
    public WebElement altUsernameField;

    @FindBy(how = How.XPATH, using = "//input[@type='password' and contains(@placeholder, '*****')]")
    public WebElement altPasswordField;

    @FindBy(how = How.XPATH, using = "//button[@type='submit' and contains(@class, 'bg-[#608BC1]')]")
    public WebElement altLoginButton;

    // Additional error message locators
    @FindBy(how = How.XPATH, using = "//div[contains(@class, 'shadow') and contains(@class, 'rounded-lg') and contains(@class, 'border')]//p")
    public WebElement altErrorMessage;

    public LoginElements(WebDriver driver) {
        super(driver);
    }
}
