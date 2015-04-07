package edu.scs.carleton.comp.ls.view.controllers;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;




//import edu.scs.carleton.comp.ls.admin.User;
import edu.comp.domain.Term;
import edu.scs.carleton.comp.ls.dbam.DBLogger;
//import edu.scs.carleton.comp.ls.dbam.DBUser;
import edu.comp.dbam.DBTerm;
import edu.scs.carleton.comp.ls.view.beans.Bean;
//import edu.comp.bean.Bean;
import edu.scs.carleton.comp.ls.view.beans.TermBean; 
import edu.scs.carleton.comp.ls.view.beans.UserBean;
import edu.scs.carleton.comp.ls.view.managers.CacheManager;
//import edu.comp.bean.TermBean;
import edu.scs.carleton.comp.ls.view.utils.IEvent;
import edu.scs.carleton.comp.ls.view.utils.Message;

@Singleton
@ManagedBean
@SessionScoped
public class TermController extends Controller implements IEvent{
	public TermController () {
		listOfObjects = new ArrayList<Bean>();
		selectedObjects = new HashMap<Object, Boolean>();	
	}
	
	@PostConstruct
	public void init() {
		bean = new TermBean();
		//((TermBean)bean).setRoleId(300);
		FacesContext facesContext = FacesContext.getCurrentInstance(); 
		session = (HttpSession) facesContext.getExternalContext().getSession(true);
	}
	
	DateFormat f=new SimpleDateFormat("yyyy-MM-dd");
	Calendar calendar = Calendar.getInstance();
	
	boolean noOverlapping;
	boolean noTimeConflict;
	
	public Bean getBean () {
		return (TermBean)bean;
	}
	
	public void setBean (Bean bean) {
		this.bean = (TermBean)bean;
	}

	public String create (Object String) throws ParseException 
	{	
		if (!((TermBean)bean).getName().equals("") 
		&& !((TermBean)bean).getStartDate().equals("") 
		&& !((TermBean)bean).getEndDate().equals("")){
			dao = new DBTerm();
			boolean resultStatus = ((DBTerm)dao).create ( ((TermBean)bean).getName()
												   , ((TermBean)bean).getStartDate()
												   , ((TermBean)bean).getEndDate()
												   ,((TermBean)bean).getEnrollStart()
												   ,((TermBean)bean).getEnrollEnd()
												   ,((TermBean)bean).getDropDeadline());
			dao.destroy();
			
			logMessage (resultStatus, IEvent.TERM_CREATE);
			DBLogger.getInstance().logEvent( (Integer)session.getAttribute("termid")
					                       , IEvent.TERM_CREATE, "Created Term: " + ((TermBean)bean).getName() + " [" + ((resultStatus) ? "SUCCESS" : "FAIL") + "]");
			
			refresh();
		}
		else 
			logMessage (false, IEvent.EMPTY_CREATE);
		
		dao.destroy();
		return "success";
	}
	
	
	
	public String refresh () {		
		return submit();
	}
	
	public String submit() {
		
		dao = new DBTerm();
		listOfObjects.clear();
		
		/*if (!((TermBean)bean).getName().isEmpty())*/
		setList(((DBTerm)dao).findAll());
		
		dao.destroy();
		
		return null;
	}
	
