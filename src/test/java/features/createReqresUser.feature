Feature: Create REQ|RES User

  Scenario Outline: Create a new reqres user
    Given I have base url
    When  I POST the "createUser" endpoint "/api/users" with "<name>" and "<job>"
    Then  User should be created
    And   Response code should be 201
    Examples:
      | name  | job         |
      | Kiran | Team Lead   |
      | Rohit | Test Lead   |
      | John  | QA Engineer |