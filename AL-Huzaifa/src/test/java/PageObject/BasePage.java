package PageObject;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	
	protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;

    public BasePage(WebDriver driver)
    {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(8));
        this.actions = new Actions(driver);
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
    public void hover(WebElement element) {
        actions.moveToElement(waitForVisible(element)).perform();
    }

    public void scrollIntoView(WebElement element) {
        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].scrollIntoView({block:'center'});", element
        );
    }
}
