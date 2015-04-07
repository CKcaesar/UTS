package edu.scs.carleton.comp.ls.view.beans;

import java.util.Enumeration;

import javax.faces.context.FacesContext;  
import javax.servlet.http.HttpSession;

import edu.scs.carleton.comp.ls.view.controllers.Controller;
import edu.scs.carleton.comp.ls.view.utils.Debug;
	  
public class BakedBean {  
	
	private String root;
	
	public String getRoot() {
		return root;
	}
	
	public void setRoot(String root) {
		this.root = root;
	}  

	public void bakeBeans () {  

		FacesContext facesContext = FacesContext.getCurrentInstance();  		
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		Enumeration<String> e = session.getAttributeNames();
				
		while (e.hasMoreElements()) {
			String key = e.nextElement();
			if (session.getAttribute(key) instanceof Bean) {
				((Bean)session.getAttribute(key)).clear();
				Debug.trace(new Object(),"getSessionAttributes","Session Attribute", key, "value", session.getAttribute(key).getClass().getName());
			}
			else if (session.getAttribute(key) instanceof Controller) {
				((Controller)session.getAttribute(key)).reset();
				Debug.trace(new Object(),"getSessionAttributes","Session Attribute", key, "value", session.getAttribute(key).getClass().getName());
			}
		}
		
	}

}
