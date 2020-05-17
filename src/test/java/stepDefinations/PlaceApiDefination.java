package stepDefinations;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

import PojoTest.AddPlace;
import PojoTest.Location;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestData;
import resources.Utils;

public class PlaceApiDefination extends Utils{
	RequestSpecification reqSpe;
	ResponseSpecification res;
	Response response;
	static String placeId;
	TestData data = new TestData();

	@Given("Add Place paylodad with  {string} {string} {string}")
	public void add_Place_paylodad(String name, String language, String address) throws IOException {
		//Create request method
		 reqSpe = given().spec(reqSpecificationAddPlace()).body(data.AddPlacePayload(name ,language, address));
	}

	@When("User calls {string} with {string} http request")
	public void user_calls_with_Post_http_request(String api, String method) {
		// Call the API and fetch response
		APIResources resourceApi =APIResources.valueOf(api);
		System.out.println(resourceApi.getResource());
		 res = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		 if(method.equalsIgnoreCase("POST")) {
			 response = reqSpe.when().post(resourceApi.getResource());
		 }
		 else if(method.equalsIgnoreCase("GET"))
		 {
			 response = reqSpe.when().get(resourceApi.getResource());
		 }
		 else {
			 response = reqSpe.when().put(resourceApi.getResource());
		 }		 
	}

	@Then("the status call is success with status code {int}")
	public void the_status_call_is_success_with_status_code(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    assertEquals(response.getStatusCode(),200);
	    System.out.println("status code is 200");
	}

	@And("{string} in response body is {string}")
	public void in_response_body_is(String KeyValue, String ExpectedValue) {
	    // Write code here that turns the phrase above into concrete actions
		assertEquals(gerRespoinsePlace(response,KeyValue),ExpectedValue);
		System.out.println("Status is " + ExpectedValue);
	}
	
	@And("verify place_id created in maps for {string} using {string}")
	public void verify_place_id_created_in_maps_for_using(String name, String method) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		placeId = gerRespoinsePlace(response,"place_id");
		reqSpe = given().spec(reqSpecificationAddPlace()).queryParam("place_id",placeId );
		
		user_calls_with_Post_http_request(method, "GET");
		String PersonName = gerRespoinsePlace(response, "name");
		System.out.println(PersonName);
		System.out.println(placeId);
		assertEquals(PersonName, name);
	}
	
	@Given("delete place payload")
	public void delete_place_payload() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		reqSpe = given().spec(reqSpecificationAddPlace()).body(data.deletePlacePayload(placeId));
	}
}
