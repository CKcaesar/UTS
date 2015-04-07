package edu.scs.carleton.comp.ls.view.utils;

import edu.scs.carleton.comp.ls.dbam.DBLoan;
import edu.scs.carleton.comp.ls.utils.ConfigProperties;

public class BackgroundProcesses {
	public static boolean doRun;
	static Thread u = new SetFinesThread();

    public static void start () {
    	doRun = true;
    	u.setDaemon(true);
        u.start();
    }
    
    public static void stop () {
    	doRun = false;
        u.interrupt();
    }
    
   private static class SetFinesThread extends Thread {
        private final int TIMEOUT = ConfigProperties.getInstance().LENGTH_OF_DAY;

        public void run() {
        	DBLoan dbLoan = new DBLoan();
            while (doRun) {
                try {
                    Thread.sleep(TIMEOUT);
                    dbLoan.setFines();
                } catch (InterruptedException ex) {}
            }
            dbLoan.destroy();
      	}
	}
}
