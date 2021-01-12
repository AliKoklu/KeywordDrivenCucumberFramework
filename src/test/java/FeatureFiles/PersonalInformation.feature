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
      | myPersonalInformation |

    And Enter the following values in the "AddressPageAndPersonalInfo" page
      | firstname   | firstname2|
      | lastname    | lastname2|
      | oldPassword | Password1 |

    And Click on following button in the "AddressPageAndPersonalInfo" page
      | SaveButton |

    And Verify "Ffirst nname" text is displayed in the "LandingPage" page
      | nameButton |

    And Click on following button in the "LandingPage" page
      | nameButton |

    And Click on following button in the "LandingPage" page
      | myPersonalInformation |

    And Enter the following values in the "AddressPageAndPersonalInfo" page
      | firstname   | firstname1 |
      | lastname    | lastname1 |
      | oldPassword | Password1 |

    And Click on following button in the "AddressPageAndPersonalInfo" page
      | SaveButton |

    And Verify "Some one" text is displayed in the "LandingPage" page
      | nameButton |
    Then "quit" browser