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

# Pemahaman Mendalam tentang Cucumber dan BDD

Berdasarkan kuis-kuis yang sudah dikerjakan di kursus "BDD with Cucumber (Java)" di school.cucumber.io, berikut adalah pemahaman mendalam tentang Cucumber dan BDD:

## 1. Memahami BDD (Behaviour Driven Development)

### Apa itu BDD?
BDD (Behaviour Driven Development) adalah pendekatan yang kolaboratif untuk menentukan perilaku sistem yang diinginkan. Setiap kali ada perilaku yang disepakati, kita menggunakan spesifikasi tersebut untuk "mendorong" pengembangan kode yang akan mengimplementasikan perilaku tersebut.

### Tiga Praktik Utama dalam BDD
BDD memiliki tiga praktik utama yang diterapkan dalam urutan berikut:
1. **Discovery (Penemuan)**: Secara kolaboratif menemukan cakupan perilaku yang diperlukan oleh suatu fitur/story.
2. **Formulation (Perumusan)**: Merumuskan spesifikasi dalam bahasa yang bisa dibaca bisnis.
3. **Automation (Otomatisasi)**: Mengotomatisasi spesifikasi yang dirumuskan untuk memverifikasi bahwa sistem benar-benar berperilaku sesuai harapan.

### Hubungan BDD dengan Agile
BDD adalah kumpulan praktik yang dibangun di atas cara kerja Agile, membantu tim untuk sukses. Agar praktik-praktik ini memberikan nilai, kita perlu bekerja dengan cara Agile.

### Peran User Stories dalam BDD
User stories dibuat untuk menjadi "placeholder for a conversation" (penampung untuk percakapan). Mereka memungkinkan kita menunda analisis detail sampai kita yakin bahwa perilaku yang mereka gambarkan benar-benar perlu dikembangkan.

## 2. Cucumber dan Kaitannya dengan BDD

### Apa itu Cucumber?
Cucumber adalah alat yang memahami dokumentasi kita dan mengubahnya menjadi tes otomatis. BDD adalah pendekatan kolaboratif yang terdiri dari tiga praktik. Praktisi BDD dapat menggunakan Cucumber untuk mengotomatisasi dokumentasi mereka.

### Living Documentation
Kita menyebutnya "Living Documentation" karena dokumentasi secara otomatis memberi tahu kita ketika tidak sinkron dengan perilaku aplikasi. Dokumentasi ini bukan dihasilkan oleh tes otomatis - kita masih harus menulisnya! Tes otomatis hanya memberi tahu kita apakah apa yang kita tulis benar atau tidak.

## 3. Kolaborasi dalam BDD

### Three Amigos
Three Amigos adalah pertemuan yang bertujuan untuk memastikan bahwa tim sepenuhnya memahami cakupan story yang dibahas. Untuk efektif, kita membutuhkan setidaknya tiga perspektif berbeda yang diwakili dalam pertemuan:
1. **Perspektif Pelanggan/Bisnis** - biasanya disediakan oleh Product Owner
2. **Perspektif Pengembangan** - biasanya disediakan oleh Developer
3. **Perspektif Pengujian** - biasanya disediakan oleh Tester

Selama pertemuan Three Amigos, wajar jika kita menemukan aturan bisnis baru yang sebelumnya tidak jelas. Tujuan utama pertemuan ini adalah untuk menemukan hal-hal tentang story yang sebelumnya tidak jelas.

### Pentingnya Pertanyaan dalam Discovery Session
Pertanyaan sangat berharga dalam sesi discovery karena kita ingin menemukan area ketidaktahuan kita sebelum mulai mengembangkan fitur. Dengan menemukan pertanyaan, tim sekarang memiliki area konkret untuk diselidiki.

### Peran Keterampilan Testing dalam Discovery
Perspektif pengujian sangat penting selama proses discovery. Ini memungkinkan kita mengajukan pertanyaan "what if" yang sulit yang memastikan bahwa kita telah memikirkan story secara detail. Tim menggunakan contoh konkret untuk menguji pemahaman mereka tentang apa yang diminta.

