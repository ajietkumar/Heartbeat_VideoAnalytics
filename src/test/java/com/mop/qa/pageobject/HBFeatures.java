package com.mop.qa.pageobject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.lightbody.bmp.core.har.HarEntry;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.Utilities.ReadDataSheet;
import com.mop.qa.testbase.PageBase;
import com.relevantcodes.extentreports.LogStatus;

import de.sstoehr.harreader.HarReader;

/**
 * Class repository for Home screen properties
 * 
 * @author 557743
 *
 */
public class HBFeatures extends PageBase {
	public HBFeatures() {
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
	@FindBy(name = "Done")
	protected WebElement doneWE;
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
	@FindBy(name = "//UIAApplication[1]/UIAWindow[2]/UIAScrollView[1]/UIAWebView[1]/UIASecureTextField[1]")
	protected WebElement passwordWE;
	@FindBy(name = "Clear")
	protected WebElement clearWE;
	protected WebElement signinWE;
	@FindBy(name = "pause")
	protected WebElement pauseWE;
	@FindBy(xpath = "//UIAApplication[1]/UIAWindow[2]/UIAButton[11]")
	protected WebElement stopWE;
	@FindBy(xpath = "//UIAApplication[1]/UIAWindow[2]/UIATextField[4]")
	protected WebElement rfWE;
	@FindBy(xpath = "//UIAApplication[1]/UIAWindow[2]/UIATextField[5]")
	protected WebElement seekToWE;
	@FindBy(name = "seekTo")
	protected WebElement seekToBtnWE;
	int j = 2;
	int i = 0;
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
	ArrayList<String> HBUrls = new ArrayList<String>();
	ArrayList<String> comscoreUrls = new ArrayList<String>();
	ArrayList<String> configURLs = new ArrayList<String>();
	ArrayList<String> nielsonUrls = new ArrayList<String>();
	ArrayList<String> HBUrlsTimeStamp = new ArrayList<String>();
	ArrayList<String> comscoreUrlsTimeStamp = new ArrayList<String>();
	ArrayList<String> adBeaconsUrlsTimeStamp = new ArrayList<String>();
	ArrayList<String> configURLsTimeStamp = new ArrayList<String>();
	ArrayList<String> nielsonUrlsTimeStamp = new ArrayList<String>();
	ArrayList<String> feedUrls = new ArrayList<String>();
	ArrayList<String> feedUrlsTimeStamp = new ArrayList<String>();
	String URL = null;
	String Response;
	String appKey;
	String secretKey;
	HashMap<String, String> excelValueMap = new HashMap<String, String>();
	HashMap<String, String> actualValueMap;
	ReadDataSheet rd = new ReadDataSheet();

	public void HBValidation(String config, String Env, String UserName, String Password, String SearchMVPD, String configAnd, String showName, String season, String episodeName, String showNameA, String EpisodeNameA, String envA, String sheetName, String AdStartsheetName) throws Exception {
		try { // platFormName = TestRunner.platFormName();
			if (platFormName.equalsIgnoreCase("iOS")) {
				ArrayList<String> adBeaconsUrls = new ArrayList<String>();
				/*
				 * common.selectConfig(config, Env); common.selectChannel();
				 * common.selectShow(showName, season, episodeName);
				 */
				String hbUrl = "http://nbcume.hb.omtrdc.net/?";
				String comscoreUrl = "https://sb.scorecardresearch.com/p2?";
				String adBeaconUrl = "http://29773.s.fwmrm.net/";
				String nielsonUrl = "https://secure-dcr-cert.imrworldwide.com";
				String feedUrl = "https://feed.theplatform.com/f/HNK2IC/nbcd_app_adstitch_v3_prod?byGUID=";
				// common.selectMVPD(SearchMVPD, UserName, Password);
				/*
				 * waitForVisibilityOfElement(optionsWE); click(expandWE,
				 * "Expand clicked"); // System.out.println(getText(harnessWE));
				 * // common.getHarnessLog(); Thread.sleep(90000);
				 * click(shrinkWE, "Shrink clicked"); // click(seekToWE,
				 * "Entered"); enterText(seekToWE, "415", "seekTo");
				 * pressEnter(appiumDriver); click(seekToBtnWE, "SeekTo Button"
				 * ); Thread.sleep(140000); String path =
				 * common.createFile(common.getHarnessLog(), platFormName,
				 * currentTest); List<HarEntry> entries = getHar();
				 */
				String path = "/Users/557743/Desktop/CableBrandPOC/ConsoleOutput/HBAnalyticCalls-Run1-iOS.txt";
				HarReader harReader = new HarReader();
				de.sstoehr.harreader.model.Har har = harReader.readFromFile(new File("D:\\bravo_vod\\bravo_vod.har"));
				de.sstoehr.harreader.model.HarLog log = har.getLog();
				List<de.sstoehr.harreader.model.HarEntry> entries1 = har.getLog().getEntries();
				for (de.sstoehr.harreader.model.HarEntry entry : entries1) {
					if (entry.getRequest().getUrl().contains(hbUrl)) {
						System.out.println("im n HB call validation");
						HBUrls.add(entry.getRequest().getUrl());
						HBUrlsTimeStamp.add(entry.getStartedDateTime().toString());
					} else if (entry.getRequest().getUrl().contains(adBeaconUrl)) {
						adBeaconsUrlsTimeStamp.add(entry.getStartedDateTime().toString());
						adBeaconsUrls.add(entry.getRequest().getUrl());
					} else if (entry.getRequest().getUrl().contains(feedUrl)) {
						feedUrl = entry.getRequest().getUrl();
					}
				}
				test.log(LogStatus.INFO, "Feed URL:" + feedUrl);
				test.log(LogStatus.INFO, "========================AdBeacon URLs====================");
				for (int i = 0; i < adBeaconsUrls.size(); i++) {
					test.log(LogStatus.INFO, "<b><font color=\"green\">Request Fired------->" + adBeaconsUrlsTimeStamp.get(i) + "</font></b>");
					test.log(LogStatus.INFO, adBeaconsUrls.get(i));
				}
				test.log(LogStatus.INFO, "========================HB URLs====================");
				for (int i = 0; i < HBUrls.size(); i++) {
					test.log(LogStatus.INFO, "<b><font color=\"green\">Request Fired------->" + HBUrlsTimeStamp.get(i) + "</font></b>");
					test.log(LogStatus.INFO, HBUrls.get(i));
				}
				//beaconsValidationWithoutVideoViewCSV(adBeaconsUrls, adBeaconsUrlsTimeStamp);
				//common.validateiOSAssetDetailsCosole(path, feedUrl);
			//	common.seriesOfEvents(HBUrls, hbUrl, HBUrlsTimeStamp, "s:event:type");
				common.validateAnalytics(HBUrls, hbUrl, "EPISODE AD COMPLETE CALL", "s:event:type", "complete", HBUrlsTimeStamp, feedUrl, path);
				// common.validateAnalytics(HBUrls, hbUrl, sheetName,
				// "s:event:type", "start", HBUrlsTimeStamp, feedUrl, path);
			} else {
				ArrayList<String> adBeaconsUrls = new ArrayList<String>();
				common.selectConfig(configAnd, envA);
				common.selectChannel();
				common.selectShow(showNameA, season, EpisodeNameA);
				String hbUrl = "https://nbcume.hb.omtrdc.net/?";
				String comscoreUrl = "http://b.scorecardresearch.com/";
				String adBeaconUrl = "http://29773.s.fwmrm.net";
				String feedUrl = "https://feed.theplatform.com/f/HNK2IC/nbcd_app_adstitch_v3_prod?byGUID=";
				// waitForVisibilityOfElement(pauseWEA);
				Thread.sleep(80000);
				// click(pauseWEA, "Pause");
				String path = common.createFile(common.getHarnessLog(), platFormName, currentTest);
				// Validates ads from the console text
				// common.validateAdAndroidConsole(path);
				List<HarEntry> entries = getHar();
				for (HarEntry entry : entries) {
					if (entry.getRequest().getUrl().contains(hbUrl)) {
						System.out.println("im n HB call validation");
						HBUrls.add(entry.getRequest().getUrl());
						HBUrlsTimeStamp.add(entry.getStartedDateTime().toString());
					} else if (entry.getRequest().getUrl().contains(adBeaconUrl)) {
						adBeaconsUrlsTimeStamp.add(entry.getStartedDateTime().toString());
						adBeaconsUrls.add(entry.getRequest().getUrl());
					} else if (entry.getRequest().getUrl().contains(feedUrl)) {
						feedUrl = entry.getRequest().getUrl();
					}
				}
				test.log(LogStatus.INFO, "<b><font color=\"purple\">========================AdBeacon URLs====================</font></b>");
				for (int i = 0; i < adBeaconsUrls.size(); i++) {
					test.log(LogStatus.INFO, "<b><font color=\"green\">Request Fired------->" + adBeaconsUrlsTimeStamp.get(i) + "</font></b>");
					test.log(LogStatus.INFO, adBeaconsUrls.get(i));
				}
				test.log(LogStatus.INFO, "<b><font color=\"purple\">=========================HB URLs====================</font></b>");
				for (int i = 0; i < HBUrls.size(); i++) {
					test.log(LogStatus.INFO, "<b><font color=\"green\">Request Fired------->" + HBUrlsTimeStamp.get(i) + "</font></b>");
					test.log(LogStatus.INFO, HBUrls.get(i));
				}
				beaconsValidationWithoutVideoViewCSV(adBeaconsUrls, adBeaconsUrlsTimeStamp);
				/*
				 * common.validateAdAndroidConsole(path);
				 * common.seriesOfEvents(HBUrls, hbUrl, HBUrlsTimeStamp);
				 * common.validateAnalyticsHBAndroid(HBUrls, hbUrl, sheetName,
				 * "s:event:type", "start", HBUrlsTimeStamp, feedUrl, path);
				 * common.validateAnalyticsHBAndroid(HBUrls, hbUrl,
				 * AdStartsheetName, "s:event:type", "aa_ad_start",
				 * HBUrlsTimeStamp, feedUrl, path);
				 */
				common.seriesOfEvents(HBUrls, hbUrl, HBUrlsTimeStamp, "s:event:type");
				common.validateAnalytics(HBUrls, hbUrl, AdStartsheetName, "s:event:type", "aa_ad_start", HBUrlsTimeStamp, feedUrl, path);
				common.validateAnalytics(HBUrls, hbUrl, sheetName, "s:event:type", "start", HBUrlsTimeStamp, feedUrl, path);
			}
		} catch (Exception e) {
			test.log(LogStatus.FAIL, e);
		}
	}
}