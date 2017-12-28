package com.mop.qa.pageobject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;
import net.lightbody.bmp.core.har.HarContent;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.core.har.HarResponse;
import net.lightbody.bmp.util.BrowserMobHttpUtil;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.gargoylesoftware.htmlunit.javascript.host.geo.Geolocation;
import com.mop.qa.Utilities.JSONParser;
import com.mop.qa.Utilities.ReadDataSheet;
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
public class PlayBackFunctions extends PageBase {
	public PlayBackFunctions() {
		super();
	}

	public static List<String> assetDetails;
	CommonFunctions common = new CommonFunctions();
	String hbUrl = "https://nbcume.hb.omtrdc.net/";
	String comscoreUrl = "http://b.scorecardresearch.com/";
	String adBeaconUrl = "http://29773.s.fwmrm.net";
	/* String optionsWE="name=Options"; */
	/* CPC Elements************ */
	@FindBy(name = "Options")
	protected WebElement optionsWE;
	@FindBy(name = "CHANGE")
	protected WebElement changeWE;
	@FindBy(name = "GN - Prod Release")
	protected WebElement configWE;
	@FindBy(xpath = "//UIAApplication[1]/UIAWindow[2]/UIATextView[1]")
	protected WebElement harnessWE;
	@FindBy(xpath = "//UIAApplication[1]/UIAWindow[2]/UIASegmentedControl[1]/UIAButton[3]")
	protected WebElement envSelectWE;
	@FindBy(xpath = "//UIAApplication[1]/UIAWindow[2]/UIATextField[3]")
	protected WebElement locationZipWE;
	@FindBy(xpath = "//UIAApplication[1]/UIAWindow[2]/UIAScrollView[1]/UIAWebView[1]/UIAImage[2]")
	protected WebElement signinWE;
	/* =====Directv Elements========== */
	@FindBy(xpath = "//UIAApplication[1]/UIAWindow[2]/UIAScrollView[1]/UIAWebView[1]/UIATextField[1]")
	protected WebElement directvUserNameWE;
	@FindBy(xpath = "//UIAApplication[1]/UIAWindow[2]/UIAScrollView[1]/UIAWebView[1]/UIASecureTextField[1]")
	protected WebElement directvPasswordWE;
	@FindBy(xpath = " //UIAApplication[1]/UIAWindow[2]/UIAScrollView[1]/UIAWebView[1]/UIAButton[1]")
	protected WebElement directvSigninWE;
	@FindBy(name = "DIRECTV")
	protected WebElement directvSelectWE;
	@FindBy(name = "Done")
	protected WebElement doneWE;
	@FindBy(name = "Set")
	protected WebElement setWE;
	@FindBy(name = "Select...")
	protected WebElement selectWE;
	@FindBy(name = "VOD Listing")
	protected WebElement selectVODWE;
	@FindBy(name = "NBC")
	protected WebElement channelWE;
	@FindBy(name = "Bravo")
	protected WebElement bravoLiveWE;
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
	@FindBy(name = "Shrink")
	protected WebElement shrinkWE;
	@FindBy(name = "3512853: Mom")
	protected WebElement episodeWE;
	@FindBy(xpath = "//UIAApplication[1]/UIAWindow[2]/UIATableView[1]/UIATableGroup[1]/UIASearchBar[1]")
	protected WebElement searchMVPDWE;
	@FindBy(name = "Optimum")
	protected WebElement mvpdWE;
	@FindBy(xpath = "//UIAApplication[1]/UIAWindow[2]/UIAScrollView[1]/UIAWebView[1]/UIATextField[1]")
	protected WebElement userIDWE;
	@FindBy(xpath = "//UIAApplication[1]/UIAWindow[2]/UIAScrollView[1]/UIAWebView[1]/UIASecureTextField[1]")
	protected WebElement passwordWE;
	// Password
	// UIAApplication[1]/UIAWindow[2]/UIAScrollView[1]/UIAWebView[1]/UIASecureTextField[1]
	@FindBy(name = "Clear")
	protected WebElement clearWE;
	@FindBy(name = "pause")
	protected WebElement pauseWE;
	@FindBy(xpath = "//UIAApplication[1]/UIAWindow[2]/UIAButton[11]")
	protected WebElement stopWE;
	@FindBy(xpath = "//UIAApplication[1]/UIAWindow[2]/UIATextField[4]")
	protected WebElement rfWE;
	int j = 2;
	int i = 0;
	// common Elements
	@AndroidFindBy(id = "btn_signIn")
	@iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAButton[1]")
	public WebElement signInButton;
	// Android Elements
	@FindBy(id = "android:id/alertTitle")
	protected WebElement alertWE;
	@FindBy(name = "nbc")
	protected WebElement channelIDWE;
	@FindBy(id = "com.nbcuni.cloudpath.prod:id/choosePlayer")
	protected WebElement liveVODbtnWE;
	@FindBy(id = "com.nbcuni.cloudpath.prod:id/editText")
	protected WebElement mvpdSearchWE;
	@FindBy(id = "com.nbcuni.cloudpath.prod:id/select_channel")
	protected WebElement channelWEA;
	@FindBy(name = "Blindspot")
	protected WebElement seriesWEA;
	@FindBy(id = "android:id/text1")
	protected WebElement episodeWEA;
	@FindBy(name = "Optimum ID")
	protected WebElement useridWEA;
	@FindBy(id = "com.nbcuni.cloudpath.prod:id/overlay_pause")
	protected WebElement pauseWEA;
	@FindBy(id = "com.nbcuni.cloudpath.prod:id/txtEventsLog")
	protected WebElement harnessWEA;
	@FindBy(name = "Password")
	protected WebElement passwordWEA;
	@FindBy(id = "com.nbcuni.cloudpath.prod:id/resume_time")
	protected WebElement rfWEA;
	@FindBy(id = "com.nbcuni.cloudpath.prod:id/select_mvpd")
	protected WebElement selectConfigWE;
	@FindBy(id = "com.nbcuni.cloudpath.prod:id/videoContainer")
	protected WebElement videoPlayerWE;
	ArrayList<String> HBUrls = new ArrayList<String>();
	ArrayList<String> comscoreUrls = new ArrayList<String>();
	ArrayList<String> adBeaconsUrls = new ArrayList<String>();
	ArrayList<String> configURLs = new ArrayList<String>();
	ArrayList<String> nielsonUrls = new ArrayList<String>();
	ArrayList<String> HBUrlsTimeStamp = new ArrayList<String>();
	ArrayList<String> comscoreUrlsTimeStamp = new ArrayList<String>();
	ArrayList<String> adBeaconsUrlsTimeStamp = new ArrayList<String>();
	ArrayList<String> configURLsTimeStamp = new ArrayList<String>();
	ArrayList<String> nielsonUrlsTimeStamp = new ArrayList<String>();
	ArrayList<String> feedUrls = new ArrayList<String>();
	ArrayList<String> feedUrlsTimeStamp = new ArrayList<String>();
	ArrayList<String> tkxUrlsRequest = new ArrayList<String>();
	ArrayList<String> tkxUrlsResponse = new ArrayList<String>();
	ArrayList<String> tkxUrlsTimeStamp = new ArrayList<String>();
	String URL = null;
	String Response;
	String appKey;
	String secretKey;
	HashMap<String, String> excelValueMap = new HashMap<String, String>();
	HashMap<String, String> actualValueMap;
	ReadDataSheet rd = new ReadDataSheet();
	String tkxUrl = "http://tkx-acc.nbc.anvato.net/rest/v2/tve/?";
	HashMap<String, Object> tkxValues;

