package pageloader;

import java.time.Duration;

import java.util.Properties;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import base.CaseTestBase;

public class Signup extends CaseTestBase{
	WebDriverWait wait;
	@FindBy(id = "login2")
	WebElement loginbtn;
	
	@FindBy(id="loginusername")
	WebElement name;
	
	@FindBy(id="loginpassword")
	WebElement password;
	
	@FindBy(xpath="(//button[@class='btn btn-primary'])[3]")
	WebElement submitloginbtn;
	
	@FindBy(id="logout2")
	public WebElement logoutbtn;
	
	public Signup() {
		PageFactory.initElements(driver, this);
	}
	
	public void login() throws InterruptedException {
		loginbtn.click();
		name.sendKeys(prop.getProperty("username"));
		password.sendKeys(prop.getProperty("password"));
		submitloginbtn.click();
			
	}
	
}
