package spotify.oauth2.tests;


import org.testng.annotations.Test;
import spotify.oauth2.pojo.Error;
import spotify.oauth2.pojo.InnerError;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import spotify.oauth2.api.specBuilder;
import spotify.oauth2.pojo.PlayListPOJO;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static io.restassured.RestAssured.get;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.*;

public class TC_02_PlayListTests_POJO {
	
	@Test
	public void createAPIPlayList() {
		
		PlayListPOJO requestPlayList= new PlayListPOJO()
		.setName("New RestAssured Playlist POJO")
		.setDescription("New RestAssured playlist description POJO")
		.setPublic(false);
		

	PlayListPOJO responsePlayList=	given(specBuilder.getRequestSpec())
			.body(requestPlayList)
		.when()
			.post("/users/31tfbaxilt7ydi7327qrsyq6jgau/playlists")
		.then().spec(specBuilder.getResponseSpec())
			.assertThat()
			.log().all()
			.statusCode(201)
			.extract()
			.response()
			.as(PlayListPOJO.class);
	
		assertThat(responsePlayList.getName(),equalTo(requestPlayList.getName()));
		assertThat(	responsePlayList.getDescription(),equalTo(requestPlayList.getDescription()));
		assertThat(	responsePlayList.getPublic(),equalTo(requestPlayList.getPublic()));
	}
	
	@Test
	public void getAPIPlayList() {
		PlayListPOJO requestPlayList= new PlayListPOJO()
		.setName("New RestAssured Playlist POJO")
		.setDescription("New RestAssured playlist description POJO")
		.setPublic(false);
		
		PlayListPOJO responsePlayList=	given(specBuilder.getRequestSpec())
		.when()
			.get("/playlists/74DpEfAuNLgdNyRThfeLUw").
		then()
			.spec(specBuilder.getResponseSpec())
			.log().all()
			.statusCode(200)
			.extract()
			.response()
			.as(PlayListPOJO.class);
		assertThat(responsePlayList.getName(),equalTo(requestPlayList.getName()));
		assertThat(	responsePlayList.getDescription(),equalTo(requestPlayList.getDescription()));
	//	assertThat(	responsePlayList.getPublic(),equalTo(requestPlayList.getPublic()));
	}
	
	@Test
	public void updateAPIPlayList() {
		
		PlayListPOJO updaterequestPlayList= new PlayListPOJO()
		.setName("Update RestAssured Playlist POJO")
		.setDescription("Update RestAssured playlist description POJO")
		.setPublic(false);
		
				given(specBuilder.getRequestSpec())
			.body(updaterequestPlayList)
		.when()
			.put("/playlists/1dxI9hNyKXd3gpB6Dn55VI")
		.then()
			.spec(specBuilder.getResponseSpec())
			.log().all()
			.statusCode(200);
		
	}
	
	@Test
	public void createAPIPlayListwithExpiredToken() {
		PlayListPOJO requestPlayList= new PlayListPOJO()
				.setName("New RestAssured Playlist POJO")
				.setDescription("New RestAssured playlist description POJO")
				.setPublic(false);
				
			Error error= given(specBuilder.getRequestSpec())
					.body(requestPlayList)
				.when()
					.post("/users/31tfbaxilt7ydi7327qrsyq6jgau/playlists")
				.then().spec(specBuilder.getResponseSpec())
					.assertThat()
					.log().all()
					.statusCode(401)
					.extract()
					.as(Error.class);
		
	}

}
