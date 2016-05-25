package waitingDemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitingDemo {

	public static void main(String[] args) {
		
		WebDriver driver = new FirefoxDriver();
		
		try {
			
			driver.get("http://www.tutorialspoint.com/html/html_tables.htm");
			WebElement myDynamicElement = (new WebDriverWait(driver, 10))
					  .until(ExpectedConditions.presenceOfElementLocated(
							  By.xpath("//td[contains(text(), 'Shabbir')]/following-sibling::td[1]")));
			
			System.out.println(myDynamicElement.getText());
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
		} finally {
			
			driver.close();
		}
	}
}
