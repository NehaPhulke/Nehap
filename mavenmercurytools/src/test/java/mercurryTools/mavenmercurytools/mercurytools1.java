package mercurryTools.mavenmercurytools;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class mercurytools1 {
	
	public WebDriver driver=null;
	Properties prop=new Properties();
	
	@Test(priority=1)
	public void openbrowser() throws IOException
	{
		FileInputStream file=new FileInputStream("F:\\cjc eclips\\PropertyFilesbyMercurytool\\data.properties");
		prop.load( file);
		System.out.println(prop.getProperty("browser"));
		System.out.println(prop.getProperty("url"));
		System.out.println(prop.getProperty("username"));
		System.out.println(prop.getProperty("password"));
		System.out.println(prop.getProperty("usernameinvalid"));
		System.out.println(prop.getProperty("passwordinvalid"));
		System.out.println("neha....");
		if(prop.getProperty("browser").equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver","F:\\Selenium\\chromedriver_win32 (1)\\chromedriver.exe");
			driver=new ChromeDriver();
			System.out.println(prop.getProperty("browser")+""+"browser is opened succcessfully");
		
	}
		else if(prop.getProperty("browser").equalsIgnoreCase("firefox"))
		{
          System.setProperty("webdriver.gecko.driver","F:\\Selenium\\geckodriver-v0.21.0-win32\\geckodriver.exe");
          driver=new FirefoxDriver();
          System.out.println(prop.getProperty("browser")+""+"browser is opened succcessfully");
		}
		
		else
		{
			System.setProperty("webdriver.ie.driver","F:\\Selenium\\IEDriverServer_Win32_3.12.0\\IEDriverServer.exe");
			driver= new InternetExplorerDriver();
			System.out.println(prop.getProperty("browser")+""+"browser is opened succcessfully");
			
			
		}
	}
		@Test(priority=2)
		public void EnterApplication()
		{
			driver.get(prop.getProperty("url"));
			System.out.println("Application url"+prop.getProperty("url")+""+"is enterd successfully");
		}
        
		@Test(priority=3)
		public void maximizebrowser()
		{
		driver.manage().window().maximize();	
		}
		
		@Test(priority=4,description="verify that user able to enter mercurytours with valid credentials")
		public void login() throws InterruptedException
		{
			driver.findElement(By.xpath("//input[@name='userName']")).sendKeys(prop.getProperty("username"));
			System.out.println("user with username as"+""+prop.getProperty("username")+""+"is enterd successfully");
		    driver.findElement(By.xpath("//input[@name='password']")).sendKeys(prop.getProperty("password"));
		    System.out.println("user with password as"+""+prop.getProperty("password")+""+"is enteerd successfully");
		    driver.findElement(By.xpath("//input[@name='login']")).click();
		   // Thread.sleep(5000);
		   /* boolean act_flag=driver.findElement(By.xpath("//img[@src='/images/masts/mast_flightfinder.gif']")).isDisplayed();
		    boolean exp_flag=true;
		    Assert.assertEquals(act_flag,exp_flag);*/
		    
		    Assert.assertEquals(driver.getTitle().trim(), "Find a Flight: Mercury Tours:");
		    
		    driver.findElement(By.linkText("SIGN-OFF")).click();
		    Thread.sleep(5000);
		}
       
		@Test(priority=5,description="verify that user able to enter mercurytours with valid credentials")
		public void invalidLogin() throws InterruptedException
		{
			driver.findElement(By.xpath("//input[@name='userName']")).sendKeys(prop.getProperty("usernameinvalid"));
			System.out.println("user with username as"+""+prop.getProperty("usernameinvalid")+""+"is enterd successfully");
		    driver.findElement(By.xpath("//input[@name='password']")).sendKeys(prop.getProperty("passwordinvalid"));
		    System.out.println("user with password as"+""+prop.getProperty("passwordinvalid")+""+"is enteerd successfully");
		    driver.findElement(By.xpath("//input[@name='login']")).click();
		    Thread.sleep(5000);
		    boolean act_flag=driver.findElement(By.xpath("//img[@src='/images/masts/mast_flightfinder.gif']")).isDisplayed();
		    boolean exp_flag=true;
		    Assert.assertEquals(act_flag,exp_flag);
		    Assert.assertEquals(driver.getTitle().trim(), "Find a Flight: Mercury Tours:");
		    driver.findElement(By.linkText("SIGN-OFF")).click();
		}
}
