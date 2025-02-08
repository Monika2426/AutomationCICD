@tag
Feature: Purchase the order from Ecommerce website
  I want to use this template for my feature file
  
  Background:
  Given I landed on Ecommerce Page

  @Regression
  Scenario Outline: Positive Test of Submitting the order
    Given logged in with username <name> and password <password>
    When I add product <productName> to the cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

    Examples: 
      | name  												| password 				| productName  	|
      | rahulshettyacademy@gmail.com 	| IamKing@000 | BANARSI SAREE |
      
