package com.app.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import com.app.qa.base.TestBase;

public class HomePage extends TestBase{


	@FindBy(xpath = "//a[contains(text(),'Contacts')]")
	WebElement ContactsLink;
	
	@FindBy(xpath = "//a[contains(text(),'Deals')]")
	WebElement DealsLink;
	
	@FindBy(xpath = "//a[contains(text(),'Tasks')]")
	WebElement TasksLink;
	
	@FindBy(xpath = "//td[contains(text(),'User: Mohammad Fakhraei')]") // This won't work because there is a Frame layaer in the page
	WebElement UserNameLabel;
	
	@FindBy(xpath = "//a[contains(text(),'New Contact')]")
	WebElement NewContactLink;

	//initializing the page objects
	public HomePage() {
		PageFactory.initElements(driver,this);
	}
	
	public boolean VerifyUserNameLabel() {
		return UserNameLabel.isDisplayed();
	}
	
	public String GetPageTitle() {
		return driver.getTitle();
	}
	
	public ContactsPage ClickOnContactsLink() {
		ContactsLink.click();
		return new ContactsPage();	
	}
	
	public DealsPage ClickOnDealsLink() {
		DealsLink.click();
		return new DealsPage();	
	}
	
	public TasksPage ClickOnTasksLink() {
		TasksLink.click();
		return new TasksPage();	
	}
	
	public ContactsPage ClickOnCreateNewContactLink() {
		Actions action = new Actions(driver);
		
		action.moveToElement(ContactsLink).build().perform();
		NewContactLink.click();
		return new ContactsPage();
	}
}
