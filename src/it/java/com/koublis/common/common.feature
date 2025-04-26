Feature: Authentication

  Background:
    * def uuid = function() { return java.util.UUID.randomUUID().toString() }

  @user
  Scenario: User authentication
    Given url baseUrl + "/auth/sign-up"
    And def username = "user_" + uuid()
    And def email = "email_" + uuid().substring(0, 8) + "@test.com"
    And request
      """
      {
        "username": "#(username)",
        "password": "testpassword",
        "email": "#(email)",
        "role": "ROLE_USER"
      }
      """
    * print "Registering user with username: " + username
    When method post
    Then status 200

    Given url baseUrl + "/auth/sign-in"
    And request { username: "#(username)", password: "testpassword" }
    * print "Signing in user with username: " + username
    When method post
    Then status 200
    And print response
    * def token = response.token

  @moderator
  Scenario: Moderator authentication
    Given url baseUrl + "/auth/sign-up"
    And def username = "moderator_" + uuid()
    And def email = "email_" + uuid().substring(0, 8) + "@test.com"
    And request
      """
      {
        "username": "#(username)",
        "password": "testpassword",
        "email": "#(email)",
        "role": "ROLE_MODERATOR"
      }
      """
    * print "Registering user with username: " + username
    When method post
    Then status 200

    Given url baseUrl + "/auth/sign-in"
    And request { username: "#(username)", password: "testpassword" }
    * print "Signing in user with username: " + username
    When method post
    Then status 200
    And print response
    * def token = response.token

  @admin
  Scenario: Admin authentication
    Given url baseUrl + "/auth/sign-up"
    And def username = "admin_" + uuid()
    And def email = "email_" + uuid().substring(0, 8) + "@test.com"
    And request
      """
      {
        "username": "#(username)",
        "password": "testpassword",
        "email": "#(email)",
        "role": "ROLE_ADMIN"
      }
      """
    * print "Registering user with username: " + username
    When method post
    Then status 200

    Given url baseUrl + "/auth/sign-in"
    And request { username: "#(username)", password: "testpassword" }
    * print "Signing in user with username: " + username
    When method post
    Then status 200
    And print response
    * def token = response.token