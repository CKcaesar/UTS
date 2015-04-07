Feature: System creates a Term

Scenario: ST-05
	Given initialize a system, login as admin
	And goto "maintainTerms" page
	When create the term with name="test1", startDate="2015-01-01", endDate="2015-05-01"
	Then show "Create" "Term" "test1" success
	
Scenario: ST-06
	Given initialize a system, login as admin, create the term with name="test2", startDate="2015-05-02", endDate="2015-07-02"
	And show create Term "test2" success
	When create the term with name="test3", startDate="2015-06-01", endDate="2015-09-02"
	Then show create "Term" "test3" "Overlapping" fail