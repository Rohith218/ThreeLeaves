package Reports;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import CustomException.ExcelFileException;
import Screenshots.Screenshots;
import io.github.bonigarcia.wdm.WebDriverManager;

public class reports {
	public static WebDriver driver;
	public static void main(String[] args) throws IOException, ExcelFileException {
		// TODO Auto-generated method stub
		System.out.println(System.getProperty("user.dir"));
		extent();
	}
	public static void extent() throws IOException, ExcelFileException {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		ExtentReports er = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter(".\\reports\\report.html");
		er.attachReporter(spark);
		ExtentTest test1 = er.createTest("Test 1");
		test1.log(Status.FAIL, "Sample Failed");
		ExtentTest test2 = er.createTest("Test 2");
		ExtentTest t3 = er.createTest("Test 3");
		test2.pass("This test is passed");
		try {
			int i = 5/0;
		}
		catch(Exception e) {
			t3.fail(e.toString());
		}
		Throwable t = new RuntimeException();
		test2.info(t);
		ExtentTest t4 = er.createTest("Screenshot test","This is a screenshot test");
		Screenshots ss = new Screenshots(driver);
		driver.get("https://github.com/Rohith218");
		driver.manage().window().maximize();
		File a = ss.screenshots("Rupture");
		t4.info("Pass").
		addScreenCaptureFromPath(a.toString());
		t4.info("Pass").addScreenCaptureFromBase64String(ss.b64screenshots("Rupture1"));
		driver.close();
		er.flush();
		String reportPath = System.getProperty("user.dir") + "\\reports\\report.html";
		Desktop.getDesktop().browse(new File(reportPath).toURI());
	}
}
