package Week4Workout;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LearnWindows {

	public static void main(String[] args) throws InterruptedException {

		WebDriverManager.chromedriver().setup();

		ChromeDriver driver = new ChromeDriver();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-Notification");

		driver.get("https://leafground.com/window.xhtml;jsessionid=node0ssnceyfd5mtq1u38yu3inp0zh436503.node0");

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		//Click and Confirm new window opens 
		driver.findElement(By.xpath("//span[text()='Open']")).click();
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> windows = new ArrayList<String>(windowHandles);
		driver.switchTo().window(windows.get(1));
		System.out.println("New Window opened :" + driver.getTitle());
		// driver.close();
		driver.switchTo().window(windows.get(0));

		// Find the number of opened tabs
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[text()='Open Multiple']")).click();

		// Set<String> windowHandles = driver.getWindowHandles();
		windows = new ArrayList<String>(windows);
		int NumofWindows = driver.getWindowHandles().size();
		System.out.println("These many Tabs Opened :" + NumofWindows);
		driver.switchTo().window(windows.get(0));

		// close all windows except Primary
		driver.findElement(By.xpath("//span[text()='Close Windows']")).click();
		String ParentWindow = driver.getWindowHandle();
		for (String Parent : driver.getWindowHandles()) {
			if (!Parent.equals(ParentWindow)) {
				driver.switchTo().window(Parent);
				driver.close();

			}

		}
		driver.switchTo().window(ParentWindow);

		// wait for 2 new tabs to open
		driver.findElement(By.xpath("//span[text()='Open with delay']")).click();
		Thread.sleep(3000);

	}

}
