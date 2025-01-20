package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

// This class represents the LoginPage page object model
public class LoginPage {

    // WebDriver instance used to control the browser
    WebDriver driver;

    // Locating the email input field by its ID
    @FindBy(how = How.ID, using = "email")
    private WebElement emailInput;

    // Locating the password input field by its ID
    @FindBy(how = How.ID, using = "password")
    private WebElement pwInput;

    // Locating the login button using XPath with specific attributes
    @FindBy(how = How.XPATH, using = "//button[@type='submit' and contains(@class, 'btn') and contains(@class, 'w-100')]")
    private WebElement loginBtn;
    
    @FindBy(how = How.CSS, using = "#dropdownUser1")
    private WebElement adminSuccesMsg;
    
    @FindBy(how = How.XPATH, using = "//h6[@class='mb-0']//strong")
    private WebElement usersSuccesMsg;
    
    @FindBy(how = How.XPATH, using = "//h4[normalize-space()='Invalid Login']")
    private WebElement errorMsg;



    // Constructor to initialize the WebDriver and the elements on the page
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        // Initializing WebElements using PageFactory
        PageFactory.initElements(driver, this);
    }

    // Method to open the application by navigating to the provided URL
    public void connectToApp(String url) {
        driver.get(url);
    }

    // Method to perform login by entering email, password, and clicking the login button
    public void performLogin(String email, String password) {
        emailInput.sendKeys(email); // Entering the email
        pwInput.sendKeys(password); // Entering the password
        loginBtn.click(); // Clicking the login button
    }

	// Returns the Admin succes message element
	public WebElement getAdminSuccesMsg() {
		return adminSuccesMsg;
	}

	// Returns the users succes message element
	public WebElement getUsersSuccesMsg() {
		return usersSuccesMsg;
	}
	
	// Returns the error message element
	public WebElement getErrorMsg() {
		return errorMsg;
	}
    
    
}
