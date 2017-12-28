package com.mop.qa.Utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.regex.Pattern;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mop.qa.pageobject.CommonFunctions;
import com.mop.qa.testbase.PageBase;
import com.relevantcodes.extentreports.LogStatus;

import de.sstoehr.harreader.HarReader;
import de.sstoehr.harreader.HarReaderException;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.core.har.HarLog;

public class Comscore extends PageBase {
	static ArrayList<String> HBUrls = new ArrayList<String>();
	ArrayList<String> comscoreUrls = new ArrayList<String>();
	ArrayList<String> configURLs = new ArrayList<String>();
	ArrayList<String> nielsonUrls = new ArrayList<String>();
	static ArrayList<String> HBUrlsTimeStamp = new ArrayList<String>();
	ArrayList<String> comscoreUrlsTimeStamp = new ArrayList<String>();
	ArrayList<String> adBeaconsUrlsTimeStamp = new ArrayList<String>();
	ArrayList<String> configURLsTimeStamp = new ArrayList<String>();
	ArrayList<String> nielsonUrlsTimeStamp = new ArrayList<String>();
	ArrayList<String> feedUrls = new ArrayList<String>();
	ArrayList<String> feedUrlsTimeStamp = new ArrayList<String>();
	static ArrayList<HashMap<String, String>> seriesOfEvents;

	public static void validateAnalytics(ArrayList<String> comscoreUrls, String remove, String HbSheetName, String HBEventType, String HBEventVal) throws Exception {
		System.out.println("im in comscore");
		String correctString;
		String queryString;
		String pattern;
		ArrayList<String> comscoreUrlsModified = new ArrayList<String>();
		seriesOfEvents = new ArrayList<HashMap<String, String>>();
		int j;
		for (j = 0; j < comscoreUrls.size(); j++) {
			queryString = comscoreUrls.get(j).replace(remove, "");
			System.out.println(queryString);
			comscoreUrlsModified.add(queryString);
		}
		for (String url : comscoreUrlsModified) {
			System.out.println("========================START=============================");
			// System.out.println(comscoreUrlsModified);
			HashMap<String, String> KeyValues = new HashMap<String, String>();
			System.out.println(comscoreUrlsModified);
			String key = url;
			pattern = "(?<!&)&(?!&)";
			// List<String> HB = Arrays.asList(key.replaceAll("\\s",
			// "").split("&"));
			List<String> HB = Arrays.asList(key.replaceAll("\\s", "").split(pattern));
			for (int k = 0; k < HB.size() - 1; k++) {
				// System.out.println(HB.get(k)+'\n');
				List<String> parValue = Arrays.asList(HB.get(k).replaceAll("\\s", "").split("="));
				correctString = stringCheck(parValue.get(1));
				System.out.println(parValue.get(0) + ":" + correctString);
				KeyValues.put(parValue.get(0), correctString);
			}
			HashMap<String, String> excelValueMap = new HashMap<String, String>();
			// System.out.println(getColumValues("Expected",
			// "Comscore").size());
			// System.out.println(parameterNames);
			ArrayList<String> parameterNames = null;
			ArrayList<String> parameterValues = null;
			for (int k = 1; k <= getColumValues("Parameter", HbSheetName).size() - 1; k++) {
				excelValueMap.put(getColumValues("Parameter", HbSheetName).get(k), getColumValues("Expected", HbSheetName).get(k));
			}
			/*
			 * System.out.println(
			 * "===================================Excel captured============="
			 * ); System.out.println(excelValueMap.size());
			 * System.out.println(excelValueMap); System.out.println(
			 * "===================================Dynamically captured============="
			 * ); System.out.println(KeyValues);
			 */
			seriesOfEvents.add(KeyValues);
			System.out.println("**********************************************" + KeyValues.get("s:event:type") + "*****************************");
			if (KeyValues.get(HBEventType).equals(HBEventVal)) {
				// ////test.log(LogStatus.INFO,
				// "<b><font color=\"purple\">Parameter" + "---------" +
				// "Expected" + "---------" + "Actual" + "---------" +
				// "Status</b>");
				for (int i = 0; i < excelValueMap.size(); i++) {
					// Comparing Parameter From Charles and Parameter from excel
					if (KeyValues.containsKey(excelValueMap.keySet().toArray()[i])) {
						if (excelValueMap.get(excelValueMap.keySet().toArray()[i]).equals(KeyValues.get(excelValueMap.keySet().toArray()[i]))) {
							System.out.println("PASS");
							System.out.println("Parameter:" + excelValueMap.keySet().toArray()[i] + "Expected:" + excelValueMap.get(excelValueMap.keySet().toArray()[i]) + "Actual:" + KeyValues.get(excelValueMap.keySet().toArray()[i]));
							// //////test.log(LogStatus.INFO,
							// "<font color=\"black\">" +
							// excelValueMap.keySet().toArray()[i] +
							// "--------->" +
							// excelValueMap.get(excelValueMap.keySet().toArray()[i])
							// + "" + "--------->" +
							// "</font><font color=\"green\">" +
							// KeyValues.get(excelValueMap.keySet().toArray()[i])
							// + "--------->" + "PASS</b>");
						} else {
							System.out.println("==========FAIL=========");
							System.out.println("Parameter:" + excelValueMap.keySet().toArray()[i] + "Expected:" + excelValueMap.get(excelValueMap.keySet().toArray()[i]) + "Actual:" + KeyValues.get(excelValueMap.keySet().toArray()[i]));
							// ////test.log(LogStatus.FAIL, "<font
							// color=\"black\">"
							// + excelValueMap.keySet().toArray()[i] +
							// "--------->" +
							// excelValueMap.get(excelValueMap.keySet().toArray()[i])
							// + "--------->" + "</font><font color=\"red\">" +
							// KeyValues.get(excelValueMap.keySet().toArray()[i])
							// + "--------->" + "FAIL</b>");
						}
					} else {
						System.out.println("Parameter" + excelValueMap.keySet().toArray()[i] + "is missing");
						// ////test.log(LogStatus.FAIL, "<font color=\"black\">"
						// +
						// excelValueMap.keySet().toArray()[i] + "--------->" +
						// excelValueMap.get(excelValueMap.keySet().toArray()[i])
						// + "--------->" + "</font><font color=\"red\">" +
						// excelValueMap.keySet().toArray()[i] +
						// " Parameter is missing" + "--------->" +
						// "FAIL</b>");
						// ////test.log(LogStatus.FAIL, "Parameter" +
						// excelValueMap.keySet().toArray()[i] + "is missing");
					}
				}
			}
			// ////test.log(LogStatus.INFO,
			// "===================================Excel
			// captured=============");
			// ////test.log(LogStatus.INFO, "" + excelValueMap);
			// ////test.log(LogStatus.INFO,
			// "===================================Dynamically
			// captured=============");
			// ////test.log(LogStatus.INFO, "" + KeyValues);
			System.out.println("========================END=============================");
		}
		seriesOfEvents(seriesOfEvents);
	}

	public static  ArrayList<String> harReader(String Path) throws HarReaderException {
		String hbUrl = "http://nbcume.hb.omtrdc.net/?";
		String comscoreUrl = "https://sb.scorecardresearch.com/p2?";
		String adBeaconUrl = "http://29773.s.fwmrm.net/";
		String nielsonUrl = "https://secure-dcr-cert.imrworldwide.com";
		String feedUrl = "https://feed.theplatform.com/f/HNK2IC/nbcd_app_adstitch_v3_prod?byGUID=";
		
		HarReader harReader = new HarReader();
		de.sstoehr.harreader.model.Har har = harReader.readFromFile(new File(Path));
		de.sstoehr.harreader.model.HarLog log = har.getLog();
		List<de.sstoehr.harreader.model.HarEntry> entries = har.getLog().getEntries();
		System.out.println(entries);
		for (de.sstoehr.harreader.model.HarEntry entry : entries) {
			System.out.println(entry.getRequest().getUrl());
			if (entry.getRequest().getUrl().contains(hbUrl)) {
				HBUrls.add(entry.getRequest().getUrl());
				HBUrlsTimeStamp.add(entry.getStartedDateTime().toString());
			}
			
			// test.log(LogStatus.INFO, entry.getRequest().getUrl());
		}
		System.out.println(HBUrls.size());
		
		for(String hburl:HBUrls){
			System.out.println(hburl);
		}
		return HBUrls;
	}

	public static void seriesOfEvents(ArrayList<HashMap<String, String>> seriesOfEvents) {
		for (HashMap<String, String> eventType : seriesOfEvents) {
			System.out.println(eventType.get("s:event:type"));
			/*
			 * //////test.log(LogStatus.INFO,
			 * "=========================Series of Events Fired================="
			 * ); //////test.log(LogStatus.INFO,eventType.get("s:event:type"));
			 */
		}
	}

	public static String stringCheck(String data) {
		data = data.replace("%20", " ");
		data = data.replace("%21", "!");
		data = data.replace("%22", "\"");
		data = data.replace("%23", "#");
		data = data.replace("%24", "$");
		data = data.replace("%25", "%");
		data = data.replace("%26", "&");
		data = data.replace("%27", "\'");
		data = data.replace("%28", "(");
		data = data.replace("%29", ")");
		data = data.replace("%2A", "*");
		data = data.replace("%2B", "+");
		data = data.replace("%2C", ",");
		data = data.replace("%2D", "-");
		data = data.replace("%2E", ".");
		data = data.replace("%2F", "/");
		data = data.replace("%30", "0");
		data = data.replace("%31", "1");
		data = data.replace("%32", "2");
		data = data.replace("%33", "3");
		data = data.replace("%34", "4");
		data = data.replace("%35", "5");
		data = data.replace("%36", "6");
		data = data.replace("%37", "7");
		data = data.replace("%38", "8");
		data = data.replace("%39", "9");
		data = data.replace("%3A", ":");
		data = data.replace("%3B", ";");
		data = data.replace("%3C", "<");
		data = data.replace("%3D", "=");
		data = data.replace("%3E", ">");
		data = data.replace("%3F", "?");
		data = data.replace("%40", "@");
		data = data.replace("%41", "A");
		data = data.replace("%42", "B");
		data = data.replace("%43", "C");
		data = data.replace("%44", "D");
		data = data.replace("%45", "E");
		data = data.replace("%46", "F");
		data = data.replace("%47", "G");
		data = data.replace("%48", "H");
		data = data.replace("%49", "I");
		data = data.replace("%4A", "J");
		data = data.replace("%4B", "K");
		data = data.replace("%4C", "L");
		data = data.replace("%4D", "M");
		data = data.replace("%4E", "N");
		data = data.replace("%4F", "O");
		data = data.replace("%50", "P");
		data = data.replace("%51", "Q");
		data = data.replace("%52", "R");
		data = data.replace("%53", "S");
		data = data.replace("%54", "T");
		data = data.replace("%55", "U");
		data = data.replace("%56", "V");
		data = data.replace("%57", "W");
		data = data.replace("%58", "X");
		data = data.replace("%59", "Y");
		data = data.replace("%5A", "Z");
		data = data.replace("%5B", "[");
		data = data.replace("%5C", "\\");
		data = data.replace("%5D", "]");
		data = data.replace("%5E", "^");
		data = data.replace("%5F", "_");
		data = data.replace("%60", "`");
		data = data.replace("%61", "a");
		data = data.replace("%62", "b");
		data = data.replace("%63", "c");
		data = data.replace("%64", "d");
		data = data.replace("%65", "e");
		data = data.replace("%66", "f");
		data = data.replace("%67", "g");
		data = data.replace("%68", "h");
		data = data.replace("%69", "i");
		data = data.replace("%6A", "j");
		data = data.replace("%6B", "k");
		data = data.replace("%6C", "l");
		data = data.replace("%6D", "m");
		data = data.replace("%6E", "n");
		data = data.replace("%6F", "o");
		data = data.replace("%70", "p");
		data = data.replace("%71", "q");
		data = data.replace("%72", "r");
		data = data.replace("%73", "s");
		data = data.replace("%74", "t");
		data = data.replace("%75", "u");
		data = data.replace("%76", "v");
		data = data.replace("%77", "w");
		data = data.replace("%78", "x");
		data = data.replace("%79", "y");
		data = data.replace("%7A", "z");
		data = data.replace("%7B", "{");
		data = data.replace("%7C", "|");
		data = data.replace("%7D", "}");
		data = data.replace("%7E", "~");
		data = data.replace("%80", "`");
		return data;
	}

