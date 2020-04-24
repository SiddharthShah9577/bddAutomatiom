Feature: To Create New User
  How to Create New User

  Scenario Outline: Create New User
    Given user launching the application '<testCaseId>'
    And user clickon signIn button
    When user fill All Details of Form
    Then user is able to login Successfully
    Examples:
    |testCaseId|
    |TC01|
    
    
 @21   
Scenario Outline: Sign In to the Application
    Given user launching the application '<testCaseId>'
      And user enter sign In Details
     When user select the products
     Then user able to checkout product
    Examples: 
      | testCaseId | 
      | TC01       | 