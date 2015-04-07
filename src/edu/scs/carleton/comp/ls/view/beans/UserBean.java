package edu.scs.carleton.comp.ls.view.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import edu.scs.carleton.comp.ls.admin.Role;
import edu.scs.carleton.comp.ls.view.utils.Debug;

@ManagedBean
@RequestScoped
public class UserBean extends Bean {

	public final Integer getStuID() {
		return stuID;
	}
	public final void setStuID(Integer stuID) {
		this.stuID = stuID;
	}
	public final String getStuNo() {
		return stuNo;
	}
	public final void setStuNo(String stuNo) {
		this.stuNo = stuNo;
	}
	public final String getPassword() {
		return password;
	}
	public final void setPassword(String password) {
		this.password = password;
	}
	public final String getFirstname() {
		return firstname;
	}
	public final void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public final String getLastname() {
		return lastname;
	}
	public final void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public final String getMiddleinitial() {
		return middleinitial;
	}
	public final void setMiddleinitial(String middleinitial) {
		this.middleinitial = middleinitial;
	}
	public final String getBirthdate() {
		return birthdate;
	}
	public final void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	public final String getSchool() {
		return school;
	}
	public final void setSchool(String school) {
		this.school = school;
	}
	public final String getDegree() {
		return degree;
	}
	public final void setDegree(String degree) {
		this.degree = degree;
	}
	public final String getTime_status() {
		return time_status;
	}
	public final void setTime_status(String time_status) {
		this.time_status = time_status;
	}
	
	public void clear(){
		this.stuID=null;
		this.stuNo=null;
		this.password=null;
		this.firstname=null;
		this.lastname=null;
		this.middleinitial=null;
		this.birthdate=null;
		this.school=null;
		this.degree=null;
		this.time_status=null;
	}
	
	private Integer stuID;
	private String stuNo;
	private String password;
	private String firstname;
	private String lastname;
	private String middleinitial;
	private String birthdate;
	private String school;
	private String degree;
	private String time_status;	

}
