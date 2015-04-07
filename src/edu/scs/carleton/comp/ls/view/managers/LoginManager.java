package edu.scs.carleton.comp.ls.view.managers;

import java.util.ArrayList;

import javax.ejb.Singleton;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import edu.comp.dbam.DAO;
//import edu.comp.dbam.DBTerm;
//import edu.scs.carleton.comp.ls.dbam.DBUser;
import edu.comp.dbam.DBUser;
//import edu.comp.domain.Term;
//import edu.scs.carleton.comp.ls.admin.Role;
//import edu.scs.carleton.comp.ls.admin.User;
import edu.comp.domain.User;
import edu.scs.carleton.comp.ls.view.beans.BakedBean;
import edu.scs.carleton.comp.ls.view.utils.Message;


@Singleton
@ManagedBean
@RequestScoped
public class LoginManager {

	private String message = "";
	private String stuNo;
	private String password;
	
	private HttpSession session;
	protected DAO dao;
	public LoginManager () { }

	public final String getMessage() {
		return message;
	}

	public final String getStuNo() {
		return stuNo;
	}

	public final String getPassword() {
		return password;
	}
	
	public final void setStuNo(String stuNo) {
		this.stuNo = stuNo;
	}

	public final void setPassword(String password) {
		this.password = password;
	}

	public String login () {
		clearErrorMessages();
		
		DBUser dbUser = new DBUser();
		User user = (User) dbUser.findByPrimaryKey(stuNo);
		
		if (user == null) {
			message = "Invalid Username. Please try again.";	
			return "fail";
		}		
		
		if (!password.equals(user.getPassword())) {						
			message = "Invalid Password. Please try again.";			    
			return "fail";
		}

		message = null;
		
		(new BakedBean()).bakeBeans();
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		session = (HttpSession) facesContext.getExternalContext().getSession(true);
		
		session.setAttribute("userid", user.getStuID());
		session.setAttribute("username", user.getStuNo());
		//session.setAttribute("roleid", user.getRole().getRoleId());
		
/*		if (user.getRole().getRoleId() == Role.LIBRARIAN) {
			session.setAttribute("librarian", true);
			return "librarian";
		}*/
		if (user.getStuNo().equals("admin")){
			session.setAttribute("admin",true);
			return "librarian";
		}
			
		session.setAttribute("admin", false);
		
		return "public-member";
	}
	
	public String register(){
		return "newcomer";
	}
	
	public String logOut(){
		return "fail";
	}
	
	private void clearErrorMessages () {
		try {
			Message messages = (Message) session.getAttribute("messages");
			messages.clear();
		} catch ( Exception e) {
			//Debug.trace(this,"clearErrorMessagers",e.getLocalizedMessage());
		}
	}
	
	public void finalize () throws Throwable {
		super.finalize();
	}
	
}
