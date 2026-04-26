package PageObject;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	
	protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver)
    {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void click(WebElement element)
    {
        waitForClickable(element).click();
    }

    public void type(WebElement element, String value)
    {
        WebElement webElement = waitForVisible(element);
        webElement.clear();
        webElement.sendKeys(value);
    }

    public boolean isDisplayed(WebElement element)
    {
        try
        {
            return waitForVisible(element).isDisplayed();
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public WebElement waitForVisible(WebElement element)
    {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitForClickable(WebElement element)
    {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public String getText(WebElement element)
    {
        return waitForVisible(element).getText().trim();
    }
}
