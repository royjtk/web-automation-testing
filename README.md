# Web Automation Testing dengan Cucumber dan Selenium

Project ini berisi implementasi pengujian otomatis untuk aplikasi web Pengelolaan Dana Pendidikan Sekolah Zaidan Educare menggunakan Cucumber dan Selenium WebDriver.

## Struktur Project

- `src/main/java/org/a6/pages/`: Berisi implementasi Page Object Model
- `src/test/java/org/a6/runners/`: Berisi Test Runner untuk menjalankan pengujian
- `src/test/java/org/a6/stepdefs/`: Berisi Step Definitions untuk Cucumber
- `src/test/resources/features/`: Berisi file feature Cucumber dalam sintaks Gherkin

## Fitur yang Diuji

1. **Login Functionality**
   - Login berhasil dengan kredensial valid
   - Login gagal dengan username kosong
   - Login gagal dengan password kosong
   - Login gagal dengan username tidak valid
   - Login gagal dengan password tidak valid
   - Toggle visibilitas password

2. **Logout Functionality**
   - Logout berhasil
   - Invalidasi sesi setelah logout
   - Verifikasi elemen navbar tidak dapat diakses setelah logout

3. **Dashboard Navigation**
   - Verifikasi header dashboard dengan kredensial Bendahara
   - Verifikasi item menu navigasi untuk Bendahara

## Cara Menjalankan Pengujian

### Menggunakan Maven

1. Buka terminal atau command prompt
2. Navigasikan ke direktori proyek
3. Jalankan perintah berikut:

```
mvn clean test
```

### Menggunakan IDE

1. Buka project di IDE (IntelliJ IDEA, Eclipse, dll)
2. Navigasi ke `src/test/java/org/a6/runners/TestRunner.java`
3. Klik kanan pada file dan pilih "Run" atau "Run as JUnit Test"

## Melihat Laporan Pengujian

Laporan pengujian akan dibuat setelah menjalankan tes. Untuk menghasilkan laporan lengkap, jalankan:

```
mvn verify
```

Laporan HTML akan tersedia di:
- `target/cucumber-reports/cucumber-pretty.html`

## Melihat Laporan Pengujian dengan Allure

Laporan pengujian akan dibuat setelah menjalankan tes. Untuk menghasilkan laporan lengkap, jalankan:

```
allure serve target/allure-results
```

Laporan HTML akan langsung terbentuk.

## Troubleshooting

Jika terjadi error saat menjalankan test, coba solusi berikut:

1. **Element tidak ditemukan (NoSuchElementException)**:
   - Periksa dan sesuaikan locator di file Page Object (`LoginPage.java`, `DashboardPage.java`)
   - Gunakan metode yang lebih fleksibel seperti `xpath` dengan multiple attribute matching
   - Coba gunakan atribut yang lebih stabil seperti `name`, `id`, atau `class`
   - Tambahkan waktu tunggu (wait) yang lebih lama pada element yang sulit ditemukan

2. **Dependency Injection Error**:
   - Pastikan dependency `cucumber-picocontainer` sudah ditambahkan di pom.xml
   - Periksa constructor pada class Step Definition memiliki parameter `SharedDrivers`
   - Hindari menciptakan WebDriver instance secara terpisah di setiap class Step Definition

3. **Browser tidak terbuka**:
   - Periksa path ChromeDriver yang digunakan di `SharedDrivers.java`
   - Pastikan versi ChromeDriver cocok dengan versi Chrome yang terinstal
   - Gunakan WebDriverManager untuk mengelola driver secara otomatis
   - Jalankan dengan flag `--no-sandbox` dan `--disable-dev-shm-usage` untuk mengatasi masalah di lingkungan tertentu

4. **Timeout Error**:
   - Tingkatkan nilai timeout pada `WebDriverWait` (misalnya dari 10 detik menjadi 30 detik)
   - Gunakan `implicitlyWait` dan `pageLoadTimeout` yang lebih lama
   - Periksa koneksi internet dan responsivitas situs target

5. **Chrome DevTools Protocol Error (CDP)**:
   - Update versi Selenium ke yang terbaru yang mendukung versi Chrome Anda
   - Tambahkan dependency selenium-devtools yang sesuai dengan versi Chrome
   - Nonaktifkan log warning dengan `Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF)`

6. **Menjalankan Tes Spesifik**:
   - Gunakan tag di feature files dan konfigurasikan di TestRunner: `@CucumberOptions(tags = "@login")`
   - Jalankan file feature tertentu: `mvn test -Dcucumber.options="src/test/resources/features/login.feature"`

## Dependencies

- Java 21
- Cucumber 7.22.2
- Selenium WebDriver 4.32.0
- WebDriverManager 5.6.2
- JUnit 4.13.2
