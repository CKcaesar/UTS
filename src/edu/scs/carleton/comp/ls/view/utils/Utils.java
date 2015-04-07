package edu.scs.carleton.comp.ls.view.utils;

import java.net.URL;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import edu.scs.carleton.comp.ls.view.utils.Debug;

public class Utils {
	    
	public static void getSessionAttributes () {
		FacesContext facesContext = FacesContext.getCurrentInstance();  		
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		Enumeration<String> e = session.getAttributeNames();
		
		while (e.hasMoreElements()) {
			String key = e.nextElement();
			Debug.trace(new Object(),"getSessionAttributes","Session Attribute", key, "value", session.getAttribute(key).getClass().getName());
		}
	}
	
	public static void getSystemProperties () {
		
		Properties properties = System.getProperties();		
		Enumeration<Object> e = properties.keys();
	
		while (e.hasMoreElements()) {
			String key = (String)e.nextElement();
			Debug.trace(new Object(),"getSystemProperties","System Properties", key, "value", properties.getProperty(key));
		}
		
	}
	
	public static void getEnvironmentVariables  () {
		
		Map<String, String> env = System.getenv();
		for (String key : env.keySet()) {
			Debug.trace(new Object(),"getEnvironmentVariables","Environment variable", key, "value", env.get(key));
		}
	}
	
	public static String getWorkingDirectory () {
		URL location = Utils.class.getProtectionDomain().getCodeSource().getLocation();
		
        System.out.println(location.getFile());
        System.out.println(location.getPath());
        return location.getPath();
	}

	public static String getContextRoot () {		
		ClassLoader loader = Utils.class.getClassLoader();
		String path = loader.getResource("edu/scs/carleton/comp/ls/view/utils/Utils.class").toString();
		return ( path.substring(6,path.indexOf("WEB-INF/classes/edu")) );
	}
	
}
