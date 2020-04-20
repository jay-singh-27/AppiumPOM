package com.test.tests;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.test.Base;
import com.test.models.APModel;
import com.test.pages.AP_DetailPage;
import com.test.pages.LoginPage;
import com.test.pages.WirelessAPListPage;
import com.test.repo.APRepo;
import com.test.utils.TestUtils;

public class WirelessAPTest extends Base {
	LoginPage loginPage;
	WirelessAPListPage apPage;
	AP_DetailPage detailPage;
	JSONObject dataset;
	APModel ap;
	APRepo repo;
	TestUtils utils = new TestUtils();
	@BeforeClass
	public void beforeClass() throws Exception {
		InputStream datais = null;
		try {
			String dataFileName = "validation/data.json";
			datais = getClass().getClassLoader().getResourceAsStream(dataFileName);
			JSONTokener tokener = new JSONTokener(datais);
			dataset = new JSONObject(tokener);
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(datais != null) {
				datais.close();
			}
		}
		closeApp();
		launchApp();
	}


	@BeforeMethod
	public void beforeMethod(Method m) {
		utils.log().info("\n" + "****** starting test:" + m.getName() + "******" + "\n");
		loginPage = new LoginPage();		  
		apPage = loginPage.login(dataset.getJSONObject("validKey").getString("apikey"));
	}

	
	@AfterMethod
    public void afterMethod() {
		closeApp();
		launchApp();
		  }
	
	@Test(priority= 1)
	public void validateAPsonWirelessAPListPage() throws InterruptedException {
		SoftAssert sa = new SoftAssert();
		String APName = apPage.getAPName("1");
		sa.assertEquals(APName, getStrings().get("ap1_Name"));
		String APIP = apPage.getAPIP("1");
		sa.assertEquals(APIP, getStrings().get("ap1_IP"));
		sa.assertAll();

	}
	
	@Parameters({"testDataValue"})
	@Test(priority= 2)
	  public void validateAPonAPDetailsPage(String key) throws InterruptedException, IOException {
		  SoftAssert sa = new SoftAssert();
		  detailPage= apPage.clickAPIcon(key);
		  repo= apPage.fetchAPData(key);
		  
		  String model = detailPage.getAPModel();
		  sa.assertEquals(model, repo.getAP().getModel());
		  String serial = detailPage.getAPSerial();
		  sa.assertEquals(serial, repo.getAP().getSerial());
		  String usage = detailPage.getAPUsage();
		  sa.assertEquals(usage, repo.getAP().getUsage());
		  String nofClients = detailPage.getAPClient();
		  sa.assertEquals(nofClients, repo.getAP().getNofClients());
		  
		  sa.assertAll();
	  }



}