	// Method which will play Authenticated VOD
	public void playLive(String config, String Env, String UserName, String Password, String SearchMVPD, String configAnd, String showName, String season, String episodeName, String showNameA, String EpisodeNameA, String envA, String LocationZip, String callSignExpected) throws Exception {
		try {
			common.selectConfig(config, Env);
			click(locationZipWE, "clicked on location zip");
			enterText(locationZipWE, LocationZip, "geoZip");
			Thread.sleep(1000);
			click(setWE, "Set");
			click(selectWE, "Selcting channel");
			waitForVisibilityOfElement(channelWE);
			click(channelWE, "Live");
			Thread.sleep(2000);
			// common.direcTVlogin(SearchMVPD, UserName, Password);
			click(expandWE, "Expand clicked");
			String consoleText = common.getHarnessLog();
			Thread.sleep(40000);
			System.out.println("CallSign:" + callSignExpected);
			test.log(LogStatus.INFO, "******<b><font color=\"purple\">Validating Callsign with console data**********</b></font>");
			if (consoleText.contains("CallSign:" + callSignExpected)) {
				test.log(LogStatus.PASS, "Calllsign details are displayed correctly in console CallSign:" + callSignExpected);
			} else {
				test.log(LogStatus.FAIL, "Calllsign details are not displayed correctly in console CallSign:" + callSignExpected);
			}
			List<HarEntry> entries = getHar();
			System.out.println(entries);
			for (HarEntry entry : entries) {
				String url = entry.getRequest().getUrl();
				if (url.contains(tkxUrl)) {
					tkxUrlsRequest.add(entry.getRequest().getUrl());
					tkxUrlsResponse.add(entry.getResponse().getContent().getText());
					tkxUrlsTimeStamp.add(entry.getStartedDateTime().toString());
					tkxValues = common.getTKXValues(entry.getResponse().getContent().getText());
					for (String key : tkxValues.keySet()) {
						test.log(LogStatus.INFO, key + ":" + tkxValues.get(key));
						if (key.equals("geoZip")) {
							if (tkxValues.get(key).equals(LocationZip)) {
								test.log(LogStatus.PASS, "<b><font color=\"green\">Geo Zip is displayed correctly in TKX Response" + key + ":" + tkxValues.get(key) + "</b></font>");
							} else {
								test.log(LogStatus.FAIL, "<b><font color=\"red\">Geo Zip is not displayed correctly in TKX Response" + key + ":" + tkxValues.get(key) + "</b></font>");
							}
						}
						if (key.equals("callsign")) {
							test.log(LogStatus.INFO, "Actual CallSign" + tkxValues.get(key).toString() + "\n Expected Callsign" + callSignExpected);
							if (tkxValues.get(key).toString().equalsIgnoreCase(callSignExpected)) {
								test.log(LogStatus.PASS, "<b><font color=\"green\">Callsign is displayed correctly in TKX Response" + key + ":" + tkxValues.get(key) + "</b></font>");
							} else {
								test.log(LogStatus.FAIL, "<b><font color=\"red\">Callsign is not displayed correctly in TKX Response" + key + ":" + tkxValues.get(key) + "</b></font>");
							}
						}
					}
				}
			}
			test.log(LogStatus.INFO, "<b><font color=\"purple\">========================TKX URLs====================</font></b>");
			for (int i = 0; i < tkxUrlsRequest.size(); i++) {
				test.log(LogStatus.INFO, "<b><font color=\"green\">Request Fired------->" + tkxUrlsTimeStamp.get(i) + "</font></b>");
				test.log(LogStatus.INFO, tkxUrlsRequest.get(i));
				test.log(LogStatus.INFO, "=====<b><font color=\"orange\">Response</b></font>======");
				test.log(LogStatus.INFO, tkxUrlsResponse.get(i));
			}
		} catch (Exception e) {
			test.log(LogStatus.FAIL, e);
		}
	}

