package javainterface.demo;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import util.Common;
import util.httpOperate;
import util.md5Encrypt;

@ContextConfiguration(locations = { "classpath:spring-postDemo-config.xml" })
public class postDemo extends AbstractTestNGSpringContextTests {

	@Autowired
	private Common common;
	@Autowired
	private httpOperate httpOperate;
	@Autowired
	private md5Encrypt md5encrypt;
	@Autowired
	private PostMmodel postModel;

	@Test
	//post请求
	public void postMethod() throws IOException, NoSuchAlgorithmException {
		String url = "http://192.168.0.171:8098/Api/EPack/GetGJCode";
		String method = "POST";
		String contentType = "application/x-www-form-urlencoded";
		postModel.setSign(getSign());
		// System.out.println("数据："+mymodel.toString());

		resultCheck(url, method, contentType, postModel.toString());
	}

	// 获取签名
	private String getSign() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		StringBuilder sBuilder = new StringBuilder();
		// 参数按升序添加
		sBuilder.append("appKey").append(postModel.getAppKey());// 用户名
		sBuilder.append("appSecret").append(postModel.getAppSecret());// 用户密钥
		sBuilder.append("code1").append(postModel.getCode1());// 一段码

		sBuilder.append("extCode").append(postModel.getExtCode());// 扩展码，如EBay的带电属性
		sBuilder.append("format").append(postModel.getFormat());// 默认 json
		sBuilder.append("packNumber").append(postModel.getPackNumber());// 获取包牌号的数量

		sBuilder.append("siteId").append(postModel.getSiteId());// 获取包牌号的网点编号
		postModel.setTimeStamp(System.currentTimeMillis());// 13位时间戳
		sBuilder.append("timeStamp").append(postModel.getTimeStamp());// 13位的utc时间戳
		sBuilder.append("version").append(postModel.getVersion());// 默认1.0

		// MD5加密
		return md5encrypt.GetMD5Encrypt(postModel.getAppSecret() + sBuilder.toString() + postModel.getAppSecret());// 签名
	}

	// 打印报告信息
	private void resultCheck(String url, String method, String contentType, String info) throws IOException {
		String httpResult = httpOperate.getHttpRespone(url, method, contentType, info);
		Reporter.log("请求地址:" + url);
		Reporter.log("请求参数:" + info);
		Reporter.log("返回结果: " + httpResult);
		// System.out.println("返回结果: " + httpResult);
		String statusCode = common.getJsonValue(httpResult, "StatusCode");
		// System.out.println("返回结果StatusCode: " + statusCode);
		Assert.assertEquals(statusCode, "OK");
	}
}

class PostMmodel {
	private String appKey;
	private String appSecret;
	private long timeStamp;
	private String format;
	private String version;
	private String code1;
	private String siteId;
	private String packNumber;
	private String extCode;
	private String sign;

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getCode1() {
		return code1;
	}

	public void setCode1(String code1) {
		this.code1 = code1;
	}

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	public String getPackNumber() {
		return packNumber;
	}

	public void setPackNumber(String packNumber) {
		this.packNumber = packNumber;
	}

	public String getExtCode() {
		return extCode;
	}

	public void setExtCode(String extCode) {
		this.extCode = extCode;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	@Override
	public String toString() {
		return "appKey=" + appKey + "&appSecret=" + appSecret + "&timeStamp=" + timeStamp + "&format=" + format
				+ "&version=" + version + "&code1=" + code1 + "&siteId=" + siteId + "&packNumber=" + packNumber
				+ "&extCode=" + extCode + "&sign=" + sign;
	}
}
