package edu.scs.carleton.comp.ls.view.managers;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Singleton;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import edu.scs.carleton.comp.ls.core.Event;
import edu.scs.carleton.comp.ls.dbam.DBEvent;
import edu.scs.carleton.comp.ls.view.beans.EventBean;

@Singleton
@ManagedBean
@RequestScoped
public class EventManager {
	
	private EventBean eventBean;	
	private List<EventBean> listOfEvents;

	public EventManager () {
		listOfEvents = new ArrayList<EventBean>();
	}

	public EventBean getEventBean () {
		return this.eventBean;
	}

	public void setEventBean (EventBean eventBean) {
		this.eventBean = eventBean;
	}
	
	public final List<EventBean> getListOfEvents() {
		setListOfEvents();
		return listOfEvents;
	}
	
	private void setListOfEvents () {
		listOfEvents.clear();
		
		DBEvent dbEvent = new DBEvent();
		List<Object> list = dbEvent.getEvents();
		dbEvent.destroy();
		
		for (Object o : list) {
			Event e = (Event)o;
			
			EventBean eBean = new EventBean ();
			eBean.setLogId ( e.getLogId());
			eBean.setEventId ( e.getEventId());
			eBean.setUserId ( e.getUserId());

			eBean.setUsername ( e.getUserName());
			eBean.setAction ( e.getAction() );
			eBean.setSubject ( e.getSubject() );
			eBean.setDescription ( e.getDescription() );
			
			eBean.setEventTime ( e.getCreated() );
			
			listOfEvents.add (eBean);
		}
	}

	public void finalize () throws Throwable {
		super.finalize();
	}


}
