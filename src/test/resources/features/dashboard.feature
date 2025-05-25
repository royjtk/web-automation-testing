Feature: Dashboard Navigation after Login
  As a logged in user with Bendahara role
  I want to verify the dashboard elements and navigation menu
  So that I can ensure all required functionalities are accessible

  Background:
    Given I am logged in with valid credentials
      | username  | password  |
      | bendahara | admin123  |

  Scenario: Verify dashboard header with Bendahara credentials
    Then Dashboard header should be displayed
    And Username "bendahara" should be visible in the header

  Scenario: Verify navigation menu items for Bendahara
    Then Navigation menu should display the following options
      | menuItem            |
      | Dashboard           |
      | Tagihan Siswa       |
      | Transaksi Penerimaan |
      | Pengaturan Notifikasi |
      | Status Pembayaran   |
      | Rekapitulasi        |
      | Progres Transaksi   |
