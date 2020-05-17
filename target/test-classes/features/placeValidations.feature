Feature: Validating Place API

@AddPlace @p1
Scenario Outline: Verify Place is added or not
	Given Add Place paylodad with  "<name>" "<language>" "<address>"
	When User calls "addPlaceApi" with "POST" http request
	Then the status call is success with status code 200 
	And "status" in response body is "OK"
	And verify place_id created in maps for "<name>" using "getPlaceApi"
	
Examples:
		|name    |language| address |
		|satish  |Telugu  | Hyderabad |
#		|Sandeep |Tamil   | Chennai |


@DeletePlace @p1
Scenario: Verify place is deleted or not
	Given delete place payload
	When User calls "deletePlaceApi" with "POST" http request
	Then the status call is success with status code 200 
	And "status" in response body is "OK"
	