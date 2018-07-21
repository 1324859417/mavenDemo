package util;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class md5Encrypt {

	/**
	 * MD5加密
	 * 
	 * @param input需加密的字符串
	 * @return 
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public String GetMD5Encrypt(String input) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		byte[] result = md5.digest(input.getBytes("utf-8"));
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i < result.length; i++) {
			String hex = Integer.toHexString(result[i] & 0xFF);// ת��Ϊ16����
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sBuilder.append(hex);
		}
		return sBuilder.toString();
	}
}
