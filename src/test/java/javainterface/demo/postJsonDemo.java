package javainterface.demo;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import util.Common;
import util.httpOperate;

@ContextConfiguration(locations = { "classpath:spring-postJsonDemo-config.xml" })
public class postJsonDemo  extends AbstractTestNGSpringContextTests{
	@Autowired
	private Common common;
	@Autowired
	private httpOperate httpOperate;
	@Autowired
	private UserModel userModel;

	@Test
	//post请求
	public void postMethod() throws IOException, NoSuchAlgorithmException {
		String url = "http://192.168.124.120/intlprint/index.php/Api/Auth/token";
		String method = "POST";
		String contentType = "application/json";
		
		resultCheck(url, method, contentType, userModel.toString());
	}
	
	// 打印报告信息
	private void resultCheck(String url, String method, String contentType, String info) throws IOException {
		String httpResult = httpOperate.getHttpRespone(url, method, contentType, info);
		Reporter.log("请求地址:" + url);
		Reporter.log("请求参数:" + info);
		Reporter.log("返回结果: " + httpResult);
		//System.out.println("请求参数:" + info);
		//System.out.println("返回结果: " + httpResult);
		String success = common.getJsonValue(httpResult, "success");
		// System.out.println("返回结果StatusCode: " + statusCode);
		Assert.assertEquals(success, "true");
	}
}

class UserModel{
	private String username;
	private String password;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "{\"username\":\"" + username + "\", \"password\":\"" + password + "\"}";
	}
}
