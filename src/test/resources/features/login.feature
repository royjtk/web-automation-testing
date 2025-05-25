@login
Feature: User Authentication
  As a user
  I want to login to the application
  So that I can access protected features

  Background:
    Given I am on the login page

  Scenario: TC-LOGIN-01 Successful login with valid credentials
    When I enter username "bendahara"
    And I enter password "admin123"
    And I click login button
    Then I should be logged in successfully
    And I should be redirected to the dashboard page
    And The dashboard header should display username "bendahara"

  Scenario: TC-LOGIN-02 Failed login with empty username
    When I leave username field empty
    And I enter password "admin123"
    And I click login button
    Then I should remain on the login page
    And I should see error message "Username wajib diisi" below username field

  Scenario: TC-LOGIN-03 Failed login with empty password
    When I enter username "bendahara"
    And I leave password field empty
    And I click login button
    Then I should remain on the login page
    And I should see error message "Password wajib diisi" below password field

  Scenario: TC-LOGIN-04 Failed login with invalid username
    When I enter username "invalid_user"
    And I enter password "admin123"
    And I click login button
    Then I should remain on the login page
    And I should see error message "Username atau password salah"

  Scenario: TC-LOGIN-05 Failed login with invalid password
    When I enter username "bendahara"
    And I enter password "invalid_pass"
    And I click login button
    Then I should remain on the login page
    And I should see error message "Username atau password salah"

  Scenario: TC-LOGIN-06 Toggle password visibility
    When I enter password "admin123"
    And I click password visibility toggle
    Then Password field should be visible
    When I click password visibility toggle again
    Then Password field should be hidden
