package RestTesting;
import static io.restassured.RestAssured.given;
import org.testng.annotations.Test;
import  io.restassured.RestAssured;
import  io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
public class Trello {
	public static String baseurl ="https://api.trello.com";
	public static String id;
	@Test(priority = 0)
	public void createBoard()
	{
		RestAssured.baseURI = baseurl;
		
		
		Response response =	 given()
				.queryParam("name", "Saksham Board")
		.queryParam("key","66e3fa945a1044e4a4152e70bec0ef41")
		.queryParam("token","f0aad4151567ed853cb5f95d2fa1c43daed598055346fd718c1e7258ee64dac5")
		.header("content-Type","application/json")
		
		.when()
		.post("/1/boards/")
		
		.then()
		.assertThat().statusCode(200).contentType(ContentType.JSON)
		.extract().response();
		//this is to fetch the response as string
		String jsonresponse = response.asString();
		//I WANT TO CONVERT THE RESPONSE IN JSON FORMAT
		JsonPath js = new JsonPath(jsonresponse);
		//now i have to fetch the id
		id = js.get("id");
		System.out.println(id);
	}
	//if i make any @Test method enabled =false/ that method will not execute
	
		@Test(priority = 1)
		public void getBoard()
		{
			RestAssured.baseURI = baseurl;
			
			
		given()
			.queryParam("key", "66e3fa945a1044e4a4152e70bec0ef41")
			.queryParam("token", "f0aad4151567ed853cb5f95d2fa1c43daed598055346fd718c1e7258ee64dac5")
			.header("Content-Type","application/json")
			
			.when()
			.get("/1/boards/"+id)
			
			.then()
			.assertThat().statusCode(200).contentType(ContentType.JSON)
			.extract().response();	
		
		}
		
		@Test(priority = 2)
		public void DeleteBoard()
		{
			RestAssured.baseURI = baseurl;
				
		Response response =	given()
			.queryParam("key", "66e3fa945a1044e4a4152e70bec0ef41")
			.queryParam("token", "f0aad4151567ed853cb5f95d2fa1c43daed598055346fd718c1e7258ee64dac5")
			.header("Content-Type","application/json")
			
			.when()
			.delete("/1/boards/"+id)
			
			.then()
			.assertThat().statusCode(200).contentType(ContentType.JSON)
			.extract().response();	
			String jsonresponse = response.asString();
			
			System.out.println(jsonresponse);
		}
	}