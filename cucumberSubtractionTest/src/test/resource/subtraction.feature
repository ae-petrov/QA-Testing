Feature: As a user I want to subtract one number from another
  Scenario Outline: Two natural number subtraction
    Given I create a new subtract function
    When I add <a> and <b> to the function
    Then I get <result> as a result

  Examples:
    | a | b | result |
    | 10| 7 | 3      |
    | 15| 5 | 10     |
    | 7 | 3 | 4      |