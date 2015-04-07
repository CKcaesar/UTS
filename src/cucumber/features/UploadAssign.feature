Feature: student upload the assignment

Scenario: ST-03
	Given initialize the system, login as stuNo="001", password="001"
	And goto "assignmentExam" page
	When Upload assignment of course "COMP5104" with content "content"
	Then show upload assignment success