Feature: set attributes of the term

Scenario: ST-12
  Given initialize a system, login as admin
  And goto "maintainTerms" page
  And create "Term B" with startDate "2014-09-06" and endDate "2014-12-08"
  When set "Term B" with enrollStart "2014-08-01" and enrollEnd "2014-09-30"
  Then show "Set" "Term" "Term B" success