package resources;

import java.util.ArrayList;
import java.util.List;

import PojoTest.AddPlace;
import PojoTest.Location;

public class TestData {
	
	public AddPlace AddPlacePayload(String name, String language, String address){
		
		AddPlace ap = new AddPlace();
		ap.setAccuracy(50);
		ap.setName(name);
		ap.setPhone_number("(+91) 983 893 3937");
		ap.setAddress(address);
		ap.setWebsite("http://google.com");
		ap.setLanguage(language);
		List<String> myTypes = new ArrayList<String>();
		myTypes.add("shoe park");
		myTypes.add("shop");
		ap.setTypes(myTypes);
		Location loc = new Location();
		loc.setLat(-38.383494);
		loc.setLng(33.427362);
		ap.setLocation(loc);
		return ap;
	}
	
	public String deletePlacePayload(String placeId) {
		return "{\r\n" + 
    		"	\"place_id\":\""+placeId+"\"\r\n" + 
    		"}";
	}

}
