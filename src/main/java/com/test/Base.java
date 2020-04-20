package com.test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.InteractsWithApps;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import com.test.utils.TestUtils;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Properties;
import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;

public class Base {
	protected static ThreadLocal <AppiumDriver> driver = new ThreadLocal<AppiumDriver>();
	protected static ThreadLocal <Properties> props = new ThreadLocal<Properties>();
	protected static ThreadLocal <HashMap<String, String>> strings = new ThreadLocal<HashMap<String, String>>();
	protected static ThreadLocal <String> platform = new ThreadLocal<String>();
	protected static ThreadLocal <String> dateTime = new ThreadLocal<String>();
	protected static ThreadLocal <String> deviceName = new ThreadLocal<String>();
	TestUtils utils = new TestUtils();

	public AppiumDriver getDriver() {
		return driver.get();
	}

	public void setDriver(AppiumDriver driver2) {
		driver.set(driver2);
	}

	public Properties getProps() {
		return props.get();
	}

	public void setProps(Properties props2) {
		props.set(props2);
	}

	public HashMap<String, String> getStrings() {
		return strings.get();
	}

	public void setStrings(HashMap<String, String> strings2) {
		strings.set(strings2);
	}

	public String getPlatform() {
		return platform.get();
	}

	public void setPlatform(String platformP) {
		platform.set(platformP);
	}

	public String getDateTime() {
		return dateTime.get();
	}

	public void setDateTime(String dateTime2) {
		dateTime.set(dateTime2);
	}

	public String getDeviceName() {
		return deviceName.get();
	}

	public void setDeviceName(String deviceName2) {
		deviceName.set(deviceName2);
	}

	public Base() {
		PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
	}

	@Parameters({"emulator", "platformName", "udid", "deviceName", "systemPort", "chromeDriverPort"})
	@BeforeTest
	public void beforeTest(String emulator,String platformName,String udid,String deviceName, 
			String systemPort,String chromeDriverPort) throws Exception {

		setDateTime(utils.dateTime());
		setPlatform(platformName);
		setDeviceName(deviceName);
		URL url;
		InputStream inputStream = null;
		InputStream stringsis = null;
		Properties props = new Properties();
		AppiumDriver driver;

		String strFile = "logs" + File.separator + platformName + "_" + deviceName;
		File logFile = new File(strFile);
		if (!logFile.exists()) {
			logFile.mkdirs();
		}
		//route logs to separate file for each thread
		ThreadContext.put("ROUTINGKEY", strFile);
		utils.log().info("log path: " + strFile);

		try {
			props = new Properties();
			String propFileName = "config.properties";
			String xmlFileName = "strings/strings.xml";
			utils.log().info("load " + propFileName);
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
			props.load(inputStream);
			setProps(props);
			utils.log().info("load " + xmlFileName);
			stringsis = getClass().getClassLoader().getResourceAsStream(xmlFileName);
			setStrings(utils.parseStringXML(stringsis));
			
			DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
			desiredCapabilities.setCapability("platformName", platformName);
			desiredCapabilities.setCapability("deviceName", deviceName);
			desiredCapabilities.setCapability("udid", udid);
			url = new URL(props.getProperty("appiumURL"));
			desiredCapabilities.setCapability("automationName", props.getProperty("androidAutomationName"));	
			desiredCapabilities.setCapability("appPackage", props.getProperty("androidAppPackage"));
			desiredCapabilities.setCapability("appActivity", props.getProperty("androidAppActivity"));
			desiredCapabilities.setCapability("avd", deviceName);
			desiredCapabilities.setCapability("systemPort", systemPort);
			desiredCapabilities.setCapability("chromeDriverPort", chromeDriverPort);
			String androidAppUrl = getClass().getResource(props.getProperty("androidAppLocation")).getFile().substring(1);
			utils.log().info("appUrl is " + androidAppUrl);
			desiredCapabilities.setCapability("app", androidAppUrl);
			driver = new AndroidDriver(url, desiredCapabilities);
			setDriver(driver);
			utils.log().info("driver initialized: " + driver);
		} catch (Exception e) {
			utils.log().fatal("driver initialization failure. ABORT!!!\n" + e.toString());
			throw e;
		} finally {
			if(inputStream != null) {
				inputStream.close();
			}
			if(stringsis != null) {
				stringsis.close();
			}
		}
	}

	public void waitForVisibility(MobileElement e) {
		WebDriverWait wait = new WebDriverWait(getDriver(), TestUtils.WAIT);
		wait.until(ExpectedConditions.visibilityOf(e));
	}

	public void fluentwaitForVisibility(WebElement e){
		Wait<WebDriver> wait = new FluentWait<WebDriver>(getDriver())
				.withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(5))
				.ignoring(NoSuchElementException.class);

		wait.until(ExpectedConditions.visibilityOf(e));
	}

	public void clear(MobileElement e) {
		fluentwaitForVisibility(e);
		e.clear();
	}

	public void click(MobileElement e) {
		fluentwaitForVisibility(e);
		e.click();
	}

	public void sendKeys(MobileElement e, String txt) {
		fluentwaitForVisibility(e);
		e.sendKeys(txt);
	}

	public String getAttribute(MobileElement e, String attribute) {
		fluentwaitForVisibility(e);
		return e.getAttribute(attribute);
	}

	public String getText(MobileElement e) {
		fluentwaitForVisibility(e);
		if(e!=null)
		return getAttribute(e, "text");
		else
		return null;
	}

	public void closeApp() {
		((InteractsWithApps) getDriver()).closeApp();
	}

	public void launchApp() {
		((InteractsWithApps) getDriver()).launchApp();
	}


	@AfterTest
	public void afterTest() throws InterruptedException {
		getDriver().quit();
	}
}
