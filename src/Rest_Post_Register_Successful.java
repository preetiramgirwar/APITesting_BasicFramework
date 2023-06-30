import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import org.testng.Assert;
import io.restassured.path.json.JsonPath;

public class Rest_Post_Register_Successful {

	public static void main(String[] args) {
		
		RestAssured.baseURI="https://reqres.in/";
		
		//declare the requestBody
		
		String RequestBody="{\r\n"
				+ "    \"email\": \"eve.holt@reqres.in\",\r\n"
				+ "    \"password\": \"pistol\"\r\n"
				+ "}";
		System.out.println("Request Body is: \n" + RequestBody);
		
		 //declare given,when and then method
		
		String ResponseBody=given().header("Content-Type","application/json").body(RequestBody).
				when().post("api/register").then().extract().response().asPrettyString();
		System.out.println("Responsebody : \n"+ ResponseBody);
		
		
		JsonPath JspResponse=new JsonPath(ResponseBody);
		String Res_id=JspResponse.getString("id");
		String Res_token=JspResponse.getString("token");
		
		 Assert.assertNotEquals(Res_id,0);
         Assert.assertNotEquals(Res_id,null);
		Assert.assertEquals(Res_token,"QpwL5tke4Pnpja7X4");

	}
}