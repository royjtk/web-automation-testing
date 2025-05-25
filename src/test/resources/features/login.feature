@login
Feature: Login functionality
  As a user of the Pengelolaan Dana Pendidikan Sekolah Zaidan Educare application
  I want to be able to login to the application
  So that I can access the protected functionalities

  Scenario: Successful login with valid credentials
    Given I am on the login page
    When I enter valid username "bendahara"
    And I enter valid password "admin123"
    And I click on the login button
    Then I should be successfully logged in
    And I should be redirected to dashboard page
    And Dashboard header should be displayed

  Scenario: Failed login with empty username
    Given I am on the login page
    When I leave username field empty
    And I enter valid password "admin123"
    And I click on the login button
    Then I should remain on login page
    And Error message should be displayed indicating username is required

  Scenario: Failed login with empty password
    Given I am on the login page
    When I enter valid username "bendahara"
    And I leave password field empty
    And I click on the login button
    Then I should remain on login page
    And Error message should be displayed indicating password is required

  Scenario: Failed login with invalid username
    Given I am on the login page
    When I enter invalid username "invalid_user"
    And I enter valid password "admin123"
    And I click on the login button
    Then I should remain on login page
    And Error message should be displayed indicating invalid credentials

  Scenario: Failed login with invalid password
    Given I am on the login page
    When I enter valid username "bendahara"
    And I enter invalid password "invalid_pass"
    And I click on the login button
    Then I should remain on login page
    And Error message should be displayed indicating invalid credentials

  Scenario: Toggle password visibility
    Given I am on the login page
    When I enter password "admin123"
    And I click on password visibility toggle icon
    Then Password should toggle between visible and hidden state
    And Toggle icon should change appearance
