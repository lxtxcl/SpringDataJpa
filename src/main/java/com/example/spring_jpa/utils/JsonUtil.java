package com.example.spring_jpa.utils;



import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * @author Andy
 *
 *         解析json数据
 */
public class JsonUtil {


	/**
	 * 根据key查询信息
	 */
	public static Object getArrayByFastjson(String data) {
		// 获取数据
		JSONObject jsonObject = JSONObject
				.parseObject(data);
		return jsonObject.get("data");
	}

	/**
	 * 接口的json数据格式
	 */
	public static JSONObject toJsonByFastjson(
			boolean isFlag, Object obj) {
		JSONObject jsonResult = new JSONObject(); // new一个JSON
		jsonResult.put("result", isFlag);
		jsonResult.put("data", obj);
		return jsonResult;
	}

	/**
	 * 接口的json数据格式
	 */
	public static JSONObject toJson(boolean isFlag, Object obj) {
		JSONObject jsonResult = new JSONObject(); // new一个JSON
		jsonResult.put("result", isFlag);
		jsonResult.put("data", obj);
		return jsonResult;
	}

	/**
	 * 错误的接口json数据
	 */
	public static JSONObject toErrorJson(String errorCode, String errorMsg) {
		JSONObject jsonResult = new JSONObject(); // new一个JSON
		jsonResult.put("result", false);
		jsonResult.put("errorCode", errorCode);
		jsonResult.put("errorMsg", errorMsg);
		return jsonResult;
	}

	/**
	 * 错误的接口json数据
	 */
	public static JSONObject toErrorJsonByFastjson(
			String errorCode, String errorMsg) {
		JSONObject jsonResult = new JSONObject(); // new一个JSON
		jsonResult.put("result", false);
		jsonResult.put("errorCode", errorCode);
		jsonResult.put("errorMsg", errorMsg);
		return jsonResult;
	}

	/* --------------------- 处理web的json数据 ------------------------ */

	/**
	 * 处理多个数据的返回的json数据
	 */
	public static JSONObject toJson(Map<String, Object> obj) {
		// 获取数据信息
		JSONObject jsonResult = new JSONObject(); // new一个JSON
		for (Map.Entry<String, Object> entry : obj.entrySet()) {
			jsonResult.put(entry.getKey(), entry.getValue());
		}
		return jsonResult;
	}

	/**
	 * 处理单个数据的返回的json数据
	 */
	public static JSONObject toJson(String key,
			Object value) {
		// 获取数据信息
		JSONObject jsonResult = new JSONObject(); // new一个JSON
		jsonResult.put(key, value);
		return jsonResult;
	}

	/**
	 * 处理单个数据的返回的json数据
	 */
	public static JSONObject toJson(Object value) {
		// 获取数据信息
		JSONObject jsonResult = new JSONObject(); // new一个JSON
		jsonResult.put("data", value);
		return jsonResult;
	}

}
