package DriverSetup;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverSetup {
	public static WebDriver driver;
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		setDriver();
		driver.get("https://webapps.tekstac.com/ThreeLeaves/index.html");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//input[@id = 'name' and @type = 'text']")).sendKeys("Rohith Taninki");
		Thread.sleep(5000);
		Actions a = new Actions(driver);
		a.keyDown(Keys.ENTER).perform();
		a.keyUp(Keys.ENTER).perform();
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10000));
		Thread.sleep(20000);
//		driver.quit();
	}
	protected static WebDriver setDriver() {
		WebDriverManager.chromedriver().setup();
		 driver = new ChromeDriver();
		 return driver;
	}

}
