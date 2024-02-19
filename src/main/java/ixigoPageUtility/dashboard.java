package ixigoPageUtility;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;

import ixigoWebutility.Allutilities;
import ixigoWebutility.BaseClass;
import ixigoWebutility.Listeners;

public class dashboard {
	WebDriver driver;
	Allutilities obj1;
	@FindBy(id = "passenger-list")
	WebElement passanger;
	@FindBy (xpath = "(//div[@class='input-label'])[1]")
	WebElement clicker;
	@FindBy (xpath="//button[@class='c-btn u-link  enabled']/div")
	WebElement searchbtn;
	@FindBy (xpath = "//div[@class='error-msg']")
	WebElement fromDropdown;
	By cityInput = By.xpath("//input[@class='c-input u-v-align-middle' and @placeholder = 'Enter city or airport']");
	By cities = By.xpath("//div[@class='orgn u-ib u-v-align-bottom u-text-left']/div/div[3]/div/div");
	By Tocity = By.xpath("//div[@class='dstn u-ib u-v-align-bottom u-text-left']/div/div[3]/div/div");
	By allInputs = By.xpath("//input[@class='c-input u-v-align-middle']");
	
	
	
	public dashboard() throws IOException, InterruptedException {
		BaseClass obj1 = new BaseClass();
		this.driver = obj1.Launch();
		Thread.sleep(3000);
		this.obj1 = new Allutilities(driver);
		PageFactory.initElements(driver, this);
	}
	public String SelectedPassanger(){
		String people = passanger.getAttribute("value");
		Listeners.test.log(Status.INFO, people);
		return people;
	}
	public String FirstTest(){
		return SelectedPassanger();
	}
	public void deselect() {
		List<WebElement> crosses = driver.findElements(By.xpath("//div[@class='clear-input ixi-icon-cross']"));
		for(int i=0; i<crosses.size(); i++) {
			crosses.get(i).click();
		}
	}
	public String searchClick() {
		deselect();
		Listeners.test.log(Status.INFO, "Deselected all cities");
		searchbtn.click();
		Listeners.test.log(Status.INFO, "Clicked on search button");
		obj1.ElementVisible(fromDropdown);
		String error = fromDropdown.getText();
		Listeners.test.log(Status.INFO, error);
		return error;
		
	}
	public void EnterCity(String n1, String n2) throws InterruptedException {
		driver.navigate().refresh();
		deselect();
		List<WebElement> names = driver.findElements(cityInput);
		names.get(0).sendKeys(n1);
		Thread.sleep(1000);
		obj1.Elementlocated(cities);
		List<WebElement> allCities = driver.findElements(cities);
		allCities.get(0).click();
			Listeners.test.log(Status.INFO, "Selected"+allCities.get(0).getText());
		names.get(1).sendKeys(n2);
		obj1.Elementlocated(Tocity);
		Thread.sleep(1000);
		List<WebElement> TOallCities = driver.findElements(Tocity);
		TOallCities.get(0).click();
		Listeners.test.log(Status.INFO, "Selected"+TOallCities.get(0).getText());
		
	}
	public String[] verifycity() throws InterruptedException{
		EnterCity("Luck", "Del");
		List <WebElement> inputs = driver.findElements(allInputs);
		String input1 = inputs.get(0).getAttribute("value");
		Listeners.test.log(Status.INFO, input1);
		String input2 = inputs.get(1).getAttribute("value");
		Listeners.test.log(Status.INFO, input2);
		String[] arr = {input1, input2}; 
		return arr;
	}
	public String VerifyDate() {
		List <WebElement> inputs = driver.findElements(allInputs);
		String input3 = inputs.get(2).getAttribute("value");
		Listeners.test.log(Status.INFO, "Selected Date "+ input3);
		return input3;
	}
}
