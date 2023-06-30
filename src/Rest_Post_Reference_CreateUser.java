import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import java.time.LocalDateTime;
import org.testng.Assert;
import io.restassured.path.json.JsonPath;
public class Rest_Post_Reference_CreateUser {

	public static void main(String[] args) {
		//declare the base URL
		RestAssured.baseURI="https://reqres.in/";
		//request body string variable
		String requestBody="{\r\n"
				+ "	\"name\": \"morpheus\",\r\n"
				+ "	\"job\": \"leader\"\r\n"
				+ "}";
		System.out.println("RequestBody is : \n" + requestBody);
		
		
		//declare the given, when and then method(Response body fetching)
		
		int statusCode=given().header("Content-Type","application/json").body(requestBody).
				when().post("api/users").
				then().extract().response().statusCode();
		System.out.println("StatusCode : " + statusCode);
		
		String responseBody=given().header("Content-Type","application/json").body(requestBody).
				when().post("api/users").
				then().extract().response().asPrettyString();
		System.out.println("ResponseBody : \n" +responseBody);
		
		// declare the expected results
		JsonPath JspRequest = new JsonPath(requestBody);
		String Req_name = JspRequest.getString("name");
		String Req_job = JspRequest.getString("job");
		LocalDateTime currentdate = LocalDateTime.now();
		String expecteddate = currentdate.toString().substring(0, 11);

		// create an object of JSON path to parse the response body
		JsonPath JspResponse = new JsonPath(responseBody);
		String Res_name = JspResponse.getString("name");
		String Res_job = JspResponse.getString("job");
		String Res_id = JspResponse.getString("id");
		String Res_createdAt = JspResponse.getString("createdAt");
		Res_createdAt = Res_createdAt.substring(0, 11);

		// validate the response body parameters
		Assert.assertEquals(Res_name, Req_name);
		Assert.assertEquals(Res_job, Req_job);
		Assert.assertNotEquals(Res_id, 0);
		Assert.assertNotNull(Res_id);
		Assert.assertEquals(Res_createdAt, expecteddate);
	}

}
