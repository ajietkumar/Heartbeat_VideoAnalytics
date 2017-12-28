package com.mop.qa.pageobject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.lightbody.bmp.core.har.HarEntry;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
public class NielsonFeature extends PageBase {
	public NielsonFeature() {
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
	String URL = null;
	String Response;
	String appKey;
	String secretKey;
	HashMap<String, String> excelValueMap = new HashMap<String, String>();
	HashMap<String, String> actualValueMap;
	ReadDataSheet rd = new ReadDataSheet();

	public void NielsonCallsCheck(String config, String Env, String UserName, String Password, String SearchMVPD, String configAnd, String showName, String season, String episodeName, String showNameA, String EpisodeNameA, String envA, String sheetName) throws Exception {
		// platFormName = TestRunner.platFormName();
		if (platFormName.equalsIgnoreCase("iOS")) {
			System.out.println(server);
			common.selectConfig(config, Env);
			common.selectChannel();
			common.selectShow(showName, season, episodeName);
			Map<String, String> checkFeaturesEnabledVOD;
			Map<String, String> checkFeaturesEnabledLive;
			boolean enabled = false;
			String nielsonUrl = "https://secure-dcr-cert.imrworldwide.com";
			waitForVisibilityOfElement(optionsWE);
			click(expandWE, "Expand clicked");
			Thread.sleep(10000);
			click(shrinkWE, "Shrink clicked");
			Thread.sleep(80000);
			List<HarEntry> entries = getHar();
			for (HarEntry entry : entries) {
				if (entry.getRequest().getUrl().contains(nielsonUrl)) {
					System.out.println("im in Nielson validation");
					nielsonUrls.add(entry.getRequest().getUrl());
					nielsonUrlsTimeStamp.add(entry.getStartedDateTime().toString());
				} else if (entry.getRequest().getUrl().contains("https://ws-cloudpath-stage.media.nbcuni.com")) {
					System.out.println("Iam in If condition");
					System.out.println("cnifg Url" + entry.getRequest().getUrl());
					test.log(LogStatus.INFO, "<b><font color=\"purple\">Config Request URL Fired--->" + entry.getRequest().getUrl() + "</b></font>");
					String url = entry.getRequest().getUrl();
					String Response = SendGetPost.sendGet(url);
					// JSONParser.getVODAnvackKey(url, Response);
					test.log(LogStatus.INFO, "<b><font color=\"purple\">Request Fired On :" + entry.getStartedDateTime()+"</b></font>");
					// test.log(LogStatus.INFO,
					// "========Parameters In VOD Module========");
					checkFeaturesEnabledVOD = JSONParser.checkFeaturesEnabledVOD(url, Response);
					// test.log(LogStatus.INFO, "===========================");
					test.log(LogStatus.INFO, "=============Nielson Check For VOD Module========");
					enabled = JSONParser.checkNielsen(checkFeaturesEnabledVOD);
					test.log(LogStatus.INFO, "========Adobe AUidence Check for VOD Module========");
					JSONParser.checkAdobeAudience(checkFeaturesEnabledVOD);
					checkFeaturesEnabledLive = JSONParser.checkFeaturesEnabledLive(url, Response);
					// test.log(LogStatus.INFO,
					// "========Parameters In  LIVE Module========");
					test.log(LogStatus.INFO, "<b><font color=\"green\">=============Nielson Check For LIVE Module========</b></font>");
					JSONParser.checkNielsen(checkFeaturesEnabledLive);
					test.log(LogStatus.INFO, "========Adobe AUidence Check for Live Module========");
					JSONParser.checkAdobeAudience(checkFeaturesEnabledLive);
					common.getGlobalConfig(Response);
					
				}
			}
			System.out.println("***********" + enabled + "****size of nielsonn urls***" + nielsonUrls.size());
			//test.log(LogStatus.INFO, "***********" + enabled + "****size of nielsonn urls***" + nielsonUrls.size());
			if (enabled & (nielsonUrls.size() > 0)) {
				test.log(LogStatus.INFO, "<b><font color=\"purple\">Nielsen Enabled and Nielson Calls are fired</font></b>");
			} else if ((nielsonUrls.size() > 0)) {
				if (enabled = false) {
					test.log(LogStatus.FAIL, "<b><font color=\"purple\">Nielsen Disabled and Nielson Calls are fired</font></b>");
				}
			} else if (!enabled & (nielsonUrls.size() == 0)) {
				test.log(LogStatus.PASS, "<b><font color=\"green\">Nielsen Disabled and Nielson Calls are not fired and functionality working as expected</font></b>");
			}
			//test.log(LogStatus.INFO, "<b><font color=\"purple\">=========================Nieslon URLs====================</font></b>");
			for (int i = 0; i < nielsonUrls.size(); i++) {
				if (nielsonUrls.size() == 0) {
					test.log(LogStatus.PASS, "<b><font color=\"green\">Nielsen Disabled and Nielson Calls are not fired and functionality working as expected</font></b>");
					break;
				}
				test.log(LogStatus.INFO, "<b><font color=\"green\">Request Fired------->" + nielsonUrlsTimeStamp.get(i) + "</font></b>");
				test.log(LogStatus.INFO, nielsonUrls.get(i));
			}
		} else {
			common.selectConfig(configAnd, envA);
			common.selectChannel();
			common.selectShow(showNameA, season, EpisodeNameA);
			String hbUrl = "https://nbcume.hb.omtrdc.net/?";
			String comscoreUrl = "http://b.scorecardresearch.com/";
			String adBeaconUrl = "http://29773.s.fwmrm.net";
			String feedUrl = "http://feed.theplatform.com/f/HNK2IC/nbcd_app_adstitch_v3_prod?byGUID=";
			// waitForVisibilityOfElement(pauseWEA);
			Thread.sleep(80000);
			click(pauseWEA, "Pause");
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
		}
	}
}