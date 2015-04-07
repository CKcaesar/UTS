package cucumber.features;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class US02StepDefinitions {

	WebDriver driver=null;
	
	@Given("^initialize the system$")
	public void initialize_the_system() throws Throwable {
		driver=new FirefoxDriver();
	    driver.navigate().to("http://localhost:8080/WebApp4");
	}

	@When("^login as a current student stuNo=\"([^\"]*)\", password=\"([^\"]*)\"$")
	public void login_as_a_current_student_stuNo_password(String arg1, String arg2) throws Throwable {
		driver.findElement(By.id("loginForm:stuNo")).sendKeys(arg1);
	    driver.findElement(By.id("loginForm:password")).sendKeys(arg2);
	    driver.findElement(By.id("loginForm:confirmPassword")).click();
	}

	@Then("^the title of web page is \"([^\"]*)\"$")
	public void the_attribute_value_of_stuNo(String arg1) throws Throwable {
		String title=driver.getTitle();
		Assert.assertEquals(arg1,title);
	}
}
