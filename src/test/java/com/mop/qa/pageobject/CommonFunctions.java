package com.mop.qa.pageobject;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mop.qa.Utilities.SendGetPost;
import com.mop.qa.testbase.PageBase;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;

/**
 * Class repository for Home screen properties
 * 
 * @author 557743
 *
 */
public class CommonFunctions extends PageBase {
	public CommonFunctions() {
		super();
	}

	/* CPC Elements************ */
	@FindBy(name = "Options")
	protected WebElement optionsWE;
	/* String optionsWE="name=Options"; */
	@FindBy(name = "CHANGE")
	protected WebElement changeWE;
	@FindBy(name = "GN - Prod Release")
	protected WebElement configWE;
	@FindBy(xpath = "//UIAApplication[1]/UIAWindow[2]/UIATextView[1]")
	protected WebElement harnessWE;
	@FindBy(xpath = "//UIAApplication[1]/UIAWindow[2]/UIASegmentedControl[1]/UIAButton[3]")
	protected WebElement envSelectWE;
	@FindBy(name = "Done")
	protected WebElement doneWE;
	@FindBy(name = "Select...")
	protected WebElement selectWE;
	@FindBy(name = "VOD Listing")
	protected WebElement selectVODWE;
	@FindBy(name = "NBC")
	protected WebElement channelWE;
	@FindBy(name = "AuthN")
	protected WebElement authnWE;
	@FindBy(name = "Actions")
	protected WebElement actionsWE;
	@FindBy(name = "Logout")
	protected WebElement logoutWE;
	@FindBy(name = "Blindspot")
	protected WebElement showWE;
	@FindBy(name = "ALL")
	protected WebElement seriesWE;
	/* 3 Episode trial Elements */
	@FindBy(name = "Adobe Promo TempPass")
	protected WebElement adobePromoTempPassWE;
	@FindBy(xpath = "//UIAApplication[1]/UIAWindow[2]/UIAAlert[1]/UIAScrollView[1]/UIACollectionView[1]/UIACollectionCell[1]/UIATextField[1]")
	protected WebElement emailWE;
	@FindBy(xpath = "//UIAApplication[1]/UIAWindow[2]/UIAAlert[1]/UIAScrollView[1]/UIACollectionView[1]/UIACollectionCell[2]/UIATextField[1]")
	protected WebElement assetIDWE;
	@FindBy(name = "AuthZ and Play")
	protected WebElement authZPlayWE;
	@FindBy(name = "Expand")
	protected WebElement expandWE;
	@FindBy(name = "3493042: Senile Lines")
	protected WebElement episodeWE;
	@FindBy(xpath = "//UIAApplication[1]/UIAWindow[2]/UIATableView[1]/UIATableGroup[1]/UIASearchBar[1]")
	protected static WebElement searchMVPDWE;
	@FindBy(name = "Optimum")
	protected WebElement mvpdWE;
	@FindBy(xpath = "//UIAApplication[1]/UIAWindow[2]/UIAScrollView[1]/UIAWebView[1]/UIATextField[1]")
	protected WebElement userIDWE;
	@FindBy(xpath = "//UIAApplication[1]/UIAWindow[2]/UIAScrollView[1]/UIAWebView[1]/UIASecureTextField[1]")
	protected WebElement passwordWE;
	@FindBy(xpath = "//UIAApplication[1]/UIAWindow[2]/UIAScrollView[1]/UIAWebView[1]/UIAImage[2]")
	protected WebElement signinWE;
	@FindBy(name = "pause")
	protected WebElement pauseWE;
	@FindBy(xpath = "//UIAApplication[1]/UIAWindow[2]/UIAButton[11]")
	protected WebElement stopWE;
	// Android Elements
	@FindBy(id = "com.nbcuni.cloudpath.prod:id/select_mvpd")
	protected WebElement selectConfigWE;
	@FindBy(id = "com.nbcuni.cloudpath.prod:id/button3")
	protected WebElement prodKeyWE;
	@FindBy(id = "com.nbcuni.cloudpath.prod:id/editText")
	protected WebElement searchConfigWE;
	@FindBy(id = "com.nbcuni.cloudpath.prod:id/select_channel")
	protected WebElement channelWEA;
	@FindBy(id = "android:id/alertTitle")
	protected WebElement alertWE;
	@FindBy(name = "nbc")
	protected WebElement channelIDWE;
	@FindBy(id = "com.nbcuni.cloudpath.prod:id/choosePlayer")
	protected WebElement liveVODbtnWE;
	@FindBy(id = "com.nbcuni.cloudpath.prod:id/editText")
	protected WebElement mvpdSearchWE;
	@FindBy(name = "Optimum ID")
	protected WebElement useridWEA;
	@FindBy(name = "Password")
	protected WebElement passwordWEA;
	@FindBy(id = "com.nbcuni.cloudpath.prod:id/txtEventsLog")
	protected WebElement harnessWEA;
	@FindBy(id = "com.nbcuni.cloudpath.prod:id/ctrl4")
	protected WebElement eventLogA;
	static ArrayList<HashMap<String, String>> seriesOfEvents;
	static HashMap<String, ArrayList<String>> adPots;
	static HashMap<String, String> assetValuesFeedMap = new HashMap<String, String>();
	static int counter = 0;
	@FindBy(id = "com.nbcuni.cloudpath.prod:id/videoContainer")
	protected WebElement videoPlayerWE;
	/* =====Directv Elements========== */
	@FindBy(xpath = "//UIAApplication[1]/UIAWindow[2]/UIAScrollView[1]/UIAWebView[1]/UIATextField[1]")
	protected WebElement directvUserNameWE;
	@FindBy(xpath = "//UIAApplication[1]/UIAWindow[2]/UIAScrollView[1]/UIAWebView[1]/UIASecureTextField[1]")
	protected WebElement directvPasswordWE;
	@FindBy(xpath = " //UIAApplication[1]/UIAWindow[2]/UIAScrollView[1]/UIAWebView[1]/UIAButton[1]")
	protected WebElement directvSigninWE;
	@FindBy(name = "DIRECTV")
	protected WebElement directvSelectWE;
	static String path = System.getProperty("user.dir") + "/VideoComparison";
	// Common Elements
	@AndroidFindBy(id = "com.nbcuni.cloudpath.prod:id/select_channel")
	@iOSFindBy(name = "NBC")
	public WebElement channelE;

	// Method Which will select Config file and Environment
	public void selectConfig(String Config, String Env) throws Exception {
		String path = System.getProperty("user.dir") + "/VideoComparison";
		if (platFormName.equalsIgnoreCase("iOS")) {
			// Wait for Option button and clicks on it
			// waitForVisibilityOfElement(optionsWE);
			Thread.sleep(3000);
			click(optionsWE, "Options button");
			// elementScreenshot(optionsWE, path, currentTest);
			// Wait for change button and clicks on it
			waitForVisibilityOfElement(changeWE);
			click(changeWE, "change button");
			// elementScreenshot(optionsWE, path, currentTest);
			waitForVisibilityOfElement(configWE);
			// Selecting Config file
			appiumDriver.findElement(By.id(Config)).click();
			test.log(LogStatus.INFO, "Selected Config file:" + Config);
			// Selecting Environment
			appiumDriver.findElement(By.id(Env)).click();
			test.log(LogStatus.INFO, "Selected Environment:" + Env);
			Thread.sleep(1000);
			click(doneWE, "Done button clicked");
			waitForVisibilityOfElement(optionsWE);
		} else {
			waitForVisibilityOfElement(selectConfigWE);
			// elementScreenshot(videoPlayerWE, path, currentTest);
			click(selectConfigWE, "Config");
			Thread.sleep(2000);
			appiumDriver.findElement(By.name(Env)).click();
			test.log(LogStatus.INFO, "Selected Environment:" + Env);
			// click(prodKeyWE, "ProdKey");
			waitForVisibilityOfElement(searchConfigWE);
			appiumDriver.findElement(By.name(Config)).click();
			test.log(LogStatus.INFO, "Selected Config file:" + Config);
			Thread.sleep(2000);
		}
	}

	public void selectChannel() throws Exception {
		if (platFormName.equalsIgnoreCase("iOS")) {
			click(selectWE, "channel");
			waitForVisibilityOfElement(selectVODWE);
			click(selectVODWE, "selecting shows");
			waitForVisibilityOfElement(channelWE);
			click(channelWE, "Channel selected");
			waitForVisibilityOfElement(showWE);
		} else {
			waitForVisibilityOfElement(channelWEA);
			Thread.sleep(3000);
			click(channelWEA, "Channel");
			waitForVisibilityOfElement(alertWE);
			click(channelIDWE, "Channel");
			Thread.sleep(3000);
			click(liveVODbtnWE, "LiveVOD");
		}
	}

