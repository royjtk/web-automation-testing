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
    private WebDriverWait wait;    @FindBy(name = "username")
    private WebElement usernameField;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@type='submit' and contains(., 'Login')]")
    private WebElement loginButton;    @FindBy(xpath = "//div[contains(@class, 'error-message') or contains(text(), 'wajib') or contains(text(), 'salah') or contains(text(), 'gagal')]")
    private WebElement errorMessage;

    @FindBy(xpath = "//button[contains(@class, 'toggle-password') or .//svg[contains(@class, 'lucide-eye')]]")
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
    }    public void enterUsername(String username) {
        try {
            wait.until(ExpectedConditions.visibilityOf(usernameField));
            usernameField.clear();
            usernameField.sendKeys(username);
        } catch (Exception e) {
            System.out.println("Warning: Username field not found with standard locator. Trying alternatives.");
            
            // Metode alternatif - cari input field berdasarkan berbagai atribut
            try {
                WebElement alternativeUsernameField = driver.findElement(
                    By.xpath("//input[@name='username' or @id='username' or contains(@placeholder, 'Username') or contains(@placeholder, 'username')]"));
                alternativeUsernameField.clear();
                alternativeUsernameField.sendKeys(username);
            } catch (Exception ex) {
                System.out.println("Error entering username: " + ex.getMessage());
                throw ex;
            }
        }
    }

    public void enterPassword(String password) {
        try {
            wait.until(ExpectedConditions.visibilityOf(passwordField));
            passwordField.clear();
            passwordField.sendKeys(password);
        } catch (Exception e) {
            System.out.println("Warning: Password field not found with standard locator. Trying alternatives.");
            
            // Metode alternatif - cari password field berdasarkan berbagai atribut
            try {
                WebElement alternativePasswordField = driver.findElement(
                    By.xpath("//input[@name='password' or @id='password' or @type='password' or contains(@placeholder, 'Password') or contains(@placeholder, 'password')]"));
                alternativePasswordField.clear();
                alternativePasswordField.sendKeys(password);
            } catch (Exception ex) {
                System.out.println("Error entering password: " + ex.getMessage());
                throw ex;
            }
        }
    }    public void clickLoginButton() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(loginButton));
            loginButton.click();
        } catch (Exception e) {
            System.out.println("Warning: Login button not found with standard locator. Trying alternatives.");
            
            // Metode alternatif - cari tombol login berdasarkan berbagai atribut
            try {
                WebElement alternativeLoginButton = driver.findElement(
                    By.xpath("//button[@type='submit' or contains(text(), 'Login') or contains(text(), 'login') or contains(text(), 'Masuk') or contains(@class, 'login-button')]"));
                alternativeLoginButton.click();
            } catch (Exception ex) {
                System.out.println("Error clicking login button: " + ex.getMessage());
                throw ex;
            }
        }
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
