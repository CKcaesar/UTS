package edu.comp.domain;

public class Assignment {
	 private static final long serialVersionUID = 1L;
	 private int assignID;
	 private String courseID;
	 private String dueDate;
	 private String description;
	 private String assignName;
	 private String type;
	
	 

	public Assignment(int assignID, String courseID, String dueDate,String description, String assignName, String type) 
	{
		
		this.assignID = assignID;
		this.courseID = courseID;
		this.dueDate = dueDate;
		this.description = description;
		this.assignName = assignName;
		this.type = type;
	}



	public Assignment(String courseID, String dueDate,String description, String assignName, String type) 
	{
		this.courseID = courseID;
		this.dueDate = dueDate;
		this.description = description;
		this.assignName = assignName;
		this.type = type;
	}
	
	
	
	
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



	public static final long getSerialversionuid() {
		return serialVersionUID;
	}
	 






}
