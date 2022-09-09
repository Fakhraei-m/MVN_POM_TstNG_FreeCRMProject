package com.app.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.app.qa.base.TestBase;

public class ContactsPage extends TestBase{

	//initializing the page objects
	public ContactsPage() {
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath = "//td[contains(text(),'Contacts')]")
	WebElement ContactsLabel;
	
	@FindBy(xpath = "//input[@name='first_name']")
	WebElement NewContact_FirstNameField;
	
	@FindBy(id = "surname")
	WebElement NewContact_LastNameField;
	
	@FindBy(name = "client_lookup")
	WebElement NewContact_CompanyField;
	
	@FindBy(xpath = "//input[@type='submit' and @value='Save' and @class='button']")
	WebElement NewContact_SaveButton;
	
	
	public String GetPageTitle() {
		return driver.getTitle();
	}
	
	public String GetContactsName(String Name) {
		return driver.findElement(By.xpath("//a[text()='"+Name+"']//parent::td[@class='datalistrow']")).getText();
	}
	
	public boolean VerifyContactsLabel() {
		return ContactsLabel.isDisplayed();
	}
	
	public void CreateNewContact(String title, String firstName, String lastName, String company) {
		Select select = new Select(driver.findElement(By.name("title")));
		select.selectByVisibleText(title);
		
		NewContact_FirstNameField.sendKeys(firstName);
		NewContact_LastNameField.sendKeys(lastName);
		NewContact_CompanyField.sendKeys(company);
		NewContact_SaveButton.click();
	}
	
}
