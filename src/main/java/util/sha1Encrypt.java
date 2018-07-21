package util;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;

public class sha1Encrypt {
	public String buildSign(String secret,String logisticsInfo,String partnerCode,String timestamp)
			throws IOException {
		StringBuilder paramNameValue = new StringBuilder();
		//参数按升序排序
		paramNameValue.append("logisticsInfo").append(logisticsInfo);
		paramNameValue.append("partnerCode").append(partnerCode);
		paramNameValue.append("timestamp").append(timestamp);

		String sha1Source = secret + paramNameValue.toString() + secret;
		return sha1Encode(sha1Source);
	}
	
	public String sha1Encode(final String data) throws IOException {
		return byte2hex(getSHA1Digest(data));
	}
	
	private byte[] getSHA1Digest(String data) throws IOException {
		byte[] bytes = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			bytes = md.digest(data.getBytes("UTF-8"));
		} catch (GeneralSecurityException gse) {
			throw new IOException(gse);
		}
		return bytes;
	}
	
	private String byte2hex(byte[] bytes) {
		StringBuilder sign = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			String hex = Integer.toHexString(bytes[i] & 0xFF);
			if (hex.length() == 1) {
				sign.append("0");
			}
			sign.append(hex.toUpperCase());
		}
		return sign.toString();
	}
}
