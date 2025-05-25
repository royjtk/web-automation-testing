package org.a6.stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.a6.pages.DashboardPage;
import org.a6.pages.LoginPage;
import org.junit.Assert;

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
        Assert.assertTrue("Login failed", dashboardPage.isDashboardHeaderDisplayed());
    }

    @When("I click logout button in the header")
    public void iClickLogoutButtonInTheHeader() {
        dashboardPage.clickLogoutButton();
    }

    @Then("I should be logged out successfully")
    public void iShouldBeLoggedOutSuccessfully() {
        Assert.assertTrue("User is not logged out", loginPage.isLoginPageDisplayed());
    }

    @And("I should be redirected to the login page")
    public void iShouldBeRedirectedToTheLoginPage() {
        Assert.assertTrue("Not redirected to login page", loginPage.isLoginPageDisplayed());
    }

    @And("I click browser back button")
    public void iClickBrowserBackButton() {
        sharedDrivers.getDriver().navigate().back();
    }

    @Then("I should not be able to access protected pages")
    public void iShouldNotBeAbleToAccessProtectedPages() {
        Assert.assertFalse("User can still access protected pages", dashboardPage.isDashboardHeaderDisplayed());
    }

    @And("I try to access dashboard URL directly")
    public void iTryToAccessDashboardUrlDirectly() {
        dashboardPage.navigateToDashboardDirectly();
    }

    @Then("I should not be able to access the dashboard")
    public void iShouldNotBeAbleToAccessTheDashboard() {
        Assert.assertFalse("User can still access dashboard", dashboardPage.isDashboardHeaderDisplayed());
    }
}
