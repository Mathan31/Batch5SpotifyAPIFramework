package com.spotify.testscenarios;

import org.testng.annotations.Test;

import com.spotify.application.AlbumAPI;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static com.spotify.base.SpecBuilder.getRequestSpec;
import static com.spotify.base.SpecBuilder.getResponseSpec;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
public class TC02_Album {
	
	public static String albumId = "1tEu6qNPURb0YfjaRDK23w";
	
	@Test(priority = 1)
	public void validateGetAlbumBasedOnAlbumID() {
		Response response = AlbumAPI.getAlbumBasedOnAlbumID(albumId);
		assertThat(response.statusCode(), equalTo(200));
		assertThat(response.jsonPath().getString("id"), containsString(albumId));
	}

}
