package com.mop.qa.pageobject;

import java.util.HashMap;

import com.mop.qa.Utilities.JSONParser;
import com.mop.qa.Utilities.ReadDataSheet;
import com.mop.qa.Utilities.SendGetPost;
import com.mop.qa.testbase.PageBase;
import com.relevantcodes.extentreports.LogStatus;

/**
 * Class repository for Home screen properties
 * 
 * @author 557743
 *
 */
public class APICallsResponse extends PageBase {
	public APICallsResponse() {
		super();
	}

	String URL = null;
	String Response;
	String appKey;
	String secretKey;
	CommonFunctions common = new CommonFunctions();
	HashMap<String, String> excelValueMap = new HashMap<String, String>();
	HashMap<String, String> actualValueMap;
	ReadDataSheet rd = new ReadDataSheet();

	public void getAnvackKeys(String config, String Env, String appKey) throws Exception {
		if (Env.equalsIgnoreCase("Stage")) {
			common.selectConfig(config, Env);
			URL = "https://ws-cloudpath-" + Env + ".media.nbcuni.com/cpc/ws/services/config?key=" + appKey + "&version=";
			Response = SendGetPost.sendGet(URL);
			test.log(LogStatus.INFO, "Config File" + config + "Response");
			test.log(LogStatus.INFO, "====================================");
			test.log(LogStatus.INFO, Response);
			test.log(LogStatus.INFO, "======" + config + "    Anvack Keys Details ========");
			test.log(LogStatus.INFO, "Actual VOD Anvack Keys" + JSONParser.getVODAnvackKey(URL, Response));
			actualValueMap = (HashMap<String, String>) JSONParser.getVODAnvackKey(URL, Response);
			appKey = rd.getValue("AnvackKey-VOD", config, "ExpectedAppKey");
			secretKey = rd.getValue("AnvackKey-VOD", config, "ExpectedSecretKey");
			System.out.println("Actual" + actualValueMap);
			excelValueMap.put("appKey", appKey);
			excelValueMap.put("secretKey", secretKey);
			System.out.println("Expected" + excelValueMap);
			compareTwoMaps(excelValueMap, actualValueMap);
		} else {
			common.selectConfig(config, Env);
			URL = "https://ws-cloudpath-" + Env + ".media.nbcuni.com/cpc/ws/services/config?key=" + appKey + "&version=";
			Response = SendGetPost.sendGet(URL);
			test.log(LogStatus.INFO, "Config File" + config + "Response");
			test.log(LogStatus.INFO, "====================================");
			test.log(LogStatus.INFO, Response);
			test.log(LogStatus.INFO, "======" + config + "Anvack Keys Details ========");
			test.log(LogStatus.INFO, "" + JSONParser.getVODAnvackKey(URL, Response));
			System.out.println(URL);
		}
	}
}
