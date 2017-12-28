package com.mop.qa.test.bvt;

// import java.util.HashMap;

import org.testng.annotations.Test;

import com.mop.qa.pageobject.AdobeValidations;
import com.mop.qa.testbase.TestBase;
@Test
public class HBAnalyticsSCLive extends TestBase{
  //  static HashMap<String, String> KeyValues=new HashMap<String,String>(); 
public  void validate() throws Exception {


String HarFile_Path = rds.getValue("DATA", currentTest, "HarFile_Path");

System.out.println(HarFile_Path);
AdobeValidations hb = new AdobeValidations();           
hb.HBValidation_analytics_Live(HarFile_Path);

}
}