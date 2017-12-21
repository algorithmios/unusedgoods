package edu.swust.goods.service.impl;

import java.util.Set;

import edu.swust.goods.domain.UnderShelfGoods;
import edu.swust.goods.domain.User;
import edu.swust.goods.service.IUnderShelfGoodsService;
import edu.swust.goods.utils.ImageUtil;
import edu.swust.goods.view.UnderShelfGoodsView;

public class UnderShelfGoodsServiceImpl extends BaseServiceImpl<UnderShelfGoods> implements IUnderShelfGoodsService {
    /**
     * 根据ID获得下架商品的视图
     */
	public UnderShelfGoodsView getDetailById(Long id) {
		UnderShelfGoods goods = get(id);
		UnderShelfGoodsView view = null;
		if (goods != null) {
			view = new UnderShelfGoodsView(goods);
		}
		return view;
	}
    /**
     * 删除一条下架商品记录
     * @param id 记录id
     * @param user 商品拥有者
     * @return 是否删除成功
     */
	public boolean delete(Long id, User user) {
		UnderShelfGoods goods = get(id);
		boolean result = false;
		if (goods!= null) {
			Set<UnderShelfGoods> set = user.getUnderShelfGoods();
			for (UnderShelfGoods underShelfGoods : set) {
				if (goods.getId().equals(underShelfGoods.getId())) {
					ImageUtil.deleteImage(goods.getImageUrl());
					delete(id);
					result = true;
					break;
				}
			}
		}
		return result;
	}
	
}
