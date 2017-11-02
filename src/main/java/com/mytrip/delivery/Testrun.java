package com.mytrip.delivery;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This class is used for quick check while writing the code *
 */
public class Testrun {
	static String driverPath = System.getProperty("user.dir")
			+ "\\Resources\\drivers\\";

	public static void main(String[] args) {
		
		List<String> elemetsfrom = new ArrayList<String>();
		elemetsfrom.add("12345");
		elemetsfrom.add("12349");
		elemetsfrom.add("12345");
		elemetsfrom.add("12346");
		elemetsfrom.add("12347");
		Collections.sort(elemetsfrom);
		System.out.println(elemetsfrom.get(1));
		// String value = "SIG 1234\n Economy AirIndia";
		// //value = value.substring(3, value.length());
		// String tvalue[] = value.replace("Economy","").split("\n");
		// C:\Users\Balaji.Rao\AppData\Local\Mozilla Firefox
		// System.out.println(tvalue[0] +" " + tvalue[1].trim());

//		System.setProperty("webdriver.gecko.driver", driverPath
//				+ "geckodriver.exe");
//		WebDriver driver = new FirefoxDriver();
//
//		driver.get("https://www.cleartrip.com");
//		WebDriverWait wait = new WebDriverWait(driver, 10);
//		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By
//				.id("RoundTrip")));
//		// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//		WebElement tripRadio = driver.findElement(By.id("RoundTrip"));
//		//
//		if (tripRadio.isDisplayed())
//			tripRadio.click();
//		driver.findElement(By.id("RoundTrip")).click();
//		driver.findElement(By.id("FromTag")).click();
//		driver.findElement(By.id("FromTag")).clear();
//		driver.findElement(By.id("FromTag")).sendKeys("HYD");
//		// driver.findElement(By.id("ui-id-7")).click();
//		// driver.findElement(By.id("ToTag")).clear();
//		// driver.findElement(By.id("ToTag")).sendKeys("Visakhapatnam, IN - Vishakhapatnam (VTZ)");

	}

}
