Feature: student is able to login the system

Scenario: ST-03
	Given initialize the system
	When login as a current student stuNo="001", password="001"
	Then the title of web page is "welcome student"
	
