package util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class httpOperate {
	/*
	 * url：访问地址 method：提交类型（GET、POST等） contentType
	 */
	public HttpURLConnection getConnection(String url, String method, String contentType) {
		HttpURLConnection connection = null;
		try {
			// 1.得到URL对象
			URL myurl = new URL(url);
			// 2.打开连接
			connection = (HttpURLConnection) myurl.openConnection();
			// 3.设置提交类型
			connection.setRequestMethod(method);
			// 4.设置允许写出数据
			connection.setDoOutput(true);
			// 当前的连接可以从服务器读取内容
			connection.setDoInput(true);
			// 设置不使用缓存
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			
			if (contentType != "") {
				connection.setRequestProperty("Content-Type", contentType);
			}
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("Charset", "utf-8");
			connection.setRequestProperty("Accept-Charset", "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}

	/*
	 * url：请求地址
	 * method：请求方式
	 * contentType：
	 * requestInfo：请求信息
	*/
	public String getHttpRespone(String url, String method, String contentType, String requestInfo) throws IOException {
		String line = "";
		StringBuffer  httpResults = null;
		HttpURLConnection connection=null;
		DataOutputStream out = null;
		BufferedReader reader =null;
		// url = ("http://www.weather.com.cn/data/cityinfo/" + cityCode + ".html");
		try {
		 connection = getConnection(url, method, contentType);
			
			// 建立实际的连接
			connection.connect();
			
			if (requestInfo != null && requestInfo.length()>0) {
				// 创建一个新的数据输出流，将数据写入指定基础输出流
				out = new DataOutputStream(connection.getOutputStream());
				//发送请求参数
				out.write(requestInfo.getBytes());
				//flush输出流的缓冲
				out.flush();
			}
			

		    reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		    httpResults = new StringBuffer();
		    while ((line = reader.readLine()) != null) {
				httpResults.append(line.toString());
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(out!=null)
				out.close();
			if(reader!=null)
				// 关闭该流并释放与之关联的所有资源
				reader.close();
			if(connection!=null)
				// 断开连接
				connection.disconnect();
		}
		return httpResults.toString();
	}

}
