/*package edu.scs.carleton.comp.ls.view.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import edu.scs.carleton.comp.ls.admin.User;
import edu.scs.carleton.comp.ls.book.Item;
import edu.scs.carleton.comp.ls.book.Title;
import edu.scs.carleton.comp.ls.core.Loan;
import edu.scs.carleton.comp.ls.dbam.DBItem;
import edu.scs.carleton.comp.ls.dbam.DBLoan;
import edu.scs.carleton.comp.ls.dbam.DBLogger;
import edu.scs.carleton.comp.ls.dbam.DBTitle;
import edu.scs.carleton.comp.ls.dbam.DBUser;
import edu.scs.carleton.comp.ls.view.beans.Bean;
import edu.scs.carleton.comp.ls.view.beans.TitleBean;
import edu.scs.carleton.comp.ls.view.utils.IEvent;
import edu.scs.carleton.comp.ls.view.utils.Message;

@Singleton
@ManagedBean
@SessionScoped
public class TitleController extends Controller {

	public TitleController () {
		listOfObjects = new ArrayList<Bean>();
		selectedObjects = new HashMap<Object, Boolean>();	
	}
	
	@PostConstruct
	public void init(){
		bean = new TitleBean();
		FacesContext facesContext = FacesContext.getCurrentInstance(); 
		session = (HttpSession) facesContext.getExternalContext().getSession(true);
	}

	public String returnToSender () {
		return "returnToSender";
	}
	
	public void setBean (Bean bean) {
		listOfObjects.clear();
		this.bean = (TitleBean)bean;
	}
	
	public Bean getBean () {
		return (TitleBean)bean;
	}
	
	public String delete() {
		
		dao = new DBTitle();
		
		for (Object o : selectedObjects.keySet())
        {				
			String isbn = (String)o;
			if (selectedObjects.get(isbn) == true) {
				boolean resultStatus = ((DBTitle)dao).delete(isbn);
				logMessage (resultStatus, IEvent.TITLE_DELETE, isbn);
				DBLogger.getInstance().logEvent((Integer)session.getAttribute("userid"), IEvent.TITLE_DELETE, "Title with ISBN: " + isbn + " deleted by User: " + session.getAttribute("username") + " [" + ((resultStatus) ? "SUCCESS" : "FAIL") + "]");
			}
        }	
		
		dao.destroy();
		
		((TitleBean)bean).setIsbn("");
		
		refresh("");
		return null;
	}
	
	public String create() {
		if (!((TitleBean)bean).getIsbn().equals("") && !((TitleBean)bean).getTitle().equals("")){
			
			dao = new DBTitle();
			boolean resultStatus = ((DBTitle)dao).create ( ((TitleBean)bean).getIsbn()
								 					    , ((TitleBean)bean).getTitle()
								 						, ((TitleBean)bean).getAuthorId()
								 						, ((TitleBean)bean).getPublisherId());
			dao.destroy();
			
			logMessage (resultStatus, IEvent.TITLE_CREATE, ((TitleBean)bean).getIsbn());
			DBLogger.getInstance().logEvent((Integer)session.getAttribute("userid"), IEvent.TITLE_CREATE, "Title with ISBN: " + ((TitleBean)bean).getIsbn() + " created by User: " + session.getAttribute("username") + " [" + ((resultStatus) ? "SUCCESS" : "FAIL") + "]");
			
			refresh("");
		}
		else {
			logMessage (false, IEvent.EMPTY_CREATE, "");
		}
		
		return null;
	}
	
	public String createItems() {
		DBItem dbItem = new DBItem();	
		
		for (Object o : selectedObjects.keySet())
        {		
			String isbn = (String)o;
			if (selectedObjects.get(isbn) == true) {	

				boolean resultStatus = dbItem.create(isbn);
				
				logMessage (resultStatus, IEvent.ITEM_CREATE, isbn);
				DBLogger.getInstance().logEvent( (Integer)session.getAttribute("userid")
						                        , IEvent.ITEM_CREATE
						                        , "Item with ISBN: " 
						                        + isbn + " created by User: " 
						                        + session.getAttribute("username") 
						                        + " [" + ((resultStatus) ? "SUCCESS" : "FAIL") + "]");
			} 
        }		

		dbItem.destroy();

		refresh("");		
		return null;
	}
	
	public String refresh (String action) {
		
		dao = new DBTitle();
		
		if (((TitleBean)bean).getIsbn().length() != 0) {
			List<Object> title = new ArrayList<Object>();
			title.add(((DBTitle)dao).findByPrimaryKey(((TitleBean)bean).getIsbn()));
			setList (title, action);
		}
		else
		if (((TitleBean)bean).getTitle().length() != 0) 
			setList (((DBTitle)dao).findByTitle(((TitleBean)bean).getTitle()), action);			
		else
		if (((TitleBean)bean).getAuthorId() != null ) 
			setList (((DBTitle)dao).findByAuthor(((TitleBean)bean).getAuthorId()), action);
		
		dao.destroy();
		
		return null;
	}
	
	public String submit(ActionEvent e) {
		
		String action = e.getComponent().getId();
		
		if (action.equalsIgnoreCase("findAvailableItems") 
		|| !((TitleBean)bean).getIsbn().equals("") 
		|| !((TitleBean)bean).getTitle().equals("") 
		|| !((TitleBean)bean).getAuthor().equals("")) {
			
			dao = new DBTitle();
			
			if (((TitleBean)bean).getIsbn().length() != 0) {
				List<Object> title = new ArrayList<Object>();
				title.add(((DBTitle)dao).findByPrimaryKey(((TitleBean)bean).getIsbn()));
				setList (title, action);
			}	
			else
			if (((TitleBean)bean).getTitle().length() != 0)
				setList (((DBTitle)dao).findByTitle(((TitleBean)bean).getTitle()), action);
			else
			if (((TitleBean)bean).getAuthorId() != null)
				setList (((DBTitle)dao).findByAuthor(((TitleBean)bean).getAuthorId()), action);
			else
			if (((TitleBean)bean).getAuthor() != null)
				setList (((DBTitle)dao).findByAuthor(((TitleBean)bean).getAuthor()), action);
			
			dao.destroy();
		}
		else
			logMessage (false, IEvent.EMPTY_SEARCH, "");

		return null;
	}
	
	public String submit() { return ""; }
	
	private void setList (List<Object> titles, String action) {
		listOfObjects.clear();
		
		for (Object o : titles) {
			Title title = (Title)o;
			TitleBean tBean = new TitleBean (title);
			
			if (action.equalsIgnoreCase("findAvailableItems") || action.equalsIgnoreCase("memberFindTitles"))
				tBean.setOnHand(((DBTitle)dao).getNumberOfAvailableItems(title.getIsbn()));
			else
				tBean.setOnHand(((DBTitle)dao).getTotalNumberOfItems(title.getIsbn()));
			
			listOfObjects.add(tBean);
		}
		
		((TitleBean)bean).clear();

		if (!listOfObjects.isEmpty()){
			if (((TitleBean)(listOfObjects.get(0))).getAuthor().contains(","))
				((TitleBean)bean).setAuthor(((TitleBean)(listOfObjects.get(0))).getAuthor().substring(0, ((TitleBean)(listOfObjects.get(0))).getAuthor().indexOf(",")) );
			else 
				((TitleBean)bean).setAuthor(((TitleBean)(listOfObjects.get(0))).getAuthor());
			
			((TitleBean)bean).setIsbn(((TitleBean)(listOfObjects.get(0))).getIsbn());
			((TitleBean)bean).setPublisher(((TitleBean)(listOfObjects.get(0))).getPublisher());
			((TitleBean)bean).setTitle(((TitleBean)(listOfObjects.get(0))).getTitle());
		}
		
		if (listOfObjects.size() != 0)
			bean = listOfObjects.get(0);
		else
			logMessage (false, IEvent.EMPTY_SEARCH, "");
		
		selectedObjects.clear();		
		this.render = !listOfObjects.isEmpty();

	}
		
	public String checkOutItems  () {
		DBUser dbUser = new DBUser();
		DBLoan dbLoan = new DBLoan();
		DBItem dbItem = new DBItem();
		
		User user = (User)(dbUser).findByPrimaryKey((Integer)session.getAttribute("userid"));
		dbUser.destroy();
		
		boolean resultStatus = false;
		for (Object o : selectedObjects.keySet())
        {			
			String isbn = (String)o;
			if (selectedObjects.get(isbn) == true) {				
				Item item = (Item) dbItem.findByPrimaryKey(isbn);

				if (item != null) {
					resultStatus = dbLoan.create(new Loan (user, item));
				}

				logMessage (resultStatus, IEvent.LOAN_CREATE, null);
				DBLogger.getInstance().logEvent((Integer)session.getAttribute("userid"), IEvent.LOAN_CREATE, "Loan created by User: " + session.getAttribute("username") 
						              + " [" + isbn + " ] [" + ((resultStatus) ? "SUCCESS" : "FAIL") + "]");
			}
	
        }
		dbItem.destroy();
		dbLoan.destroy();

		LoanController loanController = (LoanController) (session.getAttribute("loanController"));
		loanController.updateLoans();

		refresh("findAvailableItems");
		
		return "success";
		
	}

	private void logMessage (boolean success, int event, String message) {
		
		Message messages = (Message) session.getAttribute("messages");
	
		if (event == IEvent.LOAN_CREATE) 
			message = "Create Loan [" + session.getAttribute("username") + "]";
		else if (event == IEvent.TITLE_CREATE) 
			message = "Create Title [" + message + "]";
		else if (event == IEvent.TITLE_DELETE) 
			message = "Delete Title [" + message + "] currently has items listed";
		else if (event == IEvent.ITEM_CREATE) 
			message = "Create Item [" + message + "]";
		else if (event == IEvent.EMPTY_SEARCH) 
			message = "No search results to display.";
		else if (event == IEvent.EMPTY_CREATE) 
			message = "Please fill in all fields.";

		messages.setMessage (success,message);
	}
	
	protected void logMessage (boolean success, int event) {	}

}
*/