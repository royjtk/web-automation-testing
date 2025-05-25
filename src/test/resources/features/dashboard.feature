@dashboard
Feature: Dashboard Navigation after Login
  As a logged in user with Bendahara role
  I want to verify the dashboard elements and navigation menu
  So that I can ensure all required functionalities are accessible

  Background:
    Given I am logged in with valid credentials
      | username  | password  |
      | bendahara | admin123  |

  Scenario: TC-DASH-01 Verify dashboard header with Bendahara credentials
    When I am on the dashboard page
    Then The dashboard header should be displayed
    And The username "bendahara" should be visible in the header

  Scenario: TC-DASH-02 Verify navigation menu items for Bendahara
    When I am on the dashboard page
    Then The navigation menu should display the following options:
      | menuItem            |
      | Dashboard           |
      | Tagihan Siswa       |
      | Transaksi Penerimaan |
      | Pengaturan Notifikasi |
      | Status Pembayaran   |
      | Rekapitulasi        |
      | Progres Transaksi   |
