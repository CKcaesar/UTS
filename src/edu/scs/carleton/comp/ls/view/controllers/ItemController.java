/*package edu.scs.carleton.comp.ls.view.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import edu.scs.carleton.comp.ls.book.Item;
import edu.scs.carleton.comp.ls.dbam.DBItem;
import edu.scs.carleton.comp.ls.dbam.DBLogger;
import edu.scs.carleton.comp.ls.view.beans.Bean;
import edu.scs.carleton.comp.ls.view.beans.ItemBean;
import edu.scs.carleton.comp.ls.view.utils.IEvent;
import edu.scs.carleton.comp.ls.view.utils.Message;

@ManagedBean
@SessionScoped
public class ItemController extends Controller {

	public ItemController () {
		listOfObjects = new ArrayList<Bean>();
		selectedObjects = new HashMap<Object, Boolean>();	
	}
	
	@PostConstruct
	public void init(){
		bean = new ItemBean();
		FacesContext facesContext = FacesContext.getCurrentInstance(); 
		session = (HttpSession) facesContext.getExternalContext().getSession(true);
	}
	
	public Bean getBean () {
		return (ItemBean)bean;
	}

	public void setBean (Bean bean) {
		bean = (ItemBean)bean;
	}
	
	public String submit() {
		
		listOfObjects.clear();
		
		dao = new DBItem();
		
		if (!((ItemBean)bean).getIsbn().isEmpty()) {
			setList (((DBItem)dao).findItems(((ItemBean)bean).getIsbn()));
		}
		else if (!((ItemBean)bean).getTitle().isEmpty()) {
			setList (((DBItem)dao).findByTitle(((ItemBean)bean).getTitle()));
		}
		else if (((ItemBean)bean).getAuthorId() != null) {
			setList (((DBItem)dao).findByAuthor(((ItemBean)bean).getAuthorId()));
		}
		
		dao.destroy();

		return null;
	}
	
	public String create() { return ""; }
	
	public String delete() {
		
		for (Object o : selectedObjects.keySet())
        {				
			String beanId = (String)o;
			if (selectedObjects.get(beanId) == true) {		
				ItemBean iBean = findItemBean (beanId);
				
				dao = new DBItem();
				boolean resultStatus = ((DBItem)dao).delete(iBean.getIsbn(), iBean.getItemNumber());
				dao.destroy();
				
				logMessage (resultStatus, IEvent.ITEM_EXPIRED);
				DBLogger.getInstance().logEvent((Integer)session.getAttribute("userid"), IEvent.ITEM_EXPIRED, "Item with ISBN.ID: " + beanId + " expired by User: " + session.getAttribute("username") + " [" + ((resultStatus) ? "SUCCESS" : "FAIL") + "]");
			} 
        }		
		
		refresh();
		return null;
	}
	
	private void setList (List<Object> items) {
		listOfObjects.clear();
		
		for (Object o: items) {
			Item item = (Item)o;
			ItemBean iBean = new ItemBean ();
			
			iBean.setId (item.getTitle().getIsbn() + ":" + item.getItemNumber());
			iBean.setIsbn (item.getTitle().getIsbn());
			iBean.setItemNumber(item.getItemNumber());
			iBean.setAuthor (item.getTitle().getAuthor().getFamilyName() + ", " +  item.getTitle().getAuthor().getGivenName());
			iBean.setTitle(item.getTitle().getTitle());
			iBean.setPublisher(item.getTitle().getPublisher().getName());
			iBean.setAuthorId(item.getTitle().getAuthor().getAuthorID());
			
			listOfObjects.add(iBean);
		}
		
		((ItemBean)bean).clear();
		
		if (!listOfObjects.isEmpty())
			bean = listOfObjects.get(0);
		else
			logMessage (false, IEvent.EMPTY_SEARCH);
		
		selectedObjects.clear();		
		this.render = !listOfObjects.isEmpty();		
	}
		
    private ItemBean findItemBean (String beanId) {
    	
    	for (Object o : listOfObjects) {
    		ItemBean iBean = (ItemBean)o;
    		if (iBean.getId().equalsIgnoreCase(beanId))
    			return iBean;
    	}
    	
    	return null;
    }

    protected void logMessage (boolean success, int event) {
		
		Message messages = (Message) session.getAttribute("messages");
	
		if (event == IEvent.ITEM_EXPIRED) 
			message = "Expired Item [" + session.getAttribute("username") + "]";
		else if (event == IEvent.EMPTY_SEARCH) 
			message = "No search results to display.";

		messages.setMessage (success,message);
		
	}
}
*/