@logout
Feature: Logout functionality
  As a logged in user of the Pengelolaan Dana Pendidikan Sekolah Zaidan Educare application
  I want to be able to logout from the application
  So that my session is securely terminated

  Background:
    Given I am logged in with valid credentials
      | username  | password  |
      | bendahara | admin123  |
    And I am on the dashboard page

  Scenario: Successful logout
    When I click on logout button in the header
    Then I should be successfully logged out
    And I should be redirected to login page
    And Login form should be displayed

  Scenario: Session invalidation after logout
    When I click on logout button in the header
    And I click on browser back button after logout
    Then I should not be able to access protected pages
    And I should be redirected to login page

  Scenario: Verify navbar elements are not accessible after logout
    When I click on logout button in the header
    And I try to access dashboard URL directly after logout
    Then I should not be able to access the dashboard
    And I should be redirected to login page
