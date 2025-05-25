package org.a6.stepdefs;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.a6.pages.DashboardPage;
import org.a6.pages.LoginPage;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class LogoutSteps {
    private SharedDrivers sharedDrivers;
    private DashboardPage dashboardPage;
    private LoginPage loginPage;

    public LogoutSteps(SharedDrivers sharedDrivers) {
        this.sharedDrivers = sharedDrivers;
        this.dashboardPage = new DashboardPage(sharedDrivers.getDriver());
        this.loginPage = new LoginPage(sharedDrivers.getDriver());
    }

    @Given("I am logged in with valid credentials")
    public void iAmLoggedInWithValidCredentials(DataTable dataTable) {
        List<Map<String, String>> credentials = dataTable.asMaps(String.class, String.class);
        String username = credentials.get(0).get("username");
        String password = credentials.get(0).get("password");
        
        loginPage.navigateToLoginPage();
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
        
        Assert.assertTrue("Login failed, dashboard not displayed", 
            dashboardPage.isDashboardHeaderDisplayed());
    }

    @Given("I am on the dashboard page")
    public void iAmOnTheDashboardPage() {
        Assert.assertTrue("Dashboard page is not displayed", 
            dashboardPage.isDashboardHeaderDisplayed());
    }

    @When("I click on logout button in the header")
    public void iClickOnLogoutButtonInTheHeader() {
        dashboardPage.clickLogoutButton();
    }

    @Then("I should be successfully logged out")
    public void iShouldBeSuccessfullyLoggedOut() {
        Assert.assertTrue("Logout failed, user not redirected to login page", 
            loginPage.isLoginPageDisplayed());
    }

    @And("Login form should be displayed")
    public void loginFormShouldBeDisplayed() {
        Assert.assertTrue("Login form is not displayed after logout", 
            loginPage.isLoginPageDisplayed());
    }

    @And("I click on browser back button after logout")
    public void iClickOnBrowserBackButtonAfterLogout() {
        sharedDrivers.getDriver().navigate().back();
    }

    @Then("I should not be able to access protected pages")
    public void iShouldNotBeAbleToAccessProtectedPages() {
        Assert.assertFalse("User can still access protected pages after logout", 
            dashboardPage.isDashboardHeaderDisplayed());
    }

    @And("I try to access dashboard URL directly after logout")
    public void iTryToAccessDashboardUrlDirectlyAfterLogout() {
        dashboardPage.navigateToDashboardDirectly();
    }

    @Then("I should not be able to access the dashboard")
    public void iShouldNotBeAbleToAccessTheDashboard() {
        Assert.assertFalse("User can still access dashboard after logout", 
            dashboardPage.isDashboardHeaderDisplayed());
    }
}
