package org.a6.stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.a6.pages.actions.DashboardActions;
import org.a6.pages.actions.LoginActions;
import org.junit.jupiter.api.Assertions;

public class LogoutSteps {
    private SharedDrivers sharedDrivers;
    private DashboardActions dashboardActions;
    private LoginActions loginActions;

    public LogoutSteps(SharedDrivers sharedDrivers) {
        this.sharedDrivers = sharedDrivers;
        this.dashboardActions = new DashboardActions(sharedDrivers.getDriver());
        this.loginActions = new LoginActions(sharedDrivers.getDriver());
    }

    @Given("I am logged in with username {string} and password {string}")
    public void iAmLoggedInWithCredentials(String username, String password) {        
        loginActions.navigateToLoginPage();
        loginActions.enterUsername(username);
        loginActions.enterPassword(password);
        loginActions.clickLoginButton();
        Assertions.assertTrue(dashboardActions.isDashboardHeaderDisplayed(), "Login failed");
    }    @When("I click logout button in the header")
    public void iClickLogoutButtonInTheHeader() {
        dashboardActions.clickLogoutButton();
    }
    
    @And("I see a logout confirmation dialog")
    public void iSeeLogoutConfirmationDialog() {
        Assertions.assertTrue(dashboardActions.isLogoutConfirmationDisplayed(), "Logout confirmation dialog is not displayed");
    }
    
    @And("I confirm the logout")
    public void iConfirmTheLogout() {
        dashboardActions.confirmLogout();
    }
    
    @And("I cancel the logout")
    public void iCancelTheLogout() {
        dashboardActions.cancelLogout();
    }
    
    @Then("I should be logged out successfully")
    public void iShouldBeLoggedOutSuccessfully() {
        Assertions.assertTrue(loginActions.isLoginPageDisplayed(), "User is not logged out");
    }    @And("I should be redirected to the login page")
    public void iShouldBeRedirectedToTheLoginPage() {
        Assertions.assertTrue(loginActions.isLoginPageDisplayed(), "Not redirected to login page");
    }

    @And("I click browser back button")
    public void iClickBrowserBackButton() {
        sharedDrivers.getDriver().navigate().back();
    }    @Then("I should not be able to access protected pages")
    public void iShouldNotBeAbleToAccessProtectedPages() {
        Assertions.assertFalse(dashboardActions.isDashboardHeaderDisplayed(), "User can still access protected pages");
    }    @And("I try to access dashboard URL directly")
    public void iTryToAccessDashboardUrlDirectly() {
        dashboardActions.navigateToDashboardDirectly();
    }    @Then("I should not be able to access the dashboard")
    public void iShouldNotBeAbleToAccessTheDashboard() {
        Assertions.assertFalse(dashboardActions.isDashboardHeaderDisplayed(), "User can still access dashboard");
    }
    
    @Then("I should remain on the dashboard page")
    public void iShouldRemainOnDashboardPage() {
        Assertions.assertTrue(dashboardActions.isDashboardHeaderDisplayed(), "User is not on the dashboard page");
    }
}
