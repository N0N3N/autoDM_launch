Feature: Checking and Launching Auto-DM

  Scenario: Just Check for requested tables status
    Given Opening Auto-DM webpage
    When Table name contains: DNT release QA
    Then Check status
    And Close webpage

  Scenario: Check status and launch tables
    Given Opening Auto-DM webpage
    When Table name contains: Tex_
    Then Check status
    And Start tables
    And Close webpage