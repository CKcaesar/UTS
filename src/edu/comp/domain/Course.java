package edu.comp.domain;

public class Course {
	public final String getCourseCode() {
		return courseCode;
	}
	public final void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	public final String getCourseName() {
		return courseName;
	}
	public final void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public final Integer getTermid() {
		return termid;
	}
	public final void setTermid(Integer termid) {
		this.termid = termid;
	}
	public final String getMeetingTimes() {
		return meetingTimes;
	}
	public final void setMeetingTimes(String meetingTimes) {
		this.meetingTimes = meetingTimes;
	}
	public final String getTime() {
		return time;
	}
	public final void setTime(String time) {
		this.time = time;
	}
	public final String getLocation() {
		return location;
	}
	public final void setLocation(String location) {
		this.location = location;
	}
/*	public final String getEnrollStart() {
		return enrollStart;
	}
	public final void setEnrollStart(String enrollStart) {
		this.enrollStart = enrollStart;
	}
	public final String getEnrollEnd() {
		return enrollEnd;
	}
	public final void setEnrollEnd(String enrollEnd) {
		this.enrollEnd = enrollEnd;
	}
	public final String getDropDeadline() {
		return dropDeadline;
	}
	public final void setDropDeadline(String dropDeadline) {
		this.dropDeadline = dropDeadline;
	}*/
	
	public Course(String courseCode, String courseName, Integer termid, String meetingTimes, String time, String location){
		this.courseCode=courseCode;
		this.courseName=courseName;
		this.termid=termid;
		this.meetingTimes=meetingTimes;
		this.time=time;
		this.location=location;
		//this.enrollStart=enrollStart;
		//this.enrollEnd=enrollEnd;
		//this.dropDeadline=dropDeadline;
	}
	
	public Course(String courseName, Integer termid, String meetingTimes, String time, String location){
		this.courseName=courseName;
		this.termid=termid;
		this.meetingTimes=meetingTimes;
		this.time=time;
		this.location=location;
		//this.enrollStart=enrollStart;
		//this.enrollEnd=enrollEnd;
		//this.dropDeadline=dropDeadline;
	}
	
	private String courseCode;
	private String courseName;
	private Integer termid;
	private String meetingTimes;
	private String time;
	private String location;
	//private String enrollStart;
	//private String enrollEnd;
	//private String dropDeadline;
}
