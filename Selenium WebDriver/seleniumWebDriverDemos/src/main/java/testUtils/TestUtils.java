package testUtils;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import testUtils.exceptions.ElementStillVisibleException;
import testUtils.exceptions.NotCheckedException;
import testUtils.exceptions.StillCheckedException;
import testUtils.exceptions.TextNotFoundException;

public class TestUtils {

	private WebDriver browser;
	private String baseUrl;
	private WebDriverWait wait;
	private int timeout;
	private WebElement currentElement;
	
	public TestUtils(WebDriver driver, String baseUrl, int timeOut)
    {
        this.setBrowser(driver);
        this.setBaseUrl(baseUrl);
        this.setTimeout(timeOut);
        this.setWait(new WebDriverWait(driver, this.getTimeout()));
    }

    public WebDriver getBrowser() {
		
    	return this.browser;
	}

	public void setBrowser(WebDriver browser) {
		
		this.browser = browser;
	}

	public String getBaseUrl() {
		
		return this.baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		
		this.baseUrl = baseUrl;
	}

	public WebDriverWait getWait() {
		return wait;
	}

	public void setWait(WebDriverWait wait) {
		
		this.wait = wait;
	}

	public int getTimeout() {
		
		return this.timeout;
	}

	public void setTimeout(int timeout) {
		
		this.timeout = timeout;
	}

	public WebElement getCurrentElement() {
		
		return this.currentElement;
	}

	public void setCurrentElement(WebElement currentElement) {
		
		this.currentElement = currentElement;
	}

	public WebElement getElement(By by) {
		
        WebElement result = null;
        try {
        	
            result = this.getWait().until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (TimeoutException ex) {
        	
            throw new NoSuchElementException("The specified element was not found within the specified time frame");
        }

        return result;
    }

    public boolean isElementPresent(By by)
    {
        try {
        	
            this.getBrowser().findElement(by);
            return true;
        } catch (NoSuchElementException ex) {
        	
            return false;
        }
    }

    public void waitForElementPresent(By by) {
    	
        this.getElement(by);
    }

    public void waitForElementNotPresent(By by) throws ElementStillVisibleException {
    	
        try {
        	
            this.getElement(by);
            throw new ElementStillVisibleException("The specified element is still visible.");
        }
        catch (NoSuchElementException ex) {
        	
        	System.out.println(ex.getMessage());
        }
    }

    public void waitForChecked(By by) throws NotCheckedException
    {
        WebElement currentElement = this.getElement(by);
        boolean isSelected = currentElement.isSelected();

        if (!isSelected) {
        	
            throw new NotCheckedException("The specified element was not checked.");
        }
    }

    public void waitForNotChecked(By by) throws StillCheckedException
    {
        WebElement currentElement = this.getElement(by);
        boolean isSelected = currentElement.isSelected();

        if (isSelected) {
        	
            throw new StillCheckedException("The specified element is still checked");
        }
    }

    public void waitForText(By by, String textToFind) throws TextNotFoundException
    {
        WebElement currentElement = this.getElement(by);
        String elementText = currentElement.getText();

        if (!textToFind.equals(elementText)) {
        	
            throw new TextNotFoundException("The specified text element wa snot found.");
        }
    }
}
