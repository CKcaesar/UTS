package edu.scs.carleton.comp.ls.view.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class PublisherBean extends Bean {

	private Integer publisherId;

	private String name;

	public final Integer getPublisherId() {
		return publisherId;
	}

	public final void setPublisherId(Integer publisherId) {
		this.publisherId = publisherId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void clear () {
		this.publisherId = null;
		this.name = null;
	}

}
