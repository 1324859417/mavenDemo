package jmeter.plugin;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

public class javaRequestNew extends AbstractJavaSamplerClient {
	private SampleResult sample;

	// 设置入参参数名，及参数默认值
	@Override
	public Arguments getDefaultParameters() {
		Arguments arguments = new Arguments();
		arguments.addArgument("a", "1");
		arguments.addArgument("b", "4");
		return arguments;
	}

	@Override
	public void setupTest(JavaSamplerContext context) {
		sample = new SampleResult();
		sample.setSampleLabel("插件开发");
		sample.setDataEncoding("utf-8");
		sample.setDataType(SampleResult.TEXT);

	}

	@SuppressWarnings("deprecation")
	@Override
	public SampleResult runTest(JavaSamplerContext arg0) {
		// 测试开始时间戳
		sample.sampleStart();
		hello hel = new hello();
		try {
			// 根据实际情况的逻辑处理
			int mysum = hel.sum(Integer.parseInt(arg0.getParameter("a")), Integer.parseInt(arg0.getParameter("b")));
			String responseData = String.valueOf(mysum);

			// 设置返回信息
			if (responseData != null && responseData != "") {
				sample.setResponseData("返回结果为：" + responseData);
			} else {
				sample.setResponseData("返回结果为空");
			}

			// 测试通过
			sample.setSuccessful(true);
		} catch (Exception e) {
			// 测试失败
			sample.setSuccessful(false);
		} finally {
			// 测试结束时间戳
			sample.sampleEnd();
		}

		return sample;
	}
}
