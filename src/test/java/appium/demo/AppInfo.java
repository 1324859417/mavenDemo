package appium.demo;

public class AppInfo {
	// 待测app的入口activity名称
	public String appActivity;
	public String getAppActivity() {
		return appActivity;
	}
	public void setAppActivity(String appActivity) {
		this.appActivity = appActivity;
	}
	
	// 待测app的jar包
	public String appPackage;
	public String getAppPackage() {
		return appPackage;
	}
	public void setAppPackage(String appPackage) {
		this.appPackage = appPackage;
	}
	
	// 待安装app路径，安装apk时使用
	public String app;
	public String getApp() {
		return app;
	}
	public void setApp(String app) {
		this.app = app;
	}
	
	// 每次启动时覆盖session，否则第二次后运行会报错不能新建session
	public String sessionOverride;
	public String isSessionOverride() {
		return sessionOverride;
	}
	public void setSessionOverride(String sessionOverride) {
		this.sessionOverride = sessionOverride;
	}
	
	// 设置键盘：支持中文输入
	public String unicodeKeyboard;
	public String isUnicodeKeyboard() {
		return unicodeKeyboard;
	}
	public void setUnicodeKeyboard(String unicodeKeyboard) {
		this.unicodeKeyboard = unicodeKeyboard;
	}
	
	// 设置默认键盘为appium的键盘，必须两条都配置
	public String resetKeyboard;
	public String isResetKeyboard() {
		return resetKeyboard;
	}
	public void setResetKeyboard(String resetKeyboard) {
		this.resetKeyboard = resetKeyboard;
	}
}
