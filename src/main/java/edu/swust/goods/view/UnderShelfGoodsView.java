package edu.swust.goods.view;

import edu.swust.goods.domain.UnderShelfGoods;
import edu.swust.goods.utils.TimeToStringUtil;
/**
 * 下架商品映射类
 * @author hanpeng
 *
 */
public class UnderShelfGoodsView extends SimpleGoodsView{
	private String underShelfTime;
	public UnderShelfGoodsView() {
	}
    
	public UnderShelfGoodsView(UnderShelfGoods goods) {
		super(goods, goods.getImageUrl());
        this.underShelfTime = TimeToStringUtil.timeToString(goods.getUnderShelfTime());
	}

	public String getUnderShelfTime() {
		return underShelfTime;
	}

	public void setUnderShelfTime(String underShelfTime) {
		this.underShelfTime = underShelfTime;
	}

	@Override
	public String toString() {
		return "UnderShelfGoodsView [underShelfTime=" + underShelfTime + ", id=" + id + ", name=" + name + ", price="
				+ price + ", uploadTime=" + uploadTime + ", ownerName=" + ownerName + ", ownerId=" + ownerId
				+ ", imageUrl=" + imageUrl + ", classification=" + classification + "]";
	}


}