## 4. Gherkin: Bahasa untuk BDD

### Apa itu Gherkin?
Gherkin adalah sintaks sederhana yang memungkinkan tim menulis spesifikasi yang bisa dibaca bisnis dan dapat dieksekusi. Beberapa kata kunci Gherkin adalah Given, When, dan Then. Gherkin dipahami oleh banyak tools, terutama Cucumber dan Specflow, yang secara efektif mengubah spesifikasi menjadi tes otomatis.

### Hubungan antara Skenario dan Contoh
Selama Three Amigos, tim menggunakan contoh konkret untuk memastikan bahwa mereka memiliki pemahaman bersama tentang fungsionalitas yang akan dikembangkan. Contoh konkret tersebut dirumuskan menjadi skenario Gherkin. Jadi, setiap skenario adalah contoh.

### Komponen Skenario Gherkin
Setiap skenario memiliki tiga komponen utama:
1. **Given (Konteks)**: Konteks untuk skenario. Kita menempatkan sistem dalam keadaan spesifik, siap untuk skenario terungkap.
2. **When (Aksi)**: Aksi. Sesuatu yang terjadi pada sistem yang akan menyebabkan sesuatu yang lain terjadi: hasil.
3. **Then (Hasil)**: Hasil. Ini adalah perilaku yang kita harapkan dari sistem ketika aksi ini terjadi dalam konteks ini.

Bersama-sama, mereka menggambarkan satu aspek tunggal dari perilaku sistem - sebuah contoh.

## 5. Bekerja dengan Cucumber

### Step Definitions
Step definitions adalah metode Java yang benar-benar melakukan apa yang dijelaskan dalam setiap langkah skenario Gherkin. Saat mencoba menjalankan setiap langkah skenario, Cucumber akan mencari step definition yang cocok. Jika ada step definition yang cocok, Cucumber akan memanggil metode tersebut.

### Status Langkah Cucumber
- **Undefined**: Cucumber belum memiliki definisi langkah yang cocok.
- **Pending**: Otomatisasi untuk langkah tersebut sedang dalam proses (ditandai dengan PendingException).
- **Skipped**: Langkah dilewati karena langkah sebelumnya gagal atau pending.
- **Failed**: Langkah gagal karena bug dalam sistem.
- **Passed**: Langkah berhasil.

### Pola Red-Green-Refactor dalam BDD
BDD mengikuti proses yang sama dengan pengembangan berbasis tes (TDD), yang terkadang digambarkan sebagai red-green-refactor:
1. **Red**: Tulis skenario/tes dan lihat gagal
2. **Green**: Buat berhasil (sesederhana mungkin)
3. **Refactor**: Tingkatkan kode, sambil menjaga semua tes/skenario tetap hijau (berhasil)

Penting untuk selalu memastikan kita melihat skenario gagal sebelum membuat berhasil, karena transisi dari merah ke hijau memberi kita keyakinan bahwa skenario dan implementasi benar-benar melakukan apa yang kita harapkan.

## 6. Cucumber Expressions

### Perbedaan dengan Regular Expressions
Regular Expressions adalah alat yang kuat yang telah digunakan dalam ilmu komputer selama beberapa dekade. Mereka bisa sulit dipahami dan dipelihara, jadi tim Cucumber membuat mekanisme yang disederhanakan, yang disebut Cucumber Expressions. Namun, Cucumber tetap kompatibel ke belakang, jadi kita bisa menggunakan keduanya.

### Fitur-fitur Cucumber Expressions
- Teks yang dikelilingi oleh tanda kurung `()` dianggap opsional.
- Kata-kata yang dipisahkan oleh garis miring `/` dianggap sebagai alternatif. Tidak boleh ada spasi antara kata dan garis miring.
- Parameter bisa ditangkap dengan format `{tipe}`, misalnya `{int}` untuk bilangan bulat.

