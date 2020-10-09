package pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import common.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelDataReader {
	public String projectpath;
	
public XSSFWorkbook workbook;
public XSSFSheet sheet;

public ExcelDataReader() {
	projectpath=System.getProperty("user.dir");
	try {
		
		File src= new File(projectpath+"\\src\\main\\resources\\TestData.xlsx");
		FileInputStream fis= new FileInputStream(src);
		try {
			this.workbook=new XSSFWorkbook(fis);
		}catch(IOException e) {
			e.printStackTrace();	
		}
	}catch(FileNotFoundException e) {
		e.printStackTrace();
	}
}



/**
 * @author Shobhit Gahoi
 *
 * Reading data based on sheet name , row number and column number
 * @return String
 * @tag  @param sheetName
 * @tag  @param rowNumber
 * @tag  @param columnNumber
 * @tag  @return
 * @tag  @throws IOException
 */
public String readingData(String sheetName,int rowNumber,int columnNumber)throws IOException{
	sheet=workbook.getSheet(sheetName);
	String value=sheet.getRow(rowNumber).getCell(columnNumber).getStringCellValue();
	return value;
}



/**
 * @author Shobhit Gahoi
 *
 *
 * @return int
 * @tag  @param filePath
 * @tag  @param sheetName
 * @tag  @return
 * @tag  @throws FileNotFoundException
 */
@SuppressWarnings("resource")
public static int getLastRowIndex(String filePath, String sheetName) throws FileNotFoundException{
	int rowNum=0;
	File file =new File(filePath);
	FileInputStream fis=new FileInputStream(file);
	if(filePath.substring(filePath.lastIndexOf(".")+1).equalsIgnoreCase("xls")) {
		
		try {
			rowNum=new HSSFWorkbook(fis).getSheet(sheetName).getLastRowNum();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}else if(filePath.substring(filePath.lastIndexOf(".")+1).equalsIgnoreCase("xlsx")) {
		try {
			rowNum=new XSSFWorkbook(fis).getSheet(sheetName).getLastRowNum();
			//System.out.println("row num: "+rowNum);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	return rowNum;
	
}
	
/*
* Writes a given string value in a particular cell in the excel workbook
* 
* @param filePath
* 			Fully defined file path of the excel sheet
* @param sheetName
* 			The name of the workbook inside the excel sheet in which the cell is present
* @param rowNum
* 			The row number of the cell in the workbook {starts from index 0}
* @param colNum
* 			The column number of the cell in the workbook {starts from index 0}
* @param valueToBeWritten
* 			The string value to be entered in the cell
* 
* @return 
* 			true if executed successfully else false
*/
public synchronized static boolean writeIntoCell(String filePath, String sheetName , int rowNum , int colNum , String[] valueToBeWritten){
	try{
		FileInputStream excelFile = new FileInputStream(filePath);
		Workbook workbook = new XSSFWorkbook(excelFile);
       Sheet sheet = workbook.getSheet(sheetName);
       //Row row = sheet.getRow(rowNum);
       Row newRow=sheet.createRow(rowNum);
      // Cell cell = newRow.createCell(colNum);
       
       for(int j = 0; j < valueToBeWritten.length; j++){

           //Fill data in row

           Cell cell = newRow.createCell(j);

           cell.setCellValue(valueToBeWritten[j]);

       }

       FileOutputStream fos = new FileOutputStream(filePath);
       workbook.write(fos);
       workbook.close();
       excelFile.close();
       fos.close();
       
       return true;
	}
	catch(Exception e){
		LoggerAgent.LogError("{EXCEPTION} Writing into excel file. File Path : " +  filePath + " but general exception occured " + e.getMessage());
		
		LoggerAgent.LogInfo("[EXCEPTION] Unexpected Exception occurred while reading excel file - Method{writeIntoCell} Class{ExcelOperations}!!");
		e.printStackTrace();
	}
	return false;
}

}
