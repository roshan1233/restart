package generic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.Reporter;

public abstract class Utility {
	
	public static String getProperties(String path,String property)
	{
		String url="";
		try {
			Properties prop = new Properties();
			prop.load(new FileInputStream(path));
			url=prop.getProperty(property);
		}
		catch(Exception e)
		{
			Reporter.log("Not able to get data", true);
		}
		return url;
	}
	
	public static String getExcelData(String fileName, String sheetName, int row, int column) throws EncryptedDocumentException, FileNotFoundException, IOException
	{
		try {
			Workbook wb = WorkbookFactory.create(new FileInputStream(fileName));
			Sheet sheetNam = wb.getSheet(sheetName);
			Row rowNum = sheetNam.getRow(row);
			String data = rowNum.getCell(column).getStringCellValue();
			return data;
		}
		catch(Exception e)
		{
			Reporter.log("Data not found", true);
			return "";
		}
		
	}

}
