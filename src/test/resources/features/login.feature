@login
Feature: User Authentication
  As a user
  I want to login to the application
  So that I can access protected features

  Background:
    Given User has opened the browser
    And User has navigated on the login page Education Fund Payment Management System for Zaidan Educare School app

  @positive
  Scenario: TC-1.1 Check login is successful with valid credentials as role "bendahara"
    When User enters username "bendahara" and password "admin123"
    And User clicks login button
    Then I should be logged in successfully
    And System is navigated user to the dashboard page and show page tittle "Dasbor - Bendahara"    
    And User should be able to see navigation bar for bendahara
      | Dasbor                          |
      | Tagihan Siswa                   |
      | Transaksi Penerimaan Dana       |
      | Pengaturan Notifikasi           |
      | Status Pembayaran               |
      | Rekapitulasi                    |
      | Progres Transaksi Penerima Dana |

  Scenario: TC-1.2 Check login is un-successful with invalid credentials. Its is username not registered
    When User enters username "indra" and password "admin123"
    And User clicks login button
    Then I should remain on the login page
    And I should see error message "Incorrect username or password, please try again!"

  # Scenario: TC-LOGIN-02 Failed login with empty username
  #   When I leave username field empty
  #   And I enter password "admin123"
  #   And I click login button
  #   Then I should remain on the login page
  #   And I should see error message "Username wajib diisi" below username field

  # Scenario: TC-LOGIN-03 Failed login with empty password
  #   When I enter username "bendahara"
  #   And I leave password field empty
  #   And I click login button
  #   Then I should remain on the login page
  #   And I should see error message "Password wajib diisi" below password field



  # Scenario: TC-LOGIN-05 Failed login with invalid password
  #   When I enter username "bendahara"
  #   And I enter password "invalid_pass"
  #   And I click login button
  #   Then I should remain on the login page
  #   And I should see error message "Username atau password salah"

  # Scenario: TC-LOGIN-06 Toggle password visibility
  #   When I enter password "admin123"
  #   And I click password visibility toggle
  #   Then Password field should be visible
  #   When I click password visibility toggle again
  #   Then Password field should be hidden
