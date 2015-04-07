Feature: student is able to register

Scenario: ST-01
	Given initialize a system, goto register page.
	When create the student account with firstname="AAA", lastname="AAA", birthDate="1991-05-24", school="COMP"
	Then return message username, password
	
Scenario: ST-02
	Given initialize a system, goto register page.
	When create the student account with firstname="AAA", lastname="AAA", birthDate="1991-05-24", school="COMP"
	Then return failed to create account