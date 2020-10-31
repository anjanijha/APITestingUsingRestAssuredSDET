package com.qa.data_driven_api;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtils {
	
		public static FileInputStream fi;
		public static FileOutputStream fo;
		public static XSSFWorkbook wb;
		public static XSSFSheet ws;
		public static XSSFRow row;
		public static XSSFCell cell;
		
		public static int getRowCount(String xlfile, String xlsheet ) throws IOException{
			fi= new FileInputStream(xlfile);
			wb= new XSSFWorkbook(fi);
			System.out.println("xlsheet==>"+xlsheet);
			ws=wb.getSheet(xlsheet);
			System.out.println(fi+"  "+wb+" "+ws);
			int rowcount= ws.getLastRowNum();
			System.out.println("rowcount==>"+rowcount);
			wb.close();
			fi.close();
			return rowcount;
		}
		public static int getCellCount(String xlfile, String xlsheet,int rownum ) throws IOException{
			fi= new FileInputStream(xlfile);
			wb= new XSSFWorkbook(fi);
			ws=wb.getSheet(xlsheet);
			row=ws.getRow(rownum);
			int cellcount= row.getLastCellNum();
			wb.close();
			fi.close();
			return cellcount;
		}
		public static String getCellData(String xlfile, String xlsheet,int rownum, int colnum ) throws IOException{
			fi= new FileInputStream(xlfile);
			wb= new XSSFWorkbook(fi);
			ws=wb.getSheet(xlsheet);
			row=ws.getRow(rownum);
			cell=row.getCell(colnum);
			String data;
			try {
				DataFormatter formatter= new DataFormatter();
				String cellData= formatter.formatCellValue(cell);
				return cellData;
			}catch (Exception e) {
				data="";
			}
			wb.close();
			fi.close();
			return data;
		}
		public static void setCellData(String xlfile, String xlsheet,int rownum,int colnum,String data ) throws IOException{
			fi= new FileInputStream(xlfile);
			wb= new XSSFWorkbook(fi);
			ws=wb.getSheet(xlsheet);
			row=ws.getRow(rownum);
			cell=row.createCell(colnum);
			cell.setCellValue(data);
			fo=new FileOutputStream(xlfile);
			wb.write(fo);
			wb.close();
			fi.close();
			fo.close();
		}
}
