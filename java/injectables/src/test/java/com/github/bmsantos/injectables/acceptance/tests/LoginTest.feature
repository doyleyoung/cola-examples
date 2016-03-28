Feature: Allow users to login
  In order to provide a greeting service
  Users are required to login

  Scenario: Should login
    As a user
    I want to login
    So that I can use app services

    Given a valid user credential
    When the user logins
    Then the welcome greeting will be displayed