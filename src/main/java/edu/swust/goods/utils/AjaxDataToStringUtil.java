package edu.swust.goods.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.servlet.http.HttpServletRequest;
/**
 * 负责提取HTTP请求中的数据成字符串
 * @author hanpeng
 *
 */
public class AjaxDataToStringUtil {
    private static final String CHARSET = "utf-8";
	public static String getAjaxString(HttpServletRequest request) {
        String ajax = null;
        BufferedReader br;
		try {
			br = new BufferedReader(new InputStreamReader(request.getInputStream(),CHARSET));
			String line = null;  
			StringBuilder sb = new StringBuilder();  
			while ((line = br.readLine()) != null) {  
			  sb.append(line);  
			}
			ajax = sb.toString();
		} catch (IOException e) {
		}  
		
		return ajax;
    }
}
