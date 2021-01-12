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
  Scenario Outline: Add remove wish list
    And Click on following button in the "LandingPage" page
      | WomenButtonMenu |

    And Click on following button in the "ItemsPage" page
      | <Items>          |
      | wishlistButton   |
      | titleCloseButton |

    And Click on following button in the "LandingPage" page
      | nameButton       |
      | myWishListButton |

    Then Verify "MY WISHLISTS" text is displayed in the "LandingPage" page
      | wishListText |

    And Click on the "WishListDeleteButton" in the "LandingPage" page

    Then Verify "WishListDeleteButton" element count is 0 in the "LandingPage" page

    Then "quit" browser

    Examples:
      | Items |
      | Item1 |
      | Item2 |
      | Item3 |

