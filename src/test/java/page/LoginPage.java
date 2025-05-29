package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	@FindBy(xpath="//input[@name='username']")
	private WebElement uName;
	
	@FindBy(xpath="//input[@name='password']")
	private WebElement pwd;
	
	@FindBy(xpath="//button")
	private WebElement goButton;
	
	public LoginPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	public void sendUname(String userName)
	{
		uName.sendKeys(userName);
	}
	public void sendPwd(String password)
	{
		pwd.sendKeys(password);
	}
	public void clickGoButton()
	{
		goButton.click();
	}

}
