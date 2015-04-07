package edu.comp.dbam;
/*    */ 
/*    */ import edu.scs.carleton.comp.ls.utils.ConfigProperties;

/*    */ import java.io.File;
/*    */ import java.io.FileInputStream;
/*    */ import java.io.IOException;
/*    */ import java.sql.ResultSet;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Properties;
/*    */ 
/*    */ 
/*    */ public abstract class DAO
/*    */   implements IDAO
/*    */ {
/* 15 */   protected DAOHelper _dao = null;
/* 16 */   protected Properties _sqlCode = null;
/*    */   
/*    */   public DAO() {}
/*    */   
/*    */   protected abstract Object getDataObject(ResultSet paramResultSet, boolean paramBoolean);
/*    */   
/*    */   protected void init()
/*    */   {
/*    */     try {
/* 25 */       String sqlFile = ConfigProperties.getInstance()._SQL_DIR;
/* 26 */       File resFile = new File(sqlFile);
/* 27 */       FileInputStream fileInput = new FileInputStream(resFile);
/* 28 */       this._sqlCode = new Properties();
/* 29 */       this._sqlCode.load(fileInput);
/* 30 */       fileInput.close();
/*    */     } catch (IOException e) {
/* 32 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */   
/*    */   public void destroy()
/*    */   {
/* 38 */     if (this._dao != null)
/*    */     {
/* 40 */       this._dao.returnConnection();
/* 41 */       this._dao = null;
/* 42 */       this._sqlCode = null;
/*    */     }
/*    */     try
/*    */     {
/* 46 */       super.finalize();
/*    */     } catch (Throwable t) {
/* 48 */       t.printStackTrace();
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   protected ArrayList<Object> processResultSet(ResultSet rs)
/*    */   {
/* 56 */     ArrayList<Object> list = new ArrayList<Object>();
/*    */     Object obj;
/* 58 */     while ((obj = getDataObject(rs, false)) != null) {
/* 59 */       list.add(obj);
/*    */     }
/* 61 */     this._dao.close(rs);
/* 62 */     return list;
/*    */   }
/*    */   
/*    */   protected void close(ResultSet rs) {
/* 66 */     this._dao.close(rs);
/*    */   }
/*    */ }