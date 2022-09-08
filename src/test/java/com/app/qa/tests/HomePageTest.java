package com.app.qa.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.app.qa.base.TestBase;
import com.app.qa.pages.ContactsPage;
import com.app.qa.pages.HomePage;
import com.app.qa.pages.LoginPage;
import com.app.qa.util.TestUtil;

public class HomePageTest extends TestBase{

	LoginPage _loginPage;
	HomePage _homePage;
	TestUtil _testUtil;
	ContactsPage _contactsPage;
	
	public HomePageTest() {
		super();
	}
	
	@BeforeMethod
	public void SetUp() throws IOException{
		initialization();
		_testUtil = new TestUtil();
		_loginPage = new LoginPage();
		_contactsPage = new ContactsPage();
		_homePage = _loginPage.Login(prop.getProperty("username"), prop.getProperty("password"));
	}
		
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test(enabled = false) 
	public void HomePageTitleTest() {
		String Title = _homePage.GetPageTitle();
		Assert.assertEquals(Title, "CRMPRO", "Failed: HomePage title not matched!");
	}
	
	@Test(enabled = false) 
	public void UserNameLabelTest() {
		_testUtil.SwitchToFrame("mainpanel");
		Assert.assertTrue(_homePage.VerifyUserNameLabel(), "Failed: The Username Label in HomePage not displayed!");
	}
	
	@Test
	public void VerifyContactLinkTest() {
		_testUtil.SwitchToFrame("mainpanel");
		_contactsPage = _homePage.ClickOnContactsLink();
		String Title = _contactsPage.GetPageTitle();
		Assert.assertEquals(Title, "CRMPRO", "Failed: ContactsPage title not matched!");
		Assert.assertTrue(_contactsPage.VerifyContactsLabel(), "Failed: The Contact Label in ContactsPage not displayed!");
	}
}
