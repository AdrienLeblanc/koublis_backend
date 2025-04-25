Feature: User sign-up and sign-in

  Scenario: Sign up as a new user
    #    Given url 'http://localhost:8080/auth/sign-up'
    Given url baseUrl + '/auth/sign-up'
    And request
      """
      {
        "username": "testuser",
        "password": "testpassword",
        "email": "testemail",
        "roles": ["ROLE_USER"]
      }
      """
    When method post
    Then status 200

  Scenario: Sign in as a new user
    Given url baseUrl + '/auth/sign-in'
    And request
      """
      {
        "username": "testuser",
        "password": "testpassword"
      }
      """
    When method post
    Then status 200

  Scenario: Fail sign up with existing username
    Given url baseUrl + '/auth/sign-up'
    And request
      """
      {
        "username": "testuser",
        "password": "testpassword",
        "email": "testemail",
        "roles": ["ROLE_USER"]
      }
      """
    When method post
    Then status 400

  Scenario: Fail sign in with wrong password
    Given url baseUrl + '/auth/sign-in'
    And request
      """
      {
        "username": "testuser",
        "password": "wrongpassword"
      }
      """
    When method post
    Then status 401