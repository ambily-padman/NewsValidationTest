
Feature: Validate Guardian News
  As a user I want ro verify the news listed in Guardian website is valid

  @test
  Scenario:Validate the Guardian news against google results
    Given I navigate to Guardian home page
    And I get the 1st headline news listed
    And I navigated to 'google' home page
    When I search the headline news in 'google'
    And I select the news tab in 'google' search result page
    And Get all the search 'google' results
    Then I can see the news is a valid news as of google results

  Scenario:Validate the Guardian news against bing results
    Given I navigate to Guardian home page
    And I get the 1st headline news listed
    And I navigated to 'bing' home page
    When I search the headline news in 'bing'
    And I select the news tab in 'bing' search result page
    And Get all the search 'bing' results
   Then I can see the news is a valid news as of bing results