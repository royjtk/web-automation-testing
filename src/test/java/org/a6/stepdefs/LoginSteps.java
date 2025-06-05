package org.a6.stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.a6.pages.actions.DashboardActions;
import org.a6.pages.actions.LoginActions;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

public class LoginSteps {
    private final WebDriver driver;
    private LoginActions loginActions;
    private DashboardActions dashboardActions;
    
    public LoginSteps(SharedDrivers sharedDrivers) {
        this.driver = sharedDrivers.getDriver();
        this.loginActions = new LoginActions(driver);
        this.dashboardActions = new DashboardActions(driver);
    }
    
    @Given("User has opened the browser")
    public void userHasOpenedTheBrowser() {
        // Browser sudah dibuka oleh SharedDrivers class melalui setup method
    }
    
    @Given("User has navigated on the login page Education Fund Payment Management System for Zaidan Educare School app")
    public void userHasNavigatedToLoginPage() {
        loginActions.navigateToLoginPage();
    }
    
    @Given("I am on the login page")
    public void iAmOnTheLoginPage() {
        // For backward compatibility with other tests
        loginActions.navigateToLoginPage();
    }

    @When("User enters username {string} and password {string}")
    public void iEnterUsernameAndPassword(String username, String password) {
        loginActions.enterUsername(username);
        loginActions.enterPassword(password);
    }
    
    @And("I enter username {string}")
    public void iEnterUsername(String username) {
        loginActions.enterUsername(username);
    }

    @And("I enter password {string}")
    public void iEnterPassword(String password) {
        loginActions.enterPassword(password);
    }

    @And("User clicks login button")
    public void userClicksLoginButton() {
        loginActions.clickLoginButton();
    }
      
    @And("User is navigated to the dashboard page")
    public void userIsNavigatedToDashboardPage() {
        String currentUrl = driver.getCurrentUrl();
        Assertions.assertTrue(currentUrl.contains("/dashboard") || currentUrl.contains("/dasbor"), 
            "URL does not contain dashboard path");
    }
    
    @When("I leave username field empty")
    public void iLeaveUsernameFieldEmpty() {
        loginActions.enterUsername("");
    }

    @And("I leave password field empty")
    public void iLeavePasswordFieldEmpty() {
        loginActions.enterPassword("");
    }
    
    @Then("I should be logged in successfully")
    public void iShouldBeLoggedInSuccessfully() {
        Assertions.assertTrue(dashboardActions.isDashboardHeaderDisplayed(), "Dashboard is not displayed after login");
    }

    @Then("I should remain on the login page")
    public void iShouldRemainOnTheLoginPage() {
        Assertions.assertTrue(loginActions.isLoginPageDisplayed(), "User is not on login page");
    }    @And("I should see error message {string} below username field")
    public void iShouldSeeErrorMessageBelowUsernameField(String errorMessage) {
        Assertions.assertTrue(loginActions.isErrorMessageDisplayed(), "Error message is not displayed");
        Assertions.assertEquals(errorMessage, loginActions.getErrorMessageText(), "Incorrect error message");
    }

    @And("I should see error message {string} below password field")
    public void iShouldSeeErrorMessageBelowPasswordField(String errorMessage) {
        Assertions.assertTrue(loginActions.isErrorMessageDisplayed(), "Error message is not displayed");
        Assertions.assertEquals(errorMessage, loginActions.getErrorMessageText(), "Incorrect error message");
    }

    @And("I should see error message {string}")
    public void iShouldSeeErrorMessage(String errorMessage) {
        Assertions.assertTrue(loginActions.isErrorMessageDisplayed(), "Error message is not displayed");
        Assertions.assertEquals(errorMessage, loginActions.getErrorMessageText(), "Incorrect error message");
    }
      @And("I click password visibility toggle")
    public void iClickPasswordVisibilityToggle() {
        loginActions.clickPasswordVisibilityToggle();
    }

    @Then("Password field should be visible")
    public void passwordFieldShouldBeVisible() {
        Assertions.assertTrue(loginActions.isPasswordVisible(), "Password is not visible");
    }

    @When("I click password visibility toggle again")
    public void iClickPasswordVisibilityToggleAgain() {
        loginActions.clickPasswordVisibilityToggle();
    }

    @Then("Password field should be hidden")
    public void passwordFieldShouldBeHidden() {
        Assertions.assertFalse(loginActions.isPasswordVisible(), "Password is still visible");
    }

    @And("Login form should be displayed")
    public void loginFormShouldBeDisplayed() {
        Assertions.assertTrue(loginActions.isLoginPageDisplayed(), "Login form is not displayed");
    }
}
