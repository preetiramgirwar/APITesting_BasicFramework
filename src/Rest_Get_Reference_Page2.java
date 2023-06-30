import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

public class Rest_Get_Reference_Page2 {

	public static void main(String[] args) {
		//declare the base URL
		RestAssured.baseURI="https://reqres.in/";
		
		//declare the given, when and then method(Response body fetching)
		given().header("Content-Type","application/json").
				when().get("api/users?page=2").
				then().log().all().extract().response();
	}
}

