package edu.comp.domain;

public class User{
	
	public User(Integer stuID, String stuNo, String password, String firstname, String lastname, String middleinitial, String birthdate, String school, String degree, String time_status){
		this.stuID=stuID;
		this.stuNo=stuNo;
		this.password=password;
		this.firstname=firstname;
		this.lastname=lastname;
		this.middleinitial=middleinitial;
		this.birthdate=birthdate;
		this.school=school;
		this.degree=degree;
		this.time_status=time_status;
	}
	
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