	public void selectShow(String showName, String season, String episodeName) throws Exception {
		try {
			if (platFormName.equalsIgnoreCase("iOS")) {
				appiumDriver.findElement(By.id(showName)).click();
				test.log(LogStatus.INFO, "Show Name" + showName);
				waitForVisibilityOfElement(seriesWE);
				appiumDriver.findElement(By.id(season)).click();
				test.log(LogStatus.INFO, "Season :" + season);
				// /waitForVisibilityOfElement(episodeWE);
				appiumDriver.findElement(By.id(episodeName)).click();
				test.log(LogStatus.INFO, "Season :" + episodeName);
			} else {
				waitForVisibilityOfElement(showWE);
				appiumDriver.findElement(By.name(showName)).click();
				test.log(LogStatus.INFO, "Show Name" + showName);
				// waitForVisibilityOfElement(episodeWEA);
				Thread.sleep(2000);
				test.log(LogStatus.INFO,
						"Episode Name" + appiumDriver.findElement(By.id("android:id/text1")).getText());
				String epsiodeName = appiumDriver.findElement(By.id("android:id/text1")).getText();
				System.out.println(appiumDriver.findElement(By.id("android:id/text1")).getText());
				appiumDriver.findElement(By.name(epsiodeName)).click();
			}
		} catch (Exception e) {
			test.log(LogStatus.FAIL, e);
		}
	}

	public void direcTVlogin(String SearchMVPD, String UserName, String Password) throws Exception {
		click(searchMVPDWE, "searchbar clicked");
		enterText(searchMVPDWE, SearchMVPD, "optimum mvpd searched");
		waitForVisibilityOfElement(directvSelectWE);
		click(directvSelectWE, "mvpd selected");
		Thread.sleep(2000);
		click(directvUserNameWE, "userid selected");
		enterText(directvUserNameWE, UserName, "userID");
		waitForVisibilityOfElement(directvPasswordWE);
		click(directvPasswordWE, "password clicked");
		enterText(directvPasswordWE, Password, "Password enterd");
		waitForVisibilityOfElement(directvSigninWE);
		click(directvSigninWE, "signin button clicked");
		waitForVisibilityOfElement(optionsWE);
	}

	public static HashMap<String, Object> getTKXValues(String Response) throws Exception {
		// String Response =
		// SendGetPost.sendPost("http://tkx-prod.nbc.anvato.net/rest/v2/tve/?anvack=XE5XpDni9xLBF6C02OBLFLb3vRhlP5zz");
		// String Response = SendGetPost.sendPost(url);
		System.out.println(Response);
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<String, Object>();
		// convert JSON string to Map
		map = objectMapper.readValue(Response, new TypeReference<Map<String, Object>>() {
		});
		HashMap<String, Object> returnValues = new HashMap<String, Object>();
		HashMap<String, Object> geoMap = null;
		// System.out.println(map.size());
		Map<String, String> dynamicValuesMap = new HashMap<String, String>();
		// System.out.println(map.size());
		HashMap<String, Object> data = (HashMap<String, Object>) map.get("user");
		HashMap<String, Object> station = (HashMap<String, Object>) map.get("station");
		System.out.println("======" + station);
		Map<String, Object> attributes = (Map<String, Object>) data.get("geo-station");
		System.out.println(attributes.get("callsign"));
		System.out.println(data.get("geoZip"));
		for (String key : data.keySet()) {
			System.out.println(key + "value" + data.get(key));
		}
		returnValues.put("callsign", attributes.get("callsign"));
		returnValues.put("geoZip", data.get("geoZip"));
		returnValues.put("showDetails", station);
		return returnValues;
	}

	public void selectMVPD(String SearchMVPD, String UserName, String Password) throws Exception {
		if (platFormName.equalsIgnoreCase("iOS")) {
			click(searchMVPDWE, "searchbar clicked");
			enterText(searchMVPDWE, SearchMVPD, "optimum mvpd searched");
			waitForVisibilityOfElement(mvpdWE);
			click(mvpdWE, "mvpd selected");
			Thread.sleep(2000);
			click(userIDWE, "userid selected");
			enterText(userIDWE, UserName, "userID");
			waitForVisibilityOfElement(passwordWE);
			click(passwordWE, "password clicked");
			enterText(passwordWE, Password, "Password enterd");
			waitForVisibilityOfElement(signinWE);
			click(signinWE, "signin button clicked");
		} else {
			enterText(mvpdSearchWE, "optimum", "Optimum");
			click(mvpdWE, "mvpd");
			Thread.sleep(3000);
			waitForVisibilityOfElement(useridWEA);
			enterText(useridWEA, UserName, "username");
			enterText(passwordWEA, Password, "password");
			Thread.sleep(2000);
			pressEnter(appiumDriver);
		}
	}

	// Method Which will logout from MVPD
	public void logut() throws Exception {
		click(actionsWE, "action button");
		click(logoutWE, "Logout");
	}

	public static String createFile(String consoleText, String platformName, String currentTest) throws IOException {
		String path = System.getProperty("user.dir") + "/ConsoleOutput";
		File consolePath = new File(path);
		if (!consolePath.exists())
			consolePath.mkdirs();
		File consoleFile = new File(path + "/" + currentTest + "-" + platFormName + ".txt");
		FileWriter fw = new FileWriter(consoleFile);
		fw.write(consoleText);
		fw.close();
		System.out.println(consoleFile.getPath());
		return consoleFile.getPath();
	}

	public String getHarnessLog() throws Exception {
		String consoleText;
		if (platFormName.equalsIgnoreCase("iOS")) {
			consoleText = getText(harnessWE);
			test.log(LogStatus.INFO, "===================Console Log=====================");
			test.log(LogStatus.INFO, "" + consoleText);
		} else {
			click(eventLogA, "Event Log");
			consoleText = getText(harnessWEA);
			test.log(LogStatus.INFO, "===================Console Log=====================");
			test.log(LogStatus.INFO, "" + consoleText);
		}
		return consoleText;
	}

	public static String formatSeconds(int timeInSeconds) {
		int hours = timeInSeconds / 3600;
		int secondsLeft = timeInSeconds - hours * 3600;
		int minutes = secondsLeft / 60;
		int seconds = secondsLeft - minutes * 60;
		String formattedTime = "";
		if (hours < 10)
			formattedTime += "0";
		formattedTime += hours + ":";
		if (minutes < 10)
			formattedTime += "0";
		formattedTime += minutes + ":";
		if (seconds < 10)
			formattedTime += "0";
		formattedTime += seconds;
		return formattedTime;
	}

	public static void validateTime(int Expected, int Actual) {
		if (Expected == Actual) {
			test.log(LogStatus.PASS, "Expected :" + formatSeconds(Expected));
			test.log(LogStatus.PASS, "Actual :" + formatSeconds(Actual));
		} else {
			test.log(LogStatus.FAIL, "Expected :" + formatSeconds(Expected));
			test.log(LogStatus.FAIL, "Actual :" + formatSeconds(Actual));
		}
	}

