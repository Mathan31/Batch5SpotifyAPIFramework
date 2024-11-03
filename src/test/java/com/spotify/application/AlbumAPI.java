package com.spotify.application;

import static com.spotify.route.Rounting.*;
import com.wrapper.api.RestAssureHTTPMethods;

import io.restassured.response.Response;

public class AlbumAPI {
	
	public static Response getAlbumBasedOnAlbumID(String albumId) {
		return RestAssureHTTPMethods.get(ALBUM+"/"+albumId);
	}

}
