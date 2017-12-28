package com.mop.qa.test.bvt;

import org.testng.annotations.Test;

import com.mop.qa.pageobject.AdobeValidations;
import com.mop.qa.testbase.TestBase;
@Test
public class HBValidationsEpisode extends TestBase{
	public void playVOD_AdComplete() throws Exception {
		//String Event_Type = rds.getValue("DATA", currentTest, "Event_Type");
		String HarFile_Path = rds.getValue("DATA", currentTest, "HarFile_Path");
		String FeedURL = rds.getValue("DATA", currentTest, "FeedURL");
		String ShowType = rds.getValue("DATA", currentTest, "ShowType");
		
		String SheetName = rds.getValue("DATA", currentTest, "SheetName");
		String ExecutionStatus = rds.getValue("Evalutaion", currentTest, "ExecutionStatus");
		String EventType = rds.getValue("Evalutaion", currentTest, "EventType");
		String EventValue = rds.getValue("Evalutaion", currentTest, "EventValue");
		String AssetType = rds.getValue("Evalutaion", currentTest, "AssetType");
		String AssetValue = rds.getValue("Evalutaion", currentTest, "AssetValue");
		
		// Create object for the pageobject 
	AdobeValidations hb = new AdobeValidations();
		
		hb.HBValidation(ShowType,SheetName,HarFile_Path,FeedURL);
	}
}
