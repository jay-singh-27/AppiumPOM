package com.test.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import java.io.InputStream;
import java.lang.reflect.Method;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.test.Base;
import com.test.pages.LoginPage;
import com.test.pages.WirelessAPListPage;
import com.test.utils.TestUtils;


public class LoginTest extends Base {
	LoginPage loginPage;
	WirelessAPListPage ap_MenuPage;
	JSONObject dataset;
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
	}

	@AfterMethod
    public void afterMethod() {
		closeApp();
		launchApp();
		  }
	
	@Test
	public void successfulLogin() {
		loginPage.enterAPIKey(dataset.getJSONObject("validKey").getString("apikey"));
		ap_MenuPage = loginPage.pressGoBtn();

		String actualProductTitle = ap_MenuPage.getTitle();		  
		String expectedProductTitle = getStrings().get("ap_page_title");
		Assert.assertEquals(actualProductTitle, expectedProductTitle);
	}

}
