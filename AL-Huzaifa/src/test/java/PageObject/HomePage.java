package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{
	
	public HomePage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath = "//button[@type='button' and contains(@class,'absolute') and contains(@class,'right-2.5')]")
    WebElement welcomePopupCloseButton;
	
	 public void closeWelcomePopupIfDisplayed()
	    {
	        if (isDisplayed(welcomePopupCloseButton))
	        {
	            click(welcomePopupCloseButton);
	        }
	    }

}
