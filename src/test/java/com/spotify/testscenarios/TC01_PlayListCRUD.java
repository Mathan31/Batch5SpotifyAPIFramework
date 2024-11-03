package com.spotify.testscenarios;

import static com.spotify.base.SpecBuilder.getRequestSpec;
import static com.spotify.base.SpecBuilder.getResponseSpec;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import org.testng.annotations.Test;

import com.library.FakerDataFactory;
import com.spotify.application.PlayListAPI;
import com.spotify.pojo.Error;
import com.spotify.pojo.PlayList;
import com.util.PropertyFileUtil;
import static com.spotify.base.SpecBuilder.propFileName;

import io.restassured.response.Response;


public class TC01_PlayListCRUD {
	
	
	public static String playListID = PropertyFileUtil.readDataFromPropertyFile(propFileName, "playlist_id");
	
	@Test(priority = 1)
	public void validateCreatePlayList() {
		
		PlayList requestPlayList = new PlayList();
		requestPlayList.setName(FakerDataFactory.getFirstName());
		requestPlayList.setDescription(FakerDataFactory.getCompanyName());
		requestPlayList.set_public(true);
		
		Response response = PlayListAPI.createPlayList(requestPlayList);
		assertThat(response.statusCode(), equalTo(201));
		PlayList responsePlayList = response.as(PlayList.class);
		
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
		
		Response response = PlayListAPI.getPlayListBasedOnPlayListID(playListID);
		PlayList responsePlayList = response.as(PlayList.class);
		
		assertThat(responsePlayList.getName(), equalTo(requestPlayList.getName()));
		assertThat(responsePlayList.getDescription(), equalTo(requestPlayList.getDescription()));
		assertThat(responsePlayList.get_public(), equalTo(requestPlayList.get_public()));
	}
	
	@Test(priority = 3)
	public void validateUpdatePlayListByPassingPlayListID() {
		PlayList requestPlayList = new PlayList();
		requestPlayList.setName(FakerDataFactory.getFirstName());
		requestPlayList.setDescription(FakerDataFactory.getAddress());
		requestPlayList.set_public(true);
		
		Response response = PlayListAPI.updatePlayListBasedOnPlayListID(requestPlayList, playListID);
		
		assertThat(response.statusCode(), equalTo(200));
	}
	

	@Test(priority = 4)
	public void validateCreatePlayListWithNoMandatoryField() {
		PlayList requestPlayList = new PlayList();
		requestPlayList.setName("");
		requestPlayList.setDescription(FakerDataFactory.getAddress());
		requestPlayList.set_public(true);
		
		Response response = PlayListAPI.createPlayList(requestPlayList);
		
		Error ResponseError =  response.as(Error.class);
		
		assertThat(ResponseError.getError().getStatus(), equalTo(400));
		assertThat(ResponseError.getError().getMessage(), equalTo("Missing required field: name"));
	}
	
}
