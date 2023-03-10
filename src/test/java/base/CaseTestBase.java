package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
public class CaseTestBase {
	//include  property
	//include WebDriverManager
	public static Properties prop = null;
	public static WebDriver driver = null;
	
	
	public CaseTestBase() {
		String path = System.getProperty("user.dir")+"//src//test//resources//config//config.properties";
		prop = new Properties();
		FileInputStream fin;
		try {
			fin = new FileInputStream(path);
			try {
				prop.load(fin);
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
	}
	public void instance() {
		String browser = prop.getProperty("browser");
		if(browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
			driver.get("https://www.demoblaze.com/index.html");
						
		}
	}
	
}
