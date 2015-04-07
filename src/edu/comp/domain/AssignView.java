package edu.comp.domain;

public class AssignView {
	private int assignID;
	 private String courseID;
	 private String dueDate;
	 private String description;
	 private String assignName;
	 private String type;
	private String grade;
	 

	public final String getGrade() {
		return grade;
	}

	public final void setGrade(String grade) {
		this.grade = grade;
	}

	public AssignView(int assignID, String courseID, String dueDate,String description, String assignName, String type, String grade) 
	{
		
		this.assignID = assignID;
		this.courseID = courseID;
		this.dueDate = dueDate;
		this.description = description;
		this.assignName = assignName;
		this.type = type;
		this.grade=grade;
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
}
