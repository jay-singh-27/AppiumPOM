package com.test.pages;

import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

import com.test.Base;
import com.test.utils.TestUtils;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;



public class LoginPage extends Base {
	TestUtils utils = new TestUtils();

	@AndroidFindBy (xpath = "//android.widget.EditText[@resource-id='com.meraki.mapidemo:id/apiKeyTxt']") 
	private MobileElement apiKeyText;

	@AndroidFindBy (xpath = "//android.widget.Button[@resource-id='com.meraki.mapidemo:id/goButton']") 
	private MobileElement goButton;

	public LoginPage enterAPIKey(String apikey) {
		clear(apiKeyText);
		utils.log().info("login with " + apikey);
		sendKeys(apiKeyText, apikey);
		return this;
	}

	public WirelessAPListPage pressGoBtn() {
		utils.log().info("press Go button");
		click(goButton);
		return new WirelessAPListPage();
	}

	public WirelessAPListPage login(String apikey) {
		enterAPIKey(apikey);
		return pressGoBtn();
	}

}
