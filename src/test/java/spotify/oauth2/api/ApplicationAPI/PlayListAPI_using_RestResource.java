package spotify.oauth2.api.ApplicationAPI;

import static spotify.oauth2.api.TokenManager.*;
import static spotify.oauth2.api.EndPoint_Route.*;

import io.restassured.response.Response;

import spotify.oauth2.pojo.PlayListPOJO;

public class PlayListAPI_using_RestResource {

	public static Response post(PlayListPOJO requestPlayList) {
		return RestResource.post(USERS+ "/31tfbaxilt7ydi7327qrsyq6jgau"+PLAYLISTS, getToken(), requestPlayList);

	}

	public static Response get(String playListID) {
		return RestResource.get(PLAYLISTS+"/" + playListID, getToken());

	}

	public static Response update(PlayListPOJO requestPlayList, String playListID) {
		return RestResource.update(PLAYLISTS+"/"  + playListID, getToken(), requestPlayList);

	}

}
