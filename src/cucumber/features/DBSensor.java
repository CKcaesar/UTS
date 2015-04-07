package cucumber.features;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class DBSensor {
	
	public void writeToLog(String msg){
		FileWriter fw;
		try {
			fw = new FileWriter("D:\\log.txt",true);
			Date date=new Date();
			String logMsg=date.toString()+" "+msg+"\n";
			fw.write(logMsg,0,logMsg.length());  
			fw.flush();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void writeToLogInt(int num){
		FileWriter fw;
		try {
			fw = new FileWriter("D:\\log.txt",true);
			Date date=new Date();
			String logMsg=date.toString()+" "+num+" row(s) affected.\n";
			fw.write(logMsg,0,logMsg.length());  
			fw.flush();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
