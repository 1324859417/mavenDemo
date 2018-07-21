package jmeter.plugin;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

public class javaRequest implements JavaSamplerClient {
	private SampleResult result;
	private static Log log = LogFactory.getLog(javaRequest.class);

	// 设置入参参数名，及参数默认值
	@Override
	public Arguments getDefaultParameters() {
		Arguments arguments = new Arguments();
		arguments.addArgument("name", "你好");
		arguments.addArgument("age", "18");
		return arguments;
	}

	// 执行方法
	@SuppressWarnings("deprecation")
	@Override
	public SampleResult runTest(JavaSamplerContext arg0) {
		// 获取参数值
		String name = arg0.getParameter("name");
		String age = arg0.getParameter("age");
		String responseData = "返回结果：name=" + name + ";age=" + age;
		// 写日志
		log.info(responseData);
		try {
			// 测试开始时间戳
			result.sampleStart();
			// 设置返回信息，可根据实际需求做更改
			result.setResponseData(responseData);
			// 测试通过
			result.setSuccessful(true);
		} catch (Exception e) {
			result.setSuccessful(false);
			log.error("java请求错误：" + e.getMessage());
		} finally {
			// 测结束时间戳
			result.sampleEnd();
		}

		return result;
	}

	@Override
	public void setupTest(JavaSamplerContext arg0) {
		result = new SampleResult();
		result.setSampleLabel("插件开发");
		result.setDataEncoding("utf-8");
		result.setDataType(SampleResult.TEXT);
	}

	@Override
	public void teardownTest(JavaSamplerContext arg0) {
		// 写日志
		log.info("end......");
	}
	
	//本地调试用
//	public static void main(String[] args) {
//		javaRequest jr = new javaRequest();
//		Arguments arguments = jr.getDefaultParameters();
//		JavaSamplerContext arg0 = new JavaSamplerContext(arguments);
//		jr.setupTest(arg0);
//		jr.runTest(arg0);
//		jr.teardownTest(arg0);
//	}
}
