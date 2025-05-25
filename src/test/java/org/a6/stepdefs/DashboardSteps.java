package org.a6.stepdefs;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.a6.pages.DashboardPage;
import org.a6.pages.LoginPage;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Map;

public class DashboardSteps {
    private WebDriver driver;
    private DashboardPage dashboardPage;
    private LoginPage loginPage;

    // We'll use dependency injection from Cucumber's PicoContainer
    public DashboardSteps(SharedDrivers sharedDrivers) {
        this.driver = sharedDrivers.getDriver();
        this.dashboardPage = new DashboardPage(driver);
        this.loginPage = new LoginPage(driver);
    }

    @Then("Username {string} should be visible in the header")
    public void usernameShouldBeVisibleInTheHeader(String username) {
        Assert.assertTrue("Username is not visible in header", dashboardPage.isUserNameDisplayed(username));
    }

    @Then("Navigation menu should display the following options")
    public void navigationMenuShouldDisplayTheFollowingOptions(DataTable dataTable) {
        List<Map<String, String>> menuItems = dataTable.asMaps(String.class, String.class);
        
        for (Map<String, String> menuItem : menuItems) {
            String item = menuItem.get("menuItem");
            Assert.assertTrue("Menu item '" + item + "' is not displayed", 
                dashboardPage.isNavigationMenuItemDisplayed(item));
        }
    }
}
