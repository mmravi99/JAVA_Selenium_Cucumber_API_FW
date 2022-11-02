package csg.stepdefinitions;

import static org.testng.Assert.assertFalse;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.hc.core5.http.protocol.ResponseServer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.GherkinKeyword;

import csg.TestComponents.ResponseTags;
import csg.TestComponents.APIBaseClass;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.*;
import payloads.CreateBooking;
import payloads.UpdateBooking;

public class BookingInformation extends APIBaseClass {

	public static Response response = null;
	public static String resString = null;
	public static String bookingID = null;

	@Given("I hit Get Booking All details request")
	public void i_hit_get_booking_all_details_request() {
		// Write code here that turns the phrase above into concrete actions
		test = logInfo.get().createNode("I hit Get Booking All details request");

		response = given().when().get("/booking").then().extract().response();
		resString = response.then().extract().asPrettyString();

		reportInfo(resString);

		System.out.println("Get All Booking Details ");
	}

	
	
	
	
	@Given("I hit Create Booking request")
	public void i_hit_create_booking_request() {
		// Write code here that turns the phrase above into concrete actions
		test = logInfo.get().createNode("I hit Create Booking request");

		String reqBody = CreateBooking.getPayLoad("Ravi", "Kumar", "1200", "false", "2022-10-31", "2022-11-03","Parking");

		reportInfo(reqBody);
		response = given().header("Content-type", "application/json").and().body(reqBody).when().post("/booking").then().extract().response();
		resString = response.then().extract().asPrettyString();

		JsonPath js = new JsonPath(resString);
		bookingID = js.getString(ResponseTags.BOOKING_ID);
		reportInfo("Booikng is created with ID " + bookingID);
		System.out.println ("Booikng is created with ID " + bookingID);


	}
	
	@When("Status code is {string}")
	public void status_code_is(String stcode) {
		// Write code here that turns the phrase above into concrete actions
		test = logInfo.get().createNode("Status code is " + stcode);
		reportPass("Status Code",stcode, String.valueOf(response.statusCode()));
	}

	
	@Then("verify booking details")
	public void verify_booking_details() {
		// Write code here that turns the phrase above into concrete actions
		test = logInfo.get().createNode("verify  booking details");
		JsonPath js= new JsonPath(resString);
		reportInfo(resString);
		validateField("First Name ","Ravi",js.getString(ResponseTags.CREATE_FIRST_NAME));
		validateField("Last Name ","Kumar",js.getString(ResponseTags.CREATE_LAST_NAME));
		validateField("Total Price ","1200",js.getString(ResponseTags.CREATE_TOT_PRICE));
		validateField("Deposit Paid ","false",js.getString(ResponseTags.CREATE_DEPOSIT_PAID));
		validateField("check In","2022-10-31",js.getString(ResponseTags.CREATE_CHECK_IN));
		validateField("check Out ","2022-11-03",js.getString(ResponseTags.CREATE_CHECK_OUT));
		validateField("Additional Needs ","Parking",js.getString(ResponseTags.CREATE_ADD_NEEDS));

	}
	
	
	@Then("verify updated booking details")
	public void verify_updated_booking_details() {
		// Write code here that turns the phrase above into concrete actions
				test = logInfo.get().createNode("verify  Updated Booking details");
				JsonPath js= new JsonPath(resString);
				reportInfo(resString);
				validateField("First Name ","Rahul",js.getString(ResponseTags.UPDATE_FIRST_NAME));
				validateField("Last Name ","Mohan",js.getString(ResponseTags.UPDATE_LAST_NAME));
				validateField("Total Price ","1562",js.getString(ResponseTags.UPDATE_TOT_PRICE));
				validateField("Deposit Paid ","true",js.getString(ResponseTags.UPDATE_DEPOSIT_PAID));
				validateField("check In","2022-11-02",js.getString(ResponseTags.UPDATE_CHECK_IN));
				validateField("check Out ","2022-11-06",js.getString(ResponseTags.UPDATE_CHECK_OUT));
				validateField("Additional Needs ","Parking, Breakfast",js.getString(ResponseTags.UPDATE_ADD_NEEDS));
	}
	
	
	
	
	
	
	
	

	@Given("I hit Update Booking request for booking with {string}")
	public void i_hit_update_booking_request_for_booking_with(String bookingid) {
		// Write code here that turns the phrase above into concrete actions

		test = logInfo.get().createNode("I hit Update Booking request with Booking ID");

		String reqBody = UpdateBooking.getPayLoad("Rahul", "Mohan", "1245", "true", "2022-11-02", "2022-11-06",
				"Parking, Breakfast");

		reportInfo(reqBody);
		bookingid = bookingID;
		response = given().contentType(ContentType.JSON).header("Cookie", "token=" + token).body(reqBody).when()
				.put("/booking/" + bookingid).then().extract().response();
		resString = response.then().extract().asPrettyString();

		if (response.statusCode() == 200) {
			reportInfo("Booking " + bookingID + " is updated successfully");
			System.out.println ("Booking " + bookingID + " is updated successfully");

		}
			
		else {
			reportInfo("Error in response with status code " + response.statusCode());
			reportFail(" Status Code ","200", String.valueOf(response.statusCode()));
			assertFalse(true);
		}
	}

	@Given("I hit delete Booking request for booking with {string}")
	public void i_hit_delete_booking_request_for_booking_with(String bookingid) {
		// Write code here that turns the phrase above into concrete actions
		test = logInfo.get().createNode("I hit Delete Booking request with Booking ID");

		bookingid = bookingID;
		response = given().contentType(ContentType.JSON).header("Cookie", "token=" + token).when()
				.delete("/booking/" + bookingid).then().extract().response();
			
		reportInfo("Booking with ID : " + bookingid +" is deleted ");
		System.out.println ("Booking with ID : " + bookingid +" is deleted ");
		
	}

	@Then("verify deleted booking not exists")
	public void verify_deleted_booking_not_exists() {
		// Write code here that turns the phrase above into concrete actions
		test = logInfo.get().createNode("Verify deleted booking not exists");
		
		response = given().when().get("/booking/"+bookingID).then().extract().response();
		resString = response.then().extract().asPrettyString();

		reportInfo(resString);
		validateField("Status Code ", "404", String.valueOf(response.statusCode()));
	}

	

}
