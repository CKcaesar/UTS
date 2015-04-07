package edu.scs.carleton.comp.ls.view;

import java.util.Vector;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;

import edu.scs.carleton.comp.ls.dbam.connection.ConnectionPool;
import edu.scs.carleton.comp.ls.utils.ConfigProperties;
import edu.scs.carleton.comp.ls.view.managers.CacheManager;
import edu.scs.carleton.comp.ls.view.utils.BackgroundProcesses;
import edu.scs.carleton.comp.ls.view.utils.Logger;
import edu.scs.carleton.comp.ls.view.utils.Utils;

public class ObjectLifetimeManager extends HttpServlet {
	
	private static final long serialVersionUID = -6568228153043291246L;

	private static final String ROOT_DIR = Utils.getContextRoot();
	private static final String WEB_INF = "WEB-INF/classes";
	
	private static String _rootDir = null;

	private static ConnectionPool connPool = null;
	
	private static CacheManager cacheMgr = null;

	private static Vector <ManagedObject> container = new Vector <ManagedObject> ();
	
	public void init() throws ServletException {		
		 _rootDir = ROOT_DIR + WEB_INF;
		 ConfigProperties.getInstance(_rootDir);
		
		 Logger.TestLogger.openFile(ConfigProperties.getInstance(_rootDir)._LOG_DIR);
		 Logger.TestLogger.writeMessage("INFO: Starting Web Application");

		 connPool = ConnectionPool.getInstance();
		 Logger.TestLogger.writeMessage("INFO: Connection Pool initialized");
		
		 cacheMgr = new CacheManager ();
		 Logger.TestLogger.writeMessage("INFO: Cache Manager initialized");
		
		 Logger.TestLogger.writeMessage("INFO: Background Processes started");
		 BackgroundProcesses.start();
	}
	
	public static void registerObject (ManagedObject obj) {
		container.add (obj);
		Logger.TestLogger.writeMessage(obj.getClass().getName(), "Added to the Object Lifetime Manager");
	}
	
	private static void shutdownContainer () {
		for (ManagedObject obj : container) {
			obj.cleanup();
		}
		container.clear();
	}

	public void destroy () {
		Logger.TestLogger.writeMessage("WebApp: INFO: Cleaning up ObjectLifetimeManager");

		BackgroundProcesses.stop();

		cacheMgr.destroy();
		connPool.unregisterDrivers();
		connPool.shutDown();
		
		shutdownContainer();
		ConfigProperties.getInstance(_rootDir).destroy();

		System.gc();
	}

	@Override
	public void finalize () throws Throwable {
		Logger.TestLogger.writeMessage("WebApp: INFO: Finalizing the ObjectLifetimeManager");

		container = null;
		cacheMgr = null;
		connPool = null;
		container = null;
		Logger.TestLogger.closeFile();
		super.finalize();
	}
}
