Feature: the constraints between term date and course date

Scenario: ST-10
	Given initialize a system, login as admin
	And goto "maintainTerms" page
	When create the term with name="test4", startDate="2015-05-01", endDate="2015-08-01"
	Then show "Create" "Term" "test4" success
	
Scenario: ST-11
	Given initialize a system, login as admin
	And set term "Term A" enrolldate to"2015-01-01"
	When click update button
	Then update failed "invalid date"