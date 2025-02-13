package spotify.oauth2.tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeClass;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static io.restassured.RestAssured.get;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TC_01_PlayListTests {

	

	String access_token = "BQC7BwHqFcMwHY4xAYcI6MUL33ZX8DGa0BeCixLVIRKiLPgal_l1GdX6hFV41oVhTlbvuT3lmniyY8s1UdsOHrFsOwJOQ7cNJI-d70nf9lPhvfygXFvXbgE8GdwNxkR_x7S4UFJ3stf32qN7P5GolgKJIlFU9y2EkSsBbWVYUsNFzKb4UFfmW8YYUXQidJyAN5aNN5Xa9h8LNplipTlMvmEaSMmpfOvbzkFv8hjVYrlbv4iqzRGSPOVH4rDQYQXWDKpf6AN7wHPt0nxKHhKUE8Mp";
	RequestSpecification requestSpecification;
	ResponseSpecification responseSpecification;
	@BeforeClass
	public void beforeClass() {

		RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder()
				.setBaseUri("https://api.spotify.com")
				.setBasePath("/v1")
				.setContentType(ContentType.JSON)
				.addHeader("Authorization", "Bearer"+ " "+ access_token)
				.log(LogDetail.ALL);

		requestSpecification = requestSpecBuilder.build();

		ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder()
				.log(LogDetail.ALL);
		responseSpecification = responseSpecBuilder.build();
	}
	
	@Test
	public void createAPIPlayList() {
		
		String paylaod="{\n"
				+ "    \"name\": \"New RestAssured Playlist\",\n"
				+ "    \"description\": \"New RestAssured playlist description\",\n"
				+ "    \"public\": false\n"
				+ "}";
		given(requestSpecification)
			.body(paylaod)
		.when()
			.post("/users/31tfbaxilt7ydi7327qrsyq6jgau/playlists")
		.then().spec(responseSpecification)
			.assertThat()
			.log().all()
			.statusCode(201)
			.body("name", equalTo("New RestAssured Playlist")
				,"description",equalTo("New RestAssured playlist description")
				,"public",equalTo(false));
	}
	
	@Test
	public void getAPIPlayList() {
		given(requestSpecification)
		.when()
			.get("/playlists/1dxI9hNyKXd3gpB6Dn55VI").
		then()
			.spec(responseSpecification)
			.log().all()
			.assertThat().statusCode(200)
			.body("name", equalTo("New RestAssured Playlist")
				,"description",equalTo("New RestAssured playlist description")
				,"public",equalTo(false));
	}
	
	@Test
	public void updateAPIPlayList() {
		
		String update_PlayList_paylaod="{\n"
				+ "    \"name\": \"Updated Playlist\",\n"
				+ "    \"description\": \"Updated playlist description\",\n"
				+ "    \"public\": false\n"
				+ "}";
		given(requestSpecification)
			.body(update_PlayList_paylaod)
		.when()
			.put("/playlists/1dxI9hNyKXd3gpB6Dn55VI")
		.then()
			.spec(responseSpecification)
			.log().all()
			.assertThat().statusCode(200);
		
	}

}
