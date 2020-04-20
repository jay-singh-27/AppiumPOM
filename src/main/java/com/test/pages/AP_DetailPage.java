package com.test.pages;

import com.test.utils.TestUtils;
import io.appium.java_client.MobileElement;
import com.test.Base;

public class AP_DetailPage extends Base{

	TestUtils utils = new TestUtils();

	private MobileElement getAPModelElement(){
		MobileElement e = (MobileElement) getDriver().findElementByXPath("//android.widget.TextView[@resource-id='com.meraki.mapidemo:id/modelValue']");
		return e;
	}

	private MobileElement getAPSerialElement(){
		MobileElement e = (MobileElement) getDriver().findElementByXPath("//android.widget.TextView[@resource-id='com.meraki.mapidemo:id/serialValue']");
		return e;
	}


	private MobileElement getAPUsageElement(){
		MobileElement e = (MobileElement) getDriver().findElementByXPath("//android.widget.TextView[@resource-id='com.meraki.mapidemo:id/usageValue']");
		return e;
	}


	private MobileElement getAPClientElement(){
		MobileElement e = (MobileElement) getDriver().findElementByXPath("//android.widget.TextView[@resource-id='com.meraki.mapidemo:id/numClientsValue']");
		return e;
	}



	public String getAPModel() throws InterruptedException {
		String model = getText(getAPModelElement());
		utils.log().info("model is - " + model);
		return model;

	}


	public String getAPSerial() throws InterruptedException {
		String serial = getText(getAPSerialElement());
		utils.log().info("serial is - " + serial);
		return serial;

	}


	public String getAPUsage() throws InterruptedException {
		String usage = getText(getAPUsageElement());
		utils.log().info("usage is - " + usage);
		return usage;
	}


	public String getAPClient() throws InterruptedException {
		String client = getText(getAPClientElement());
		utils.log().info("client is - " + client);
		return client;
	}


}
