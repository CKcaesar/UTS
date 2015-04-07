package edu.scs.carleton.comp.ls.view.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;


@FacesValidator ("edu.scs.carleton.comp.ls.view.utils.Message")

public final class Message {
		
	List<String> errorMessages = new ArrayList<String> ();
	List<String> confirmations = new ArrayList<String> ();
	List<String> feedbacks=new ArrayList<String>();
	
	public long timeStamp;
	
	public Message () {	
		timeStamp = Calendar.getInstance().getTimeInMillis();
	}
	
	public boolean isErrorRender () {
			return !errorMessages.isEmpty();
	}
	
	public boolean isConfirmRender () {
		return !confirmations.isEmpty();
	}
	
	public boolean isFeedbackRender () {
		return !feedbacks.isEmpty();
	}
		
	public List<String> getMessage () {
		return errorMessages;
	}
	
	public void setMessage (boolean success, String message) {
		clear();
	
		if ( !success ) {
			if (message.equals("No search results to display."))
				errorMessages.add(message);
			else
				errorMessages.add(message += ": FAILED");
		}
		else
			confirmations.add(message += ": SUCCESS");
	}
	
	public void setFeedback(String stuNo, String password){
		feedbacks.add("Please remember carefully!/n");
		feedbacks.add("stuNo: "+stuNo+" password:"+password);
	}
	
	public final List<String> getErrorMessages() {
		return errorMessages;
	}

	public final List<String> getConfirmations() {
		return confirmations;
	}
	
	public final List<String> getFeedbacks() {
		return feedbacks;
	}
	
	public void clear () {

		long now = Calendar.getInstance().getTimeInMillis(); 
		if (now - timeStamp > 1000 ) {
			this.errorMessages.clear();
			this.confirmations.clear();
		}
		
		timeStamp = now;
	}
	
	public void validateString (FacesContext context, UIComponent component, Object value)
			throws ValidatorException {
	
		clear();
		
		String s = (String) value;
		String cid = component.getId();
				
		Pattern pattern = Pattern.compile("[a-zA-Z]{4,25}");
	    Matcher matcher = pattern.matcher(s);
	    
	    if (!matcher.matches()) {
	    	String m = cid;
			m += ": Invalid String format/length [" + s + "]" ;
			this.errorMessages.add(m);
			FacesMessage fMsg = new FacesMessage ("String Validation Failed",m);
			throw new ValidatorException(fMsg);
	    }
	
	}
	
	public void validateMiddleInitial (FacesContext context, UIComponent component, Object value)
			throws ValidatorException {
		
		clear();
		
		String s = (String) value;
		String cid = component.getId();
				
		Pattern pattern = Pattern.compile("[a-zA-Z]\\.?");
	    Matcher matcher = pattern.matcher(s);
	    
	    if (!matcher.matches()) {
	    	String m = cid;
			m += ": Invalid String format/length [" + s + "]" ;
			this.errorMessages.add(m);
			FacesMessage fMsg = new FacesMessage ("Middle Initial Validation Failed",m);
			throw new ValidatorException(fMsg);
	    }	
	}
	
	public void validateEmail (FacesContext context, UIComponent component, Object value)
			throws ValidatorException {
		
		clear();
		String s = (String) value;
		String cid = component.getId();
				
		String regex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern pattern = Pattern.compile(regex);
	    Matcher matcher = pattern.matcher(s);
	    
	    if (!matcher.matches()) {
	    	String m = cid;
			m += ": Invalid Email Address Format [" + s + "]" ;
			FacesMessage fMsg = new FacesMessage ("Email Validation Failed",m);
			this.errorMessages.add(m);
			throw new ValidatorException(fMsg);
		}	
	}
	
	public void validateUsername (FacesContext context, UIComponent component, Object value)
			throws ValidatorException {
		
		clear();
		
		String s = (String) value;
		String cid = component.getId();
				
		String regex = "([a-zA-Z0-9]{4,25})";

		Pattern pattern = Pattern.compile(regex);
	    Matcher matcher = pattern.matcher(s);
	    
	    if (!matcher.matches()) {
	    	String m = cid;
			m += ": Invalid Username Format [" + s + "]" ;
			this.errorMessages.add(m);
			FacesMessage fMsg = new FacesMessage ("Middle Initial Validation Failed",m);
			throw new ValidatorException(fMsg);
	    }	
	}

