Feature: Greet visitors
As a company, we want to greet visitors in order to increase customer satisfaction.

Scenario: Should have default greeting
When a greeting request is submitted
Then it will display Hello, World

Scenario: Should personalize greeting
When a personalized greeting request is submitted
Then it will display Hello, Einstein
