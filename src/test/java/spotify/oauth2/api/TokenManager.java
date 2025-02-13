package spotify.oauth2.api;

import java.time.Instant;
import java.util.HashMap;
import io.restassured.response.Response;
import spotify.oauth2.api.ApplicationAPI.RestResource;
import static spotify.oauth2.api.specBuilder.getResponseSpec;

public class TokenManager {
	private static String access_Token;
	private static Instant expiry_Time;

	public static String getToken() {

		try {
			if (access_Token == null || Instant.now().isAfter(expiry_Time)) {
				Response response = renewToken();
				System.out.println("Renewing Token!!");
				access_Token = response.path("access_token");
				// this means we are fetching the value of access_token from the response path.
				// then we set that access_Token value to static String access_Token;
				int expiresIn = response.path("expires_in");
				// same for expiry_Token
				expiry_Time = Instant.now().plusSeconds(expiresIn);
			} else {
				System.out.println("Token is goog to use, no need to renew Token now");
			}
		} catch (Exception e) {
			throw new RuntimeException("Abort!! Renew Token failed");
		}

		return access_Token;
	}

	private static Response renewToken() {

		HashMap<String, String> formParams = new HashMap<String, String>();
		formParams.put("client_id", "787aeda4e891449e98d8598c27c9e5f9");
		formParams.put("client_secret", "e0a6eef839914611b73952272e4bdcb5");
		formParams.put("grant_type", "refresh_token");
		formParams.put("refresh_token",
				"AQB4jaKsASUxnafgB-JCuIWt_ecTd9gDDOd0LRFUIXvLZSdqiq7FItXTKmXNG_eUeGpbyPi6kNGgoZ1jomy5ETUEeWHlDu-B3SVVMB9eSVqTPUlF7dSr7uN-9AVJ2xRGVYg");

		Response response =RestResource.postAccountRenewToken(formParams);

		if (response.statusCode() != 200) {
			throw new RuntimeException("Abort!! Renew Token failed");
		}

		return response;

	}

}
