package edu.scs.carleton.comp.ls.view.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

public class Logger {

    public static class TestLogger {
	
    	private static PrintWriter test;
		private static final String fileName = "WebApp - " + Calendar.getInstance().getTime().toString().replaceAll(":", "-") + ".log";
		
	    public static void openFile(String logDir) {
	    	String logFile = logDir + fileName;
	    	
	    	try {
	    		File directory = new File(logDir);
	    		if (!directory.exists())
	    		{
	    			new File(logDir).mkdirs();
	    		}
	    		
				test = new PrintWriter(new BufferedWriter(new FileWriter(logFile, true)));

				writeMessage ("INFO: Writing to " +  logDir );
				writeMessage ("INFO: Opened Log File " + logFile);
			} catch (IOException e) {
			    //System.out.println("Logging IO exception");
			}
	    }
	    
	    public static void writeMessage(String message){
	    	test.println(message.replaceAll("\n", "\r\n"));
	    }
	    
	    public static void writeMessage(String message, String sParam1){
    		test.printf("Logging: [%s] [%s]\r\n", message, sParam1 );
	    }
	    
	    public static void writeMessage(String message, String sParam1, String sParam2){
    		test.printf("Logging: [%s] [%s] [%s]\r\n", message, sParam1, sParam2 );
	    }
	    
	    public static void closeFile(){
	    	test.close();
	    }
	    
    }
	
}
