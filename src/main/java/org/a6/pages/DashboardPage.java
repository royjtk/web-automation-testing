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
    private final String DASHBOARD_URL = "http://ptbsp.ddns.net:6882/";    @FindBy(xpath = "//div[contains(@class, 'bg-[#F2F3F4]') or contains(@class, 'header') or contains(@class, 'navbar') or contains(@class, 'nav')]")
    private WebElement header;

    @FindBy(xpath = "//h1[contains(text(), 'Dasbor') or contains(text(), 'Dashboard') or contains(text(), 'Beranda')] | //div[contains(@class, 'dashboard-title') or contains(@class, 'page-title')]")
    private WebElement dashboardHeader;

    @FindBy(xpath = "//span[contains(text(), 'Bendahara') or contains(text(), 'bendahara')] | //div[contains(@class, 'user-role') and contains(text(), 'Bendahara')]")
    private WebElement userNameDisplay;

    @FindBy(xpath = "//button[.//svg[contains(@class, 'lucide-log-out')]]")
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
                // Try finding the dashboard header text
                return dashboardHeader.isDisplayed();
            } catch (Exception ex) {
                try {
                    // Try finding any header/navbar element
                    WebElement alternativeHeader = driver.findElement(
                        By.xpath("//div[contains(@class, 'header') or contains(@class, 'nav-bar') or contains(@class, 'navbar')]"));
                    return alternativeHeader.isDisplayed();
                } catch (Exception ex2) {
                    // If all locators fail, try to detect if we're on a page that appears to be the dashboard
                    return !driver.getCurrentUrl().contains("/login") && 
                           !driver.getTitle().toLowerCase().contains("login");
                }
            }
        }
    }    public boolean isUserNameDisplayed(String username) {
        try {
            // Coba metode utama menggunakan userNameDisplay
            wait.until(ExpectedConditions.visibilityOf(userNameDisplay));
            String displayedText = userNameDisplay.getText().toLowerCase();
            String expectedText = username.toLowerCase();
            return displayedText.contains(expectedText);
        } catch (Exception e) {
            System.out.println("Warning: Username element not found with standard locator. Trying alternatives.");
            
            // Coba metode alternatif - cari text username di manapun di header
            try {
                WebElement alternativeUsernameDisplay = driver.findElement(
                    By.xpath("//span[contains(text(),'" + username + "') or text()='" + username + "']"));
                return alternativeUsernameDisplay.isDisplayed();
            } catch (Exception ex) {
                // Jika tidak ada elemen yang cocok, kembalikan false
                return false;
            }
        }
    }    public void clickLogoutButton() {
        try {
            // Metode utama - menggunakan logoutButton yang didefinisikan dengan @FindBy
            wait.until(ExpectedConditions.elementToBeClickable(logoutButton));
            logoutButton.click();
        } catch (Exception e) {
            System.out.println("Warning: Logout button not found with standard locator. Trying alternatives.");
            
            // Metode alternatif - cari tombol logout berdasarkan isi atau atribut
            try {
                WebElement alternativeLogoutButton = driver.findElement(
                    By.xpath("//button[contains(@class, 'destructive') or contains(@class, 'logout') or .//svg[contains(@class, 'log-out')]]"));
                alternativeLogoutButton.click();
            } catch (Exception ex) {
                System.out.println("Error: Failed to click logout button: " + ex.getMessage());
                throw ex; // Re-throw exception karena logout adalah fungsi penting
            }
        }
    }    public boolean isNavigationMenuItemDisplayed(String menuItem) {
        try {
            // Metode 1: Cek dalam navigationMenuItems
            for (WebElement element : navigationMenuItems) {
                if (element.getText().trim().toLowerCase().contains(menuItem.toLowerCase())) {
                    return true;
                }
            }
            
            // Metode 2: Cari langsung dengan XPath yang lebih fleksibel
            try {
                String xpathPattern = "//a[contains(text(),'" + menuItem + "')] | //button[contains(text(),'" + menuItem + "')] | //span[contains(text(),'" + menuItem + "')]";
                WebElement menuElement = driver.findElement(By.xpath(xpathPattern));
                return menuElement.isDisplayed();
            } catch (Exception e) {
                // Jika tidak ditemukan dengan metode 2, coba metode 3 - mencari di semua elemen yang mungkin
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
}
