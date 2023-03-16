package pageloader;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.CaseTestBase;

public class HomePage extends CaseTestBase{
	WebDriverWait wait;
	@FindBy(id = "login2")
	WebElement loginbtn;
	
	@FindBy(id="cartur")
	WebElement cart;
	
	@FindBy(id="logout2")
	public WebElement logoutbtn;
	
	@FindBy(xpath="(//td/a)[2]")
	WebElement deletebtn;
	
	@FindBy(xpath="//button[text()='Place Order']")
	WebElement placeorder;
	
	@FindBy(xpath="(//ul/li/a)[1]")
	public WebElement home;
	
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	public LoginPage login_func() {
		loginbtn.click();
		return new LoginPage();
	}
	public Selectionpage sel() {
		home.click();
		return new Selectionpage();
	}
	
	public OrderListPage viewcart() {
		cart.click();
		return new OrderListPage();
	}
	
	public OrderListDeletePage delete_cart() {
		deletebtn.click();
		return new OrderListDeletePage();
	}
	public PurchasePage purchase() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		placeorder.click();
		wait.until(ExpectedConditions.elementToBeClickable(placeorder));
		return new PurchasePage();
	}
}
