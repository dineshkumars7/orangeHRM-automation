
package com.orangeHRM.practise;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest {

    WebDriver driver;
    WebDriverWait wait;

    String url = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";

    @BeforeMethod
    public void setup() {

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get(url);
    }

    // Test Case 1: Valid Login

    @Test
    public void validLoginTest() {

        driver.findElement(By.name("username")).sendKeys("Admin");

        driver.findElement(By.name("password")).sendKeys("admin123");

        driver.findElement(By.cssSelector("button[type='submit']")).click();

        boolean dashboardDisplayed = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h6[text()='Dashboard']")
            )
        ).isDisplayed();

        Assert.assertTrue(
            dashboardDisplayed,
            "Dashboard is not displayed after valid login"
        );
    }

    // Test Case 2: Invalid Username

    @Test
    public void invalidUsernameTest() {

        driver.findElement(By.name("username")).sendKeys("InvalidUser");

        driver.findElement(By.name("password")).sendKeys("admin123");

        driver.findElement(By.cssSelector("button[type='submit']")).click();

        boolean errorDisplayed = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".oxd-alert-content-text")
            )
        ).isDisplayed();

        Assert.assertTrue(
            errorDisplayed,
            "Invalid credentials message is not displayed"
        );
    }

    // Test Case 3: Logout

    @Test
    public void logoutTest() {

        driver.findElement(By.name("username")).sendKeys("Admin");

        driver.findElement(By.name("password")).sendKeys("admin123");

        driver.findElement(By.cssSelector("button[type='submit']")).click();

        wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h6[text()='Dashboard']")
            )
        );

        driver.findElement(
            By.cssSelector(".oxd-userdropdown-tab")
        ).click();

        wait.until(
            ExpectedConditions.elementToBeClickable(
                By.xpath("//a[text()='Logout']")
            )
        ).click();

        boolean loginPageDisplayed = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.name("username")
            )
        ).isDisplayed();

        Assert.assertTrue(
            loginPageDisplayed,
            "User was not redirected to the login page"
        );
    }

    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}
