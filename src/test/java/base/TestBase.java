package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBase {
	// Static WebDriver instance, shared across tests.
	protected  WebDriver driver;

	@BeforeMethod
	public void setup() {
		// Gets the browser type from system properties, defaulting to "chrome"
		String browser = System.getProperty("browser");
		if (browser == null) {
			browser = "chrome";
		}
		// Initializes the WebDriver based on the specified browser
		driver = initializeDriver(browser);
		// Maximizes the browser window for better visibility during tests
		driver.manage().window().maximize();
	}

	private WebDriver initializeDriver(String browser) {
		// Switch statement to select the WebDriver based on the browser type
		switch (browser.toLowerCase()) {
		case "chrome":
			return new ChromeDriver(); // Returns Chrome WebDriver instance
		case "firefox":
			return new FirefoxDriver(); // Returns Firefox WebDriver instance
		case "edge":
			return new EdgeDriver(); // Returns Edge WebDriver instance
		default:
			// Throws an exception if the browser type is not supported
			throw new IllegalArgumentException("Browser \"" + browser + "\" is not supported");
		}
	}

	@AfterMethod
	// Closes the WebDriver if it is still active
	public void tearDown() { 
		if (driver != null) {
			driver.quit();
		}
	}

}