	public String create () throws ParseException
    {
		
		dao = new DBTerm();
		StateSignature ss=new StateSignature();
		ss.getPreData();
		
		if ((!((TermBean)bean).getName().equals("") 
			&& !((TermBean)bean).getStartDate().equals("") 
			&& !((TermBean)bean).getEndDate().equals("")))
			{
				noOverlapping=dateValidate(((DBTerm)dao).findAll(), 
						((TermBean)bean).getStartDate(), 
						((TermBean)bean).getEndDate());
			
				//set default enrollStart
				if(((TermBean)bean).getEnrollStart().equals(""))
					{
						Date startDate=f.parse(((TermBean)bean).getStartDate());
						calendar.setTime(startDate);
						calendar.add(Calendar.DATE,-30);
						((TermBean)bean).setEnrollStart(f.format(calendar.getTime()));
					}
				//set default enrollEnd
				if(((TermBean)bean).getEnrollEnd().equals(""))
					{
						Date startDate=f.parse(((TermBean)bean).getStartDate());
						calendar.setTime(startDate);
						calendar.add(Calendar.DATE,30);
						((TermBean)bean).setEnrollEnd(f.format(calendar.getTime()));
					}
				//set default dropDeadline
				if(((TermBean)bean).getDropDeadline().equals(""))
					{
						Date endDate=f.parse(((TermBean)bean).getEndDate());
						calendar.setTime(endDate);
						calendar.add(Calendar.DATE,-7);
						((TermBean)bean).setDropDeadline(f.format(calendar.getTime()));
					}
			
			if(noOverlapping){
				noTimeConflict=((f.parse(((TermBean)bean).getStartDate())).after(f.parse(((TermBean)bean).getEnrollStart())))
				&&((f.parse(((TermBean)bean).getEndDate())).after(f.parse(((TermBean)bean).getEnrollEnd())))
				&&((f.parse(((TermBean)bean).getEnrollStart())).before(f.parse(((TermBean)bean).getEnrollEnd())));
				if(noTimeConflict)
				{
					dao = new DBTerm();
					boolean resultStatus=((DBTerm)dao).create(
								((TermBean)bean).getName(), 
								((TermBean)bean).getStartDate(), 
								((TermBean)bean).getEndDate(),
								((TermBean)bean).getEnrollStart(), 
								((TermBean)bean).getEnrollEnd(), 
								((TermBean)bean).getDropDeadline());
					dao.destroy();
					
					if(resultStatus){
						CacheManager.resetTerms();
					}
				 	
					logMessage (resultStatus, IEvent.TERM_CREATE);
					DBLogger.getInstance().logEvent( (Integer)session.getAttribute("userid")
			                       , IEvent.TERM_CREATE, "Created Term: " + ((TermBean)bean).getName()+ " [" + ((resultStatus) ? "SUCCESS" : "FAIL") + "]");
					refresh();
					bean.clear();
				}else					
					logMessage (false, IEvent.TERM_CREATE);
					
				}else{
					logMessage (false, IEvent.TERM_CREATE);
				}
			}
		else 
			logMessage (false, IEvent.EMPTY_CREATE);
		
		ss.getCurData();
		ss.checkSystemStateChange();
		ss.logStateChange();
		if(ss.systemChange_flag==true){
			ss.outPutPreStateInDetail();
			ss.outPutCurStateInDetail();
		}
		
		return "success";
    }
	
	//check if two terms are overlapping
	public boolean dateValidate(List<Object> list, String x, String y) throws ParseException{
		
		
		Date sDate=f.parse(x);
		Date eDate=f.parse(y);
		boolean flag=true;
		for(Object o:list){
			Term term = (Term)o;
			
			Date sd=f.parse(term.getStartDate());
			Date ed=f.parse(term.getEndDate());
			if((sDate.after(sd)&&sDate.before(ed))||(eDate.after(sd)&&eDate.before(ed))){
				flag=false;
			}
		}
		return flag;
	}
	
	//update term with termName, enrollStart and enrollEnd
	public boolean update() throws ParseException{
		dao=new DBTerm();
		StateSignature ss=new StateSignature();
		ss.getPreData();
		
		Object o=((DBTerm)dao).findByPrimaryKey(((TermBean)bean).getName());
		Term term=(Term)o;
				
		if(((f.parse(term.getStartDate())).after(f.parse(((TermBean)bean).getEnrollStart())))
				&&((f.parse(term.getEndDate())).after(f.parse(((TermBean)bean).getEnrollEnd())))
				&&((f.parse(term.getEnrollStart())).before(f.parse(((TermBean)bean).getEnrollEnd())))){
			boolean resultStatus= ((DBTerm)dao).updateBy(
					((TermBean)bean).getName(),
					((TermBean)bean).getEnrollStart(),
					((TermBean)bean).getEnrollEnd());
			
			if(resultStatus){
				CacheManager.resetTerms();
			}
			
			logMessage (resultStatus, IEvent.TERM_SET);	
			refresh();
		}else{
			logMessage (false, IEvent.TERM_SET);
		}
		
		ss.getCurData();
		ss.checkSystemStateChange();
		ss.logStateChange();
		return false;
	}
	
