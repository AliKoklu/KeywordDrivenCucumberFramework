Feature: Address functionality

  Background:Browser Start and first steps
    Given "Open" browser
    And "Enter URL" browser
    And Click on following button in the "login" page
      |singInButton|
    And Enter the following values in the "login" page
      |username|Username1|
      |password|Password1|
    And Click on following button in the "login" page
      |loginBtn|

  @Smoke
  Scenario: Add remove address
    And Click on following button in the "LandingPage" page
      |myAddressesButton|

    And Click on following button in the "AddressPageAndPersonalInfo" page
      |addNewAddress|

    And Enter the following values in the "AddressPageAndPersonalInfo" page
      |address1|address1|
      |city|city|
      |postcode|postcode|
      |phone|phone|
      |addressTitle|addressTitle|

    And Handle dropdown in the "AddressPageAndPersonalInfo" page
      |State|State|

    And Click on following button in the "AddressPageAndPersonalInfo" page
      |SaveButton|

    And Click on the element according to other list "AddressPageAndPersonalInfo" page
    |address1TextInMyAddress|DeleteButton|address1|

    And Alert actions "Okay"

    Then Verify element is removed in the "AddressPageAndPersonalInfo" page
    |address1TextInMyAddress|address1|

    Then "quit" browser