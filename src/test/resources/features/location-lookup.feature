Feature: Lookup location by country and postal code

  @happy
  Scenario Outline: Successful lookup with valid country code and postal code
    Given the country code is "<countryCode>"
    And the postal code is "<postalCode>"
    When I send a GET request to the Zippopotam API
    Then the response status code should be 200
    And the country name should be "Germany"
    And the place name should include "Köln"
    And the state should include "Nordrhein-Westfalen"
    Examples:
      | countryCode | postalCode |
      | de          | 51149      |
      | DE          | 51149      |

  @negative @invalid
  Scenario Outline: Failed lookup with invalid input
  Given the country code is "<countryCode>"
  And the postal code is "<postalCode>"
  When I send a GET request to the Zippopotam API
  Then the response status code should be 404

  Examples:
    | countryCode | postalCode  |
    #Special characters
    | @#          | 50667       |
    | ĐE          | 50667       |
    #Long input
    | DE          |9999999999999|
    #Missing country
     |            |  50667      |
#    Missing postal code
    | DE          |             |
    #Empty params
    |             |             |
    # Emoji
    | DE          | 🇩🇪          |

  @negative @security
  Scenario Outline: Failed lookup with encoded/malicious input
    Given the country code is "<countryCode>"
    And the postal code is "<postalCode>"
    When I send a malicious GET request to the Zippopotam API
    Then the response status code should be 404

    Examples:
      | countryCode | postalCode |
      # SQL Injection
      | DE          |' OR '1'='1 |
      # XSS attempt
      | DE          |<script>alert(1)</script>|
      # Null byte
      | \u0000    |   50667    |
