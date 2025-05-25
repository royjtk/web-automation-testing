# Pertemuan 12,13: Web Automation Testing (Selenium, Cucumber)

## Pengujian Perangkat Lunak  
**Pengajar:** Asri Maspupah  
**Jadwal Kuliah:** Selasa, 07.00 - 09.30 (3B) & 09.50 - 12.20 (3A)  

---

## Materi

### Definisi Automation Testing  
- Proses pengujian yang dilakukan secara otomatis dengan memanfaatkan tools untuk memeriksa bug, berbagai masalah teknis, dan meningkatkan kualitas perangkat lunak.  
- Sangat cocok digunakan untuk pengujian regresi.  
- Effort pengerjaan automation testing lebih mahal daripada pengerjaan dengan manual testing → Oleh karena itu perlu diketahui kapan sebaiknya menggunakan automation.  
- Berkaitan dengan proses mengeksekusi test case, memelihara test data, menganalisis test result dengan menggunakan alat khusus dan campur tangan manusia yang minimal.

### Tujuan Automation Testing  
1. **Meningkatkan efisiensi pengujian**  
   Membantu tim mengurangi beban pekerjaan berulang.  
2. **Mengotomatiskan aktivitas yang tidak dapat dijalankan secara manual**  
   Misalnya: menambahkan seribu entri ke database sebagai pengujian performa.  
3. **Meningkatkan reliability pengujian**  
   Pengujian dapat dilakukan di berbagai environments dengan berbagai skenario dan waktu secara mudah dan cepat.

### Manual Testing VS Automation Testing  

| Manual Testing | Automation Testing |
|----------------|--------------------|
| Buat test case pada dokumen daftar test case | Buat satu atau lebih test cases dalam script test |
| Menjalankan kode secara manual (misal mengisi formulir langkah demi langkah) | Auto-compile dan run untuk melihat tests fail |
| Periksa file Log, Basis Data, Layanan Eksternal, nilai variabel, output layar | Tulis code agar test case menjadi pass |
| Jika pengujian tidak berhasil, ulangi proses sebelumnya | Auto-compile dan run lagi |
|  | Jika tests fail, modifikasi sesuai kebutuhan |
|  | Jika tests pass, lanjut ke metode berikutnya |

### Kapan Harus Menggunakan Otomasi Pengujian?  
**Sebaiknya dilakukan otomatis jika:**  
- Bangunan sistem sudah stabil  
- Test Cases dan Test Scenarios sudah siap dan final  
- Test Data sudah siap  
- Test Bed sudah siap  
- Automation tool sudah terinstal  

**Jangan otomatis jika:**  
- Sistem Under Test (SUT) tidak besar/kompleks  
- Hanya menerima beberapa build untuk diuji  
- Fitur tidak bekerja secara akurat  

### Manfaat dan Risiko Penerapan Automation Testing  

**Manfaat:**  
- Eksekusi pengujian yang lebih cepat  
- Peningkatan produktivitas  
- Peningkatan akurasi  
- Reusability  
- Sangat diandalkan pada pengujian regresi  
- Mengurangi tenaga manusia  

**Risiko:**  
- Butuh waktu, biaya, dan upaya untuk mengenal alat pengujian otomatis  
- Perubahan proses pengujian sehingga perlu penyesuaian kembali  
- Tingkat kemahiran staf yang tinggi untuk menggunakan automation testing tools dalam pembuatan test script  

### Berapa Banyak yang Harus Diotomatisasi?  
- Secara ideal, diharapkan 60% otomatisasi untuk Regression Suite  
- Pertama, otomatisasikan fungsi utama yang akan dilakukan oleh end-users  
- Selanjutnya, tambahkan bagian-bagian aplikasi yang tidak terlalu kritis sesuai waktu yang memungkinkan  
- Kembangkan matriks test coverage  

---

## Automation Methodology & Framework

### Architecture Automation Testing  
- Software kita bisa disimpan di repository development seperti server lokal, GitLab, Git, dll  
- Script Test Engine menggunakan Selenium, JUnit, Katalon, dll  
- Ada utility lain seperti Jenkins untuk penjadwalan pengujian (jadwal waktu tertentu atau saat ada perubahan)  

### Ilustrasi Pemanfaatan Pengujian Otomatis  
- Pengujian Regresi  
- Produk yang dikembangkan berkelanjutan (product line based / evolution software), contohnya OS-Windows, Microsoft, Mobile Apps yang selalu update  
- Release product → Software di-upgrade → Client (CEO/Product Owner) → Dev Software melakukan modifikasi → Perubahan tidak menggangu fitur yang sudah running → retest  

