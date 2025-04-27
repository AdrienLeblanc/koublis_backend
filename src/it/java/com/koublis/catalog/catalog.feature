Feature: Ensure that the catalog is populated at startup

  Background:
    * call read('classpath:com/koublis/common/common.feature@user')

  Scenario: Can search through the catalog
    Given url baseUrl + '/catalog/wines'
    And path '/_search'
    And header Authorization = 'Bearer ' + token
    When method get
    Then status 200
    And match response != null && response.length > 0

  