package spotify.oauth2.api.ApplicationAPI;

import static io.restassured.RestAssured.given;
import static spotify.oauth2.api.TokenManager.*;
import io.restassured.response.Response;
import static spotify.oauth2.api.specBuilder.*;
import spotify.oauth2.pojo.PlayListPOJO;

public class PlayListAPI {
	
	public static Response post(PlayListPOJO requestPlayList) {
	return	given(getRequestSpec())
				.body(requestPlayList).header("Authorization", "Bearer" + " " + getToken())
			.when()
				.post("/users/31tfbaxilt7ydi7327qrsyq6jgau/playlists")
			.then().spec(getResponseSpec())
				
				.log().all()
				.extract()
				.response();
	}
	public static Response get(String playListID) {
	return	given(getRequestSpec())
				.header("Authorization", "Bearer" + " " + getToken())
			.when()
				.get("/playlists/" +playListID).
			then()
				.spec(getResponseSpec())
				.log().all()
				.extract()
				.response();
	}
	
	public static Response update(PlayListPOJO requestPlayList, String playListID) {
	return 	given(getRequestSpec())
				.body(requestPlayList)
				.header("Authorization", "Bearer" + " " + getToken())
				.when()
					.put("/playlists/" +playListID)
				.then()
					.spec(getResponseSpec())
					.log().all()
					.extract()
					.response();
				
	}

}
