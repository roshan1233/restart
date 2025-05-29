package script;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import generic.BaseTest;
import generic.Utility;
import page.HomePage;
import page.LoginPage;

public class ValidLogin extends BaseTest {
	
	@Test()
	public void loginTest() throws EncryptedDocumentException, FileNotFoundException, IOException
	{
		Reporter.log("Inside", true);
		Reporter.log("qa branch", true);
		String uName = Utility.getExcelData(EXCELFILEPATH, "Sheet1", 1, 0);
		String pwd = Utility.getExcelData(EXCELFILEPATH, "Sheet1", 1, 1);
		
		LoginPage login = new LoginPage(driver);
		login.sendUname(uName);
		login.sendPwd(pwd);
		login.clickGoButton();
		
		HomePage hp = new HomePage(driver);
		hp.clickLogout();
		
		String expected="https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
		String actual=driver.getCurrentUrl();
		Assert.assertEquals(actual, expected);
	}

}