### Top 15 List of Automation Testing Tools (Latest Update in 2023)  
*(Daftar alat ini tidak disebutkan detail dalam slide, bisa dicari referensi tambahan jika perlu)*

---

## Pengenalan Alat Pengujian Automation Web: Selenium dengan Java + Cucumber (BDD) Framework  

- **Cucumber** adalah alat pengujian yang mendukung Behaviour Driven Development (BDD).  
  - Menawarkan cara menulis tes yang bisa dimengerti siapa pun tanpa harus punya pengetahuan teknis.  
  - Dalam BDD, pengguna seperti business analysts dan product owners menulis skenario/acceptance tests dari sudut pandang pelanggan untuk disetujui sebelum developer menulis kode.  
  - Framework Cucumber menggunakan bahasa pemrograman Ruby.  

- **Selenium** adalah framework pengujian otomatis open-source untuk aplikasi web di berbagai browser dan platform.  
  - Mendukung beberapa bahasa pemrograman seperti Java, C#, Python, dll untuk membuat skrip pengujian.  

### Behaviour Driven Development (BDD)  
- Fokus pada test scenario untuk setiap requirement selama pengembangan.  
- Semua stakeholder (developer, QA, non-tech team) berkolaborasi aktif.  
- Proses development dimulai dengan brainstorming pendefinisian behavior software.  
- Behavior ditulis dalam bentuk test scenario dengan bahasa natural (Gherkin).  
- Step definition menghubungkan skenario dengan kode agar aplikasi berjalan otomatis sesuai pengujian.

---

## Page Object Model (POM)  

- Pendekatan pengujian otomatis dengan memisahkan struktur dan perilaku halaman web menjadi objek yang bisa dioperasikan skrip pengujian.  
- **Page Object**: kelas berorientasi objek untuk mengidentifikasi web element antarmuka yang dibutuhkan selama pengujian.  
- **Test Method**: kelas yang berisi kode pengujian berupa interaksi pada page object untuk menjalankan skenario pengujian.  

**Sumber:**  
- [Guru99 Page Object Model](https://www.guru99.com/page-object-model-pom-page-factory-in-selenium-ultimate-guide.html)  

### Lebih Lanjut tentang POM  
- Setiap halaman web atau UI komponen memiliki kelas terkait "Page Object" yang berisi elemen HTML dan metode interaksi (klik tombol, isi formulir, cek status).  
- POM membuat pengujian lebih terstruktur, mudah dipelihara, dan mudah dipahami.  
- Perubahan halaman hanya perlu penyesuaian pada kelas Page Object tanpa mempengaruhi skrip pengujian.  

---

## Web Locator  

- Objek yang digunakan untuk mencari web elemen pada halaman.  
- Locator dapat berupa:  
  - ID  
  - CSS Selector  
  - Class Name  
  - Tag Name  
  - XPath  
- Pemilihan locator yang tepat penting dan bisa menjadi tantangan terutama jika tidak ada locator unik.  

---

## Fitur Page Factory di Selenium  

- Memudahkan inisialisasi elemen dalam Page Object sehingga menghindari penggunaan berulang `driver.findElement`.  
- Dapat digunakan bersama POM untuk kemudahan pembacaan dan organisasi kode.  
- Memungkinkan pembuatan objek Page Object otomatis dengan anotasi untuk mengidentifikasi elemen halaman web.

---

## Kegiatan Praktikum  

**Pemahaman Automation Testing Web Application dengan Cucumber dan Selenium**  

1. Pahami instruksi praktikum di:  
   [Link Praktikum](https://drive.google.com/file/d/1JZ1Rm7cuGhtHpD4cvU2luFivLfNi7Gir/view?usp=sharing)  
   - Meliputi persiapan environment web automation testing dan pelaksanaan pengujian fungsionalitas web secara otomatis.  

2. Goals:  
   a. Paham persiapan environment web automation testing  
   b. Melakukan pengujian fungsionalitas otomatis menggunakan script yang sudah dibuat  

3. Laporan pemahaman praktikum dibuat dalam bentuk slide (individu).  

4. Tautan pengumpulan:  
   - Progres (25/05/2025): folder progress  
   - Final (01/06/2025): folder final  
   - Perbaikan (05/06/2025): folder perbaikan  

---

## Perangkat Praktikum  

Folder "TGS. 5 Automation Web Testing" berisi:  
1. Detail Instruksi Praktikum  
2. Sheet monitoring Tab "W12-13"  

Isi Sheet Monitoring dapat diakses di:  
[bit.ly/40W84pU](http://bit.ly/40W84pU)  

---

# Selesai  
