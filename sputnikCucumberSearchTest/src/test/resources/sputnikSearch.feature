Feature: Search test on sputnik.ru
  Scenario Outline: User searches <phrase>
  //Given User opens the site // move step to @Before
    Given User clicks on input search field
    When User searches for <phrase>
    And User click search button
    Then User sees the list of results
    And <phrase> should be displayed

    Examples:
      | phrase            |
      | поисковик Спутник |
      | лучшие фото |