## 7. Plugins dan Reporting di Cucumber

### Jenis-jenis Plugin
Cucumber memiliki banyak plugin bawaan, termasuk:
- Plugin HTML
- Plugin JSON
- Plugin JUnit
- Plugin rerun

### Formatter Rerun
Formatter rerun melacak file fitur dan nomor baris setiap skenario yang gagal. Informasi ini ditampilkan dalam format file fitur/nomor baris yang dapat disimpan ke file. Ketika diberikan ke Cucumber sebagai input, skenario yang diidentifikasi akan dijalankan.

### Cucumber Reports
Tim Cucumber membuat Cucumber Reports untuk memudahkan berbagi "living documentation". Ini menampilkan informasi tambahan tentang menjalankan Cucumber, merangkum konteks eksekusi dan hasil skenario.

Informasi tambahan yang dipublikasikan oleh Cucumber Reports:
- Statistik tingkat tinggi dari skenario yang dijalankan dan statusnya
- Waktu yang berlalu sejak laporan dibuat
- Waktu yang diperlukan untuk menjalankan skenario dan menghasilkan laporan
- Versi sistem operasi
- Versi bahasa yang digunakan untuk stepdefs
- Versi Cucumber

### Penggunaan Tag di Cucumber
Tag di Cucumber digunakan untuk mengkategorikan skenario dan fitur. Tag diwarisi dari cakupan yang melingkupinya, jadi Skenario mewarisi tag dari Fitur. Tag bersifat case-sensitive, jadi @SLOW tidak cocok dengan @slow.

Ekspresi tag dapat:
- Berada pada baris yang sama dan pada baris berurutan
- Mengimplementasikan operator boolean: and, not, or

## 8. Konsep-konsep Penting Lainnya

### Perbedaan File Fitur Cucumber dengan Tes Tradisional
File fitur Cucumber ditulis menggunakan Gherkin, sehingga kita dapat membuat skenario yang menggunakan bahasa domain kita sendiri, sehingga dapat dibaca dan dipahami oleh semua orang yang terlibat dalam menentukan dan menghasilkan software.

Meskipun skenario Cucumber bertindak sebagai tes ketika mereka diotomatisasi, ini bukan tujuan utama mereka. Tujuan utama mereka adalah untuk menyediakan spesifikasi tunggal dan bersama, yang ditulis dalam bahasa domain bisnis - memfasilitasi kolaborasi, umpan balik, dan dokumentasi yang andal. Tujuan utama tes otomatis tradisional, di sisi lain, adalah untuk memeriksa bahwa software berperilaku sesuai harapan.

### Bridging the Gap antara Domain Experts dan Tim Pengembangan
File fitur yang dipahami Cucumber ditulis menggunakan Gherkin, sehingga kita dapat membuat skenario yang menggunakan bahasa domain sendiri, agar dapat dibaca dan dipahami oleh semua orang yang terlibat dalam menentukan dan menghasilkan software.

### Proses Implementasi Solusi dalam BDD
Tujuan kita pada tahap awal adalah mendapatkan tes yang gagal, di mana satu-satunya hal yang tersisa untuk membuatnya berhasil adalah melakukan perubahan pada implementasi aplikasi itu sendiri.

Pada sistem yang ada, kita mungkin tidak perlu membuat begitu banyak kode baru untuk mencapai tujuan ini, tetapi kita mungkin perlu melakukan beberapa perubahan pada cara kita memanggil sistem. Ini memberi kita kesempatan untuk melakukan pemodelan domain ringan.

Kita menggunakan skenario untuk memandu kita dalam implementasi kita. Pendekatan ini disebut pengembangan "outside-in", membantu kita memastikan bahwa ketika kita mengimplementasikan solusi, kita mengimplementasikannya berdasarkan kebutuhan nyata.