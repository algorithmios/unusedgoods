package edu.swust.goods.service.impl;

import java.util.ArrayList;
import java.util.List;

import edu.swust.goods.domain.GoodsImage;
import edu.swust.goods.service.IGoodsImageService;
import edu.swust.goods.view.ImageUrlView;

public class GoodsImageServiceImpl extends BaseServiceImpl<GoodsImage> implements IGoodsImageService {
    
	private static final String IMAGE_HQL = "select image from GoodsImage image";

	private static final int SIZE = 7;
	/**
	 * 首页图片
	 */
	public List<ImageUrlView> homeImageUrl() {
        @SuppressWarnings("unchecked")
		List<GoodsImage> images = (List<GoodsImage>) baseDao.find(IMAGE_HQL, null, 0, SIZE);
        List<ImageUrlView> views = new ArrayList<ImageUrlView>();
        for (GoodsImage image : images) {
			views.add(new ImageUrlView(image));
		}
        return views;
    }
}
