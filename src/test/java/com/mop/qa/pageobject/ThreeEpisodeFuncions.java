package com.mop.qa.pageobject;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
public class ThreeEpisodeFuncions extends PageBase {
	public ThreeEpisodeFuncions() {
		super();
	}

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
	protected WebElement searchMVPDWE;
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
	int j = 2;
	int i = 0;
	CommonFunctions common = new CommonFunctions();

	// Method Which will select Config file and Environment
	public void playThreeEpisodeVOD(String Email) throws Exception {
		click(actionsWE, "action button");
		click(adobePromoTempPassWE, "AdobeTempass selected");
		click(emailWE, "Email Field");
		enterText(emailWE, Email, "Email Entered");
	}

	// Generate Random Number
	public static String random(String Email) {
		Random random = new Random();
		int val = random.nextInt(2000);
		List<String> list = Arrays.asList(Email.replaceAll("\\s", "").split("@"));
		Email = list.get(0) + val + '@' + list.get(1);
		return Email;
	}

	// Method to validate 3 episode Trial
	public void threeEpisodeTest(String config, String Env, String assetID, String Email) throws Exception {
		// Calling method to select config file
		common.selectConfig(config, Env);
		// Get the assetID and Email Id details from DataSheet
		List<String> list = Arrays.asList(assetID.replaceAll("\\s", "").split(","));
		// Forming strings to validate from test harness
		String viewedAssets1 = "Viewed Assets :(" + '\n' + "    " + list.get(0) + '\n' + ")";
		String viewedAssets2 = "Viewed Assets :(" + '\n' + "    " + list.get(1) + "," + '\n' + "    " + list.get(0) + '\n' + ")";
		String viewedAssets3 = "Viewed Assets :(" + '\n' + "    " + list.get(1) + "," + '\n' + "    " + list.get(0) + "," + "\n" + "    " + list.get(2) + '\n' + ")";
		// List contains viewed asset details
		List<String> assetList = new ArrayList();
		assetList.add(viewedAssets1);
		assetList.add(viewedAssets2);
		assetList.add(viewedAssets3);
		test.log(LogStatus.INFO, "===========Below are the  assets going to validate in 3 episode trial===================");
		test.log(LogStatus.INFO, "Assets Are:"+assetID);
		// Calling random method to generate random number
		Email = random(Email);
		for (i = 0; i <= 2; i++) {
			// Invoking method to play AuthVOD through adobepromotempass
			playThreeEpisodeVOD(Email);
			enterText(assetIDWE, list.get(i), "AssetID");
			click(authZPlayWE, "Playing 3 episode VOD");
			waitForVisibilityOfElement(optionsWE);
			Thread.sleep(10000);
			// Getting Text from TestHarness
			String consoleText = getText(harnessWE);
			// Validating Viewed Asset count and Remaining Asset count
			// Fist IF to validate Remaining asset count
			if (consoleText.contains("Remaining Assets :" + (j - i))) {
				if (consoleText.contains(assetList.get(i))) {
					System.out.println("Remaining Assets :" + (j - i) + '\n' + assetList.get(i));
					test.log(LogStatus.INFO, "Remaining Assets :" + (j - i) + '\n' + assetList.get(i));
					test.log(LogStatus.INFO, getText(harnessWE));
					click(stopWE, "Stop button");
				} else {
					System.out.println("Fail :Viewed Asset details are wrong");
					test.log(LogStatus.FAIL, "Viewed Asset details are wrong");
					test.log(LogStatus.FAIL, "Expected : Remaining Assets :" + (j - i) + '\n' + assetList.get(i));
					click(stopWE, "Stop button");
					appiumDriver.closeApp();
				}
			} else {
				System.out.println("Fail:Remaining assets count is wrong");
				test.log(LogStatus.FAIL, "Remaining assets count is wrong");
				test.log(LogStatus.INFO, "Expected :Remaining Assets :" + (j - i) + '\n' + assetList.get(i));
				test.log(LogStatus.FAIL, "Actual" + '\n' + getText(harnessWE));
				click(stopWE, "Stop button");
				appiumDriver.closeApp();
			}
		}
		test.log(LogStatus.INFO, getText(harnessWE));
	}

	public void validateThreeEpisodeVOD() throws Exception {
		String config = rds.getValue("DATA", Thread.currentThread().getStackTrace()[1].getMethodName(), "Config");
		String Env = rds.getValue("DATA", Thread.currentThread().getStackTrace()[1].getMethodName(), "Env");
		common.selectConfig(config, Env);
		String assetID = rds.getValue("DATA", Thread.currentThread().getStackTrace()[1].getMethodName(), "AssetID");
		String UserName = rds.getValue("DATA", Thread.currentThread().getStackTrace()[1].getMethodName(), "Username");
		String Password = rds.getValue("DATA", Thread.currentThread().getStackTrace()[1].getMethodName(), "Password");
		String SearchMVPD = rds.getValue("DATA", Thread.currentThread().getStackTrace()[1].getMethodName(), "SearchMVPD");
		String Email = rds.getValue("DATA", Thread.currentThread().getStackTrace()[1].getMethodName(), "Email");
		playThreeEpisodeVOD(Email);
		enterText(assetIDWE, assetID, "AssetID");
		click(authZPlayWE, "Playing 3 episode VOD");
	
		waitForVisibilityOfElement(searchMVPDWE);
		if (searchMVPDWE.isDisplayed()) {
			click(searchMVPDWE, "searchbar clicked");
			enterText(searchMVPDWE, SearchMVPD, "optimum mvpd searched");
			waitForVisibilityOfElement(mvpdWE);
			click(mvpdWE, "mvpd selected");
			Thread.sleep(2000); //
			waitForVisibilityOfElement(userIDWE);
			click(userIDWE, "userid selected");
			enterText(userIDWE, UserName, "userID");
			waitForVisibilityOfElement(passwordWE);
			click(passwordWE, "password clicked");
			enterText(passwordWE, Password, "Password enterd");
			waitForVisibilityOfElement(signinWE);
			click(signinWE, "signin button clicked");
			waitForVisibilityOfElement(optionsWE);
			Thread.sleep(10000);
		} else {
			test.log(LogStatus.FAIL, "MVPD Page is not displayed. 3 Episode trial is not working as expected");
			appiumDriver.closeApp();
		}
		String consoleText = getText(harnessWE);
		String errorDesc = "Description =" + "\"" + "Authorization failed because the user has no subscription for the requested resource or because of other reasons originating from the MVPD" + "\"";
		if (consoleText.contains(errorDesc)) {
			System.out.println("3 Episode trial is working as expected");
		} else {
			test.log(LogStatus.FAIL, "3 Episode trial is failed");
			test.log(LogStatus.INFO,"Console Txt:"+consoleText);
			appiumDriver.closeApp();
		}
	}
	
}
