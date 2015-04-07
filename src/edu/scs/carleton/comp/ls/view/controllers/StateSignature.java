package edu.scs.carleton.comp.ls.view.controllers;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cucumber.features.DBSensor;
import edu.comp.dbam.DAO;
import edu.comp.dbam.DBCourse;
import edu.comp.dbam.DBTerm;
import edu.comp.dbam.DBUser;
import edu.comp.domain.Course;
import edu.comp.domain.Term;
import edu.comp.domain.User;
import edu.scs.carleton.comp.ls.view.beans.Bean;
import edu.scs.carleton.comp.ls.view.beans.TermBean;

public class StateSignature {
	
	protected DAO dao;
	DBSensor dbs=new DBSensor();
	
	protected List<Object> preTermData;
	protected List<Object> curTermData;
	protected List<Object> preUserData;
	protected List<Object> curUserData;
	protected List<Object> preCourseData;
	protected List<Object> curCourseData;
	String delta="";
	
	boolean termChange_flag=false;
	boolean userChange_flag=false;
	boolean courseChange_flag=false;
	boolean systemChange_flag=false;
	
	public void getPreData(){
		dao = new DBTerm();
		//setList(((DBTerm)dao).findAll(),preTermData);
		preTermData=((DBTerm)dao).findAll();
		dao.destroy();
		dao = new DBUser();
		//setList(((DBUser)dao).findAll(),preUserData);
		preUserData=((DBUser)dao).findAllX();
		dao.destroy();
		dao = new DBCourse();
		//setList(((DBCourse)dao).findAll(),preCourseData);
		preCourseData=((DBCourse)dao).findAll();
		dao.destroy();
	}
	
	public void getCurData(){
		dao = new DBTerm();
		//setList(((DBTerm)dao).findAll(),curTermData);
		curTermData=((DBTerm)dao).findAll();
		dao.destroy();
		dao = new DBUser();
		//setList(((DBUser)dao).findAll(),curUserData);
		curUserData=((DBUser)dao).findAllX();
		dao.destroy();
		dao = new DBCourse();
		//setList(((DBCourse)dao).findAll(),curCourseData);
		curCourseData=((DBCourse)dao).findAll();
		dao.destroy();
	}
	
	public void checkTermStateChange(){
		System.out.println("checkTerm");
		if(preTermData.size()==curTermData.size()){
			termChange_flag=false;
		}else if(preTermData.size()>curTermData.size()){
			termChange_flag=true;
			Term t=(Term) preTermData.get(preTermData.size()-1);
			delta=t.getName();
		}else if(preTermData.size()<curTermData.size()){
			termChange_flag=true;
			Term t=(Term) curTermData.get(curTermData.size()-1);
			delta=t.getName();
		}
		//return termChange_flag;
	}
	
	public void checkUserStateChange(){
		System.out.println("checkUser");
		if(preUserData.size()==curUserData.size()){
			userChange_flag=false;
		}else if(preUserData.size()>curUserData.size()){
			userChange_flag=true;
			User u=(User) preUserData.get(preUserData.size()-1);
			delta=u.getStuNo();
		}else if(preUserData.size()<curUserData.size()){
			userChange_flag=true;
			User u=(User) curUserData.get(curUserData.size()-1);
			delta=u.getStuNo();
		}
			
		//return userChange_flag;
	}
	
	public void checkCourseStateChange(){
		System.out.println("checkcourse");
		if(preCourseData.size()==curCourseData.size()){
			courseChange_flag=false;
		}else if(preCourseData.size()>curCourseData.size()){
			courseChange_flag=true;
			Course c=(Course) preCourseData.get(preCourseData.size()-1);
			delta=c.getCourseName();
		}else if(preCourseData.size()<curCourseData.size()){
			courseChange_flag=true;
			Course c=(Course) curCourseData.get(curCourseData.size()-1);
			delta=c.getCourseName();
		}
		//return courseChange_flag;
	}
	
	public boolean checkSystemStateChange(){
		checkTermStateChange();
		checkUserStateChange();
		checkCourseStateChange();
		if(!termChange_flag&&!userChange_flag&&!courseChange_flag){
			systemChange_flag=false; 
		}else{
			systemChange_flag=true;
		}
		return systemChange_flag;
	}
	
	public void logStateChange(){
		String stateChangeResult="SystemStateChange: "+systemChange_flag
				+"[termstatechange:"+termChange_flag
				+" userstatechange:"+userChange_flag
				+" coursestatechange:"+courseChange_flag
				+" delta:"+delta+"]";
		dbs.writeToLog(stateChangeResult);
	}
	
	public void outPutPreStateInDetail(){
		String preTerm="";
		String preUser="";
		String preCourse="";
		for(int i=0; i<preTermData.size();i++){
			Term t=(Term)preTermData.get(i);
			preTerm=preTerm+t.getName()+"\t"+t.getStartDate()+"\t"+t.getEndDate()+"\t"+t.getEnrollStart()+"\t"+t.getEnrollEnd()+"\n";
		}
		for(int i=0; i<preUserData.size();i++){
			User u=(User)preUserData.get(i);
			preUser=preUser+u.getStuNo()+"\t"+u.getPassword()+"\t"+u.getSchool()+"\t"+u.getBirthdate()+"\n";
		}
		for(int i=0; i<preCourseData.size();i++){
			Course c=(Course)preCourseData.get(i);
			preCourse=preCourse+c.getCourseCode()+"\t"+c.getCourseName()+"\t"+c.getLocation()+"\t"+c.getMeetingTimes()+"\n";
		}
		DBSensor dbs=new DBSensor();
		dbs.writeToLog("\n"+preTerm+preUser+preCourse);
	}
	
	public void outPutCurStateInDetail(){
		String curTerm="";
		String curUser="";
		String curCourse="";
		for(int i=0; i<curTermData.size();i++){
			Term t=(Term)curTermData.get(i);
			curTerm=curTerm+t.getName()+"\t"+t.getStartDate()+"\t"+t.getEndDate()+"\t"+t.getEnrollStart()+"\t"+t.getEnrollEnd()+"\n";
		}
		for(int i=0; i<curUserData.size();i++){
			User u=(User)curUserData.get(i);
			curUser=curUser+u.getStuNo()+"\t"+u.getPassword()+"\t"+u.getSchool()+"\t"+u.getBirthdate()+"\n";
		}
		for(int i=0; i<curCourseData.size();i++){
			Course c=(Course)curCourseData.get(i);
			curCourse=curCourse+c.getCourseCode()+"\t"+c.getCourseName()+"\t"+c.getLocation()+"\t"+c.getMeetingTimes()+"\n";
		}
		DBSensor dbs=new DBSensor();
		dbs.writeToLog("\n"+curTerm+curUser+curCourse);
	}
}
