package com.mytrip.pages;

import java.util.HashMap;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GetFlightResultPage {

	private WebDriver driver;
	private By btnBookTickets = By.xpath("//div[2]/section[2]/section/div/form/section[2]/div[3]/div[3]/button");

	String fromflightList = ".//*[@id='flightForm']/section[2]/div[4]/div[1]/nav/ul/li";
	
	String FlightName = "/div/label/table/tbody/tr[2]/td[1]/span";
	String flightTime1 = "/div/label/table/tbody/tr[1]/th[3]"; 
	String flightTime2 = "/div/label/table/tbody/tr[1]/th[4]";
	String flightDuration = "/div/label/table/tbody/tr[1]/th[5]";
		
	String returnflightList = ".//*[@id='flightForm']/section[2]/div[4]/div[2]/nav/ul/li";
	
	String radioSecongHighest = "/div/label/table/tbody/tr[1]/th[1]/input";
	String fromFlighbox = "//*[@id='flightForm']/section[2]/div[3]/div[1]/h2";
	
	String SecondHighestflightPrice = "/div/label/table/tbody/tr[1]/th[6]";
	
	public HashMap<String , String> onwardJourney = new HashMap<String,String>();
	public HashMap<String , String> returnJourney = new HashMap<String,String>();
	
	                          
	public GetFlightResultPage(WebDriver driver) {
		this.driver = driver;
	
	}

	public String getPageTitle(){
		String title = driver.getTitle();
		return title;
	}
	
	public boolean verifyHomePageTitle() {
		String expectedPageTitle="Book Cheap Air Tickets";
		return getPageTitle().contains(expectedPageTitle);
	}
	
	public void SelectSecondhighestTicket(){
		
		System.out.println("Getting the second highest Ticket details...");		
		onwardJourney = selectSecondHighestTicketAmount(fromflightList);		
		returnJourney = selectSecondHighestTicketAmount(returnflightList);
		
	}
	
	public  HashMap<String, String> selectSecondHighestTicketAmount(String tablexpath) {
       
		System.out.println("Getting the second highest Ticket details...");
		WebDriverWait wait = new WebDriverWait(driver, 30);
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(fromFlighbox)));
		System.out.println("Waiting for webtable appear...");
		final int count = driver.findElements(By.xpath(tablexpath)).size();
		
		int temp = 0;
		HashMap<String , String> hm = new HashMap<String,String>();
		for(int i=count; i>=0; i--){
			String  value =driver.findElement(By.xpath(tablexpath+"["+i+"]"+SecondHighestflightPrice)).getText();
			value = value.substring(3, value.length());
	    	value = value.replaceAll("[-+.^:,]","");
			Integer intAmt = Integer.parseInt(value);
			if(i == count){
				temp = intAmt;
				continue;
			}
			if(intAmt < temp){
				driver.findElement(By.xpath(tablexpath+"["+i+"]"+radioSecongHighest)).click();
				String fname = driver.findElement(By.xpath(tablexpath+"["+i+"]"+FlightName)).getText();
				String duration = driver.findElement(By.xpath(tablexpath+"["+i+"]"+flightDuration)).getText();
				String time1 = driver.findElement(By.xpath(tablexpath+"["+i+"]"+flightTime1)).getText();
				String time2 = driver.findElement(By.xpath(tablexpath+"["+i+"]"+flightTime2)).getText();
				
				
				hm.put("fname",fname);
				hm.put("duration",duration);
				hm.put("time1", time1);
				hm.put("time2", time2);
				
				System.out.println(fname + " " + duration +" " + time1 + " " + time2);
						
				return hm;
			}
		}
		 
		return hm;
	}

	
	public ItineraryPage clickonBookTicket() {
		
	    //wait.until(ExpectedConditions.elementToBeClickable(btnBookTickets));
	    
		WebElement btnEle = driver.findElement(btnBookTickets);
		if(btnEle.isDisplayed()||btnEle.isEnabled())
			btnEle.click();
		else System.out.println("Element not found");
		return new ItineraryPage(driver);
		
	}
}

