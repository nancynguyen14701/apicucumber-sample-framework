Feature: Lookup location by country and postal code

  Scenario: Successful lookup with valid country code and postal code
    Given the country code is "DE"
    And the postal code is "51149"
    When I send a GET request to the Zippopotam API
    Then the response status code should be 200
    And the country name should be "Germany"
    And the place name should include "Köln"
    And the state should include "Nordrhein-Westfalen"
