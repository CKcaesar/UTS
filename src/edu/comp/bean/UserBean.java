package edu.comp.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

//import edu.scs.carleton.comp.ls.admin.Role;
import edu.scs.carleton.comp.ls.view.utils.Debug;

@ManagedBean
@RequestScoped
public class UserBean extends Bean {

	private Integer stuId;

	private String stuNo;
	private String password;
	//private String roleName;
	//private String email;
	private String firstname;
	private String lastname;
	private String middleinitial;
	
	public final String getLastName() {
		return lastname;
	}

	public final void setLastName(String lastname) {
		this.lastname = lastname;
	}

	public final String getFirstName() {
		return firstname;
	}

	public final void setFirstName(String firstname) {
		Debug.trace(this, "setFirstName", firstname);
		this.firstname = firstname;
	}

	public final String getMiddleInitial() {
		return middleinitial;
	}

	public final void setMiddleInitial(String middleinitial) {
		this.middleinitial = middleinitial;
	}

	public final double getOutstandingBalance() {
		return outstandingBalance;
	}

	public final void setOutstandingBalance(double outstandingBalance) {
		this.outstandingBalance = outstandingBalance;
	}

	private double outstandingBalance;
	
	public final int getStuId() {
		return stuId;
	}

	public final void setStuId(int stuId) {
		this.stuId = stuId;
	}

/*	public final int getRoleId() {
		return roleId;
	}

	public final void setRoleId(int roleId) {
		this.roleId = roleId;
	}*/
	
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
	
	public void makePayment (double payment) {
		this.outstandingBalance -= payment;
	}

/*	public final String getEmail() {
		return email;
	}

	public final void setEmail(String email) {
		this.email = email;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}*/

	public void clear () {
		this.stuId = null;
		//this.roleId = new Integer (Role.PUBLIC_MEMBER);
		
		this.stuNo = null;
		this.password = "******";
		//this.roleName = null;
		//this.email = null;
		this.firstname = null;
		this.lastname = null;
		this.middleinitial = null;
	}
	
}