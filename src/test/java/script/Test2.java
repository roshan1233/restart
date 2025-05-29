package script;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class Test2 {
	@Test
	public void test1() throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get("https://www.fantasticfurniture.com.au/");
		
		Actions act = new Actions(driver);
		//WebElement ele1 = ;
		Thread.sleep(5000);
		act.moveToElement(driver.findElement(By.xpath("//a[text()=' Living, Dining & Outdoor ']"))).perform();
		Thread.sleep(3000);
		WebElement ele2 = driver.findElement(By.xpath("(//a[text()='Coffee Tables'])[3]"));
		ele2.click();
		Thread.sleep(3000);
		WebElement ele3 = driver.findElement(By.xpath("//button[text()=' Budget ']"));
		
		act.scrollToElement(ele3).perform();
		Thread.sleep(3000);
		WebElement ele4 = driver.findElement(By.xpath("(//input[@type='number' and @inputmode ='numeric'])[2]"));
		ele4.clear();
		ele4.sendKeys("500");
		driver.findElement(By.xpath("(//button[text()='Apply'])[1]")).click();
		WebElement ele5 = driver.findElement(By.xpath("//span[text()='(5)']"));
		try {
			if(ele5.isDisplayed())
			{
				ele5.click();
			}
		}
		catch(Exception e)
		{
			Reporter.log("Ele not found", true);
		}
		driver.findElement(By.xpath("(//div[text()='Landyn Side Table']/../../../../../..//button)[2]")).click();
		
		driver.findElement(By.xpath("//button[text()=' View Cart & Check Out ']")).click();
		
		driver.findElement(By.xpath("(//input[@inputmode='numeric']/./../../../../../../../..//i)[3]")).click();
		
		WebElement ele6 = driver.findElement(By.xpath("//h2[text()='Your cart is empty']"));
		
		if(ele6.getText().equalsIgnoreCase("Your cart is empty"))
		{
			Reporter.log("Cart is empty", true);
		}
		else {
			Reporter.log("Cart is not empty", true);
		}
		
	
	}
}
