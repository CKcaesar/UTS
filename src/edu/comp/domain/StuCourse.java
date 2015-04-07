package edu.comp.domain;

public class StuCourse {
	
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
	
	public StuCourse(String stuNo, String course, String registerDate, String termName){
		this.stuNo=stuNo;
		this.course=course;
		this.registerDate=registerDate;
		this.termName=termName;
	}
	
	private String stuNo;
	private String course;
	private String registerDate;
	private String termName;
}
