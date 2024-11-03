package com.wrapper.api;

import static com.spotify.base.SpecBuilder.getRequestSpec;
import static com.spotify.base.SpecBuilder.getResponseSpec;
import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class RestAssureHTTPMethods {
	
	public static Response post(Object requestPlayList,String path) {
		return given()
				.spec(getRequestSpec())
				.body(requestPlayList)
				.when()
				.post(path)
				.then()
				.spec(getResponseSpec())
				.contentType(ContentType.JSON)
				.and()
				.extract()
				.response();
	}
	
	public static Response get(String path) {
		return  given()
				.spec(getRequestSpec())
				.when()
				.get(path)
				.then()
				.spec(getResponseSpec())
				.contentType(ContentType.JSON)
				.statusCode(200)
				.and()
				.extract()
				.response();
	}
	
	public static Response update(Object  requestPlayList,String path) {
		return given()
				.spec(getRequestSpec())
				.body(requestPlayList)
				.when()
				.put(path)
				.then()
				.spec(getResponseSpec())
				.extract()
				.response();
	}

}
