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
//		a.	Start Chromedriver
		System.setProperty("webdriver.chrome.driver", driverLink);		webdriver = new ChromeDriver();
		timeout = Duration.ofSeconds(5);
		 wait = new WebDriverWait(webdriver, timeout );
	}

	@Test(priority = 2)
	@Parameters({"emailAddrLogin", "password"})
	public void testValidLogin(String emailAddrLogin, String password) {
//		b.	Go to Url http://automationpractice.com/
		String actualUrl = "http://automationpractice.com/";
		webdriver.get(actualUrl);
//		c.	Wait for the sign button to load
//		d.	Click on Sign in Button
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Sign in")));
		webdriver.findElement(By.linkText("Sign in")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
//		f.	Click on email textbox
//		g.	Enter Email Id
		WebElement email = webdriver.findElement(By.id("email"));
		email.click();
		email.sendKeys(emailAddrLogin);
//		h.	Click on password text  box
//		i.	Enter Password
		WebElement passwd = webdriver.findElement(By.id("passwd"));
		passwd.click();
		passwd.sendKeys(password);
//		j.	Click on Sign In
		webdriver.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div/div/div[2]/form/div/p[2]/button")).click();
//		l.	Check the top bar for the registered name to verify successful login
		assertTrue(webdriver.findElement(By.xpath("/html/body/div/div[1]/header/div[2]/div/div/nav/div[1]/a/span"))
				.getText().equals("Mohammad Hasnain Rajan"));
		System.out.println("Valid Login test successfull");
	}

	
	@Test(priority = 3)
	public void testLogOut() {
//		a.	Click on Sign out Button
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Sign out")));
		webdriver.findElement(By.linkText("Sign out")).click();
//		b.	Wait for the page to load
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Sign in")));
//		c.	Check the top bar for “Sign in” to verify successful logout
		String signin = webdriver.findElement(By.linkText("Sign in")).getText();
		assertEquals("Sign in", signin);
		System.out.println("Test Logout successfull");
	}
	
	@Test(priority = 1)
	@Parameters({"emailAddrLogin", "invPassword"})
	public void testInvalidLogin(String emailAddrLogin, String invPassword) {
//		b.	Go to Url http://automationpractice.com/
		String actualUrl = "http://automationpractice.com/";
		webdriver.get(actualUrl);
//		c.	Wait for the sign button to load
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Sign in")));
//		d.	Click on Sign in Button
		webdriver.findElement(By.linkText("Sign in")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
//		f.	Click on email textbox
//		g.	Enter wrong Email Id
		WebElement email = webdriver.findElement(By.id("email"));
		email.click();
		email.sendKeys(emailAddrLogin);
//		h.	Click on password text  box
//		i.	Enter wrong Password
		WebElement passwd = webdriver.findElement(By.id("passwd"));
		passwd.click();
		passwd.sendKeys(invPassword);
//		j.	Click on Sign In
		webdriver.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div/div/div[2]/form/div/p[2]/button")).click();
//		k.	Wait for the Page to load
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div[2]/div/div[3]/div/div[1]/ol/li")));
//		l.	Check the error message that authentication failed		
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
