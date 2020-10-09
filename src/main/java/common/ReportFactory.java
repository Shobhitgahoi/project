/**
 * 
 */
package common;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;

/**
 * This class contains static methods to create Extent reports and publish them
 * @author Shobhit Gahoi
 *
 */
public class ReportFactory {
	
	static ExtentReports reporter = null;
	public static String reportfilePath = null;
	
	/**
	 * Creates report with a given name and returns the reporter instance of it
	 * @param 
	 * 		The name of the report
	 * @return
	 * 		The instance of the created report
	 */
	public static synchronized ExtentReports getReporter(String reportName){
		try{
			reportfilePath = PropertyExecutor.getProperty("testReportFilePath") + reportName + ".html";
			if (reporter == null) {
				reporter = new ExtentReports(reportfilePath, true, DisplayOrder.OLDEST_FIRST);
			}
			LoggerAgent.LogInfo("The Report Path is: " +reportfilePath);
			return reporter;
		}catch(Exception e){
			LoggerAgent.LogInfo("[EXCEPTION] Exception encountered while reading property file - Method{getReporter} Class{ReportFactory}!!");
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Closed the given reporter instance
	 */
	public static synchronized void closeReporter() {
		reporter.flush();
		reporter.close();
	}
}
