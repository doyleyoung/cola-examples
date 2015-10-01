Feature: Should find beverages

  Scenario Outline: Should find beverages
    Given a <beverage>
    When a google search is performed
    Then the <beverage> wikipedia entry will be present

  Examples:
    | beverage            |
    | Vinho Verde         |
    | Port wine           |
    | Albariño            |
    | Cava (Spanish_wine) |