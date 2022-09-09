package com.app.qa.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.app.qa.base.TestBase;
import com.app.qa.pages.ContactsPage;
import com.app.qa.pages.HomePage;
import com.app.qa.pages.LoginPage;
import com.app.qa.util.TestUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;

public class ContactsPageTest extends TestBase{

	LoginPage _loginPage;
	HomePage _homePage;
	TestUtil _testUtil;
	ContactsPage _contactsPage;
	String sheetName = "contacts";
	
	public ContactsPageTest() {
		super();
	}
	
	@BeforeMethod
	public void SetUp() throws IOException{
		initialization();
		_testUtil = new TestUtil();
		_loginPage = new LoginPage();
		//_contactsPage = new ContactsPage();
		_homePage = _loginPage.Login(prop.getProperty("username"), prop.getProperty("password"));
		_testUtil.SwitchToFrame("mainpanel");
		
	}
		
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test(priority=1, description="Verifying Contacts Label exists")//(enabled = false) //TestNG Annotation
	@Severity(SeverityLevel.NORMAL) //Allure Annotation
	@Description("Test Case Description: Verifying Contacts Label exists in Contacts Page") //Allure Annotation
	@Epic("EP001") //Allure Annotation
	@Feature("Feature1: Contact Page Label") //Allure Annotation
	@Story("Story: Label Presence") //Allure Annotation
	@Step("Verifying Contacts Label exists in Contacts Page") //Allure Annotation
	public void VerifyContactsLabelTest() {
		_contactsPage = _homePage.ClickOnContactsLink();
		_contactsPage.VerifyContactsLabel();
	}
	
	@Test(enabled = false)
	public void SelectContactsTest() {
		_contactsPage = _homePage.ClickOnContactsLink();
		String Name = _contactsPage.GetContactsName(prop.getProperty("name"));
		Name = Name.replaceAll("\\s+$", "");
		Assert.assertEquals(Name, prop.getProperty("name"), "Failed: Contacts name not matched!");
	}
	
	@DataProvider
	public Object[][] GetTestData() {
		Object data[][] = TestUtil.GetTestData(sheetName);
		return data;
	}
	
	@Test(enabled = false, dataProvider="GetTestData")
	public void CreateNewContactTest(String title, String firstName, String lastName, String company) {
		_contactsPage = _homePage.ClickOnCreateNewContactLink();
		
		_contactsPage.CreateNewContact(title, firstName, lastName, company);
	}
	
	@Test//(retryAnalyzer = com.app.qa.util.MyRetryAnalyzer.class) 
	public void CreateNewContactTest_HardCodedData() {
		_contactsPage = _homePage.ClickOnCreateNewContactLink();
		
		_contactsPage.CreateNewContact("Mr.", "Mehmet", "Efendim", "CoffeeBeans");
	}
	
}
