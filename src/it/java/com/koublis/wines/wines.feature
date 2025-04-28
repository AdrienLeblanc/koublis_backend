Feature: Ensure that the catalog is populated at startup

  Background:
    * call read('classpath:com/koublis/common/common.feature@user')

  Scenario: Perform CRUD operations on caves

    # Create cave
    Given url baseUrl + '/caves'
    And param cave-name = 'Test Cave'
    And header Authorization = 'Bearer ' + token
    When method post
    Then status 201
    And def caveId = response.id
    And match response.name == 'Test Cave'

    # Get cave by id
    Given url baseUrl + '/caves/' + caveId
    And header Authorization = 'Bearer ' + token
    When method get
    Then status 200
    And match response.id == caveId
    And match response.name == 'Test Cave'
    And match response.wines != null && response.wines.length == 0

    # Get all caves
    Given url baseUrl + '/caves'
    And header Authorization = 'Bearer ' + token
    When method get
    Then status 200
    And match response != null && response.length > 0
    And match response[0].id == caveId

    # Update cave
    Given url baseUrl + '/caves/' + caveId
    And param cave-name = 'Updated Cave'
    And header Authorization = 'Bearer ' + token
    When method put
    Then status 200
    And match response.id == caveId
    And match response.name == 'Updated Cave'
    And match response.wines != null && response.wines.length == 0

    # Delete cave
    Given url baseUrl + '/caves/' + caveId
    And header Authorization = 'Bearer ' + token
    When method delete
    Then status 204

  Scenario: Perform CRUD operations on wines

    # Create cave
    Given url baseUrl + '/caves'
    And param cave-name = 'Test Cave'
    And header Authorization = 'Bearer ' + token
    When method post
    Then status 201
    And def caveId = response.id
    And match response.name == 'Test Cave'

    # Create wine
    Given url baseUrl + '/caves/' + caveId + '/wines'
    And header Authorization = 'Bearer ' + token
    And request
      """
      {
        "count": 1,
        "name": "Test Wine",
        "vintage": 2025,
        "country": "Test country",
        "color": "Test color",
        "regions": ["Test region"],
        "classification": "Test classification",
        "primeur": true
      }
      """
    When method post
    Then status 201
    And def wineId = response.id
    And match response.count == 1
    And match response.name == 'Test Wine'
    And match response.vintage == 2025
    And match response.country == 'Test country'
    And match response.color == 'Test color'
    And match response.regions == ['Test region']
    And match response.classification == 'Test classification'
    And match response.primeur == true
    
    # Get wine by id
    Given url baseUrl + '/caves/' + caveId + '/wines/' + wineId
    And header Authorization = 'Bearer ' + token
    When method get
    Then status 200
    And match response.id == wineId
    And match response.count == 1
    And match response.name == 'Test Wine'
    And match response.vintage == 2025
    And match response.country == 'Test country'
    And match response.color == 'Test color'
    And match response.regions == ['Test region']
    And match response.classification == 'Test classification'
    And match response.primeur == true
    
    # Get all wines
    Given url baseUrl + '/caves/' + caveId + '/wines'
    And header Authorization = 'Bearer ' + token
    When method get
    Then status 200
    And match response != null && response.length > 0
    And match response[0].id == wineId
    And match response[0].count == 1
    And match response[0].name == 'Test Wine'
    And match response[0].vintage == 2025
    And match response[0].country == 'Test country'
    And match response[0].color == 'Test color'
    And match response[0].regions == ['Test region']
    And match response[0].classification == 'Test classification'
    And match response[0].primeur == true
    
    # Update wine
    Given url baseUrl + '/caves/' + caveId + '/wines/' + wineId
    And header Authorization = 'Bearer ' + token
    And request
      """
      {
        "count": 2,
        "name": "Updated Wine",
        "vintage": 2026,
        "country": "Updated country",
        "color": "Updated color",
        "regions": ["Updated region"],
        "classification": "Updated classification",
        "primeur": false
      }
      """
    When method put
    Then status 200
    And match response.id == wineId
    And match response.count == 2
    And match response.name == 'Updated Wine'
    And match response.vintage == 2026
    And match response.country == 'Updated country'
    And match response.color == 'Updated color'
    And match response.regions == ['Updated region']
    And match response.classification == 'Updated classification'
    And match response.primeur == false
    
    # Delete wine
    Given url baseUrl + '/caves/' + caveId + '/wines/' + wineId
    And header Authorization = 'Bearer ' + token
    When method delete
    Then status 204
    