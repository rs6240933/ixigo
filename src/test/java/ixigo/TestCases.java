package ixigo;

import java.io.IOException;
import java.time.LocalDate;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import ixigoPageUtility.dashboard;
import ixigoWebutility.Listeners;

//1) By default 1 passanger, economy is selected by default or not 
//2) If no city is selected in From and two dropdown 
//3)check by selected cities in from and To
//4)verify +1 date is selected from current date or not by default 
//5)verify more than 9 passanngers cannot be added 
//6)Verify the func of search button
//7)Check by selecting Air India flight 

public class TestCases {
	dashboard dash;
	@Test(priority = 1)
	public void Test1() throws IOException, InterruptedException {
		this.dash = new dashboard();
		String passangers = dash.FirstTest();
		Assert.assertTrue(passangers.contains("1 Passenger, Economy"), "Not selected by default");
	}
	@Test(priority = 2)
	public void Test2() {
		String error_text = dash.searchClick();
		Assert.assertTrue(error_text.contains("Please enter departure airport"), "Wrong message is showing");
	}
	
	@Test(priority = 3)
	public void Test3() throws InterruptedException {
		String[] arr1 = dash.verifycity();
		Assert.assertTrue(arr1[0].contains("Lucknow"), "Lucknow is not selected");
		Assert.assertTrue(arr1[1].contains("Delhi"), "Delhi is not selected");
	}
	@Test(priority = 4)
	public void Test4() {
		 LocalDate currentDate = LocalDate.now();
		 LocalDate onedayAfter =  currentDate.plusDays(1);
		 String n = onedayAfter.toString();
		 Listeners.test.log(Status.INFO, "Date Should be "+n);
		String n1 =  n.substring(8);
		 String selected = dash.VerifyDate();
		 Assert.assertTrue(selected.contains(n1), "Wrong date is selected");
	}

}
