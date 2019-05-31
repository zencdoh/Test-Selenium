package stepdefination;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TestHealthDirect {

	static WebDriver driver;

	
	@Given("^I have opened the firefox browser and enter the URL$")
	public void i_have_opened_the_Mozila_browser_and_enter_URL() throws Throwable {

		System.setProperty("webdriver.chrome.driver", "/home/ubuntu/chromedriver");
		driver=new ChromeDriver();
		

		driver.get("https://www.healthdirect.gov.au/");
		System.out.println("URL is opened");
		driver.manage().window().maximize();
		
	}

	@Given("^page is loaded properly$")
	public void page_is_loaded_properly() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		
			try{
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.titleContains("Trusted Health Advice | healthdirect"));
			System.out.println("Title is matched: Page is loaded");
			}
			catch(Exception e){
				System.out.println("Title is not matched");
				Assert.fail();
			}
}

	@When("^page is loaded properly, I search for search textbox in the homepage$")
	public void page_is_loaded_properly_I_search_for_search_textbox_in_the_homepage() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		WebElement ele = driver.findElement(By.xpath("(//input[@id='header-search'])[2]"));
		WebDriverWait wait = new WebDriverWait(driver, 20);
		try{
		wait.until(ExpectedConditions.visibilityOf(ele));
		System.out.println("Element is present");
		}
		catch(Exception e){
			System.out.println("element is not present");
			Assert.fail();
		}
			
	}
	@When("^I provide proper \"(.*?)\" in the header searchbox and click Enter$")
	public void i_provide_proper_in_the_header_searchbox_and_click_Enter(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		 // Write code here that turns the phrase above into concrete actions
		WebElement ele = driver.findElement(By.xpath("(//input[@id='header-search'])[2]"));
		ele.clear();
		ele.sendKeys(arg1);
		ele.sendKeys(Keys.ENTER);
	}


	@Then("^I verify search results are displayed for the \"(.*?)\" and get the information$")
	public void i_verify_search_results_displayed_for_the(String arg1) throws Throwable {
	 
		
		int count = driver.findElements(By.xpath("//a[@class='main_content-search-tile veyron-local-result-item']")).size();
		System.out.println("Number of matching results"+count);
		
	 for(int i=1;i<=count;i++){
		 driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		 String text = driver.findElement(By.xpath("(//a[contains(.,'"+arg1+"')]//h4)["+i+"]")).getText();
		 System.out.println("Search result is for "+arg1+""+text);
		
	 }
	 
	}


	@When("^I close the browser$")
	public void i_close_the_browser() throws Throwable {
    driver.quit();
	}
}
