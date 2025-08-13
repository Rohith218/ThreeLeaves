package Results;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.idealized.Javascript;

import ExcelUtils.ExcelReader;

public class result {
	WebDriver driver;
	public result(WebDriver driver) {
		this.driver = driver;
	}
	public String getResult() {
		String s = "";
		WebElement ele = driver.findElement(By.id("result"));
		s = ele.getText();
		System.out.println(s);
		return s;
	}
	public String actualResult() throws Exception {
		String s="";
		ExcelReader e = new ExcelReader();
		List<String> li = e.excelRead();
		
		String amt = "";
		String dt= li.get(4);
		String time=li.get(5);
		int num= Integer.parseInt(li.get(3));
		String slot =li.get(6);
		if(time.equalsIgnoreCase("lunch")) {
			if(num<=3) {
				amt = "500";
			}else if(num<=6&&num>=3) {
				amt = "1000";
			}else if(num<=10&&num>=6) {
				amt = "2500";
			}else if(num<=20&&num>=10) {
				amt = "4000";
			}
		}else if(time.equalsIgnoreCase("dinner")) {
			if(num<=3) {
				amt = "600";
			}else if(num<=6&&num>=3) {
				amt = "1200";
			}else if(num<=10&&num>=6) {
				amt = "2750";
			}else if(num<=20&&num>=10) {
				amt = "4200";
			}
		}
		 s = "The Advance Amount is Rs."+amt+".\r\n"
				+ "Your Table is Reserved and Confirmed on "+formatDate(dt)+" at "+slot;
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("scrollBy(0,4500);");
		 
		return s;
	}
	public String formatDate(String dt) throws ParseException {
		SimpleDateFormat parser = new SimpleDateFormat("yyyymmdd");
		Date Date = parser.parse(dt);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
		return format.format(Date);
	}
}
