#Author: 

Feature: User is able to search valid disease with proper auto-suggestions

Scenario Outline: User should be able to enter any keyword of disease and proper results should display

Given I have opened the firefox browser and enter the URL
And page is loaded properly
When page is loaded properly, I search for search textbox in the homepage
And I provide proper "<keyword>" in the header searchbox and click Enter
Then I verify search results are displayed for the "<keyword>" and get the information
And I close the browser



Examples:
    | keyword|
		| asthma | 
		| cancer |