	private void setList (ArrayList<Object> list) {
		
		for (Object o : list) {
			Term term = (Term)o; 
			
			TermBean tBean = new TermBean();
			//tBean.setTermid (term.getTermid());
			tBean.setName(term.getName());
			tBean.setStartDate(term.getStartDate());
			tBean.setEndDate(term.getEndDate());
			tBean.setEnrollStart(term.getEnrollStart());
			tBean.setEnrollEnd(term.getEnrollEnd());
			tBean.setDropDeadline(term.getDropDeadline());
			listOfObjects.add(tBean);
		}		
		if (listOfObjects.size() != 0) {
			//bean = (TermBean)listOfObjects.get(0);
		}
		else
			logMessage (false, IEvent.EMPTY_SEARCH);
		
		selectedObjects.clear();		
		this.render = !listOfObjects.isEmpty();
	}
	
	public String delete  () {

		dao = new DBTerm();
		StateSignature ss=new StateSignature();
		ss.getPreData();
		
		for (Object o : selectedObjects.keySet())
        {			
			String termName = (String)o;
			if(selectedObjects.get(o)==true){
				boolean resultStatus = ((DBTerm)dao).delete(termName);
				
				if(resultStatus){
					CacheManager.resetTerms();
				}
				
				((TermBean)bean).setName(termName);
				logMessage (resultStatus, IEvent.TERM_DELETE);
				DBLogger.getInstance().logEvent( (Integer)session.getAttribute("termid")
										   , IEvent.USER_DELETE, "Deleted Term: " + ((TermBean)bean).getName() + " [" + ((resultStatus) ? "SUCCESS" : "FAIL") + "]");
				//bean=(TermBean)bean;
			}			 
        }		
		dao.destroy();
		
		refresh();
		
		ss.getCurData();
		ss.checkSystemStateChange();
		ss.logStateChange();
		if(ss.systemChange_flag==true){
			ss.outPutPreStateInDetail();
			ss.outPutCurStateInDetail();
		}
		
		return "success";
	}

	protected void logMessage (boolean success, int event) {
		
		Message messages = (Message) session.getAttribute("messages");
	
		if ((event == IEvent.TERM_CREATE)&&noOverlapping&&noTimeConflict) 
			message = "Create Term [" + ((TermBean)bean).getName() + "]";
		else if ((event == IEvent.TERM_CREATE)&&!noOverlapping)
			message = "Create Term [" + ((TermBean)bean).getName() + "] Overlapping";
		else if	((event == IEvent.TERM_CREATE)&&noOverlapping&&!noTimeConflict)
			message = "Create Term [" + ((TermBean)bean).getName() + "] TimeConflict";
		else if (event == IEvent.TERM_DELETE)
			//termName display error!
			message = "Delete Term [" + ((TermBean)bean).getName() + "]";
		else if (event == IEvent.EMPTY_SEARCH) 
			message = "No search results to display.";
		else if (event == IEvent.EMPTY_CREATE) 
			message = "Please fill in all fields.";
		else if (event == IEvent.TERM_SET&&!success)
			message = "Set Term [" + ((TermBean)bean).getName() + "] invalid date";
		else if (event == IEvent.TERM_SET&&success)
			message = "Set Term [" + ((TermBean)bean).getName() + "]";
		
		messages.setMessage (success,message);
		
		//write into the log.txt
		writeToLog(message);
		
	}
		
}
