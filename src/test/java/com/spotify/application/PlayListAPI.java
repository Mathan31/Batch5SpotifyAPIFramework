package com.spotify.application;


import com.spotify.pojo.PlayList;
import com.util.PropertyFileUtil;
import com.wrapper.api.RestAssureHTTPMethods;

import io.restassured.response.Response;
import static com.spotify.route.Rounting.*;
import static com.spotify.base.SpecBuilder.propFileName;
public class PlayListAPI {
	
	public static String userID = PropertyFileUtil.readDataFromPropertyFile(propFileName, "user_id");
	
	public static Response createPlayList(PlayList requestPlayList) {
		return RestAssureHTTPMethods.post(requestPlayList, USER+"/"+userID+PLAYLIST);
	}
	
	public static Response getPlayListBasedOnPlayListID(String playListId) {
		return RestAssureHTTPMethods.get(PLAYLIST+"/"+playListId);
	}
	
	
	public static Response updatePlayListBasedOnPlayListID(PlayList requestPlayList,String playListId) {
		return RestAssureHTTPMethods.update(requestPlayList, PLAYLIST+"/"+playListId);
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
