package tests;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import base.TestBase;
import pageObject.LoginPage;
import utils.BrowserUtils;
import utils.ReadXLSdata;

public class LoginPageTests extends TestBase {

	private BrowserUtils browserUtils;

	// This test uses a data provider to supply different sets of input data from an
	// Excel file.
	// The data provider method is defined in the ReadXLSdata class and helps in
	// running the test with multiple data sets.
	@Test(dataProvider = "testingData", dataProviderClass = ReadXLSdata.class)
	public void validLoginTest(String url, String email, String password, String expectedMsg) {
		LoginPage loginPage = new LoginPage(driver);
		browserUtils = new BrowserUtils();
		loginPage.connectToApp(url);
		loginPage.performLogin(email, password);

		String actualMsg;

		if (email.startsWith("admin")) {
			browserUtils.explicitVisibleWait(loginPage.getAdminSuccesMsg());
			actualMsg = loginPage.getAdminSuccesMsg().getText();
		} else {

			browserUtils.explicitVisibleWait(loginPage.getUsersSuccesMsg());
			actualMsg = loginPage.getUsersSuccesMsg().getText();

		}

		assertEquals(actualMsg, expectedMsg);
	}

	// This test also utilizes a data provider to run with different invalid login
	// credentials.
	// The expected output is an error message which is compared with the actual
	// result.
	@Test(dataProvider = "testingData", dataProviderClass = ReadXLSdata.class)
	public void inValidLoginTest(String url, String email, String password, String expectedMsg) {
		LoginPage loginPage = new LoginPage(driver);
		browserUtils = new BrowserUtils();
		loginPage.connectToApp(url);
		loginPage.performLogin(email, password);

		browserUtils.explicitVisibleWait(loginPage.getErrorMsg());
		String actualMsg = loginPage.getErrorMsg().getText();

		assertEquals(actualMsg, expectedMsg);
	}

}