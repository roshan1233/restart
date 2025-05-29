package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	@FindBy(xpath="//p[@class='oxd-userdropdown-name']")
	private WebElement popoutButton;
	
	@FindBy(xpath="//a[text()='Logout']")
	private WebElement logoutButton;
	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);	
	}
	
	public void clickLogout()
	{
		popoutButton.click();
		
		logoutButton.click();
	}
}
