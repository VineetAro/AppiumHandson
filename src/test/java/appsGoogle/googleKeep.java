package appsGoogle;

import org.testng.annotations.Test;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class googleKeep {
	AndroidDriver<MobileElement> driver;
	
  @Test
  public void addNotes() {
	  driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	  List<MobileElement> created_Tasks = driver.findElements(MobileBy.AndroidUIAutomator("resourceId(\"com.google.android.keep:id/browse_text_note\")"));
	  int init_count = (created_Tasks.size());
	  
	  driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"com.google.android.keep:id/new_note_button\")")).click();
	  driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"com.google.android.keep:id/editable_title\")")).sendKeys("TitleNotes");
	  driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"com.google.android.keep:id/edit_note_text\")")).sendKeys("Notes Text");
	  
	  driver.findElementByAccessibilityId("Open navigation drawer").click();
	  
	  created_Tasks = driver.findElements(MobileBy.AndroidUIAutomator("resourceId(\"com.google.android.keep:id/browse_text_note\")"));
	  int new_count = (created_Tasks.size());
	  
	  Assert.assertEquals((new_count - init_count), 1);
	    
	  
  }
  
  @Test
  public void addReminder() {
	  driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	  driver.findElementByAccessibilityId("Open navigation drawer").click();
	  driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"com.google.android.keep:id/drawer_navigation_reminders\")")).click();	  
	  
	  List<MobileElement> created_Tasks = driver.findElements(MobileBy.AndroidUIAutomator("resourceId(\"com.google.android.keep:id/browse_text_note\")"));
	  int init_count = (created_Tasks.size());
	  
	  driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"com.google.android.keep:id/new_note_button\")")).click();
	  driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"com.google.android.keep:id/editable_title\")")).sendKeys("TitleNotes");
	  driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"com.google.android.keep:id/edit_note_text\")")).sendKeys("Notes Text");
	  
	  driver.findElementByAccessibilityId("Reminder").click();
	  
	  driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"com.google.android.keep:id/time_spinner\")")).click();
	  driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"com.google.android.keep:id/reminder_time_afternoon\")")).click();
	  driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"com.google.android.keep:id/save\")")).click();
	  
	  driver.findElementByAccessibilityId("Open navigation drawer").click();
	  driver.findElementByAccessibilityId("Open navigation drawer").click();
	  driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"com.google.android.keep:id/drawer_navigation_reminders\")")).click();	  
	  
	  created_Tasks = driver.findElements(MobileBy.AndroidUIAutomator("resourceId(\"com.google.android.keep:id/browse_text_note\")"));
	  int new_count = (created_Tasks.size());
	  
	  Assert.assertEquals((new_count - init_count), 1);
	    
	  
  }
  
  @BeforeMethod
  public void beforeMethod() throws MalformedURLException {

		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("deviceName", "Redmi Note 6 Pro");
		caps.setCapability("platformName", "android");
		caps.setCapability("automationName", "UiAutomator2");
		caps.setCapability("appPackage", "com.google.android.keep");
		caps.setCapability("appActivity", ".activities.BrowseActivity");
		caps.setCapability("noReset", true);

		
		URL appServer = new URL("http://0.0.0.0:4723/wd/hub");

		driver = new AndroidDriver<MobileElement>(appServer, caps);

	}


  @AfterMethod
  public void afterMethod() {
	  driver.quit();
  }

}
