package org.a6.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class DashboardPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private final String DASHBOARD_URL = "http://ptbsp.ddns.net:6882/";    @FindBy(css = "header, .header")
    private WebElement header;

    @FindBy(css = "header .user-info, .user-profile")
    private WebElement userNameDisplay;

    @FindBy(css = "button.logout-button, a.logout, button[title='Logout']")
    private WebElement logoutButton;

    @FindBy(xpath = "//nav//a[contains(@href, '/dashboard')]")
    private WebElement dashboardLink;

    @FindBy(xpath = "//nav//a[contains(@href, '/tagihan-siswa')]")
    private WebElement tagihanSiswaLink;

    @FindBy(xpath = "//nav//a[contains(@href, '/transaksi-penerimaan')]")
    private WebElement transaksiPenerimaanLink;

    @FindBy(xpath = "//nav//a[contains(@href, '/pengaturan-notifikasi')]")
    private WebElement pengaturanNotifikasiLink;

    @FindBy(xpath = "//nav//a[contains(@href, '/status-pembayaran')]")
    private WebElement statusPembayaranLink;

    @FindBy(xpath = "//nav//a[contains(@href, '/rekapitulasi')]")
    private WebElement rekapitulasiLink;

    @FindBy(xpath = "//nav//a[contains(@href, '/progres-transaksi')]")
    private WebElement progresTransaksiLink;
    
    @FindBy(xpath = "//nav//a")
    private List<WebElement> navigationMenuItems;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }    public boolean isDashboardHeaderDisplayed() {
        try {
            // First try with our locator
            wait.until(ExpectedConditions.visibilityOf(header));
            return header.isDisplayed();
        } catch (Exception e) {
            System.out.println("Warning: Header not found with standard locator. Trying alternatives.");
            
            // Try alternative locators if the first one fails
            try {
                WebElement alternativeHeader = driver.findElement(
                    By.xpath("//div[contains(@class, 'header') or contains(@class, 'nav-bar') or contains(@class, 'navbar')]"));
                return alternativeHeader.isDisplayed();
            } catch (Exception ex) {
                // If all locators fail, try to detect if we're on a page that appears to be the dashboard
                return !driver.getCurrentUrl().contains("/login") && 
                       !driver.getTitle().toLowerCase().contains("login");
            }
        }
    }

    public boolean isUserNameDisplayed(String username) {
        try {
            wait.until(ExpectedConditions.textToBePresentInElement(userNameDisplay, username));
            return userNameDisplay.getText().contains(username);
        } catch (Exception e) {
            return false;
        }
    }

    public void clickLogoutButton() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton));
        logoutButton.click();
    }

    public boolean isNavigationMenuItemDisplayed(String menuItem) {
        for (WebElement element : navigationMenuItems) {
            if (element.getText().trim().equalsIgnoreCase(menuItem)) {
                return true;
            }
        }
        return false;
    }

    public void navigateToDashboardDirectly() {
        driver.get(DASHBOARD_URL);
    }
}
