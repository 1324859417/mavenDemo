package appium.demo;

public class DeviceInfo {
	// 连接appium服务端的url
	public String url;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	// 启动的设备名称，可在appium的手机配置中查看
	public String deviceName;
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	
	// 使用的自动化引擎：appium、selendroid  默认为appium
	public String automationName;
	public String getAutomationName() {
		return automationName;
	}
	public void setAutomationName(String automationName) {
		this.automationName = automationName;
	}
	
	// 启动的移动平台：ios、android、FirefoxOS
	public String platformName;
	public String getPlatformName() {
		return platformName;
	}
	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}
	
	// 启动的移动平台的版本，可在appium的手机配置中查看
	public String platformVersion;
	public String getPlatformVersion() {
		return platformVersion;
	}
	public void setPlatformVersion(String platformVersion) {
		this.platformVersion = platformVersion;
	}
}
