package edu.swust.goods.service;

import edu.swust.goods.domain.UnderShelfGoods;
import edu.swust.goods.domain.User;
import edu.swust.goods.view.UnderShelfGoodsView;

public interface IUnderShelfGoodsService extends IBaseService<UnderShelfGoods> {
	UnderShelfGoodsView getDetailById(Long id);
    boolean delete(Long id, User user);
}
