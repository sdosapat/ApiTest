package resources;

public enum APIResources {
	
	addPlaceApi("/maps/api/place/add/json"),
	getPlaceApi("/maps/api/place/get/json"),
	deletePlaceApi("/maps/api/place/delete/json");
	private String resource;
	
	private APIResources(String resource) {
		// TODO Auto-generated constructor stub
		this.resource=resource;
	}
	
	public String getResource() {
		return resource;
	}

}
