package pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

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
//Read data based on sheet name , row number and column number
public String readingData(String sheetName,int rowNumber,int columnNumber)throws IOException{
	sheet=workbook.getSheet(sheetName);
	String value=sheet.getRow(rowNumber).getCell(columnNumber).getStringCellValue();
	return value;
}
}