	public static void patternCheck() {
		String a = "https://ws-cloudpath-stage.media.nbcuni.com/cpc/ws/services/config?key=cloudpath_nbcd_ios_prod_release_gracenote_n4bfcinslip4ou2a9txj0q49wa4cpmghpn9nfa86&version=";
		String b = "https://ws-cloudpath.media.nbcuni.com/cpc/ws/services/config?key=cloudpath_nbcd_ios_prod_release_gracenote_n4bfcinslip4ou2a9txj0q49wa4cpmghpn9nfa86&version=";
		String c = "https://d2gtdlj3w402rf.cloudfront.net/anvacks/XE5XpDni9xLBF6C02OBLFLb3vRhlP5zz.json?t=1492676440";
		String d = "https://sp.auth.adobe.com/adobe-services/config/nbcentertainment";
		String e = "http://nbcume.hb.omtrdc.net/settings/nbc.xml?r=1492676446181";
		String f = "https://d2gtdlj3w402rf.cloudfront.net/anvacks/XE5XpDni9xLBF6C02OBLFLb3vRhlP5zz.json?t=1492676446";
		String g = "https://sb.scorecardresearch.com";
		String h = "http://29773.s.fwmrm.net";
		String pattern = "^(https:|http:)//(ws-cloudpath.*|sp.auth.*|nbcume.hb.*|nbcume.sc.*|tkx.*|ma99-r.*|play-.*|sb.scorecardresearch.*|tve-nbc.*|29773.*)|secure-dcr.*";
		/*
		 * Config file :
		 * https://ws-cloudpath-<<stage/Prod/dev>>.media.nbcuni.com Nielsen
		 * Tracking Server : https://secure-dcr-cert.imrworldwide.com ComScore
		 * Tracking Server : https://sb.scorecardresearch.com Ads tracking :
		 * http://29773.s.fwmrm.net Heartbeat Tracking :
		 * http://nbcume.hb.omtrdc.net Omniture Tracking :
		 * http://nbcume.sc.omtrdc.net VOD Session ID :
		 * http://vod-prod1.nbc.anvato.net Live Metadata :
		 * http://tkx-prod.nbc.anvato.net Live Anvato Session Id:
		 * http://play-prod1.nbc.anvato.net Geo Endpoint Url:
		 * https://ws-cloudpath-qa.media.nbcuni.com Akamai QoS Url:
		 * http://ma99-r.analytics.edgesuite.net TV Rating Images:
		 * http://tve-nbc.nbcuni.com Live Anvato Session Id:
		 * http://play-prod1.nbc.anvato.net
		 */
		System.out.println(h.matches(pattern));
		ArrayList<String> regxUrls = new ArrayList<String>();
		regxUrls.add(a);
		regxUrls.add(b);
		regxUrls.add(c);
		regxUrls.add(d);
		regxUrls.add(e);
		regxUrls.add(f);
		regxUrls.add(g);
		regxUrls.add(h);
		int i = 0;
		int size = regxUrls.size();
		while (regxUrls.size() > 0) {
			if (regxUrls.get(i).matches(pattern)) {
				System.out.println(regxUrls.get(i));
			} else {
				System.out.println("Not matched" + regxUrls.get(i));
			}
			size--;
			i++;
		}
	}

	public static HashMap<String, Map<String, String>> validateAdiOSConsole(String path, String platformName) throws IOException {
		// TODO Auto-generated method stub
		Map<String, String> addynamicValuesMap = new HashMap<String, String>();
		Map<String, String> dynamicValuesMap = new HashMap<String, String>();
		String s = "";
		String adString;
		String separator;
		String adSeparator;
		String adTypeSeparator;
		int noOfAds;
		BufferedReader in = new BufferedReader(new FileReader(path));
		StringBuffer str = new StringBuffer();
		String line;
		while ((line = in.readLine()) != null) {
			str.append(" ");
			str.append(line);
		}
		in.close();
		String st = str.toString();
		System.out.println(st);
		if (platformName.equalsIgnoreCase("iOS")) {
			adString = "CPAdObserver CPAdObserverAdBreakInstance";
			separator = ";";
			adSeparator = "CPAdIdKey =";
			adTypeSeparator = "CPBreakTypeKey";
			// System.out.println(st);
			String[] adpart = st.split("CPAdPositionsKey =");
			String adpart1 = adpart[1].substring(0, adpart[1].indexOf(";"));
			int j = 1;
			for (int i = 0; i < adpart1.length(); i++) {
				if (adpart1.charAt(i) == ',') {
					j++;
				}
			}
			System.out.println("Total Number of AdPod = " + (j));
		} else {
			adString = "CPAdObserver : CPAdObserverAdBreakInstance";
			separator = ",";
			adSeparator = "CPAdIdKey=";
			adTypeSeparator = "CPAdBreakTypeKey";
		}
		System.out.println("===================Ad Details From Console=============");
		String[] adpartText = st.split(adString);
		System.out.println("===================Ad Details=============");
		ArrayList<String> adTypes = new ArrayList<>(); // preRoll1,....,midRoll1,midRoll2,....
		HashMap<String, String> dataMap;
		HashMap<String, Map<String, String>> adsMap = new HashMap<>();
		int preRollcount = 1;
		int midCount = 1;
		System.out.println(adpartText.length);
		for (int i = 1; i < adpartText.length; i++) {
			if (adpartText[i].contains(adSeparator)) {
				dataMap = new HashMap<>();
				String indAdData = adpartText[i].replaceAll("\\{", "").trim();
				String finalData = indAdData.split("}")[0];
				String[] data = finalData.split(separator);
				if (platformName.equalsIgnoreCase("iOS")) {
					noOfAds = data.length - 1;
				} else {
					noOfAds = data.length;
				}
				for (int temp = 0; temp < noOfAds; temp++) {
					String[] keyValues = data[temp].split("=");
					String key = keyValues[0].trim();
					String value = keyValues[1].trim();
					// //test.log(LogStatus.INFO, key + ":" + value);
					System.out.println(key + ":" + value);
					if (key.equalsIgnoreCase("CPAdIdKey")) {
						dataMap.put("s:asset:ad_id", value);
					} else if (key.equalsIgnoreCase("CPAdDurationKey")) {
						dataMap.put("l:asset:ad_length", value);
					} else if (key.equalsIgnoreCase("CPAdIndexKey")) {
						dataMap.put("s:asset:pod_position", value);
					}
					if (key.equalsIgnoreCase(adTypeSeparator)) {
						if (value.contains("Preroll")) {
							adTypes.add("Preroll" + preRollcount);
							dataMap.put("s:asset:pod_name", "pre-roll");
							preRollcount++;
						} else {
							adTypes.add("Midroll" + midCount);
							dataMap.put("s:asset:pod_name", "mid-roll");
							midCount++;
						}
					}
				}
				// //test.log(LogStatus.INFO,
				// "*************************************");
				/*
				 * for (String dynKey : dynamicValuesMap.keySet()) {
				 * dataMap.put(dynKey, dynamicValuesMap.get(dynKey)); }
				 */
				adsMap.put(adTypes.get(adTypes.size() - 1), dataMap);
			}
		}
		System.out.println(adsMap);
		return adsMap;
	}

	public static HashMap<String, Map<String, String>> validateAdAndroidConsole(String path) throws IOException {
		// TODO Auto-generated method stub
		Map<String, String> dynamicValuesMap = new HashMap<String, String>();
		Map<String, String> addynamicValuesMap = new HashMap<String, String>();
		StringBuffer str = new StringBuffer();
		// BufferedReader in = new BufferedReader(new
		// FileReader("ConsoleOutputnull.txt"));
		BufferedReader in = new BufferedReader(new FileReader(path));
		String line;
		while ((line = in.readLine()) != null) {
			str.append(" ");
			str.append(line);
		}
		in.close();
		String st = str.toString();
		String s = st;
		System.out.println("============AD details===========");
		/* Added Code */
		String[] adpartText = st.split("CPAdObserver : CPAdObserverAdBreakInstance");
		System.out.println(adpartText.length);
		System.out.println(adpartText[0]);
		ArrayList<String> adTypes = new ArrayList<>(); // preRoll1,....,midRoll1,midRoll2,....
		HashMap<String, String> dataMap;
		HashMap<String, Map<String, String>> adsMap = new HashMap<>();
		int preRollcount = 1;
		int midCount = 1;
		System.out.println(adpartText.length);
		System.out.println(adpartText[1]);
		for (int i = 1; i < adpartText.length; i++) {
			if (adpartText[i].contains("CPAdIdKey=")) {
				System.out.println("=========================");
				dataMap = new HashMap<>();
				String indAdData = adpartText[i].replaceAll("\\{", "").trim();
				String finalData = indAdData.split("}")[0];
				String[] data = finalData.split(",");
				System.out.println(data.length);
				for (int temp = 0; temp < data.length; temp++) {
					String[] keyValues = data[temp].split("=");
					String key = keyValues[0].trim();
					String value = keyValues[1].trim();
					System.out.println(key + ":" + value);
					// //test.log(LogStatus.INFO, key + ":" + value);
					if (key.equalsIgnoreCase("CPAdIdKey")) {
						dataMap.put("s:asset:ad_id", value);
					} else if (key.equalsIgnoreCase("CPAdDurationKey")) {
						dataMap.put("l:asset:ad_length", value);
					} else if (key.equalsIgnoreCase("CPAdIndexKey")) {
						dataMap.put("s:asset:pod_position", value);
					}
					if (key.equalsIgnoreCase("CPAdBreakTypeKey")) {
						if (value.contains("Preroll")) {
							adTypes.add("Preroll" + preRollcount);
							dataMap.put("s:asset:pod_name", "pre-roll");
							preRollcount++;
						} else {
							adTypes.add("Midroll" + midCount);
							dataMap.put("s:asset:pod_name", "mid-roll");
							midCount++;
						}
					}
				}
				adsMap.put(adTypes.get(adTypes.size() - 1), dataMap);
				System.out.println("=========================");
			}
		}
		// System.out.println(adsMap);
		return adsMap;
	}

	public static HashMap<String, String> feedResponse(String url) throws Exception {
		HashMap<String, String> assetValuesFeedMap = new HashMap<String, String>();
		String Response = SendGetPost.sendGet(url);
		System.out.println(Response);
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<String, Object>();
		// convert JSON string to Map
		map = objectMapper.readValue(Response, new TypeReference<Map<String, Object>>() {
		});
		// System.out.println(map.size());
		Map<String, String> dynamicValuesMap = new HashMap<String, String>();
		@SuppressWarnings("unchecked")
		ArrayList<Map<String, Object>> data = (ArrayList<Map<String, Object>>) map.get("entries");
		ArrayList<Map<String, Object>> seriesName;
		for (Map<String, Object> show : data) {
			if (show.get("pl1$fullEpisode").toString().equalsIgnoreCase("true")) {
				dynamicValuesMap.put("s:stream:type", "VOD Episode");
			} else {
				dynamicValuesMap.put("s:stream:type", "VOD Clip");
			}
			if (show.get("pl1$entitlement").toString().equalsIgnoreCase("free")) {
				dynamicValuesMap.put("s:meta:videostatus", "Unrestricted");
			} else {
				dynamicValuesMap.put("s:meta:videostatus", "Restricted");
			}
			// System.out.println(show.get("pl1$episodeNumber"));
			// System.out.println(show.get("pl1$seasonNumber"));
			// System.out.println(show.get("guid"));
			dynamicValuesMap.put("s:meta:videoguid", show.get("guid").toString());
			// Comscore event
			dynamicValuesMap.put("ns_st_ci", show.get("guid").toString());
			assetValuesFeedMap.put("VOD ID", show.get("guid").toString());
			dynamicValuesMap.put("s:meta:videodaypart", show.get("pl1$dayPart").toString());
			dynamicValuesMap.put("s:meta:videoepnumber", show.get("pl1$episodeNumber").toString());
			// Comscore events
			dynamicValuesMap.put("ns_st_en", show.get("pl1$episodeNumber").toString());
			assetValuesFeedMap.put("VOD Episode Number", show.get("pl1$episodeNumber").toString());
			dynamicValuesMap.put("s:meta:videotitle", show.get("title").toString());
			dynamicValuesMap.put("ns_st_pr", show.get("title").toString());
			dynamicValuesMap.put("ns_st_ep", show.get("title").toString());
			assetValuesFeedMap.put("VOD Episode", show.get("title").toString());
			dynamicValuesMap.put("s:asset:name", show.get("title").toString());
			dynamicValuesMap.put("VOD Episode Number", show.get("pl1$episodeNumber").toString());
			dynamicValuesMap.put("s:meta:videoseason", show.get("pl1$seasonNumber").toString());
			dynamicValuesMap.put("ns_st_sn", show.get("pl1$seasonNumber").toString());
			assetValuesFeedMap.put("VOD Season", show.get("pl1$seasonNumber").toString());
			// System.out.println(show.get("title"));
			seriesName = (ArrayList<Map<String, Object>>) show.get("media$categories");
			for (Map<String, Object> series : seriesName) {
				System.out.println(series.get("media$name"));
				dynamicValuesMap.put("s:meta:videoprogram", series.get("media$name").toString().replace("Series/", ""));
				// Comscore Events
				dynamicValuesMap.put("c6", series.get("media$name").toString().replace("Series/", ""));
				assetValuesFeedMap.put("VOD Series", series.get("media$name").toString().replace("Series/", ""));
			}
			ArrayList<Map<String, Object>> adPositions = new ArrayList<>();
			ArrayList<String> positions = new ArrayList<>();
			adPositions = (ArrayList<Map<String, Object>>) show.get("plmedia$chapters");
			/*
			 * adPots = new HashMap<>(); for (Map<String, Object> series :
			 * adPositions) { String pos =
			 * String.valueOf(series.get("plmedia$startTime"));
			 * positions.add(pos); adPots.put("seekTo", positions); }
			 */
		}
		for (String parameter : assetValuesFeedMap.keySet()) {
			System.out.println(parameter + ":" + assetValuesFeedMap.get(parameter));
		}
		/*
		 * for (String parameter : assetValuesFeedMap.keySet()) {
		 * System.out.println(parameter + ":" +
		 * assetValuesFeedMap.get(parameter)); }
		 */
		return (HashMap<String, String>) dynamicValuesMap;
	}

