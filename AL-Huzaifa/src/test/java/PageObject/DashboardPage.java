package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashboardPage extends BasePage{
	public DashboardPage(WebDriver driver)
	{
		super(driver);
	}
	@FindBy(xpath="(//div[@class='description small-mb'])[1]")
	WebElement ContactInformation;
	public boolean isContactInformationVisible() {
        return isDisplayed(ContactInformation);
    }
	
}
