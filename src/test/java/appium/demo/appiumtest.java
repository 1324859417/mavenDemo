package appium.demo;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;

@ContextConfiguration(locations = { "classpath:spring-appium-config.xml" })
public class appiumtest extends AbstractTestNGSpringContextTests {
	@Autowired
	private DeviceInfo deviceInfo;
	@Autowired
	private AppInfo appInfo;
	
	private DesiredCapabilities capabilities;
	@SuppressWarnings("rawtypes")
	private AndroidDriver  driver ;
	private static Logger logger = Logger.getLogger(appiumtest.class);

	@BeforeClass
	public void befourRun() {
		
		// 启动appium
		capabilities = new DesiredCapabilities();
		// 启动的设备名称，可在appium的手机配置中查看
		capabilities.setCapability("deviceName", deviceInfo.getDeviceName());
		// 使用的自动化引擎：appium、selendroid 默认为appium
		capabilities.setCapability("automationName", deviceInfo.getAutomationName());
		// 启动的移动平台：ios、android、FirefoxOS
		capabilities.setCapability("platformName", deviceInfo.getPlatformName());
		// 启动的移动平台的版本，可在appium的手机配置中查看
		capabilities.setCapability("platformVersion", deviceInfo.getPlatformVersion());
		
		// 配置测试apk
		// 安装app
		capabilities.setCapability("app", appInfo.getApp());
	}
	
	@AfterClass
	public void afterRun() {
		driver.quit();
		logger.info("end...");
	}

	@SuppressWarnings("rawtypes")
	@Test
	public void runTest() throws MalformedURLException, InterruptedException {
		// 把配置传到appium服务端并连接手机
		driver = new AndroidDriver(new URL(deviceInfo.getUrl()), capabilities);
		//隐式等待
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(2000);
	}
}
