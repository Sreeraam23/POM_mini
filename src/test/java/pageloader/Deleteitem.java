package pageloader;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.CaseTestBase;

public class Deleteitem extends CaseTestBase{
	public String pval_del;
	public int del_cart_size;
	WebDriverWait wait;
	@FindBy(xpath="(//td/a)[2]")
	WebElement deletebtn;
	
	public Deleteitem() {
		PageFactory.initElements(driver, this);
	}

	public void delete() {
		deletebtn.click();
		wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		pval_del = driver.findElement(By.id("totalp")).getText();
		del_cart_size = driver.findElements(By.xpath("//tbody//td[2]")).size();
	}
	
}
