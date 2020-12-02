package dice_update_profile;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class DiceUpdateProfile {
	
	public static int profileQty = 2;
	public static String email = "";
	public static String password = "";
	
	public static void main(String[] args) {
		// init presets
        Map<String, String> map1 = new HashMap<String, String>();
        Map<String, String> map2 = new HashMap<String, String>();
		map1.put("profile", "QA Tester");
		map1.put("href", "https://www.dice.com/dashboard/profiles/f512a3758f52261a639b1f19e6274801");
		map2.put("profile", "QA Enineer");
		map2.put("href", "https://www.dice.com/dashboard/profiles/ec50913d5ff199b148082104be5e7513");
		List<Map<String, String>> presets = new ArrayList<>();
        presets.add(0, map1);
        presets.add(1, map2);
        
        // main loop
        String result;
        for (int i = 0; i < profileQty; i++) {
        	Map<String, String> data = presets.get(i);
        	script(data);
        }
	}
		
	public static void script(Map<String, String> data) {
		
		// init driver
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver_win32\\chromedriver.exe");
		ChromeOptions options= new ChromeOptions();
		options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors");
		WebDriver driver = new ChromeDriver(options);
		driver.get(data.get("href"));
		
		// wait for JS and JQuery
		waitForJSandJQueryToLoad(driver);
		
		// set Email
		WebElement emailField = driver.findElement(By.id("email"));
		emailField.clear();
		emailField.sendKeys(email);
		
		// set Password
		WebElement passwordField = driver.findElement(By.id("password"));
		passwordField.clear();
		passwordField.sendKeys(password);
		
		// click Log In button
		WebElement LogInButton = driver.findElement(By.xpath("//button[@type='submit']"));
		LogInButton.click();
		
		// wait for JS and JQuery
		waitForJSandJQueryToLoad(driver);
		
		// click Edit Profile
		try {
			Thread.sleep(2000);
			}
		catch(InterruptedException ie) {
			System.out.println(ie);
			}
		WebElement editProfile = driver.findElement(By.id("editProfile"));
		editProfile.click();
		
		// click Save Profile
		WebElement saveProfile = driver.findElement(By.id("profileForm"));
		saveProfile.click();
		
		// close WebDriver
		driver.close();
	}
	
	public static boolean waitForJSandJQueryToLoad(WebDriver driver) {

	    WebDriverWait wait = new WebDriverWait(driver, 30);

	    // wait for jQuery to load
	    ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
	      @Override
	      public Boolean apply(WebDriver driver) {
	        try {
	          return ((Long)((JavascriptExecutor)driver).executeScript("return jQuery.active") == 0);
	        }
	        catch (Exception e) {
	          // no jQuery present
	          return true;
	        }
	      }
	    };

	    // wait for JavaScript to load
	    ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
	      @Override
	      public Boolean apply(WebDriver driver) {
	        return ((JavascriptExecutor)driver).executeScript("return document.readyState")
	        .toString().equals("complete");
	      }
	    };

	  return wait.until(jQueryLoad) && wait.until(jsLoad);
	}
}