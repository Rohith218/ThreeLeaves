package SetDriver;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import DriverSetup.DriverSetup;

public class setDriver extends DriverSetup {
	protected String name;
	protected String num;

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
		driver.findElement(By.id("mobileNumber")).sendKeys(num);
	}

	protected String email;
	protected String guests;
	protected String dov;
	protected String slot;
	protected String tov;
	
	public void setSubmit() {
		WebElement sub = driver.findElement(By.id("btnSubmit"));
		sub.click();
	}
//	public WebDriver driver;
	public setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		driver.findElement(By.xpath("//input[@id = 'name' and @type = 'text']")).sendKeys(name);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		driver.findElement(By.xpath("//input[@id = 'email']")).sendKeys(email);
		this.email = email;
	}

	public String getGuests() {
		return guests;
	}

	public void setGuests(String guests) {
		System.out.println(guests);
		driver.findElement(By.xpath("//input[@id = 'noOfGuests' and @type = 'number']")).sendKeys(guests);
		this.guests = guests;
	}

	public String getDov() {
		return dov;
	}

	public void setDov(String dov) throws ParseException {
		
//		String dt = "2025-02-02";
		SimpleDateFormat parser = new SimpleDateFormat("yyyymmdd");
		Date Date = parser.parse(dov);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
		WebElement date =  driver.findElement(By.xpath("//input[@type = 'date' and @id = 'dateOfVisit']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('value', '"+format.format(Date)+"')",date);
		this.dov = dov;
	}
	public String getSlot() {
		
		return slot;
	}
	public void setSlot(String slot) {
		if(slot.equalsIgnoreCase("lunch")) {
			driver.findElement(By.xpath("//input[@id='lunch']")).click();
		}else if(slot.equalsIgnoreCase("dinner")) {
			driver.findElement(By.xpath("//input[@id='dinner']")).click();
		}
		this.slot = slot;
	}

	public String getTov() {
		return tov;
	}

	public void setTov(String tov) {
		WebElement ele = driver.findElement(By.id("timing"));
		Select dd = new Select(ele);
		dd.selectByVisibleText(tov);
		this.tov = tov;
	}
}
