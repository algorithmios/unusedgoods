package edu.swust.goods.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import edu.swust.goods.tempbean.ReplyItem;
/**
 * 负责回复请求
 * @author hanpeng
 *
 */
public class ReplyUtil {
    private static ReplyItem okItem = new ReplyItem(GlobalMessage.OK_SIGN, null, null);
    private static ReplyItem noItem = new ReplyItem(GlobalMessage.NO_SIGN, null, null);
	/**
	 * 回复自定义内容
	 * @param response
	 * @param result 自定义内容 
	 */
    public static void reply(HttpServletResponse response, String result) {
    	PrintWriter writer = null;
    	try {
			writer = response.getWriter();
			writer.print(result);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
    }
    /**
     * 操作成功的回复
     * @param response
     * @param value 提示信息
     */
	public static void replyOK(HttpServletResponse response, String value) {
		okItem.setValue(value);
        reply(response, JsonUtil.objectToJson(okItem));
	}
	/**
	 * 操作失败的回复
	 * @param response
	 * @param value 提示信息
	 */
	public static void replyNO(HttpServletResponse response, String value) {
		noItem.setValue(value);
		reply(response, JsonUtil.objectToJson(noItem));
	}
}
