package tests.softuni;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestSoftUni {

	private WebDriver driver;
	
	@Before
	public void setUp(){
		
		this.driver = new FirefoxDriver();
		this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		this.driver.manage().window().maximize();
	}
	
	@Test
	public void testLogin_ValidCredentials_ShouldLoginCorrectly() {
		
		this.driver.get("https://softuni.bg");
		
		WebElement loginLink = this.driver.findElement(
				By.xpath("/html/body/div[2]/div[1]/header/nav/div[2]/ul/li[3]/span/a"));
		loginLink.click();
		
		WebElement loginUsernameField = this.driver.findElement(By.id("LoginUserName"));
		String validUsername = "testtest";
		loginUsernameField.sendKeys(validUsername);
		
		WebElement loginPasswordField = this.driver.findElement(By.id("LoginPassword"));
		String validPassword = "testtest";
		loginPasswordField.sendKeys(validPassword);
		
		WebElement loginButton = this.driver.findElement(
				By.xpath("/html/body/div[3]/div[2]/div/div[1]/form/input[2]"));
		loginButton.click();
		
		assertEquals("https://softuni.bg/users/profile/show", this.driver.getCurrentUrl());
		
		WebElement actualFullName = this.driver.findElement(
				By.xpath("/html/body/div[3]/div[2]/div[1]/div/div[1]/div/div/div[1]/div/div[2]/h2/strong"));
		String expectedFullName = "testtest" + " (" + validUsername + ")";
		assertEquals(expectedFullName, actualFullName.getText());
	}

	@After
	public void tearDown(){
		
		this.driver.quit();
	}
}
