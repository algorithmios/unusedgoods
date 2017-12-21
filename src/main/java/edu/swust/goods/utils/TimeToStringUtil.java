package edu.swust.goods.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Date类转字符串
 * @author hanpeng
 *
 */
public class TimeToStringUtil {
    private static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final SimpleDateFormat formatter = new SimpleDateFormat(TIME_FORMAT);
    
    public static String timeToString(Date date) {
		return date == null ? null : formatter.format(date);
	}
}
