package com.orangeHRM.practise;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginWithValidDataTest {

	@Test
	public void loginWithValidData() throws InterruptedException{
		WebDriver driver=new ChromeDriver();
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		
		String username="Admin";
		String password="admin123";
		
		try {
//		1.open the browser and get the url
			driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
			driver.manage().window().maximize();
			
//			2. check whether the right page is shown up
			Assert.assertTrue(driver.getTitle().contains("OrangeHRM"),"Login page is not displayed");
			
//			3.Login using the correct user name and password
//			driver.findElement(By.xpath("//input[@name='username']")).sendKeys(username);
//			driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
			wait.until(ExpectedConditions.visibilityOfElementLocated(
				    By.xpath("//input[@name='username']"))).sendKeys(username);
			wait.until(ExpectedConditions.visibilityOfElementLocated(
				    By.xpath("//input[@name='password']"))).sendKeys(password);
			
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']"))).click();
			
//			4.switch to the alert
//			Alert alert=driver.switchTo().alert();

			
			Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[text()='Dashboard']"))).isDisplayed(),"Dashboard is not visible");
			
			
		}finally {
			driver.quit();
		}
	}
}
