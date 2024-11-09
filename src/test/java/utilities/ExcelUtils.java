package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	
	public  FileInputStream fi;
	public  FileOutputStream fo;
	public  XSSFWorkbook workbook;
	public  XSSFSheet sheet;
	public  XSSFRow row;
	public  XSSFCell cell;
	public  CellStyle style;
	String path;
	
	public ExcelUtils(String path) {
		this.path=path;
		
	}
	
	public  int getRowCount(String sheetName) throws IOException {
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);		
		int rowcount=sheet.getLastRowNum();

		workbook.close();
		fi.close();		
		return rowcount;
	}

	
	public  int getCellCount(String sheetName,int rownum) throws IOException {
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		row = sheet.getRow(rownum);
		int cellcount=row.getLastCellNum();
		workbook.close();
		fi.close();		
		return cellcount;
	}

	public  String getCellData(String sheetName,int rownum,int colnum) throws IOException {
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		row = sheet.getRow(rownum);
		cell=row.getCell(colnum);
		
		DataFormatter a= new DataFormatter();// return thr data in string format no matter the type of cell value is
		String data;
		//we have used try catch because some cell might be blank then it will throw excel
		try {
			//data =cell.toString();
			
			data =a.formatCellValue(cell);
		}
		catch(Exception e) {
			data="";
			//System.out.print(e.getMessage());
		}
		workbook.close();
		fi.close();	
		
		return data;
	}
	
	public  void setCellData(String sheetName,int rownum,int colnum,String data) throws IOException {
		File xlfile =new File(path);
		if(!xlfile.exists()) {//if file not exist then create new file
			workbook=new XSSFWorkbook();
			fo=new FileOutputStream(path);
			workbook.write(fo);
		}
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		
		if(workbook.getSheetIndex(sheetName)==-1)//if sheet not exist create one
			workbook.createSheet(sheetName);
       sheet=workbook.getSheet(sheetName); 
       
       if(sheet.getRow(rownum)==null)//if row not exist create new
    	   sheet.createRow(rownum);
		row = sheet.getRow(rownum);
		
		cell=row.createCell(colnum);
		cell.setCellValue(data);
		
		//saving the entire wb into file f2
		fo=new FileOutputStream(path);
		workbook.write(fo);
		
		workbook.close();
		fi.close();	
		fo.close();
	
	}
	
	public  void fillgreencolor(String sheetName,int rownum,int colnum) throws IOException {
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		row = sheet.getRow(rownum);
		cell=row.getCell(colnum);
		style=workbook.createCellStyle();
		
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);
		
		//saving the entire wb into file f2
		fo=new FileOutputStream(path);
		workbook.write(fo);
		
		workbook.close();
		fi.close();
		fo.close();
	
	}
	
	public  void fillredcolor(String sheetName,int rownum,int colnum) throws IOException {
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		row = sheet.getRow(rownum);
		cell=row.getCell(colnum);
		style=workbook.createCellStyle();
		
		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.BRICKS);
		
		cell.setCellStyle(style);
		
		//saving the entire wb into file f2
		fo=new FileOutputStream(path);
		workbook.write(fo);
		
		workbook.close();
		fi.close();
		fo.close();
	
	}
	
	
	

}
