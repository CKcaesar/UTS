package edu.scs.carleton.comp.ls.view.beans;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class EventBean extends Bean {
	
	private Integer logId;
	private Integer eventId;
	private Integer userId;

	private String action;
	private String username;
	private String subject;
	private String description;
	
	private Date eventTime;
	
	public final Integer getLogId() {
		return logId;
	}

	public final void setLogId(Integer logId) {
		this.logId = logId;
	}

	public final Integer getEventId() {
		return eventId;
	}

	public final void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	public final Integer getUserId() {
		return userId;
	}

	public final void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public final String getUsername() {
		return username;
	}

	public final void setUsername(String username) {
		this.username = username;
	}

	public final String getAction() {
		return action;
	}

	public final void setAction(String action) {
		this.action = action;
	}

	public final String getSubject() {
		return subject;
	}

	public final void setSubject(String subject) {
		this.subject = subject;
	}

	public final String getDescription() {
		return description;
	}

	public final void setDescription(String description) {
		this.description = description;
	}

	public final Date getEventTime() {
		return eventTime;
	}

	public final void setEventTime(Date eventTime) {
		this.eventTime = eventTime;
	} 

	public final void clear () {
		this.logId = null;
		this.eventId = null;;
		this.userId = null;;
		this.action = null;;
		this.username = null;;
		this.subject = null;;
		this.description = null;;
		this.eventTime = null;;		
	}
	
}
