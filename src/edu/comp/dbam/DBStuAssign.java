package edu.comp.dbam;


import edu.comp.domain.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;


public class DBStuAssign extends DAO{
	
   	public DBStuAssign(){
   		super.init();
    	this._dao=new DAOHelper();
   	}
   	
    public DBStuAssign(DAOHelper dAOHelper) {
    	super.init();
    	this._dao = dAOHelper;
    }
    
    public boolean create(Object o){
    	return true; 
    }

	public boolean create(String dueDate, String courseID, String description,String assignName, String type) 
	{
		String sql=this._sqlCode.getProperty("assignment.insert");
		String sqlString= MessageFormat.format(sql, new Object[]{ 
					dueDate,courseID,description,assignName,type});
		return this._dao.executeUpdate(sqlString);
	}
	
	public ArrayList<Object> findByCourseCode(String courseID) {
		String sql=this._sqlCode.getProperty("assignment.findByCourseID");
		String sqlString= MessageFormat.format(sql, new Object[]{courseID});
		return super.processResultSet(this._dao.executeLookup(sqlString,"assignment.findByCourseID"));
	}
	
	public boolean update(int stuID, int assignID, String content){
		String sql=this._sqlCode.getProperty("stuassign.updateContent");
		String sqlString= MessageFormat.format(sql, new Object[]{ 
					stuID, assignID, content});
		return this._dao.executeUpdate(sqlString);
	}
	
	public boolean updateGrade(int stuAssignID, String grade){
		String sql=this._sqlCode.getProperty("stuassign.updateGrade");
		String sqlString= MessageFormat.format(sql, new Object[]{stuAssignID, grade});
		return this._dao.executeUpdate(sqlString);
	}
	
	
	@Override
	public boolean update(Object paramObject) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int paramInt) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(String paramString) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Object paramObject) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteAll() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object findByPrimaryKey(int paramInt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object findByPrimaryKey(String paramString) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object find(Object paramObject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected Object getDataObject(ResultSet rs,boolean close){
		StuAssign stuassign = null;
		try{
			if(rs.next()){
				stuassign=new StuAssign(rs.getInt(1),
						rs.getInt(2),
						rs.getInt(3),
						rs.getDate(4),
						rs.getString(5),
						rs.getString(6));		
			}
		}catch(SQLException e){
			edu.scs.carleton.comp.ls.utils.ExceptionHandler.display(this, e);
		}finally{
			if (close) close(rs);
		}return stuassign;
	}
	
	public List<Object> findStuAssignByCourseID(String courseID) {
		String sql=this._sqlCode.getProperty("stuassign.findStuAssignByCourseID");
		String sqlString=MessageFormat.format(sql, new Object[]{ courseID});
		return super.processResultSet(this._dao.executeLookup(sqlString, "stuassign.findStuAssignByCourseID"));
	}

	public List<Object> findAll() {
		String sql=this._sqlCode.getProperty("stuassign.findAll");
		return super.processResultSet(this._dao.executeLookup(sql, "stuassign.findAll"));
	}

	

}
