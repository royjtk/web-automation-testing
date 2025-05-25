# Pemahaman Web Automation Testing

## Apa itu Automation Testing?

Automation Testing itu adalah cara pengujian software yang dilakukan secara otomatis pakai tools khusus, bukan manual. Jadi kita nggak perlu klik-klik sendiri buat ngecek semua fitur aplikasi, tapi bisa bikin script yang bakal jalan sendiri buat ngecek semuanya. Cocok banget buat nguji aplikasi yang sering diupdate, karena kita bisa reuse script-nya buat ngecek apakah fitur yang sudah ada masih jalan dengan baik setelah update (regression testing).

Keuntungan pakai automation testing:
- Jauh lebih cepat dibanding manual testing
- Akurasinya lebih tinggi (komputer nggak bakal bosan atau lupa)
- Bisa dipakai berulang kali (reusable)
- Ngurangin tenaga manusia yang dibutuhin
- Cocok banget buat regression testing

Tapi perlu diingat, automation testing juga punya risiko:
- Butuh waktu dan biaya di awal buat belajar tools-nya
- Perlu skill programming yang lumayan
- Kalau UI sering berubah, script juga harus sering diupdate

## Kapan Harus Pakai Automation Testing?

Otomasi testing bagusnya dilakukan kalau:
- Sistem udah stabil (nggak terlalu banyak perubahan mendadak)
- Test case dan test scenario udah fix
- Test data udah siap
- Environment (test bed) udah ready
- Tools automation-nya udah terinstal

Jangan dipaksain automation kalau:
- Aplikasi yang diuji masih kecil/simpel
- Cuma ada beberapa build untuk diuji
- Fitur-fiturnya masih belum stabil/banyak bug

## Automation Testing Tools yang Kita Pakai

Dalam praktikum ini, kita pakai dua tools utama:

### 1. Cucumber
Cucumber adalah tool untuk mendukung Behaviour Driven Development (BDD). Kerennya, Cucumber memungkinkan kita nulis test case pakai bahasa manusia biasa (bahasa natural) yang bisa dimengerti oleh semua stakeholder, termasuk yang nggak ngerti coding.

### 2. Selenium
Selenium adalah framework buat automation testing web applications. Dia bisa mengendalikan browser secara otomatis buat melakukan aksi-aksi seperti yang dilakukan user (klik, input text, scroll, dll). Selenium support banyak bahasa pemrograman, tapi dalam praktikum ini kita pakai Java.

## Konsep Penting dalam Web Automation Testing

### 1. Behaviour Driven Development (BDD)
BDD itu pendekatan development yang fokus pada perilaku aplikasi dari perspektif pengguna. Prosesnya:
- Semua stakeholder (developer, QA, business team) kerja bareng
- Definisiin dulu behavior software yang diharapkan
- Behavior ditulis dalam bentuk test scenario pakai bahasa natural (Gherkin)
- Format standarnya: Given-When-Then
  - Given: kondisi awal
  - When: aksi yang dilakukan
  - Then: hasil yang diharapkan

### 2. Page Object Model (POM)
POM adalah design pattern yang biasa dipake di automation testing. Intinya, setiap halaman web punya class sendiri yang berisi semua element dan action di halaman tersebut. Jadi kalau ada perubahan di UI, kita cuma perlu update class untuk halaman itu aja, nggak perlu ubah semua test case.

### 3. Web Locator
Web Locator digunakan untuk mencari elemen di halaman web. Bisa berupa:
- ID
- CSS Selector
- Class Name
- Tag Name
- XPath

Pemilihan locator yang tepat penting banget biar script kita nggak gampang rusak kalau ada perubahan di UI.

### 4. Page Factory
Page Factory adalah fitur di Selenium yang mempermudah inisialisasi elemen dalam Page Object. Jadi kita nggak perlu menulis `driver.findElement()` berulang kali.

## Alur Kerja Web Automation Testing

1. Siapkan environment (install Java, Maven, Selenium, Cucumber, dll)
2. Bikin file feature (.feature) yang berisi skenario pengujian dalam format Gherkin
3. Bikin Page Object untuk setiap halaman yang mau diuji
4. Implementasi Step Definition yang menghubungkan Gherkin dengan kode Java
5. Konfigurasi WebDriver
6. Jalankan pengujian
7. Generate test report

## Perbandingan Manual Testing vs Automation Testing

| Manual Testing | Automation Testing |
|----------------|-------------------|
| Bikin test case di dokumen/excel | Bikin test case dalam bentuk script |
| Jalanin test step by step secara manual | Komputer jalanin test secara otomatis |
| Kita perlu cek hasil testing satu-satu | Hasil testing dikumpulin secara otomatis |
| Kalau gagal, ulangi dari awal | Kalau gagal, cukup perbaiki script, lalu run lagi |
| Butuh waktu lama untuk test berulang | Sangat cepat untuk test berulang |

Dalam praktikum ini, kita akan belajar cara mengotomatisasi pengujian web application (http://ptbsp.ddns.net:6882/) menggunakan Cucumber dan Selenium. Kita akan fokus pada fungsi login dan logout sebagai contoh kasus.


