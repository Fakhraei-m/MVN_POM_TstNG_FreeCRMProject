package com.app.qa.pages;

import com.app.qa.base.TestBase;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
//import org.eclips.jetty.

public class LoginPage extends TestBase{

	@FindBy(name= "username")
	WebElement username;
	
	@FindBy(name= "password")
	WebElement password;
	
	@FindBy(xpath= "//input[@type='submit']")
	WebElement loginBtn;
	
	@FindBy(xpath= "//button[contains(text(),'Sign Up')]")
	WebElement signupBtn;
	
	@FindBy(xpath= "//img[@src='https://classic.freecrm.com/img/logo@2x.png']")
	WebElement crmLogo;
	
	//Initializing the page objects
	public LoginPage(){
		PageFactory.initElements(driver, this);
	}
	
	public String GetPageTitle() {
		return driver.getTitle();
	}

	public boolean GetCRMImage() {
		return crmLogo.isDisplayed();
	}
	
	public HomePage Login(String un, String pwd) {
		username.sendKeys(un);
		password.sendKeys(pwd);
		loginBtn.click();
		
		return new HomePage();
	}
}
