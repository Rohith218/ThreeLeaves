package ExcelUtils;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.*;

import CustomException.*;



public class ExcelReader {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		List<String> s = excelRead();
		for(int i=0;i<s.size();i++) {
			System.out.println(s.get(i));;
		}
	}
	public static List<String> excelRead() throws Exception {
		List<String> s = new ArrayList<>();
		String path1 = System.getProperty("user.dir") + "\\src\\test\\java\\ExcelUtils.xlsx";
//		System.out.println(path+"ExcelReader");
		String path = "C:\\Users\\rohit\\OneDrive\\Desktop\\ThreeLeaves1\\ThreeLeaves\\src\\test\\java\\"+"ExcelUtils.xlsx";
		File file = new File(path);
		System.out.println(file.exists());
		try(FileInputStream fis = new FileInputStream(path); ){
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet = wb.getSheet("form");
			XSSFRow row = sheet.getRow(0);
			int colnum = row.getLastCellNum();
			for(int i=0;i<colnum;i++) {	
				switch(row.getCell(i).getCellType()) {
					case STRING:
						s.add(i, row.getCell(i).toString());
						break;
					case NUMERIC:
						long a = (long) row.getCell(i).getNumericCellValue();
					    s.add(i, String.valueOf(a));
					    break;
					default:
						s.add("");
				}
			}
		}catch(Exception e){
			throw new ExcelFileException("Excel File Not Found : "+ path);
		}
		return s;
	}

}
