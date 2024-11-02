package com.spotify.testscenarios;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class TC01_PlayListCRUD {
	
	public String baseURI = "https://api.spotify.com";
	public RequestSpecification requestSpecification;
	public ResponseSpecification responseSpecification;
	public String access_token = "BQC0usDob9fW1PP0j1v9dLu8SsfTIjAOlka8LBwXazi6gC9Qc_2-WQmTGIOUjh4tob6GeRMUdBWDZStm02NlPTGqLjuh0IgsqRzhxU11W7R7x8AcrTrPYvT8OAlNxrHRuyUCLlpb51EkFFJJl8QGKkDpnw36GoJauYK7OffR7i9NM050B8IoKbxDh2z_oJ3JtNpoPa4Z-FXXwDjyt7nZK0rEb1ijhpGtm_fOB-rBRhIgrY5sVG2U5GVodsNiq-ytHRg4Bnc3HI3JlKuHroWcLPow";
	public String userID = "31cwetdnd4oeo5wq2c7khzkqt5oe";
	public String playListID = "4Vj8Fgtb7UTR5LHOGKJqu9";
	@BeforeClass
	public void requestSpecification() {
		RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
		requestSpecBuilder
		.addHeader("Authorization", "Bearer "+access_token)
		.setContentType(ContentType.JSON)
		.setAccept(ContentType.JSON)
		.setBaseUri(baseURI)
		.setBasePath("/v1")
		.log(LogDetail.ALL);
		
		requestSpecification = requestSpecBuilder.build();
		
		
		ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
		responseSpecBuilder
		.log(LogDetail.ALL);
		responseSpecification = responseSpecBuilder.build();
	}
	
	@Test(priority = 1)
	public void validateCreatePlayList() {
		String payLoad = "{\r\n"
				+ "    \"name\": \"New Playlist from Batch 5 restassure\",\r\n"
				+ "    \"description\": \"New playlist description from Batch 5 restassure\",\r\n"
				+ "    \"public\": true\r\n"
				+ "}";
		given()
		.spec(requestSpecification)
		.body(payLoad)
		.when()
		.post("/users/"+userID+"/playlists")
		.then()
		.spec(responseSpecification)
		.body("name", equalTo("New Playlist from Batch 5 restassure"),
			  "description",equalTo("New playlist description from Batch 5 restassure"),
			  "public",equalTo(true));
		
	}
	
	@Test(priority = 2)
	public void validateGetPlayListByPassingPlayListID() {
		given()
		.spec(requestSpecification)
		.when()
		.get("/playlists/"+playListID)
		.then()
		.spec(responseSpecification)
		.contentType(ContentType.JSON)
		.body("name", equalTo("Update Playlist from Batch 5 restassure"),
			  "description",equalTo("Update playlist description from Batch 5 restassure"),
			  "public",equalTo(true));
	}
	
	@Test(priority = 3)
	public void validateUpdatePlayListByPassingPlayListID() {
		String payLoad = "{\r\n"
				+ "    \"name\": \"Update Playlist from Batch 5 restassure\",\r\n"
				+ "    \"description\": \"Update playlist description from Batch 5 restassure\",\r\n"
				+ "    \"public\": true\r\n"
				+ "}";
		given()
		.spec(requestSpecification)
		.body(payLoad)
		.when()
		.put("/playlists/"+playListID)
		.then()
		.spec(responseSpecification)
		.statusCode(200);
	}
	

	@Test(priority = 4)
	public void validateCreatePlayListWithNoMandatoryField() {
		String payLoad = "{\r\n"
				+ "    \"name\": \"\",\r\n"
				+ "    \"description\": \"New playlist description from Batch 5 restassure\",\r\n"
				+ "    \"public\": true\r\n"
				+ "}";
		given()
		.spec(requestSpecification)
		.body(payLoad)
		.when()
		.post("/users/"+userID+"/playlists")
		.then()
		.spec(responseSpecification)
		.body("error.status", equalTo(400),
			  "error.message",equalTo("Missing required field: name"));
		
	}
	
	
	
	
	
	
	
	

}
