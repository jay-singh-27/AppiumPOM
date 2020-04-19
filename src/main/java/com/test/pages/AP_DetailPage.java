package com.test.pages;

import java.util.List;

import org.testng.annotations.Test;

import com.test.utils.TestUtils;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

import com.test.Base;
import com.test.models.APModel;

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