	public void validatePassword (FacesContext context, UIComponent component, Object value)
			throws ValidatorException {
		
		clear();
		
		String s = (String) value;
		String cid = component.getId();
				
		String regex = "([a-zA-Z0-9!@#$%^&*()~]{6,20})";

		Pattern pattern = Pattern.compile(regex);
	    Matcher matcher = pattern.matcher(s);
	    
	    if (!matcher.matches()) {
	    	String m = cid;
			m += ": Invalid Password Format [" + s + "]" ;
			this.errorMessages.add(m);
			FacesMessage fMsg = new FacesMessage ("Middle Initial Validation Failed",m);
			throw new ValidatorException(fMsg);
	    }	
	}
	
	public void validateISBN (FacesContext context, UIComponent component, Object value)
			throws ValidatorException {
		
		clear();
		
		String s = (String) value;
		String cid = component.getId();
				
		String regex = "([0-9a-zA-Z]{10,10})";

		Pattern pattern = Pattern.compile(regex);
	    Matcher matcher = pattern.matcher(s);
	    
	    if (!matcher.matches()) {
	    	String m = cid;
			m += ": Invalid ISBN Format [" + s + "]" ;
			this.errorMessages.add(m);
			FacesMessage fMsg = new FacesMessage ("Middle Initial Validation Failed",m);
			throw new ValidatorException(fMsg);
	    }	
	}
	
	public void validatePayment (FacesContext context, UIComponent component, Object value)
			throws ValidatorException {
		
		clear();
		
		String s = Double.toString((Double)value);
		String cid = component.getId();
				
		String regex = "([1-9][0-9]{0,10}\\.[0-9]{1,2})|([1-9][0-9]{0,10})|(0\\.[0-9]{1,2})";

		Pattern pattern = Pattern.compile(regex);
	    Matcher matcher = pattern.matcher(s);
	    
	    if (!matcher.matches()) {
	    	String m = cid;
			m += ": Invalid Payment Format [" + s + "]" ;
			this.errorMessages.add(m);
			FacesMessage fMsg = new FacesMessage ("Middle Initial Validation Failed",m);
			throw new ValidatorException(fMsg);
	    }	
	}
	
	public void validateName (FacesContext context, UIComponent component, Object value)
			throws ValidatorException {
		
		clear();
		
		String s = (String) value;
		String cid = component.getId();
				
		String regex = "([a-zA-Z-\\s\\.,\\(\\)]{2,25})";

		Pattern pattern = Pattern.compile(regex);
	    Matcher matcher = pattern.matcher(s);
	    
	    if (!matcher.matches()) {
	    	String m = cid;
			m += ": Invalid Name Format [" + s + "]" ;
			this.errorMessages.add(m);
			FacesMessage fMsg = new FacesMessage ("Middle Initial Validation Failed",m);
			throw new ValidatorException(fMsg);
	    }	
	}

	public void validateGivenName (FacesContext context, UIComponent component, Object value)
			throws ValidatorException {
		
		clear();
		
		String s = (String) value;
		String cid = component.getId();
				
		String regex = "([\\s.*][a-zA-Z-]{2,25}(\\.)(,))";

		Pattern pattern = Pattern.compile(regex);
	    Matcher matcher = pattern.matcher(s);
	    
	    if (!matcher.matches()) {
	    	String m = cid;
			m += ": Invalid Name Format [" + s + "]" ;
			this.errorMessages.add(m);
			FacesMessage fMsg = new FacesMessage ("Middle Initial Validation Failed",m);
			throw new ValidatorException(fMsg);
	    }	
	}
	
	public void validatePublisher (FacesContext context, UIComponent component, Object value)
			throws ValidatorException {
		
		clear();
		
		String s = (String) value;
		String cid = component.getId();
				
		String regex = "([a-zA-Z0-9\\s\\(\\)]{3,30})";

		Pattern pattern = Pattern.compile(regex);
	    Matcher matcher = pattern.matcher(s);
	    
	    if (!matcher.matches()) {
	    	String m = cid;
			m += ": Invalid String Format [" + s + "]" ;
			this.errorMessages.add(m);
			FacesMessage fMsg = new FacesMessage ("Middle Initial Validation Failed",m);
			throw new ValidatorException(fMsg);
	    }	
	}
}
