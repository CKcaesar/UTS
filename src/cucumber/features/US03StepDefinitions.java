package cucumber.features;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
//import edu.scs.carleton.comp.ls.view.beans.TermBean;

public class US03StepDefinitions {
	
	WebDriver driver=null;
	
	@Given("^initialize a system, login as admin$")
	public void initializeLoginAsAdmin() throws Throwable {
	    driver=new FirefoxDriver();
	    driver.navigate().to("http://localhost:8080/WebApp4");
	    driver.findElement(By.id("loginForm:stuNo")).sendKeys("admin");
	    driver.findElement(By.id("loginForm:password")).sendKeys("admin");
	    driver.findElement(By.id("loginForm:confirmPassword")).click();
	}

	@Given("^goto \"([^\"]*)\" page$")
	public void gotoPage(String arg1) throws Throwable {
		String fullId="j_idt21:"+arg1;
	    driver.findElement(By.id(fullId)).click();
	}
	
	@Given("^initialize a system, login as admin, create the term with name=\"([^\"]*)\", startDate=\"([^\"]*)\", endDate=\"([^\"]*)\"$")
	public void initialize_a_system_login_as_admin_create_the_term_with_name_startDate_endDate(String arg1, String arg2, String arg3) throws Throwable {
		driver=new FirefoxDriver();
	    driver.navigate().to("http://localhost:8080/WebApp4");
	    driver.findElement(By.id("loginForm:stuNo")).sendKeys("admin");
	    driver.findElement(By.id("loginForm:password")).sendKeys("admin");
	    driver.findElement(By.id("loginForm:confirmPassword")).click();
	    String fullComponentId="j_idt21:maintainTerms";
	    driver.findElement(By.id(fullComponentId)).click();
	    driver.findElement(By.id("maintainTermsForm:inname")).sendKeys(arg1);
		driver.findElement(By.id("maintainTermsForm:start-Date")).sendKeys(arg2);
		driver.findElement(By.id("maintainTermsForm:end-Date")).sendKeys(arg3);
		driver.findElement(By.id("maintainTermsForm:createTerm")).click();
	}

	@When("^create the term with name=\"([^\"]*)\", startDate=\"([^\"]*)\", endDate=\"([^\"]*)\"$")
	public void createTermWithNameStartDateEndDate(String arg1, String arg2, String arg3) throws Throwable {
		driver.findElement(By.id("maintainTermsForm:inname")).sendKeys(arg1);
		driver.findElement(By.id("maintainTermsForm:start-Date")).sendKeys(arg2);
		driver.findElement(By.id("maintainTermsForm:end-Date")).sendKeys(arg3);
		driver.findElement(By.id("maintainTermsForm:createTerm")).click();
	}
	
	@When("^select \"([^\"]*)\"$") 
	public void selectSth(String arg1){
		/*WebElement select = driver.findElement(By.name("maintainCourseForm:j_idt21"));
	    java.util.List<WebElement> options = select.findElements(By.tagName("option"));
	    for(WebElement option : options){
	        if(option.getText().equals(arg1)) {
	            option.click();
	            break;
	        }
	    }*/
		//maintainCourseForm:j_idt21 or maintainCourseForm:j_idt99
	    new Select (driver.findElement(By.name("maintainCoursesForm:j_idt21"))).selectByVisibleText(arg1);
	}
	
	@Given("^show create Term \"([^\"]*)\" success$")
	public void showCreateSuccessAtGiven(String arg1) throws Throwable {
		String msg1=driver.findElement(By.id("maintainTermsForm:successMessagePane")).getText();
		Assert.assertEquals("Create Term ["+arg1+"]: SUCCESS", msg1);
	}

