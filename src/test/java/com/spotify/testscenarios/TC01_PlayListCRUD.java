package com.spotify.testscenarios;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.spotify.pojo.Error;
import com.spotify.pojo.PlayList;

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
	public String access_token = "BQDaDfpwfAdtS4jFdgvepEWGxPxaGepSDX1w7VwANZRsICj6U9zcyMCjft_0Ve03wwNxpU-pDbTk-YXuvZ7YRYW0aeI_xu9FKhBRyxYSe8T38qYhwt5WNn4Tv4EE7iGCAL7HTPPF-95XJI5Y3VRN_kY3GJG_dDGu_Z_utayEcAo_DDwrakqlkZ-q7qpHzpquBqG_UEQcHWJ_PIBDLUNJE_TIVqgDN4Ex22K86PElydsoZTvTgMwSWlYVb-5TAj4bOIGVHl0rfuvpPsjL7OnIoqCX";
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
		
		PlayList requestPlayList = new PlayList();
		requestPlayList.setName("New Playlist from Batch 5 restassure");
		requestPlayList.setDescription("New playlist description from Batch 5 restassure");
		requestPlayList.set_public(true);
		
		PlayList responsePlayList = given()
		.spec(requestSpecification)
		.body(requestPlayList)
		.when()
		.post("/users/"+userID+"/playlists")
		.then()
		.spec(responseSpecification)
		.contentType(ContentType.JSON)
		.statusCode(201)
		.and()
		.extract()
		.response()
		.as(PlayList.class);
		
		assertThat(responsePlayList.getName(), equalTo(requestPlayList.getName()));
		assertThat(responsePlayList.getDescription(), equalTo(requestPlayList.getDescription()));
		assertThat(responsePlayList.get_public(), equalTo(requestPlayList.get_public()));
	}
	
	@Test(priority = 2)
	public void validateGetPlayListByPassingPlayListID() {
		
		PlayList requestPlayList = new PlayList();
		requestPlayList.setName("Update Playlist from Batch 5 restassure");
		requestPlayList.setDescription("Update playlist description from Batch 5 restassure");
		requestPlayList.set_public(true);
		
		
		PlayList responsePlayList = given()
		.spec(requestSpecification)
		.when()
		.get("/playlists/"+playListID)
		.then()
		.spec(responseSpecification)
		.contentType(ContentType.JSON)
		.statusCode(200)
		.and()
		.extract()
		.response().as(PlayList.class);
		
		assertThat(responsePlayList.getName(), equalTo(requestPlayList.getName()));
		assertThat(responsePlayList.getDescription(), equalTo(requestPlayList.getDescription()));
		assertThat(responsePlayList.get_public(), equalTo(requestPlayList.get_public()));
	}
	
	@Test(priority = 3)
	public void validateUpdatePlayListByPassingPlayListID() {
		PlayList requestPlayList = new PlayList();
		requestPlayList.setName("Update Playlist from Batch 5 restassure");
		requestPlayList.setDescription("Update playlist description from Batch 5 restassure");
		requestPlayList.set_public(true);
		
		given()
		.spec(requestSpecification)
		.body(requestPlayList)
		.when()
		.put("/playlists/"+playListID)
		.then()
		.spec(responseSpecification)
		.statusCode(200);
	}
	

	@Test(priority = 4)
	public void validateCreatePlayListWithNoMandatoryField() {
		PlayList requestPlayList = new PlayList();
		requestPlayList.setName("");
		requestPlayList.setDescription("Update playlist description from Batch 5 restassure");
		requestPlayList.set_public(true);
		
		Error ResponseError =  given()
		.spec(requestSpecification)
		.body(requestPlayList)
		.when()
		.post("/users/"+userID+"/playlists")
		.then()
		.spec(responseSpecification)
		.statusCode(400)
		.and()
		.extract()
		.response()
		.as(Error.class);
		
		assertThat(ResponseError.getError().getStatus(), equalTo(400));
		assertThat(ResponseError.getError().getMessage(), equalTo("Missing required field: name"));
	}
	
	
	
	
	
	
	
	

}
