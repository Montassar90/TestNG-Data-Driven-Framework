package utils;

import java.time.Duration;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.TestBase;


public class BrowserUtils extends TestBase{

	   /**
     * Waits explicitly until the given element is visible on the page.
     * 
     * @param element the WebElement to wait for
     * @return the visible WebElement once it appears
     */
    public WebElement explicitVisibleWait(WebElement element) {
        // Create a WebDriverWait instance with a timeout of 30 seconds
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        // Wait until the specified element is visible and return it
        return wait.until(ExpectedConditions.visibilityOf(element));
    }


}
