package edu.scs.carleton.comp.ls.view.controllers;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import edu.comp.domain.Assignment;
//import edu.scs.carleton.comp.ls.admin.User;
import edu.comp.domain.Course;
import edu.comp.domain.StuAssign;
import edu.comp.domain.Term;
//import edu.comp.domain.Term;
import edu.scs.carleton.comp.ls.dbam.DBLogger;
import edu.comp.dbam.DBAssignment;
//import edu.scs.carleton.comp.ls.dbam.DBUser;
import edu.comp.dbam.DBCourse;
import edu.comp.dbam.DBStuAssign;
import edu.scs.carleton.comp.ls.view.beans.AssignmentBean;
//import edu.comp.dbam.DBTerm;
import edu.scs.carleton.comp.ls.view.beans.Bean;
import edu.scs.carleton.comp.ls.view.beans.CourseBean; 
import edu.scs.carleton.comp.ls.view.beans.StuAssignBean;
import edu.scs.carleton.comp.ls.view.beans.StuCourseBean;
import edu.scs.carleton.comp.ls.view.beans.TermBean;
import edu.scs.carleton.comp.ls.view.managers.CacheManager;
import edu.scs.carleton.comp.ls.view.utils.IEvent;
import edu.scs.carleton.comp.ls.view.utils.Message;

@Singleton
@ManagedBean
@SessionScoped

public class GradingController extends Controller{
	public GradingController () {
		listOfObjects = new ArrayList<Bean>();
		selectedObjects = new HashMap<Object, Boolean>();
		
	}
	
	@PostConstruct
	public void init(){
		bean=new AssignmentBean();
		bean1=new StuAssignBean();
		
		FacesContext facesContext = FacesContext.getCurrentInstance(); 
		session = (HttpSession) facesContext.getExternalContext().getSession(true);
	}
	
	public Bean getBean () {
		return (AssignmentBean)bean;
	}
	
	public Bean getBean1(){
		return (StuAssignBean)bean1;
	}
	
	public void setBean (Bean bean){
		this.bean = (AssignmentBean)bean;
	}
	
	public void setBean1 (Bean bean1){
		this.bean1=(StuAssignBean)bean1;
	}
	
	public void search(){
		//show stuassignment of the course
		listOfObjects.clear();
		dao = new DBStuAssign();
		String courseID=((AssignmentBean)bean).getCourseID();
		//setList(((DBStuAssign)dao).findAll());
		setList(((DBStuAssign)dao).findStuAssignByCourseID(courseID));
		dao.destroy();
	}
	
	private void setList (List<Object> list) {
		
		listOfObjects.clear();
		
		if(list.size()!=0){
			for (Object o : list) {
			StuAssign stuassign = (StuAssign)o; 
			
			StuAssignBean sBean = new StuAssignBean();
			sBean.setStuAssignmentID(stuassign.getStuAssignmentID());
			sBean.setAssignID(stuassign.getAssignID());
			sBean.setSubmitDate(stuassign.getSubmitDate());
			sBean.setContent(stuassign.getContent());
			sBean.setGrade(stuassign.getGrade());
			listOfObjects.add(sBean);
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
	
	protected void logMessage (boolean success, int event) {
			
			Message messages = (Message) session.getAttribute("messages");
		
			if (event == IEvent.GRADING) 
				message = "Grading ["+((AssignmentBean)bean).getCourseID() +"]";
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

	@Override
	public String submit() {
		String grade=((StuAssignBean)bean1).getGrade();
		if(!grade.isEmpty()){
			dao=new DBStuAssign();
			for (Object o : selectedObjects.keySet()){
				int stuAssignID=(Integer)o;
				if(selectedObjects.get(o)==true){
				boolean resultStatus=((DBStuAssign)dao).updateGrade(
					stuAssignID,
					grade);
				logMessage (resultStatus, IEvent.GRADING);
				}
			}
		}
		else 
			logMessage (false, IEvent.EMPTY_CREATE);
		
		dao.destroy();
		return "success";
	}

	@Override
	protected String create() throws ParseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String delete() {
		// TODO Auto-generated method stub
		return null;
	}
	}

