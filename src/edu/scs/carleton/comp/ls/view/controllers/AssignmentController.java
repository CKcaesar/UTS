package edu.scs.carleton.comp.ls.view.controllers;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import edu.comp.dbam.DAO;
import edu.comp.dbam.DBAssignment;
import edu.comp.dbam.DBStuAssign;
import edu.comp.dbam.DBStuCourse;
import edu.comp.domain.Assignment;
import edu.scs.carleton.comp.ls.dbam.DBLogger;
import edu.scs.carleton.comp.ls.view.beans.AssignmentBean;
import edu.scs.carleton.comp.ls.view.beans.Bean;

//import edu.scs.carleton.comp.ls.view.utils.IEvent;
import edu.scs.carleton.comp.ls.view.utils.IEvent;
import edu.scs.carleton.comp.ls.view.utils.Message;
@Singleton
@ManagedBean
@SessionScoped
public class AssignmentController extends Controller {
   public AssignmentController(){
	   listOfObjects = new ArrayList<Bean>();
	   selectedObjects = new HashMap<Object, Boolean>();
	  
   }
	@PostConstruct
   public void init(){
		bean = new AssignmentBean();
		FacesContext facesContext = FacesContext.getCurrentInstance(); 
		session = (HttpSession) facesContext.getExternalContext().getSession(true);
	}
	
	public Bean getBean() {
		return (AssignmentBean)bean;
	}

	
	public void setBean(Bean bean) {
		this.bean = (AssignmentBean)bean;
		
	}
    
	public String search() {
				
		listOfObjects.clear();
		dao = new DBAssignment();
		String courseID=((AssignmentBean)bean).getCourseID();
		setList(((DBAssignment)dao).findByCourseCode(courseID));
		dao.destroy();
		return null;		
		}
	
	public void setList(ArrayList<Object> list) {
		for (Object o : list) {
			Assignment assignment = (Assignment)o;
		    AssignmentBean aBean=new AssignmentBean();
		    aBean.setAssignID(assignment.getAssignID());
		    aBean.setCourseID(assignment.getCourseID());
		    aBean.setDueDate(assignment.getDueDate());
		    aBean.setDescription(assignment.getDescription());
		    aBean.setAssignName(assignment.getAssignName());
		    aBean.setType(assignment.getType());
		    listOfObjects.add(aBean);
		
		}
		if (!listOfObjects.isEmpty())
			bean = (AssignmentBean)listOfObjects.get(0);
		else
			logMessage (false, IEvent.EMPTY_SEARCH);
		selectedObjects.clear();
		this.render = !listOfObjects.isEmpty();
	}

	
	
	public String refresh () {		
		//return null;
		return submit();
	}
	public String submit() {
		
		return null;
	}

	public String addinfo()throws ParseException{
				
		if (!((AssignmentBean)bean).getDueDate().equals("") 
					&& !((AssignmentBean)bean).getDescription().equals("") 
					&& !((AssignmentBean)bean).getAssignName().equals(""))	
				{
							dao = new DBAssignment();
							
							boolean resultStatus=((DBAssignment)dao).create( 
									((AssignmentBean)bean).getDueDate(), 
									((AssignmentBean)bean).getCourseID(),
									((AssignmentBean)bean).getDescription(), 
									((AssignmentBean)bean).getAssignName(),
									((AssignmentBean)bean).getType());
							
							//If adding operation success, it should trigger other updates in table stuassign.
							/*if(resultStatus==true){
								dao1=new DBStuCourse();
								DAO dao2=new DBStuAssign();
								List<Object> student_list=((DBStuCourse)dao).findByCourseID(((AssignmentBean)bean).getCourseID());
							}*/
							
							dao.destroy();
							logMessage (resultStatus, IEvent.Assignment_CREATE);
							//DBLogger.getInstance().logEvent( (String)session.getAttribute("stuNo")
					                    //   , IEvent.Assignment_CREATE, "Created Assignment: " + ((AssignmentBean)bean).getAssignName()+ " [" + ((resultStatus) ? "SUCCESS" : "FAIL") + "]");
							refresh();
								bean.clear();
				}else{
							logMessage (false, IEvent.Assignment_CREATE);
							refresh();	
					}
		  		
					dao.destroy();
					refresh();
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


	protected void logMessage (boolean success, int event) {
		
		Message messages = (Message) session.getAttribute("messages");
		if (event == IEvent.Assignment_CREATE) 
			message = "Create Assignment [" + ((AssignmentBean)bean).getAssignName() + "]";
		else if (event == IEvent.EMPTY_SEARCH) 
			message = "No search results to display.";
		else if (event == IEvent.EMPTY_CREATE) 
			message = "Please fill in all fields.";
		messages.setMessage (success,message);	
		
		String logMessage=session.getAttribute("username").toString()+" "+message;
		writeToLog(logMessage);
	}

}
