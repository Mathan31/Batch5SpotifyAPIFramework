package com.spotify.base;

import java.util.HashMap;
import java.util.Map;

import com.util.PropertyFileUtil;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static com.spotify.base.SpecBuilder.propFileName;
import static io.restassured.RestAssured.given;
import static com.spotify.base.SpecBuilder.getResponseSpec;

public class TokenManager {
	
	public static String base_uri = PropertyFileUtil.readDataFromPropertyFile(propFileName, "token_uri");
	
	public static String renewToken() {
		Map<String, String> formParam = new HashMap<String, String>();
		formParam.put("grant_type", "refresh_token");
		formParam.put("client_id", PropertyFileUtil.readDataFromPropertyFile(propFileName, "client_id"));
		formParam.put("client_secret", PropertyFileUtil.readDataFromPropertyFile(propFileName, "client_secret"));
		formParam.put("refresh_token", PropertyFileUtil.readDataFromPropertyFile(propFileName, "refresh_token"));
		
		Response response = given()
		.baseUri(base_uri)
		.contentType(ContentType.URLENC)
		.formParams(formParam)
		.post()
		.then()
		.spec(getResponseSpec())
		.extract()
		.response();
		
		if(response.statusCode() != 200) {
			throw new RuntimeException("Renewal of token is failed....");
		}
		
		return response.jsonPath().getString("access_token");
	}

}
