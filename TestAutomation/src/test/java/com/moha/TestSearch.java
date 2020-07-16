package com.moha;

import static org.testng.Assert.assertEquals;

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

public class TestSearch {

	private WebDriver webdriver;
	private Duration timeout;
	private WebDriverWait wait;

	@BeforeClass
	@Parameters({"driverLink"})
	public void createWebDriver(String driverLink) {
		System.setProperty("webdriver.chrome.driver", driverLink);
//		a.	Start Chromedriver
		webdriver = new ChromeDriver();
		timeout = Duration.ofSeconds(5);
		wait = new WebDriverWait(webdriver, timeout);
	}

	@Test
	@Parameters({ "searchKey" })
	public void testSearch(String searchKey) {
//		b.	Go to Url http://automationpractice.com/
		String actualUrl = "http://automationpractice.com/";
		webdriver.get(actualUrl);
//		d.	Click on search textbox
//		e.	Enter search query
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search_query_top")));
		String searchQuery = searchKey;
		WebElement search_query_top = webdriver.findElement(By.id("search_query_top"));
		search_query_top.click();
		search_query_top.sendKeys(searchQuery);
//		f.	Press search button
		webdriver.findElement(By.xpath("/html/body/div/div[1]/header/div[3]/div/div/div[2]/form/button")).click();
//		g.	Wait for the page to load
//		h.	Assert name of first Product to be same as search query to verify correct search
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div[2]/div/div[3]/div[2]/h1/span[1]")));
		String fromSearch = webdriver.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div[2]/h1/span[1]"))
				.getText();
		assertEquals("\"" + searchQuery.toUpperCase() + "\"", fromSearch);
		String fromResults = webdriver
				.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div[2]/ul/li[1]/div/div[2]/h5/a")).getText();
		assertEquals(fromResults, searchQuery);
		System.out.println("Search test successfull");
	}

	@AfterClass
	public void closeWebDrier() {
		webdriver.close();
		webdriver.quit();
	}
}
