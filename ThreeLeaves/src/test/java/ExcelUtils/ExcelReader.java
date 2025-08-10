package ExcelUtils;


import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.*;

import CustomException.*;



public class ExcelReader {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
//		List<String> s = excelRead();
//		for(int i=0;i<s.size();i++) {
//			System.out.println(s.get(i));;
//		}
	}
	public List<String> excelRead() throws Exception {
		List<String> s = new ArrayList<>();
		String path = System.getProperty("user.dir")+ "\\src\\test\\java\\ExcelUtils.xlsx";
		System.out.println(path);
		try(FileInputStream fis = new FileInputStream(path); XSSFWorkbook wb = new XSSFWorkbook(fis);){
			XSSFSheet sheet = wb.getSheet("form");
			XSSFRow row = sheet.getRow(0);
			int colnum = row.getLastCellNum();
			for(int i=0;i<colnum;i++) {	
				switch(row.getCell(i).getCellType()) {
					case STRING:
						s.add(i, row.getCell(i).toString());
					case NUMERIC:
						s.add(i, row.getCell(i).toString());
				}
			}
		}catch(Exception e){
			throw new ExcelFileException("Excel File Not Found : "+ path);
		}
		for(int i=0;i<s.size();i++) {
			System.out.println(s.get(i));
		}
		return s;
	}

}
