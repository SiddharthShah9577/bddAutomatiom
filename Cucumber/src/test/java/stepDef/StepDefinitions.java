package stepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.PageNavigation;
import utils.CommonFunctions;

public class StepDefinitions extends CommonFunctions {
	PageNavigation pageNavigation = new PageNavigation();
	@Given("^user launching the application '(.+)'$")
	public void user_launching_the_application_(String testcaseid) throws Throwable {
		excelReader(testcaseid);
		launchURL();
	}

	@When("^user fill All Details of Form$")
	public void user_fill_all_details_of_form() throws Throwable {
		pageNavigation.signUpPage.createNewUser();
	}

	@Then("^user is able to login Successfully$")
	public void user_is_successfully_able_to_login() throws Throwable {
		waitforElement("orderDetails");
	}

	@And("^user clickon signIn button$")
	public void user_clickon_signin_button() throws Throwable {
		propertiesFileName = "homePage";
		clickOnObject("signIn");
	}

	@When("^user select the products$")
	public void user_select_the_products() throws Throwable {
		propertiesFileName = "homePage";
		clickOnObject("women");
		pageNavigation.productBuyPage.orderProduct();
	}

	@Then("^user able to checkout product$")
	public void user_able_to_checkout_product() throws Throwable {
		pageNavigation.productBuyPage.orderPayment();
		pageNavigation.productBuyPage.verifyTotalPrice();

	}

	@And("^user enter sign In Details$")
	public void user_enter_sign_in_details() throws Throwable {
		propertiesFileName = "homePage";
		clickOnObject("signIn");
		propertiesFileName = "buyProduct";
		pageNavigation.signInPage.signInDetails();
	}

}
