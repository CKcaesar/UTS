package edu.comp.domain;

import java.util.Date;

public class StuAssign {
	
	public final Integer getStuAssignmentID() {
		return stuAssignmentID;
	}
	public final void setStuAssignmentID(Integer stuAssignmentID) {
		this.stuAssignmentID = stuAssignmentID;
	}
	public final Integer getStuID() {
		return stuID;
	}
	public final void setStuID(Integer stuID) {
		this.stuID = stuID;
	}
	public final Integer getAssignID() {
		return assignID;
	}
	public final void setAssignID(Integer assignID) {
		this.assignID = assignID;
	}
	public final Date getSubmitDate() {
		return submitDate;
	}
	public final void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}
	public final String getContent() {
		return content;
	}
	public final void setContent(String content) {
		this.content = content;
	}
	public final String getGrade() {
		return grade;
	}
	public final void setGrade(String grade) {
		this.grade = grade;
	}
	
	public StuAssign(Integer stuAssignmentID, Integer stuID, Integer assignID, Date submitDate, String content, String grade){
		this.stuAssignmentID=stuAssignmentID;
		this.stuID=stuID;
		this.assignID=assignID;
		this.submitDate=submitDate;
		this.content=content;
		this.grade=grade;
	}
	
	private Integer stuAssignmentID;
	private Integer stuID;
	private Integer assignID;
	private Date submitDate;
	private String content;
	private String grade;
	
	
}
