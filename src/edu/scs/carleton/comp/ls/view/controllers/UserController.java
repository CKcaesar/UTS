package edu.scs.carleton.comp.ls.view.controllers;

import java.io.FileWriter;
import java.io.IOException;
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





import edu.comp.domain.Term;
//import edu.scs.carleton.comp.ls.admin.User;
import edu.comp.domain.User;
import edu.scs.carleton.comp.ls.dbam.DBLogger;
import edu.comp.dbam.DBTerm;
//import edu.scs.carleton.comp.ls.dbam.DBUser;
import edu.comp.dbam.DBUser;
import edu.scs.carleton.comp.ls.view.beans.Bean;
import edu.scs.carleton.comp.ls.view.beans.TermBean;
import edu.scs.carleton.comp.ls.view.beans.UserBean;
import edu.scs.carleton.comp.ls.view.utils.IEvent;
import edu.scs.carleton.comp.ls.view.utils.Message;

@Singleton
@ManagedBean
@SessionScoped
public class UserController extends Controller implements IEvent {
	
	String message=null;
	int mysql_affected_rows=0;
	
	public UserController () {
		listOfObjects = new ArrayList<Bean>();
		selectedObjects = new HashMap<Object, Boolean>();	
	}
	
	@PostConstruct
	public void init() {
		bean = new UserBean();
		//((UserBean)bean).setRoleId(300);
		FacesContext facesContext = FacesContext.getCurrentInstance(); 
		session = (HttpSession) facesContext.getExternalContext().getSession(true);
	}
	
	boolean notRegisterTwice=true;
	
	public Bean getBean () {
		return (UserBean)bean;
	}
	
	public void setBean (Bean bean) {
		this.bean = (UserBean)bean;
	}
	
	public boolean userValidate(List<Object> list, String x, String y, String z) throws ParseException{
		
		String firstname=x;
		String lastname=y;
		String birthdate=z;
		//boolean flag=true;
		for(Object o:list){
			User user = (User)o;
			
			String fn=user.getFirstname();
			String ln=user.getLastname();
			String bd=user.getBirthdate();
			if(firstname.equals(fn)&&(lastname.equals(ln))&&birthdate.equals(bd)){
				notRegisterTwice=false;
			}
		}
		return notRegisterTwice;
	}
	
	public String create () throws ParseException 
	{	
		dao=new DBUser();
		
		if (!((UserBean)bean).getFirstname().equals("") 
		&& !((UserBean)bean).getLastname().equals("") 
		&& !((UserBean)bean).getBirthdate().equals("") 
		&& !((UserBean)bean).getSchool().equals("")) {
			
			if(userValidate(
					((DBUser)dao).findAll(), 
					((UserBean)bean).getFirstname(), 
					((UserBean)bean).getLastname(),
					((UserBean)bean).getBirthdate())){
			
			String stuNo="stu"+(int)(Math.random()*10000);
			String password="ps"+(int)(Math.random()*1000);
			String middleinitial=null;
			String degree=null;
			String time_status=null;
			message="Please remeber carefully! studentNo:"+stuNo+"  "+"password:"+password;
			
			dao = new DBUser();
			boolean resultStatus = ((DBUser)dao).create ( 
				stuNo
				,password
				,((UserBean)bean).getFirstname()
				,((UserBean)bean).getLastname()
				,middleinitial
				,((UserBean)bean).getBirthdate()
				,((UserBean)bean).getSchool()
				,degree
				,time_status);
			dao.destroy();
			Message ms=(Message) session.getAttribute("messages");
			ms.setFeedback(stuNo, password);
			
			logMessage (resultStatus, IEvent.USER_CREATE);
			DBLogger.getInstance().logEvent( (Integer)session.getAttribute("userid")
					                       , IEvent.USER_CREATE, "Created User: " + ((UserBean)bean).getFirstname()+" "+ ((UserBean)bean).getLastname() + " [" + ((resultStatus) ? "SUCCESS" : "FAIL") + "]");			
			}else{
				logMessage (false, IEvent.USER_CREATE);
			}
			
			
		}
		else 
			logMessage (false, IEvent.EMPTY_CREATE);
		
		return "success";
	}
	
	public String refresh () {		
		return null;
	}
	
	public String submit() {
		
		return null;
	}
	
	/*private void setList (List<Object> list) {
		
		for (Object o : list) {
			User user = (User)o; 
			
			UserBean uBean = new UserBean();
			uBean.setUserId (user.getUserId());
			uBean.setRoleId (user.getRole().getRoleId());
			uBean.setRoleName (user.getRole().getName());
			uBean.setUserName (user.getUsername());
			uBean.setPassword (user.getPassword());
			
			uBean.setEmail (user.getEmail());
			uBean.setFamilyName (user.getFamilyName());
			uBean.setGivenName (user.getGivenName());
			uBean.setMiddleInitial (user.getMiddleInitial());
			
			listOfObjects.add(uBean);
		}
		
		if (listOfObjects.size() != 0) {
			bean = listOfObjects.get(0);
		}
		else
			logMessage (false, IEvent.EMPTY_SEARCH);
		
		selectedObjects.clear();		
		this.render = !listOfObjects.isEmpty();
	}*/
	
	public String delete  () {

/*		dao = new DBUser();
		
		for (Object o : selectedObjects.keySet())
        {			
			String userName = (String)o;
			boolean resultStatus = ((DBUser)dao).delete(userName);
			logMessage (resultStatus, IEvent.USER_DELETE);
			DBLogger.getInstance().logEvent( (Integer)session.getAttribute("userid")
										   , IEvent.USER_DELETE, "Deleted User: " + ((UserBean)bean).getUserName() + " [" + ((resultStatus) ? "SUCCESS" : "FAIL") + "]"); 
        }
		
		dao.destroy();
		
		refresh();
		return "success";*/
		return null;
	}

	protected void logMessage (boolean success, int event) {
		
		Message messages = (Message) session.getAttribute("messages");
	
		if ((event == IEvent.USER_CREATE)&&notRegisterTwice) 
			message = "Create User [" + ((UserBean)bean).getFirstname() + " "+ ((UserBean)bean).getLastname()+"]";
		else if ((event == IEvent.USER_CREATE)&&!notRegisterTwice)
			message = "Create User [" + ((UserBean)bean).getFirstname() + " "+ ((UserBean)bean).getLastname()+"] register twice" ;
		else if (event == IEvent.USER_DELETE)
			message = "Delete User [" + ((UserBean)bean).getFirstname() + " "+ ((UserBean)bean).getLastname()+"]";
		else if (event == IEvent.EMPTY_SEARCH) 
			message = "No search results to display.";
		else if (event == IEvent.EMPTY_CREATE) 
			message = "Please fill in all fields.";

		messages.setMessage (success,message);
		
		String logMessage=session.getAttribute("username").toString()+" "+message+" affected_rows:"+mysql_affected_rows;
		writeToLog(logMessage);
	}
		
}