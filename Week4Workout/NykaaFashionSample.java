package Week4Workout;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NykaaFashionSample {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--diable-notification-");

		driver.get("https://www.nykaa.com/");
		driver.manage().window().maximize();

		WebElement BrandList = driver.findElement(By.xpath("//a[text()='brands']"));
		Actions bulider = new Actions(driver);
		bulider.moveToElement(BrandList).perform();

		driver.findElement(By.xpath("//input[@id='brandSearchBox']")).sendKeys("L'Oreal Paris");
		Thread.sleep(2000);
		WebElement BrandName = driver.findElement(By.xpath("(//div[@id='list_topbrands']/following::a)[1]"));
		BrandName.click();
		String Brand = driver.getTitle();
		System.out.println(Brand);

		driver.findElement(By.xpath("//button[@class=' css-n0ptfk']")).click();
		driver.findElement(By.xpath("//span[text()='customer top rated']")).click();
		driver.findElement(By.xpath("//span[text()='Category']")).click();
		driver.findElement(By.xpath("//span[text()='Hair']")).click();
		driver.findElement(By.xpath("(//span[text()='Hair Care'])[2]")).click();
		driver.findElement(By.xpath("//span[text()='Shampoo']")).click();
		driver.findElement(By.xpath("//span[text()='Concern']")).click();
		driver.findElement(By.xpath("//span[text()='Color Protection']")).click();

		String Filteritems = driver.findElement(By.xpath("//span[@class='filter-value']")).getText();
		System.out.println(Filteritems);

		if (Filteritems.contains("Shampoo")) {
			System.out.println("Text Matched");
		} else {
			System.out.println("Text Not Matched");
		}
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[@class='css-xrzmfa']")).click();
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> windows = new ArrayList<String>(windowHandles);

		driver.switchTo().window(windows.get(1));
		String Pricingpage = driver.getTitle();
		System.out.println(Pricingpage);
		WebElement ShampooQantity = driver.findElement(By.xpath("//select[@title='SIZE']"));
		Select SQ = new Select(ShampooQantity);
		SQ.selectByVisibleText("175ml");

		String ShampooPrice = driver.findElement(By.xpath("//span[text()='MRP:']/following-sibling::span")).getText();
		String Price = ShampooPrice.replace("₹", "");
		int shampooprice = Integer.parseInt(Price);
		System.out.println("Shampoo Total Price :" + shampooprice);

		driver.findElement(By.xpath("//span[@class='btn-text']")).click();
		driver.findElement(By.xpath("//button[@class='css-g4vs13']")).click();

		driver.switchTo().frame(0);
		String CartGrandTotal = driver.findElement(By.xpath("(//span[text()='Grand Total']/preceding::span)[15]"))
				.getText();
		String CartTotal = CartGrandTotal.replace("₹", "");
		int CartPrice = Integer.parseInt(CartTotal);
		System.out.println("Grand Total :" + CartPrice);

		driver.findElement(By.xpath("//span[text()='Proceed']")).click();
		driver.findElement(By.xpath("//button[text()='Continue as guest']")).click();
		/*
		 * windowHandles= driver.getWindowHandles(); windows = new
		 * ArrayList<String>(windowHandles); driver.switchTo().window(windows.get(1));
		 */
		  driver.findElement(By.xpath("//div[@class='css-65fmv9 emgron30']")).click();
		 
		
		String FinalTotal = driver.findElement(By.xpath("//p[@class='css-5t7v9l eka6zu20']")).getText();
		String finalTotal = FinalTotal.replace("₹", "");
		int FinalPrice = Integer.parseInt(finalTotal);
		System.out.println("Final Grand Total :" + FinalPrice);
		if (shampooprice == FinalPrice) {
			System.out.println("Grand Total Matched");
		} else {
			System.out.println("Grand Total Not Matched");
		}
driver.quit();
	}

}
