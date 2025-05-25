package org.a6.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private final String LOGIN_URL = "http://ptbsp.ddns.net:6882/login";
    private WebDriverWait wait;    @FindBy(id = "username")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginButton;

    @FindBy(xpath = "//div[contains(@class, 'error-message')]")
    private WebElement errorMessage;

    @FindBy(xpath = "//button[contains(@class, 'toggle-password')]")
    private WebElement passwordToggleButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }    public void navigateToLoginPage() {
        driver.get(LOGIN_URL);
        try {
            // Wait with increased timeout for slow page loads
            wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOf(usernameField),
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@name, 'username') or contains(@id, 'username') or contains(@placeholder, 'Username')]"))
            ));
        } catch (Exception e) {
            System.out.println("Warning: Username field not found with standard locators. Trying alternative methods.");
            
            // If the standard locator fails, try to find input fields by various attributes
            try {
                WebElement usernameInput = driver.findElement(
                    By.xpath("//input[contains(@type, 'text') or contains(@placeholder, 'Username') or contains(@placeholder, 'Email')]"));
                
                if (usernameInput != null) {
                    this.usernameField = usernameInput;
                }
            } catch (Exception ex) {
                System.out.println("Error finding username field: " + ex.getMessage());
            }
        }
    }

    public void enterUsername(String username) {
        usernameField.clear();
        usernameField.sendKeys(username);
    }

    public void enterPassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public boolean isErrorMessageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(errorMessage));
            return errorMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getErrorMessageText() {
        if (isErrorMessageDisplayed()) {
            return errorMessage.getText();
        } else {
            return "";
        }
    }

    public boolean isLoginPageDisplayed() {
        return driver.getCurrentUrl().contains("/login");
    }

    public void clickPasswordVisibilityToggle() {
        passwordToggleButton.click();
    }

    public String getPasswordFieldType() {
        return passwordField.getAttribute("type");
    }

    public boolean isPasswordVisible() {
        return "text".equals(getPasswordFieldType());
    }
}
