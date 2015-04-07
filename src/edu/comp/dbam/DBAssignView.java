package edu.comp.dbam;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import edu.comp.domain.AssignView;
import edu.comp.domain.Assignment;


public class DBAssignView extends DAO{

	public DBAssignView(){
		super.init();
		this._dao=new DAOHelper();
	}
	
	public DBAssignView(DAOHelper dAOHelper) {
		    super.init();
		    this._dao = dAOHelper;
		  }
	
	@Override
	public boolean create(Object paramObject) {
		// TODO Auto-generated method stub
		return false;
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
	protected Object getDataObject(ResultSet rs, boolean close) {
		// TODO Auto-generated method stub
		AssignView ass = null;
		try{
			if(rs.next()){
				ass=new AssignView(rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6),
						rs.getString(7));		
			}
		}catch(SQLException e){
			edu.scs.carleton.comp.ls.utils.ExceptionHandler.display(this, e);
		}finally{
			if (close) close(rs);
		}return ass;
	}

	public List<Object> findGradeByCourseID(String courseID) {
		String sql=this._sqlCode.getProperty("assignment.findGradeByCourseID");
		String sqlString=MessageFormat.format(sql, new Object[]{ courseID});
		return super.processResultSet(this._dao.executeLookup(sqlString, "assignment.findGradeByCourseID"));
	}
}
