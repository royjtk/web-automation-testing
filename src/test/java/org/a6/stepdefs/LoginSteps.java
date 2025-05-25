package org.a6.stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.a6.pages.DashboardPage;
import org.a6.pages.LoginPage;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class LoginSteps {
    private final WebDriver driver;
    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    
    public LoginSteps(SharedDrivers sharedDrivers) {
        this.driver = sharedDrivers.getDriver();
        this.loginPage = new LoginPage(driver);
        this.dashboardPage = new DashboardPage(driver);
    }

    @Given("I am on the login page")
    public void iAmOnTheLoginPage() {
        loginPage.navigateToLoginPage();
    }

    @When("I enter username {string}")
    public void iEnterUsername(String username) {
        loginPage.enterUsername(username);
    }

    @And("I enter password {string}")
    public void iEnterPassword(String password) {
        loginPage.enterPassword(password);
    }

    @And("I click login button")
    public void iClickLoginButton() {
        loginPage.clickLoginButton();
    }

    @When("I leave username field empty")
    public void iLeaveUsernameFieldEmpty() {
        loginPage.enterUsername("");
    }

    @And("I leave password field empty")
    public void iLeavePasswordFieldEmpty() {
        loginPage.enterPassword("");
    }

    @Then("I should be logged in successfully")
    public void iShouldBeLoggedInSuccessfully() {
        Assert.assertTrue("Dashboard is not displayed after login", dashboardPage.isDashboardHeaderDisplayed());
    }

    @And("I should be redirected to the dashboard page")
    public void iShouldBeRedirectedToDashboardPage() {
        Assert.assertTrue("User is not redirected to dashboard page", 
            !driver.getCurrentUrl().contains("/login"));
    }

    @Then("I should remain on the login page")
    public void iShouldRemainOnTheLoginPage() {
        Assert.assertTrue("User is not on login page", loginPage.isLoginPageDisplayed());
    }

    @And("I should see error message {string} below username field")
    public void iShouldSeeErrorMessageBelowUsernameField(String errorMessage) {
        Assert.assertTrue("Error message is not displayed", loginPage.isErrorMessageDisplayed());
        Assert.assertEquals("Incorrect error message", errorMessage, loginPage.getErrorMessageText());
    }

    @And("I should see error message {string} below password field")
    public void iShouldSeeErrorMessageBelowPasswordField(String errorMessage) {
        Assert.assertTrue("Error message is not displayed", loginPage.isErrorMessageDisplayed());
        Assert.assertEquals("Incorrect error message", errorMessage, loginPage.getErrorMessageText());
    }

    @And("I should see error message {string}")
    public void iShouldSeeErrorMessage(String errorMessage) {
        Assert.assertTrue("Error message is not displayed", loginPage.isErrorMessageDisplayed());
        Assert.assertEquals("Incorrect error message", errorMessage, loginPage.getErrorMessageText());
    }

    @And("I click password visibility toggle")
    public void iClickPasswordVisibilityToggle() {
        loginPage.clickPasswordVisibilityToggle();
    }

    @Then("Password field should be visible")
    public void passwordFieldShouldBeVisible() {
        Assert.assertTrue("Password is not visible", loginPage.isPasswordVisible());
    }

    @When("I click password visibility toggle again")
    public void iClickPasswordVisibilityToggleAgain() {
        loginPage.clickPasswordVisibilityToggle();
    }

    @Then("Password field should be hidden")
    public void passwordFieldShouldBeHidden() {
        Assert.assertFalse("Password is still visible", loginPage.isPasswordVisible());
    }

    @And("Login form should be displayed")
    public void loginFormShouldBeDisplayed() {
        Assert.assertTrue("Login form is not displayed", loginPage.isLoginPageDisplayed());
    }
}
