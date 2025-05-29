package generic;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest implements IAutoConst{
	public WebDriver driver;
	public WebDriverWait wait;
	@Parameters({"Grid","GridUrl","Browser","AppUrl"})
	@BeforeMethod
	public void beforeMethod(@Optional(GRID) String grid,@Optional(GRIDURL) String gridUrl,@Optional(BROWSER) String br,@Optional(PROP) String prop) throws MalformedURLException
	{
		Reporter.log("inside before method", true);
		if(grid.equalsIgnoreCase("yes"))
		{
			String gurl = Utility.getProperties(prop, gridUrl);
			URL url = new URL(gurl);
			if(br.equalsIgnoreCase("chrome"))
			{
				ChromeOptions op = new ChromeOptions();
				driver=new RemoteWebDriver(url, op);
			}
			else if(br.equalsIgnoreCase("firefox")){
				FirefoxOptions fo = new FirefoxOptions();
				driver=new RemoteWebDriver(url, fo);
			}
			else {
				EdgeOptions eo = new EdgeOptions();
				driver=new RemoteWebDriver(url, eo);
			}
		}
		else {
			if(br.equalsIgnoreCase("chrome"))
			{
				driver = new ChromeDriver();
			}
			else if(br.equalsIgnoreCase("firefox")) 
			{
				driver = new FirefoxDriver();
			}
			else {
				driver = new EdgeDriver();
			}
		}
		
		driver.manage().window().maximize();
		String appUrl = Utility.getProperties(prop, "APPURL");
		driver.get(appUrl);
		
		int ito = Integer.parseInt(Utility.getProperties(prop, "ITO"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ito));
		
		int eto =Integer.parseInt(Utility.getProperties(prop, "ETO"));
		wait=new WebDriverWait(driver, Duration.ofSeconds(eto));
	}
	
	@AfterMethod
	public void afterMethod(ITestResult ts)
	{
		int st = ts.getStatus();
		String testName = ts.getTestName();
		if(st==2)
		{
			Reporter.log("test case failed", true);
			try {
			TakesScreenshot tc = (TakesScreenshot)driver;
			File src = tc.getScreenshotAs(OutputType.FILE);
			File dest = new File("./screenshot/"+testName+".png");
			FileUtils.copyFile(src, dest);
			} 
			catch (IOException e) {
				Reporter.log("Issue in taking screenshot", true);
			}
		}
	}
	
	@AfterSuite
	public void atEnd() throws InterruptedException
	{
		Thread.sleep(3000);
		Reporter.log("Closing the rowser", true);
		driver.quit();
	}

}
