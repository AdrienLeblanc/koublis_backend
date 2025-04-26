Feature: Roles security

  Background:
    * call read('classpath:com/koublis/common/common.feature@user')

  Scenario: Can access unprotected REST endpoint with token
    Given url baseUrl + '/api/test'
    And path '/all'
    And header Authorization = 'Bearer ' + token
    When method get
    Then status 200

  Scenario: Can access USER protected REST endpoint with token
    Given url baseUrl + '/api/test'
    And path '/user'
    And header Authorization = 'Bearer ' + token
    When method get
    Then status 200

  Scenario: Cannot access MODERATOR protected REST endpoint with token
    Given url baseUrl + '/api/test'
    And path '/mod'
    And header Authorization = 'Bearer ' + token
    When method get
    Then status 403

  Scenario: Cannot access ADMIN protected REST endpoint with token
    Given url baseUrl + '/api/test'
    And path '/admin'
    And header Authorization = 'Bearer ' + token
    When method get
    Then status 403