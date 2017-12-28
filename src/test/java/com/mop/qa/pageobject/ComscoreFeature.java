package com.mop.qa.pageobject;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import net.lightbody.bmp.core.har.HarEntry;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.Utilities.CharlesController;
import com.mop.qa.Utilities.CharlesIntegration;
import com.mop.qa.Utilities.MobileExtractor;
import com.mop.qa.Utilities.ReadDataSheet;
import com.mop.qa.testbase.PageBase;
import com.relevantcodes.extentreports.LogStatus;

/**
 * Class repository for Home screen properties
 * 
 * @author 557743
 *
 */
public class ComscoreFeature extends PageBase {
	public ComscoreFeature() {
		super();
	}

	int j = 2;
	int i = 0;
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
	/*
	 * int j = 2; int i = 0;
	 */
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

	public static void comscoreAppLaunch() throws Exception {
		HashMap<String, String> comscoreValueList = MobileExtractor.xmlParser();
		HashMap<String, String> excelValueMap = new HashMap<String, String>();
		System.out.println(getColumValues("Expected", "Comscore").size());
		// System.out.println(parameterNames);
		ArrayList<String> parameterNames = null;
		ArrayList<String> parameterValues = null;
		for (int k = 1; k <= getColumValues("Parameter", "Comscore").size() - 1; k++) {
			excelValueMap.put(getColumValues("Parameter", "Comscore").get(k), getColumValues("Expected", "Comscore").get(k));
		}
		System.out.println("===================================Excel captured=============");
		System.out.println(excelValueMap.size());
		System.out.println(excelValueMap);
		System.out.println("===================================Charles captured=============");
		System.out.println(comscoreValueList);
		test.log(LogStatus.INFO, "<b><font color=\"purple\">Parameter" + "---------" + "Expected" + "---------" + "Actual" + "---------" + "Status</b>");
		for (int i = 0; i < excelValueMap.size(); i++) {
			// Comparing Parameter From Charles and Parameter from excel
			if (comscoreValueList.containsKey(excelValueMap.keySet().toArray()[i])) {
				if (excelValueMap.get(excelValueMap.keySet().toArray()[i]).equals(comscoreValueList.get(excelValueMap.keySet().toArray()[i]))) {
					test.log(LogStatus.INFO, "<font color=\"black\">" + excelValueMap.keySet().toArray()[i] + "--------->" + excelValueMap.get(excelValueMap.keySet().toArray()[i]) + "" + "--------->" + "</font><font color=\"green\">" + comscoreValueList.get(excelValueMap.keySet().toArray()[i]) + "--------->" + "PASS</b>");
				} else {
					test.log(LogStatus.FAIL, "<font color=\"black\">" + excelValueMap.keySet().toArray()[i] + "--------->" + excelValueMap.get(excelValueMap.keySet().toArray()[i]) + "--------->" + "</font><font color=\"red\">" + comscoreValueList.get(excelValueMap.keySet().toArray()[i]) + "--------->" + "FAIL</b>");
				}
			} else {
				test.log(LogStatus.FAIL, "<font color=\"black\">" + excelValueMap.keySet().toArray()[i] + "--------->" + excelValueMap.get(excelValueMap.keySet().toArray()[i]) + "--------->" + "</font><font color=\"red\">" + excelValueMap.keySet().toArray()[i] + "    Parameter is missing" + "--------->" + "FAIL</b>");
				// test.log(LogStatus.FAIL,
				// "Parameter"+excelValueMap.keySet().toArray()[i]+"is
				// missing");
			}
		}
		test.log(LogStatus.INFO, "===================================Excel captured=============");
		test.log(LogStatus.INFO, "" + excelValueMap);
		test.log(LogStatus.INFO, "===================================Charles captured=============");
		test.log(LogStatus.INFO, "" + comscoreValueList);
		CharlesIntegration.stopCharlesProcess();
		//
	}

