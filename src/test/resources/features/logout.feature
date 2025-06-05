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

#   Scenario: TC-LOGOUT-03 Session invalidation after logout
#     When I click logout button in the header
#     Then I see a logout confirmation dialog
#     When I confirm the logout
#     And I should be redirected to the login page
#     And I click browser back button
#     Then I should not be able to access protected pages
#     And I should be redirected to the login page

#   Scenario: TC-LOGOUT-04 Verify navbar elements not accessible after logout
#     When I click logout button in the header
#     Then I see a logout confirmation dialog
#     When I confirm the logout
#     And I should be redirected to the login page 
#     And I try to access dashboard URL directly
#     Then I should not be able to access the dashboard
#     And I should be redirected to the login page
