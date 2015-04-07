package cucumber.features;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDefinitions {
	
	WebDriver driver=null;
	
	@Given("^I am on my zoo website$")
	public void I_am_on_my_zoo_website() throws Throwable {
	    driver=new FirefoxDriver();
		//driver=new ChromeDriver();
	    driver.navigate().to("http://localhost:8080/WebApp4");
	}

	@When("^populate the contact form$")
	public void populate_the_contact_form() throws Throwable {
	    driver.findElement(By.id("loginForm:stuNo")).sendKeys("001");
	    driver.findElement(By.id("loginForm:password")).sendKeys("001");
	    //driver.findElement(By.id("confirmPassword")).click();
	}
	
	@When("^I cick on the contact links$")
	public void I_cick_on_the_contact_links() throws Throwable {
	    driver.findElement(By.id("loginForm:confirmPassword")).click();
	}

	@Then("^I should be on the contact confirmation$")
	public void I_should_be_on_the_contact_confirmation() throws Throwable {
	    Assert.assertTrue("failed", driver.getTitle().equals("Welcome Dog"));
	}
}
