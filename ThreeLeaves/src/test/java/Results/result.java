package Results;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
}
