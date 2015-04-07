/*package edu.scs.carleton.comp.ls.view.controllers;

import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import edu.scs.carleton.comp.ls.book.Author;
import edu.scs.carleton.comp.ls.dbam.DBAuthor;
import edu.scs.carleton.comp.ls.dbam.DBLogger;
import edu.scs.carleton.comp.ls.view.beans.AuthorBean;
import edu.scs.carleton.comp.ls.view.beans.Bean;
import edu.scs.carleton.comp.ls.view.managers.CacheManager;
import edu.scs.carleton.comp.ls.view.utils.IEvent;
import edu.scs.carleton.comp.ls.view.utils.Message;

@Singleton
@ManagedBean
@SessionScoped
public class AuthorController extends Controller {

	public AuthorController () {
		listOfObjects = new ArrayList<Bean>();
		selectedObjects = new HashMap<Object, Boolean>();	
	}
	
	@PostConstruct
	public void init(){
		bean = new AuthorBean();
		FacesContext facesContext = FacesContext.getCurrentInstance(); 
		session = (HttpSession) facesContext.getExternalContext().getSession(true);
	}
	
	public Bean getBean () {
		return (AuthorBean)bean;
	}
	
	public void setBean (Bean bean){
		this.bean = (AuthorBean)bean;
	}
	
	public String submit() {
		listOfObjects.clear();
		
		if (!((AuthorBean)bean).getFamilyName().isEmpty()) {
			dao = new DBAuthor();
			setList (((DBAuthor)dao).findByFamilyName(((AuthorBean)bean).getFamilyName()));
			dao.destroy();
			return null;
		}
		
		((AuthorBean)bean).clear();
		return null;
	}
	
	private void setList (ArrayList<Object> list) {

		for (Object o : list) {
			Author author = (Author)o;
			AuthorBean aBean = new AuthorBean();
			aBean.setAuthorId(author.getAuthorID());
			aBean.setFamilyName (author.getFamilyName());
			aBean.setGivenName (author.getGivenName());
	
			listOfObjects.add(aBean);
		}
		
		if (!listOfObjects.isEmpty())
			bean = (AuthorBean)listOfObjects.get(0);
		else
			logMessage (false, IEvent.EMPTY_SEARCH);

		selectedObjects.clear();
		this.render = !listOfObjects.isEmpty();
	}
	
	public String create () {
		
		if (!((AuthorBean)bean).getFamilyName().equals("") && !((AuthorBean)bean).getGivenName().equals("")){
			dao = new DBAuthor();
			boolean resultStatus = ((DBAuthor)dao).create ( ((AuthorBean)bean).getGivenName(), ((AuthorBean)bean).getFamilyName());
			dao.destroy();
			
			logMessage (resultStatus, IEvent.AUTHOR_CREATE);
			DBLogger.getInstance().logEvent((Integer)session.getAttribute("userid"), IEvent.AUTHOR_CREATE, "Author: " + ((AuthorBean)bean).getFamilyName() + ", " + ((AuthorBean)bean).getGivenName() + " created by User: " + session.getAttribute("username") + " [" + ((resultStatus) ? "SUCCESS" : "FAIL") + "]");
		
			refresh();
		}
		else 
			logMessage (false, IEvent.EMPTY_CREATE);
		
		refreshCacheManager ();
		return "success";
	}
	
	public String delete  () {
		
		for (Object o : selectedObjects.keySet())
        {			
			int authorId = (Integer)o;
			if (selectedObjects.get(authorId) == true) {
				dao = new DBAuthor();
				boolean resultStatus = ((DBAuthor)dao).delete(authorId);
				dao.destroy();
				
				logMessage (resultStatus, IEvent.AUTHOR_DELETE);
				DBLogger.getInstance().logEvent((Integer)session.getAttribute("userid"), IEvent.AUTHOR_DELETE, "Author: " + authorId + " deleted by User: " + session.getAttribute("username") + " [" + ((resultStatus) ? "SUCCESS" : "FAIL") + "]");
			} 
        }
		
		refresh();
		refreshCacheManager ();
		return "success";
	}
	

	protected void logMessage (boolean success, int event) {
		
		Message messages = (Message) session.getAttribute("messages");
	
		if (event == IEvent.AUTHOR_CREATE) 
			message = "Create Author [" + ((AuthorBean)bean).getFamilyName() + ", " + ((AuthorBean)bean).getGivenName() + "]";
		else if (event == IEvent.AUTHOR_DELETE) 
			message = "Delete Author [" + session.getAttribute("username") + "]";
		else if (event == IEvent.EMPTY_SEARCH) 
			message = "No search results to display.";
		else if (event == IEvent.EMPTY_CREATE) 
			message = "Please fill in all fields.";

		messages.setMessage (success,message);
		
	}
	
	
	private void refreshCacheManager () 
	{
		CacheManager.resetAuthors();
	}
	
}
*/