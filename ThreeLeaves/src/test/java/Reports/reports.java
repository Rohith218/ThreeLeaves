package Reports;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class reports {
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.println(System.getProperty("user.dir"));
		extent();
	}
	public static void extent() throws IOException {
		ExtentReports e = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter(".\\reports\\report.html");
		e.attachReporter(spark);
		ExtentTest test1 = e.createTest("Test 1");
		test1.log(Status.FAIL, "Sample Failed");
		ExtentTest test2 = e.createTest("Test 1");
		test2.pass("This test is passed");
		e.flush();
		String reportPath = System.getProperty("user.dir") + "\\reports\\report.html";
		Desktop.getDesktop().browse(new File(reportPath).toURI());
	}
}