	public void playVOD(String config, String Env, String UserName, String Password, String SearchMVPD, String configAnd, String showName, String season, String episodeName, String showNameA, String EpisodeNameA, String envA) throws Exception {
		// platFormName = TestRunner.platFormName();
		if (platFormName.equalsIgnoreCase("iOS")) {
			System.out.println(server);
			common.selectConfig(config, Env);
			common.selectChannel();
			common.selectShow(showName, season, episodeName);
			String hbUrl = "http://nbcume.hb.omtrdc.net/?";
			String comscoreUrl = "https://sb.scorecardresearch.com/p2";
			String adBeaconUrl = "http://29773.s.fwmrm.net/";
			String nielsonUrl = "https://secure-dcr-cert.imrworldwide.com";
			// common.selectMVPD(SearchMVPD, UserName, Password);
			waitForVisibilityOfElement(optionsWE);
			click(expandWE, "Expand clicked");
			// System.out.println(getText(harnessWE));
			Thread.sleep(10000);
			click(shrinkWE, "Expand clicked");
			Thread.sleep(10000);
			click(expandWE, "Expand clicked");
			Thread.sleep(10000);
			click(shrinkWE, "Expand clicked");
			List<HarEntry> entries = getHar();
			for (HarEntry entry : entries) {
				System.out.println(entry.getRequest().getUrl());
				// SendGetPost.sendGet(entry.getRequest().getUrl());
				test.log(LogStatus.INFO, "Request URL-->" + entry.getRequest().getUrl());
				// test.log(LogStatus.INFO, "Request Headers-->" +
				// entry.getRequest().getHeaders());
				test.log(LogStatus.INFO, "Request Fired" + entry.getStartedDateTime());
				test.log(LogStatus.INFO, "Response Code" + entry.getResponse().getStatus());
				test.log(LogStatus.INFO, "Responsebody" + entry.getResponse().getContent().getText());
				System.out.println(entry.getResponse().getContent().getText());
			}
		} else {
			common.selectConfig(configAnd, envA);
			common.selectChannel();
			common.selectShow(showNameA, season, EpisodeNameA);
			String hbUrl = "https://nbcume.hb.omtrdc.net/";
			String comscoreUrl = "http://b.scorecardresearch.com/";
			String adBeaconUrl = "http://29773.s.fwmrm.net";
			// waitForVisibilityOfElement(pauseWEA);
			Thread.sleep(80000);
			// click(pauseWEA, "Pause");
			String path = common.createFile(common.getHarnessLog(), platFormName, currentTest);
			// Validates ads from the console text
			common.validateAdiOSConsole(path);
			/*
			 * List<HarEntry> entries = getHar(); for (HarEntry entry : entries)
			 * { System.out.println(entry.getRequest().getUrl()); if
			 * (entry.getRequest().getUrl().startsWith(adBeaconUrl)) {
			 * System.out.println("im n Ad call validation");
			 * test.log(LogStatus.INFO,
			 * "========================All Ad  URLs====================");
			 * adBeaconsUrls.add(entry.getRequest().getUrl()); } else if
			 * (entry.getRequest().getUrl().startsWith(comscoreUrl)) {
			 * test.log(LogStatus.INFO,
			 * "========================All Comscore URLs====================");
			 * comscoreUrls.add(entry.toString()); test.log(LogStatus.INFO,
			 * entry.getRequest().getUrl()); } else if
			 * (entry.getRequest().getUrl().startsWith(hbUrl)) {
			 * System.out.println("im n HB call validation");
			 * HBUrls.add(entry.toString()); test.log(LogStatus.INFO,
			 * "========================All HB URLs====================");
			 * test.log(LogStatus.INFO, entry.getRequest().getUrl()); } }
			 */
			// Validates ads from the console text
			// common.validateAdFromConsole(common.getHarnessLog());
			// Validates HB Calls
		}
	}

