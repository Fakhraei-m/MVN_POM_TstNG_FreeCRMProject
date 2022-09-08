package com.app.qa.util;
/*************************************** PURPOSE **********************************
 - This class implements the WebDriverEventListener, which is included under events.
 The purpose of implementing this interface is to override all the methods and define certain useful  Log statements 
 which would be displayed/logged as the application under test is being run.
 Do not call any of these methods, instead these methods will be invoked automatically
 as an when the action done (click, findBy etc). 
 */

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;

import com.app.qa.base.TestBase;

public class MyWebDriverListener extends TestBase implements WebDriverListener {

	/*
	@Override
    public void beforeAnyCall(Object target, Method method, Object[] args) {
		System.out.printf(" * About to call a method '%s' in '%s' with parameters '%s' ",
                 method, target, args);
    }
    @Override
    public void afterAnyCall(Object target, Method method, Object[] args, Object result) {
    	System.out.printf(" * Method %s called in %s with parameters %s returned %s",
                 method, target, args, result);
    }
    */
	
    @Override
    public void beforeGet(WebDriver driver, String url) {
    	System.out.printf("About to open a page %s", url);
    	System.out.println();
    }
    
    @Override
    public void afterGetText(WebElement element, String result) {
    	System.out.printf("Element %s has text '%s'", element, result);
    	System.out.println();
    }
    
    @Override
    public void beforeAnyWebElementCall(WebElement element, Method method, Object[] args) {
    	System.out.printf("About to call a method %s in element %s with parameters %s",
                 method, element, args);
    	System.out.println();
    }
    
    @Override
    public void afterAnyWebElementCall(WebElement element, Method method, Object[] args, Object result) {
    	System.out.printf("Method %s called in element %s with parameters %s returned %s",
                 method, element, args, result);
    	System.out.println();
    }

    @Override
    public void onError(Object target, Method method, Object[] args, InvocationTargetException e) {
        try {
        	TestUtil.TakeScreenshot();//TakeScreenShot();
        	System.out.println("***TAKING SCREENSHOT ****");
        } catch (IOException exp) {
        	exp.printStackTrace();
        }   	
    	//throw new RuntimeException("listener");
    }

}