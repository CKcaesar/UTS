Feature: admin post assignments on UTS

Scenario: add an assignment on UTS
	Given initialize a system, login as admin
	And goto "maintainAssignments" page
	When create the assignment of course="OOSystemDev" with dueDate="2014-12-08", description="xxxxxx", name="assignTest2", type="assignment"
	Then show "Create" Assignment "assignTest2" success