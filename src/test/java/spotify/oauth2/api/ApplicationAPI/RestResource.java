package spotify.oauth2.api.ApplicationAPI;

import static io.restassured.RestAssured.given;
import static spotify.oauth2.api.EndPoint_Route.*;
import static spotify.oauth2.api.specBuilder.*;
import java.util.HashMap;
import io.restassured.response.Response;

public class RestResource {

	public static Response post(String path, String access_token,Object requestPlayList) {
	return	given(getRequestSpec())
				.body(requestPlayList).header("Authorization", "Bearer" + " " + access_token)
			.when()
				.post(path)
			.then().spec(getResponseSpec())
				.log().all()
				.extract()
				.response();
	}
	
	public static Response postAccountRenewToken(HashMap<String,String> formParams) {
	return	   given(getAccountRequestSpec())
				.formParams(formParams).log().all().when().post(API+TOKEN).then().spec(getResponseSpec()).log().all()
				.extract().response();
	}
	
	public static Response get(String path,String access_token) {
	return	given(getRequestSpec())
				.header("Authorization", "Bearer" + " " + access_token)
			.when()
				.get(path).
			then()
				.spec(getResponseSpec())
				.log().all()
				.extract()
				.response();
	}
	
	public static Response update(String path,String access_token,Object requestPlayList) {
	return 	given(getRequestSpec())
				.body(requestPlayList)
				.header("Authorization", "Bearer" + " " + access_token)
				.when()
					.put(path)
				.then()
					.spec(getResponseSpec())
					.log().all()
					.extract()
					.response();
				
	}

}
