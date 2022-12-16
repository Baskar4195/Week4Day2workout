package Week4Workout;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LearnFrames {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();

		ChromeDriver driver = new ChromeDriver();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-Notification");

		driver.get("https://chercher.tech/practice/frames-example-selenium-webdriver");

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		String title = driver.getTitle();
		System.out.println(title);

		driver.switchTo().frame("frame1");

		WebElement Frame1Webelement = driver.findElement(By.xpath("//b[text()='Topic :']/following-sibling::input"));
		Frame1Webelement.sendKeys("Baskar");

		driver.switchTo().frame("frame3");

		driver.findElement(By.xpath("//input[@id='a']")).click();

		driver.switchTo().defaultContent();

		driver.switchTo().frame("frame2");
		WebElement Selectinput = driver.findElement(By.xpath("//select[@id='animals']"));
		Selectinput.click();
		Select Dropdown = new Select(Selectinput);
		Dropdown.selectByVisibleText("Avatar");
		driver.switchTo().defaultContent();

	}

}
