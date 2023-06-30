import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import org.testng.Assert;
import io.restassured.path.xml.*;

public class SOAP_api {

	public static void main(String[] args) {
		// delclare the base uri
		RestAssured.baseURI="https://www.dataaccess.com/";
		//Declare request Body
		String RequestBody= "<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n"
				+ "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n"
				+ "  <soap:Body>\r\n"
				+ "    <NumberToDollars xmlns=\"http://www.dataaccess.com/webservicesserver/\">\r\n"
				+ "      <dNum>21</dNum>\r\n"
				+ "    </NumberToDollars>\r\n"
				+ "  </soap:Body>\r\n"
				+ "</soap:Envelope>";
		String ResponseBody=given().header("Content-Type","text/xml; charset=utf-8").body(RequestBody).
				when().post("webservicesserver/NumberConversion.wso").
				then().extract().response().asString();
		System.out.println(ResponseBody);
		XmlPath XmlResponse=new XmlPath (ResponseBody);
		String Res_Parameter = XmlResponse.getString("NumberToDollarsResult");
		System.out.println(Res_Parameter);
		Assert.assertEquals(Res_Parameter, "twenty one dollars");
		
	}

}