	// Comscore Valiadtion
	public void comscoreValidation(String config, String Env, String UserName, String Password, String SearchMVPD, String configAnd, String showName, String season, String episodeName, String showNameA, String EpisodeNameA, String envA, String comscoreSheetName) throws Exception {
		// platFormName = TestRunner.platFormName();
		try {
			if (platFormName.equalsIgnoreCase("iOS")) {
				common.selectConfig(config, Env);
				common.selectChannel();
				/*common.selectShow(showName, season, episodeName);
				String hbUrl = "http://nbcume.hb.omtrdc.net/?";
				String comscoreUrl = "https://sb.scorecardresearch.com/p2?";
				String adBeaconUrl = "http://29773.s.fwmrm.net/";
				String nielsonUrl = "https://secure-dcr-cert.imrworldwide.com";
				String feedUrl = "https://feed.theplatform.com/f/HNK2IC/nbcd_app_adstitch_v3_prod?byGUID=";
				// common.selectMVPD(SearchMVPD, UserName, Password);
				waitForVisibilityOfElement(optionsWE);
				click(expandWE, "Expand clicked");
				// System.out.println(getText(harnessWE));
				// common.getHarnessLog();
				Thread.sleep(90000);
				click(shrinkWE, "Shrink clicked");
				Thread.sleep(100000);
				String path = common.createFile(common.getHarnessLog(), platFormName, currentTest);
				List<HarEntry> entries = getHar();
				for (HarEntry entry : entries) {
					if (entry.getRequest().getUrl().contains(comscoreUrl)) {
						comscoreUrlsTimeStamp.add(entry.getStartedDateTime().toString());
						comscoreUrls.add(entry.getRequest().getUrl());
					} else if (entry.getRequest().getUrl().contains(feedUrl)) {
						feedUrl = entry.getRequest().getUrl();
					}
				}
				test.log(LogStatus.INFO, "========================Comscore URLs====================");
				for (int i = 0; i < comscoreUrls.size(); i++) {
					test.log(LogStatus.INFO, "Request Fired" + comscoreUrlsTimeStamp.get(i));
					test.log(LogStatus.INFO, comscoreUrls.get(i));
				}
				common.seriesOfEvents(comscoreUrls, comscoreUrl, comscoreUrlsTimeStamp, "ns_st_ev");
				common.validateComscore(comscoreUrls, comscoreUrl, comscoreSheetName, "ns_st_ev", "play", comscoreUrlsTimeStamp, feedUrl, path);
				*/
			} else {
				common.selectConfig(configAnd, envA);
				common.selectChannel();
				/*common.selectShow(showNameA, season, EpisodeNameA);
				String hbUrl = "https://nbcume.hb.omtrdc.net/";
				String comscoreUrl = "http://b.scorecardresearch.com/p2?";
				String adBeaconUrl = "http://29773.s.fwmrm.net";
				String feedUrl = "https://feed.theplatform.com/f/HNK2IC/nbcd_app_adstitch_v3_prod?byGUID=";
				// waitForVisibilityOfElement(pauseWEA);
				Thread.sleep(80000);
				String path = common.createFile(common.getHarnessLog(), platFormName, currentTest);
				List<HarEntry> entries = getHar();
				for (HarEntry entry : entries) {
					System.out.println(entry.getRequest().getUrl());
					
					if (entry.getRequest().getUrl().startsWith(comscoreUrl)) {
						comscoreUrls.add(entry.getRequest().getUrl());
						comscoreUrlsTimeStamp.add(entry.getStartedDateTime().toString());
					} else if (entry.getRequest().getUrl().contains(feedUrl)) {
						feedUrl = entry.getRequest().getUrl();
						System.out.println(feedUrl);
					}
				}
				test.log(LogStatus.INFO, "========================Comscore URLs====================");
				for (int i = 0; i < comscoreUrls.size(); i++) {
					test.log(LogStatus.INFO, "Request Fired" + comscoreUrlsTimeStamp.get(i));
					
					test.log(LogStatus.INFO, comscoreUrls.get(i));
				}
				common.seriesOfEvents(comscoreUrls, comscoreUrl, comscoreUrlsTimeStamp, "ns_st_ev");
				common.validateComscore(comscoreUrls, comscoreUrl, comscoreSheetName, "ns_st_ev", "play", comscoreUrlsTimeStamp, feedUrl, path);
				//common.seriesOfEvents(comscoreUrls, comscoreUrl, comscoreUrlsTimeStamp, "ns_st_ev");
*/			}
		} catch (Exception e) {
			test.log(LogStatus.FAIL, e);
		}
	}
}
