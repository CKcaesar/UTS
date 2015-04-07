package edu.scs.carleton.comp.ls.view.utils;

import java.sql.SQLException;

public class ExceptionHandler 
{
	public static void display (String s, SQLException e)
	{	
		if (Debug.SHOW_SQL_EXCEPTIONS)
		{
			System.out.printf("%25s thrown from %25s\n",e.getClass().getName(), s);
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: "     + e.getSQLState());
			System.out.println("VendorError: "  + e.getErrorCode());
		}
	}
	
	public static void display (Object o, SQLException e)
	{	
		if (Debug.SHOW_SQL_EXCEPTIONS)
		{
			System.out.printf("%25s thrown from %25s\n",e.getClass().getName(), o.getClass().getName());
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: "     + e.getSQLState());
			System.out.println("VendorError: "  + e.getErrorCode());
		}
	}
	
	public static void display (Object o, String method, SQLException e)
	{	
		if (Debug.SHOW_SQL_EXCEPTIONS)
		{
			System.out.printf("%s thrown from %s method %s\n",e.getClass().getName(), o.getClass().getName(), method );
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: "     + e.getSQLState());
			System.out.println("VendorError: "  + e.getErrorCode());
		}
	}
	
	public static void display (String s, Exception e)
	{		
		System.out.printf("%25s thrown from %25s\n",e.getClass().getName(), s);
		System.out.println("Exception: " + e.getMessage());
	}
	
	public static void display (Object o, Exception e)
	{		
		System.out.printf("%25s thrown from %25s\n",e.getClass().getName(), o.getClass().getName());
		System.out.println("Exception: " + e.getMessage());
	}
	
	public static void display (Object o, Exception e, String method )
	{		
		System.out.printf("%25s thrown from %25s\n method %s",e.getClass().getName(), o.getClass().getName(), method);
		System.out.println("Exception: " + e.getMessage());
	}
	
}
