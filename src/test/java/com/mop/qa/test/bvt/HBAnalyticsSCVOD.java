
package com.mop.qa.test.bvt;

import org.testng.annotations.Test;

import com.mop.qa.pageobject.AdobeValidations;
import com.mop.qa.testbase.TestBase;
@Test
public class HBAnalyticsSCVOD extends TestBase{
            //    static HashMap<String, String> KeyValues=new HashMap<String,String>(); 
   public  void validate() throws Exception {

		
	   String HarFile_Path = rds.getValue("DATA", currentTest, "HarFile_Path");
	   String FeedURL = rds.getValue("DATA", currentTest, "FeedURL");
	   String ShowType = rds.getValue("DATA", currentTest, "ShowType");
	   
	   AdobeValidations hb = new AdobeValidations(); 
	   
	   hb.HBValidation_analytics_VOD(HarFile_Path);
	   
   }
}
