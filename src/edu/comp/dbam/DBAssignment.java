package edu.comp.dbam;


import edu.comp.domain.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;


public class DBAssignment extends DAO{
	
    	public DBAssignment(){
    		super.init();
    		this._dao=new DAOHelper();
    	}
    	 public DBAssignment(DAOHelper dAOHelper) {
    		    super.init();
    		    this._dao = dAOHelper;
    		  }
     public boolean create(Object o)
     {
    	Assignment assign=(Assignment)o;
    	//Term term=(Term)o;
        return create(
        		assign.getDueDate(),
        		assign.getCourseID(),
        		assign.getDescription(),
        		assign.getAssignName(),
        		assign.getType()
        		);
    
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
		Assignment ass = null;
		try{
			if(rs.next()){
				ass=new Assignment(rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6));		
			}
		}catch(SQLException e){
			edu.scs.carleton.comp.ls.utils.ExceptionHandler.display(this, e);
		}finally{
			if (close) close(rs);
		}return ass;
	}
	
	public List<Object> findByCourseID(String courseID) {
		String sql=this._sqlCode.getProperty("assignment.findByCourseID");
		String sqlString=MessageFormat.format(sql, new Object[]{ courseID});
		return super.processResultSet(this._dao.executeLookup(sqlString, "assignment.findByCourseID"));
	}

	public List<Object> findGradeByCourseID(String courseID) {
		String sql=this._sqlCode.getProperty("assignment.findGradeByCourseID");
		String sqlString=MessageFormat.format(sql, new Object[]{ courseID});
		return super.processResultSet(this._dao.executeLookup(sqlString, "assignment.findGradeByCourseID"));
	}

}
