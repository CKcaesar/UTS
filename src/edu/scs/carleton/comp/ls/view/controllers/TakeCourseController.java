package edu.scs.carleton.comp.ls.view.controllers;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;






//import edu.scs.carleton.comp.ls.admin.User;
import edu.comp.domain.Course;
import edu.comp.domain.StuCourse;
//import edu.comp.domain.Term;
import edu.scs.carleton.comp.ls.dbam.DBLogger;
//import edu.scs.carleton.comp.ls.dbam.DBUser;
import edu.comp.dbam.DBCourse;
import edu.comp.dbam.DBStuCourse;
import edu.scs.carleton.comp.ls.view.beans.AssignmentBean;
//import edu.comp.dbam.DBTerm;
import edu.scs.carleton.comp.ls.view.beans.Bean;
import edu.scs.carleton.comp.ls.view.beans.CourseBean;
import edu.scs.carleton.comp.ls.view.beans.StuCourseBean;
//import edu.scs.carleton.comp.ls.view.beans.TermBean;
import edu.scs.carleton.comp.ls.view.managers.CacheManager;
import edu.scs.carleton.comp.ls.view.utils.IEvent;
import edu.scs.carleton.comp.ls.view.utils.Message;

@Singleton
@ManagedBean
@SessionScoped
public class TakeCourseController extends Controller{
	public TakeCourseController () {
		listOfObjects = new ArrayList<Bean>();
		selectedObjects = new HashMap<Object, Boolean>();
				
		myListOfObjects=new ArrayList<Bean>();
		mySelectedObjects=new HashMap<Object, Boolean>();
	}
	
	@PostConstruct
	public void init(){
		bean = new CourseBean();
		bean1 = new StuCourseBean();
		
		FacesContext facesContext = FacesContext.getCurrentInstance(); 
		session = (HttpSession) facesContext.getExternalContext().getSession(true);
		
	}
	
	//String stuNo=session.getAttribute("username").toString();
	
	public Bean getBean () {
		return (CourseBean)bean;
	}
	
	public Bean getBean1 () {
		return (StuCourseBean)bean1;
	}
	
	public void setBean (Bean bean){
		this.bean = (CourseBean)bean;
	}
	
	public void setBean1 (Bean bean1){
		this.bean1 = (StuCourseBean)bean1;
	}
	
	public String create () {
		
		dao1 = new DBStuCourse();
		//System.out.println(selectedObjects.size());
		for (Object o : selectedObjects.keySet())
        {			
			String courseCode = (String)o;
			if(selectedObjects.get(o)==true){
				boolean resultStatus = ((DBStuCourse)dao1).create ( 
					session.getAttribute("username").toString(),
					courseCode,
					null,
					((StuCourseBean)bean1).getTermName());
				
				if(resultStatus){
					CacheManager.resetMyCourses();
				}
			
			logMessage (resultStatus, IEvent.COURSE_CREATE);
			DBLogger.getInstance().logEvent((Integer)session.getAttribute("courseCode"), IEvent.COURSE_CREATE, "Course: " + ((StuCourseBean)bean1).getCourse() + " [" + ((resultStatus) ? "SUCCESS" : "FAIL") + "]");
		
			//refresh();
			}
			
		}
		dao1.destroy();
		refresh();
		return "success";
	}
	
	public void showCourse(){
		//show course of the term
		listOfObjects.clear();
		dao = new DBCourse();
		int termID=((StuCourseBean)bean1).getTermid();
		//setList(((DBCourse)dao).findByTermId(termID));
		setList(((DBCourse)dao).findAll());
		dao.destroy();
		
		//show my course
		myListOfObjects.clear();
		dao1=new DBStuCourse();
		try{
			//setMyList(((DBStuCourse)dao1).findByTermId(termID));
			setMyList(((DBStuCourse)dao1).findAll());}
		 catch(NullPointerException e){
			e.printStackTrace();
		}
		
		dao1.destroy();
	}
	
