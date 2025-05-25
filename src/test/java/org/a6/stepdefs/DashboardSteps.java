package org.a6.stepdefs;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.a6.pages.DashboardPage;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Map;

public class DashboardSteps {
    private WebDriver driver;
    private DashboardPage dashboardPage;

    public DashboardSteps(SharedDrivers sharedDrivers) {
        this.driver = sharedDrivers.getDriver();
        this.dashboardPage = new DashboardPage(driver);
    }

    @When("I am on the dashboard page")
    public void iAmOnTheDashboardPage() {
        Assert.assertTrue("Dashboard page is not displayed", dashboardPage.isDashboardHeaderDisplayed());
    }

    @Then("The dashboard header should be displayed")
    public void theDashboardHeaderShouldBeDisplayed() {
        Assert.assertTrue("Dashboard header is not displayed", dashboardPage.isDashboardHeaderDisplayed());
    }

    @And("The username {string} should be visible in the header")
    public void usernameVisibleInHeader(String username) {
        Assert.assertTrue("Username not visible in header", dashboardPage.isUserNameDisplayed(username));
    }
    
    @Then("The navigation menu should display the following options:")
    public void navigationMenuDisplaysOptions(DataTable dataTable) {
        List<String> menuItems = dataTable.asList(String.class);
        for (String menuItem : menuItems) {
            Assert.assertTrue("Menu item '" + menuItem + "' is not displayed", 
                dashboardPage.isNavigationMenuItemDisplayed(menuItem));
        }
    }
}
