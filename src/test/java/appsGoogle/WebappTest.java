package appsGoogle;

import org.testng.annotations.Test;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class WebappTest {
	
	MobileDriver<MobileElement> driver;
	String[] toDos = {"Add tasks to list", "Get number of tasks", "Clear the list"};
	
  @Test
  public void act_1() {
	  
	  driver.findElement(MobileBy.AndroidUIAutomator("UiScrollable(UiSelector().scrollable(true).instance(0)).scrollIntoView(textStartsWith(\"To-Do List\"))"));
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  driver.findElement(MobileBy.xpath("//android.widget.TextView[@text='To-Do List']")).click();
	  
	  //Clear toDos before starting
	  //driver.findElement(MobileBy.AndroidUIAutomator("text(\"Clear List\")")).click();
	  driver.findElement(By.xpath("//android.webkit.WebView/android.view.View/android.view.View[3]/android.view.View[3]")).click();
	  
	  //Add ToDos
	  
	  for (String s : toDos) {
	  driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"taskInput\")")).sendKeys(s);
	  driver.findElement(MobileBy.AndroidUIAutomator("text(\"Add Task\")")).click();
	  }
	  
	  //Clear todos
	  List<MobileElement> created_Tasks = driver.findElements(MobileBy.AndroidUIAutomator("resourceId(\"tasksList\")"));
	  for (MobileElement s : created_Tasks) {
		  s.click();
		  }
	  
	  driver.findElement(By.xpath("//android.webkit.WebView/android.view.View/android.view.View[3]/android.view.View[3]")).click();
	  
	  //Assertion
	  List<MobileElement> cleared_Tasks = driver.findElements(MobileBy.AndroidUIAutomator("resourceId(\"tasksList\")"));
	  Assert.assertEquals(cleared_Tasks.size(), 0);
	  	  
  }
  
  @Test
  public void ValidLogin() {
	  
	  driver.findElement(MobileBy.AndroidUIAutomator("UiScrollable(UiSelector().scrollable(true).instance(0)).scrollIntoView(textStartsWith(\"Login Form\"))"));
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  driver.findElement(MobileBy.xpath("//android.widget.TextView[@text='Login Form']")).click();
	  
	  //Enter credentials 
	  //Valid Credentials
	  
	  String UserName = "admin";
	  String PassWord = "password";
	  
	  driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"username\")")).sendKeys(UserName);
	  driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"password\")")).sendKeys(PassWord);
	  driver.findElement(MobileBy.AndroidUIAutomator("text(\"Log in\")")).click();
	  
	  String loginMessage = driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"action-confirmation\")")).getText();
	  
	  Assert.assertEquals(loginMessage, "Welcome Back, admin");
	  
	  
	  //Invalid Credentials
	  String UserName1 = "admin1";
	  String PassWord1 = "password1";
	  
	  WebElement user = driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"username\")"));
	  WebElement pass = driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"password\")"));
	  user.clear();
	  pass.clear();
	  user.sendKeys(UserName1);
	  pass.sendKeys(PassWord1);
	  driver.findElement(MobileBy.AndroidUIAutomator("text(\"Log in\")")).click();
	  
	  String inloginMessage = driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"action-confirmation\")")).getText();
	  
	  Assert.assertEquals(inloginMessage, "Invalid Credentials");
	  
	  	  	  
  }
  
  @Test
  public void LoginSimpleForm() {
	  
	  driver.findElement(MobileBy.AndroidUIAutomator("UiScrollable(UiSelector().scrollable(true).instance(0)).scrollIntoView(textStartsWith(\"Popups\"))"));
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  driver.findElement(MobileBy.xpath("//android.widget.TextView[@text='Popups']")).click();
	  
	  
	  //Enter credentials 
	  //Valid Credentials
	  
	  String UserName = "admin";
	  String PassWord = "password";
	  
	  driver.findElement(MobileBy.AndroidUIAutomator("text(\"Sign In\")")).click();
	  
	  WebElement user = driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"username\")"));
	  WebElement pass = driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"password\")"));
	  
	  user.sendKeys(UserName);
	  pass.sendKeys(PassWord);
	  driver.findElement(MobileBy.AndroidUIAutomator("text(\"Log in\")")).click();
	  
	  String loginMessage = driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"action-confirmation\")")).getText();
	  
	  Assert.assertEquals(loginMessage, "Welcome Back, admin");
	  
	  
	  //Invalid Credentials
	  String UserName1 = "admin1";
	  String PassWord1 = "password1";
	  
	  driver.findElement(MobileBy.AndroidUIAutomator("text(\"Sign In\")")).click();
	  
	  
	  user.clear();
	  pass.clear();
	  user.sendKeys(UserName1);
	  pass.sendKeys(PassWord1);
	  driver.findElement(MobileBy.AndroidUIAutomator("text(\"Log in\")")).click();
	  
	  String inloginMessage = driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"action-confirmation\")")).getText();
	  
	  Assert.assertEquals(inloginMessage, "Invalid Credentials");
	  
	  	  	  
  }
  
  
  
  @BeforeMethod
  public void beforeMethod() throws MalformedURLException {

		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("deviceName", "Redmi Note 6 Pro");
		caps.setCapability("platformName", "android");
		caps.setCapability("automationName", "UiAutomator2");
		caps.setCapability("appPackage", "com.android.chrome");
		caps.setCapability("appActivity", "com.google.android.apps.chrome.Main");
		caps.setCapability("noReset", true);

		
		URL appServer = new URL("http://0.0.0.0:4723/wd/hub");

		driver = new AndroidDriver<MobileElement>(appServer, caps);
		
		driver.get("https://www.training-support.net/selenium");

	}

  @AfterMethod
  public void afterMethod() {
	  
	  driver.quit();
  }

}
