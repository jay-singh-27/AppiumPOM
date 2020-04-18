package com.test;

import com.test.pages.SettingsPage;
import com.test.utils.TestUtils;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class MenuPage extends BaseTest{
	TestUtils utils = new TestUtils();
	
	@AndroidFindBy (xpath="//android.view.ViewGroup[@content-desc=\"test-Menu\"]/android.view.ViewGroup/android.widget.ImageView\n" + 
			"") 
	@iOSXCUITFindBy (xpath="//XCUIElementTypeOther[@name=\"test-Menu\"]/XCUIElementTypeOther")
	private MobileElement settingsBtn;
	
	public SettingsPage pressSettingsBtn() {
		utils.log().info("press Settings button");
		click(settingsBtn);
		return new SettingsPage();
	}

}
