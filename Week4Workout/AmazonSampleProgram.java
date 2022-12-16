package Week4Workout;

import java.awt.Desktop.Action;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.utils.FileUtil;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.netty.handler.codec.AsciiHeadersEncoder.NewlineType;

public class AmazonSampleProgram {

	public static void main(String[] args) throws IOException, InterruptedException {

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--diable-notification-");

		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();

		driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys("oneplus 9 pro");

		driver.findElement(By.xpath("//input[@id='nav-search-submit-button']")).click();
		List<WebElement> FirstMobilePrice = driver.findElements(
				By.xpath("(//span[@class='a-price-symbol']/following-sibling::span[@class='a-price-whole'])[1]"));
		FirstMobilePrice.size();
		List<Integer> PriceList = new ArrayList<Integer>();
		for (WebElement mobilePrice : FirstMobilePrice) {
			String text = mobilePrice.getText();
			System.out.println(text);
			String replace = text.replace(",", "");
			if (replace.trim().length() > 0) {
				int price = Integer.parseInt(replace);
				PriceList.add(price);
			}
		}
		System.out.println("First Mobile Price :" + PriceList.get(0));

		String CustomerReview = driver.findElement(By.xpath("//span[@class='a-declarative']/following::span[@class]"))
				.getText();
		System.out.println("Review Count :" + CustomerReview);

		driver.findElement(By.xpath("//span[@class='a-size-medium a-color-base a-text-normal']")).click();

		Set<String> windowHandles = driver.getWindowHandles();
		List<String> windows = new ArrayList<String>(windowHandles);

		driver.switchTo().window(windows.get(1));

		WebElement EMIoptions = driver.findElement(By.xpath("//a[@id='trigger_emioptions']"));
		Actions bulider = new Actions(driver);
		bulider.doubleClick(EMIoptions).perform();

		driver.findElement(By.xpath("//span[@class='a-size-base inemi-installment-plan-tab-title']")).click();

		File screenshotAs = driver.getScreenshotAs(OutputType.FILE);
		File MyScreenshot = new File("./snaps/amazon.png");
		FileUtils.copyFile(screenshotAs, MyScreenshot);

		driver.findElement(By.xpath("//input[@id='add-to-cart-button']")).click();
		/*
		 * driver.getWindowHandles(); windows = new ArrayList<String>(windowHandles);
		 * System.out.println(windows);
		 */Thread.sleep(3000);
		String text2 = driver.findElement(By.xpath("//span[@class='a-size-base-plus a-color-base']//span[text()=' (1 item): ']")).getText();
		System.out.println("Added items : " + text2);
		String Subtotal = driver.findElement(By.xpath("//span[@id='attach-accessory-cart-subtotal']")).getText();
		System.out.println("Sub Total amount :" + Subtotal);
		String replace1 = Subtotal.replace("\\D", "").replace(",", "").replace("₹", "");
		System.out.println(replace1);
		int Totalamount = Integer.parseInt(replace1);
		if (Totalamount == PriceList.get(0)) {
			System.out.println("Total Matched");
		} else {
			System.out.println("Total is not matched");
		}
		driver.close();
		/*
		 * Amazon: 1.Load the URL https://www.amazon.in/ 2.search as oneplus 9 pro 3.Get
		 * the price of the first product 4. Print the number of customer ratings for
		 * the first displayed product 5. Click the first text link of the first image
		 * 6. Take a screen shot of the product displayed 7. Click 'Add to Cart' button
		 * 8. Get the cart subtotal and verify if it is correct. 9.close the browser
		 */

	}

}
