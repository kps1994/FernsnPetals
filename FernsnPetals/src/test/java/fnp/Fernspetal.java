package fnp;

import org.testng.annotations.Test;

import fnp.Dataexc;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.BeforeClass;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class Fernspetal {
	public static WebDriver driver;

	@Test(dataProvider = "dp")
	public void fan(String u, String p) throws InterruptedException {
		driver.get("https://www.flipkart.com");
		driver.findElement(By.xpath("//input[@class='_2IX_2- VJZDxU']")).sendKeys(u);
		driver.findElement(By.xpath("//input[@class='_2IX_2- _3mctLh VJZDxU']")).sendKeys(p);
		driver.findElement(By.xpath("//button[@class='_2KpZ6l _2HKlqd _3AWRsL']")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//span[@class='cartButton_login_content__2j1f7']")).click();
		Thread.sleep(4000);
	}

	@DataProvider
	public Object[][] dp() throws IOException {
		Dataexc da = new Dataexc("C:\\Users\\kps\\Documents\\fnp_login1.xlsx");
		int totalrow = da.rowcount(0);
		Object[][] data = new Object[totalrow][2];
		for (int i = 0; i < totalrow; i++) {
			data[i][0] = da.getCellData(0, i, 0);
			data[i][1] = da.getCellData(0, i, 1);
		}
		return data;
	};

	@Parameters("browser")
  @BeforeClass
  public void beforeClass(String browser)
  {
	  if(browser.equalsIgnoreCase("chrome"))
	  {
		  
	  System.setProperty("webdriver.chrome.driver", "E:\\Selenium\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
  }
	  else if(browser.equalsIgnoreCase("firefox"))
	  {
		  System.setProperty("webdriver.gecko.driver","E:\\Selenium\\geckodriver.exe");
	      driver=new FirefoxDriver();
	  }
  }

	@AfterClass
	public void afterClass() {
	}

}
