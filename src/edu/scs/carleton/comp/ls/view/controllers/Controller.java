package edu.scs.carleton.comp.ls.view.controllers;

import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;



//import edu.scs.carleton.comp.ls.dbam.DAO;
import edu.comp.dbam.DAO;
import edu.scs.carleton.comp.ls.view.beans.Bean;
//import edu.comp.bean.Bean;


public abstract class Controller {
	
	protected HashMap<Object, Boolean> selectedObjects;
	protected List<Bean> listOfObjects;
	
	//my code
	protected HashMap<Object, Boolean> mySelectedObjects;
	protected List<Bean> myListOfObjects;
	
	//protected Bean bean;
	public Bean bean;
	protected DAO dao;
	
	//my code
	protected Bean bean1;
	protected DAO dao1;
	
	protected HttpSession session;
	
	protected String message = "";
	protected boolean render;
	
	public final List<Bean> getListOfObjects() {
		return listOfObjects;
	}

	public final void setListOfObjects(List<Bean> listOfObjects) {
		this.listOfObjects = listOfObjects;
	}
	
	//my code
	public final List<Bean> getMyListOfObjects() {
		return myListOfObjects;
	}

	public final void setMyListOfObjects(List<Bean> myListOfObjects) {
		this.myListOfObjects = myListOfObjects;
	}//my code ends

	public Map<Object, Boolean> getSelectedObjects() {
        return selectedObjects;
    }
	
	//my code
	public Map<Object, Boolean> getMySelectedObjects() {
        return mySelectedObjects;
    }
	
	public final String getMessage() {
		return message;
	}
	
	public boolean isRender() {
		return render;
	}

	public void setRender(boolean render) {
		this.render = render;
	}
	
	public String refresh () {
		return submit();
	}
	
	public String closePane (ActionEvent e) {	
		bean.clear();
		this.render = false;
		listOfObjects.clear();
		selectedObjects.clear();
		//my code
		//myListOfObjects.clear();
		//mySelectedObjects.clear();
		return "closed";
	}
	
	public void reset() {
		if (bean != null)
			bean.clear();
		
		selectedObjects.clear();
		listOfObjects.clear();
		//my code
		//myListOfObjects.clear();
		//mySelectedObjects.clear();
		
		render = false;
		message = "";
	}
	
	public void clear () {	
		bean.clear();
		listOfObjects.clear();
		selectedObjects.clear();
		//my code
		//myListOfObjects.clear();
		//mySelectedObjects.clear();
		render = false;
	}
	
	public void writeToLog(String msg){
		FileWriter fw;
		try {
			fw = new FileWriter("D:\\log.txt",true);
			Date date=new Date();
			String logMsg=date.toString()+" "+msg+"\n";
			fw.write(logMsg,0,logMsg.length());  
			fw.flush();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected abstract Bean getBean ();
		
	protected abstract void setBean (Bean bean);
	
	protected abstract String submit();
	
	protected abstract String create () throws ParseException;
	
	protected abstract String delete ();
	
	protected abstract void logMessage (boolean success, int event);
}
