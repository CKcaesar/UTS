package cucumber.features;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class US01StepDefinitions {
	
	WebDriver driver=null;
	
	@Given("^initialize a system, goto register page.$")
	public void initialize_a_system_goto_register_page() throws Throwable {
		driver=new FirefoxDriver();
	    driver.navigate().to("http://localhost:8080/WebApp4");
	    driver.findElement(By.id("loginForm:confirmRegister")).click();
	}

	@When("^create the student account with firstname=\"([^\"]*)\", lastname=\"([^\"]*)\", birthDate=\"([^\"]*)\", school=\"([^\"]*)\"$")
	public void create_the_student_account_with_firstname_lastname_birthDate_school(String arg1, String arg2, String arg3, String arg4) throws Throwable {
		driver.findElement(By.id("loginForm:firstname")).sendKeys(arg1);
		driver.findElement(By.id("loginForm:lastname")).sendKeys(arg2);
		driver.findElement(By.id("loginForm:birthdate")).sendKeys(arg3);
		driver.findElement(By.id("loginForm:school")).sendKeys(arg4);
		driver.findElement(By.id("loginForm:confirmRegister")).click();
	}

	@Then("^return message username, password$")
	public void return_message_username_password() throws Throwable {
		String actual=driver.findElement(By.id("loginForm:feedbackMessagePane")).getAttribute("class");
		Assert.assertEquals("feedbackMessagePane", actual);
	}
	
	@Then("^return failed to create account$")
	public void return_failed_to_create_account() throws Throwable {
		String actual=driver.findElement(By.id("loginForm:errorMessagePane")).getAttribute("class");
		Assert.assertEquals("errorMessagePane", actual);
	}
}
