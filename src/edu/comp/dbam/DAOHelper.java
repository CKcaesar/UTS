package edu.comp.dbam;

/*     */ import edu.scs.carleton.comp.ls.dbam.connection.MySqlConnection;
/*     */ import edu.scs.carleton.comp.ls.utils.ExceptionHandler;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ 
/*     */ 
/*     */ public class DAOHelper
/*     */ {
/*  12 */   private MySqlConnection connection = null;
			public int mysql_affected_rows = 0;
/*     */   
/*     */   public DAOHelper()
/*     */   {
/*  16 */     this.connection = new MySqlConnection();
/*     */   }
/*     */   
/*     */   public boolean execute(String sql)
/*     */   {
/*  21 */     return this.connection.executeStatement(sql);
/*     */   }
/*     */   
/*     */   public boolean executeUpdate(String sql)
/*     */   {
/*  26 */     	mysql_affected_rows=this.connection.executeUpdate(sql);
				return mysql_affected_rows != 0;
/*     */   }
/*     */   
/*     */   public ResultSet executeLookup(String sql, String originator)
/*     */   {
/*  31 */     PreparedStatement ps = null;
/*     */     try {
/*  33 */       ps = this.connection.prepareStatement(sql);
/*  34 */       return ps.executeQuery(sql);
/*     */     } catch (SQLException e) {
/*  36 */       ExceptionHandler.display(this, originator, e);
/*     */     } catch (Exception e) {
/*  38 */       ExceptionHandler.display(this, "executeLookup", e);
/*     */     }
/*     */     
/*  41 */     return null;
/*     */   }
/*     */   
/*     */   public boolean executeStoredProcedure(String sql, String originator)
/*     */   {
/*  46 */     PreparedStatement ps = null;
/*     */     try
/*     */     {
/*  49 */       ps = this.connection.prepareStatement(sql);
/*  50 */       return ps.execute();
/*     */     } catch (SQLException e) {
/*  52 */       ExceptionHandler.display(this, originator, e);
/*     */     } catch (Exception e) {
/*  54 */       ExceptionHandler.display(this, "executeStoredProcedure", e);
/*     */     }
/*     */     
/*  57 */     return false;
/*     */   }
/*     */   
/*     */   public int getCount(String sql, String originator)
/*     */   {
/*     */     try {
/*  63 */       PreparedStatement ps = this.connection.prepareStatement(sql);
/*  64 */       ResultSet rs = ps.executeQuery(sql);
/*     */       
/*  66 */       rs.next();
/*  67 */       int count = rs.getInt(1);
/*  68 */       rs.close();
/*     */       
/*  70 */       return count;
/*     */     } catch (SQLException e) {
/*  72 */       ExceptionHandler.display(this, originator, e);
/*     */     } catch (Exception e) {
/*  74 */       ExceptionHandler.display(this, "getCount", e);
/*     */     }
/*     */     
/*  77 */     return -1;
/*     */   }
/*     */   
/*     */   public boolean returnConnection()
/*     */   {
/*  82 */     if (this.connection != null) {
/*  83 */       this.connection.returnConnection();
/*  84 */       this.connection = null;
/*     */     }
/*  86 */     return true;
/*     */   }
/*     */   
/*     */   protected ResultSet getNextRow(ResultSet rs)
/*     */   {
/*     */     try {
/*  92 */       if (rs.next())
/*  93 */         return rs;
/*     */     } catch (SQLException e) {
/*  95 */       ExceptionHandler.display(this, "getNextRow", e);
/*  96 */       return null;
/*     */     }
/*     */     
/*  99 */     return rs;
/*     */   }
/*     */   
/*     */   protected void close(ResultSet rs) {
/* 103 */     if (rs != null) {
/*     */       try
/*     */       {
/* 106 */         rs.close();
/*     */       } catch (SQLException e) {
/* 108 */         ExceptionHandler.display(this, "ResultSet exception", e);
/*     */       }
/*     */     }
/*     */   }
/*     */ }

/* Location:           D:\KaiChen\WebApp4\WebContent\WEB-INF\lib\app-model-3.1.1.1.jar
 * Qualified Name:     edu.scs.carleton.comp.ls.dbam.DAOHelper
 * Java Class Version: 7 (51.0)
 * JD-Core Version:    0.7.0.1
 */
