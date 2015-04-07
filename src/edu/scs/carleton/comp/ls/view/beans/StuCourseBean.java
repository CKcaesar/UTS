package edu.scs.carleton.comp.ls.view.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import edu.scs.carleton.comp.ls.view.beans.Bean;
import edu.scs.carleton.comp.ls.view.utils.Debug;

@ManagedBean
@RequestScoped
public class StuCourseBean extends Bean{
		
	public final String getStuNo() {
		return stuNo;
	}
	public final void setStuNo(String stuNo) {
		this.stuNo = stuNo;
	}
	public final String getCourse() {
		return course;
	}
	public final void setCourse(String course) {
		this.course = course;
	}
	public final String getRegisterDate() {
		return registerDate;
	}
	public final void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}
	public final String getTermName() {
		return termName;
	}
	public final void setTermName(String termName) {
		this.termName = termName;
	}
	
	public void clear(){
		this.stuNo=null;
		this.course=null;
		this.registerDate=null;
		this.termName=null;

	}
	
	private String stuNo;
	private String course;
	private String registerDate;
	private String termName;
	private int termid;
	public final int getTermid() {
		return termid;
	}
	public final void setTermid(int termid) {
		this.termid = termid;
	}
}
