/*package edu.scs.carleton.comp.ls.view.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import edu.scs.carleton.comp.ls.book.Publisher;
import edu.scs.carleton.comp.ls.dbam.DBLogger;
import edu.scs.carleton.comp.ls.dbam.DBPublisher;
import edu.scs.carleton.comp.ls.view.beans.Bean;
import edu.scs.carleton.comp.ls.view.beans.PublisherBean;
import edu.scs.carleton.comp.ls.view.managers.CacheManager;
import edu.scs.carleton.comp.ls.view.utils.IEvent;
import edu.scs.carleton.comp.ls.view.utils.Message;

@Singleton
@ManagedBean
@SessionScoped
public class PublisherController extends Controller {
	
	public PublisherController () {
		listOfObjects = new ArrayList<Bean>();
		selectedObjects = new HashMap<Object, Boolean>();	
	}
	
	@PostConstruct
	public void init(){
		bean = new PublisherBean();
		FacesContext facesContext = FacesContext.getCurrentInstance(); 
		session = (HttpSession) facesContext.getExternalContext().getSession(true);
	}
	
	public Bean getBean () {
		return (PublisherBean)bean;
	}
	
	public void setBean (Bean bean){
		this.bean = (PublisherBean)bean;
	}

	public String submit() {
		
		listOfObjects.clear();
		
		if (!((PublisherBean)bean).getName().isEmpty()) {
			dao = new DBPublisher();
			setList (((DBPublisher)dao).findByName(((PublisherBean)bean).getName()));
			dao.destroy();
			return null;
		}

		return null;
	}
	
	protected void setList (List<Object> publishers) {

		for (Object o : publishers) {
			Publisher publisher = (Publisher)o;
			
			PublisherBean pBean = new PublisherBean();
			pBean.setPublisherId(publisher.getPublisherID());
			pBean.setName (publisher.getName());	
			listOfObjects.add(pBean);
		}
		
		if (!listOfObjects.isEmpty())
			bean = listOfObjects.get(0);
		else
			logMessage (false, IEvent.EMPTY_SEARCH);

		selectedObjects.clear();		
		this.render = !listOfObjects.isEmpty();

	}

	public String create () {
		
		if ( ((PublisherBean)bean).getName() != null && ((PublisherBean)bean).getName().length() != 0 ) {
			dao = new DBPublisher();
			boolean resultStatus =  ((DBPublisher)dao).create ( ((PublisherBean)bean).getName());
			dao.destroy();
			
			logMessage (resultStatus, IEvent.PUBLISHER_CREATE);
			DBLogger.getInstance().logEvent((Integer)session.getAttribute("userid"), IEvent.PUBLISHER_CREATE, "Publisher: " + ((PublisherBean)bean).getName() + " created by User: " + session.getAttribute("username") + " [" + ((resultStatus) ? "SUCCESS" : "FAIL") + "]");
		
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
			int publisherId = (Integer)o;
			if (selectedObjects.get(publisherId) == true) {
				dao = new DBPublisher();
				boolean resultStatus =  ((DBPublisher)dao).delete(publisherId);
				dao.destroy();
				
				logMessage (resultStatus, IEvent.PUBLISHER_DELETE);
				DBLogger.getInstance().logEvent((Integer)session.getAttribute("userid"), IEvent.PUBLISHER_DELETE, "Publisher: " + publisherId + " deleted by User: " + session.getAttribute("username") + " [" + ((resultStatus) ? "SUCCESS" : "FAIL") + "]");
			} 
        }
		
		refresh();
		refreshCacheManager ();
		return "success";
	}
	
	protected void logMessage (boolean success, int event) {
		
		Message messages = (Message) session.getAttribute("messages");
	
		if (event == IEvent.PUBLISHER_CREATE) 
			message = "Create Publisher [" + ((PublisherBean)bean).getName() + "]";
		else if (event == IEvent.PUBLISHER_DELETE) 
			message = "Delete Publisher [" + session.getAttribute("username") + "]";
		else if (event == IEvent.EMPTY_SEARCH) 
			message = "No search results to display.";
		else if (event == IEvent.EMPTY_CREATE) 
			message = "Please enter in a valid publisher name.";

		messages.setMessage (success,message);
		
	}
	
	private void refreshCacheManager () {
		CacheManager.resetPublishers();
	}	
}
*/