package edu.scs.carleton.comp.ls.view.controllers;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.ejb.Singleton;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@Singleton
@ManagedBean
@RequestScoped
public class Timecon {
	public String Time;
	private String set;
	private String currenttime;
	public void Timcon(){}
	
	public final String getSet() {
		return set;
	}

	public final void setSet(String set) {
		this.set = set;
	}

	public final String getCurrenttime() {
		return currenttime;
	}

	public final void setCurrenttime(String currenttime) {
		this.currenttime = currenttime;
	}

	public void changetime(){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
		String today = format.format(Calendar.getInstance().getTime());
		if(set.equals("")){
			currenttime=today;
			Time=currenttime;
		}
		else 
			currenttime=set;
			Time=currenttime;
		}
	}