	public void addCourse(){
		create ();
		submit();
	}
	
	public void dropCourse(){
		delete();
	}
	
	public String refresh () {		
		//return null;
		return submit();
	}
	
	public String submit() {
		/*if (!((AuthorBean)bean).getFamilyName().isEmpty()) {
			dao = new DBAuthor();
			setList (((DBAuthor)dao).findByFamilyName(((AuthorBean)bean).getFamilyName()));
			dao.destroy();
			return null;
		}
		
		((AuthorBean)bean).clear();*/
		showCourse();
		return null;
	}
	
	private void setList (List<Object> list) {
		
		if(list.size()!=0){
			for (Object o : list) {
			Course course = (Course)o; 
			
			CourseBean cBean = new CourseBean();
			cBean.setCourseCode(course.getCourseCode());
			cBean.setCourseName(course.getCourseName());
			cBean.setTermid(course.getTermid());
			cBean.setMeetingTimes(course.getMeetingTimes());
			cBean.setTime(course.getTime());
			cBean.setLocation(course.getLocation());
			listOfObjects.add(cBean);
			}
		}
		if (listOfObjects.size() != 0) {
			bean = listOfObjects.get(0);
		}
		else
			logMessage (false, IEvent.EMPTY_SEARCH);
		
		selectedObjects.clear();		
		this.render = !listOfObjects.isEmpty();
	}
	
	private void setMyList (List<Object> list) {
		
		if(list.size()!=0){
			for (Object o : list) {
			StuCourse stucourse = (StuCourse)o; 
			
			StuCourseBean stucBean = new StuCourseBean();
			stucBean.setStuNo(stucourse.getStuNo());
			stucBean.setCourse(stucourse.getCourse());
			stucBean.setRegisterDate(stucourse.getRegisterDate());
			stucBean.setTermName(stucourse.getTermName());
			myListOfObjects.add(stucBean);
		}
		}
		
		//System.out.println(list.size());
		if (myListOfObjects.size() != 0) {
			bean1 = myListOfObjects.get(0);
			
		}
		else
			logMessage (false, IEvent.EMPTY_SEARCH);
		
		mySelectedObjects.clear();		
		this.render = !myListOfObjects.isEmpty();
	}
	
	public String delete  () {

		dao1 = new DBStuCourse();
		
		for (Object o : mySelectedObjects.keySet())
        {			
			String courseCode = (String)o;
			String stuNo=session.getAttribute("username").toString();
			if(mySelectedObjects.get(o)==true){
				boolean resultStatus = ((DBStuCourse)dao1).delete(courseCode, stuNo);
				if(resultStatus){
					CacheManager.resetMyCourses();
				}
				
				logMessage (resultStatus, IEvent.COURSE_DELETE);
				DBLogger.getInstance().logEvent( (Integer)session.getAttribute("userNo")
										   , IEvent.COURSE_DELETE, "Deleted COURSE: " + ((StuCourseBean)bean1).getCourse() + " [" + ((resultStatus) ? "SUCCESS" : "FAIL") + "]");
			}
			 
        }		
		dao.destroy();
		refresh();
		return "success";
	}
	
	protected void logMessage (boolean success, int event) {
		
		Message messages = (Message) session.getAttribute("messages");
	
		if (event == IEvent.COURSE_CREATE) 
			message = "Add Course [" + ((CourseBean)bean).getCourseCode() + "]";
		else if (event == IEvent.COURSE_DELETE) 
			message = "Drop Course [" + session.getAttribute("courseCode") + "]";
		else if (event == IEvent.EMPTY_SEARCH) 
			message = "No search results to display.";
		else if (event == IEvent.EMPTY_CREATE) 
			message = "Please fill in all fields.";

		messages.setMessage (success,message);
		
		String logMessage=session.getAttribute("username").toString()+" "+message;
		writeToLog(logMessage);
	}
}
