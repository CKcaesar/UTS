package edu.comp.dbam;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.comp.domain.Course;
import edu.comp.domain.StuCourse;
import edu.comp.domain.Term;

public class DBStuCourse extends DAO{
	public DBStuCourse(){
		super.init();
		this._dao=new DAOHelper();
	}
	
	public DBStuCourse(DAOHelper daoHelper){
		super.init();
		this._dao=daoHelper;
	}
	
	public boolean create(Object o){
		StuCourse stucourse=(StuCourse) o;
		return create(stucourse.getStuNo(),
				stucourse.getCourse(),
				stucourse.getRegisterDate(),
				stucourse.getTermName());
	}
	
	public boolean create(String stuNo, String course, String registerDate, String termName){
		String sql=this._sqlCode.getProperty("stucourse.insert");
		String sqlString=MessageFormat.format(sql,new Object[]{
				stuNo,
				course,
				registerDate,
				termName
		});
		return this._dao.executeUpdate(sqlString);
	}
	
	public ArrayList<Object> findAll(){
		String sql=this._sqlCode.getProperty("stucourse.findAll");
		return super.processResultSet(this._dao.executeLookup(sql, "stucourse.findAll"));
	}
	
	public boolean update(Object o){
		//.....
		return true;
	}
	
	protected Object getDataObject(ResultSet rs, boolean close){
		StuCourse stucourse = null;
		try{
			if(rs.next()){
				stucourse=new StuCourse(
						rs.getString(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4));		
			}
		}catch(SQLException e){
			edu.scs.carleton.comp.ls.utils.ExceptionHandler.display(this, e);
		}finally{
			if (close) close(rs);
		}return stucourse;
	}
	
	public boolean delete(int paraInt){
		return false;
	}
	
	public boolean delete(String courseCode){
		StuCourse stucourse = (StuCourse)findByPrimaryKey(courseCode);
		if(stucourse!=null){
			return delete(stucourse);
		}
		return false;
	}
	
	public boolean delete(String courseCode, String stuNo){
		String sql=this._sqlCode.getProperty("stucourse.deleteBycourseCodeandstuNo");
		String sqlString = MessageFormat.format(sql, new Object[] { courseCode, stuNo });
		return this._dao.executeUpdate(sqlString);
	}
	
	public boolean delete(Object o) {
		StuCourse stucourse = (StuCourse)o;
		
		String sql = this._sqlCode.getProperty("stucourse.deleteByCourseCode");
		String sqlString = MessageFormat.format(sql, new Object[] { stucourse.getCourse() });
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
	
	public Object find(Object o){
		return null;
	}
	
	public Object findByPrimaryKey(String primaryKey){
		return null;
	}

	public List<Object> findByTermId(Integer termId) {
		String sql=this._sqlCode.getProperty("stucourse.findByTermId");
		String sqlString=MessageFormat.format(sql, new Object[] {termId});
		return super.processResultSet(this._dao.executeLookup(sqlString, "stucourse.findByTermId"));
	}
	
	public List<Object> findByCourseID(String courseId) {
		String sql=this._sqlCode.getProperty("stucourse.findByCourseId");
		String sqlString=MessageFormat.format(sql, new Object[] {courseId});
		return super.processResultSet(this._dao.executeLookup(sqlString, "stucourse.findByCourseId"));
	}

	public List<Object> getListOfMyCourses(String stuNo) {
		String sql=this._sqlCode.getProperty("stucourse.findByStuNo");
		String sqlString=MessageFormat.format(sql, new Object[] {stuNo});
		return super.processResultSet(this._dao.executeLookup(sqlString, "stucourse.findByStuNo"));
	}
}