	public void playAuthVOD(String config, String Env, String UserName, String Password, String SearchMVPD, String configAnd, String showName, String season, String episodeName, String showNameA, String EpisodeNameA, String envA) throws Exception {
		try {
			// platFormName = TestRunner.platFormName();
			if (platFormName.equalsIgnoreCase("iOS")) {
				common.selectConfig(config, Env);
				common.selectChannel();
				common.selectShow(showName, season, episodeName);
				common.selectMVPD(SearchMVPD, UserName, Password);
				waitForVisibilityOfElement(optionsWE);
				// click(expandWE, "Expand clicked");
				System.out.println(getText(harnessWE));
				common.getHarnessLog();
				Thread.sleep(20000);
			} else {
				common.selectConfig(configAnd, envA);
				common.selectChannel();
				common.selectShow(showNameA, season, EpisodeNameA);
				common.selectMVPD(SearchMVPD, UserName, Password);
				Thread.sleep(15000);
				waitForVisibilityOfElement(pauseWEA);
				click(pauseWEA, "Pause");
				common.getHarnessLog();
			}
		} catch (Exception e) {
			test.log(LogStatus.FAIL, e);
		}
	}

	// Selecting Config
	@SuppressWarnings("deprecation")
	public void selectConfig(String config, String Env, String UserName, String Password, String SearchMVPD, String configAnd, String showName, String season, String episodeName, String showNameA, String EpisodeNameA, String envA) throws Exception {
		try {
			System.out.println("im in selectconfig" + platFormName);
			Map<String, String> checkFeaturesEnabledVOD;
			Map<String, String> checkFeaturesEnabledLive;
			boolean enabled = false;
			if (platFormName.equalsIgnoreCase("iOS")) {
				System.out.println("Iam in " + platFormName);
				common.selectConfig(config, Env);
				common.selectChannel();
				common.selectShow(showName, season, episodeName);
				String hbUrl = "http://nbcume.hb.omtrdc.net/?";
				String comscoreUrl = "https://sb.scorecardresearch.com/p2";
				String adBeaconUrl = "http://29773.s.fwmrm.net/";
				String nielsonUrl = "https://secure-dcr-cert.imrworldwide.com";
				// common.selectMVPD(SearchMVPD, UserName, Password);
				waitForVisibilityOfElement(optionsWE);
				List<HarEntry> entries = getHar();
				for (HarEntry entry : entries) {
					System.out.println("cnifg Url" + entry.getRequest().getUrl());
					// SendGetPost.sendGet(entry.getRequest().getUrl());
					String url = entry.getRequest().getUrl();
					System.out.println(entry.getResponse().getContent().getText());
					if (entry.getRequest().getUrl().contains("https://ws-cloudpath-stage.media.nbcuni.com")) {
						System.out.println("Iam in If condition");
						System.out.println("cnifg Url" + entry.getRequest().getUrl());
						// SendGetPost.sendGet(entry.getRequest().getUrl());
						url = entry.getRequest().getUrl();
						System.out.println(entry.getResponse().getContent().getText());
						// HarResponse response = entry.getResponse();
					}
				}
			} else {
				// System.out.println(TestRunner.platFormName());
				common.selectConfig(configAnd, envA);
				common.selectChannel();
				common.selectShow(showNameA, season, EpisodeNameA);
				String hbUrl = "https://nbcume.hb.omtrdc.net/";
				String comscoreUrl = "http://b.scorecardresearch.com/";
				String adBeaconUrl = "http://29773.s.fwmrm.net";
				waitForVisibilityOfElement(pauseWEA);
				Thread.sleep(30000);
				click(pauseWEA, "Pause");
				String file1 = common.elementScreenshot(videoPlayerWE, common.path, currentTest);
				Thread.sleep(5000);
				String file2 = common.elementScreenshot(videoPlayerWE, common.path, currentTest);
				processImage(file1, file2);
				// validateVideo(videoPlayerWE, common.path, currentTest);
			}
		} catch (Exception e) {
			test.log(LogStatus.FAIL, e);
		}
	}

