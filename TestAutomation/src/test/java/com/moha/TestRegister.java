package com.moha;

import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestRegister {

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
	@Parameters({ "emailAddrRegister" })
	public void testRegister(String emailAddrRegister) {
//		b.	Go to Url http://automationpractice.com/
		String actualUrl = "http://automationpractice.com/";
		webdriver.get(actualUrl);
//		c.	Wait for the sign button to load
//		d.	Click on Sign in Button
		webdriver.findElement(By.linkText("Sign in")).click();
//		f.	Click on email textbox
//		g.	Enter new Email Id
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email_create")));
		WebElement email = webdriver.findElement(By.id("email_create"));
		email.click();
		email.sendKeys(emailAddrRegister);
//		h.	Click on Create new Account
		webdriver.findElement(By.id("SubmitCreate")).click();
//		i.	Throw exception if email Id already used
//		j.	Wait for gender radio button to become available
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id_gender1")));

		} catch (TimeoutException e) {
			assertTrue(false, "Please use a different email Address, Account with this email id exists");
		}
//		k.	Select Male
		webdriver.findElement(By.id("id_gender1")).click();
//		l.	Click on input controls to enter First name (By Id), Last Name (By Id), Password (By Id), birthdate (By xpath), Birth Month (By xpath), Birth year(By xpath), address First Name (By Id), Address Last Name (By Id), Address Line 1 (By Id), Address Line 2 (By Id), City (By Id), State (By xpath), postcode (By Id), country (By Id), phone number (By Id), mobile number (By Id), address alias (By Id)
		WebElement firstName = webdriver.findElement(By.id("customer_firstname"));
		firstName.click();
		firstName.sendKeys("Babu");
		WebElement lastName = webdriver.findElement(By.id("customer_lastname"));
		lastName.click();
		lastName.sendKeys("Sharma");
		WebElement passwd = webdriver.findElement(By.id("passwd"));
		passwd.click();
		passwd.sendKeys("babu123");
		WebElement days = webdriver.findElement(By.id("days"));
		days.click();
		webdriver.findElement(By.xpath("//*[@id=\"days\"]/option[2]")).click();
		WebElement months = webdriver.findElement(By.id("months"));
		months.click();
		webdriver.findElement(By.xpath("//*[@id=\"months\"]/option[2]")).click();
		WebElement years = webdriver.findElement(By.id("years"));
		years.click();
		webdriver.findElement(By.xpath("//*[@id=\"years\"]/option[2]")).click();
		WebElement afirstName = webdriver.findElement(By.id("firstname"));
		afirstName.click();
		afirstName.sendKeys("Babu");
		WebElement alastName = webdriver.findElement(By.id("lastname"));
		alastName.click();
		alastName.sendKeys("Sharma");
		WebElement addr1 = webdriver.findElement(By.id("address1"));
		addr1.click();
		addr1.sendKeys("101, rahul complex");
		WebElement address2 = webdriver.findElement(By.id("address2"));
		address2.click();
		address2.sendKeys("raje Marg");
		WebElement city = webdriver.findElement(By.id("city"));
		city.click();
		city.sendKeys("Mumbai");
		WebElement id_state = webdriver.findElement(By.id("id_state"));
		id_state.click();
		webdriver.findElement(By.xpath("//*[@id=\"id_state\"]/option[12]")).click();
		WebElement postcode = webdriver.findElement(By.id("postcode"));
		postcode.click();
		postcode.sendKeys("00000");
		WebElement id_country = webdriver.findElement(By.id("id_country"));
		id_country.click();
		webdriver.findElement(By.xpath("//*[@id=\"id_country\"]/option[2]")).click();
		WebElement phone = webdriver.findElement(By.id("phone"));
		phone.click();
		phone.sendKeys("9867519861");
		WebElement phone_mobile = webdriver.findElement(By.id("phone_mobile"));
		phone_mobile.click();
		phone_mobile.sendKeys("9867519261");
		WebElement alias = webdriver.findElement(By.id("alias"));
		alias.click();
		alias.sendKeys("address alias");
//		m.	Click on the register button
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("submitAccount")));
		webdriver.findElement(By.id("submitAccount")).click();
//		n.	Wait for the page to load
//		o.	Verify name displayed in top bar to verify successful register
		assertTrue(webdriver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a/span")).getText()
				.equals("Babu Sharma"));
		System.out.println("Register test Successfull");
	}

	@AfterClass
	public void closeWebDrier() {
		webdriver.close();
		webdriver.quit();
	}
}
