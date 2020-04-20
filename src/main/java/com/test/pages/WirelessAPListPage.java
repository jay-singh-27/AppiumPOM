package com.test.pages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.test.Base;
import com.test.repo.APRepo;
import com.test.utils.TestUtils;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;


public class WirelessAPListPage extends Base{
	APRepo repo;
	TestUtils utils = new TestUtils();

	@AndroidFindBy (className = "android.widget.TextView") 
	private MobileElement ap_MenuPageTitle;

	@AndroidFindBy (xpath = "//androidx.recyclerview.widget.RecyclerView[@resource-id = 'com.meraki.mapidemo:id/deviceList']/child::android.view.ViewGroup") 
	private List<MobileElement> apList;

	@AndroidFindBy (xpath = "//android.widget.TextView[@resource-id='com.meraki.mapidemo:id/deviceName']")
	private List<MobileElement> apName;

	@AndroidFindBy (xpath = "//android.widget.TextView[@resource-id = 'com.meraki.mapidemo:id/deviceDetails']") 
	private List<MobileElement> apIP;

	@AndroidFindBy (xpath = "//android.widget.ImageView[@resource-id = 'com.meraki.mapidemo:id/deviceIcon']") 
	private List<MobileElement> apIcon;

	private MobileElement getAPNameElement(String key){
		MobileElement e = (MobileElement) getDriver().findElementByXPath("(//android.widget.TextView[@resource-id='com.meraki.mapidemo:id/deviceName'])["+key+"]");
		return e;
	}


	private MobileElement getAPIPElement(String key){
		MobileElement e = (MobileElement) getDriver().findElementByXPath("(//android.widget.TextView[@resource-id='com.meraki.mapidemo:id/deviceDetails'])["+key+"]");
		return e;
	}


	private MobileElement getAPIconElement(String key){
		MobileElement e = (MobileElement) getDriver().findElementByXPath("(//android.widget.ImageView[@resource-id='com.meraki.mapidemo:id/deviceIcon'])["+key+"]");
		return e;
	}



	public String getTitle() {
		String title = getText(ap_MenuPageTitle);
		utils.log().info("ap menu page title is - " + title);
		return title;
	}


	public void printAPNameAndIP(){
		List<String> ap = new ArrayList();
		if(apList==null) return;
		for(MobileElement e: apList){
			ap.add(getText(e));
		}
	}


	public String getAPName(String key) throws InterruptedException {
		Thread.sleep(3000);
		String name = getText(getAPNameElement(key));
		utils.log().info("name is - " + name);
		return name;

	}

	public String getAPIP(String key) throws InterruptedException {
		Thread.sleep(3000);
		String ip = getText(getAPIPElement(key));
		utils.log().info("ip is - " + ip);
		return ip;
	}


	public AP_DetailPage clickAPIcon(String key) throws InterruptedException, IOException {
		Thread.sleep(3000);
		utils.log().info("click AP Icon");
		click(getAPIconElement(key));
		return new AP_DetailPage();
	}


	public APRepo fetchAPData(String key) throws IOException {
		return new APRepo(key);
	}


}
