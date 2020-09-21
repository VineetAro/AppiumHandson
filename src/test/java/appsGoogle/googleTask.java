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

public class googleTask {
	AndroidDriver<MobileElement> driver;
    String[] tasks = {"Complete Activity with Google Tasks", "Complete Activity with Google Keep", "Complete the second Activity Google Keep"};
	

	@Test
	public void addTasks() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		MobileElement new_task = driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"com.google.android.apps.tasks:id/tasks_fab\")"));
		for (String s : tasks) {
			new_task.click();
			driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"com.google.android.apps.tasks:id/add_task_title\")")).sendKeys(s);
			driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"com.google.android.apps.tasks:id/add_task_done\")")).click();
		}
		
		List<MobileElement> created_Tasks = driver.findElements(MobileBy.AndroidUIAutomator("resourceId(\"com.google.android.apps.tasks:id/task_name\")"));
		int count = (created_Tasks.size());
		
		Assert.assertEquals(count, 3);
		
	}

	@BeforeMethod
	public void beforeMethod() throws MalformedURLException {

		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("deviceName", "Redmi Note 6 Pro");
		caps.setCapability("platformName", "android");
		caps.setCapability("automationName", "UiAutomator2");
		caps.setCapability("appPackage", "com.google.android.apps.tasks");
		caps.setCapability("appActivity", ".ui.TaskListsActivity");
		caps.setCapability("noReset", true);

		
		URL appServer = new URL("http://0.0.0.0:4723/wd/hub");

		driver = new AndroidDriver<MobileElement>(appServer, caps);

	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}

}
