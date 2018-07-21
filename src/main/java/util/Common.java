package util;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class Common {
	//获取json字符串中，key对应的value值
	public String getJsonValue(String JsonString, String JsonId) {
		String JsonValue = "";
		if (JsonString == null || JsonString.trim().length() < 1) {
			return null;
		}

		try {
			JSONObject obj1 = new JSONObject(JsonString);
			JsonValue = (String) obj1.getString(JsonId);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return JsonValue;
	}
}
