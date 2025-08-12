package TestRunner;

import java.text.ParseException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import DriverSetup.DriverSetup;
import ExcelUtils.ExcelReader;
import Results.result;
import SetDriver.setDriver;

public class FormTest extends DriverSetup{
	private WebDriver driver;
	private String site = "https://webapps.tekstac.com/ThreeLeaves/index.html";
	
	@BeforeMethod
	public WebDriver setup() throws Exception {
		driver = setDriver();
		driver.get(site);
		driver.manage().window().maximize();
		return driver;
	}
	@Test
	public void test() throws Exception {
		initialize();
		result ar = new result(driver);
		String texx = ar.getResult();
		System.out.println("Expected "+texx);
		System.out.println("Actul Test: "+ar.actualResult());
		Assertion a = new Assertion();
//		a.assertFalse(texx.equalsIgnoreCase(ar.actualResult()));
//		a.assertEquals(texx, ar.actualResult().trim().toLowerCase());
		a.assertEquals(normalize(ar.actualResult()), normalize(texx));
	}
	public String normalize(String s){ 
		return s==null?null : s.trim().replaceAll("\\s+"," "); 
	}
	public void initialize() throws Exception {
		setDriver s = new setDriver(driver);
		ExcelReader ex = new ExcelReader();
		List<String> sa = ex.excelRead();
		s.setName(sa.get(0));
		s.setNum(sa.get(1));
		s.setEmail(sa.get(2));
		s.setGuests(sa.get(3));
		s.setDov(sa.get(4));
		s.setSlot(sa.get(5));
		s.setTov(sa.get(6));
		s.setSubmit();
	}
	@AfterMethod
	public void teardown() {
		driver.close();
	}

	public static void main(String[] args)  {
		// TODO Auto-generated method stub

	}

}
