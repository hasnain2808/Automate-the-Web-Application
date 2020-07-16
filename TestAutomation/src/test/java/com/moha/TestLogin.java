package com.moha;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestLogin {

	private WebDriver webdriver;
	private Duration timeout;
	private WebDriverWait wait; 
	@BeforeClass
	@Parameters({"driverLink"})
	public void createWebDriver(String driverLink) {
		System.setProperty("webdriver.chrome.driver", driverLink);		webdriver = new ChromeDriver();
		timeout = Duration.ofSeconds(5);
		 wait = new WebDriverWait(webdriver, timeout );

	}

	@Test(priority = 2)
	@Parameters({"emailAddrLogin", "password"})
	public void testValidLogin(String emailAddrLogin, String password) {
		String actualUrl = "http://automationpractice.com/";
		webdriver.get(actualUrl);
		//		WebDriverWait.
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Sign in")));
		webdriver.findElement(By.linkText("Sign in")).click();
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));

		
		WebElement email = webdriver.findElement(By.id("email"));
		email.click();
		email.sendKeys(emailAddrLogin);
		WebElement passwd = webdriver.findElement(By.id("passwd"));
		passwd.click();
		passwd.sendKeys(password);
		webdriver.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div/div/div[2]/form/div/p[2]/button")).click();
		assertTrue(webdriver.findElement(By.xpath("/html/body/div/div[1]/header/div[2]/div/div/nav/div[1]/a/span"))
				.getText().equals("Mohammad Hasnain Rajan"));
		System.out.println("Valid Login test successfull");

	}

	
	@Test(priority = 3)
	public void testLogOut() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Sign out")));
		webdriver.findElement(By.linkText("Sign out")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Sign in")));
		String signin = webdriver.findElement(By.linkText("Sign in")).getText();
		assertEquals("Sign in", signin);
		System.out.println("Test Logout successfull");
	}
	
	@Test(priority = 1)
	@Parameters({"emailAddrLogin", "invPassword"})

	public void testInvalidLogin(String emailAddrLogin, String invPassword) {

		String actualUrl = "http://automationpractice.com/";
		webdriver.get(actualUrl);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Sign in")));

		webdriver.findElement(By.linkText("Sign in")).click();
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));

		WebElement email = webdriver.findElement(By.id("email"));
		email.click();
		email.sendKeys(emailAddrLogin);
		WebElement passwd = webdriver.findElement(By.id("passwd"));
		passwd.click();
		passwd.sendKeys(invPassword);
		webdriver.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div/div/div[2]/form/div/p[2]/button")).click();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div[2]/div/div[3]/div/div[1]/ol/li")));

		
		
		assertTrue(webdriver.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div/div[1]/ol/li")).getText()
				.equals("Authentication failed."));
		System.out.println("Invalid Login test successfull");

	}

	@AfterClass
	public void closeWebDrier() {
		webdriver.close();
		webdriver.quit();
	}
}
