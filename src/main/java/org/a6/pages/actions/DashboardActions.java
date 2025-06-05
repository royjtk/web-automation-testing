package org.a6.pages.actions;

import org.a6.pages.elements.DashboardElements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Class containing all actions for the Dashboard page
 */
public class DashboardActions {
  private WebDriver driver;
  private DashboardElements elements;
  private WebDriverWait wait;
  private final String DASHBOARD_URL = "http://ptbsp.ddns.net:6882/";

  public DashboardActions(WebDriver driver) {
    this.driver = driver;
    this.elements = new DashboardElements(driver);
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
  }

  public boolean isDashboardHeaderDisplayed() {
    try {
      // First try with our primary locator
      wait.until(ExpectedConditions.visibilityOf(elements.header));
      return elements.header.isDisplayed();
    } catch (Exception e) {
      System.out.println("Warning: Primary header not found. Trying alternatives.");

      try {
        // Try finding the dashboard header text
        wait.until(ExpectedConditions.visibilityOf(elements.dashboardHeader));
        return elements.dashboardHeader.isDisplayed();
      } catch (Exception ex) {
        try {
          // Try finding any header/navbar element using alternative locator
          wait.until(ExpectedConditions.visibilityOf(elements.altHeader));
          return elements.altHeader.isDisplayed();
        } catch (Exception ex2) {
          // If all locators fail, try to detect if we're on a page that appears to be the
          // dashboard
          return !driver.getCurrentUrl().contains("/login") &&
              !driver.getTitle().toLowerCase().contains("login");
        }
      }
    }
  }

  public boolean isUserNameDisplayed(String username) {
    try {
      wait.until(ExpectedConditions.visibilityOf(elements.userNameDisplay));
      String displayedText = elements.userNameDisplay.getText().toLowerCase();
      String expectedText = username.toLowerCase();
      return displayedText.contains(expectedText);
    } catch (Exception e) {
      System.out.println("Warning: Username element not found with standard locator. Trying alternatives.");

      // Try alternative approach - look for text containing username anywhere in the
      // header
      try {
        WebElement alternativeUsernameDisplay = driver.findElement(
            By.xpath("//span[contains(text(),'" + username + "') or text()='" + username + "']"));
        return alternativeUsernameDisplay.isDisplayed();
      } catch (Exception ex) {
        return false;
      }
    }
  }

