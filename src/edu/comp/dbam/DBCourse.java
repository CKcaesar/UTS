package edu.comp.dbam;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.comp.domain.Course;

public class DBCourse extends DAO{
	public DBCourse(){
		super.init();
		this._dao=new DAOHelper();
	}
	
	public DBCourse(DAOHelper daoHelper){
		super.init();
		this._dao=daoHelper;
	}
	
	public boolean create(Object o){
		Course course=(Course) o;
		return create(course.getCourseCode(),
				course.getCourseName(),
				course.getTermid(),
				course.getMeetingTimes(),
				course.getTime(),
				course.getLocation());
	}
	
	public boolean create(String courseCode, String courseName, Integer termid, String meetingTimes, String time, String location){
		String sql=this._sqlCode.getProperty("course.insert");
		String sqlString=MessageFormat.format(sql,new Object[]{
				courseCode,
				courseName,
				termid,
				meetingTimes,
				time,
				location
		});
		return this._dao.executeUpdate(sqlString);
	}
	
	public Object findByPrimaryKey(String primaryKey){
		String sql=this._sqlCode.getProperty("course.findByCourseCode");
		String sqlString=MessageFormat.format(sql,new Object[]{
				primaryKey
		});
		return getDataObject(this._dao.executeLookup(sqlString, "course.findByCourseCode"), true);
	}
	
	//use "name" update "endDate"
	public boolean update(Object o){
		Course course=(Course)o;
		if (findByPrimaryKey(course.getCourseName())==null) 
			return false;
		String sql=this._sqlCode.getProperty("course.update");
		String sqlString = MessageFormat.format(sql, new Object[]{
				course.getCourseName(),
				course.getLocation()
		});
		return this._dao.executeUpdate(sqlString);
	}
	
	protected Object getDataObject(ResultSet rs, boolean close){
		Course course = null;
		try{
			if(rs.next()){
				course=new Course(rs.getString(1),
						rs.getString(2),
						rs.getInt(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6));		
			}
		}catch(SQLException e){
			edu.scs.carleton.comp.ls.utils.ExceptionHandler.display(this, e);
		}finally{
			if (close) close(rs);
		}return course;
	}
	
	public ArrayList<Object> findByName(String name){
		String sql=this._sqlCode.getProperty("term.findByName");
		String sqlString=MessageFormat.format(sql, new Object[] {name});
		return super.processResultSet(this._dao.executeLookup(sqlString, "term.findByName"));
	}
	
	public ArrayList<Object> findByTermId(int termid){
		String sql=this._sqlCode.getProperty("course.findByTermId");
		String sqlString=MessageFormat.format(sql, new Object[] {termid});
		return super.processResultSet(this._dao.executeLookup(sqlString, "course.findByTermId"));
	}
	
	public ArrayList<Object> findAll(){
		String sql=this._sqlCode.getProperty("course.findAll");
		//String sqlString=MessageFormat.format(sql, new Object[]{});
		return super.processResultSet(this._dao.executeLookup(sql, "course.findAll"));
		//return super.processResultSet(this._dao.executeLookup(sqlString, "term.findAll"));
	}
	
	public Object find(Object o){
		return null;
	}
	
	public boolean delete(int paraInt){
		return false;
	}
	
	public boolean delete(String courseCode){
		Course course = (Course)findByPrimaryKey(courseCode);
		if(course!=null){
			return delete(course);
		}
		/*String sql = this._sqlCode.getProperty("course.deleteByCourseCode");
		return this._dao.executeUpdate(sql);*/
		return false;
	}
	
	public boolean delete(Object o) {
		Course course = (Course)o;
		
		String sql = this._sqlCode.getProperty("course.deleteByCourseCode");
		String sqlString = MessageFormat.format(sql, new Object[] { course.getCourseCode() });
		return this._dao.executeUpdate(sqlString);
	}
	
	public boolean deleteAll() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public Object findByPrimaryKey(int paramInt) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<Object> getListOfCourses() {
		String sql = this._sqlCode.getProperty("course.getList");
	    return super.processResultSet(this._dao.executeLookup(sql, "course.getList"));
	}

	public List<Object> findByTermID(Integer termId) {
		String sql=this._sqlCode.getProperty("course.findByTermId");
		String sqlString=MessageFormat.format(sql, new Object[] {termId});
		return super.processResultSet(this._dao.executeLookup(sqlString, "course.findByTermId"));
	}


}
