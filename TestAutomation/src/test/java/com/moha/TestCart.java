package com.moha;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestCart {

	private WebDriver webdriver;
	private Duration timeout;
	private WebDriverWait wait; 
	@BeforeClass
	@Parameters({"driverLink"})
	public void createWebDriver(String driverLink) {
		System.setProperty("webdriver.chrome.driver", driverLink);
		webdriver = new ChromeDriver();
		timeout = Duration.ofSeconds(5);
		 wait = new WebDriverWait(webdriver, timeout );
	}

	@Test
	public void testCart() {
		String actualUrl = "http://automationpractice.com/";
		webdriver.get(actualUrl);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Sign in")));
		webdriver.findElement(By.linkText("Sign in")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
		WebElement email = webdriver.findElement(By.id("email"));
		email.click();
		email.sendKeys("hasnain2808@gmail.com");
		WebElement passwd = webdriver.findElement(By.id("passwd"));
		passwd.click();
		passwd.sendKeys("moha123");
		webdriver.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div/div/div[2]/form/div/p[2]/button")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div[1]/header/div[3]/div/div/div[6]/ul/li[2]/a")));
		webdriver.findElement(By.xpath("/html/body/div/div[1]/header/div[3]/div/div/div[6]/ul/li[2]/a")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div[2]/div/div[3]/div[2]/ul/li[2]/div/div[2]/h5/a")));
		String listingText = webdriver
				.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div[2]/ul/li[2]/div/div[2]/h5/a")).getText();
		WebElement target = //WebDriverWait().
				webdriver.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div[2]/ul/li[2]/div/div[1]/div/a[1]/img"));
		Actions action = new Actions(webdriver);
		action.moveToElement(target).moveToElement(webdriver.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div[2]/ul/li[2]/div/div[2]/div[2]/a[1]"))).pause(2000).click().build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div[1]/header/div[3]/div/div/div[4]/div[1]/div[1]/h2")));		
		assertTrue(webdriver.findElement(By.xpath("/html/body/div/div[1]/header/div[3]/div/div/div[4]/div[1]/div[1]/h2")).getText()
				.equals("Product successfully added to your shopping cart"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div[1]/header/div[3]/div/div/div[4]/div[1]/div[1]/span")));
		webdriver.findElement(By.xpath("/html/body/div/div[1]/header/div[3]/div/div/div[4]/div[1]/div[1]/span"))
				.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div[1]/header/div[3]/div/div/div[3]/div/a")));
		webdriver.findElement(By.xpath("/html/body/div/div[1]/header/div[3]/div/div/div[3]/div/a")).click();
		String cartText = webdriver
				.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div/div[2]/table/tbody/tr/td[2]/p/a"))
				.getText();
		assertEquals(cartText, listingText);
		System.out.println("Cart test successfull");
	}

	@AfterClass
	public void closeWebDrier() {
		webdriver.close();
		webdriver.quit();
	}
}