	// Comscore Validation
	public static void validateComscore(ArrayList<String> comscoreUrls, String remove, String comscoreSheetName, String comscoreEventType, String comscoreEventVal, String feedurl, String path) throws Exception {
		System.out.println("im in Comscore Validation");
		String correctString;
		String queryString;
		String pattern = "(?<!&)&(?!&)";
		int counter = 0;
		ArrayList<String> comscoreUrlsModified = new ArrayList<String>();
		seriesOfEvents = new ArrayList<HashMap<String, String>>();
		int j;
		// Map is to get what are all the dynamic values in excel
		HashMap<String, String> dynamicValuesMap;
		// Map is to get Dynamic values from Feed
		HashMap<String, String> dynamicValuesFeedMap;
		// Map is for static values capture in excel
		HashMap<String, String> staticValuesMap;
		// Excel Capture
		HashMap<String, String> excelValueMap = new HashMap<String, String>();
		// Map is to get the dynamic adValues from console
		for (int k = 1; k <= getColumValues("Parameter", comscoreSheetName).size() - 1; k++) {
			excelValueMap.put(getColumValues("Parameter", comscoreSheetName).get(k), getColumValues("Expected", comscoreSheetName).get(k));
		}
		dynamicValuesMap = new HashMap<String, String>();
		dynamicValuesFeedMap = feedResponse(feedurl);
		staticValuesMap = new HashMap<String, String>();
		ArrayList<String> parameterNames = null;
		ArrayList<String> parameterValues = null;
		for (String expected : excelValueMap.keySet()) {
			if (excelValueMap.get(expected).equalsIgnoreCase("Dyn")) {
				dynamicValuesMap.put(expected, excelValueMap.get(expected).trim());
			} else {
				staticValuesMap.put(expected, excelValueMap.get(expected).trim());
			}
		}
		// Put All Dynamic values which are captured into excelvalues
		for (String key : dynamicValuesMap.keySet()) {
			if (dynamicValuesFeedMap.containsKey(key)) {
				dynamicValuesMap.put(key, dynamicValuesFeedMap.get(key));
			} else {
				// //test.log(LogStatus.INFO,"This"+key+"is not captured either
				// from Feed or Console");
				System.out.println("This" + key + "is not captured either from Feed or Console");
			}
		}
		/*
		 * System.out.println("********" + dynamicValuesMap + "************");
		 * System.out.println("********" + staticValuesMap + "************");
		 * System.out.println("********" + dynamicValuesMap + "************");
		 */
		for (String key : excelValueMap.keySet()) {
			if (dynamicValuesFeedMap.containsKey(key)) {
				excelValueMap.put(key, dynamicValuesFeedMap.get(key));
			} else if (staticValuesMap.containsKey(key)) {
				excelValueMap.put(key, staticValuesMap.get(key));
			} else {
				System.out.println("Not there in static and Dynamic");
			}
		}
		System.out.println("********" + excelValueMap + "************");
		for (j = 0; j < comscoreUrls.size(); j++) {
			queryString = comscoreUrls.get(j).replace(remove, "");
			System.out.println(queryString);
			comscoreUrlsModified.add(queryString);
		}
		for (String url : comscoreUrlsModified) {
			System.out.println("========================START=============================");
			HashMap<String, String> KeyValues = new HashMap<String, String>();
			System.out.println(comscoreUrlsModified);
			String key = url;
			List<String> HB = Arrays.asList(key.replaceAll("\\s", "").split(pattern));
			for (int k = 0; k < HB.size() - 1; k++) {
				List<String> parValue = Arrays.asList(HB.get(k).replaceAll("\\s", "").split("="));
				if (parValue.size() <= 1)
					continue;
				correctString = stringCheck(parValue.get(1));
				System.out.println(parValue.get(0) + ":" + correctString);
				KeyValues.put(parValue.get(0), correctString);
			}
			// seriesOfEvents.add(KeyValues);
			// System.out.println("**********************************************"
			// + KeyValues.get("s:event:type") +
			// "*****************************");
			System.out.println("im befoire validation");
			System.out.println(comscoreEventType + ":" + comscoreEventVal);
			System.out.println(KeyValues.containsKey(comscoreEventType));
			if (KeyValues.containsKey(comscoreEventType)) {
				if (KeyValues.get(comscoreEventType).equals(comscoreEventVal)) {
					System.out.println("im in validation");
					// test.log(LogStatus.INFO, "<b><font color=\"blue\">" +
					// "Validating Event--->" + KeyValues.get("s:event:type") +
					// "*-->*" + counter + "Event Fired On---->" +
					// "HBUrlsTimeStamp.get(counter)" + "</font></b>");
					// test.log(LogStatus.INFO,
					// "===================================Excel
					// captured=============");
					// test.log(LogStatus.INFO, "" + excelValueMap);
					// test.log(LogStatus.INFO,
					// "===================================Dynamically
					// captured=============");
					// test.log(LogStatus.INFO, "" + KeyValues);
					// test.log(LogStatus.INFO, "<b><font
					// color=\"purple\">Parameter" + "---------" + "Expected" +
					// "---------" + "Actual" + "---------" + "Status</b>");
					for (int i = 0; i < excelValueMap.size(); i++) {
						// Comparing Parameter From HAR file entries and
						// Parameter
						// from excel
						if (KeyValues.containsKey(excelValueMap.keySet().toArray()[i])) {
							if (excelValueMap.get(excelValueMap.keySet().toArray()[i]).equals(KeyValues.get(excelValueMap.keySet().toArray()[i]))) {
								System.out.println("PASS");
								System.out.println("Parameter:" + excelValueMap.keySet().toArray()[i] + "Expected:" + excelValueMap.get(excelValueMap.keySet().toArray()[i]) + "Actual:" + KeyValues.get(excelValueMap.keySet().toArray()[i]));
								// test.log(LogStatus.INFO, "<font
								// color=\"black\">" +
								// excelValueMap.keySet().toArray()[i] +
								// "--------->" +
								// excelValueMap.get(excelValueMap.keySet().toArray()[i])
								// + "" + "--------->" + "</font><font
								// color=\"green\">" +
								// KeyValues.get(excelValueMap.keySet().toArray()[i])
								// + "--------->" + "PASS</b>");
							} else {
								System.out.println("==========FAIL=========");
								System.out.println("Parameter:" + excelValueMap.keySet().toArray()[i] + "Expected:" + excelValueMap.get(excelValueMap.keySet().toArray()[i]) + "Actual:" + KeyValues.get(excelValueMap.keySet().toArray()[i]));
								// test.log(LogStatus.FAIL, "<font
								// color=\"black\">" +
								// excelValueMap.keySet().toArray()[i] +
								// "--------->" +
								// excelValueMap.get(excelValueMap.keySet().toArray()[i])
								// + "--------->" + "</font><font
								// color=\"red\">" +
								// KeyValues.get(excelValueMap.keySet().toArray()[i])
								// + "--------->" + "FAIL</b>");
							}
						} else {
							System.out.println("Parameter" + excelValueMap.keySet().toArray()[i] + "is missing");
							// test.log(LogStatus.FAIL, "<font color=\"black\">"
							// + excelValueMap.keySet().toArray()[i] +
							// "--------->" +
							// excelValueMap.get(excelValueMap.keySet().toArray()[i])
							// + "--------->" + "</font><font color=\"red\">" +
							// excelValueMap.keySet().toArray()[i] + " Parameter
							// is missing" + "--------->" + "FAIL</b>");
							// test.log(LogStatus.FAIL, "Parameter" +
							// excelValueMap.keySet().toArray()[i] + "is
							// missing");
						}
					}
					counter++;
				}
			}
			System.out.println("========================END=============================");
		}
		// seriesOfEvents(seriesOfEvents, HBUrlsTimeStamp);
	}

