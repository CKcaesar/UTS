System tests are implemented from browser side.

US-02.ST-04 not implemented;
US-04.ST-07 not implemented;
US-06.ST-11 not implemented;

I consider the relationship among startDate, endDate, enrollStart, enrollEnd dropdeadline. all these attributes are set
in the table term. So when a course is created in certain term, the time related attributed extend from this term.
So i don't need to implement US-07, US-08, US-09 (including ST-10, ST-11, ST-12, ST-13, ST-14, US-15). Just be
careful to set the times of the term.
In turn, I wrote a method to validate the five time attributes of the term.
Only under the following condition that the time setting is reasonable:
atartDate.before(enrollStart)&&startDate.before(enrollEnd)
	&&enrollStart.before(enrollEnd)
		&&enrollEnd.before(dropDeadline)
			&&dropDeadline.before(endDate)
			
   
   
  I have implemented most of functionalities such as term, course and assignment operations, admin and common student
  access to the system data and service.
  Unfortunately, my droplists as well as data table components crash after i cleaned the browser session yesterday. 
  I spent much time fixing it but in vain. the function of assignment part is imcomplete and I will make it up as soon
  as posssible. 
  I run several cucumber selenium tests to test the system on front side and some validation functions still need to be done.
  
  test account:
  admin username: admin
        password: admin
         
  commmon username: 001
          password: 001 	       
        
  all the cucumber feature files are included in package cucumber.features.
	