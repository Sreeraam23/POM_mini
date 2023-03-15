package testscripts;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import base.CaseTestBase;
import pageloader.OrderListPage;
import pageloader.OrderListDeletePage;
import pageloader.PurchasePage;
import pageloader.Selectionpage;
import pageloader.Signup;

public class CasePlaceOrder extends CaseTestBase{
	Signup signin;
	WebDriverWait wait;
	Selectionpage item;
	OrderListPage check;
	OrderListDeletePage del; 
	PurchasePage place;
	String p_val;
	public int c_size;
	String del_p_val;
	public int del_c_size;
  @BeforeTest
  public void setup() {
	  instance();
  }
  @Test(priority=1)
  public void login() throws InterruptedException {
	  signin = new Signup();
	  signin.login();
	  WebElement logout = signin.logoutbtn;
	  wait = new WebDriverWait(driver,Duration.ofSeconds(10));
	  wait.until(ExpectedConditions.textToBePresentInElement(logout, "Log out"));
	  Assert.assertTrue(logout.isDisplayed());
  }
	
  @Test(priority=2,dataProvider = "products")
  public void additem(String cat,String pro) {
	  item = new Selectionpage();
	  item.select(cat,pro);
  }
  @Test(priority=3)
  public void cart() {
	  check = new OrderListPage();
	  check.cart();
	  c_size = check.cart_size;
	  p_val = check.price;
  }

  @Test(priority=4,dependsOnMethods="cart")
  public void del_cart(){
	  int before_cart = c_size;
	  del = new OrderListDeletePage();
	  del.delete();
	  driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));	  
	  String price_before = check.price;
	  String price_after = del.pval_del;
	  int after_cart = del.del_cart_size;
	  boolean res = price_before.equalsIgnoreCase(price_after);
	  Assert.assertFalse(res);
  }
  @Test(priority=5)
  public void finalise() throws InterruptedException {
	  place = new PurchasePage();
	  Thread.sleep(5000);
	  place.order();
	  WebElement msg = place.message;
//	  Assert.assertEquals(msg.getText(),"Thank you for your purchase!");
  }
@DataProvider(name="products")
  public Object[][] getdata() throws CsvValidationException, IOException{
	  String path = System.getProperty("user.dir")+"\\src\\test\\resources\\DataFolder\\selectitems.csv";
	  CSVReader reader = new CSVReader(new FileReader(path));
	  String[] col;
	  ArrayList<Object> datalist = new ArrayList<Object>();
	  while((col = reader.readNext())!=null) {
		  Object[] record = {col[0],col[1]};
		  datalist.add(record);
	  }
	  
	  	return datalist.toArray(new Object[datalist.size()][]);
	  
  }
}
