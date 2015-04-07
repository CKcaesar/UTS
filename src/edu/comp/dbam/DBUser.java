package edu.comp.dbam;

import cucumber.features.DBSensor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Properties;

import cucumber.features.DBSensor;
import edu.comp.domain.User;

//import edu.scs.carleton.comp.ls.admin.Role;
//import edu.scs.carleton.comp.ls.dbam.DBRole;
public class DBUser extends DAO{
	
	DBSensor dbs=new DBSensor();
	//private static Integer number=1;
	public int mysql_affected_rows;
	public DBUser(){
		super.init();
		this._dao=new DAOHelper();
	}
	
	public DBUser(DAOHelper daoHelper){
		super.init();
		this._dao=daoHelper;
	}
	
	public boolean create(Object o){
		User user=(User) o;
		return create(
				user.getStuNo(),
				user.getPassword(),
				user.getFirstname(),
				user.getLastname(),
				user.getMiddleinitial(),
				user.getBirthdate(),
				user.getSchool(),
				user.getDegree(),
				user.getTime_status());
	}
	
	public boolean create(String stuNo, String password, String firstname, String lastname, String middleinitial, String birthdate, String school, String degree, String time_status){
		//String stuNo="stu"+Math.random()*10000;
		//String password="ps"+Math.random()*1000;
		//String middleinitial=null;
		//String degree=null;
		//String time_status=null;

		String sql=this._sqlCode.getProperty("stu.insert");
		String sqlString=MessageFormat.format(sql,new Object[]{
				stuNo,
				password,
				firstname,
				lastname,
				middleinitial,
				birthdate,
				school,
				degree,
				time_status
		});
		boolean flag=this._dao.executeUpdate(sqlString);
		mysql_affected_rows=this._dao.mysql_affected_rows;
		dbs.writeToLogInt(mysql_affected_rows);
		return flag;
	}
	
	public Object findByPrimaryKey(String primaryKey){
		String sql=this._sqlCode.getProperty("stu.findByStuNo");
		String sqlString=MessageFormat.format(sql,new Object[]{
				primaryKey
		});
		return getDataObject(this._dao.executeLookup(sqlString, "stu.findByStuNo"), true);
	}
	
	public boolean update(Object o){
		User user=(User)o;
		if (findByPrimaryKey(user.getStuNo())==null) 
			return false;
		String sql=this._sqlCode.getProperty("stu.update");
		String sqlString = MessageFormat.format(sql, new Object[]{
				user.getStuNo(),
				user.getPassword()
		});
		return this._dao.executeUpdate(sqlString);
	}
	
	public ArrayList<Object> findAll(){
		String sql=this._sqlCode.getProperty("stu.findAll");
		return super.processResultSet(this._dao.executeLookup(sql, "stu.findAll"));
	}
	
	public ArrayList<Object> findAllX(){
		String sql=this._sqlCode.getProperty("stu.findAllX");
		return super.processResultSet(this._dao.executeLookup(sql, "stu.findAllX"));
	}
	
	protected Object getDataObject(ResultSet rs, boolean close){
		User user = null;
		try{
			if(rs.next()){
				user=new User(rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6),
						rs.getString(7),
						rs.getString(8),
						rs.getString(9),
						rs.getString(10));			
			}
		}catch(SQLException e){
			edu.scs.carleton.comp.ls.utils.ExceptionHandler.display(this, e);
		}finally{
			if (close) close(rs);
		}return user;
	}
	
	public Object find(Object o){
		return null;
	}
	
	public boolean delete(int paraInt){
		return false;
	}
	
	public boolean delete(String paraString){
		return false;
	}
	
	public boolean delete(Object paramObject) {
		// TODO Auto-generated method stub
		return false;
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
	
}