package com.mop.qa.pageobject;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mop.qa.Utilities.CharlesController;
import com.mop.qa.Utilities.CharlesIntegration;
import com.mop.qa.Utilities.MobileExtractor;
import com.mop.qa.testbase.PageBase;
import com.relevantcodes.extentreports.LogStatus;

/**
 * Class repository for Home screen properties
 * 
 * @author 557743
 *
 */
public class GetEpisodeDetailsWithAPI extends PageBase {
	public GetEpisodeDetailsWithAPI() {
		super();
	}

	public ArrayList<String> getEpisodeNames(String URL) throws Exception {
		
		OkHttpClient client = new OkHttpClient();
		System.out.println(URL);
		Request request = new Request.Builder().url(URL).get().addHeader("api_key", "vmr3trpf7hwtp7pkrg6tpqv9").addHeader("accept", "application/vnd.api+json; ext=\"park/derivatives\"")
				.addHeader("content-type", "application/vnd.api+json").addHeader("cache-control", "no-cache").addHeader("postman-token", "b182138b-0bf2-056a-f1a3-2330efa87349").build();
		Response response = client.newCall(request).execute();
		String showDetails = response.body().string();
		// ;
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<String, Object>();
		// convert JSON string to Map
		map = objectMapper.readValue(showDetails, new TypeReference<Map<String, Object>>() {
		});
		@SuppressWarnings("unchecked")
		ArrayList<Map<String, Object>> data = (ArrayList<Map<String, Object>>) map.get("data");

		ArrayList<String> episodeNames = new ArrayList<String>();
		int i = 1;
		for (Map<String, Object> show : data) {
			Map<String, Object> attributes = (Map<String, Object>) show.get("attributes");
			String episodeName = (String) attributes.get("title");
			episodeNames.add(episodeName);
			i++;
		}
		test.log(LogStatus.INFO,"Entire JSOn Response"+'\n'+showDetails);
		
		test.log(LogStatus.INFO,"================== After Parsing JSON===============");
		test.log(LogStatus.INFO, "EpisodeDetais:"+episodeNames);		
		return episodeNames;
	}
}
