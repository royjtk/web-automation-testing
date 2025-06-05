package org.a6.stepdefs;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.a6.pages.actions.DashboardActions;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class DashboardSteps {
    private WebDriver driver;
    private DashboardActions dashboardActions;

    public DashboardSteps(SharedDrivers sharedDrivers) {
        this.driver = sharedDrivers.getDriver();
        this.dashboardActions = new DashboardActions(driver);
    }
    
    @When("I am on the dashboard page")
    public void iAmOnTheDashboardPage() {
        Assertions.assertTrue(dashboardActions.isDashboardHeaderDisplayed(), "Dashboard page is not displayed");
    }

    @Then("The dashboard header should be displayed")
    public void theDashboardHeaderShouldBeDisplayed() {
        Assertions.assertTrue(dashboardActions.isDashboardHeaderDisplayed(), "Dashboard header is not displayed");
    }

    @And("The username {string} should be visible in the header")
    public void usernameVisibleInHeader(String username) {
        Assertions.assertTrue(dashboardActions.isUserNameDisplayed(username), "Username not visible in header");
    }    @Then("The dashboard header should display username {string}")
    public void theDashboardHeaderShouldDisplayUsername(String username) {
        Assertions.assertTrue(dashboardActions.isUserNameDisplayed(username), 
            "Username '" + username + "' is not displayed in dashboard header");
    }
    
    @Then("The navigation menu should display the following options:")
    public void navigationMenuDisplaysOptions(DataTable dataTable) {
        List<String> menuItems = dataTable.asList(String.class);
        for (String menuItem : menuItems) {
            Assertions.assertTrue(dashboardActions.isNavigationMenuItemDisplayed(menuItem), 
                "Menu item '" + menuItem + "' is not displayed");
        }
    }    
    
    @Then("System is navigated user to the dashboard page and show page tittle {string}")
    public void systemNavigatesToDashboardPageWithTitle(String pageTitle) {
        // Verifikasi URL dashboard
        String currentUrl = driver.getCurrentUrl();
        Assertions.assertTrue(currentUrl.contains("/") || currentUrl.contains("/dasbor"), 
            "URL does not contain dashboard path");
        
        // Verifikasi judul halaman menggunakan teks dari header dashboard
        String headerText = dashboardActions.getDashboardHeaderText();
        System.out.println("Dashboard header text: " + headerText);
        
        Assertions.assertTrue(headerText.contains(pageTitle), 
            "Dashboard header text '" + headerText + "' does not contain expected title: '" + pageTitle + "'");
    }    
    @And("User should be able to see navigation bar for bendahara")
    public void userShouldSeeNavigationBarForBendahara(io.cucumber.datatable.DataTable dataTable) {
        List<String> expectedMenuItems = dataTable.asList(String.class);
        for (String menuItem : expectedMenuItems) {
            Assertions.assertTrue(dashboardActions.isNavigationMenuItemDisplayed(menuItem), 
                "Navigation menu item not found: " + menuItem);
        }
    }
}
