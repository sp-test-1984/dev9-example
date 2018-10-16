Feature: enter a search in google
   Scenario Outline: Search results should display the search term in the title regardless of submission method
      Given A Google search page
      When I enter the search term "<search_term>"
      And I submit the search by pressing "<submission_method>"
      Then The search result page title should contain the search term

   Examples: Term and submission method combinations
       | search_term | submission_method |
       | cats        | enter             |
       | dogs        | search            |
