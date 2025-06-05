package org.a6.pages.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

/**
 * Class containing all Page Factory elements for the Dashboard page
 */
public class DashboardElements extends BaseElements {
      @FindBy(how = How.XPATH, using = "//div[contains(@class, 'bg-[#F2F3F4]') or contains(@class, 'navbar')]")
    public WebElement header;
    
    @FindBy(how = How.XPATH, using = "//h1[contains(@class, 'font-secular') and (contains(text(), 'Dasbor') or contains(text(), 'Dashboard') or contains(text(), 'Beranda'))]")
    public WebElement dashboardHeader;

    @FindBy(how = How.XPATH, using = "//span[contains(text(), 'Bendahara')] | //p[contains(text(), 'Bendahara')]")
    public WebElement userNameDisplay;

    @FindBy(how = How.XPATH, using = "//button[.//svg[contains(@class, 'lucide-log-out')]] | //button[contains(@class, 'logout')]")
    public WebElement logoutButton;    @FindBy(how = How.XPATH, using = "//nav//a[contains(@href, '/dashboard') or contains(@href, '/dasbor')]")
    public WebElement dashboardLink;

    @FindBy(how = How.XPATH, using = "//nav//a[contains(@href, '/tagihan-siswa')]")
    public WebElement tagihanSiswaLink;

    @FindBy(how = How.XPATH, using = "//nav//a[contains(@href, '/transaksi-penerimaan')]")
    public WebElement transaksiPenerimaanLink;

    @FindBy(how = How.XPATH, using = "//nav//a[contains(@href, '/pengaturan-notifikasi')]")
    public WebElement pengaturanNotifikasiLink;

    @FindBy(how = How.XPATH, using = "//nav//a[contains(@href, '/status-pembayaran')]")
    public WebElement statusPembayaranLink;

    @FindBy(how = How.XPATH, using = "//nav//a[contains(@href, '/rekapitulasi')]")
    public WebElement rekapitulasiLink;

    @FindBy(how = How.XPATH, using = "//nav//a[contains(@href, '/progres-transaksi')]")
    public WebElement progresTransaksiLink;
    
    @FindBy(how = How.XPATH, using = "//nav//a | //nav//button")
    public List<WebElement> navigationMenuItems;
      // Alternative locators for robustness
    @FindBy(how = How.XPATH, using = "//div[contains(@class, 'navbar') or contains(@class, 'header')]")
    public WebElement altHeader;
    
    @FindBy(how = How.XPATH, using = "//h1[contains(text(), 'Dasbor') or contains(text(), 'Dashboard') or contains(text(), 'Beranda')]")
    public WebElement altDashboardHeader;
    
    @FindBy(how = How.XPATH, using = "//button[contains(@aria-label, 'Logout') or contains(@title, 'Logout')]")
    public WebElement altLogoutButton;
    
    @FindBy(how = How.XPATH, using = "//img[contains(@alt, 'user') or contains(@alt, 'profile')]")
    public WebElement userProfileImage;
      @FindBy(how = How.XPATH, using = "//div[contains(@class, 'dropdown') or contains(@class, 'menu')]//button[contains(text(), 'Logout') or contains(text(), 'Keluar')]")
    public WebElement logoutMenuOption;
    
    // Logout confirmation dialog elements
    @FindBy(how = How.XPATH, using = "//div[@role='alertdialog']//h2[contains(text(), 'Apakah Anda yakin')]")
    public WebElement logoutConfirmationDialog;
    
    @FindBy(how = How.XPATH, using = "//div[@role='alertdialog']//button[contains(text(), 'Ya')]")
    public WebElement confirmLogoutButton;
    
    @FindBy(how = How.XPATH, using = "//div[@role='alertdialog']//button[contains(text(), 'Tidak')]")
    public WebElement cancelLogoutButton;
    
    public DashboardElements(WebDriver driver) {
        super(driver);
    }
}
