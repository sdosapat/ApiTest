package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeTestScenario() throws IOException {
		
		PlaceApiDefination pad = new PlaceApiDefination();
		if(PlaceApiDefination.placeId==null)
		{
			//Add the place to get place_id
			pad.add_Place_paylodad("Jyothi", "Hindi", "Khammam");
			
			// Call the request
			pad.user_calls_with_Post_http_request("addPlaceApi", "POST");

			//get place_id
			pad.verify_place_id_created_in_maps_for_using("Jyothi", "getPlaceApi");
		}	
	}

}
