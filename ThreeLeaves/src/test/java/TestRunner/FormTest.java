package TestRunner;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import org.testng.internal.annotations.ITest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import DriverSetup.DriverSetup;
import ExcelUtils.ExcelReader;
import Results.result;
import Screenshots.Screenshots;
import SetDriver.setDriver;

public class FormTest extends DriverSetup{
	private WebDriver driver;
	private String site = "https://webapps.tekstac.com/ThreeLeaves/index.html";
	private static Logger logs = LogManager.getLogger(FormTest.class);
	public static ExtentReports er;
	public ExtentSparkReporter esr;
	public static ExtentTest log;
	public Screenshots ss;
	public result ar;
	
	@BeforeSuite
	public void initilizeReports() {
		er = new ExtentReports();
		esr = new ExtentSparkReporter(".\\reports\\test.html");
		er.attachReporter(esr);
		log = er.createTest("Test");
		
	}
	
	@Parameters("browsername")
	@BeforeTest
	public WebDriver setup(ITestContext context, @Optional("chrome") String browsername) throws Exception {
		driver = setDriver(browsername);
		driver.get(site);
		driver.manage().window().maximize();
		log.info(browsername + "Launched succesfully");
		return driver;
	}
//	@BeforeMethod
//	public void addtc(Method method,ITestContext context) {
////		String browserTestName = context.getName(); 
////	    String methodName = method.getName();       
//	    
//	    
//	}
	@Test
	public void test() throws Exception {
		initialize();
		 ar = new result(driver);
		String texx = ar.getResult();
		System.out.println("Expected "+texx);
		System.out.println("Actul Test: "+ar.actualResult());
		Assertion a = new Assertion();
		a.assertEquals(normalize(ar.actualResult()), normalize(texx));
		ss = new Screenshots(driver);
		ss.screenshots("actualText");
		
//		fatal("checkingfwrduykgwiyhebguaireufy");
		Thread.sleep(5000);
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
	public void getResult(ITestResult r) throws Exception {
		if(r.getStatus()  == ITestResult.FAILURE) {
			log.fail("Reservation failed");
			log.addScreenCaptureFromBase64String(ss.b64screenshots("xyz"), "Screenshot after pass");
		}else {
			log.pass("Reservation is booked with nooking message as" + ar.actualResult().toString());
			log.addScreenCaptureFromBase64String(ss.b64screenshots("xyz"), "Screenshot after pass");
		}
	}
	@AfterTest
	public void teardown() {
		driver.quit();;
	}
	@AfterSuite
	public void generateReports() throws IOException {
		er.flush();
		String reportPath = System.getProperty("user.dir") + "\\reports\\test.html";
		Desktop.getDesktop().browse(new File(reportPath).toURI());
	}
	public static void main(String[] args)  {
		// TODO Auto-generated method stub
	}

}
