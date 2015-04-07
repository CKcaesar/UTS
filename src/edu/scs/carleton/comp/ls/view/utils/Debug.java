package edu.scs.carleton.comp.ls.view.utils;

public class Debug {
	
	public static final boolean CREATE_TEST_DATA = false;
	public static final boolean SHOW_SQL_EXCEPTIONS = false;
	public static final boolean SHOW_STACK_TRACE = false;
	public static final boolean SHOW_EXCEPTIONS = false;
	public static final boolean TRACE_PROGRAM_FLOW = false;
	public static final boolean TRACE_CONNECTIONS = false;
	
	
	public static void traceConnection ( Object o, String method, int cID ) {
		if (TRACE_CONNECTIONS)
			System.out.printf ( "Tracing connection from [%s] method [%s] ID [%d]\n"
							  , o.getClass().getName(), method, cID); 
	}
	
	public static void trace ( Object o, String method, String msg) {
		if (TRACE_PROGRAM_FLOW)
			System.out.printf ( "Tracing [%s] method [%s] ID [%s]\n", o.getClass().getCanonicalName(), method, msg);
	}
	
	public static void trace ( Object o, String method, String msg, String value) {
		if (TRACE_PROGRAM_FLOW)
			System.out.printf ( "Tracing [%s] method [%s] Message [%s] [%s]\n", o.getClass().getCanonicalName(), method, msg, value);
	}
	
	public static void trace ( Object o, String method, String msg, int value) {
		if (TRACE_PROGRAM_FLOW)
			System.out.printf ( "Tracing [%s] method [%s] ID [%s] [%d]\n", o.getClass().getCanonicalName(), method, msg, value);
	}
	
	public static void trace ( Object o, String method, String function, String key, String variable, String value) {
		if (TRACE_PROGRAM_FLOW)
			System.out.printf ( "Tracing [%s] method [%s] ID [%s] [%s] [%s] [%s]\n"
							  , o.getClass().getCanonicalName()
							  , method
							  , function
							  , key
							  , variable
							  , value);
	}
	
}
