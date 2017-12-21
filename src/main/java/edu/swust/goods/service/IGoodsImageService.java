package edu.swust.goods.service;

import java.util.List;

import edu.swust.goods.domain.GoodsImage;
import edu.swust.goods.view.ImageUrlView;

public interface IGoodsImageService extends IBaseService<GoodsImage> {
	List<ImageUrlView> homeImageUrl();
}