	// to Pause the content
	public void pauseEvent() throws Exception {
		click(clearWE, "Clear");
		waitForVisibilityOfElement(pauseWE);
		click(pauseWE, "Pause");
		System.out.println(getText(harnessWE));
	}

	@SuppressWarnings("deprecation")
	public void validateVideo(String config, String Env, String UserName, String Password, String SearchMVPD, String configAnd, String showName, String season, String episodeName, String showNameA, String EpisodeNameA, String envA) throws Exception {
		try {
			System.out.println("im in selectconfig" + platFormName);
			Map<String, String> checkFeaturesEnabledVOD;
			Map<String, String> checkFeaturesEnabledLive;
			boolean enabled = false;
			if (platFormName.equalsIgnoreCase("iOS")) {
				System.out.println("Iam in " + platFormName);
				common.selectConfig(config, Env);
				common.selectChannel();
				common.selectShow(showName, season, episodeName);
				String hbUrl = "http://nbcume.hb.omtrdc.net/?";
				String comscoreUrl = "https://sb.scorecardresearch.com/p2";
				String adBeaconUrl = "http://29773.s.fwmrm.net/";
				String nielsonUrl = "https://secure-dcr-cert.imrworldwide.com";
				// common.selectMVPD(SearchMVPD, UserName, Password);
				waitForVisibilityOfElement(optionsWE);
				List<HarEntry> entries = getHar();
				for (HarEntry entry : entries) {
					System.out.println("cnifg Url" + entry.getRequest().getUrl());
					// SendGetPost.sendGet(entry.getRequest().getUrl());
					String url = entry.getRequest().getUrl();
					System.out.println(entry.getResponse().getContent().getText());
					if (entry.getRequest().getUrl().contains("https://ws-cloudpath-stage.media.nbcuni.com")) {
						System.out.println("Iam in If condition");
						System.out.println("cnifg Url" + entry.getRequest().getUrl());
						// SendGetPost.sendGet(entry.getRequest().getUrl());
						url = entry.getRequest().getUrl();
						System.out.println(entry.getResponse().getContent().getText());
						// HarResponse response = entry.getResponse();
					}
				}
			} else {
				// System.out.println(TestRunner.platFormName());
				common.selectConfig(configAnd, envA);
				common.selectChannel();
				common.selectShow(showNameA, season, EpisodeNameA);
				String hbUrl = "https://nbcume.hb.omtrdc.net/";
				String comscoreUrl = "http://b.scorecardresearch.com/";
				String adBeaconUrl = "http://29773.s.fwmrm.net";
				waitForVisibilityOfElement(pauseWEA);
				Thread.sleep(30000);
				click(pauseWEA, "Pause");
				String file1 = common.elementScreenshot(videoPlayerWE, common.path, currentTest);
				Thread.sleep(5000);
				String file2 = common.elementScreenshot(videoPlayerWE, common.path, currentTest);
				processImage(file1, file2);
				// validateVideo(videoPlayerWE, common.path, currentTest);
			}
		} catch (Exception e) {
			test.log(LogStatus.FAIL, e);
		}
	}

