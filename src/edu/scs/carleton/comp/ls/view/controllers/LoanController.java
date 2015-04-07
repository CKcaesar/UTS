/*package edu.scs.carleton.comp.ls.view.controllers;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import edu.scs.carleton.comp.ls.admin.User;
import edu.scs.carleton.comp.ls.core.Loan;
import edu.scs.carleton.comp.ls.dbam.DBLoan;
import edu.scs.carleton.comp.ls.dbam.DBLogger;
import edu.scs.carleton.comp.ls.dbam.DBUser;
import edu.scs.carleton.comp.ls.view.beans.Bean;
import edu.scs.carleton.comp.ls.view.beans.LoanBean;
import edu.scs.carleton.comp.ls.view.beans.TitleBean;
import edu.scs.carleton.comp.ls.view.beans.UserBean;
import edu.scs.carleton.comp.ls.view.utils.IEvent;
import edu.scs.carleton.comp.ls.view.utils.Message;

@Singleton
@ManagedBean
@SessionScoped
public class LoanController extends Controller {

	private HashMap<Integer, Boolean> renewals;
	private HashMap<Integer, Boolean> returns;
	private String outstandingBalance;
	private double payment;
	
	public LoanController () {
		listOfObjects = new ArrayList<Bean>();
		selectedObjects = new HashMap<Object, Boolean>();	
		returns = new HashMap<Integer, Boolean> ();
		renewals = new HashMap<Integer, Boolean> ();
	}
	
	@PostConstruct
	public void init(){
		bean = new UserBean();
		dao = new DBLoan();
		FacesContext facesContext = FacesContext.getCurrentInstance(); 
		session = (HttpSession) facesContext.getExternalContext().getSession(true);
		buildBeans ( ((DBLoan)dao).getOutstandingLoans((Integer)session.getAttribute("userid")) );
		dao.destroy();
		
		DBUser dbUser = new DBUser();
		DecimalFormat df = new DecimalFormat("$###,###.00");
		outstandingBalance = df.format(((User)dbUser.findByPrimaryKey( (Integer)session.getAttribute("userid") )).getFines());
		dbUser.destroy();
	}
	
	public final Bean getBean() {		
		return (UserBean)bean;
	}

	public void setBean(Bean bean) {
		this.bean = (UserBean)bean;
	}
	
	public String getOutstandingBalance(){
		return outstandingBalance;
	}

	public double getPayment() {
		return payment;
	}

	public void setPayment(double payment) {
		this.payment = payment;
	}
	
	public Map<Integer, Boolean> getRenewals() {
        return renewals;
    }
	
	public Map<Integer, Boolean> getReturns() {
        return returns;
    }
	
	public String submit () {

		dao = new DBLoan();
		
		for ( int loanId : renewals.keySet() )
        {	
			if (renewals.get(loanId) == true) {
				boolean resultStatus = ((DBLoan)dao).renewItem (loanId);
				
				logMessage (resultStatus, IEvent.LOAN_UPDATE);
				DBLogger.getInstance().logEvent((Integer)session.getAttribute("userid"), IEvent.LOAN_UPDATE, "Loan: " + loanId + " renewed by User: " + session.getAttribute("username") + " [" + ((resultStatus) ? "SUCCESS" : "FAIL") + "]");
			}
			
			if (returns.get(loanId) == true) {
				boolean resultStatus = ((DBLoan)dao).returnItem (loanId);
				
				logMessage (resultStatus, IEvent.LOAN_UPDATE);
				DBLogger.getInstance().logEvent((Integer)session.getAttribute("userid"), IEvent.LOAN_UPDATE, "Loan: " + loanId + "  returned by User: " + session.getAttribute("username") + " [" + ((resultStatus) ? "SUCCESS" : "FAIL") + "]");
			}
        }
		
		dao.destroy();
		
		refresh();
	
		TitleController titleController = (TitleController) (session.getAttribute("titleController"));
		TitleBean titleBean = (TitleBean)titleController.getBean();
		titleBean.setIsbn("");
		titleController.setBean(titleBean);
		titleController.refresh("findAvailableItems");
		
		return "";
	}
	
	public String create() { return ""; }
	
	public String delete() { return ""; }
	
	public final void makePayment () {
		DBUser dbUser = new DBUser();
		
		((UserBean)bean).makePayment(payment);
		boolean resultStatus = dbUser.makePayment((Integer)session.getAttribute("userid"), payment);
		
		if (resultStatus){
			
			DecimalFormat df = new DecimalFormat("$###,###.00");
			outstandingBalance = df.format(((User)dbUser.findByPrimaryKey( (Integer)session.getAttribute("userid") )).getFines());
		}
		
		dbUser.destroy();
		
		logMessage (resultStatus, IEvent.USER_UPDATE);
		DBLogger.getInstance().logEvent((Integer)session.getAttribute("userid"), IEvent.USER_UPDATE, "Fine payment of $" + payment + " by User: " + session.getAttribute("username") + " [" + ((resultStatus) ? "SUCCESS" : "FAIL") + "]");
		
	}
	
	public String refresh () {
		renewals.clear();
		returns.clear();

		dao = new DBLoan();
		buildBeans ( ((DBLoan)dao).getOutstandingLoans((Integer)session.getAttribute("userid")) );
		dao.destroy();
		((UserBean)bean).clear();
		
		this.render = !listOfObjects.isEmpty();

		return "";
	}
	
	private final void buildBeans (ArrayList<Object> arrayList) {
		listOfObjects.clear();

		for (Object o : arrayList) {
			Loan loan = (Loan)o;

			StringBuffer sb = new StringBuffer();
			sb.append (loan.getItem().getTitle().getAuthor().getFamilyName());
			sb.append (", ");
			sb.append (loan.getItem().getTitle().getAuthor().getGivenName());

			LoanBean loanBean = new LoanBean();
			loanBean.setLoanId(loan.getId());
			loanBean.setItemNumber(loan.getItem().getItemNumber());
			loanBean.setNumOfRenewals(loan.getNumRenewals());
			
			loanBean.setIsbn(loan.getItem().getTitle().getIsbn());
			loanBean.setTitle(loan.getItem().getTitle().getTitle());
			loanBean.setAuthor(sb.toString());
			loanBean.setDateBorrowed(loan.getDateOut());
			loanBean.setDueDate(loan.getDateDue());

			listOfObjects.add (loanBean);
		}
		
		if (!listOfObjects.isEmpty())
			render = true;
	}

	protected void logMessage (boolean success, int event) {
		
		Message messages = (Message) session.getAttribute("messages");
	
		if (event == IEvent.USER_UPDATE) 
			message = "Update User [" + session.getAttribute("username") + "]";
		else if (event == IEvent.LOAN_UPDATE) 
			message = "Update Loan [" + session.getAttribute("username") + "]";

		messages.setMessage (success,message);
	}
	
	public void updateLoans(){
		refresh();
	}
	
	public final void reset(){
		renewals.clear();
		returns.clear();
		listOfObjects.clear();
		((UserBean)bean).clear();
		render = false;
		message = "";
		payment = 0.0;
	}
}
*/