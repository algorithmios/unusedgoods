package edu.swust.goods.utils;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * 将对象或者对象列表进行序列化，或者根据字符串进行反序列化
 * @author hanpeng
 *
 */
public class JsonUtil {
	/**
	 * 将对象列表进行序列化
	 * @param list 待初始化列表
	 * @return 序列化的字符串
	 */
    public static String listToString(List<?> list) {
    	String jsonStr = null;
        try {
            JSONArray jsons = JSONArray.fromObject(list);
            jsonStr = jsons.toString();
 	    } catch (Exception e) {
 	    	
        }
        return jsonStr;
	}
    /**
     * 将对象进行序列化
     * @param object 待序列化对象
     * @return 序列化的字符串
     */
    public static String objectToJson(Object object) {
	    String jsonStr = null;
	    try {
	        JSONObject json = JSONObject.fromObject(object);
	        jsonStr = json.toString();
        } catch (Exception e) {
        	e.printStackTrace();
	    }
		return jsonStr;
	}
    /**
     * 序列化的字符串转为对象
     * @param json 序列化的字符串
     * @param clazz 待转换对象的class
     * @return 反序列化的对象
     */
    public static Object stringToObject(String json, Class<?> clazz) {
        Object object = null;
        try {
        	JSONObject jsonObject = JSONObject.fromObject(json);
    		object = JSONObject.toBean(jsonObject, clazz);
		} catch (Exception e) {
		}
		return object;
	}
}