	// Resume From Feature
	public void resumeFrom(String config, String Env, String UserName, String Password, String SearchMVPD, String configAnd, String showName, String season, String episodeName, String showNameA, String EpisodeNameA, String envA, String RFVal) throws Exception {
		try {
			String CurrentTime;
			if (platFormName.equalsIgnoreCase("iOS")) {
				click(rfWE, "Resume From");
				enterText(rfWE, RFVal, "Enter Resume From Value" + RFVal);
				pressEnter(appiumDriver);
				playVOD(config, Env, UserName, Password, SearchMVPD, configAnd, showName, season, episodeName, showNameA, EpisodeNameA, envA);
				CurrentTime = appiumDriver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[2]/UIAStaticText[10]")).getText();
				System.out.println("Current Time:" + CurrentTime);
				List<String> list = Arrays.asList(CurrentTime.replaceAll("\\s", "").split("/"));
				CurrentTime = list.get(0);
				int Actual = common.covertHHMMSStoSeconds(CurrentTime);
				common.validateTime(Integer.parseInt(RFVal), Actual);
			} else {
				Thread.sleep(1000);
				appiumDriver.findElement(By.id("com.nbcuni.cloudpath.prod:id/resume_time")).sendKeys(RFVal);
				Thread.sleep(1000);
				// appiumDriver.hideKeyboard();
				pressBack(appiumDriver);
				// reverseScrollCPC(appiumDriver);
				playVOD(config, Env, UserName, Password, SearchMVPD, configAnd, showName, season, episodeName, showNameA, EpisodeNameA, envA);
				Thread.sleep(5000);
				CurrentTime = appiumDriver.findElement(By.id("com.nbcuni.cloudpath.prod:id/overlay_duration")).getText();
				System.out.println("Current Time:" + CurrentTime);
				List<String> list = Arrays.asList(CurrentTime.replaceAll("\\s", "").split("/"));
				CurrentTime = list.get(0);
				int Actual = common.covertHHMMSStoSeconds(CurrentTime);
				common.validateTime(Integer.parseInt(RFVal), Actual);
				common.getHarnessLog();
			}
			common.getHarnessLog();
		} catch (Exception e) {
			test.log(LogStatus.FAIL, e);
		}
	}
}
