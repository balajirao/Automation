package com.mytrip.pages;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ItineraryPage {

	private WebDriver driver;
	private By itineryblock = By.xpath(".//*[@id='itinBlock']");
	String itinBlock1xpath = "//*[@id='itinBlock']/div/div/div[1]/ul";
	String itinBlock2xpath = "//*[@id='itinBlock']/div/div/div[2]/ul";
	
	String flightxpath = "/li[1]/div[2]";
	String time1xpath = "/li[2]/time/span[1]/strong";
	String time2xpath = "/li[4]/time/span[1]/strong";
	String JournyDuration = "/li[3]/abbr";
	
	public HashMap<String, String> onwardJourney, returnJourney;


	
	public ItineraryPage(WebDriver driver){
		this.driver = driver;
	}
	
	private HashMap<String, String>  getFlightDetails(String tablexpath) {
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		HashMap<String, String> flightdetailsMap = new HashMap<String, String>();
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
	    wait.until(ExpectedConditions.visibilityOf(driver.findElement(itineryblock)));
	    
		String fname = driver.findElement(By.xpath(tablexpath+flightxpath)).getText();
		String tvalue[] = fname.replace("Economy","").split("\n");
    	
    	fname= tvalue[0] +" " + tvalue[1].trim();
		
		String duration = driver.findElement(By.xpath(tablexpath+JournyDuration)).getText();
		String time1 = driver.findElement(By.xpath(tablexpath+time1xpath)).getText();
		String time2 = driver.findElement(By.xpath(tablexpath+time2xpath)).getText();
		
		
		flightdetailsMap.put("fname",fname);
		flightdetailsMap.put("duration",duration);
		flightdetailsMap.put("time1", time1);
		flightdetailsMap.put("time2", time2);
		
		return flightdetailsMap;
	}
	
	public void getJounreyItenaryDetails(){
		
		onwardJourney = getFlightDetails(itinBlock1xpath);
		returnJourney = getFlightDetails(itinBlock2xpath);
	}
	
}