	@Then("^show \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" success$")
	public void showCreateSuccessAtThen(String arg1, String arg2, String arg3) throws Throwable {
		String msg2=driver.findElement(By.id("maintainTermsForm:successMessagePane")).getText();
		Assert.assertTrue("creation failed", msg2.equals(arg1+" "+arg2+" ["+arg3+"]: SUCCESS"));
		//Create Term [summ]: SUCCESS
	}
		
	@Then("^show create \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" fail$")
	public void showCreateOverlappingFail(String arg1, String arg2, String arg3) throws Throwable {
		String msg=driver.findElement(By.id("maintainTermsForm:errorMessagePane")).getText();
		Assert.assertTrue("creation success", msg.equals("Create "+arg1+" ["+arg2+"] "+arg3+": FAILED"));
	}
	
	//methods for US-05
/*	@When("^select \"([^\"]*)\"$") 
	public void selectSth(String arg1){
		//driver=new WebDriver();
		WebElement select = driver.findElement(By.name("maintainCourseForm:j_idt21"));
	    java.util.List<WebElement> options = select.findElements(By.tagName("option"));
	    for(WebElement option : options){
	        if(option.getText().equals(arg1)) {
	            option.click();
	            break;
	        }
	    }
	    //new Select (driver.findElement(By.name("maintainCourseForm:j_idt21"))).selectByVisibleText(arg1);
	}*/
	
	@When("^create a course whose name is \"([^\"]*)\", courseCode=\"([^\"]*)\", meeting Times are \"([^\"]*)\", time=\"([^\"]*)\", location=\"([^\"]*)\"$")
	public void create_a_course_whose_name_is_courseCode_meeting_Times_are_time_location(String arg1, String arg2, String arg3, String arg4, String arg5) throws Throwable {
	    //select selectItems options
		/*WebElement select = driver.findElement(By.name("maintainCourseForm:j_idt21"));
	    java.util.List<WebElement> options = select.findElements(By.tagName("option"));
	    for(WebElement option : options){
	        if(option.getText().equals(arg1)) {
	            option.click();
	            break;
	        }
	    }*/
	    driver.findElement(By.id("maintainCoursesForm:course-code")).sendKeys(arg2);
	    driver.findElement(By.id("maintainCoursesForm:course-name")).sendKeys(arg1);
	    driver.findElement(By.id("maintainCoursesForm:meeting-times")).sendKeys(arg3);
	    driver.findElement(By.id("maintainCoursesForm:intime")).sendKeys(arg4);
	    driver.findElement(By.id("maintainCoursesForm:inlocation")).sendKeys(arg5);
	    driver.findElement(By.id("maintainCoursesForm:createCourse")).click();
	}

	@Then("^show course \"([^\"]*)\" create success$")
	public void show_course_create_success(String arg1) throws Throwable {
	    String msg=driver.findElement(By.id("maintainCoursesForm:successMessagePane")).getText();
	    Assert.assertEquals("Create Course [" + arg1 + "]: SUCCESS", msg);		
	}
	
	//new methods for ST-09
	@Then("^show course \"([^\"]*)\" create failed$")
	public void show_course_create_failed(String arg1) throws Throwable {
	    String msg=driver.findElement(By.id("maintainCoursesForm:messagePane")).getText();
	    Assert.assertEquals("Create Course [" + arg1 + "]: FAILED", msg);		
	}
	
	//new methods for ST-11
	@Given("^set term \"([^\"]*)\" enrolldate to\"([^\"]*)\"$")
	public void set_term_enrolldate_to(String arg1, String arg2) throws Throwable {
		driver.findElement(By.id("j_idt21:maintainTerms")).click();
		driver.findElement(By.id("maintainTermsForm:inname")).sendKeys(arg1);
		driver.findElement(By.id("maintainTermsForm:enroll-start")).sendKeys(arg2);
	}

	@When("^click update button$")
	public void click_update_button() throws Throwable {
		driver.findElement(By.id("maintainTermsForm:update")).click();
	}

	@Then("^update failed \"([^\"]*)\"$")
	public void update_failed_invalid_date(String arg1) throws Throwable {
	    Assert.assertEquals("invalid date", arg1);
	}
	
	//add methods to ST-12
	@Given("^create \"([^\"]*)\" with startDate \"([^\"]*)\" and endDate \"([^\"]*)\"$")
	public void createWithstartDateendDate(String arg1, String arg2, String arg3){
		driver.findElement(By.id("maintainTermsForm:inname")).sendKeys(arg1);
		driver.findElement(By.id("maintainTermsForm:start-Date")).sendKeys(arg2);
		driver.findElement(By.id("maintainTermsForm:end-Date")).sendKeys(arg3);
		driver.findElement(By.id("maintainTermsForm:createTerm")).click();
	}
	
	@When("^set \"([^\"]*)\" with enrollStart \"([^\"]*)\" and enrollEnd \"([^\"]*)\"$")
	public void set_with_enrollStart_and_enrollEnd(String arg1, String arg2, String arg3) throws Throwable {
		driver.findElement(By.id("maintainTermsForm:inname")).sendKeys(arg1);
		driver.findElement(By.id("maintainTermsForm:enroll-start")).sendKeys(arg2);
		driver.findElement(By.id("maintainTermsForm:enroll-end")).sendKeys(arg3);
		driver.findElement(By.id("maintainTermsForm:update")).click();
	}

	//add methods to post assignment
	@When("^create the assignment of course=\"([^\"]*)\" with dueDate=\"([^\"]*)\", description=\"([^\"]*)\", name=\"([^\"]*)\", type=\"([^\"]*)\"$")
	public void createAssignWithCourseDueDateDescriptionNameType(String arg1, String arg2, String arg3, String arg4, String arg5) throws Throwable{
		new Select (driver.findElement(By.name("maintainassignmentForm:j_idt47"))).selectByVisibleText(arg1);
		driver.findElement(By.id("maintainassignmentForm:indueDate")).sendKeys(arg2);
		driver.findElement(By.id("maintainassignmentForm:indescription")).sendKeys(arg3);
		driver.findElement(By.id("maintainassignmentForm:inassinName")).sendKeys(arg4);
		new Select (driver.findElement(By.name("maintainassignmentForm:j_idt53"))).selectByVisibleText(arg5);
		driver.findElement(By.id("maintainassignmentForm:add")).click();
	}
	
	@Then("^show \"([^\"]*)\" Assignment \"([^\"]*)\" success$")
	public void showAssignSuccess(String arg1, String arg2){
		String msg=driver.findElement(By.id("maintainassignmentForm:successMessagePane")).getText();
		Assert.assertEquals(arg1+" Assignment ["+arg2+"]: SUCCESS", msg);
	}
	
	//add methods to upload assignment
	@Given("^initialize the system, login as stuNo=\"([^\"]*)\", password=\"([^\"]*)\"$")
	public void initialize_the_system_login_as_stuNo_password(String arg1, String arg2) throws Throwable {
		driver=new FirefoxDriver();
	    driver.navigate().to("http://localhost:8080/WebApp4");
	    driver.findElement(By.id("loginForm:stuNo")).sendKeys(arg1);
	    driver.findElement(By.id("loginForm:password")).sendKeys(arg2);
	    driver.findElement(By.id("loginForm:confirmPassword")).click();
	}

	@When("^Upload assignment of course \"([^\"]*)\" with content \"([^\"]*)\"$")
	public void Upload_assignment_of_course_with_content(String arg1, String arg2) throws Throwable {
		new Select (driver.findElement(By.name("assignExamForm:j_idt45"))).selectByVisibleText(arg1);
		driver.findElement(By.id("assignExamForm:search")).click();
		driver.findElement(By.id("assignExamForm:dataTable:1:checked")).click();
		driver.findElement(By.id("assignExamForm:assignContent")).sendKeys(arg2);
		driver.findElement(By.id("assignExamForm:upload")).click();
	}

	@Then("^show upload assignment success$")
	public void show_upload_assignment_success() throws Throwable {
		String actual=driver.findElement(By.id("assignExamForm:successMessagePane")).getAttribute("class");
		Assert.assertEquals("successMessagePane", actual);	
	}
}
