 package Screenshots;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import CustomException.ExcelFileException;

public class Screenshots {
	 WebDriver driver;
	public Screenshots(WebDriver driver) {
		this.driver = driver;
	}
	public File screenshots(String fileName) throws IOException, ExcelFileException {
		try{
			TakesScreenshot ss = (TakesScreenshot) driver;
	        
	        File src = ss.getScreenshotAs(OutputType.FILE);
	        String path = System.getProperty("user.dir");
	        
	        File des = new File(path+"\\src\\test\\java\\Screenshots\\"+ fileName + ".png");
	        
	        FileHandler.copy(src, des);
	        return des;
		}catch(Exception e) {
			 throw new ExcelFileException("Screenshot Folder not found");
		}
		
	}
	public String b64screenshots(String fileName) throws IOException, ExcelFileException {
		try{
			TakesScreenshot ss = (TakesScreenshot) driver;
	        
	        String src = ss.getScreenshotAs(OutputType.BASE64);
	        String path = System.getProperty("user.dir");
	        
	        File des = new File(path+"\\src\\test\\java\\Screenshots\\"+ fileName + ".png");
	        
	        String reportPath = System.getProperty("user.dir") + "\\reports\\test.html";
			
	        
	        return src;
		}catch(Exception e) {
			 throw new ExcelFileException("Screenshot Folder not found");
		}
		
	}

}