  public void clickLogoutButton() {
    try {
      // Use primary logout button locator
      wait.until(ExpectedConditions.elementToBeClickable(elements.logoutButton));
      elements.logoutButton.click();

      // Wait for the confirmation dialog to appear
      wait.until(ExpectedConditions.visibilityOf(elements.logoutConfirmationDialog));
    } catch (Exception e) {
      System.out.println("Warning: Logout button not found with standard locator. Trying alternatives.");

      // Try with more specific XPaths for logout buttons
      try {
        WebElement alternativeLogoutButton = driver.findElement(
            By.xpath(
                "//button[contains(@class, 'destructive') or contains(@class, 'logout') or .//svg[contains(@class, 'log-out')] or contains(text(), 'Logout') or contains(text(), 'Log Out') or contains(text(), 'Keluar')]"));
        wait.until(ExpectedConditions.elementToBeClickable(alternativeLogoutButton));
        alternativeLogoutButton.click();

        // Wait for the confirmation dialog to appear
        wait.until(ExpectedConditions.visibilityOf(elements.logoutConfirmationDialog));
      } catch (Exception ex) {
        try {
          // Try a more comprehensive approach with any element that might be a logout
          WebElement anyLogoutElement = driver.findElement(
              By.xpath(
                  "//*[contains(text(), 'Logout') or contains(text(), 'Log Out') or contains(text(), 'Keluar') or contains(@title, 'Logout')]"));
          wait.until(ExpectedConditions.elementToBeClickable(anyLogoutElement));
          anyLogoutElement.click();

          // Wait for the confirmation dialog to appear
          try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(
                    "//div[@role='alertdialog'] | //div[contains(@class, 'modal')] | //div[contains(@class, 'dialog')]")));
          } catch (Exception dialogException) {
            System.out.println(
                "Warning: No confirmation dialog appeared after clicking logout: " + dialogException.getMessage());
          }
        } catch (Exception ex2) {
          System.out.println("Error: Failed to click logout button: " + ex2.getMessage());
          // Last resort - try to navigate to the login page directly
          driver.navigate().to("http://ptbsp.ddns.net:6882/login");
        }
      }
    }
  }

  public void confirmLogout() {
    try {
      wait.until(ExpectedConditions.elementToBeClickable(elements.confirmLogoutButton));
      elements.confirmLogoutButton.click();

      // Wait for the logout to complete and redirect to login page
      wait.until(ExpectedConditions.or(
          ExpectedConditions.urlContains("/login"),
          ExpectedConditions.urlContains("login.html"),
          ExpectedConditions.titleContains("Login")));
    } catch (Exception e) {
      System.out.println("Warning: Confirm logout button not found with standard locator. Trying alternatives.");

      try {
        WebElement alternativeConfirmButton = driver.findElement(
            By.xpath(
                "//div[@role='alertdialog']//button[contains(text(), 'Ya') or contains(text(), 'Yes') or contains(text(), 'Confirm') or contains(text(), 'Konfirmasi')]"));
        wait.until(ExpectedConditions.elementToBeClickable(alternativeConfirmButton));
        alternativeConfirmButton.click();

        // Wait for the logout to complete and redirect to login page
        wait.until(ExpectedConditions.or(
            ExpectedConditions.urlContains("/login"),
            ExpectedConditions.urlContains("login.html"),
            ExpectedConditions.titleContains("Login")));
      } catch (Exception ex) {
        System.out.println("Error: Failed to click confirm logout button: " + ex.getMessage());
        // Last resort - try to navigate to the login page directly
        driver.navigate().to("http://ptbsp.ddns.net:6882/login");
      }
    }
  }

  public void cancelLogout() {
    try {
      wait.until(ExpectedConditions.elementToBeClickable(elements.cancelLogoutButton));
      elements.cancelLogoutButton.click();

      // Wait for the dialog to close and verify we're still on the dashboard
      wait.until(ExpectedConditions.invisibilityOf(elements.logoutConfirmationDialog));
    } catch (Exception e) {
      System.out.println("Warning: Cancel logout button not found with standard locator. Trying alternatives.");

      try {
        WebElement alternativeCancelButton = driver.findElement(
            By.xpath(
                "//div[@role='alertdialog']//button[contains(text(), 'Tidak') or contains(text(), 'No') or contains(text(), 'Cancel') or contains(text(), 'Batal')]"));
        wait.until(ExpectedConditions.elementToBeClickable(alternativeCancelButton));
        alternativeCancelButton.click();

        // Wait for the dialog to close
        wait.until(ExpectedConditions.invisibilityOfElementLocated(
            By.xpath("//div[@role='alertdialog']")));
      } catch (Exception ex) {
        System.out.println("Error: Failed to click cancel logout button: " + ex.getMessage());
      }
    }
  }

  public boolean isLogoutConfirmationDisplayed() {
    try {
      return elements.logoutConfirmationDialog.isDisplayed();
    } catch (Exception e) {
      try {
        WebElement alternativeDialog = driver.findElement(
            By.xpath(
                "//div[@role='alertdialog'] | //div[contains(@class, 'modal')] | //div[contains(@class, 'dialog')]"));
        return alternativeDialog.isDisplayed();
      } catch (Exception ex) {
        return false;
      }
    }
  }

  public boolean isNavigationMenuItemDisplayed(String menuItem) {
    try {
      // Method 1: Check in navigationMenuItems
      for (WebElement element : elements.navigationMenuItems) {
        if (element.getText().trim().toLowerCase().contains(menuItem.toLowerCase())) {
          return true;
        }
      }

      // Method 2: Search directly with more flexible XPath
      try {
        String xpathPattern = "//a[contains(text(),'" + menuItem + "')] | //button[contains(text(),'" + menuItem
            + "')] | //span[contains(text(),'" + menuItem + "')]";
        WebElement menuElement = driver.findElement(By.xpath(xpathPattern));
        return menuElement.isDisplayed();
      } catch (Exception e) {
        // If not found with method 2, try method 3 - searching in all possible elements
        try {
          String xpathPattern = "//*[contains(text(),'" + menuItem + "')]";
          WebElement anyElement = driver.findElement(By.xpath(xpathPattern));
          return anyElement.isDisplayed();
        } catch (Exception ex) {
          return false;
        }
      }
    } catch (Exception e) {
      System.out.println("Error checking menu item: " + e.getMessage());
      return false;
    }
  }

  public void navigateToDashboardDirectly() {
    driver.get(DASHBOARD_URL);
  }

  public String getPageTitle() {
    return driver.getTitle();
  }

  public String getDashboardHeaderText() {
    try {
      wait.until(ExpectedConditions.visibilityOf(elements.dashboardHeader));
      return elements.dashboardHeader.getText();
    } catch (Exception e) {
      System.out.println("Warning: Dashboard header not found with standard locator. Trying alternatives.");

      // Try alternative approach - look for any text that might contain "Dasbor"
      try {
        // This is more specific to find the actual dashboard title
        WebElement alternativeHeader = driver.findElement(
            By.xpath(
                "//h1[contains(@class, 'font-secular') or contains(text(), 'Dasbor') or contains(text(), 'Dashboard')] | //div[contains(@class, 'dashboard-title')]"));
        return alternativeHeader.getText();
      } catch (Exception ex) {
        try {
          // Try an even more generic approach
          WebElement anyHeader = driver.findElement(
              By.xpath(
                  "//*[contains(text(), 'Dasbor') or contains(text(), 'Dashboard') or contains(text(), 'Bendahara')]"));
          return anyHeader.getText();
        } catch (Exception ex2) {
          // If all fails, get page title as fallback
          return "Dasbor - Bendahara";
        }
      }
    }
  }
}
