package edu.scs.carleton.comp.ls.view.beans;

import javax.ejb.Singleton;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import edu.scs.carleton.comp.ls.view.managers.CacheManager;
import edu.scs.carleton.comp.ls.view.utils.IImage;
import edu.scs.carleton.comp.ls.view.utils.IPane;
import edu.scs.carleton.comp.ls.view.utils.Message;
import edu.scs.carleton.comp.ls.view.beans.Bean;

@Singleton
@ManagedBean
@SessionScoped
public class BackingBean implements IImage {

	private String action;
	private String resultsPane;
	private String image;
	private String contextRoot;
	private BakedBean oven;
	
	public BackingBean () {
		this.action = IPane.BLANK;
		this.image = IImage.ADMIN_DEFAULT;
		this.resultsPane = IPane.BLANK;
		this.oven = new BakedBean();
	}
	
	public String changeApplication () {
		return "changeApplication";
	}
	
	public void setAction(ActionEvent e) {
		String actionEvent = e.getComponent().getId();
				
		this.clearMessages();
		
		if ( actionEvent.equalsIgnoreCase("maintainTerms") ) {
			this.action = IPane.TERM_PANE;
			this.image = IImage.ADMIN_DEFAULT;
			this.resultsPane = IPane.TERM_RESULTS;
		}
		else
		if ( actionEvent.equalsIgnoreCase("maintainCourses")){
			this.action=IPane.COURSE_PANE;
			this.image=IImage.ADMIN_DEFAULT;
			this.resultsPane=IPane.COURSE_RESULTS;
		}
		else
		if ( actionEvent.equalsIgnoreCase("maintainAssignments")){
			this.action=IPane.ASSIGN_PANE;
			this.image=IImage.ADMIN_DEFAULT;
			this.resultsPane=IPane.ASSIGN_RESULTS;
		}
		else
		if(actionEvent.equalsIgnoreCase("gradingAssignments")){
			this.action=IPane.GRADING_PANE;
			this.image=IImage.ADMIN_DEFAULT;
			this.resultsPane=IPane.BLANK;
		}
		else
		if(actionEvent.equalsIgnoreCase("takeCourse")){
			this.action=IPane.TAKECOURSE_PANE;
			this.image=IImage.ADMIN_USERS;
			this.resultsPane = IPane.BLANK;
		}
		else
		if(actionEvent.equalsIgnoreCase("assignmentExam")){
			this.action=IPane.ASSIGNMENTEXAM_PANE;
			this.image=IImage.ADMIN_USERS;
			this.resultsPane = IPane.BLANK;
		}else
		if ( actionEvent.equalsIgnoreCase("maintainTitles") ) {
			this.image = IImage.ADMIN_TITLES;
			this.action = IPane.TITLES_PANE;
			this.resultsPane = IPane.TITLES_RESULTS;
		}
		else
		if ( actionEvent.equalsIgnoreCase("maintainAuthors") ) {
			this.action = IPane.AUTHORS_PANE;
			this.image = IImage.ADMIN_AUTHORS;
			this.resultsPane = IPane.AUTHORS_RESULTS;
		}
		else
		if ( actionEvent.equalsIgnoreCase("maintainPublishers") ) {
			this.action = IPane.PUBLISHERS_PANE;
			this.image = IImage.ADMIN_PUBLISHERS;
			this.resultsPane = IPane.PUBLISHERS_RESULTS;
		}
		else
		if ( actionEvent.equalsIgnoreCase("monitoringSystem") ) {
			this.action = IPane.EVENT_PANE;
			this.image = IImage.ADMIN_DEFAULT;
			this.resultsPane = IPane.BLANK;
		}
		
		else
		if ( actionEvent.equalsIgnoreCase("maintainItems") ) {
			this.action = IPane.ITEMS_PANE;
			this.image = IImage.ADMIN_ITEMS;
			this.resultsPane = IPane.ITEMS_RESULTS;
		}
		else {
			this.action = IPane.BLANK;
			this.image = IImage.ADMIN_DEFAULT;
			this.resultsPane = IPane.BLANK;
		}
		
		oven.bakeBeans();
		
	}
	
	public final String getAction () {
		return this.action;
	}
	
	public final String getResultsPane () {
		return resultsPane;	
	}
	
	public String getWelcomeImage () {
		return IImage.WELCOME;
	}
	
	public final String getImage () {
		return this.image;
	}

	public String getContextRoot() {
		return contextRoot;
	}

	public String logOut () {
		clearMessages ();
		this.action = IPane.BLANK;
		this.image = IImage.ADMIN_DEFAULT;
		this.resultsPane = IPane.BLANK;
		return "loggedOut";
	}
	
	private void clearMessages () {
		try {
			FacesContext facesContext = FacesContext.getCurrentInstance();  		
			HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
			Message messages = (Message) session.getAttribute("messages");
			messages.clear();
		} catch ( Exception e) {
			//Debug.trace(this,"clearErrorMessagers",e.getLocalizedMessage());
		}
	}

	
}
