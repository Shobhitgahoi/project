package common;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * This class contains static methods to read 'config.properties' property file and returns the given value
 * @author Shobhit Gahoi
 */
public class PropertyExecutor {
	
	/**
	 * Returns the value associated with the given key in the property file
	 * @param
	 * 		Key whose value need to be returned
	 * @return
	 * 		Property value for the given key
	 */
	@SuppressWarnings("unused")
	public static String getProperty(String arg){
		try{
			//LoggerAgent.LogInfo("Reading property value from config propertes file of " + arg);
			
			Properties prop = new Properties();
			File file=new File(System.getProperty("user.dir")+"/src/main/resources/config.properties");
			InputStream input =new FileInputStream(file) ;
					//PropertyExecutor.class.getClassLoader().getResourceAsStream("");
			if(input==null){
				LoggerAgent.LogInfo("[ERROR] Sorry, unable to find property file");
		  	}
			prop.load(input);
			return prop.getProperty(arg);
		}
		catch(Exception e){
			LoggerAgent.LogError("{EXCEPTION} occured while reading property value of " + arg);
			e.printStackTrace();
			return e.getMessage();
		}
	}
}

