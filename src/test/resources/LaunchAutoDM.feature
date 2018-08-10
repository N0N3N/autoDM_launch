Feature: Checking and Launching Auto-DM


  Scenario: Check status and launch tables
    Given Opening Auto-DM webpage
    When Table name contains: Tex_
    Then Check status
    And Start tables
    And Close webpage
