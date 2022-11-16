package helper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataProvider {
	
	public  XSSFWorkbook wb;
	public ExcelDataProvider() throws IOException
	{
		FileInputStream fis= new FileInputStream(System.getProperty("user.dir")+"\\resource\\data.xlsx");
		 wb = new XSSFWorkbook(fis);	
	}
	
	public  String getStringData(String sheetName, int row, int column)
	{
		
		return wb.getSheet(sheetName).getRow(row).getCell(column).getStringCellValue();
	}
	
	public  double getNumericData(String sheetName, int row, int column)
	{
		
		return wb.getSheet(sheetName).getRow(row).getCell(column).getNumericCellValue();
	}
	
	
	

}
