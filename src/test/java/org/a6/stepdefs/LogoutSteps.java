package org.a6.stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.a6.pages.DashboardPage;
import org.a6.pages.LoginPage;
import org.junit.jupiter.api.Assertions;

public class LogoutSteps {
    private SharedDrivers sharedDrivers;
    private DashboardPage dashboardPage;
    private LoginPage loginPage;

    public LogoutSteps(SharedDrivers sharedDrivers) {
        this.sharedDrivers = sharedDrivers;
        this.dashboardPage = new DashboardPage(sharedDrivers.getDriver());
        this.loginPage = new LoginPage(sharedDrivers.getDriver());
    }

    @Given("I am logged in with username {string} and password {string}")
    public void iAmLoggedInWithCredentials(String username, String password) {        
        loginPage.navigateToLoginPage();
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
        Assertions.assertTrue(dashboardPage.isDashboardHeaderDisplayed(), "Login failed");
    }

    @When("I click logout button in the header")
    public void iClickLogoutButtonInTheHeader() {
        dashboardPage.clickLogoutButton();
    }    @Then("I should be logged out successfully")
    public void iShouldBeLoggedOutSuccessfully() {
        Assertions.assertTrue(loginPage.isLoginPageDisplayed(), "User is not logged out");
    }

    @And("I should be redirected to the login page")
    public void iShouldBeRedirectedToTheLoginPage() {
        Assertions.assertTrue(loginPage.isLoginPageDisplayed(), "Not redirected to login page");
    }

    @And("I click browser back button")
    public void iClickBrowserBackButton() {
        sharedDrivers.getDriver().navigate().back();
    }    @Then("I should not be able to access protected pages")
    public void iShouldNotBeAbleToAccessProtectedPages() {
        Assertions.assertFalse(dashboardPage.isDashboardHeaderDisplayed(), "User can still access protected pages");
    }

    @And("I try to access dashboard URL directly")
    public void iTryToAccessDashboardUrlDirectly() {
        dashboardPage.navigateToDashboardDirectly();
    }

    @Then("I should not be able to access the dashboard")
    public void iShouldNotBeAbleToAccessTheDashboard() {
        Assertions.assertFalse(dashboardPage.isDashboardHeaderDisplayed(), "User can still access dashboard");
    }
}
