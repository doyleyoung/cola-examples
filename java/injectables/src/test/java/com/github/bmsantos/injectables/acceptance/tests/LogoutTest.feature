Feature: Allow users to logout
  In order to ensure privacy is guaranteed
  Users should be able to logout

  Scenario: Should logout
    As a user
    I want to logout
    So that I can use app services from different systems

    Given a user is logged in
    When the user logs out
    Then the welcome greeting will be displayed