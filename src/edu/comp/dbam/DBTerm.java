package edu.comp.dbam;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Properties;

import edu.comp.domain.Term;
//import edu.scs.carleton.comp.ls.admin.User;

public class DBTerm extends DAO{
	public DBTerm(){
		super.init();
		this._dao=new DAOHelper();
	}
	
	public DBTerm(DAOHelper daoHelper){
		super.init();
		this._dao=daoHelper;
	}
	
	public boolean create(Object o){
		Term term=(Term) o;
		return create(term.getName(),
				term.getStartDate(),
				term.getEndDate(),
				term.getEnrollStart(),
				term.getEnrollEnd(),
				term.getDropDeadline());
	}
	
	public boolean create(String name, String startDate, String endDate, String enrollStart, String enrollEnd, String dropDeadline){
		String sql=this._sqlCode.getProperty("term.insert");
		String sqlString=MessageFormat.format(sql,new Object[]{
				name,
				startDate,
				endDate,
				enrollStart,
				enrollEnd,
				dropDeadline
		});
		return this._dao.executeUpdate(sqlString);
	}
	
	public Object findByPrimaryKey(String primaryKey){
		String sql=this._sqlCode.getProperty("term.findByName");
		String sqlString=MessageFormat.format(sql,new Object[]{
				primaryKey
		});
		return getDataObject(this._dao.executeLookup(sqlString, "users.findByPrimarykey"), true);
	}
	
	//use "name" update "endDate"
	public boolean update(Object o){
		Term term=(Term)o;
		if (findByPrimaryKey(term.getName())==null) 
			return false;
		String sql=this._sqlCode.getProperty("term.update");
		String sqlString = MessageFormat.format(sql, new Object[]{
				term.getName(),
				term.getEndDate()
		});
		return this._dao.executeUpdate(sqlString);
	}
	
	protected Object getDataObject(ResultSet rs, boolean close){
		Term term = null;
		try{
			if(rs.next()){
				term=new Term(rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6),
						rs.getString(7));		
			}
		}catch(SQLException e){
			edu.scs.carleton.comp.ls.utils.ExceptionHandler.display(this, e);
		}catch(Exception e){
			edu.scs.carleton.comp.ls.utils.ExceptionHandler.display(this,e);
		}finally{		
			if (close) close(rs);
		}return term;
	}
	
	public ArrayList<Object> findByName(String name){
		String sql=this._sqlCode.getProperty("term.findByName");
		String sqlString=MessageFormat.format(sql, new Object[] {name});
		return super.processResultSet(this._dao.executeLookup(sqlString, "term.findByName"));
	}
	
	public Object findByTermName(String primaryKey){
		String sql=this._sqlCode.getProperty("term.findByTermName");
		String sqlString=MessageFormat.format(sql,new Object[]{
				primaryKey
		});
		return getDataObject(this._dao.executeLookup(sqlString, "term.findByTermName"), true);
	}
	
	public ArrayList<Object> findAll(){
		String sql=this._sqlCode.getProperty("term.findAll");
		//String sqlString=MessageFormat.format(sql, new Object[]{});
		return super.processResultSet(this._dao.executeLookup(sql, "term.findAll"));
		//return super.processResultSet(this._dao.executeLookup(sqlString, "term.findAll"));
	}
	
	//update term with termName and enrollStart
	public boolean updateBy(String name, String enrollStart, String enrollEnd){
		String sql=this._sqlCode.getProperty("term.updateBytermName");
		String sqlString=MessageFormat.format(sql, new Object[] {name, enrollStart, enrollEnd});
		return this._dao.executeUpdate(sqlString);
	}
	
	public Object find(Object o){
		return null;
	}
	
	public boolean delete(int paraInt){
		return false;
	}
	
	public boolean delete(String termName){
		Term term = (Term)findByPrimaryKey(termName);
		if(term!=null){
			return delete(term);
		}
		return false;
	}
	
	public boolean delete(Object o) {
		Term term = (Term)o;
		
		String sql = this._sqlCode.getProperty("term.deleteByTermName");
		String sqlString = MessageFormat.format(sql, new Object[] { term.getName() });
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
	
	public ArrayList<Object> getListOfTerms(){
		String sql = this._sqlCode.getProperty("term.getList");
	    return super.processResultSet(this._dao.executeLookup(sql, "term.getList"));
	}
}
