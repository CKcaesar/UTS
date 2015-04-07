package edu.scs.carleton.comp.ls.view.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class AuthorBean extends Bean {

	private Integer authorId;

	private String familyName;
	private String givenName;

	public final Integer getAuthorId() {
		return authorId;
	}

	public final void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

	public final String getFamilyName() {
		return familyName;
	}

	public final void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public final String getGivenName() {
		return givenName;
	}


	public final void setGivenName(String givenName) {
		this.givenName = givenName;
	}


	public void clear () {
		this.authorId = null;
		this.familyName = null;
		this.givenName = null;
	}
}
