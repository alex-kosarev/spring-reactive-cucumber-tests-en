# language: en
Feature: contacts list

  Scenario: client requests contacts list
    Given contacts stored in the storage:
      | email                    | name         |
      | jack.daniels@example.com | Jack Daniels |
      | jim.beam@example.com     | Jim Beam     |
      | john.dewar@example.com   | John Dewar   |
    When client sends GET "/contacts"
    Then service should return response with status 200
    Then response body should be compatible with "application/json"
    Then response body should be an array and contain 3 entries and should be documented as "findAll"