Feature: Ensure that the catalog is populated at startup

  Background:
    * call read('classpath:com/koublis/common/common.feature@user')

  Scenario: Can search 'red' through the catalog
    Given url baseUrl + '/catalog/wines'
    And path '/_search'
    And param query = 'red'
    And header Authorization = 'Bearer ' + token
    When method get
    Then status 200
    And match response != null && response.length == 2

  Scenario: Can search 'California' through the catalog
    Given url baseUrl + '/catalog/wines'
    And path '/_search'
    And param query = 'California'
    And header Authorization = 'Bearer ' + token
    When method get
    Then status 200
    And match response != null && response.length == 1
    And match response.content[0].title == 'Caymus 1998 Cabernet Sauvignon (Napa Valley)'

  Scenario: Can search 'France' through the catalog
    Given url baseUrl + '/catalog/wines'
    And path '/_search'
    And param query = 'France'
    And header Authorization = 'Bearer ' + token
    When method get
    Then status 200
    And match response != null && response.length == 1
    And match response.content[0].title == 'M. Chapoutier 1999 Le Méal Ermitage  (Hermitage)'

  Scenario: Can search with fuzziness through the catalog
    Given url baseUrl + '/catalog/wines'
    And path '/_search'
    And param query = 'Frqnce'
    And header Authorization = 'Bearer ' + token
    When method get
    Then status 200
    And match response != null && response.length == 1
    And match response.content[0].title == 'M. Chapoutier 1999 Le Méal Ermitage  (Hermitage)'