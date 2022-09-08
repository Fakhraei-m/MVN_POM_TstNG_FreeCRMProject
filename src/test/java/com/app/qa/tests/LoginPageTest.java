package com.app.qa.tests;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.app.qa.base.TestBase;
import com.app.qa.pages.HomePage;
import com.app.qa.pages.LoginPage;

public class LoginPageTest extends TestBase{
	
	LoginPage loginPage;
	HomePage homePage;
	
	public LoginPageTest() throws IOException{
		super(); //calling the super class constructor
	}

	@BeforeMethod
	public void SetUp() throws IOException{
		initialization();
		loginPage = new LoginPage();
	}
		
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test(priority=1)
	public void loginPageTitleTest() {
		String Title = loginPage.GetPageTitle();
		Assert.assertEquals(Title, "Free CRM - CRM software for customer relationship management, sales, and support.");
	}
	
	@Test(priority=2)
	public void crmLogoImageTest() {
		boolean flag = loginPage.GetCRMImage();
		Assert.assertTrue(flag,"Failed: LoginPage Logo was not displayed!");
	}
	
	@Test(priority=3, enabled = false)
	public void loginTest() {
		homePage = loginPage.Login(prop.getProperty("username"), prop.getProperty("password"));
		String Title = homePage.GetPageTitle();
		Assert.assertEquals(Title, "CRMPRO", "Failed: HomePage title not matched!");
	}
}
