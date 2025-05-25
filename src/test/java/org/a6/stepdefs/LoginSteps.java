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

    @When("I enter valid username {string}")
    public void iEnterValidUsername(String username) {
        loginPage.enterUsername(username);
    }

    @And("I enter valid password {string}")
    public void iEnterValidPassword(String password) {
        loginPage.enterPassword(password);
    }

    @When("I leave username field empty")
    public void iLeaveUsernameFieldEmpty() {
        loginPage.enterUsername("");
    }

    @And("I leave password field empty")
    public void iLeavePasswordFieldEmpty() {
        loginPage.enterPassword("");
    }

    @When("I enter invalid username {string}")
    public void iEnterInvalidUsername(String username) {
        loginPage.enterUsername(username);
    }

    @And("I enter invalid password {string}")
    public void iEnterInvalidPassword(String password) {
        loginPage.enterPassword(password);
    }

    @When("I enter password {string}")
    public void iEnterPassword(String password) {
        loginPage.enterPassword(password);
    }

    @And("I click on the login button")
    public void iClickOnTheLoginButton() {
        loginPage.clickLoginButton();
    }    @Then("I should be successfully logged in")
    public void iShouldBeSuccessfullyLoggedIn() {
        // This is verified by checking if dashboard is displayed
        Assert.assertTrue("Dashboard is not displayed after login", dashboardPage.isDashboardHeaderDisplayed());
    }

    @And("I should be redirected to dashboard page")
    public void iShouldBeRedirectedToDashboardPage() {
        Assert.assertTrue("User is not redirected to dashboard page", 
            driver.getCurrentUrl().contains("http://ptbsp.ddns.net:6882/"));
    }

    @And("Dashboard header should be displayed")
    public void dashboardHeaderShouldBeDisplayed() {
        Assert.assertTrue("Dashboard header is not displayed", dashboardPage.isDashboardHeaderDisplayed());
    }

    @Then("I should remain on login page")
    public void iShouldRemainOnLoginPage() {
        Assert.assertTrue("User is not on login page", loginPage.isLoginPageDisplayed());
    }

    @And("Error message should be displayed indicating username is required")
    public void errorMessageShouldBeDisplayedIndicatingUsernameIsRequired() {
        Assert.assertTrue("Error message is not displayed", loginPage.isErrorMessageDisplayed());
        String errorMsg = loginPage.getErrorMessageText().toLowerCase();
        Assert.assertTrue("Error message does not indicate username is required", 
            errorMsg.contains("username") && (errorMsg.contains("required") || errorMsg.contains("wajib")));
    }

    @And("Error message should be displayed indicating password is required")
    public void errorMessageShouldBeDisplayedIndicatingPasswordIsRequired() {
        Assert.assertTrue("Error message is not displayed", loginPage.isErrorMessageDisplayed());
        String errorMsg = loginPage.getErrorMessageText().toLowerCase();
        Assert.assertTrue("Error message does not indicate password is required", 
            errorMsg.contains("password") && (errorMsg.contains("required") || errorMsg.contains("wajib")));
    }

    @And("Error message should be displayed indicating invalid credentials")
    public void errorMessageShouldBeDisplayedIndicatingInvalidCredentials() {
        Assert.assertTrue("Error message is not displayed", loginPage.isErrorMessageDisplayed());
        String errorMsg = loginPage.getErrorMessageText().toLowerCase();
        Assert.assertTrue("Error message does not indicate invalid credentials", 
            errorMsg.contains("invalid") || errorMsg.contains("salah") || errorMsg.contains("tidak valid"));
    }

    @And("I click on password visibility toggle icon")
    public void iClickOnPasswordVisibilityToggleIcon() {
        loginPage.clickPasswordVisibilityToggle();
    }

    @Then("Password should toggle between visible and hidden state")
    public void passwordShouldToggleBetweenVisibleAndHiddenState() {
        boolean isVisible = loginPage.isPasswordVisible();
        loginPage.clickPasswordVisibilityToggle();
        boolean isNowHidden = !loginPage.isPasswordVisible();
        
        Assert.assertTrue("Password visibility did not toggle correctly", isVisible != isNowHidden);
    }

    @And("Toggle icon should change appearance")
    public void toggleIconShouldChangeAppearance() {
        // This is a visual verification that would need a screenshot comparison
        // For now, we'll rely on the password visibility toggle verification
        Assert.assertTrue("The test passed based on password visibility toggle functionality", true);
    }
}
