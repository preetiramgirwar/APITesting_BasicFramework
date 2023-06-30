import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;

public class Rest_Delete_Reference {

	public static void main(String[] args) {
		//declare the base URL
		RestAssured.baseURI="https://reqres.in/";
		
		//declare the given, when and then method(Response body fetching)
		given().header("Content-Type","application/json").
				when().delete("api/users/2").
				then().log().all().extract().response();
	}
}