Feature: To test contact form works when there are no errors

  Scenario: Check form is validate
    Given I am on my zoo website
    When populate the contact form
    And I cick on the contact links
    Then I should be on the contact confirmation
