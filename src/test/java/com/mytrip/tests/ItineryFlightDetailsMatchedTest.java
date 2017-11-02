package com.mytrip.tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.mytrip.base.TestBaseSetup;
import com.mytrip.pages.GetFlightResultPage;
import com.mytrip.pages.ItineraryPage;
import com.mytrip.pages.SearchFlightsPage;


public class ItineryFlightDetailsMatchedTest extends TestBaseSetup{

	private WebDriver driver;
	
	//Search Flight page xpaths
	private String fromCitySelect = "//*[@id='ui-id-1']";
	private String  toCitySelect = "//*[@id='ui-id-2']";
	private String OnwardJourneyDate ;
	private String returnJourneyDate;
	private String fromCity, toCity, fromCityMatchedValue, toCityMatchedValue;
	
	private SearchFlightsPage searchFlightPage;
	private GetFlightResultPage getflightPage;
	private ItineraryPage itineraryPage;
	
	@BeforeClass
	public void setUp() {
		driver=getDriver();
		loadTestData();
	}
	
	
	public void loadTestData(){
		FileInputStream fs = null;
		try {
			fs = new FileInputStream(System.getProperty("user.dir")+"\\config\\testdata.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}  
	    Properties prop = new Properties();

		try {
			prop.load(fs);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	    OnwardJourneyDate = prop.getProperty("FromDate");
		returnJourneyDate = prop.getProperty("ReturnDate");
		fromCity = prop.getProperty("FromCity");
		toCity = prop.getProperty("ToCity");
		fromCityMatchedValue = prop.getProperty("FromCityMatchedValue");
		toCityMatchedValue = prop.getProperty("ToCityMatchedValue");
	}
	
	@Test(priority=1)
	public void verifyHomePage() {
		System.out.println("Home page test...");
		 searchFlightPage = new SearchFlightsPage(driver);
	     Assert.assertTrue(searchFlightPage.verifyHomePageTitle(), "Home page title doesn't match");
	}

	/* if we want we can write a test method(s) for every 
	 * webelement functionality to validate...*/
	@Test(priority=2)
	public void  VerifyRoundTripRadioSelected() {
		System.out.println("Radio Button selection functionality test...");
		searchFlightPage = new SearchFlightsPage(driver);
		boolean isRadioSelected = searchFlightPage.selectTrip();
		Assert.assertTrue(isRadioSelected, "Round Trip radio button was not selected");
	     
	 }
	
	@Test(priority=3)
	public void VerifyFlightJourneyDetailsTest(){
		System.out.println("Search Flight functionality test...");
		
		searchFlightPage = new SearchFlightsPage(driver);
		getflightPage  = new GetFlightResultPage(driver);
		itineraryPage = new ItineraryPage(driver);
		
		searchFlightPage.selectTrip();
		searchFlightPage.enterFromCity(fromCity);
		searchFlightPage.selectCity(fromCitySelect, fromCityMatchedValue);
		searchFlightPage.enterToCity(toCity);		
		searchFlightPage.selectCity(toCitySelect, toCityMatchedValue);
		searchFlightPage.clickFromDateCalIcon();		
		searchFlightPage.selectJourneyDate(OnwardJourneyDate);
		searchFlightPage.clickReturnDateCalIcon();
		searchFlightPage.selectJourneyDate(returnJourneyDate);
		searchFlightPage.selectAdultPassengers();
		searchFlightPage.selectchildPassengers();
		searchFlightPage.clickSearchFlightsBtn();
		getflightPage.SelectSecondhighestTicket();		
		getflightPage.clickonBookTicket();
		itineraryPage.getJounreyItenaryDetails();
		
		System.out.println("Matching flight details ");
		
		Assert.assertEquals(getflightPage.onwardJourney, itineraryPage.onwardJourney);
		Assert.assertEquals(getflightPage.returnJourney, itineraryPage.returnJourney);
		
		
	}
	
	
	
	
}
