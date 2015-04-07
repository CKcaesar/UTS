package edu.scs.carleton.comp.ls.view.controllers;

import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
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
import edu.comp.domain.Term;
//import edu.comp.domain.Term;
import edu.scs.carleton.comp.ls.dbam.DBLogger;
//import edu.scs.carleton.comp.ls.dbam.DBUser;
import edu.comp.dbam.DBCourse;
//import edu.comp.dbam.DBTerm;
import edu.scs.carleton.comp.ls.view.beans.Bean;
import edu.scs.carleton.comp.ls.view.beans.CourseBean; 
import edu.scs.carleton.comp.ls.view.beans.TermBean;
import edu.scs.carleton.comp.ls.view.managers.CacheManager;
import edu.scs.carleton.comp.ls.view.utils.IEvent;
import edu.scs.carleton.comp.ls.view.utils.Message;

@Singleton
@ManagedBean
@SessionScoped

public class CourseController extends Controller{
	public CourseController () {
		listOfObjects = new ArrayList<Bean>();
		selectedObjects = new HashMap<Object, Boolean>();
		
	}
	
	@PostConstruct
	public void init(){
		
		bean = new CourseBean();
		FacesContext facesContext = FacesContext.getCurrentInstance(); 
		session = (HttpSession) facesContext.getExternalContext().getSession(true);
	}
	
	boolean duplicatedcourses;
	
	//stringtime->integer
	public Integer trimChar(String str)throws ParseException{
		/*int index = str.indexOf(":");
		String newString =  str.substring(0,index) + str.substring(index+1,str.length());
		return Integer.parseInt(newString);*/
		String[] temp=str.split(":");
		String result="";
		for(int i=0;i<temp.length;i++){
			result=result+temp[i];
		}
		return Integer.parseInt(result);
	}
	
	//check the conflict during course creation
	public boolean courseCompare(List<Object> list, String location, String coursecode, String coursename, String time)throws ParseException{
		//boolean flag=true;
		
	    String[] b=time.split("-");
	    Integer begin = trimChar(b[0]);
	    Integer end = trimChar(b[1]);
	    for(Object o:list){
			Course course = (Course)o;
		    course.getTime();
			String[] t=course.getTime().split("-");
			Integer B=trimChar(t[0]);
			Integer E=trimChar(t[1]);
			if((((location).equals(course.getLocation()))
				&&(B<end&&E>begin))||
				((coursecode).equals(course.getCourseCode())||
				coursename.equals(course.getCourseName())
				))
				return false;
			    }
		
	    return true;
	}
	
	public Bean getBean () {
		return (CourseBean)bean;
	}
	
	public void setBean (Bean bean){
		this.bean = (CourseBean)bean;
	}
	
	public String create () throws ParseException {
		
		dao = new DBCourse();
		
		if (!((CourseBean)bean).getCourseName().equals("") && !((CourseBean)bean).getCourseCode().equals("")){
			if(courseCompare(((DBCourse)dao).findAll(),
					((CourseBean)bean).getLocation(),
					((CourseBean)bean).getCourseCode(),
					((CourseBean)bean).getCourseName(),
					((CourseBean)bean).getTime())){
			//dao = new DBCourse();
				boolean resultStatus = ((DBCourse)dao).create ( 
						((CourseBean)bean).getCourseCode(),
						((CourseBean)bean).getCourseName(),
						((CourseBean)bean).getTermid(),
						((CourseBean)bean).getMeetingTimes(),
						((CourseBean)bean).getTime(),
						((CourseBean)bean).getLocation());
				dao.destroy();
				
				if(resultStatus){
					CacheManager.resetCourses();
				}
				
				logMessage (resultStatus, IEvent.COURSE_CREATE);
				DBLogger.getInstance().logEvent((Integer)session.getAttribute("courseCode"), IEvent.COURSE_CREATE, "Course: " + ((CourseBean)bean).getCourseName() + " [" + ((resultStatus) ? "SUCCESS" : "FAIL") + "]");
			
				refresh();
			}else
			logMessage (false, IEvent.COURSE_CREATE);
		}
		else 
			logMessage (false, IEvent.EMPTY_CREATE);
		
		return "success";
	}
	
	public String refresh () {		
		return submit();
	}
	
	private void refreshCacheManager () 
	{
		CacheManager.resetCourses();
	}
	
	public String submit() {
		listOfObjects.clear();
		dao = new DBCourse();
		/*if (!((AuthorBean)bean).getFamilyName().isEmpty()) {
			dao = new DBAuthor();
			setList (((DBAuthor)dao).findByFamilyName(((AuthorBean)bean).getFamilyName()));
			dao.destroy();
			return null;
		}
		
		((AuthorBean)bean).clear();*/
		//setList(((DBCourse)dao).findAll());
		//bean=new CourseBean();
		setList(((DBCourse)dao).findByTermId(((CourseBean)bean).getTermid()));
		dao.destroy();
		return null;
	}
	
	private void setList (ArrayList<Object> list) {
		
		//listOfObjects.clear();
		
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
		/*if (listOfObjects.size() != 0) {
			//bean = listOfObjects.get(0);
		}*/
		else
			logMessage (false, IEvent.EMPTY_SEARCH);
		
		selectedObjects.clear();
		
		this.render = !listOfObjects.isEmpty();
	}
	
	public String delete  () {

		dao = new DBCourse();
		
		for (Object o : selectedObjects.keySet())
        {			
			String courseCode = (String)o;
			if(selectedObjects.get(o)==true){
				boolean resultStatus = ((DBCourse)dao).delete(courseCode);
				if(resultStatus){
					CacheManager.resetCourses();
				}			
				logMessage (resultStatus, IEvent.COURSE_DELETE);
				DBLogger.getInstance().logEvent( (Integer)session.getAttribute("courseCode")
										   , IEvent.COURSE_DELETE, "Deleted COURSE: " + ((CourseBean)bean).getCourseCode() + " [" + ((resultStatus) ? "SUCCESS" : "FAIL") + "]");
			}	 
        }		
		dao.destroy();	
		submit();
		//refresh();
		return "success";
	}
	
	protected void logMessage (boolean success, int event) {
			
			Message messages = (Message) session.getAttribute("messages");
		
			if (event == IEvent.COURSE_CREATE) 
				message = "Create Course [" + ((CourseBean)bean).getCourseCode() + "]";
			else if (event == IEvent.COURSE_DELETE) 
				message = "Delete Course [" + ((CourseBean)bean).getCourseCode() + "]";
			else if (event == IEvent.EMPTY_SEARCH) 
				message = "No search results to display.";
			else if (event == IEvent.EMPTY_CREATE) 
				message = "Please fill in all fields.";
	
			messages.setMessage (success,message);
			
			String logMessage=session.getAttribute("username").toString()+" "+message;
			writeToLog(logMessage);
		}
	}
