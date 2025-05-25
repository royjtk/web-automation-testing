# Panduan Instalasi Cucumber untuk Windows

Dokumen ini berisi langkah-langkah instalasi Cucumber dan komponen pendukungnya untuk pengujian otomatis aplikasi web menggunakan Behaviour Driven Development (BDD) pada sistem operasi Windows.

## Daftar Isi
1. [Pendahuluan](#pendahuluan)
2. [Prasyarat](#prasyarat)
3. [Instalasi Java Development Kit (JDK)](#instalasi-java-development-kit-jdk)
4. [Instalasi Maven](#instalasi-maven)
5. [Konfigurasi IDE (IntelliJ IDEA atau Eclipse)](#konfigurasi-ide)
6. [Membuat Project Maven untuk Cucumber](#membuat-project-maven-untuk-cucumber)
7. [Menambahkan Dependensi Cucumber dan Selenium](#menambahkan-dependensi-cucumber-dan-selenium)
8. [Instalasi WebDriver](#instalasi-webdriver)
9. [Struktur Project](#struktur-project)
10. [Membuat File Feature Pertama](#membuat-file-feature-pertama)
11. [Membuat Step Definition](#membuat-step-definition)
12. [Membuat Test Runner](#membuat-test-runner)
13. [Implementasi Page Object Model](#implementasi-page-object-model)
14. [Menjalankan Pengujian](#menjalankan-pengujian)
15. [Menghasilkan Laporan Pengujian](#menghasilkan-laporan-pengujian)
16. [Troubleshooting](#troubleshooting)
17. [Referensi](#referensi)

## Pendahuluan <a name="pendahuluan"></a>

Cucumber adalah alat pengujian yang mendukung Behaviour Driven Development (BDD). Cucumber memungkinkan penulisan skenario pengujian dalam bahasa alami yang bisa dimengerti oleh semua pemangku kepentingan, termasuk mereka yang tidak memiliki pengetahuan teknis mendalam. 

Dokumen ini akan memandu Anda melalui proses instalasi Cucumber dengan Java dan Selenium WebDriver untuk pengujian otomatis aplikasi web pada sistem operasi Windows.

## Prasyarat <a name="prasyarat"></a>

Sebelum menginstal Cucumber, pastikan Anda memiliki komponen berikut:
- Sistem Operasi Windows 10 atau yang lebih baru
- Akses internet
- Hak administrator pada komputer Anda

## Instalasi Java Development Kit (JDK) <a name="instalasi-java-development-kit-jdk"></a>

Cucumber dengan Java memerlukan JDK 8 atau yang lebih baru.

1. Kunjungi situs web [Oracle JDK](https://www.oracle.com/java/technologies/javase-downloads.html) atau [OpenJDK](https://adoptopenjdk.net/)
2. Unduh installer JDK terbaru untuk Windows (misalnya JDK 21)
3. Jalankan installer dan ikuti instruksi
4. Setelah instalasi selesai, atur variabel lingkungan:
   - Buka menu Start, cari "Edit environment variables for your account" dan buka
   - Pada tab "User variables", klik "New" dan tambahkan:
     - Nama variabel: `JAVA_HOME`
     - Nilai variabel: lokasi instalasi JDK (misalnya `C:\Program Files\Java\jdk-21`)
   - Cari variabel `Path` di "User variables", pilih dan klik "Edit"
   - Klik "New" dan tambahkan `%JAVA_HOME%\bin`
   - Klik "OK" untuk menyimpan perubahan
5. Verifikasi instalasi dengan membuka Command Prompt dan ketik:
   ```
   java -version
   ```
   Anda akan melihat versi Java yang terinstal.

## Instalasi Maven <a name="instalasi-maven"></a>

Maven adalah alat build automation yang akan digunakan untuk mengelola dependensi dan menjalankan pengujian.

1. Unduh Apache Maven dari [situs resmi Maven](https://maven.apache.org/download.cgi) (pilih file binary .zip)
2. Ekstrak file zip ke direktori pilihan Anda, misalnya `C:\Program Files\apache-maven-3.9.5`
3. Atur variabel lingkungan:
   - Buka menu Start, cari "Edit environment variables for your account" dan buka
   - Pada tab "User variables", klik "New" dan tambahkan:
     - Nama variabel: `MAVEN_HOME`
     - Nilai variabel: lokasi ekstraksi Maven (misalnya `C:\Program Files\apache-maven-3.9.5`)
   - Cari variabel `Path` di "User variables", pilih dan klik "Edit"
   - Klik "New" dan tambahkan `%MAVEN_HOME%\bin`
   - Klik "OK" untuk menyimpan perubahan
4. Verifikasi instalasi dengan membuka Command Prompt dan ketik:
   ```
   mvn -version
   ```
   Anda akan melihat versi Maven yang terinstal.

## Membuat Project Maven untuk Cucumber <a name="membuat-project-maven-untuk-cucumber"></a>

### Dengan IntelliJ IDEA

1. Buka IntelliJ IDEA
2. Pilih File > New > Project
3. Pilih Maven dan klik Next
4. Masukkan informasi project:
   - GroupId: `com.example` (atau nama organisasi Anda)
   - ArtifactId: `cucumber-selenium-project` (atau nama project Anda)
   - Version: biarkan default (1.0-SNAPSHOT)
5. Klik Finish

### Dengan Eclipse

1. Buka Eclipse
2. Pilih File > New > Maven Project
3. Centang "Create a simple project" dan klik Next
4. Masukkan informasi project:
   - Group Id: `com.example` (atau nama organisasi Anda)
   - Artifact Id: `cucumber-selenium-project` (atau nama project Anda)
   - Version: biarkan default (0.0.1-SNAPSHOT)
5. Klik Finish

### Dengan Command Line

1. Buka Command Prompt
2. Navigasikan ke direktori tempat Anda ingin membuat project
3. Jalankan perintah berikut:
   ```
   mvn archetype:generate -DgroupId=com.example -DartifactId=cucumber-selenium-project -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -DinteractiveMode=false
   ```
4. Setelah perintah selesai, buka project di IDE pilihan Anda

## Instalasi WebDriver <a name="instalasi-webdriver"></a>

Selenium memerlukan WebDriver untuk berkomunikasi dengan browser. WebDriverManager yang sudah kita tambahkan sebagai dependensi akan menangani download dan konfigurasi driver secara otomatis.

Namun, jika Anda ingin menginstal WebDriver secara manual:

### Chrome WebDriver
1. Periksa versi Chrome Anda: buka Chrome > klik tiga titik di kanan atas > Help > About Google Chrome
2. Unduh [ChromeDriver](https://sites.google.com/a/chromium.org/chromedriver/downloads) yang sesuai dengan versi Chrome Anda
3. Ekstrak file zip ke direktori tertentu, misalnya `C:\WebDrivers`
4. Tambahkan direktori ini ke variabel Path lingkungan

### Firefox WebDriver (GeckoDriver)
1. Unduh [GeckoDriver](https://github.com/mozilla/geckodriver/releases) terbaru
2. Ekstrak file zip ke direktori tertentu, misalnya `C:\WebDrivers`
3. Tambahkan direktori ini ke variabel Path lingkungan

### Edge WebDriver
1. Unduh [Edge WebDriver](https://developer.microsoft.com/en-us/microsoft-edge/tools/webdriver/) yang sesuai dengan versi Edge Anda
2. Ekstrak file zip ke direktori tertentu, misalnya `C:\WebDrivers`
3. Tambahkan direktori ini ke variabel Path lingkungan

## Membuat File Feature Pertama <a name="membuat-file-feature-pertama"></a>

File feature berisi skenario BDD dalam bahasa Gherkin.

1. Klik kanan pada folder `src/test/resources/features` > New > File
2. Beri nama file `login.feature`
3. Tambahkan konten berikut:

```gherkin
Feature: User Authentication
  As a registered user
  I want to login to the application
  So that I can access protected features

  Scenario: Successful login with valid credentials
    Given I am on the login page
    When I enter valid username and password
    And I click on the login button
    Then I should be logged in successfully
    And I should see the dashboard page

  Scenario: Failed login with invalid credentials
    Given I am on the login page
    When I enter invalid username and password
    And I click on the login button
    Then I should see an error message
    And I should remain on the login page
```

## Implementasi Page Object Model <a name="implementasi-page-object-model"></a>

Sekarang buat kelas Page Object untuk halaman login dan dashboard:

1. Klik kanan pada package `com.example.pages` > New > Java Class
2. Beri nama class `LoginPage`
3. Tambahkan konten berikut:

```java
package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private final String LOGIN_URL = "http://ptbsp.ddns.net:6882/login"; // Ganti dengan URL login aplikasi Anda

    @FindBy(id = "username")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(css = ".error-message")
    private WebElement errorMessage;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        // Inisialisasi Page Factory
        PageFactory.initElements(driver, this);
    }

    public void navigateToLoginPage() {
        driver.get(LOGIN_URL);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(usernameField));
    }

    public void enterUsername(String username) {
        usernameField.clear();
        usernameField.sendKeys(username);
    }

    public void enterPassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public boolean isErrorMessageDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOf(errorMessage));
            return errorMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isLoginPageDisplayed() {
        return driver.getCurrentUrl().contains("/login");
    }
}
```

4. Klik kanan pada package `com.example.pages` > New > Java Class
5. Beri nama class `DashboardPage`
6. Tambahkan konten berikut:

```java
package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class DashboardPage {
    private WebDriver driver;

    @FindBy(css = ".dashboard-header")
    private WebElement dashboardHeader;

    @FindBy(css = ".user-profile")
    private WebElement userProfile;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        // Inisialisasi Page Factory
        PageFactory.initElements(driver, this);
    }

    public boolean isLoggedIn() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(userProfile));
            return userProfile.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isDashboardDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(dashboardHeader));
            return dashboardHeader.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
```

## Menjalankan Pengujian <a name="menjalankan-pengujian"></a>

Sekarang Anda dapat menjalankan pengujian:

### Dari IDE

#### IntelliJ IDEA
1. Klik kanan pada kelas `TestRunner`
2. Pilih "Run 'TestRunner'"

#### Eclipse
1. Klik kanan pada kelas `TestRunner`
2. Pilih "Run As" > "JUnit Test"

### Dari Command Line
1. Buka Command Prompt
2. Navigasikan ke direktori project Anda
3. Jalankan perintah:
   ```
   mvn clean test
   ```

## Menghasilkan Laporan Pengujian <a name="menghasilkan-laporan-pengujian"></a>

Untuk menghasilkan laporan Cucumber yang lebih detail:

1. Jalankan perintah:
   ```
   mvn verify
   ```

2. Setelah selesai, buka laporan HTML di `target/cucumber-reports/cucumber-pretty.html` menggunakan browser web.

## Troubleshooting <a name="troubleshooting"></a>

### Masalah Umum dan Solusinya

1. **WebDriver tidak ditemukan**
   - Pastikan WebDriverManager diimpor dan diinisialisasi dengan benar
   - Jika menggunakan instalasi manual, periksa variabel Path lingkungan

2. **Element tidak ditemukan**
   - Periksa lokator elemen (ID, CSS, XPath)
   - Tambahkan WebDriverWait untuk menunggu elemen muncul
   - Verifikasi struktur HTML halaman web

3. **Gagal build dengan Maven**
   - Periksa koneksi internet
   - Verifikasi syntax pom.xml
   - Bersihkan cache Maven dengan perintah `mvn clean`

4. **Step Definition tidak terhubung**
   - Pastikan path package di `@CucumberOptions` benar
   - Periksa apakah teks di file feature dan step definition sama persis

## Referensi <a name="referensi"></a>

- [Dokumentasi Resmi Cucumber](https://cucumber.io/docs/cucumber/)
- [Panduan Cucumber](https://cucumber.io/docs/guides/)
- [Dokumentasi Selenium](https://www.selenium.dev/documentation/)
- [Tutorial Selenium dengan Java](https://www.browserstack.com/guide/selenium-with-java-for-automated-test)
- [Tutorial BDD dengan Cucumber](https://school.cucumber.io/)
- [Page Object Model](https://www.guru99.com/page-object-model-pom-page-factory-in-selenium-ultimate-guide.html)
