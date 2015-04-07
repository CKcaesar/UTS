package edu.scs.carleton.comp.ls.view.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import edu.scs.carleton.comp.ls.view.beans.Bean;

@ManagedBean
@RequestScoped
public class AssignmentBean extends Bean{
	 private int assignID;
	 private String courseID;
	 private String dueDate;
	 private String description;
	 private String assignName;
	 private String type;
	 
	public final int getAssignID() {
		return assignID;
	}

	public final void setAssignID(int assignID) {
		this.assignID = assignID;
	}

	public final String getCourseID() {
		return courseID;
	}
	
	public final void setCourseID(String courseID) {
		this.courseID = courseID;
	}



	public final String getDueDate() {
		return dueDate;
	}



	public final void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}



	public final String getDescription() {
		return description;
	}



	public final void setDescription(String description) {
		this.description = description;
	}



	public final String getAssignName() {
		return assignName;
	}



	public final void setAssignName(String assignName) {
		this.assignName = assignName;
	}



	public final String getType() {
		return type;
	}



	public final void setType(String type) {
		this.type = type;
	}



	public void clear() {
	    this.courseID=null;
		this.dueDate = null;
		this.description = null;
		this.assignName = null;
	    this.type=null;
		
	}

}
