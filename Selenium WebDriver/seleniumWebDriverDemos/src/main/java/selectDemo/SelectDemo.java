package selectDemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class SelectDemo {

	public static void main(String[] args) {
		
		WebDriver driver = new FirefoxDriver();
		
		try {
			
			driver.get("http://demo.loway.ch/queuemetrics-livedemo/autenticazione.jsp");

	        WebElement loginUsernameField = driver.findElement(By.id("AUTH_logon"));
	        String validUsername = "demo";
	        loginUsernameField.sendKeys(validUsername);

	        WebElement loginPasswordField = driver.findElement(By.id("AUTH_password"));
	        String validPassword = "demo";
	        loginPasswordField.sendKeys(validPassword);

	        WebElement loginButton = driver.findElement(
	            By.xpath("//div[@id='xCorpo']/center/table/tbody/tr[4]/td[2]/input"));
	        loginButton.click();

	        Select selectElement = new Select(driver.findElement(By.id("CODA_F_agenteFiltro")));
	        selectElement.selectByVisibleText("Mara (301)");


	        System.out.println(selectElement.getAllSelectedOptions().get(0).getText());
		} catch (Exception e) {

			System.out.println(e.getMessage());
		} finally {
			
			driver.close();
		}
	}
}
