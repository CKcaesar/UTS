package edu.scs.carleton.comp.ls.view.beans;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import edu.scs.carleton.comp.ls.view.beans.Bean;

@ManagedBean
@RequestScoped

public class StuAssignBean extends Bean{
	
	//stuAssignID, stuID, assignID, submitDate, content, grade
	
	public final int getStuAssignmentID() {
		return stuAssignmentID;
	}
	public final void setStuAssignmentID(int stuAssignmentID) {
		this.stuAssignmentID = stuAssignmentID;
	}
	public final int getStuID() {
		return stuID;
	}
	public final void setStuID(int stuID) {
		this.stuID = stuID;
	}
	public final int getAssignID() {
		return assignID;
	}
	public final void setAssignID(int assignID) {
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
	
	private Integer stuAssignmentID;
	private Integer stuID;
	private Integer assignID;
	private Date submitDate;
	private String content;
	private String grade;
	
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		this.stuAssignmentID=null;
		this.stuID=null;
		this.assignID=null;
		this.submitDate=null;
		this.content=null;
		this.grade=null;		
	}
	
	
}
