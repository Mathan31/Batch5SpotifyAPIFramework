package com.spotify.base;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static com.spotify.route.Rounting.*;

import com.util.PropertyFileUtil;

public class SpecBuilder {
	
	public RequestSpecification requestSpecification;
	public ResponseSpecification responseSpecification;
	public static String propFileName = "environment";
	public static String baseURI = PropertyFileUtil.readDataFromPropertyFile(propFileName, "uri");
	public static String access_token = TokenManager.renewToken();
	
	public static RequestSpecification getRequestSpec() {
		return new RequestSpecBuilder()
		.addHeader("Authorization", "Bearer "+access_token)
		.setContentType(ContentType.JSON)
		.setAccept(ContentType.JSON)
		.setBaseUri(baseURI)
		.setBasePath(BASE_PATH)
		.log(LogDetail.ALL)
		.build();
	}
	
	public static ResponseSpecification getResponseSpec() {
		return new ResponseSpecBuilder()
		.log(LogDetail.ALL)
		.build();
	}

}
