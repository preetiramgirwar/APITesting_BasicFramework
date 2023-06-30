import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import org.testng.Assert;
import io.restassured.path.json.JsonPath;

public class Rest_Post_Login_Successful {

	public static void main(String[] args) {
		
		RestAssured.baseURI="https://reqres.in/";
		
		//declare the requestBody
		
		String RequestBody="{\r\n"
				+ "    \"email\": \"eve.holt@reqres.in\",\r\n"
				+ "    \"password\": \"cityslicka\"\r\n"
				+ "}";
		System.out.println("Request Body is: \n" + RequestBody);
		
		 //declare given,when and then method
		
		String ResponseBody=given().header("Content-Type","application/json").body(RequestBody).
				when().post("api/login").then().extract().response().asPrettyString();
		System.out.println("Responsebody : \n"+ ResponseBody);
		
		
		JsonPath JspResponse = new JsonPath(ResponseBody);
		String Res_token=JspResponse.getString("token");
		
		//Validate the response body
		
		Assert.assertEquals(Res_token,"QpwL5tke4Pnpja7X4");

	}
}