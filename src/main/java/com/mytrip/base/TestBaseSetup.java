package com.mytrip.base;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;


	public class TestBaseSetup {

		private WebDriver driver;
		static String driverPath = System.getProperty("user.dir")+"\\Resources\\drivers\\";
        
		public WebDriver getDriver() {
			return driver;
		}

		private void setDriver(String browserType, String appURL) {
			System.out.println("------------IN setting browser");
			switch (browserType) {
			case "chrome":
				driver = initChromeDriver(appURL);
				break;
			case "firefox":
				driver = initFirefoxDriver(appURL);
				break;
			case "ie":
				driver = initIEDriver(appURL);
				break;
			default:
				System.out.println("browser : " + browserType
						+ " is invalid, Launching Firefox as browser of choice..");
				driver = initFirefoxDriver(appURL);
			}
		}

		private static WebDriver initChromeDriver(String appURL) {
			System.out.println("Launching google chrome with new profile..");
			System.setProperty("webdriver.chrome.driver", driverPath+ "chromedriver.exe");
			WebDriver driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.navigate().to(appURL);
			return driver;
		}

		private static WebDriver initFirefoxDriver(String appURL) {
			System.out.println("Launching Firefox browser..");
			System.setProperty("webdriver.gecko.driver", driverPath+ "geckodriver.exe");
			WebDriver driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.get(appURL);
			return driver;
		}
		private static WebDriver initIEDriver(String appURL) {
			System.out.println("Launching IE browser..");
			System.setProperty("webdriver.ie.driver", driverPath+ "IEDriverServer.exe");
			WebDriver driver = new InternetExplorerDriver();
			driver.manage().window().maximize();
			driver.navigate().to(appURL);
			return driver;
		}

		//@Parameters({ "browserType", "appURL" })
		/*@BeforeClass
		public void initializeTestBaseSetup(String browserType, String appURL) {
			
			try {
				setDriver(browserType, appURL);

			} catch (Exception e) {
				System.out.println("Error....." + e.getCause().getLocalizedMessage());
				e.getCause().printStackTrace();
			}
		}*/
		
		@BeforeClass
		public void initializeTestBaseSetup() {
			
			String appURL = System.getProperty("url");
			String browserType = System.getProperty("browsertype");
			
			System.out.println("-------"+appURL);
			System.out.println("------"+browserType);		
			try {
				setDriver(browserType, appURL);

			} catch (Exception e) {
				System.out.println("Error....." + e.getCause().getLocalizedMessage());
				e.getCause().printStackTrace();
			}
		}
		@AfterClass
		public void tearDown() {
			driver.quit();
		}
		
	}
	