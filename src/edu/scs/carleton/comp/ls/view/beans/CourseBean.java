package edu.scs.carleton.comp.ls.view.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import edu.scs.carleton.comp.ls.view.beans.Bean;
import edu.scs.carleton.comp.ls.view.utils.Debug;

@ManagedBean
@RequestScoped
public class CourseBean extends Bean{
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
	/*public final String getEnrollStart() {
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
	
	public void clear(){
		this.courseCode=null;
		this.courseName=null;
		this.termid=null;
		this.meetingTimes=null;
		this.time=null;
		this.location=null;
		//this.enrollStart=null;
		//this.enrollEnd=null;
		//this.dropDeadline=null;
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
