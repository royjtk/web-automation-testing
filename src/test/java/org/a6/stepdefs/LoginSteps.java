package org.a6.stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.a6.pages.DashboardPage;
import org.a6.pages.LoginPage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

public class LoginSteps {
    private final WebDriver driver;
    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    
    public LoginSteps(SharedDrivers sharedDrivers) {
        this.driver = sharedDrivers.getDriver();
        this.loginPage = new LoginPage(driver);
        this.dashboardPage = new DashboardPage(driver);
    }    @Given("User has opened the browser")
    public void userHasOpenedTheBrowser() {
        // Browser sudah dibuka oleh SharedDrivers class melalui setup method
    }
    
    @Given("User has navigated on the login page Education Fund Payment Management System for Zaidan Educare School app")
    public void userHasNavigatedToLoginPage() {
        loginPage.navigateToLoginPage();
    }
    
    @Given("I am on the login page")
    public void iAmOnTheLoginPage() {
        // For backward compatibility with other tests
        loginPage.navigateToLoginPage();
    }

    @When("User enters username {string} and password {string}")
    public void iEnterUsernameAndPassword(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }
    
    @And("I enter username {string}")
    public void iEnterUsername(String username) {
        loginPage.enterUsername(username);
    }

    @And("I enter password {string}")
    public void iEnterPassword(String password) {
        loginPage.enterPassword(password);
    }

    @And("User clicks login button")
    public void userClicksLoginButton() {
        loginPage.clickLoginButton();
    }
    
    @And("User is navigated to the dashboard page")
    public void userIsNavigatedToDashboardPage() {
        String currentUrl = driver.getCurrentUrl();
        Assertions.assertTrue(currentUrl.contains("/dashboard") || currentUrl.contains("/dasbor"), 
            "URL does not contain dashboard path");
    }
    
    @When("I leave username field empty")
    public void iLeaveUsernameFieldEmpty() {
        loginPage.enterUsername("");
    }

    @And("I leave password field empty")
    public void iLeavePasswordFieldEmpty() {
        loginPage.enterPassword("");
    }    @Then("I should be logged in successfully")
    public void iShouldBeLoggedInSuccessfully() {
        Assertions.assertTrue(dashboardPage.isDashboardHeaderDisplayed(), "Dashboard is not displayed after login");
    }

    @Then("I should remain on the login page")
    public void iShouldRemainOnTheLoginPage() {
        Assertions.assertTrue(loginPage.isLoginPageDisplayed(), "User is not on login page");
    }    @And("I should see error message {string} below username field")
    public void iShouldSeeErrorMessageBelowUsernameField(String errorMessage) {
        Assertions.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message is not displayed");
        Assertions.assertEquals(errorMessage, loginPage.getErrorMessageText(), "Incorrect error message");
    }

    @And("I should see error message {string} below password field")
    public void iShouldSeeErrorMessageBelowPasswordField(String errorMessage) {
        Assertions.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message is not displayed");
        Assertions.assertEquals(errorMessage, loginPage.getErrorMessageText(), "Incorrect error message");
    }

    @And("I should see error message {string}")
    public void iShouldSeeErrorMessage(String errorMessage) {
        Assertions.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message is not displayed");
        Assertions.assertEquals(errorMessage, loginPage.getErrorMessageText(), "Incorrect error message");
    }    @And("I click password visibility toggle")
    public void iClickPasswordVisibilityToggle() {
        loginPage.clickPasswordVisibilityToggle();
    }

    @Then("Password field should be visible")
    public void passwordFieldShouldBeVisible() {
        Assertions.assertTrue(loginPage.isPasswordVisible(), "Password is not visible");
    }

    @When("I click password visibility toggle again")
    public void iClickPasswordVisibilityToggleAgain() {
        loginPage.clickPasswordVisibilityToggle();
    }

    @Then("Password field should be hidden")
    public void passwordFieldShouldBeHidden() {
        Assertions.assertFalse(loginPage.isPasswordVisible(), "Password is still visible");
    }

    @And("Login form should be displayed")
    public void loginFormShouldBeDisplayed() {
        Assertions.assertTrue(loginPage.isLoginPageDisplayed(), "Login form is not displayed");
    }
}
