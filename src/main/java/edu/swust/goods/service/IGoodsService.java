package edu.swust.goods.service;

import java.util.List;

import edu.swust.goods.domain.Goods;
import edu.swust.goods.domain.User;
import edu.swust.goods.tempbean.QueryBean;
import edu.swust.goods.view.ContactView;
import edu.swust.goods.view.GoodsView;
import edu.swust.goods.view.PaginationView;
import edu.swust.goods.view.SegmentView;
import edu.swust.goods.view.SimpleGoodsView;
import edu.swust.goods.view.UnderShelfGoodsView;

public interface IGoodsService extends IBaseService<Goods> {
	
	boolean delete(Long id, String reason, User user);
	
	PaginationView<SimpleGoodsView> ownGoodsList(User user, Integer start);
	
	List<SimpleGoodsView> recommend(Long id);
	
	GoodsView goodsDetail(Long id, User user);
	
	PaginationView<GoodsView> pendingReview(Integer start);
	
	List<ContactView> contact(Long id);
	
	PaginationView<UnderShelfGoodsView> ownUnderShelf(User user, Integer start);
	
	PaginationView<UnderShelfGoodsView> ownCollectUnderShelf(User user, Integer start);
	
	PaginationView<SimpleGoodsView> ownCollect(User user, Integer start);
	
	PaginationView<SimpleGoodsView> byPrice(QueryBean query, int flag, Integer size);
	
	PaginationView<SimpleGoodsView> bySynthesis(QueryBean query, Integer size);
	
	PaginationView<SimpleGoodsView> byCollection(QueryBean query, Integer size) ;
	
	PaginationView<SimpleGoodsView> byDate(QueryBean query, Integer size);
	
	List<SegmentView<SimpleGoodsView>> homeRecommend();
	
}
