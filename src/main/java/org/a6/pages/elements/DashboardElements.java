package org.a6.pages.elements;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

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

  @FindBy(xpath = "//button[@aria-haspopup='dialog' and starts-with(@aria-controls,'radix-')]")
  public WebElement logoutButton;

  @FindBy(how = How.XPATH, using = "//nav//a | //nav//button")
  public List<WebElement> navigationMenuItems;

  // Alternative locators for robustness
  @FindBy(how = How.XPATH, using = "//div[contains(@class, 'navbar') or contains(@class, 'header')]")
  public WebElement altHeader;

  // Dialog root (judul “Apakah Anda yakin?”)
  @FindBy(xpath = "//div[@role='alertdialog' and starts-with(@id,'radix-')]//h2[contains(.,'Apakah Anda yakin')]")
  public WebElement logoutConfirmationDialog;

  // Tombol “Ya”
  @FindBy(xpath = "//div[@role='alertdialog' and starts-with(@id,'radix-')]//button[normalize-space()='Ya']")
  public WebElement confirmLogoutButton;

  // Tombol “Tidak”
  @FindBy(xpath = "//div[@role='alertdialog' and starts-with(@id,'radix-')]//button[normalize-space()='Tidak']")
  public WebElement cancelLogoutButton;

  public DashboardElements(WebDriver driver) {
    super(driver);
  }
}
