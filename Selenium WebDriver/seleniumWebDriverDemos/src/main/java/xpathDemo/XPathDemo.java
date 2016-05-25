package xpathDemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class XPathDemo {

	public static void main(String[] args) {
		
		WebDriver driver = new FirefoxDriver();
		
		try {
			
			driver.get("http://www.tutorialspoint.com/html/html_tables.htm");
	        WebElement element = driver.findElement(By.xpath("//td[contains(text(), 'Shabbir')]/following-sibling::td[1]"));
	        System.out.println(element.getText());
		} catch (Exception e) {

			System.out.println(e.getMessage());
		} finally {
			
			driver.close();
		}
	}
}
