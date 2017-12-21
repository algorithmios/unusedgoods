package edu.swust.goods.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import edu.swust.goods.domain.GoodsImage;
/**
 * 从图片集合中获取第一个
 * @author hanpeng
 *
 */
public class HeadImageUrlUtil {
	private static List<GoodsImage> images = new ArrayList<GoodsImage>();
	private static final String DEFAULT_IMAGE = null;
	public static String headImageUrl(Collection<GoodsImage> myImages) {
		images.clear();
		if (myImages != null) {
			images.addAll(myImages);
		}
		return images.size() > 0 ? images.get(0).getImageUrl() : DEFAULT_IMAGE;
	}
}
