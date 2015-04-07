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

import edu.comp.domain.AssignView;
import edu.comp.domain.Assignment;
//import edu.scs.carleton.comp.ls.admin.User;
import edu.comp.domain.Course;
import edu.comp.domain.Term;
//import edu.comp.domain.Term;
import edu.scs.carleton.comp.ls.dbam.DBLogger;
import edu.comp.dbam.DBAssignView;
import edu.comp.dbam.DBAssignment;
//import edu.scs.carleton.comp.ls.dbam.DBUser;
import edu.comp.dbam.DBCourse;
import edu.comp.dbam.DBStuAssign;
import edu.scs.carleton.comp.ls.view.beans.AssignViewBean;
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

public class AssignExamController extends Controller{
	public AssignExamController () {
		listOfObjects = new ArrayList<Bean>();
		selectedObjects = new HashMap<Object, Boolean>();
		
	}
	
	@PostConstruct
	public void init(){
		
		bean = new AssignmentBean();
		bean1=new StuAssignBean();
		
		FacesContext facesContext = FacesContext.getCurrentInstance(); 
		session = (HttpSession) facesContext.getExternalContext().getSession(true);
	}
	
	String assignName="";
	
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
		//show assignment of the course
		listOfObjects.clear();
		//dao = new DBAssignment();
		dao=new DBAssignView();
		String courseID=((AssignmentBean)bean).getCourseID();
		//setList(((DBAssignment)dao).findByCourseID(courseID));
		setList(((DBAssignView)dao).findGradeByCourseID(courseID));
		dao.destroy();
	}
	
	private void setList (List<Object> list) {
		
		listOfObjects.clear();
		
		/*if(list.size()!=0){
			for (Object o : list) {
			Assignment assign = (Assignment)o; 
			
			AssignmentBean aBean = new AssignmentBean();
			aBean.setAssignID(assign.getAssignID());
			aBean.setCourseID(assign.getCourseID());
			aBean.setDueDate(assign.getDueDate());
			aBean.setDescription(assign.getDescription());
			aBean.setAssignName(assign.getAssignName());
			aBean.setType(assign.getType());
			listOfObjects.add(aBean);
			}
		}*/
		if(list.size()!=0){
			for (Object o : list) {
			AssignView assign = (AssignView)o; 
			
			AssignViewBean aBean = new AssignViewBean();
			aBean.setAssignID(assign.getAssignID());
			aBean.setCourseID(assign.getCourseID());
			aBean.setDueDate(assign.getDueDate());
			aBean.setDescription(assign.getDescription());
			aBean.setAssignName(assign.getAssignName());
			aBean.setType(assign.getType());
			aBean.setGrade(assign.getGrade());
			listOfObjects.add(aBean);
			assignName=assign.getAssignName();
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
		
			if (event == IEvent.Assignment_UPLOAD) 
				//message = "Upload Assignment [" + ((AssignViewBean)bean).getAssignName() + "]";
				message = "Upload Assignment [" + assignName + "]";
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
	
	public void upload(){
		String content=((StuAssignBean)bean1).getContent();
		if(!content.isEmpty()){
			dao1=new DBStuAssign();
			for (Object o : selectedObjects.keySet()){
				int assignID=(Integer)o;
				if(selectedObjects.get(o)==true){
				int stuID=Integer.parseInt(session.getAttribute("username").toString());
				boolean resultStatus=((DBStuAssign)dao1).update(
					stuID,
					assignID,
					content);
				logMessage (resultStatus, IEvent.Assignment_UPLOAD);
				}
			}
		}
		else 
			logMessage (false, IEvent.EMPTY_CREATE);
		
		dao.destroy();
	}

	@Override
	protected String submit() {
		// TODO Auto-generated method stub
		return null;
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