	public static int covertHHMMSStoSeconds(String Actual) {
		List<String> list = Arrays.asList(Actual.replaceAll("\\s", "").split(":"));
		int totalSeconds = 0;
		if (list.size() == 2) {
			Actual = "00:" + Actual;
			list = Arrays.asList(Actual.replaceAll("\\s", "").split(":"));
		}
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
			if (i == 0) {
				int hrs = Integer.parseInt(list.get(i)) * 3600;
				totalSeconds = hrs;
			} else if (i == 1) {
				int mm = Integer.parseInt(list.get(i)) * 60;
				totalSeconds = mm + totalSeconds;
			} else {
				int seconds = Integer.parseInt(list.get(i));
				totalSeconds = seconds + totalSeconds;
			}
		}
		return totalSeconds;
	}

	public static void validateAnalytics(ArrayList<String> comscoreUrls, String remove) throws Exception {
		System.out.println("im in comscore");
		String correctString;
		String queryString;
		ArrayList<String> comscoreUrlsModified = new ArrayList<String>();
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
			List<String> HB = Arrays.asList(key.replaceAll("\\s", "").split("&"));
			for (int k = 0; k < HB.size() - 1; k++) {
				// System.out.println(HB.get(k)+'\n');
				List<String> parValue = Arrays.asList(HB.get(k).replaceAll("\\s", "").split("="));
				correctString = stringCheck(parValue.get(1));
				KeyValues.put(parValue.get(0), correctString);
			}
			HashMap<String, String> excelValueMap = new HashMap<String, String>();
			System.out.println(getColumValues("Expected", "Comscore").size());
			// System.out.println(parameterNames);
			ArrayList<String> parameterNames = null;
			ArrayList<String> parameterValues = null;
			for (int k = 1; k <= getColumValues("Parameter", "Comscore").size() - 1; k++) {
				excelValueMap.put(getColumValues("Parameter", "Comscore").get(k),
						getColumValues("Expected", "Comscore").get(k));
			}
			/*
			 * System.out.println(
			 * "===================================Excel captured============="
			 * ); System.out.println(excelValueMap.size());
			 * System.out.println(excelValueMap); System.out.println(
			 * "===================================Dynamically captured============="
			 * ); System.out.println(KeyValues);
			 */
			System.out.println("**********************************************" + KeyValues.get("ns_st_ev")
					+ "*****************************");
			if (KeyValues.get("ns_st_ev").equals("play")) {
				test.log(LogStatus.INFO, "<b><font color=\"purple\">Parameter" + "---------" + "Expected" + "---------"
						+ "Actual" + "---------" + "Status</b>");
				for (int i = 0; i < excelValueMap.size(); i++) {
					// Comparing Parameter From Charles and Parameter from excel
					if (KeyValues.containsKey(excelValueMap.keySet().toArray()[i])) {
						if (excelValueMap.get(excelValueMap.keySet().toArray()[i])
								.equals(KeyValues.get(excelValueMap.keySet().toArray()[i]))) {
							System.out.println("PASS");
							System.out.println("Parameter:" + excelValueMap.keySet().toArray()[i] + "Expected:"
									+ excelValueMap.get(excelValueMap.keySet().toArray()[i]) + "Actual:"
									+ KeyValues.get(excelValueMap.keySet().toArray()[i]));
							test.log(LogStatus.INFO, "<font color=\"black\">" + excelValueMap.keySet().toArray()[i]
									+ "--------->" + excelValueMap.get(excelValueMap.keySet().toArray()[i]) + ""
									+ "--------->" + "</font><font color=\"green\">"
									+ KeyValues.get(excelValueMap.keySet().toArray()[i]) + "--------->" + "PASS</b>");
						} else {
							System.out.println("==========FAIL=========");
							System.out.println("Parameter:" + excelValueMap.keySet().toArray()[i] + "Expected:"
									+ excelValueMap.get(excelValueMap.keySet().toArray()[i]) + "Actual:"
									+ KeyValues.get(excelValueMap.keySet().toArray()[i]));
							test.log(LogStatus.FAIL, "<font color=\"black\">" + excelValueMap.keySet().toArray()[i]
									+ "--------->" + excelValueMap.get(excelValueMap.keySet().toArray()[i])
									+ "--------->" + "</font><font color=\"red\">"
									+ KeyValues.get(excelValueMap.keySet().toArray()[i]) + "--------->" + "FAIL</b>");
						}
					} else {
						System.out.println("Parameter" + excelValueMap.keySet().toArray()[i] + "is missing");
						test.log(LogStatus.FAIL,
								"<font color=\"black\">" + excelValueMap.keySet().toArray()[i] + "--------->"
										+ excelValueMap.get(excelValueMap.keySet().toArray()[i]) + "--------->"
										+ "</font><font color=\"red\">" + excelValueMap.keySet().toArray()[i]
										+ "    Parameter is missing" + "--------->" + "FAIL</b>");
						test.log(LogStatus.FAIL, "Parameter" + excelValueMap.keySet().toArray()[i] + "is missing");
					}
				}
			}
			test.log(LogStatus.INFO, "===================================Excel captured=============");
			test.log(LogStatus.INFO, "" + excelValueMap);

			// Commented the dynamically captured values
			/*
			 * test.log(LogStatus.INFO,
			 * "===================================Dynamically captured============="
			 * ); test.log(LogStatus.INFO, "" + KeyValues);
			 */
			//
			System.out.println("========================END=============================");
		}
	}

	public static void validateAnalytics(ArrayList<String> hbUrls, String remove, String HbSheetName,
			String HBEventType, String HBEventVal, ArrayList<String> HBUrlsTimeStamp, String feedurl, String path)
			throws Exception {
		String correctString;
		String queryString;
		String pattern;
		int counter = 0;
		String preRolladParam;
		String midRolladParam;
		// Map is to get what are all the dynamic values in excel
		HashMap<String, String> dynamicValuesMap;
		// Map is to get Dynamic values from Feed
		HashMap<String, String> dynamicValuesFeedMap;
		// Map is for static values capture in excel
		HashMap<String, String> staticValuesMap;
		// Map is to get the dynamic adValues from console
		Map<String, String> dynamicValuesAdMap = null;
		ArrayList<String> comscoreUrlsModified = new ArrayList<String>();
		// Excel Capture
		HashMap<String, String> excelValueMap = new HashMap<String, String>();
		seriesOfEvents = new ArrayList<HashMap<String, String>>();
		int j;
		if (platFormName.equalsIgnoreCase("iOS")) {
			preRolladParam = "s:asset:pod_name=pre-roll";
			midRolladParam = "s:asset:pod_name=mid-roll";
		} else {
			preRolladParam = "s:asset:pod_name=preroll";
			midRolladParam = "s:asset:pod_name=midRoll";
		}
		for (j = 0; j < hbUrls.size(); j++) {
			queryString = hbUrls.get(j).replace(remove, "");
			System.out.println(queryString);
			comscoreUrlsModified.add(queryString);
		}
		int preCount = 1, midCount = 1;
		HashMap<String, Map<String, String>> adData = validateAdiOSConsole(path);
		System.out.println(adData);
		for (String url : comscoreUrlsModified) {
			dynamicValuesAdMap = new HashMap<>();
			// System.out.println("========================START=============================");
			// System.out.println(comscoreUrlsModified);
			HashMap<String, String> KeyValues = new HashMap<String, String>();
			// HashMap<String, Map<String, String>> adData =
			// validateAdiOSConsole(path);
			if (url.contains(preRolladParam)) {
				if (url.contains(adData.get("Preroll" + preCount).get("s:asset:ad_id"))) {
					dynamicValuesAdMap = adData.get("Preroll" + preCount);
				} else {
					try {
						preCount++;
						dynamicValuesAdMap = adData.get("Preroll" + preCount);
					} catch (Exception e) {
						preCount--;
						System.out.println("Ad ID with " + adData.get("Preroll" + preCount).get("s:asset:ad_id")
								+ " is not found in Console Data");
					}
				}
			} else if (url.contains(midRolladParam)) {
				if (url.contains(adData.get("Midroll" + midCount).get("s:asset:ad_id"))) {
					dynamicValuesAdMap = adData.get("Midroll" + midCount);
				} else {
					try {
						midCount++;
						dynamicValuesAdMap = adData.get("Midroll" + midCount);
					} catch (Exception e) {
						midCount--;
						System.out.println("Ad ID with " + adData.get("Midroll" + midCount).get("s:asset:ad_id")
								+ " is not found in Console Data");
					}
				}
			} else {
				dynamicValuesAdMap = assetValuesFeedMap;
			}
			// System.out.println(comscoreUrlsModified);
			String key = url;
			pattern = "((?!<&)&(?!&))|(&&&&)|(&&)";
			// List<String> HB = Arrays.asList(key.replaceAll("\\s",
			// "").split("&"));
			List<String> HB = Arrays.asList(key.replaceAll("\\s", "").split(pattern));
			for (int k = 0; k < HB.size() - 1; k++) {
				// System.out.println(HB.get(k)+'\n');
				List<String> parValue = Arrays.asList(HB.get(k).replaceAll("\\s", "").split("="));
				if (parValue.size() <= 1)
					continue;
				correctString = stringCheck(parValue.get(1));
				System.out.println(parValue.get(0) + ":" + correctString);
				KeyValues.put(parValue.get(0), correctString);
			}
			for (int k = 1; k <= getColumValues("Parameter", HbSheetName).size() - 1; k++) {
				excelValueMap.put(getColumValues("Parameter", HbSheetName).get(k),
						getColumValues("Expected", HbSheetName).get(k));
			}
			dynamicValuesMap = new HashMap<String, String>();
			// dynamicValuesFeedMap =
			feedResponse(feedurl);
			dynamicValuesFeedMap = feedResponse(feedurl);
			staticValuesMap = new HashMap<String, String>();
			ArrayList<String> parameterNames = null;
			ArrayList<String> parameterValues = null;
			// Get All Dynamic Parameters from Excel Sheet
			for (String expected : excelValueMap.keySet()) {
				if (excelValueMap.get(expected).equalsIgnoreCase("Dyn")) {
					dynamicValuesMap.put(expected, excelValueMap.get(expected));
				} else {
					staticValuesMap.put(expected, excelValueMap.get(expected));
				}
			}
			// Put All Dynamic values which are captured into excelvalues
			for (String keyData : dynamicValuesMap.keySet()) {
				System.out.println(keyData);
				if (dynamicValuesFeedMap.containsKey(keyData)) {
					dynamicValuesMap.put(keyData, dynamicValuesFeedMap.get(keyData));
				} else if (dynamicValuesAdMap.containsKey(keyData)) {
					dynamicValuesMap.put(keyData, dynamicValuesAdMap.get(keyData));
				} else {
					// test.log(LogStatus.INFO,"This"+key+"is not captured
					// either
					// from Feed or Console");
					System.out.println("This" + keyData + "is not captured either from Feed or Console");
				}
			}
			/*
			 * System.out.println("********" + dynamicValuesMap +
			 * "************"); System.out.println("********" + staticValuesMap
			 * + "************"); System.out.println("********" +
			 * dynamicValuesMap + "************");
			 */
			for (String keyExcel : excelValueMap.keySet()) {
				if (dynamicValuesFeedMap.containsKey(keyExcel)) {
					excelValueMap.put(keyExcel, dynamicValuesFeedMap.get(keyExcel));
				} else if (staticValuesMap.containsKey(keyExcel)) {
					excelValueMap.put(keyExcel, staticValuesMap.get(keyExcel));
				} else if (dynamicValuesAdMap.containsKey(keyExcel)) {
					excelValueMap.put(keyExcel, dynamicValuesAdMap.get(keyExcel));
				} else {
					System.out.println("Not there in static and Dynamic");
				}
			}
			System.out.println("********" + excelValueMap + "************");
			/*
			 * System.out.println(
			 * "===================================Excel captured============="
			 * ); System.out.println(excelValueMap.size());
			 * System.out.println(excelValueMap); System.out.println(
			 * "===================================Dynamically captured============="
			 * ); System.out.println(KeyValues);
			 */
			seriesOfEvents.add(KeyValues);
			// System.out.println("**********************************************"
			// + KeyValues.get("s:event:type") +
			// "*****************************");
			if (KeyValues.get(HBEventType).equals(HBEventVal)) {
				test.log(LogStatus.INFO,
						"<b><font color=\"blue\">" + "Validating Event--->" + KeyValues.get("s:event:type") + "*-->*"
								+ counter + "Event Fired On---->" + HBUrlsTimeStamp.get(counter) + "</font></b>");
				test.log(LogStatus.INFO, "===================================Excel captured=============");
				test.log(LogStatus.INFO, "" + excelValueMap);
				/*
				 * test.log(LogStatus.INFO,
				 * "===================================Dynamically captured============="
				 * ); test.log(LogStatus.INFO, "" + KeyValues);
				 */
				test.log(LogStatus.INFO, "<b><font color=\"purple\">Parameter" + "---------" + "Expected" + "---------"
						+ "Actual" + "---------" + "Status</b>");
				for (int i = 0; i < excelValueMap.size(); i++) {
					// Comparing Parameter From HAR file entries and Parameter
					// from excel
					if (KeyValues.containsKey(excelValueMap.keySet().toArray()[i])) {
						if (excelValueMap.get(excelValueMap.keySet().toArray()[i])
								.equals(KeyValues.get(excelValueMap.keySet().toArray()[i]))) {
							System.out.println("PASS");
							System.out.println("Parameter:" + excelValueMap.keySet().toArray()[i] + "Expected:"
									+ excelValueMap.get(excelValueMap.keySet().toArray()[i]) + "Actual:"
									+ KeyValues.get(excelValueMap.keySet().toArray()[i]));
							test.log(LogStatus.INFO, "<font color=\"black\">" + excelValueMap.keySet().toArray()[i]
									+ "--------->" + excelValueMap.get(excelValueMap.keySet().toArray()[i]) + ""
									+ "--------->" + "</font><font color=\"green\">"
									+ KeyValues.get(excelValueMap.keySet().toArray()[i]) + "--------->" + "PASS</b>");
						} else {
							System.out.println("==========FAIL=========");
							System.out.println("Parameter:" + excelValueMap.keySet().toArray()[i] + "Expected:"
									+ excelValueMap.get(excelValueMap.keySet().toArray()[i]) + "Actual:"
									+ KeyValues.get(excelValueMap.keySet().toArray()[i]));
							test.log(LogStatus.FAIL, "<font color=\"black\">" + excelValueMap.keySet().toArray()[i]
									+ "--------->" + excelValueMap.get(excelValueMap.keySet().toArray()[i])
									+ "--------->" + "</font><font color=\"red\">"
									+ KeyValues.get(excelValueMap.keySet().toArray()[i]) + "--------->" + "FAIL</b>");
						}
					} else {
						System.out.println("Parameter" + excelValueMap.keySet().toArray()[i] + "is missing");
						test.log(LogStatus.FAIL,
								"<font color=\"black\">" + excelValueMap.keySet().toArray()[i] + "--------->"
										+ excelValueMap.get(excelValueMap.keySet().toArray()[i]) + "--------->"
										+ "</font><font color=\"red\">" + excelValueMap.keySet().toArray()[i]
										+ " Parameter is missing" + "--------->" + "FAIL</b>");
						// test.log(LogStatus.FAIL, "Parameter" +
						// excelValueMap.keySet().toArray()[i] + "is missing");
					}
				}
				counter++;
			}
			System.out.println("========================END=============================");
		}
	}

	public static void validateAnalyticsHBwithoutDyn(ArrayList<String> comscoreUrls, String remove, String HbSheetName,
			String HBEventType, String HBEventVal, String responseUrl, String feedurl) throws Exception {
		String correctString;
		String queryString;
		String pattern;
		// Map is to get what are all the dynamic values in excel
		HashMap<String, String> dynamicValuesMap;
		// Map is to get Dynamic values from Feed
		HashMap<String, String> dynamicValuesFeedMap;
		// Map is for static values capture in excel
		HashMap<String, String> staticValuesMap;
		// Map is to get the dynamic adValues from console
		Map<String, String> dynamicValuesAdMap = null;
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
			pattern = "((?!<&)&(?!&))|(&&&&)|(&&)";
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
			System.out.println(KeyValues);
			HashMap<String, String> excelValueMap = new HashMap<String, String>();
			// System.out.println(getColumValues("Expected",
			// "Comscore").size());
			// System.out.println(parameterNames);
			ArrayList<String> parameterNames = null;
			ArrayList<String> parameterValues = null;
			for (int k = 1; k <= getColumValues("Parameter", HbSheetName).size() - 1; k++) {
				excelValueMap.put(getColumValues("Parameter", HbSheetName).get(k),
						getColumValues("Expected", HbSheetName).get(k));
			}
			dynamicValuesMap = new HashMap<String, String>();
			// dynamicValuesFeedMap =
			// feedResponse(feedurl);
			dynamicValuesFeedMap = feedResponse(feedurl);
			staticValuesMap = new HashMap<String, String>();
			// ArrayList<String> parameterNames = null;
			// ArrayList<String> parameterValues = null;
			// Get All Dynamic Parameters from Excel Sheet
			for (String expected : excelValueMap.keySet()) {
				if (excelValueMap.get(expected).equalsIgnoreCase("Dyn")) {
					dynamicValuesMap.put(expected, excelValueMap.get(expected));
				} else {
					staticValuesMap.put(expected, excelValueMap.get(expected));
				}
			}
			// Put All Dynamic values which are captured into excelvalues
			for (String keyData : dynamicValuesMap.keySet()) {
				System.out.println(keyData);
				if (dynamicValuesFeedMap.containsKey(keyData)) {
					dynamicValuesMap.put(keyData, dynamicValuesFeedMap.get(keyData));
				} else {
					// test.log(LogStatus.INFO,"This"+key+"is not captured
					// either
					// from Feed or Console");
					System.out.println("This" + keyData + "is not captured either from Feed or Console");
				}
			}
			/*
			 * System.out.println("********" + dynamicValuesMap +
			 * "************"); System.out.println("********" + staticValuesMap
			 * + "************"); System.out.println("********" +
			 * dynamicValuesMap + "************");
			 */
			for (String keyExcel : excelValueMap.keySet()) {
				if (dynamicValuesFeedMap.containsKey(keyExcel)) {
					excelValueMap.put(keyExcel, dynamicValuesFeedMap.get(keyExcel));
				} else if (staticValuesMap.containsKey(keyExcel)) {
					excelValueMap.put(keyExcel, staticValuesMap.get(keyExcel));
				} else {
					System.out.println("Not there in static and Dynamic");
				}
			}
			System.out.println("********" + excelValueMap + "************");
			/*
			 * System.out.println(
			 * "===================================Excel captured============="
			 * ); System.out.println(excelValueMap.size());
			 * System.out.println(excelValueMap); System.out.println(
			 * "===================================Dynamically captured============="
			 * ); System.out.println(KeyValues);
			 */
			seriesOfEvents.add(KeyValues);
			System.out.println("**********************************************" + KeyValues.get("s:event:type")
					+ "*****************************");
			if (KeyValues.get(HBEventType).equals(HBEventVal)) {
				// ////test.log(LogStatus.INFO,
				// "<b><font color=\"purple\">Parameter" + "---------" +
				// "Expected" + "---------" + "Actual" + "---------" +
				// "Status</b>");
				for (int i = 0; i < excelValueMap.size(); i++) {
					// Comparing Parameter From Charles and Parameter from excel
					if (KeyValues.containsKey(excelValueMap.keySet().toArray()[i])) {
						if (excelValueMap.get(excelValueMap.keySet().toArray()[i])
								.equals(KeyValues.get(excelValueMap.keySet().toArray()[i]))) {
							System.out.println("PASS");
							System.out.println("Parameter:" + excelValueMap.keySet().toArray()[i] + "Expected:"
									+ excelValueMap.get(excelValueMap.keySet().toArray()[i]) + "Actual:"
									+ KeyValues.get(excelValueMap.keySet().toArray()[i]));
							test.log(LogStatus.INFO, "<font color=\"black\">" + excelValueMap.keySet().toArray()[i]
									+ "--------->" + excelValueMap.get(excelValueMap.keySet().toArray()[i]) + ""
									+ "--------->" + "</font><font color=\"green\">"
									+ KeyValues.get(excelValueMap.keySet().toArray()[i]) + "--------->" + "PASS</b>");
						} else {
							System.out.println("==========FAIL=========");
							System.out.println("Parameter:" + excelValueMap.keySet().toArray()[i] + "Expected:"
									+ excelValueMap.get(excelValueMap.keySet().toArray()[i]) + "Actual:"
									+ KeyValues.get(excelValueMap.keySet().toArray()[i]));
							test.log(LogStatus.FAIL, "<font color=\"black\">" + excelValueMap.keySet().toArray()[i]
									+ "--------->" + excelValueMap.get(excelValueMap.keySet().toArray()[i])
									+ "--------->" + "</font><font color=\"red\">"
									+ KeyValues.get(excelValueMap.keySet().toArray()[i]) + "--------->" + "FAIL</b>");
						}
					} else {
						System.out.println("Parameter" + excelValueMap.keySet().toArray()[i] + "is missing");
						test.log(LogStatus.FAIL,
								"<font color=\"black\">" + excelValueMap.keySet().toArray()[i] + "--------->"
										+ excelValueMap.get(excelValueMap.keySet().toArray()[i]) + "--------->"
										+ "</font><font color=\"red\">" + excelValueMap.keySet().toArray()[i]
										+ " Parameter is missing" + "--------->" + "FAIL</b>");
						// test.log(LogStatus.FAIL, "Parameter" +
						// excelValueMap.keySet().toArray()[i] + "is missing");
					}
				}
			}
			test.log(LogStatus.INFO, "===================================Excel captured=============");
			test.log(LogStatus.INFO, "" + excelValueMap);

			// Commented the dynamically captured values
			/*
			 * test.log(LogStatus.INFO,
			 * "===================================Dynamically captured============="
			 * ); test.log(LogStatus.INFO, "" + KeyValues);
			 */
			System.out.println("========================END=============================");
		}
	}

	public static void HBValidationsVOD_Clips(ArrayList<String> comscoreUrls, String remove, String HbSheetName,
            String HBEventType, String HBEventVal, String HBAssestType, String HBAssestVal, String feedurl)
            throws Exception {
     String correctString;
     String queryString;
     String pattern;
     // Map is to get what are all the dynamic values in excel
     HashMap<String, String> dynamicValuesMap;
     // Map is to get Dynamic values from Feed
     HashMap<String, String> dynamicValuesFeedMap;
     // Map is for static values capture in excel
     HashMap<String, String> staticValuesMap;
     // Map is to get the dynamic adValues from console
     Map<String, String> dynamicValuesAdMap = null;
     ArrayList<String> comscoreUrlsModified = new ArrayList<String>();
     seriesOfEvents = new ArrayList<HashMap<String, String>>();

     // limiting events checking
     int EventCountCheck = 0;

     int j;
     for (j = 0; j < comscoreUrls.size(); j++) {
            queryString = comscoreUrls.get(j).replace(remove, "");
            System.out.println(queryString);
            comscoreUrlsModified.add(queryString);
     }
     for (String url : comscoreUrlsModified) {

            //System.out.println("========================START=============================");
            // System.out.println(comscoreUrlsModified);
            HashMap<String, String> KeyValues = new HashMap<String, String>();
            //System.out.println(comscoreUrlsModified);
            System.out.println((url.contains(HBEventType+"="+HBEventVal))&&(url.contains(HBAssestType+"="+HBAssestVal)));
            if((url.contains(HBEventType+"="+HBEventVal))&&(url.contains(HBAssestType+"="+HBAssestVal))){
            
            //Add the condition contains here
            String key = url;
            pattern = "((?!<&)&(?!&))|(&&&&)|(&&)";
            
            List<String> HB = Arrays.asList(key.replaceAll("\\s", "").split(pattern));

            // limiting the number of the events checking
            if (EventCountCheck >= 1) {
                  return;
            }
            for (int k = 0; k < HB.size(); k++) {

                  System.out.println(HB.get(k) + '\n');
                  List<String> parValue = Arrays.asList(HB.get(k).replaceAll("\\s", "").split("="));

                  correctString = stringCheck(parValue.get(1));
                  correctString.trim();
                  //System.out.println(parValue.get(0) + ":" + correctString);
                  KeyValues.put(parValue.get(0), correctString);
            }

            System.out.println(KeyValues);
            HashMap<String, String> excelValueMap = new HashMap<String, String>();
            
            ArrayList<String> parameterNames = null;
            ArrayList<String> parameterValues = null;
            for (int k = 1; k <= getColumValues("Parameter", HbSheetName).size() - 1; k++) {
                  excelValueMap.put(getColumValues("Parameter", HbSheetName).get(k),
                                getColumValues("Expected", HbSheetName).get(k));
            }
            dynamicValuesMap = new HashMap<String, String>();
            
            dynamicValuesFeedMap = feedResponse(feedurl);
            staticValuesMap = new HashMap<String, String>();
            
            // Get All Dynamic Parameters from Excel Sheet
            for (String expected : excelValueMap.keySet()) {
                  if (excelValueMap.get(expected).equalsIgnoreCase("Dyn")) {
                         dynamicValuesMap.put(expected, excelValueMap.get(expected));
                  } else {
                         staticValuesMap.put(expected, excelValueMap.get(expected));
                  }
            }
            // Put All Dynamic values which are captured into excelvalues
            for (String keyData : dynamicValuesMap.keySet()) {
                  System.out.println(keyData);

                  if (dynamicValuesFeedMap.containsKey(keyData)) {
                         dynamicValuesMap.put(keyData, dynamicValuesFeedMap.get(keyData));
                  } else {
                         
                         System.out.println("This" + keyData + "is not captured either from Feed or Console");
                  }
            }
            
            for (String keyExcel : excelValueMap.keySet()) {
                  if (dynamicValuesFeedMap.containsKey(keyExcel)) {
                         excelValueMap.put(keyExcel, dynamicValuesFeedMap.get(keyExcel));
                  } else if (staticValuesMap.containsKey(keyExcel)) {
                         excelValueMap.put(keyExcel, staticValuesMap.get(keyExcel));
                  } else {
                         //System.out.println("Not there in static and Dynamic");
                  }
            }
            System.out.println("********" + excelValueMap + "************");
            
            seriesOfEvents.add(KeyValues);
            String missedValues = "";
            

            if (KeyValues.get(HBEventType).equals(HBEventVal) && KeyValues.get(HBAssestType).equals(HBAssestVal)) {
                 
                  

                  for (int i = 0; i < excelValueMap.size(); i++) {
                         if (KeyValues.containsKey(excelValueMap.keySet().toArray()[i].toString())) {
                                

                                if (excelValueMap.get(excelValueMap.keySet().toArray()[i])
                                              .contains(KeyValues.get(excelValueMap.keySet().toArray()[i]))) {
                                       
                                       test.log(LogStatus.PASS, "<font color=\"black\">" + excelValueMap.keySet().toArray()[i]
                                                     + " = " + KeyValues.get(excelValueMap.keySet().toArray()[i]) + "</b>");

                                } else if (excelValueMap.get(excelValueMap.keySet().toArray()[i]).equals("Dyn")) {
                                       
                                       test.log(LogStatus.PASS, "<font color=\"black\">" + excelValueMap.keySet().toArray()[i]
                                                     + "  =  " + KeyValues.get(excelValueMap.keySet().toArray()[i]) + "</b>");
                                } else {
                                       
                                       test.log(LogStatus.WARNING, "<font color=\"orange\">" +
                                                     excelValueMap.keySet().toArray()[i]
                                                                  + " output result value mismatch, Charles O/P value is" + "  =  "
                                                                  + KeyValues.get(excelValueMap.keySet().toArray()[i]) + "</b>");

                                }

                         } else if (KeyValues
                                       .containsValue(excelValueMap.get(excelValueMap.keySet().toArray()[i].toString()))) {

                                test.log(LogStatus.WARNING, "<font color=\"orange\">" +
                                              excelValueMap.keySet().toArray()[i] + " Parameter value is miss match" + "</b>");

                         }

                         else {
                                
                                test.log(LogStatus.FAIL, "<font color=\"red\">" + excelValueMap.keySet().toArray()[i]
                                              + " Parameter is not displayed in Charles Log" + "</b>");

                         }

                  }

                   EventCountCheck++;
                  return ;
            }
            }
            
  }
     if (EventCountCheck <1) {
 		test.log(LogStatus.INFO,"<u><b><font color=\"orange\"><-------  Calls are not logged in Charles  -------></font></b></u>");
		}
     
     
}



	public static void HBValidationsLive(ArrayList<String> comscoreUrls, String remove, String HbSheetName,
			String HBEventType, String HBEventVal, String HBAssestType, String HBAssestVal) throws Exception {
		String correctString;
		String queryString;
		String pattern;
		// Map is to get what are all the dynamic values in excel
		HashMap<String, String> dynamicValuesMap;
		// Map is to get Dynamic values from Feed
		HashMap<String, String> dynamicValuesFeedMap;
		// Map is for static values capture in excel
		HashMap<String, String> staticValuesMap;
		// Map is to get the dynamic adValues from console
		Map<String, String> dynamicValuesAdMap = null;
		ArrayList<String> comscoreUrlsModified = new ArrayList<String>();
		seriesOfEvents = new ArrayList<HashMap<String, String>>();
		// limiting events checking
		int EventCountCheck = 0;
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
			  System.out.println((url.contains(HBEventType+"="+HBEventVal))&&(url.contains(HBAssestType+"="+HBAssestVal)));
	            if((url.contains(HBEventType+"="+HBEventVal))&&(url.contains(HBAssestType+"="+HBAssestVal))){
			System.out.println(comscoreUrlsModified);
			String key = url;
			// pattern = "(?<!&)&(?!&)";
			pattern = "((?!<&)&(?!&))|(&&&&)|(&&)";

			List<String> HB = Arrays.asList(key.replaceAll("\\s", "").split(pattern));
			if (EventCountCheck > 1) {
				return;
			}
			for (int k = 0; k < HB.size(); k++) {
				System.out.println(HB);
				System.out.println(HB.get(k) + '\n');
				List<String> parValue = Arrays.asList(HB.get(k).replaceAll("\\s", "").split("="));

				correctString = stringCheck(parValue.get(1));
				correctString.trim();
				System.out.println(parValue.get(0) + ":" + correctString);
				KeyValues.put(parValue.get(0), correctString);
			}
			HashMap<String, String> excelValueMap = new HashMap<String, String>();

			ArrayList<String> parameterNames = null;
			ArrayList<String> parameterValues = null;
			for (int k = 1; k <= getColumValues("Parameter", HbSheetName).size() - 1; k++) {
				excelValueMap.put(getColumValues("Parameter", HbSheetName).get(k),
						getColumValues("Expected", HbSheetName).get(k));
			}
			dynamicValuesMap = new HashMap<String, String>();

			staticValuesMap = new HashMap<String, String>();

			for (String expected : excelValueMap.keySet()) {
				if (excelValueMap.get(expected).equalsIgnoreCase("Dyn")) {
					dynamicValuesMap.put(expected, excelValueMap.get(expected));
				} else {
					staticValuesMap.put(expected, excelValueMap.get(expected));
				}
			}

			System.out.println("********" + excelValueMap + "************");

			seriesOfEvents.add(KeyValues);
			String missedValues = "";
			System.out.println("**********************************************" + KeyValues.get("s:event:type")
					+ "*****************************");
			// Added new parameter to limit the execution of events
			// "EventCountCheck"
			if (KeyValues.get(HBEventType).equals(HBEventVal) && KeyValues.get(HBAssestType).equals(HBAssestVal)) {
				EventCountCheck++;
				for (int i = 0; i < excelValueMap.size(); i++) {
					if (KeyValues.containsKey(excelValueMap.keySet().toArray()[i].toString())) {
						

						if (excelValueMap.get(excelValueMap.keySet().toArray()[i])
								.contains(KeyValues.get(excelValueMap.keySet().toArray()[i]))) {
							
							test.log(LogStatus.PASS, "<font color=\"black\">" + excelValueMap.keySet().toArray()[i]
									+ " = " + KeyValues.get(excelValueMap.keySet().toArray()[i]) + "</b>");

						} else if (excelValueMap.get(excelValueMap.keySet().toArray()[i]).equals("Dyn")) {
							
							test.log(LogStatus.PASS, "<font color=\"black\">" + excelValueMap.keySet().toArray()[i]
									+ "  =  " + KeyValues.get(excelValueMap.keySet().toArray()[i]) + "</b>");
						} else {
							
							test.log(LogStatus.WARNING,
									excelValueMap.keySet().toArray()[i]
											+ " output result value mismatch, Charles O/P value is" + "  =  "
											+ KeyValues.get(excelValueMap.keySet().toArray()[i]) + "</b>");

						}

					} else if (KeyValues
							.containsValue(excelValueMap.get(excelValueMap.keySet().toArray()[i].toString()))) {

						test.log(LogStatus.WARNING,
								excelValueMap.keySet().toArray()[i] + " Parameter value is miss match" + "</b>");

					}

					else {
						test.log(LogStatus.FAIL, excelValueMap.keySet().toArray()[i]
								+ " Parameter is not displayed in Charles Log" + "</b>");

					}

				}

				System.out.println("========================END=============================");
			}
			 EventCountCheck++;
             return ;
		}
		}
		if (EventCountCheck <1) {
	 		test.log(LogStatus.INFO,"<u><b><font color=\"orange\"><-------  Calls are not logged in Charles  -------></font></b></u>");
			}
	     
	}

	// Code for Analytics SC

	public static void validateAnalytics(ArrayList<String> comscoreUrls, HashMap<String, String> KeyValues,
			String HbSheetName) throws Exception {
		String correctString;
		String queryString;
		String pattern;
		// Map is to get what are all the dynamic values in excel
		HashMap<String, String> dynamicValuesMap;
		// Map is to get Dynamic values from Feed
		HashMap<String, String> dynamicValuesFeedMap;
		// Map is for static values capture in excel
		HashMap<String, String> staticValuesMap;
		// Map is to get the dynamic adValues from console
		Map<String, String> dynamicValuesAdMap = null;

		HashMap<String, String> excelValueMap = new HashMap<String, String>();
		// System.out.println(getColumValues("Expected",
		// "Comscore").size());
		// System.out.println(parameterNames);
		ArrayList<String> parameterNames = null;
		ArrayList<String> parameterValues = null;
		for (int k = 1; k <= getColumValues("Parameter", HbSheetName).size() - 1; k++) {
			excelValueMap.put(getColumValues("Parameter", HbSheetName).get(k),
					getColumValues("Expected", HbSheetName).get(k));
		}
		dynamicValuesMap = new HashMap<String, String>();
		// dynamicValuesFeedMap =
		// feedResponse(feedurl);
		// dynamicValuesFeedMap = feedResponse(feedurl);
		staticValuesMap = new HashMap<String, String>();
		// ArrayList<String> parameterNames = null;
		// ArrayList<String> parameterValues = null;
		// Get All Dynamic Parameters from Excel Sheet
		for (String expected : excelValueMap.keySet()) {
			if (excelValueMap.get(expected).equalsIgnoreCase("Dyn")) {
				dynamicValuesMap.put(expected, excelValueMap.get(expected));
			} else {
				staticValuesMap.put(expected, excelValueMap.get(expected));
			}
		}

		for (int i = 0; i < excelValueMap.size(); i++) {
			if (KeyValues.containsKey(excelValueMap.keySet().toArray()[i].toString())) {
				

				if (excelValueMap.get(excelValueMap.keySet().toArray()[i])
						.contains(KeyValues.get(excelValueMap.keySet().toArray()[i]))) {
					
					test.log(LogStatus.PASS, "<font color=\"black\">" + excelValueMap.keySet().toArray()[i] + " = "
							+ KeyValues.get(excelValueMap.keySet().toArray()[i]) + "</b>");

				} else if (excelValueMap.get(excelValueMap.keySet().toArray()[i]).equals("Dyn")) {
					
					test.log(LogStatus.PASS, "<font color=\"black\">" + excelValueMap.keySet().toArray()[i] + "  =  "
							+ KeyValues.get(excelValueMap.keySet().toArray()[i]) + "</b>");
				} else {
					
					test.log(LogStatus.WARNING, "<font color=\"orange\">" +
							excelValueMap.keySet().toArray()[i] + " output result value mismatch, Charles O/P value is"
									+ "  =  " + KeyValues.get(excelValueMap.keySet().toArray()[i]) + "</b>");

				}

			} else if (KeyValues.containsValue(excelValueMap.get(excelValueMap.keySet().toArray()[i].toString()))) {

				test.log(LogStatus.WARNING, "<font color=\"orange\">" +
						excelValueMap.keySet().toArray()[i] + " Parameter value is miss match" + "</b>");
			}

			else {
			
				test.log(LogStatus.FAIL, "<font color=\"red\">" +
						excelValueMap.keySet().toArray()[i] + " Parameter is not displayed in Charles Log" + "</b>");

			}

		}

		test.log(LogStatus.PASS, "========================END=============================");

		System.out.println("========================END=============================");

	}

	// Comscore Validation
	public static void validateComscore(ArrayList<String> comscoreUrls, String remove, String comscoreSheetName,
			String comscoreEventType, String comscoreEventVal, ArrayList<String> HBUrlsTimeStamp, String feedurl,
			String path) throws Exception {
		System.out.println("im in Comscore Validation");
		String correctString;
		String queryString;
		String pattern = "((?!<&)&(?!&))|(&&&&)|(&&)";
		// pattern = "(?<!&)&(?!&)";

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
			excelValueMap.put(getColumValues("Parameter", comscoreSheetName).get(k),
					getColumValues("Expected", comscoreSheetName).get(k));
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
				// test.log(LogStatus.INFO,"This"+key+"is not captured either
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
				KeyValues.put(parValue.get(0), correctString);
			}
			// seriesOfEvents.add(KeyValues);
			// System.out.println("**********************************************"
			// + KeyValues.get("s:event:type") +
			// "*****************************");
			if (KeyValues.containsKey(comscoreEventType)) {
				if (KeyValues.get(comscoreEventType).equals(comscoreEventVal)) {
					test.log(LogStatus.INFO,
							"<b><font color=\"blue\">" + "Validating Event--->" + KeyValues.get(comscoreEventType)
									+ "*-->*" + counter + "Event Fired On---->" + HBUrlsTimeStamp.get(counter)
									+ "</font></b>");
					test.log(LogStatus.INFO, "===================================Excel captured=============");
					test.log(LogStatus.INFO, "" + excelValueMap);
					test.log(LogStatus.INFO, "===================================Dynamically captured=============");
					test.log(LogStatus.INFO, "" + KeyValues);
					test.log(LogStatus.INFO, "<b><font color=\"purple\">Parameter" + "---------" + "Expected"
							+ "---------" + "Actual" + "---------" + "Status</b>");
					for (int i = 0; i < excelValueMap.size(); i++) {
						// Comparing Parameter From HAR file entries and
						// Parameter
						// from excel
						if (KeyValues.containsKey(excelValueMap.keySet().toArray()[i])) {
							if (excelValueMap.get(excelValueMap.keySet().toArray()[i])
									.equals(KeyValues.get(excelValueMap.keySet().toArray()[i]))) {
								System.out.println("PASS");
								System.out.println("Parameter:" + excelValueMap.keySet().toArray()[i] + "Expected:"
										+ excelValueMap.get(excelValueMap.keySet().toArray()[i]) + "Actual:"
										+ KeyValues.get(excelValueMap.keySet().toArray()[i]));
								test.log(LogStatus.INFO,
										"<font color=\"black\">" + excelValueMap.keySet().toArray()[i] + "--------->"
												+ excelValueMap.get(excelValueMap.keySet().toArray()[i]) + ""
												+ "--------->" + "</font><font color=\"green\">"
												+ KeyValues.get(excelValueMap.keySet().toArray()[i]) + "--------->"
												+ "PASS</b>");
							} else {
								System.out.println("==========FAIL=========");
								System.out.println("Parameter:" + excelValueMap.keySet().toArray()[i] + "Expected:"
										+ excelValueMap.get(excelValueMap.keySet().toArray()[i]) + "Actual:"
										+ KeyValues.get(excelValueMap.keySet().toArray()[i]));
								test.log(LogStatus.FAIL,
										"<font color=\"black\">" + excelValueMap.keySet().toArray()[i] + "--------->"
												+ excelValueMap.get(excelValueMap.keySet().toArray()[i]) + "--------->"
												+ "</font><font color=\"red\">"
												+ KeyValues.get(excelValueMap.keySet().toArray()[i]) + "--------->"
												+ "FAIL</b>");
							}
						} else {
							System.out.println("Parameter" + excelValueMap.keySet().toArray()[i] + "is missing");
							test.log(LogStatus.FAIL,
									"<font color=\"black\">" + excelValueMap.keySet().toArray()[i] + "--------->"
											+ excelValueMap.get(excelValueMap.keySet().toArray()[i]) + "--------->"
											+ "</font><font color=\"red\">" + excelValueMap.keySet().toArray()[i]
											+ "    Parameter is missing" + "--------->" + "FAIL</b>");
							test.log(LogStatus.FAIL, "Parameter" + excelValueMap.keySet().toArray()[i] + "is missing");
						}
					}
					counter++;
				}
			}
			System.out.println("========================END=============================");
		}
		// seriesOfEvents(seriesOfEvents, HBUrlsTimeStamp);
	}

	public static void seriesOfEvents(ArrayList<String> comscoreUrls, String remove, ArrayList<String> HBUrlsTimeStamp,
			String paramName) {
		test.log(LogStatus.INFO, "=========================Series of Events Fired=================");
		seriesOfEvents = new ArrayList<HashMap<String, String>>();
		ArrayList<String> comscoreUrlsModified = new ArrayList<String>();
		int i = 0;
		int j;
		String correctString;
		String queryString;
		String pattern = "((?!<&)&(?!&))|(&&&&)|(&&)";
		for (j = 0; j < comscoreUrls.size(); j++) {
			queryString = comscoreUrls.get(j).replace(remove, "");
			System.out.println(queryString);
			comscoreUrlsModified.add(queryString);
		}
		for (String url : comscoreUrlsModified) {
			HashMap<String, String> KeyValues = new HashMap<String, String>();
			System.out.println(comscoreUrlsModified);
			String key = url;
			List<String> HB = Arrays.asList(key.replaceAll("\\s", "").split(pattern));
			for (int k = 0; k < HB.size() - 1; k++) {
				List<String> parValue = Arrays.asList(HB.get(k).replaceAll("\\s", "").split("="));
				if (parValue.size() <= 1)
					continue;
				correctString = stringCheck(parValue.get(1));
				KeyValues.put(parValue.get(0), correctString);
			}
			seriesOfEvents.add(KeyValues);
			System.out.println("**********************************************" + KeyValues.get(paramName)
					+ "*****************************");
		}
		for (HashMap<String, String> eventType : seriesOfEvents) {
			System.out.println(eventType.get(paramName));
			test.log(LogStatus.INFO, "<b><font color=\"blue\">" + eventType.get(paramName) + "*-->*"
					+ "Event Fired On---->" + HBUrlsTimeStamp.get(i) + "</font></b>");
			i++;
		}
	}

	private static String stringCheck(String data) {
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
		data = data.replace("+", " ");
		return data;
	}

	public static Map<String, String> validateiOSAssetDetailsCosole(String path, String url) throws Exception {
		// ,String url
		// TODO Auto-generated method stub
		HashMap<String, String> dynamicValuesMap = new HashMap<String, String>();
		Map<String, String> addynamicValuesMap = new HashMap<String, String>();
		Map<String, String> feedValuesMap = new HashMap<String, String>();
		String s = "";
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
		// System.out.println(st);
		String[] vod = st.split("VOD ID:");
		String[] vod1 = vod[1].split("VOD Series");
		System.out.println("VOD ID = " + vod1[0].substring(0, vod1[0].length() - 14));
		String guid = vod1[0].substring(0, vod1[0].length() - 14);
		test.log(LogStatus.INFO,
				"************<b><font color=\"purple\">" + "VOD Asset Details From Console*************</font></b>");
		test.log(LogStatus.INFO, "VOD ID = " + vod1[0].substring(0, vod1[0].length() - 14));
		List<String> typeKey = Arrays.asList(guid.replaceAll("\\s", "").split("_"));
		System.out.println(typeKey);
		dynamicValuesMap.put("VOD ID", typeKey.get(2).trim());
		test.log(LogStatus.INFO, "VOD ID", vod1[0].substring(0, vod1[0].length() - 14));
		String[] vodseries = st.split("VOD Series:");
		String[] vodseries1 = vodseries[1].split("VOD Season:");
		System.out.println("VOD Series = " + vodseries1[0].substring(0, vodseries1[0].length() - 14));
		test.log(LogStatus.INFO, "VOD Series = " + vodseries1[0].substring(0, vodseries1[0].length() - 14));
		dynamicValuesMap.put("VOD Series", vodseries1[0].substring(0, vodseries1[0].length() - 14).trim());
		String[] VODSeason = st.split("VOD Season:");
		String[] VODSeason1 = VODSeason[1].split("VOD Episode Number:");
		System.out.println("VOD Season = " + VODSeason1[0].substring(0, VODSeason1[0].length() - 14));
		test.log(LogStatus.INFO, "VOD Season = " + VODSeason1[0].substring(0, VODSeason1[0].length() - 14));
		dynamicValuesMap.put("VOD Season", VODSeason1[0].substring(0, VODSeason1[0].length() - 14).trim());
		String[] VODEpisodeNumber = st.split("VOD Episode Number:");
		String[] VODEpisodeNumber1 = VODEpisodeNumber[1].split("VOD Episode:");
		System.out.println(
				"VOD Episode Number = " + VODEpisodeNumber1[0].substring(0, VODEpisodeNumber1[0].length() - 14));
		test.log(LogStatus.INFO,
				"VOD Episode Number = " + VODEpisodeNumber1[0].substring(0, VODEpisodeNumber1[0].length() - 14));
		dynamicValuesMap.put("VOD Episode Number",
				VODEpisodeNumber1[0].substring(0, VODEpisodeNumber1[0].length() - 14).trim());
		String[] VODEpisode = st.split("VOD Episode:");
		String[] VODEpisode1 = VODEpisode[1].split("VOD Rating:");
		System.out.println("VOD Episode = " + VODEpisode1[0].substring(0, VODEpisode1[0].length() - 14));
		test.log(LogStatus.INFO, "VOD Episode = " + VODEpisode1[0].substring(0, VODEpisode1[0].length() - 14));
		dynamicValuesMap.put("VOD Episode", VODEpisode1[0].substring(0, VODEpisode1[0].length() - 14).trim());
		String[] VODRating = st.split("VOD Rating:");
		String[] VODRating1 = VODRating[1].split("ContentLoadStatus:");
		System.out.println("VOD Rating = " + VODRating1[0].substring(0, VODRating1[0].length() - 14));
		test.log(LogStatus.INFO, "VOD Rating = " + VODRating1[0].substring(0, VODRating1[0].length() - 14));
		// dynamicValuesMap.put("VOD Rating", VODRating1[0].substring(0,
		// VODRating1[0].length() - 14));
		feedResponse(url);
		test.log(LogStatus.INFO, "*******Validating VOD Asset Details with Feed Url******");
		compareTwoMaps(dynamicValuesMap, assetValuesFeedMap);
		return dynamicValuesMap;
	}

	public static HashMap<String, Map<String, String>> validateAdiOSConsole(String path) throws IOException {
		// TODO Auto-generated method stub
		Map<String, String> addynamicValuesMap = new HashMap<String, String>();
		Map<String, String> dynamicValuesMap = new HashMap<String, String>();
		String s = "";
		String adString;
		String separator;
		String adSeparator;
		String adTypeSeparator;
		String preRolladName;
		String midRolladName;
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
		if (platFormName.equalsIgnoreCase("iOS")) {
			adString = "CPAdObserver CPAdObserverAdBreakInstance";
			separator = ";";
			adSeparator = "CPAdIdKey =";
			adTypeSeparator = "CPBreakTypeKey";
			preRolladName = "pre-roll";
			midRolladName = "mid-roll";
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
			test.log(LogStatus.INFO, "Total Number of AdPod = " + (j));
		} else {
			adString = "CPAdObserver : CPAdObserverAdBreakInstance";
			separator = ",";
			adSeparator = "CPAdIdKey=";
			adTypeSeparator = "CPAdBreakTypeKey";
			preRolladName = "preroll";
			midRolladName = "midRoll";
		}
		test.log(LogStatus.INFO, "===================Ad Details From Console=============");
		String[] adpartText = st.split(adString);
		System.out.println("===================Ad Details=============");
		// System.out.println("CPAdObserverAdBreak"
		// + adpartText[2].split("CPAdObserver
		// CPAdObserverAdBreakInstanceEnded")[0]
		// + "CPAdObserver CPAdObserverAdBreakInstanceEnded");
		ArrayList<String> adTypes = new ArrayList<>(); // preRoll1,....,midRoll1,midRoll2,....
		HashMap<String, String> dataMap;
		HashMap<String, Map<String, String>> adsMap = new HashMap<>();
		int preRollcount = 1;
		int midCount = 1;
		for (int i = 1; i < adpartText.length; i++) {
			if (adpartText[i].contains(adSeparator)) {
				dataMap = new HashMap<>();
				String indAdData = adpartText[i].replaceAll("\\{", "").trim();
				String finalData = indAdData.split("}")[0];
				String[] data = finalData.split(separator);
				if (platFormName.equalsIgnoreCase("iOS")) {
					noOfAds = data.length - 1;
				} else {
					noOfAds = data.length;
				}
				for (int temp = 0; temp < noOfAds; temp++) {
					String[] keyValues = data[temp].split("=");
					String key = keyValues[0].trim();
					String value = keyValues[1].trim();
					test.log(LogStatus.INFO, key + ":" + value);
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
							dataMap.put("s:asset:pod_name", preRolladName);
							preRollcount++;
						} else {
							adTypes.add("Midroll" + midCount);
							dataMap.put("s:asset:pod_name", midRolladName);
							midCount++;
						}
					}
				}
				test.log(LogStatus.INFO, "*************************************");
				for (String dynKey : dynamicValuesMap.keySet()) {
					dataMap.put(dynKey, dynamicValuesMap.get(dynKey));
				}
				adsMap.put(adTypes.get(adTypes.size() - 1), dataMap);
			}
		}
		System.out.println(adsMap);
		return adsMap;
	}

	public static HashMap<String, String> feedResponse(String responseUrl) throws Exception {
		// String Response = SendGetPost.sendGet(url);
		System.out.println(responseUrl);
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<String, Object>();
		// convert JSON string to Map
		map = objectMapper.readValue(responseUrl, new TypeReference<Map<String, Object>>() {
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
			// setting parameter for s:meta:videoinitiate
			dynamicValuesMap.put("s:meta:videoinitiate", "Manual");
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
			// adPositions = (ArrayList<Map<String, Object>>)
			// show.get("plmedia$chapters");
			// System.out.println(show);
			adPots = new HashMap<>();
			// System.out.println(show.get("plmedia$chapters"));
			// for (Map<String, Object> series : adPositions) {
			// String pos = String.valueOf(series.get("plmedia$startTime"));
			// positions.add(pos);
			// adPots.put("seekTo", positions);
			// }
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

	public static Map<String, String> getGlobalConfig(String Response)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, String> globalSettings = new HashMap<String, String>();
		;
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> settings = new HashMap<String, Object>();
		// convert JSON string to Map
		map = objectMapper.readValue(Response, new TypeReference<Map<String, Object>>() {
		});
		@SuppressWarnings("unchecked")
		ArrayList<String> global = new ArrayList<String>();
		HashMap<String, Object> data = (HashMap<String, Object>) map.get("globalConfig");
		// ArrayList<Map<String, Object>> data = (ArrayList<Map<String,
		// Object>>) map.get("globalConfig");
		Map<String, Object> attributes = (Map<String, Object>) data.get("settings");
		boolean forceServiceZip = (boolean) attributes.get("forceServiceZip");
		boolean allowOverrideGeolocation = (boolean) attributes.get("allowOverrideGeolocation");
		boolean collectGeolocationOnInit = (boolean) attributes.get("collectGeolocationOnInit");
		globalSettings.put("forceServiceZip", String.valueOf(collectGeolocationOnInit));
		globalSettings.put("allowOverrideGeolocation", String.valueOf(allowOverrideGeolocation));
		globalSettings.put("collectGeolocationOnInit", String.valueOf(collectGeolocationOnInit));
		System.out.println(String.valueOf("collectGeolocationOnInit"));
		System.out.println(globalSettings);
		System.out.println("**********Global Config Settings********");
		test.log(LogStatus.INFO,
				"<b><font color=\"purple\">" + "*******Global Config Settings***********" + "</font></b>");
		for (String key : globalSettings.keySet()) {
			System.out.println(key + ":" + globalSettings.get(key));
			test.log(LogStatus.INFO, "<b><font color=\"green\">" + key + ":" + globalSettings.get(key) + "</font></b>");
		}
		return globalSettings;
	}

	/************** Image Comparison *****************/
	public String elementScreenshot(WebElement ele, String fileLocation, String className) throws IOException {
		File screenshot = ((TakesScreenshot) appiumDriver).getScreenshotAs(OutputType.FILE);
		BufferedImage fullImg = ImageIO.read(screenshot);
		// Point point = ele.getLocation();
		int eleWidth = ele.getSize().getWidth();
		int eleHeight = ele.getSize().getHeight();
		// Crop the entire page screenshot to get only element screenshot
		BufferedImage eleScreenshot = fullImg.getSubimage(ele.getLocation().getX(), ele.getLocation().getY(), eleWidth,
				eleHeight);
		ImageIO.write(eleScreenshot, "png", screenshot);
		String ImageLocation = fileLocation + "/" + className + counter + ".png";
		System.out.println(ImageLocation);
		// Copy the element screenshot to disk
		FileUtils.copyFile(screenshot, new File(ImageLocation));
		counter++;
		test.log(LogStatus.INFO, "Screenshot" + test.addScreenCapture(ImageLocation));
		return ImageLocation;
	}

	public void eventsIterator(ArrayList<String> HBUrls, String hbUrl,String SheetName , String streamType , String feedUrl) throws Exception{
        String eventType ,eventValue , assetType , assetValue  , sheetname , sheetname_Live, ExecutionStatus;
        
        if((streamType.equalsIgnoreCase("episode"))||(streamType.equalsIgnoreCase("clip"))){
               for (int k = 1; k <= getColumValues("ExecutionStatus", SheetName).size() - 1; k++) {
            	   System.out.println(getColumValues("ExecutionStatus", SheetName).size());
                     // excelValueMap.put(getColumValues("Parameter", comscoreSheetName).get(k),getColumValues("Expected", comscoreSheetName).get(k));
                     eventType = getColumValues("eventType", SheetName).get(k) ;
                     eventValue = getColumValues("eventValue",SheetName ).get(k);
                     assetType = getColumValues("assetType", SheetName).get(k);
                     assetValue = getColumValues("assetValue", SheetName).get(k) ;
                     sheetname = getColumValues("sheetname", SheetName).get(k) ;
                     ExecutionStatus = getColumValues("ExecutionStatus", SheetName).get(k) ;
                     
               if (ExecutionStatus.contains("Yes")) {
				
			     
                     test.log(LogStatus.INFO,"<u><b><font color=\"purple\"><-------Validating "+  sheetname  + " CALL -------></font></b></u>");
                     
                     HBValidationsVOD_Clips(HBUrls, hbUrl, sheetname, eventType, eventValue,assetType, assetValue, feedUrl);
               } 
               
               else {
				System.out.println(sheetname + "VOD Execution status is No/Null");
				 test.log(LogStatus.INFO,"<u><b><font color=\"blue\"><-------"+  sheetname  + " Execution Status is set to No/Null -------></font></b></u>");
			}
               } 
         }
        else if (streamType.equalsIgnoreCase("live")) {
               for (int k = 1; k <= getColumValues("ExecutionStatus", SheetName).size() - 1; k++) {
                     eventType = getColumValues("eventType", SheetName).get(k) ;
                     eventValue = getColumValues("eventValue",SheetName ).get(k);
                     assetType = getColumValues("assetType", SheetName).get(k);
                     assetValue = getColumValues("assetValue", SheetName).get(k) ;
                     sheetname = getColumValues("sheetname", SheetName).get(k) ;
                     ExecutionStatus = getColumValues("ExecutionStatus", SheetName).get(k) ;
                     
                     if (ExecutionStatus.contains("Yes")) {
         				
        			     
                         test.log(LogStatus.INFO,"<u><b><font color=\"purple\"><-------Validating "+  sheetname  + " CALL -------></font></b></u>");
                         
                         HBValidationsLive(HBUrls, hbUrl, sheetname, eventType, eventValue,assetType, assetValue);
                   } 
                     
                     else {
                    	 System.out.println(sheetname + "VOD Execution status is No/Null");
                    	 test.log(LogStatus.INFO,"<u><b><font color=\"blue\"><-------"+  sheetname  + " Execution Status is set to No/Null -------></font></b></u>");
         			}
                   
                   
                     }
        }
 }

}
