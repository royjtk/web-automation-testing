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

## Troubleshooting

Jika terjadi error saat menjalankan test, coba solusi berikut:

1. **Element tidak ditemukan**:
   - Periksa dan sesuaikan locator di file Page Object
   - Periksa apakah website target berubah struktur HTML-nya

2. **Dependency Injection Error**:
   - Pastikan dependency cucumber-picocontainer sudah ditambahkan di pom.xml
   - Pastikan setiap class Step Definition memiliki constructor yang menerima SharedDrivers

3. **Browser tidak terbuka**:
   - Pastikan driver browser yang sesuai tersedia
   - Jalankan dengan opsi `webdriver.chrome.driver` yang mengarah ke executable driver

4. **Timeout Error**:
   - Tingkatkan nilai timeout pada WebDriverWait
   - Periksa koneksi internet

## Dependencies

- Java 21
- Cucumber 7.15.0
- Selenium WebDriver 4.18.1
- WebDriverManager 5.6.2
- JUnit 4.13.2
