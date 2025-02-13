package spotify.oauth2.tests;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import spotify.oauth2.api.ApplicationAPI.PlayListAPI;
import spotify.oauth2.api.ApplicationAPI.PlayListAPI_using_RestResource;
import spotify.oauth2.api.ApplicationAPI.RestResource;
import spotify.oauth2.pojo.PlayListPOJO;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


public class TC_04_PlayListTests_POJO_API_RestResource {
	 private static String playlistId; 
	
	@Test(priority = 1)
	public void createAPIPlayList() {
		
		PlayListPOJO requestPlayList= new PlayListPOJO()
		.setName("New RestAssured Playlist POJO")
		.setDescription("New RestAssured playlist description POJO")
		.setPublic(false);
		
		Response response=	PlayListAPI_using_RestResource.post(requestPlayList);
		assertThat(response.statusCode(),equalTo(201));
		
		PlayListPOJO responsePlayList=	response.as(PlayListPOJO.class);
		 playlistId = responsePlayList.getId();
	        System.out.println("Playlist ID created: " + playlistId);

		assertThat(responsePlayList.getName(),equalTo(requestPlayList.getName()));
		assertThat(	responsePlayList.getDescription(),equalTo(requestPlayList.getDescription()));
		assertThat(	responsePlayList.getPublic(),equalTo(requestPlayList.getPublic()));
	}
	
	
	@Test(priority = 2, dependsOnMethods = "createAPIPlayList")
	public void getAPIPlayList() {
		PlayListPOJO requestPlayList= new PlayListPOJO()
				.setName("New RestAssured Playlist POJO")
				.setDescription("New RestAssured playlist description POJO")
				.setPublic(false);
		
		Response response=PlayListAPI_using_RestResource.get(playlistId);
		assertThat(response.statusCode(),equalTo(200));
		PlayListPOJO responsePlayList=	response.as(PlayListPOJO.class);
		
		
		assertThat(responsePlayList.getName(),equalTo(requestPlayList.getName()));
		assertThat(	responsePlayList.getDescription(),equalTo(requestPlayList.getDescription()));
	//	assertThat(	responsePlayList.getPublic(),equalTo(requestPlayList.getPublic()));
	}
	
	@Test(priority = 3, dependsOnMethods = "createAPIPlayList")
	public void updateAPIPlayList() {
		
		PlayListPOJO requestPlayList= new PlayListPOJO()
		.setName("Update RestAssured Playlist POJO")
		.setDescription("Update RestAssured playlist description POJO")
		.setPublic(false);
		
		Response response=PlayListAPI_using_RestResource.update(requestPlayList,playlistId);
		assertThat(response.statusCode(),equalTo(200));
		
	
		
	}
	
	/*
	 * @Test public void createAPIPlayListwithExpiredToken() { PlayList
	 * requestPlayList= new PlayList() .setName("New RestAssured Playlist POJO")
	 * .setDescription("New RestAssured playlist description POJO")
	 * .setPublic(false);
	 * 
	 * Error error= given(specBuilder.getRequestSpec()) .body(requestPlayList)
	 * .when() .post("/users/31tfbaxilt7ydi7327qrsyq6jgau/playlists")
	 * .then().spec(specBuilder.getResponseSpec()) .assertThat() .log().all()
	 * .statusCode(401) .extract() .as(Error.class);
	 * 
	 * }
	 */

}
