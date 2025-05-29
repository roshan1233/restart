package generic;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class SparkReport extends BaseTest implements ITestListener {
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extReport;
	
	 /**
	   * Invoked before running all the test methods belonging to the classes inside the &lt;test&gt;
	   * tag and calling all their Configuration methods.
	   *
	   * @param context The test context
	   */
	  public void onStart(ITestContext context) {
		  sparkReporter = new ExtentSparkReporter("Report.html");
		  extReport=new ExtentReports();
		  
		  extReport.attachReporter(sparkReporter);
		  extReport.setSystemInfo("OS", System.getProperty("os.name"));
		  extReport.setSystemInfo("Java Version", System.getProperty("java.version"));
		  
//		  RemoteWebDriver rem = (RemoteWebDriver)driver;
//		  Capabilities cap = rem.getCapabilities();
//		  
//		  extReport.setSystemInfo("BROWSER NAME", cap.getBrowserName());
//		  extReport.setSystemInfo("BROWSER VERSION", cap.getBrowserVersion());
//		  extReport.setSystemInfo("PLATFORM NAME", cap.getPlatformName().toString());
	  }
	
	
	public void onTestStart(ITestResult result) {
	    // not implemented
	  }

	  /**
	   * Invoked each time a test succeeds.
	   *
	   * @param result <code>ITestResult</code> containing information about the run test
	   * @see ITestResult#SUCCESS
	   */
	  public void onTestSuccess(ITestResult result) {
	    extReport.createTest("Test1")
	    .assignAuthor("Awanish")
	    .assignCategory("Smoke")
	    .assignDevice("Edge")
	    .log(Status.PASS, "Test case passed"+result.getName());
	  }

	  /**
	   * Invoked each time a test fails.
	   *
	   * @param result <code>ITestResult</code> containing information about the run test
	   * @see ITestResult#FAILURE
	   */
	  public void onTestFailure(ITestResult result) {
	    extReport.createTest("Test name :"+result.getName())
	    .assignAuthor("Anand")
	    .assignCategory("Sanity")
	    .assignDevice("chrome")
	    .log(Status.FAIL, "Test case failed");
	  }

	  /**
	   * Invoked each time a test is skipped.
	   *
	   * @param result <code>ITestResult</code> containing information about the run test
	   * @see ITestResult#SKIP
	   */
	  public void onTestSkipped(ITestResult result) {
	    // not implemented
	  }

	  /**
	   * Invoked each time a method fails but has been annotated with successPercentage and this failure
	   * still keeps it within the success percentage requested.
	   *
	   * @param result <code>ITestResult</code> containing information about the run test
	   * @see ITestResult#SUCCESS_PERCENTAGE_FAILURE
	   */
	  public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	    // not implemented
	  }

	  /**
	   * Invoked each time a test fails due to a timeout.
	   *
	   * @param result <code>ITestResult</code> containing information about the run test
	   */
	  public void onTestFailedWithTimeout(ITestResult result) {
	    onTestFailure(result);
	  }

	 

	  /**
	   * Invoked after all the test methods belonging to the classes inside the &lt;test&gt; tag have
	   * run and all their Configuration methods have been called.
	   *
	   * @param context The test context
	   */
	  public void onFinish(ITestContext context) {
	   extReport.flush();
	   try {
		Desktop.getDesktop().browse(new URI("Report.html"));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (URISyntaxException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  }

}
