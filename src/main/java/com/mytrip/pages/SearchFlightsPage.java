package com.mytrip.pages;

import java.text.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mytrip.util.UtilityClass;


public class SearchFlightsPage {

	private WebDriver driver;

	private By tripSelection = By.id("RoundTrip");
	private By fromCityName = By.id("FromTag");
	private By toCityName = By.id("ToTag");
	private String datepicker1 = ".//*[@id='ui-datepicker-div']/div";
	private By childPaxSelect = By.id("Childrens");
	private By adultsPaxSelect = By.id("Adults");
	private By searchButton = By.id("SearchBtn");
	private By nextYorMarrow = By.xpath("//*[@id='ui-datepicker-div']/div[2]/div/a");
	private By OnJrnydateIcon = By.xpath("//*[@id='ORtrip']/section[2]/div[1]/dl/dd/div/a/i");
	private By returnJrnydateIcon = By.xpath("//*[@id='ReturnDateContainer']/dd/div/a/i");
	
    private UtilityClass util;    
	
	public SearchFlightsPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public String getPageTitle(){
		String title = driver.getTitle();
		return title;
	}
	
	public boolean verifyHomePageTitle() {
		String expectedPageTitle="Cleartrip";
		return getPageTitle().contains(expectedPageTitle);
	}

	public boolean selectTrip() {
		System.out.println("Selecting Trip...");
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(tripSelection));
		WebElement tripRadio = driver.findElement(tripSelection);		
		if (tripRadio.isDisplayed())
			tripRadio.click();
		return tripRadio.isSelected();
	}
	
	

	public void enterFromCity(String frmcityname) {
		System.out.println("Enter from Travel city...");
//		WebDriverWait wait = new WebDriverWait(driver, 10);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(fromCityName));
		WebElement frmcityele = driver.findElement(fromCityName);
		if (frmcityele.isDisplayed())
			frmcityele.clear();
			frmcityele.sendKeys(frmcityname);
	}

	public void enterToCity(String tocityname) {
		System.out.println("Enter to Travel city...");
		WebElement tocityele = driver.findElement(toCityName);
		if (tocityele.isDisplayed())
			tocityele.clear();
			tocityele.sendKeys(tocityname);
	}

	public void selectCity(String cityxpath, String matchValue) {
	    
		System.out.println("Selecting Travel city...");
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(cityxpath)));

		final int count = driver.findElements(By.xpath(cityxpath+"/li")).size();

		String part1 = cityxpath + "/li[";
		String part2 = "]/a";
		for (int i = 1; i <= count; i++) {
			String fromcity = driver.findElement(By.xpath(part1 + i + part2))
					.getText();
			if (fromcity.contains(matchValue)) {
				driver.findElement(By.xpath(part1 + i + part2)).click();
				break;
			}
		}		

	}
	
	public void clickFromDateCalIcon(){
		System.out.println("click on form date icon...");
		WebElement OnJrnyCalele = driver.findElement(OnJrnydateIcon);
		if (OnJrnyCalele.isDisplayed())
			OnJrnyCalele.click();
	}
    public void clickReturnDateCalIcon(){
    	System.out.println("click on return date city...");
    	WebElement returnJrnyCalele = driver.findElement(returnJrnydateIcon);
		if (returnJrnyCalele.isDisplayed())
			returnJrnyCalele.click();
    }
	public void selectJourneyDate(String date) {
		
		System.out.println("Selecting Journet date .....");
		util = new UtilityClass(driver);
		String DesiredYearToSel;
		String DesiredMonthToSel;
		String DesiredDaytoSel;
		String datmonyearArray[] = new String[3];
		String getTitle = "";


		try {
			datmonyearArray = util.getDayMonthYear(date);
		} catch (ParseException e) {
			
			e.printStackTrace();
		}

		DesiredYearToSel = datmonyearArray[0];
		DesiredMonthToSel = datmonyearArray[1];
		DesiredDaytoSel = datmonyearArray[2];

		String DesiredMonYear = DesiredMonthToSel + " " + DesiredYearToSel;
		boolean yearmonthMatched = false;

		while (!yearmonthMatched) {
			int counter = 0;
			for (int i = 1; i <= 2; i++) {
				getTitle = driver.findElement(
						By.xpath(datepicker1 + "[" + i + "]/div/div"))
						.getText();
				if (DesiredMonYear.equals(getTitle)) {
					yearmonthMatched = true;
					// select day
					String part1 = "//*[@id='ui-datepicker-div']/div[";
					String part2 = "]/table/tbody/tr[";
					String part3 = "]/td[";
					String part4 = "]/a";

					String actDay = "";

					boolean flag = false;
					for (int j = 1; j <= 6; j++) {
						if (flag)
							break;
						for (int k = 1; k <= 7; k++) {
							
							if (util.isElementPresent(part1 + i + part2 + j
									+ part3 + k + part4)) {
								actDay = driver.findElement(
										By.xpath(part1 + i + part2 + j + part3
												+ k + part4)).getText();//
								if (actDay.equals(DesiredDaytoSel)) {
									driver.findElement(
											By.xpath(part1 + i + part2 + j
													+ part3 + k + part4))
											.click();
									flag = true;
									break;
								}
							}
						}

					}
					break;
				}
				counter = counter + 1;
			}
			if (counter == 2 && !yearmonthMatched)
				driver.findElement(nextYorMarrow).click();

		}
	}
	
	public void selectAdultPassengers(){
		System.out.println("Selecting Adults Pax...");
		WebElement adldroplist = driver.findElement(adultsPaxSelect);
		Select selct = new Select(adldroplist);		
		selct.selectByVisibleText("2"); 
		
	}
	
	public void selectchildPassengers(){
		System.out.println("Selecting Child Pax...");
		WebElement chldroplist = driver.findElement(childPaxSelect);
		Select selct = new Select(chldroplist);		
		selct.selectByVisibleText("2"); 
    	
	}
	
	public GetFlightResultPage clickSearchFlightsBtn() {
		System.out.println("clicking on Search Flights button..");
		WebElement searchFlightsBtnEle=driver.findElement(searchButton);
		if(searchFlightsBtnEle.isDisplayed()||searchFlightsBtnEle.isEnabled())
			searchFlightsBtnEle.click();
		else System.out.println("Element not found");
		return new GetFlightResultPage(driver);
	}
	
}
