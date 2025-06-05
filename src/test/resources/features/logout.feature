@logout
Feature: User Logout
  As a logged in user
  I want to logout from the application
  So that my session is terminated and account is secured

  Background:
    Given I am logged in with username "bendahara" and password "admin123"

  Scenario: TC-LOGOUT-01 Successful logout with confirmation
    When I click logout button in the header
    Then I see a logout confirmation dialog
    When I confirm the logout
    Then I should be logged out successfully
    And I should be redirected to the login page
    And Login form should be displayed