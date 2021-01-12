Feature: WishList functionality

  Background:Browser Start and first steps
    Given "Open" browser
    And "Enter URL" browser
    And Click on following button in the "login" page
      | singInButton |
    And Enter the following values in the "login" page
      | username | Username1 |
      | password | Password1 |
    And Click on following button in the "login" page
      | loginBtn |

  @Smoke
  Scenario: Add remove wish list
    And Click on following button in the "LandingPage" page
      | WomenButtonMenu |

    And Click on following button in the "ItemsPage" page
      | Item3 |

    And Click on following button in the "AddToCart" page
      | addToCart         |
      | proceedToCheckout2|
      | proceedToCheckout |
      | proceedToCheckout |
      | checkBox          |
      | proceedToCheckout |
      | payByBankWire     |
      | IConfirmButton    |

    And Verify "ORDER CONFIRMATION" text is displayed in the "AddToCart" page
      | orderConfirmation |

    Then "quit" browser