	public static void main(String[] args) {
		try {
			String a = "https://sb.scorecardresearch.com/p2?c1=19&c2=6035083&ns_ap_an=NBC&ns_ap_pn=ios&ns_ap_pv=9.3.5&c12=3DCEB761A59BBE609101A9BA9FE45E3A-cs62&ns_ak=AoytJSOp0Br5j97X35r7idgPWRhBWueGflKeQxwJ4b6tV4yy04aKgq8fB58G51Q4p53JHrlB7sLQeYCZt8I5zmB8Uf%2BMYh6vajpYoy7rhtLFDQToRXuXIgu%2BvGpMcFpXREPhZKW396n%2BUTQ3KRi70PxJuo3P0hsto6zEPfClt64%3D&name=View%20Controller&ns_ap_ec=2&ns_ap_ev=hidden&ns_ap_device=iPhone7%2C1&ns_ap_id=1495821111795&ns_ap_bi=com.nbcuni.cloudpath&ns_ap_pfm=ios&ns_ap_pfv=9.3.5&ns_ap_ver=3.2.6.1&ns_ap_sv=3.1509.15&ns_type=hidden&ns_radio=wifi&ns_nc=1&ns_st_sv=4.1509.15&ns_st_it=r&ns_st_id=1495821248360_1&ns_st_ec=1&ns_st_sp=1&ns_st_sq=1&ns_st_cn=1&ns_st_ev=play&ns_st_po=0&ns_st_cl=15766&ns_st_pb=1&ns_st_mp=ios_puppet&ns_st_mv=4.1509.15&ns_st_pn=1&ns_st_tp=1&ns_st_ad=1&ns_st_ci=3099592&ns_ap_res=320x568&ns_ap_cs=40&ns_ap_lang=en-IN&ns_ap_ar=arm64v8&ns_ts=1495821285004&ns_st_bt=0&ns_st_bp=0&ns_st_br=0&ns_st_ub=0&ns_st_pr=The%20Clairvoyants%20and%20Jon%20Dorenbos%3A%20Live%20Performance%20feat.%20Paula%20Abdul&ns_st_sn=%2Anull&ns_st_en=%2Anull&ns_st_ep=23&ns_st_ct=va21&ns_st_st=%2Anull&ns_st_pu=NBC&c3=TVE&c4=%2Anull&c6=America%27s%20Got%20Talent";
			String b = "https://sb.scorecardresearch.com/p2?c1=19&c2=6035083&ns_ap_an=NBC&ns_ap_pn=ios&ns_ap_pv=9.3.5&c12=3DCEB761A59BBE609101A9BA9FE45E3A-cs62&ns_ak=AoytJSOp0Br5j97X35r7idgPWRhBWueGflKeQxwJ4b6tV4yy04aKgq8fB58G51Q4p53JHrlB7sLQeYCZt8I5zmB8Uf%2BMYh6vajpYoy7rhtLFDQToRXuXIgu%2BvGpMcFpXREPhZKW396n%2BUTQ3KRi70PxJuo3P0hsto6zEPfClt64%3D&name=View%20Controller&ns_ap_ec=3&ns_ap_ev=hidden&ns_ap_device=iPhone7%2C1&ns_ap_id=1495821111795&ns_ap_bi=com.nbcuni.cloudpath&ns_ap_pfm=ios&ns_ap_pfv=9.3.5&ns_ap_ver=3.2.6.1&ns_ap_sv=3.1509.15&ns_type=hidden&ns_radio=wifi&ns_nc=1&ns_st_sv=4.1509.15&ns_st_it=r&ns_st_id=1495821248360_1&ns_st_ec=2&ns_st_sp=1&ns_st_cn=1&ns_st_ev=hb&ns_st_po=10097&ns_st_cl=15766&ns_st_hc=1&ns_st_mp=ios_puppet&ns_st_mv=4.1509.15&ns_st_pn=1&ns_st_tp=1&ns_st_pt=10098&ns_st_pa=10098&ns_st_ad=1&ns_st_ci=3099592&ns_ap_res=320x568&ns_ap_cs=40&ns_ap_lang=en-IN&ns_ap_ar=arm64v8&ns_ts=1495821295101&ns_st_pe=1&ns_st_bt=0&ns_st_bp=0&ns_st_pc=0&ns_st_pp=0&ns_st_br=0&ns_st_ub=0&ns_st_pr=The%20Clairvoyants%20and%20Jon%20Dorenbos%3A%20Live%20Performance%20feat.%20Paula%20Abdul&ns_st_sn=%2Anull&ns_st_en=%2Anull&ns_st_ep=23&ns_st_ct=va21&ns_st_st=%2Anull&ns_st_pu=NBC&c3=TVE&c4=%2Anull&c6=America%27s%20Got%20Talent";
			String c = "https://sb.scorecardresearch.com/p2?c1=19&c2=6035083&ns_ap_an=NBC&ns_ap_pn=ios&ns_ap_pv=9.3.5&c12=3DCEB761A59BBE609101A9BA9FE45E3A-cs62&ns_ak=AoytJSOp0Br5j97X35r7idgPWRhBWueGflKeQxwJ4b6tV4yy04aKgq8fB58G51Q4p53JHrlB7sLQeYCZt8I5zmB8Uf%2BMYh6vajpYoy7rhtLFDQToRXuXIgu%2BvGpMcFpXREPhZKW396n%2BUTQ3KRi70PxJuo3P0hsto6zEPfClt64%3D&name=View%20Controller&ns_ap_ec=4&ns_ap_ev=hidden&ns_ap_device=iPhone7%2C1&ns_ap_id=1495821111795&ns_ap_bi=com.nbcuni.cloudpath&ns_ap_pfm=ios&ns_ap_pfv=9.3.5&ns_ap_ver=3.2.6.1&ns_ap_sv=3.1509.15&ns_type=hidden&ns_radio=wifi&ns_nc=1&ns_st_sv=4.1509.15&ns_st_it=r&ns_st_id=1495821248360_1&ns_st_ec=2&ns_st_sp=1&ns_st_cn=1&ns_st_ev=end&ns_st_po=14926&ns_st_cl=15766&ns_st_mp=ios_puppet&ns_st_mv=4.1509.15&ns_st_pn=1&ns_st_tp=1&ns_st_pt=14925&ns_st_pa=14925&ns_st_ad=1&ns_st_ci=3099592&ns_ap_res=320x568&ns_ap_cs=40&ns_ap_lang=en-IN&ns_ap_ar=arm64v8&ns_ts=1495821299929&ns_st_bt=0&ns_st_bp=0&ns_st_pc=0&ns_st_pp=0&ns_st_br=0&ns_st_ub=0&ns_st_pr=The%20Clairvoyants%20and%20Jon%20Dorenbos%3A%20Live%20Performance%20feat.%20Paula%20Abdul&ns_st_sn=%2Anull&ns_st_en=%2Anull&ns_st_ep=23&ns_st_ct=va21&ns_st_st=%2Anull&ns_st_pu=NBC&c3=TVE&c4=%2Anull&c6=America%27s%20Got%20Talent";
			String d = "https://sb.scorecardresearch.com/p2?c1=19&c2=6035083&ns_ap_an=NBC&ns_ap_pn=ios&ns_ap_pv=9.3.5&c12=3DCEB761A59BBE609101A9BA9FE45E3A-cs62&ns_ak=AoytJSOp0Br5j97X35r7idgPWRhBWueGflKeQxwJ4b6tV4yy04aKgq8fB58G51Q4p53JHrlB7sLQeYCZt8I5zmB8Uf%2BMYh6vajpYoy7rhtLFDQToRXuXIgu%2BvGpMcFpXREPhZKW396n%2BUTQ3KRi70PxJuo3P0hsto6zEPfClt64%3D&name=View%20Controller&ns_ap_ec=5&ns_ap_ev=hidden&ns_ap_device=iPhone7%2C1&ns_ap_id=1495821111795&ns_ap_bi=com.nbcuni.cloudpath&ns_ap_pfm=ios&ns_ap_pfv=9.3.5&ns_ap_ver=3.2.6.1&ns_ap_sv=3.1509.15&ns_type=hidden&ns_radio=wifi&ns_nc=1&ns_st_sv=4.1509.15&ns_st_it=r&ns_st_id=1495821248360_1&ns_st_ec=3&ns_st_sp=1&ns_st_sq=1&ns_st_cn=2&ns_st_ev=play&ns_st_po=0&ns_st_cl=15766&ns_st_mp=ios_puppet&ns_st_mv=4.1509.15&ns_st_pn=1&ns_st_tp=1&ns_st_ad=1&ns_st_ci=3099592&ns_ap_res=320x568&ns_ap_cs=40&ns_ap_lang=en-IN&ns_ap_ar=arm64v8&ns_ts=1495821299931&ns_st_bt=0&ns_st_bp=0&ns_st_br=0&ns_st_ub=0&ns_st_pr=The%20Clairvoyants%20and%20Jon%20Dorenbos%3A%20Live%20Performance%20feat.%20Paula%20Abdul&ns_st_sn=%2Anull&ns_st_en=%2Anull&ns_st_ep=23&ns_st_ct=va21&ns_st_st=%2Anull&ns_st_pu=NBC&c3=TVE&c4=%2Anull&c6=America%27s%20Got%20Talent";
			String e = "https://sb.scorecardresearch.com/p2?c1=19&c2=6035083&ns_ap_an=NBC&ns_ap_pn=ios&ns_ap_pv=9.3.5&c12=3DCEB761A59BBE609101A9BA9FE45E3A-cs62&ns_ak=AoytJSOp0Br5j97X35r7idgPWRhBWueGflKeQxwJ4b6tV4yy04aKgq8fB58G51Q4p53JHrlB7sLQeYCZt8I5zmB8Uf%2BMYh6vajpYoy7rhtLFDQToRXuXIgu%2BvGpMcFpXREPhZKW396n%2BUTQ3KRi70PxJuo3P0hsto6zEPfClt64%3D&name=View%20Controller&ns_ap_ec=6&ns_ap_ev=hidden&ns_ap_device=iPhone7%2C1&ns_ap_id=1495821111795&ns_ap_bi=com.nbcuni.cloudpath&ns_ap_pfm=ios&ns_ap_pfv=9.3.5&ns_ap_ver=3.2.6.1&ns_ap_sv=3.1509.15&ns_type=hidden&ns_radio=wifi&ns_nc=1&ns_st_sv=4.1509.15&ns_st_it=r&ns_st_id=1495821248360_1&ns_st_ec=4&ns_st_sp=1&ns_st_cn=2&ns_st_ev=end&ns_st_po=1302&ns_st_cl=15766&ns_st_mp=ios_puppet&ns_st_mv=4.1509.15&ns_st_pn=1&ns_st_tp=1&ns_st_pt=1300&ns_st_pa=16225&ns_st_ad=1&ns_st_ci=3099592&ns_ap_res=320x568&ns_ap_cs=40&ns_ap_lang=en-IN&ns_ap_ar=arm64v8&ns_ts=1495821301231&ns_st_bt=0&ns_st_bp=0&ns_st_pc=0&ns_st_pp=0&ns_st_br=0&ns_st_ub=0&ns_st_pr=The%20Clairvoyants%20and%20Jon%20Dorenbos%3A%20Live%20Performance%20feat.%20Paula%20Abdul&ns_st_sn=%2Anull&ns_st_en=%2Anull&ns_st_ep=23&ns_st_ct=va21&ns_st_st=%2Anull&ns_st_pu=NBC&c3=TVE&c4=%2Anull&c6=America%27s%20Got%20Talent";
			String f = "https://sb.scorecardresearch.com/p2?c1=19&c2=6035083&ns_ap_an=NBC&ns_ap_pn=ios&ns_ap_pv=9.3.5&c12=3DCEB761A59BBE609101A9BA9FE45E3A-cs62&ns_ak=AoytJSOp0Br5j97X35r7idgPWRhBWueGflKeQxwJ4b6tV4yy04aKgq8fB58G51Q4p53JHrlB7sLQeYCZt8I5zmB8Uf%2BMYh6vajpYoy7rhtLFDQToRXuXIgu%2BvGpMcFpXREPhZKW396n%2BUTQ3KRi70PxJuo3P0hsto6zEPfClt64%3D&name=View%20Controller&ns_ap_ec=7&ns_ap_ev=hidden&ns_ap_device=iPhone7%2C1&ns_ap_id=1495821111795&ns_ap_bi=com.nbcuni.cloudpath&ns_ap_pfm=ios&ns_ap_pfv=9.3.5&ns_ap_ver=3.2.6.1&ns_ap_sv=3.1509.15&ns_type=hidden&ns_radio=wifi&ns_nc=1&ns_st_sv=4.1509.15&ns_st_it=r&ns_st_id=1495821248360_1&ns_st_ec=5&ns_st_sp=1&ns_st_sq=1&ns_st_cn=3&ns_st_ev=play&ns_st_po=0&ns_st_cl=412000&ns_st_mp=ios_puppet&ns_st_mv=4.1509.15&ns_st_pn=1&ns_st_tp=0&ns_st_ci=3099592&ns_ap_res=320x568&ns_ap_cs=40&ns_ap_lang=en-IN&ns_ap_ar=arm64v8&ns_ts=1495821301231&ns_st_bt=0&ns_st_bp=0&ns_st_br=0&ns_st_ub=0&ns_st_pr=The%20Clairvoyants%20and%20Jon%20Dorenbos%3A%20Live%20Performance%20feat.%20Paula%20Abdul&ns_st_sn=%2Anull&ns_st_en=%2Anull&ns_st_ep=23&ns_st_ct=vc12&ns_st_st=%2Anull&ns_st_pu=NBC&c3=TVE&c4=%2Anull&c6=America%27s%20Got%20Talent";
			String g = "https://sb.scorecardresearch.com/p2?c1=19&c2=6035083&ns_ap_an=NBC&ns_ap_pn=ios&ns_ap_pv=9.3.5&c12=3DCEB761A59BBE609101A9BA9FE45E3A-cs62&ns_ak=AoytJSOp0Br5j97X35r7idgPWRhBWueGflKeQxwJ4b6tV4yy04aKgq8fB58G51Q4p53JHrlB7sLQeYCZt8I5zmB8Uf%2BMYh6vajpYoy7rhtLFDQToRXuXIgu%2BvGpMcFpXREPhZKW396n%2BUTQ3KRi70PxJuo3P0hsto6zEPfClt64%3D&name=View%20Controller&ns_ap_ec=8&ns_ap_ev=hidden&ns_ap_device=iPhone7%2C1&ns_ap_id=1495821111795&ns_ap_bi=com.nbcuni.cloudpath&ns_ap_pfm=ios&ns_ap_pfv=9.3.5&ns_ap_ver=3.2.6.1&ns_ap_sv=3.1509.15&ns_type=hidden&ns_radio=wifi&ns_nc=1&ns_st_sv=4.1509.15&ns_st_it=r&ns_st_id=1495821248360_1&ns_st_ec=6&ns_st_sp=1&ns_st_cn=3&ns_st_ev=hb&ns_st_po=17804&ns_st_cl=412000&ns_st_hc=1&ns_st_mp=ios_puppet&ns_st_mv=4.1509.15&ns_st_pn=1&ns_st_tp=0&ns_st_pt=17811&ns_st_pa=34036&ns_st_ci=3099592&ns_ap_res=320x568&ns_ap_cs=40&ns_ap_lang=en-IN&ns_ap_ar=arm64v8&ns_ts=1495821319035&ns_st_pe=1&ns_st_bt=0&ns_st_bp=0&ns_st_pc=0&ns_st_pp=0&ns_st_br=0&ns_st_ub=0&ns_st_pr=The%20Clairvoyants%20and%20Jon%20Dorenbos%3A%20Live%20Performance%20feat.%20Paula%20Abdul&ns_st_sn=%2Anull&ns_st_en=%2Anull&ns_st_ep=23&ns_st_ct=vc12&ns_st_st=%2Anull&ns_st_pu=NBC&c3=TVE&c4=%2Anull&c6=America%27s%20Got%20Talent";
			String h = "http://nbcume.hb.omtrdc.net/?h:sc:ssl=1&s:sc:rsid=nbcutvemobileapp%2Cnbcuiphoneappsnbcnetworkbu%2Cnbcunetworkbu&s:sc:tracking_server=nbcume.sc.omtrdc.net&s:sp:sdk=1.6.6&l:sp:hb_api_lvl=3&s:sp:channel=On-Domain&s:sp:ovp=ovp&s:sp:player_name=Anvato%20v4.1%20iOS%20Player&s:sp:hb_version=ios-n-1.6.6.139-e99930&s:event:sid=1495870163239906227304&l:event:prev_ts=-1&l:event:ts=1495870163240&l:event:duration=0&s:event:type=start&l:event:playhead=0&l:asset:length=412&s:asset:video_id=videoid&s:asset:publisher=A8AB776A5245B4220A490D44%40AdobeOrg&s:asset:type=main&s:asset:name=videoname&s:stream:type=videostreamtype&l:stream:dropped_frames=0&l:stream:startup_time=0&l:stream:fps=30&l:stream:bitrate=0&s:meta:a.media.channel=On-Domain&s:meta:videoguid=videoguid&s:meta:videoapp=NBC&s:meta:videoprogram=videoprogram&s:meta:videoscreen=Full&s:meta:videonetwork=NBC&s:meta:videoplayertech=Mobile%20Operating%20System&s:meta:videoepnumber=videoepnumber&s:meta:videodaypart=videodaypart&s:meta:a.contentType=VOD%20Clip&s:meta:videoday=Saturday&s:meta:videosubcat2=videosubcat2&s:meta:videoplatform=Mobile&s:meta:videotitle=The%20Clairvoyants%20and%20Jon%20Dorenbos%3A%20Live%20Performance%20feat.%20Paula%20Abdul&s:meta:videohour=00&s:meta:videosubcat1=videosubcat1&s:meta:videoairdate=videoairdate&s:meta:videocallsign=unknown&s:meta:videostatus=Unrestricted&s:meta:videoseason=videoseason&s:meta:videodate=05%2F27%2F2017&s:meta:videominute=00%3A29&s:meta:videomvpd=unauthenticated&s:meta:videoinitiate=Manual";
			String i = "http://nbcume.hb.omtrdc.net/?h:sc:ssl=1&s:sc:rsid=nbcutvemobileapp%2Cnbcuiphoneappsnbcnetworkbu%2Cnbcunetworkbu&s:sc:tracking_server=nbcume.sc.omtrdc.net&s:user:mid=04841348033340730493708752777488899218&s:sp:sdk=1.6.6&l:sp:hb_api_lvl=3&s:sp:channel=On-Domain&s:sp:ovp=ovp&s:sp:player_name=Anvato%20v4.1%20iOS%20Player&s:sp:hb_version=ios-n-1.6.6.139-e99930&s:event:sid=1495870163239906227304&l:event:prev_ts=-1&l:event:ts=1495870165273&l:event:duration=0&s:event:type=aa_start&l:event:playhead=0&l:asset:length=412&s:asset:video_id=videoid&s:asset:publisher=A8AB776A5245B4220A490D44%40AdobeOrg&s:asset:type=main&s:asset:name=videoname&s:stream:type=videostreamtype&l:stream:dropped_frames=0&l:stream:startup_time=0&l:stream:fps=30&l:stream:bitrate=0&";
			String j = "http://nbcume.hb.omtrdc.net/?h:sc:ssl=1&s:sc:rsid=nbcutvemobileapp%2Cnbcuiphoneappsnbcnetworkbu%2Cnbcunetworkbu&s:sc:tracking_server=nbcume.sc.omtrdc.net&s:user:mid=04841348033340730493708752777488899218&s:sp:sdk=1.6.6&l:sp:hb_api_lvl=3&s:sp:channel=On-Domain&s:sp:ovp=ovp&s:sp:player_name=Anvato%20v4.1%20iOS%20Player&s:sp:hb_version=ios-n-1.6.6.139-e99930&s:event:sid=149587016968725102389&l:event:prev_ts=-1&l:event:ts=1495870169687&l:event:duration=0&s:event:type=start&l:event:playhead=0&l:asset:length=412&s:asset:video_id=3099592&s:asset:publisher=A8AB776A5245B4220A490D44%40AdobeOrg&s:asset:type=main&s:asset:name=The%20Clairvoyants%20and%20Jon%20Dorenbos%3A%20Live%20Performance%20feat.%20Paula%20Abdul&s:stream:type=VOD%20Clip&l:stream:dropped_frames=0&l:stream:startup_time=0&l:stream:fps=30&l:stream:bitrate=0&s:meta:a.media.channel=On-Domain&s:meta:videoguid=3099592&s:meta:videoapp=NBC&s:meta:videoprogram=America%27s%20Got%20Talent&s:meta:videoscreen=Full&s:meta:videonetwork=NBC&s:meta:videoplayertech=Mobile%20Operating%20System&s:meta:videoepnumber=23&s:meta:videodaypart=Primetime&s:meta:a.contentType=VOD%20Clip&s:meta:videoday=Saturday&s:meta:videosubcat2=N%2FA&s:meta:videoplatform=Mobile&s:meta:videotitle=The%20Clairvoyants%20and%20Jon%20Dorenbos%3A%20Live%20Performance%20feat.%20Paula%20Abdul&s:meta:videohour=00&s:meta:videosubcat1=Reality&s:meta:videoairdate=09%2F14%2F2016&s:meta:videocallsign=unknown&s:meta:videostatus=Unrestricted&s:meta:videoseason=11&s:meta:videodate=05%2F27%2F2017&s:meta:videominute=00%3A29&s:meta:videomvpd=unauthenticated&s:meta:videoinitiate=Manual";
			String k = "http://nbcume.hb.omtrdc.net/?h:sc:ssl=1&s:sc:rsid=nbcutvemobileapp%2Cnbcuiphoneappsnbcnetworkbu%2Cnbcunetworkbu&s:sc:tracking_server=nbcume.sc.omtrdc.net&s:user:mid=04841348033340730493708752777488899218&s:sp:sdk=1.6.6&l:sp:hb_api_lvl=3&s:sp:channel=On-Domain&s:sp:ovp=ovp&s:sp:player_name=Anvato%20v4.1%20iOS%20Player&s:sp:hb_version=ios-n-1.6.6.139-e99930&s:event:sid=149587016968725102389&l:event:prev_ts=-1&l:event:ts=1495870171709&l:event:duration=0&s:event:type=aa_start&l:event:playhead=0&l:asset:length=412&s:asset:video_id=3099592&s:asset:publisher=A8AB776A5245B4220A490D44%40AdobeOrg&s:asset:type=main&s:asset:name=The%20Clairvoyants%20and%20Jon%20Dorenbos%3A%20Live%20Performance%20feat.%20Paula%20Abdul&s:stream:type=VOD%20Clip&l:stream:dropped_frames=0&l:stream:startup_time=0&l:stream:fps=30&l:stream:bitrate=0&";
			String l = "http://nbcume.hb.omtrdc.net/?h:sc:ssl=1&s:sc:rsid=nbcutvemobileapp%2Cnbcuiphoneappsnbcnetworkbu%2Cnbcunetworkbu&s:sc:tracking_server=nbcume.sc.omtrdc.net&s:user:mid=04841348033340730493708752777488899218&s:sp:sdk=1.6.6&l:sp:hb_api_lvl=3&s:sp:channel=On-Domain&s:sp:ovp=ovp&s:sp:player_name=Anvato%20v4.1%20iOS%20Player&s:sp:hb_version=ios-n-1.6.6.139-e99930&s:event:sid=149587016968725102389&l:event:prev_ts=1495870169687&l:event:ts=1495870179753&l:event:duration=10066&s:event:type=start&l:event:playhead=0&l:asset:length=412&s:asset:video_id=3099592&s:asset:publisher=A8AB776A5245B4220A490D44%40AdobeOrg&s:asset:type=main&s:asset:name=The%20Clairvoyants%20and%20Jon%20Dorenbos%3A%20Live%20Performance%20feat.%20Paula%20Abdul&s:stream:type=VOD%20Clip&l:stream:dropped_frames=0&l:stream:startup_time=10066&l:stream:fps=30&l:stream:bitrate=0&s:meta:a.media.channel=On-Domain&s:meta:videoguid=3099592&s:meta:videoapp=NBC&s:meta:videoprogram=America%27s%20Got%20Talent&s:meta:videoscreen=Full&s:meta:videonetwork=NBC&s:meta:videoplayertech=Mobile%20Operating%20System&s:meta:videoepnumber=23&s:meta:videodaypart=Primetime&s:meta:a.contentType=VOD%20Clip&s:meta:videoday=Saturday&s:meta:videosubcat2=N%2FA&s:meta:videoplatform=Mobile&s:meta:videotitle=The%20Clairvoyants%20and%20Jon%20Dorenbos%3A%20Live%20Performance%20feat.%20Paula%20Abdul&s:meta:videohour=00&s:meta:videosubcat1=Reality&s:meta:videoairdate=09%2F14%2F2016&s:meta:videocallsign=unknown&s:meta:videostatus=Unrestricted&s:meta:videoseason=11&s:meta:videodate=05%2F27%2F2017&s:meta:videominute=00%3A29&s:meta:videomvpd=unauthenticated&s:meta:videoinitiate=Manual";
			String m = "http://nbcume.hb.omtrdc.net/?h:sc:ssl=1&s:sc:rsid=nbcutvemobileapp%2Cnbcuiphoneappsnbcnetworkbu%2Cnbcunetworkbu&s:sc:tracking_server=nbcume.sc.omtrdc.net&s:user:mid=04841348033340730493708752777488899218&s:sp:sdk=1.6.6&l:sp:hb_api_lvl=3&s:sp:channel=On-Domain&s:sp:ovp=ovp&s:sp:player_name=Anvato%20v4.1%20iOS%20Player&s:sp:hb_version=ios-n-1.6.6.139-e99930&s:event:sid=149587016968725102389&l:event:prev_ts=-1&l:event:ts=1495870183686&l:event:duration=0&s:event:type=bitrate_change&l:event:playhead=0&l:asset:length=412&s:asset:video_id=3099592&s:asset:publisher=A8AB776A5245B4220A490D44%40AdobeOrg&s:asset:type=main&s:asset:name=The%20Clairvoyants%20and%20Jon%20Dorenbos%3A%20Live%20Performance%20feat.%20Paula%20Abdul&s:stream:type=VOD%20Clip&l:stream:dropped_frames=0&l:stream:startup_time=10066&l:stream:fps=30&l:stream:bitrate=552800&";
			String n = "http://nbcume.hb.omtrdc.net/?h:sc:ssl=1&s:sc:rsid=nbcutvemobileapp%2Cnbcuiphoneappsnbcnetworkbu%2Cnbcunetworkbu&s:sc:tracking_server=nbcume.sc.omtrdc.net&s:user:mid=04841348033340730493708752777488899218&s:sp:sdk=1.6.6&l:sp:hb_api_lvl=3&s:sp:channel=On-Domain&s:sp:ovp=ovp&s:sp:player_name=Anvato%20v4.1%20iOS%20Player&s:sp:hb_version=ios-n-1.6.6.139-e99930&s:event:sid=149587016968725102389&l:event:prev_ts=1495870179753&l:event:ts=1495870191074&l:event:duration=11321&s:event:type=start&l:event:playhead=0&l:asset:length=412&s:asset:video_id=3099592&s:asset:publisher=A8AB776A5245B4220A490D44%40AdobeOrg&s:asset:type=main&s:asset:name=The%20Clairvoyants%20and%20Jon%20Dorenbos%3A%20Live%20Performance%20feat.%20Paula%20Abdul&s:stream:type=VOD%20Clip&l:stream:dropped_frames=0&l:stream:startup_time=21387&l:stream:fps=30&l:stream:bitrate=0&s:meta:a.media.channel=On-Domain&s:meta:videoguid=3099592&s:meta:videoapp=NBC&s:meta:videoprogram=America%27s%20Got%20Talent&s:meta:videoscreen=Full&s:meta:videonetwork=NBC&s:meta:videoplayertech=Mobile%20Operating%20System&s:meta:videoepnumber=23&s:meta:videodaypart=Primetime&s:meta:a.contentType=VOD%20Clip&s:meta:videoday=Saturday&s:meta:videosubcat2=N%2FA&s:meta:videoplatform=Mobile&s:meta:videotitle=The%20Clairvoyants%20and%20Jon%20Dorenbos%3A%20Live%20Performance%20feat.%20Paula%20Abdul&s:meta:videohour=00&s:meta:videosubcat1=Reality&s:meta:videoairdate=09%2F14%2F2016&s:meta:videocallsign=unknown&s:meta:videostatus=Unrestricted&s:meta:videoseason=11&s:meta:videodate=05%2F27%2F2017&s:meta:videominute=00%3A29&s:meta:videomvpd=unauthenticated&s:meta:videoinitiate=Manual";
			String o = "http://nbcume.hb.omtrdc.net/?h:sc:ssl=1&s:sc:rsid=nbcutvemobileapp%2Cnbcuiphoneappsnbcnetworkbu%2Cnbcunetworkbu&s:sc:tracking_server=nbcume.sc.omtrdc.net&s:user:mid=04841348033340730493708752777488899218&s:sp:sdk=1.6.6&l:sp:hb_api_lvl=3&s:sp:channel=On-Domain&s:sp:ovp=ovp&s:sp:player_name=Anvato%20v4.1%20iOS%20Player&s:sp:hb_version=ios-n-1.6.6.139-e99930&s:event:sid=149587016968725102389&l:event:prev_ts=-1&l:event:ts=1495870191074&l:event:duration=0&s:event:type=start&l:event:playhead=0&l:asset:length=412&s:asset:ad_sid=1495870191073108883657&s:asset:pod_id=bf1f531f03a72a1c9812fd3ff27d55bb_1&l:asset:pod_offset=0&s:asset:resolver=Anvato%20v4.1%20iOS%20Player&s:asset:ad_name=N%2FA&s:asset:pod_name=pre-roll&l:asset:ad_length=15&s:asset:pod_position=1&s:asset:ad_id=18915084&s:asset:video_id=3099592&s:asset:publisher=A8AB776A5245B4220A490D44%40AdobeOrg&s:asset:type=ad&s:asset:name=The%20Clairvoyants%20and%20Jon%20Dorenbos%3A%20Live%20Performance%20feat.%20Paula%20Abdul&s:stream:type=VOD%20Clip&l:stream:dropped_frames=0&l:stream:startup_time=21387&l:stream:fps=30&l:stream:bitrate=552800&s:meta:videoseason=11&s:meta:videoguid=3099592&s:meta:videoapp=NBC&s:meta:videoprogram=America%27s%20Got%20Talent&s:meta:videoscreen=Full&s:meta:videonetwork=NBC&s:meta:videoplayertech=Mobile%20Operating%20System&s:meta:videoepnumber=23&s:meta:videodaypart=Primetime&s:meta:a.contentType=VOD%20Clip&s:meta:videoday=Saturday&s:meta:videosubcat2=N%2FA&s:meta:videoplatform=Mobile&s:meta:videotitle=The%20Clairvoyants%20and%20Jon%20Dorenbos%3A%20Live%20Performance%20feat.%20Paula%20Abdul&s:meta:videohour=00&s:meta:videosubcat1=Reality&s:meta:videoairdate=09%2F14%2F2016&s:meta:videocallsign=unknown&s:meta:videostatus=Unrestricted&s:meta:videodate=05%2F27%2F2017&s:meta:videominute=00%3A29&s:meta:videomvpd=unauthenticated&s:meta:videoinitiate=Manual&s:meta:a.media.channel=On-Domain";
			String p = "http://nbcume.hb.omtrdc.net/?h:sc:ssl=1&s:sc:rsid=nbcutvemobileapp%2Cnbcuiphoneappsnbcnetworkbu%2Cnbcunetworkbu&s:sc:tracking_server=nbcume.sc.omtrdc.net&s:user:mid=04841348033340730493708752777488899218&s:sp:sdk=1.6.6&l:sp:hb_api_lvl=3&s:sp:channel=On-Domain&s:sp:ovp=ovp&s:sp:player_name=Anvato%20v4.1%20iOS%20Player&s:sp:hb_version=ios-n-1.6.6.139-e99930&s:event:sid=149587016968725102389&l:event:prev_ts=-1&l:event:ts=1495870193109&l:event:duration=0&s:event:type=aa_ad_start&l:event:playhead=0&l:asset:length=412&s:asset:ad_sid=1495870191073108883657&s:asset:pod_id=bf1f531f03a72a1c9812fd3ff27d55bb_1&l:asset:pod_offset=0&s:asset:resolver=Anvato%20v4.1%20iOS%20Player&s:asset:ad_name=N%2FA&s:asset:pod_name=pre-roll&l:asset:ad_length=15&s:asset:pod_position=1&s:asset:ad_id=18915084&s:asset:video_id=3099592&s:asset:publisher=A8AB776A5245B4220A490D44%40AdobeOrg&s:asset:type=ad&s:asset:name=The%20Clairvoyants%20and%20Jon%20Dorenbos%3A%20Live%20Performance%20feat.%20Paula%20Abdul&s:stream:type=VOD%20Clip&l:stream:dropped_frames=0&l:stream:startup_time=21387&l:stream:fps=30&l:stream:bitrate=552800&";
			/*
			 * String h =
			 * "https://nbcume.hb.omtrdc.net/?s:sc:tracking_server=nbcume.sc.omtrdc.net&s:sc:rsid=nbcutvemobileapp%2Cnbcuiphoneappsnbcnetworkbu%2Cnbcunetworkbu&h:sc:ssl=0&&&s:sp:player_name=Anvato+Android+Player&s:sp:sdk=1.6.4&s:sp:ovp=ovp&l:sp:hb_api_lvl=3&s:sp:hb_version=android-n-1.6.4.27-21b000&s:sp:channel=On-Domain&s:event:sid=1495960262815455840156&l:event:ts=1495960262852&l:event:prev_ts=-1&l:event:playhead=0&l:event:duration=0&s:event:type=start&s:asset:name=The+Clairvoyants+and+Jon+Dorenbos%3A+Live+Performance+feat.+Paula+Abdul&s:asset:type=main&l:asset:length=412&s:asset:publisher=A8AB776A5245B4220A490D44%40AdobeOrg&s:asset:video_id=3099592&s:stream:type=VOD+Clip&l:stream:dropped_frames=0&l:stream:startup_time=14000&l:stream:fps=30&l:stream:bitrate=1048576&s:meta:a.nielsen.ctype=VIDEO&s:meta:videominute=01%3A31&s:meta:a.nielsen.sega=The+Clairvoyants+and+Jon+Dorenbos%3A+Live+Performance+feat.+Paula+Abdul&s:meta:a.nielsen.segb=segB&s:meta:videosubcat2=N%2FA&s:meta:videohour=01&s:meta:videoprogram=America%27s+Got+Talent&s:meta:videoepnumber=23&s:meta:videoscreen=Full&s:meta:videoguid=3099592&s:meta:videomvpd=Cablevision&s:meta:videodaypart=Primetime&s:meta:videotitle=The+Clairvoyants+and+Jon+Dorenbos%3A+Live+Performance+feat.+Paula+Abdul&s:meta:videoseason=11&s:meta:videoday=Sunday&s:meta:a.nielsen.segc=segC&s:meta:a.nielsen.appid=P1C377781-0BA4-4D6E-8111-0267BE0D2DC0&s:meta:videonetwork=NBC&s:meta:videoplatform=Mobile&s:meta:videodate=05%2F28%2F2017&s:meta:a.nielsen.program=America%27s+Got+Talent&s:meta:videoapp=NBC&s:meta:a.nielsen.admodel=2&s:meta:videoairdate=09%2F14%2F2016&s:meta:videostatus=Unrestricted&s:meta:videosubcat1=Reality&s:meta:a.nielsen.accmethod=1&s:meta:videocallsign=N%2FA&s:meta:videoinitiate=Manual&s:meta:a.nielsen.clientid=us-800148&s:meta:a.nielsen.vcid=c05&s:meta:videoplayertech=Mobile+Operating+System"
			 * ; String i =
			 * "https://nbcume.hb.omtrdc.net/?s:sc:tracking_server=nbcume.sc.omtrdc.net&s:sc:rsid=nbcutvemobileapp%2Cnbcuiphoneappsnbcnetworkbu%2Cnbcunetworkbu&h:sc:ssl=0&s:user:mid=59117565524229059683626022763783479790&s:user:aid=2C953B2385030746-400011854001057F&&s:sp:player_name=Anvato+Android+Player&s:sp:sdk=1.6.4&s:sp:ovp=ovp&l:sp:hb_api_lvl=3&s:sp:hb_version=android-n-1.6.4.27-21b000&s:sp:channel=On-Domain&s:event:sid=1495960262815455840156&l:event:ts=1495960262911&l:event:prev_ts=-1&l:event:playhead=0&l:event:duration=0&s:event:type=aa_start&s:asset:name=The+Clairvoyants+and+Jon+Dorenbos%3A+Live+Performance+feat.+Paula+Abdul&s:asset:type=main&l:asset:length=412&s:asset:publisher=A8AB776A5245B4220A490D44%40AdobeOrg&s:asset:video_id=3099592&s:stream:type=VOD+Clip&l:stream:dropped_frames=0&l:stream:startup_time=14000&l:stream:fps=30&l:stream:bitrate=1048576"
			 * ; String j =
			 * "https://nbcume.hb.omtrdc.net/?s:sc:tracking_server=nbcume.sc.omtrdc.net&s:sc:rsid=nbcutvemobileapp%2Cnbcuiphoneappsnbcnetworkbu%2Cnbcunetworkbu&h:sc:ssl=0&s:user:mid=59117565524229059683626022763783479790&s:user:aid=2C953B2385030746-400011854001057F&&s:sp:player_name=Anvato+Android+Player&s:sp:sdk=1.6.4&s:sp:ovp=ovp&l:sp:hb_api_lvl=3&s:sp:hb_version=android-n-1.6.4.27-21b000&s:sp:channel=On-Domain&s:event:sid=1495960262815455840156&l:event:ts=1495960266342&l:event:prev_ts=1495960262852&l:event:playhead=0&l:event:duration=3490&s:event:type=start&s:asset:name=The+Clairvoyants+and+Jon+Dorenbos%3A+Live+Performance+feat.+Paula+Abdul&s:asset:type=main&l:asset:length=412&s:asset:publisher=A8AB776A5245B4220A490D44%40AdobeOrg&s:asset:video_id=3099592&s:stream:type=VOD+Clip&l:stream:dropped_frames=0&l:stream:startup_time=14000&l:stream:fps=30&l:stream:bitrate=1048576&s:meta:a.nielsen.ctype=VIDEO&s:meta:videominute=01%3A31&s:meta:a.nielsen.sega=The+Clairvoyants+and+Jon+Dorenbos%3A+Live+Performance+feat.+Paula+Abdul&s:meta:a.nielsen.segb=segB&s:meta:videosubcat2=N%2FA&s:meta:videohour=01&s:meta:videoprogram=America%27s+Got+Talent&s:meta:videoepnumber=23&s:meta:videoscreen=Full&s:meta:videoguid=3099592&s:meta:videomvpd=Cablevision&s:meta:videodaypart=Primetime&s:meta:videotitle=The+Clairvoyants+and+Jon+Dorenbos%3A+Live+Performance+feat.+Paula+Abdul&s:meta:videoseason=11&s:meta:videoday=Sunday&s:meta:a.nielsen.segc=segC&s:meta:a.nielsen.appid=P1C377781-0BA4-4D6E-8111-0267BE0D2DC0&s:meta:videonetwork=NBC&s:meta:videoplatform=Mobile&s:meta:videodate=05%2F28%2F2017&s:meta:a.nielsen.program=America%27s+Got+Talent&s:meta:videoapp=NBC&s:meta:a.nielsen.admodel=2&s:meta:videoairdate=09%2F14%2F2016&s:meta:videostatus=Unrestricted&s:meta:videosubcat1=Reality&s:meta:a.nielsen.accmethod=1&s:meta:videocallsign=N%2FA&s:meta:videoinitiate=Manual&s:meta:a.nielsen.clientid=us-800148&s:meta:a.nielsen.vcid=c05&s:meta:videoplayertech=Mobile+Operating+System"
			 * ; String k =
			 * "https://nbcume.hb.omtrdc.net/?s:sc:tracking_server=nbcume.sc.omtrdc.net&s:sc:rsid=nbcutvemobileapp%2Cnbcuiphoneappsnbcnetworkbu%2Cnbcunetworkbu&h:sc:ssl=0&s:user:mid=59117565524229059683626022763783479790&s:user:aid=2C953B2385030746-400011854001057F&&s:sp:player_name=Anvato+Android+Player&s:sp:sdk=1.6.4&s:sp:ovp=ovp&l:sp:hb_api_lvl=3&s:sp:hb_version=android-n-1.6.4.27-21b000&s:sp:channel=On-Domain&s:event:sid=1495960262815455840156&l:event:ts=1495960266342&l:event:prev_ts=-1&l:event:playhead=0&l:event:duration=0&s:event:type=start&s:asset:name=The+Clairvoyants+and+Jon+Dorenbos%3A+Live+Performance+feat.+Paula+Abdul&l:asset:pod_offset=0&s:asset:ad_id=19032081&s:asset:pod_id=bf1f531f03a72a1c9812fd3ff27d55bb_1&s:asset:pod_position=1&s:asset:ad_sid=1495960266340271048029&s:asset:resolver=Anvato+Android+Player&l:asset:ad_length=30&s:asset:ad_name=N%2FA&s:asset:pod_name=preroll&s:asset:type=ad&l:asset:length=412&s:asset:publisher=A8AB776A5245B4220A490D44%40AdobeOrg&s:asset:video_id=3099592&s:stream:type=VOD+Clip&l:stream:dropped_frames=0&l:stream:startup_time=14000&l:stream:fps=30&l:stream:bitrate=1048576&s:meta:a.nielsen.ctype=VIDEO&s:meta:videominute=01%3A31&s:meta:a.nielsen.sega=The+Clairvoyants+and+Jon+Dorenbos%3A+Live+Performance+feat.+Paula+Abdul&s:meta:a.nielsen.segb=segB&s:meta:videosubcat2=N%2FA&s:meta:videohour=01&s:meta:videoprogram=America%27s+Got+Talent&s:meta:videoepnumber=23&s:meta:videoscreen=Full&s:meta:videoguid=3099592&s:meta:videomvpd=Cablevision&s:meta:videodaypart=Primetime&s:meta:videotitle=The+Clairvoyants+and+Jon+Dorenbos%3A+Live+Performance+feat.+Paula+Abdul&s:meta:videoseason=11&s:meta:videoday=Sunday&s:meta:a.nielsen.segc=segC&s:meta:a.nielsen.appid=P1C377781-0BA4-4D6E-8111-0267BE0D2DC0&s:meta:videonetwork=NBC&s:meta:videoplatform=Mobile&s:meta:videodate=05%2F28%2F2017&s:meta:a.nielsen.program=America%27s+Got+Talent&s:meta:videoapp=NBC&s:meta:a.nielsen.admodel=2&s:meta:videoairdate=09%2F14%2F2016&s:meta:videostatus=Unrestricted&s:meta:videosubcat1=Reality&s:meta:a.nielsen.accmethod=1&s:meta:videocallsign=N%2FA&s:meta:videoinitiate=Manual&s:meta:a.nielsen.clientid=us-800148&s:meta:a.nielsen.vcid=c05&s:meta:videoplayertech=Mobile+Operating+System"
			 * ; String l =
			 * "https://nbcume.hb.omtrdc.net/?s:sc:tracking_server=nbcume.sc.omtrdc.net&s:sc:rsid=nbcutvemobileapp%2Cnbcuiphoneappsnbcnetworkbu%2Cnbcunetworkbu&h:sc:ssl=0&s:user:mid=59117565524229059683626022763783479790&s:user:aid=2C953B2385030746-400011854001057F&&s:sp:player_name=Anvato+Android+Player&s:sp:sdk=1.6.4&s:sp:ovp=ovp&l:sp:hb_api_lvl=3&s:sp:hb_version=android-n-1.6.4.27-21b000&s:sp:channel=On-Domain&s:event:sid=1495960262815455840156&l:event:ts=1495960266369&l:event:prev_ts=-1&l:event:playhead=0&l:event:duration=0&s:event:type=aa_ad_start&s:asset:name=The+Clairvoyants+and+Jon+Dorenbos%3A+Live+Performance+feat.+Paula+Abdul&l:asset:pod_offset=0&s:asset:ad_id=19032081&s:asset:pod_id=bf1f531f03a72a1c9812fd3ff27d55bb_1&s:asset:pod_position=1&s:asset:ad_sid=1495960266340271048029&s:asset:resolver=Anvato+Android+Player&l:asset:ad_length=30&s:asset:ad_name=N%2FA&s:asset:pod_name=preroll&s:asset:type=ad&l:asset:length=412&s:asset:publisher=A8AB776A5245B4220A490D44%40AdobeOrg&s:asset:video_id=3099592&s:stream:type=VOD+Clip&l:stream:dropped_frames=0&l:stream:startup_time=14000&l:stream:fps=30&l:stream:bitrate=1048576"
			 * ; String m =
			 * "https://nbcume.hb.omtrdc.net/?s:sc:tracking_server=nbcume.sc.omtrdc.net&s:sc:rsid=nbcutvemobileapp%2Cnbcuiphoneappsnbcnetworkbu%2Cnbcunetworkbu&h:sc:ssl=0&s:user:mid=59117565524229059683626022763783479790&s:user:aid=2C953B2385030746-400011854001057F&&s:sp:player_name=Anvato+Android+Player&s:sp:sdk=1.6.4&s:sp:ovp=ovp&l:sp:hb_api_lvl=3&s:sp:hb_version=android-n-1.6.4.27-21b000&s:sp:channel=On-Domain&s:event:sid=1495960262815455840156&l:event:ts=1495960268628&l:event:prev_ts=-1&l:event:playhead=0&l:event:duration=0&s:event:type=bitrate_change&s:asset:name=The+Clairvoyants+and+Jon+Dorenbos%3A+Live+Performance+feat.+Paula+Abdul&l:asset:pod_offset=0&s:asset:ad_id=19032081&s:asset:pod_id=bf1f531f03a72a1c9812fd3ff27d55bb_1&s:asset:pod_position=1&s:asset:ad_sid=1495960266340271048029&s:asset:resolver=Anvato+Android+Player&l:asset:ad_length=30&s:asset:ad_name=N%2FA&s:asset:pod_name=preroll&s:asset:type=ad&l:asset:length=412&s:asset:publisher=A8AB776A5245B4220A490D44%40AdobeOrg&s:asset:video_id=3099592&s:stream:type=VOD+Clip&l:stream:dropped_frames=0&l:stream:startup_time=14000&l:stream:fps=30&l:stream:bitrate=4542800"
			 * ; String n =
			 * "https://nbcume.hb.omtrdc.net/?s:sc:tracking_server=nbcume.sc.omtrdc.net&s:sc:rsid=nbcutvemobileapp%2Cnbcuiphoneappsnbcnetworkbu%2Cnbcunetworkbu&h:sc:ssl=0&s:user:mid=59117565524229059683626022763783479790&s:user:aid=2C953B2385030746-400011854001057F&&s:sp:player_name=Anvato+Android+Player&s:sp:sdk=1.6.4&s:sp:ovp=ovp&l:sp:hb_api_lvl=3&s:sp:hb_version=android-n-1.6.4.27-21b000&s:sp:channel=On-Domain&s:event:sid=1495960262815455840156&l:event:ts=1495960274390&l:event:prev_ts=1495960268628&l:event:playhead=0&l:event:duration=0&s:event:type=bitrate_change&s:asset:name=The+Clairvoyants+and+Jon+Dorenbos%3A+Live+Performance+feat.+Paula+Abdul&l:asset:pod_offset=0&s:asset:ad_id=19032081&s:asset:pod_id=bf1f531f03a72a1c9812fd3ff27d55bb_1&s:asset:pod_position=1&s:asset:ad_sid=1495960266340271048029&s:asset:resolver=Anvato+Android+Player&l:asset:ad_length=30&s:asset:ad_name=N%2FA&s:asset:pod_name=preroll&s:asset:type=ad&l:asset:length=412&s:asset:publisher=A8AB776A5245B4220A490D44%40AdobeOrg&s:asset:video_id=3099592&s:stream:type=VOD+Clip&l:stream:dropped_frames=0&l:stream:startup_time=14000&l:stream:fps=30&l:stream:bitrate=1392800"
			 * ; String o =
			 * "https://nbcume.hb.omtrdc.net/?s:sc:tracking_server=nbcume.sc.omtrdc.net&s:sc:rsid=nbcutvemobileapp%2Cnbcuiphoneappsnbcnetworkbu%2Cnbcunetworkbu&h:sc:ssl=0&s:user:mid=59117565524229059683626022763783479790&s:user:aid=2C953B2385030746-400011854001057F&&s:sp:player_name=Anvato+Android+Player&s:sp:sdk=1.6.4&s:sp:ovp=ovp&l:sp:hb_api_lvl=3&s:sp:hb_version=android-n-1.6.4.27-21b000&s:sp:channel=On-Domain&s:event:sid=1495960262815455840156&l:event:ts=1495960276550&l:event:prev_ts=1495960266342&l:event:playhead=0&l:event:duration=10208&s:event:type=start&s:asset:name=The+Clairvoyants+and+Jon+Dorenbos%3A+Live+Performance+feat.+Paula+Abdul&l:asset:pod_offset=0&s:asset:ad_id=19032081&s:asset:pod_id=bf1f531f03a72a1c9812fd3ff27d55bb_1&s:asset:pod_position=1&s:asset:ad_sid=1495960266340271048029&s:asset:resolver=Anvato+Android+Player&l:asset:ad_length=30&s:asset:ad_name=N%2FA&s:asset:pod_name=preroll&s:asset:type=ad&l:asset:length=412&s:asset:publisher=A8AB776A5245B4220A490D44%40AdobeOrg&s:asset:video_id=3099592&s:stream:type=VOD+Clip&l:stream:dropped_frames=0&l:stream:startup_time=-14000&l:stream:fps=30&l:stream:bitrate=1392800&s:meta:a.nielsen.ctype=VIDEO&s:meta:videominute=01%3A31&s:meta:a.nielsen.sega=The+Clairvoyants+and+Jon+Dorenbos%3A+Live+Performance+feat.+Paula+Abdul&s:meta:a.nielsen.segb=segB&s:meta:videosubcat2=N%2FA&s:meta:videohour=01&s:meta:videoprogram=America%27s+Got+Talent&s:meta:videoepnumber=23&s:meta:videoscreen=Full&s:meta:videoguid=3099592&s:meta:videomvpd=Cablevision&s:meta:videodaypart=Primetime&s:meta:videotitle=The+Clairvoyants+and+Jon+Dorenbos%3A+Live+Performance+feat.+Paula+Abdul&s:meta:videoseason=11&s:meta:videoday=Sunday&s:meta:a.nielsen.segc=segC&s:meta:a.nielsen.appid=P1C377781-0BA4-4D6E-8111-0267BE0D2DC0&s:meta:videonetwork=NBC&s:meta:videoplatform=Mobile&s:meta:videodate=05%2F28%2F2017&s:meta:a.nielsen.program=America%27s+Got+Talent&s:meta:videoapp=NBC&s:meta:a.nielsen.admodel=2&s:meta:videoairdate=09%2F14%2F2016&s:meta:videostatus=Unrestricted&s:meta:videosubcat1=Reality&s:meta:a.nielsen.accmethod=1&s:meta:videocallsign=N%2FA&s:meta:videoinitiate=Manual&s:meta:a.nielsen.clientid=us-800148&s:meta:a.nielsen.vcid=c05&s:meta:videoplayertech=Mobile+Operating+System"
			 * ; String p =
			 * "https://nbcume.hb.omtrdc.net/?s:sc:tracking_server=nbcume.sc.omtrdc.net&s:sc:rsid=nbcutvemobileapp%2Cnbcuiphoneappsnbcnetworkbu%2Cnbcunetworkbu&h:sc:ssl=0&s:user:mid=59117565524229059683626022763783479790&s:user:aid=2C953B2385030746-400011854001057F&&s:sp:player_name=Anvato+Android+Player&s:sp:sdk=1.6.4&s:sp:ovp=ovp&l:sp:hb_api_lvl=3&s:sp:hb_version=android-n-1.6.4.27-21b000&s:sp:channel=On-Domain&s:event:sid=1495960262815455840156&l:event:ts=1495960286676&l:event:prev_ts=1495960276550&l:event:playhead=0&l:event:duration=10126&s:event:type=start&s:asset:name=The+Clairvoyants+and+Jon+Dorenbos%3A+Live+Performance+feat.+Paula+Abdul&l:asset:pod_offset=0&s:asset:ad_id=19032081&s:asset:pod_id=bf1f531f03a72a1c9812fd3ff27d55bb_1&s:asset:pod_position=1&s:asset:ad_sid=1495960266340271048029&s:asset:resolver=Anvato+Android+Player&l:asset:ad_length=30&s:asset:ad_name=N%2FA&s:asset:pod_name=preroll&s:asset:type=ad&l:asset:length=412&s:asset:publisher=A8AB776A5245B4220A490D44%40AdobeOrg&s:asset:video_id=3099592&s:stream:type=VOD+Clip&l:stream:dropped_frames=0&l:stream:startup_time=-14000&l:stream:fps=30&l:stream:bitrate=1392800&s:meta:a.nielsen.ctype=VIDEO&s:meta:videominute=01%3A31&s:meta:a.nielsen.sega=The+Clairvoyants+and+Jon+Dorenbos%3A+Live+Performance+feat.+Paula+Abdul&s:meta:a.nielsen.segb=segB&s:meta:videosubcat2=N%2FA&s:meta:videohour=01&s:meta:videoprogram=America%27s+Got+Talent&s:meta:videoepnumber=23&s:meta:videoscreen=Full&s:meta:videoguid=3099592&s:meta:videomvpd=Cablevision&s:meta:videodaypart=Primetime&s:meta:videotitle=The+Clairvoyants+and+Jon+Dorenbos%3A+Live+Performance+feat.+Paula+Abdul&s:meta:videoseason=11&s:meta:videoday=Sunday&s:meta:a.nielsen.segc=segC&s:meta:a.nielsen.appid=P1C377781-0BA4-4D6E-8111-0267BE0D2DC0&s:meta:videonetwork=NBC&s:meta:videoplatform=Mobile&s:meta:videodate=05%2F28%2F2017&s:meta:a.nielsen.program=America%27s+Got+Talent&s:meta:videoapp=NBC&s:meta:a.nielsen.admodel=2&s:meta:videoairdate=09%2F14%2F2016&s:meta:videostatus=Unrestricted&s:meta:videosubcat1=Reality&s:meta:a.nielsen.accmethod=1&s:meta:videocallsign=N%2FA&s:meta:videoinitiate=Manual&s:meta:a.nielsen.clientid=us-800148&s:meta:a.nielsen.vcid=c05&s:meta:videoplayertech=Mobile+Operating+System"
			 * ;
			 */
			String a1 = "http://b.scorecardresearch.com/p2?c1=19&c2=6035083&ns_ap_an=NBC_Android_Project&ns_ap_pn=android&ns_ap_pv=5.0.1&c12=8d081c21de88331c4ca72fd5584b39cd-cs31&ns_ak=lQwNvByUuX5kvCTftqO6nBFG467vc%2B1E1SAiAbDDpsNn9%2BR9qXEZv5XPdo%2FQpKMK6itGaDeAtZqIpzTKtv7FqmWe5qzgJdYOWCDBZkJKf1Us1zJJRckDpVrrKL8YoGKyRMHuwEZF6ifPq2FAQxEpihJjDDdoA6WQT%2BeRz522B5Q%3D&name=start&ns_ap_ec=1&ns_ap_ev=start&ns_ap_device=ja3g&ns_ap_id=1500876567302&ns_ap_csf=1&ns_ap_bi=com.nbcuni.cloudpath.prod&ns_ap_pfm=android&ns_ap_pfv=5.0.1&ns_ap_ver=3.2.11.3&ns_ap_sv=3.1508.28&ns_type=view&ns_radio=wifi&ns_nc=1&ns_ap_gs=1500876567295&ns_ap_jb=0&ns_ap_res=1080x1920&ns_ap_install=1500876567295&ns_ap_lastrun=0&ns_ap_cs=1&ns_ap_runs=1&ns_ap_usage=7&ns_ap_fg=0&ns_ap_ft=0&ns_ap_dft=0&ns_ap_bt=0&ns_ap_dbt=0&ns_ap_dit=7&ns_ap_as=1&ns_ap_das=0&ns_ap_it=7&ns_ap_ut=60000&ns_ap_lang=en&ns_ap_ar=armv7l&ns_ts=1500876567302";
			String b1 = "http://b.scorecardresearch.com/p2?c1=19&c2=6035083&ns_ap_an=NBC_Android_Project&ns_ap_pn=android&ns_ap_pv=5.0.1&c12=8d081c21de88331c4ca72fd5584b39cd-cs31&ns_ak=lQwNvByUuX5kvCTftqO6nBFG467vc%2B1E1SAiAbDDpsNn9%2BR9qXEZv5XPdo%2FQpKMK6itGaDeAtZqIpzTKtv7FqmWe5qzgJdYOWCDBZkJKf1Us1zJJRckDpVrrKL8YoGKyRMHuwEZF6ifPq2FAQxEpihJjDDdoA6WQT%2BeRz522B5Q%3D&name=background&ns_ap_ec=2&ns_ap_ev=hidden&ns_ap_device=ja3g&ns_ap_id=1500876567302&ns_ap_bi=com.nbcuni.cloudpath.prod&ns_ap_pfm=android&ns_ap_pfv=5.0.1&ns_ap_ver=3.2.11.3&ns_ap_sv=3.1508.28&ns_type=hidden&ns_radio=wifi&ns_nc=1&ns_st_sv=4.1508.28&ns_st_it=r&ns_st_id=1500876602591_1&ns_st_ec=1&ns_st_sp=1&ns_st_sq=1&ns_st_cn=1&ns_st_ev=play&ns_st_po=0&ns_st_cl=2597000&ns_st_pb=1&ns_st_mp=android_puppet&ns_st_mv=4.1508.28&ns_st_pn=1&ns_st_tp=0&ns_st_ci=3517511&ns_ap_res=1080x1920&ns_ap_cs=1&ns_ap_lang=en&ns_ap_ar=armv7l&ns_ts=1500876628924&ns_st_bt=0&ns_st_bp=0&ns_st_br=0&ns_st_ub=0&ns_st_pr=Blindspot&ns_st_sn=2&ns_st_en=22&ns_st_ep=Lepers%20Repel&ns_st_ct=vc11&ns_st_ge=videogenres&ns_st_st=NBC&ns_st_pu=NBC&c3=TVE&c4=&c6=Blindspot&c8=Lepers%20Repel&c9=*null&ns_st_tdt=05%2F17%2F2017";
			String c1 = "http://b.scorecardresearch.com/p2?c1=19&c2=6035083&ns_ap_an=NBC_Android_Project&ns_ap_pn=android&ns_ap_pv=5.0.1&c12=8d081c21de88331c4ca72fd5584b39cd-cs31&ns_ak=lQwNvByUuX5kvCTftqO6nBFG467vc%2B1E1SAiAbDDpsNn9%2BR9qXEZv5XPdo%2FQpKMK6itGaDeAtZqIpzTKtv7FqmWe5qzgJdYOWCDBZkJKf1Us1zJJRckDpVrrKL8YoGKyRMHuwEZF6ifPq2FAQxEpihJjDDdoA6WQT%2BeRz522B5Q%3D&name=background&ns_ap_ec=3&ns_ap_ev=hidden&ns_ap_device=ja3g&ns_ap_id=1500876567302&ns_ap_bi=com.nbcuni.cloudpath.prod&ns_ap_pfm=android&ns_ap_pfv=5.0.1&ns_ap_ver=3.2.11.3&ns_ap_sv=3.1508.28&ns_type=hidden&ns_radio=wifi&ns_nc=1&ns_st_sv=4.1508.28&ns_st_it=r&ns_st_id=1500876602591_1&ns_st_ec=2&ns_st_sp=1&ns_st_cn=1&ns_st_ev=end&ns_st_po=200&ns_st_cl=2597000&ns_st_mp=android_puppet&ns_st_mv=4.1508.28&ns_st_pn=1&ns_st_tp=0&ns_st_pt=199&ns_st_pa=199&ns_st_ci=3517511&ns_ap_res=1080x1920&ns_ap_cs=1&ns_ap_lang=en&ns_ap_ar=armv7l&ns_ts=1500876629123&ns_st_bt=0&ns_st_bp=0&ns_st_pc=0&ns_st_pp=0&ns_st_br=0&ns_st_ub=0&ns_st_pr=Blindspot&ns_st_sn=2&ns_st_en=22&ns_st_ep=Lepers%20Repel&ns_st_ct=vc11&ns_st_ge=videogenres&ns_st_st=NBC&ns_st_pu=NBC&c3=TVE&c4=&c6=Blindspot&c8=Lepers%20Repel&c9=*null&ns_st_tdt=05%2F17%2F2017";
			String d1 = "http://b.scorecardresearch.com/p2?c1=19&c2=6035083&ns_ap_an=NBC_Android_Project&ns_ap_pn=android&ns_ap_pv=5.0.1&c12=8d081c21de88331c4ca72fd5584b39cd-cs31&ns_ak=lQwNvByUuX5kvCTftqO6nBFG467vc%2B1E1SAiAbDDpsNn9%2BR9qXEZv5XPdo%2FQpKMK6itGaDeAtZqIpzTKtv7FqmWe5qzgJdYOWCDBZkJKf1Us1zJJRckDpVrrKL8YoGKyRMHuwEZF6ifPq2FAQxEpihJjDDdoA6WQT%2BeRz522B5Q%3D&name=background&ns_ap_ec=4&ns_ap_ev=hidden&ns_ap_device=ja3g&ns_ap_id=1500876567302&ns_ap_bi=com.nbcuni.cloudpath.prod&ns_ap_pfm=android&ns_ap_pfv=5.0.1&ns_ap_ver=3.2.11.3&ns_ap_sv=3.1508.28&ns_type=hidden&ns_radio=wifi&ns_nc=1&ns_st_sv=4.1508.28&ns_st_it=r&ns_st_id=1500876602591_1&ns_st_ec=3&ns_st_sp=1&ns_st_sq=1&ns_st_cn=2&ns_st_ev=play&ns_st_po=0&ns_st_cl=30000&ns_st_mp=android_puppet&ns_st_mv=4.1508.28&ns_st_pn=1&ns_st_tp=1&ns_st_ad=1&ns_st_ci=3517511&ns_ap_res=1080x1920&ns_ap_cs=1&ns_ap_lang=en&ns_ap_ar=armv7l&ns_ts=1500876629123&ns_st_bt=0&ns_st_bp=0&ns_st_br=0&ns_st_ub=0&ns_st_pr=Blindspot&ns_st_sn=2&ns_st_en=22&ns_st_ep=Lepers%20Repel&ns_st_ct=va11&ns_st_ge=videogenres&ns_st_st=NBC&ns_st_pu=NBC&c3=TVE&c4=&c6=Blindspot&c8=Lepers%20Repel&c9=*null&ns_st_tdt=05%2F17%2F2017";
			String e1 = "http://b.scorecardresearch.com/p2?c1=19&c2=6035083&ns_ap_an=NBC_Android_Project&ns_ap_pn=android&ns_ap_pv=5.0.1&c12=8d081c21de88331c4ca72fd5584b39cd-cs31&ns_ak=lQwNvByUuX5kvCTftqO6nBFG467vc%2B1E1SAiAbDDpsNn9%2BR9qXEZv5XPdo%2FQpKMK6itGaDeAtZqIpzTKtv7FqmWe5qzgJdYOWCDBZkJKf1Us1zJJRckDpVrrKL8YoGKyRMHuwEZF6ifPq2FAQxEpihJjDDdoA6WQT%2BeRz522B5Q%3D&name=background&ns_ap_ec=5&ns_ap_ev=hidden&ns_ap_device=ja3g&ns_ap_id=1500876567302&ns_ap_bi=com.nbcuni.cloudpath.prod&ns_ap_pfm=android&ns_ap_pfv=5.0.1&ns_ap_ver=3.2.11.3&ns_ap_sv=3.1508.28&ns_type=hidden&ns_radio=wifi&ns_nc=1&ns_st_sv=4.1508.28&ns_st_it=r&ns_st_id=1500876602591_1&ns_st_ec=4&ns_st_sp=1&ns_st_cn=2&ns_st_ev=hb&ns_st_po=10001&ns_st_cl=30000&ns_st_hc=1&ns_st_mp=android_puppet&ns_st_mv=4.1508.28&ns_st_pn=1&ns_st_tp=1&ns_st_pt=10002&ns_st_pa=10201&ns_st_ad=1&ns_st_ci=3517511&ns_ap_res=1080x1920&ns_ap_cs=1&ns_ap_lang=en&ns_ap_ar=armv7l&ns_ts=1500876639124&ns_st_pe=1&ns_st_bt=0&ns_st_bp=0&ns_st_pc=0&ns_st_pp=0&ns_st_br=0&ns_st_ub=0&ns_st_pr=Blindspot&ns_st_sn=2&ns_st_en=22&ns_st_ep=Lepers%20Repel&ns_st_ct=va11&ns_st_ge=videogenres&ns_st_st=NBC&ns_st_pu=NBC&c3=TVE&c4=&c6=Blindspot&c8=Lepers%20Repel&c9=*null&ns_st_tdt=05%2F17%2F2017";
			String f1 = "http://b.scorecardresearch.com/p2?c1=19&c2=6035083&ns_ap_an=NBC_Android_Project&ns_ap_pn=android&ns_ap_pv=5.0.1&c12=8d081c21de88331c4ca72fd5584b39cd-cs31&ns_ak=lQwNvByUuX5kvCTftqO6nBFG467vc%2B1E1SAiAbDDpsNn9%2BR9qXEZv5XPdo%2FQpKMK6itGaDeAtZqIpzTKtv7FqmWe5qzgJdYOWCDBZkJKf1Us1zJJRckDpVrrKL8YoGKyRMHuwEZF6ifPq2FAQxEpihJjDDdoA6WQT%2BeRz522B5Q%3D&name=background&ns_ap_ec=6&ns_ap_ev=hidden&ns_ap_device=ja3g&ns_ap_id=1500876567302&ns_ap_bi=com.nbcuni.cloudpath.prod&ns_ap_pfm=android&ns_ap_pfv=5.0.1&ns_ap_ver=3.2.11.3&ns_ap_sv=3.1508.28&ns_type=hidden&ns_radio=wifi&ns_nc=1&ns_st_sv=4.1508.28&ns_st_it=r&ns_st_id=1500876602591_1&ns_st_ec=4&ns_st_sp=1&ns_st_cn=2&ns_st_ev=hb&ns_st_po=20002&ns_st_cl=30000&ns_st_hc=2&ns_st_mp=android_puppet&ns_st_mv=4.1508.28&ns_st_pn=1&ns_st_tp=1&ns_st_pt=20002&ns_st_pa=20201&ns_st_ad=1&ns_st_ci=3517511&ns_ap_res=1080x1920&ns_ap_cs=1&ns_ap_lang=en&ns_ap_ar=armv7l&ns_ts=1500876649125&ns_st_pe=1&ns_st_bt=0&ns_st_bp=0&ns_st_pc=0&ns_st_pp=0&ns_st_br=0&ns_st_ub=0&ns_st_pr=Blindspot&ns_st_sn=2&ns_st_en=22&ns_st_ep=Lepers%20Repel&ns_st_ct=va11&ns_st_ge=videogenres&ns_st_st=NBC&ns_st_pu=NBC&c3=TVE&c4=&c6=Blindspot&c8=Lepers%20Repel&c9=*null&ns_st_tdt=05%2F17%2F2017";
			String g1 = "http://b.scorecardresearch.com/p2?c1=19&c2=6035083&ns_ap_an=NBC_Android_Project&ns_ap_pn=android&ns_ap_pv=5.0.1&c12=8d081c21de88331c4ca72fd5584b39cd-cs31&ns_ak=lQwNvByUuX5kvCTftqO6nBFG467vc%2B1E1SAiAbDDpsNn9%2BR9qXEZv5XPdo%2FQpKMK6itGaDeAtZqIpzTKtv7FqmWe5qzgJdYOWCDBZkJKf1Us1zJJRckDpVrrKL8YoGKyRMHuwEZF6ifPq2FAQxEpihJjDDdoA6WQT%2BeRz522B5Q%3D&name=background&ns_ap_ec=7&ns_ap_ev=hidden&ns_ap_device=ja3g&ns_ap_id=1500876567302&ns_ap_bi=com.nbcuni.cloudpath.prod&ns_ap_pfm=android&ns_ap_pfv=5.0.1&ns_ap_ver=3.2.11.3&ns_ap_sv=3.1508.28&ns_type=hidden&ns_radio=wifi&ns_nc=1&ns_st_sv=4.1508.28&ns_st_it=r&ns_st_id=1500876602591_1&ns_st_ec=4&ns_st_sp=1&ns_st_cn=2&ns_st_ev=hb&ns_st_po=30002&ns_st_cl=30000&ns_st_hc=3&ns_st_mp=android_puppet&ns_st_mv=4.1508.28&ns_st_pn=1&ns_st_tp=1&ns_st_pt=30002&ns_st_pa=30201&ns_st_ad=1&ns_st_ci=3517511&ns_ap_res=1080x1920&ns_ap_cs=1&ns_ap_lang=en&ns_ap_ar=armv7l&ns_ts=1500876659125&ns_st_pe=1&ns_st_bt=0&ns_st_bp=0&ns_st_pc=0&ns_st_pp=0&ns_st_br=0&ns_st_ub=0&ns_st_pr=Blindspot&ns_st_sn=2&ns_st_en=22&ns_st_ep=Lepers%20Repel&ns_st_ct=va11&ns_st_ge=videogenres&ns_st_st=NBC&ns_st_pu=NBC&c3=TVE&c4=&c6=Blindspot&c8=Lepers%20Repel&c9=*null&ns_st_tdt=05%2F17%2F2017";
			String h1 = "http://b.scorecardresearch.com/p2?c1=19&c2=6035083&ns_ap_an=NBC_Android_Project&ns_ap_pn=android&ns_ap_pv=5.0.1&c12=8d081c21de88331c4ca72fd5584b39cd-cs31&ns_ak=lQwNvByUuX5kvCTftqO6nBFG467vc%2B1E1SAiAbDDpsNn9%2BR9qXEZv5XPdo%2FQpKMK6itGaDeAtZqIpzTKtv7FqmWe5qzgJdYOWCDBZkJKf1Us1zJJRckDpVrrKL8YoGKyRMHuwEZF6ifPq2FAQxEpihJjDDdoA6WQT%2BeRz522B5Q%3D&name=background&ns_ap_ec=8&ns_ap_ev=hidden&ns_ap_device=ja3g&ns_ap_id=1500876567302&ns_ap_bi=com.nbcuni.cloudpath.prod&ns_ap_pfm=android&ns_ap_pfv=5.0.1&ns_ap_ver=3.2.11.3&ns_ap_sv=3.1508.28&ns_type=hidden&ns_radio=wifi&ns_nc=1&ns_st_sv=4.1508.28&ns_st_it=r&ns_st_id=1500876602591_1&ns_st_ec=4&ns_st_sp=1&ns_st_cn=2&ns_st_ev=hb&ns_st_po=40002&ns_st_cl=30000&ns_st_hc=4&ns_st_mp=android_puppet&ns_st_mv=4.1508.28&ns_st_pn=1&ns_st_tp=1&ns_st_pt=40002&ns_st_pa=40201&ns_st_ad=1&ns_st_ci=3517511&ns_ap_res=1080x1920&ns_ap_cs=1&ns_ap_lang=en&ns_ap_ar=armv7l&ns_ts=1500876669125&ns_st_pe=1&ns_st_bt=0&ns_st_bp=0&ns_st_pc=0&ns_st_pp=0&ns_st_br=0&ns_st_ub=0&ns_st_pr=Blindspot&ns_st_sn=2&ns_st_en=22&ns_st_ep=Lepers%20Repel&ns_st_ct=va11&ns_st_ge=videogenres&ns_st_st=NBC&ns_st_pu=NBC&c3=TVE&c4=&c6=Blindspot&c8=Lepers%20Repel&c9=*null&ns_st_tdt=05%2F17%2F2017";
			ArrayList<String> comscoreUrls = new ArrayList<String>();
			ArrayList<String> hbUrls = new ArrayList<String>();
			String comscoreSheetName = "Comscore";
			String comscoreRemove = "https://sb.scorecardresearch.com/p2?";
			String comscoreUrl = "http://b.scorecardresearch.com/p2?";
			String comscoreEventType = "ns_st_ev";
			String feedUrl = "http://feed.theplatform.com/f/HNK2IC/nbcd_app_adstitch_v3_prod?byGUID=3517511";
			String path = "/Users/557743/Desktop/CableBrandPOC/ConsoleOutput/ComscoreAnalytics-Run1-Android.txt";
			String comscoreEventVal = "play";
			comscoreUrls.add(a1);
			comscoreUrls.add(b1);
			comscoreUrls.add(c1);
			comscoreUrls.add(d1);
			comscoreUrls.add(e1);
			comscoreUrls.add(f1);
			comscoreUrls.add(g1);
			comscoreUrls.add(h1);
			// validateAnalytics(comscoreUrls, comscoreRemove);
			// validateAnalytics(comscoreUrls, comscoreRemove,
			// comscoreSheetName, comscoreEventType, comscoreEventVal);
			/*
			 * hbUrls.add(h); hbUrls.add(i); hbUrls.add(j); hbUrls.add(k);
			 * hbUrls.add(l); hbUrls.add(m); hbUrls.add(n); hbUrls.add(o);
			 * hbUrls.add(p);
			 */
			String HBRemove = "https://nbcume.hb.omtrdc.net/?";
			// String HbSheetName = "HB-Chapter-Start";
			String HbSheetName = "HB-Ad-Start";
			String HBEventType = "s:event:type";
			// String HBEventVal = "start";
			String HBEventVal = "play";
			String Path="/Users/557743/Desktop/CableBrandPOC/HarFiles/ComscoreAnalytics-Run1-iOS.har";
			hbUrls=harReader(Path);
			validateAnalytics(hbUrls, HBRemove, HbSheetName, HBEventType, HBEventVal);
			// validateAnalytics(hbUrls, HBRemove);
			// patternCheck();
			// validateAdiOSConsole("/Users/557743/Desktop/CableBrandPOC/ConsoleOutput/HBAnalyticCalls-Run1-Android.txt",
			// "Android");
			// validateComscore(comscoreUrls, comscoreUrl, "Comscore-Play",
			// "ns_st_ev", "play", feedUrl, path);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
