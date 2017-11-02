package com.mytrip.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UtilityClass {

	private WebDriver driver;

	public UtilityClass(WebDriver driver) {
		this.driver = driver;

	}

	public boolean isElementPresent(String elementXpath) {

		int count = driver.findElements(By.xpath(elementXpath)).size();
		if (count == 0)
			return false;
		else
			return true;
	}
	
	public String[] getDayMonthYear(String givenDate) throws ParseException{
		
	
		String months[]= {"January", "February", "March", "April", "May", "June", "July", "August", "September",
				         "October", "November", "December"};		
		
		SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		
		String[] dateArray = new String[3];
		Date mydate = df.parse(givenDate);
		
		System.out.println("Mydate "+ mydate);
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(mydate);

		int day = cal.get(Calendar.DAY_OF_MONTH);
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
				
		dateArray[0] = String.valueOf(year);
		dateArray[1] = months[month];
		dateArray[2] = String.valueOf(day);

		return dateArray;
		
	}

}
