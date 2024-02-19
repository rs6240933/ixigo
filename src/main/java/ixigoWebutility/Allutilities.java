package ixigoWebutility;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Allutilities {
	WebDriver driver;
	WebDriverWait wait;

	public Allutilities(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	}
	
	public void ElementVisible(WebElement ele) {
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	public void Elementlocated(By ele) {
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(ele));
	}
}
