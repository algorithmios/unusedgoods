package edu.swust.goods.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Set;


import edu.swust.goods.dao.IUnderShelfGoodsDao;
import edu.swust.goods.domain.Goods;
import edu.swust.goods.domain.UnderShelfGoods;
import edu.swust.goods.domain.User;
import edu.swust.goods.service.IGoodsService;
import edu.swust.goods.tempbean.QueryBean;
import edu.swust.goods.utils.GlobalMessage;
import edu.swust.goods.utils.HeadImageUrlUtil;
import edu.swust.goods.view.ContactView;
import edu.swust.goods.view.GoodsView;
import edu.swust.goods.view.PaginationView;
import edu.swust.goods.view.SegmentView;
import edu.swust.goods.view.SimpleGoodsView;
import edu.swust.goods.view.UnderShelfGoodsView;

public class GoodsServiceImpl extends BaseServiceImpl<Goods> implements IGoodsService {
	
	private IUnderShelfGoodsDao underShelfGoodsDao;
	private List<Object> objects =new ArrayList<Object>();
	private static final String BASE_HQL = "select goods from Goods goods where goods.status = 2";
	private static final String CALSSIFICATION_CONDITION = " and (goods.classification = ?)";
	private static final String KEY_WORD_CONDITION = " and (goods.name like ? or goods.introduction like ?)";
	private static final String MIN_PRICE_CONDITION = " and (goods.price >= ?)";
	private static final String MAX_PRICE_CONDITION = " and (goods.price <= ?)";
	private static final String PAGEVIEWS_CONDITION = " ORDER BY goods.pageViews ";
	private static final String PRICE_ORDER_CONDITION = " ORDER BY goods.price ";
	private static final String COLLECTION_ORDER = " ORDER BY goods.uploadTime DESC";
	private static final String SYSTHEY_ORDER = " ORDER BY goods.pageViews + goods.collectedCount * 2";
	private static final String OWN_GOODS_HQL = "select goods from Goods goods where goods.owner = ?"
			                                    + " ORDER BY goods.uploadTime";
	private static final String OWN_UNDER_SHELF_HQL = "select goods from UnderShelfGoods goods where goods.owner = ?"
			                                          + " ORDER BY goods.uploadTime";
	private static final String RECOMMEND_HQL = "select goods from Goods goods where goods != ?"
                        	                    + " and goods.status=2 and goods.classification = ? order by abs(goods.price - ?)";
	private static final String DESC = "DESC";
	private static final String SYSTHESIS = "综合推荐";
	private static final String NEWST = "最新发布";
	private static final String AFFORDABLE = "最实惠";
	private static final String COLLECTION = "收藏量";
	private static final String PAGE_VIEWS = "浏览量";
	private static final String OTHER = "其他";
	private static Integer HOME_PAGE_SIZE = 8;
	
	private static int PRICE_DESC_FLAG = 1;
	
	private static final int PENDING_SIZE = 7;
	
	private static final int OWN_GOODS_PAGE_SIZE = 8;
	
	private static final int DEFAULT_START = 0;
	/**
	 * 更新收藏量
	 */
	@Override
	public void saveOrUpdate(Goods t) {
		Integer size = t.getCollecters().size();
		t.setCollectedCount(size.longValue());
		super.saveOrUpdate(t);
	}
	/**
	 * 构建hql查询语句
	 * @param query 查询条件
	 * @return 对应hql的 StringBuffer
	 */
	private StringBuffer searchBuffer(QueryBean query) {
		StringBuffer buffer = new StringBuffer(BASE_HQL);
		objects.clear();
		if (query != null) {
			if (query.getClassification() != null) {
				objects.add(query.getClassification());
				buffer.append(CALSSIFICATION_CONDITION);
			}
			if (query.getKeyWords() != null) {
				objects.add(query.getKeyWords());
				objects.add(query.getKeyWords());
				buffer.append(KEY_WORD_CONDITION);
			}
			if (query.getMin() != null) {
				objects.add(query.getMin());
				buffer.append(MIN_PRICE_CONDITION);
			}
			if (query.getMax() != null) {
				objects.add(query.getMax());
				buffer.append(MAX_PRICE_CONDITION);
			}
		}
		return buffer;
	}
	/**
     * 综合推荐、发售时间、价格
	 * 收藏量、浏览量、其他
	 * 图片 三处
     * @return
     */
	public List<SegmentView<SimpleGoodsView>> homeRecommend() {
		List<SegmentView<SimpleGoodsView>> list = new ArrayList<SegmentView<SimpleGoodsView>>();
		
		List<SimpleGoodsView> views = bySynthesis(null, HOME_PAGE_SIZE).getList();
		list.add(new SegmentView<SimpleGoodsView>(SYSTHESIS, views));
		
		views = byDate(null, HOME_PAGE_SIZE).getList();
		list.add(new SegmentView<SimpleGoodsView>(NEWST, views));
		
		views = byPrice( null, PRICE_DESC_FLAG, HOME_PAGE_SIZE).getList();
		list.add(new SegmentView<SimpleGoodsView>(AFFORDABLE, views));
		
		views = byCollection(null, HOME_PAGE_SIZE).getList();
		list.add(new SegmentView<SimpleGoodsView>(COLLECTION, views));

		views = byPageViews(null, HOME_PAGE_SIZE).getList();
		list.add(new SegmentView<SimpleGoodsView>(PAGE_VIEWS, views));
		
		views = byOther(null, HOME_PAGE_SIZE).getList();
		list.add(new SegmentView<SimpleGoodsView>(OTHER, views));

		return list;
	}
	
	private PaginationView<SimpleGoodsView> byPageViews(QueryBean query, int size) {
		final StringBuffer PAGE_VIEWS_ORDER = new StringBuffer(PAGEVIEWS_CONDITION);
		return searchResult(query, PAGE_VIEWS_ORDER.toString(), size);
	}

	private PaginationView<SimpleGoodsView> byOther(QueryBean query, int size) {
		return searchResult(query, "", size);
	}

	/**
	 * 通过价格 
	 * @param query 查询条件
	 * @param flag 1-->降序   其他 升序
	 * @return
	 */
	public PaginationView<SimpleGoodsView> byPrice(QueryBean query, int flag, Integer size) {
		final StringBuffer PRICE_ORDER = new StringBuffer(PRICE_ORDER_CONDITION);
		
		if (flag == 1) {
			PRICE_ORDER.append(DESC);
		}
		return searchResult(query, PRICE_ORDER.toString(), size);
	}
	
	public PaginationView<SimpleGoodsView> bySynthesis(QueryBean query, Integer size) {
		return searchResult(query, SYSTHEY_ORDER, size);
	}
	
	public PaginationView<SimpleGoodsView> byCollection(QueryBean query, Integer size) {
		final String COLLECTION_ORDER = " ORDER BY goods.collectedCount DESC";
		return searchResult(query, COLLECTION_ORDER, size);
	}
	
	public PaginationView<SimpleGoodsView> byDate(QueryBean query, Integer size) {
		return searchResult(query, COLLECTION_ORDER, size);
	}
	
	/**
	 * 搜索结果
	 * @param query 查询条件对象
	 * @param condition 附带条件语句
	 * @param size 结果条数
	 * @return 查询结果
	 */
	private PaginationView<SimpleGoodsView>  searchResult(QueryBean query, String condition, Integer size) {
		StringBuffer buffer = searchBuffer(query);
		buffer.append(condition);
		Integer start = query==null ? 0 : query.getStart();
		size = size == null ? GlobalMessage.PAGE_SIZE : size;
		if (HOME_PAGE_SIZE.equals(size)) {
			start = 0;
		}
		final String hql = buffer.toString();
		@SuppressWarnings("unchecked")
		List<Goods> list = (List<Goods>) baseDao.find(hql, objects.toArray(), start * size, size);
		int count = baseDao.find(hql, objects.toArray(), null, null).size();
		List<SimpleGoodsView> views = goodsToView(list);
		return new PaginationView<SimpleGoodsView>(start , count / size + 1, views);
	}


	/**
	 * 删除一个商品
	 */
	public boolean delete(Long id, String reason, User user) {
        Goods goods = get(id);
        boolean result = false;
        if (goods != null) {
            if (user.getGoods().contains(goods)) {
            	UnderShelfGoods underShelfGoods = new UnderShelfGoods(goods, reason, 
			               HeadImageUrlUtil.headImageUrl(goods.getImages()));
                underShelfGoodsDao.saveOrUpdate(underShelfGoods);
                Set<User> users = goods.getCollecters();
                System.out.println(users.size());
                for (User item : users) {
                    item.getCollectedUnderShelfGoods().add(underShelfGoods);
                }
                baseDao.delete(id);
                result = true;
			}
			
		}
        return result;
	}
	/**
     * 用户上架的商品
     */
    public PaginationView<SimpleGoodsView> ownGoodsList(User user, Integer start) {
    	Object[] objects = {user};
    	start = start == null? DEFAULT_START : start;
		@SuppressWarnings("unchecked")
		List<Goods> list = (List<Goods>) baseDao.find(OWN_GOODS_HQL, objects, start, OWN_GOODS_PAGE_SIZE);
		int count = baseDao.find(OWN_GOODS_HQL, objects, null, null).size();
		PaginationView<SimpleGoodsView> paginationView = 
				new PaginationView<SimpleGoodsView>(start, count / OWN_GOODS_PAGE_SIZE + 1, goodsToView(list));
    	return paginationView;
    }

    
    /**
     * 用户下架的商品
     */
    public PaginationView<UnderShelfGoodsView> ownUnderShelf(User user, Integer start) {
    	
    	Object[] objects = {user};
    	start = start == null? DEFAULT_START : start;
		@SuppressWarnings("unchecked")
		List<UnderShelfGoods> list = (List<UnderShelfGoods>) baseDao.find(OWN_UNDER_SHELF_HQL, objects, start * OWN_GOODS_PAGE_SIZE, OWN_GOODS_PAGE_SIZE);
		int count = baseDao.find(OWN_UNDER_SHELF_HQL, objects, null, null).size();
		List<UnderShelfGoodsView> view = new ArrayList<UnderShelfGoodsView>();
		for (UnderShelfGoods goods : list) {
			view.add(new UnderShelfGoodsView(goods));
		}
		PaginationView<UnderShelfGoodsView> paginationView = 
				new PaginationView<UnderShelfGoodsView>(start, count / OWN_GOODS_PAGE_SIZE + 1, view);
    	return paginationView;
	}
    /**
     * 待审核的商品列表
     */
    public PaginationView<GoodsView> pendingReview(Integer start) {
		final String hql = "select goods from Goods goods where goods.status = 0 ORDER BY goods.uploadTime";
		start = start == null? DEFAULT_START : start;
		@SuppressWarnings("unchecked")
		List<Goods> list = (List<Goods>) baseDao.find(hql, objects.toArray(), start * PENDING_SIZE, PENDING_SIZE);
		int count = baseDao.find(hql, objects.toArray(), null, null).size();
		List<GoodsView> views = new ArrayList<GoodsView>();
		for (Goods goods : list) {
			views.add(new GoodsView(goods));
		}
		return new PaginationView<GoodsView>(start, count / PENDING_SIZE + 1, views);
	}
    /**
     * 商品详情
     */
	public GoodsView goodsDetail(Long id, User user) {
		Goods goods = get(id);
		GoodsView view = null;
		if (goods.getStatus() == 2) {
			goods.setPageViews(goods.getPageViews() + 1);
		}
		boolean collected = false;
		if (user != null) {
			collected  = user.getCollecteds().contains(goods);
		}
		
		if (goods != null) {
			view = new GoodsView(goods, collected, getContact(goods.getOwner()));
		}
		saveOrUpdate(goods);
		
		return view;
	}
	
	/**
	 * 得到指定人员的连续方式
	 */
	public List<ContactView> contact(Long id) {
		Goods goods = get(id);
		User user = goods.getOwner();
		return getContact(user);
	}
	
	/**
	 * 得到指定人员的连续方式
	 */
	private List<ContactView> getContact(User user){
		List<ContactView> list = new ArrayList<ContactView>();
		if (user != null) {
			list.add(new ContactView(GlobalMessage.WEIXIN, user.getWeixin()));
	        list.add(new ContactView(GlobalMessage.QQ, user.getQq()));
	        list.add(new ContactView(GlobalMessage.PHONE, user.getPhone()));
		}
		return list;
	}
	
	/**
	 * 商品映射成视图，防止懒加载
	 * @param goods 待映射商品信息列表
	 * @return 商品信息映射对象列表
	 */
	private List<SimpleGoodsView> goodsToView(Collection<Goods> goods) {
		List<SimpleGoodsView> list = new ArrayList<SimpleGoodsView>();
        for (Goods item : goods) {
			list.add(new SimpleGoodsView(item, HeadImageUrlUtil.headImageUrl(item.getImages())));
		}
        return list;
	}
	
	/**
	 * 推荐
	 */
	public List<SimpleGoodsView> recommend(Long id) {
        Goods goods = get(id);
        List<SimpleGoodsView> views = null;
        if (goods != null) {
            System.out.println(goods.getId() + " " + goods.getClassification() + " " + goods.getPrice());
            Object[] objects = {goods, goods.getClassification(), goods.getPrice()};
            @SuppressWarnings("unchecked")
			List<Goods> list = (List<Goods>)baseDao.find(RECOMMEND_HQL, objects, 0, 4);
            views = goodsToView(list);
		}
        
		return views;
	}
	
    /**
     * 收藏商品中已经下架的部分
     */
	public PaginationView<UnderShelfGoodsView> ownCollectUnderShelf(User user, Integer start) {
		Set<UnderShelfGoods> set = user.getCollectedUnderShelfGoods();
		List<UnderShelfGoods> list = new ArrayList<UnderShelfGoods>();
		int count = set.size();
		list.addAll(set);
		list.sort(new Comparator<UnderShelfGoods>() {
			public int compare(UnderShelfGoods o1, UnderShelfGoods o2) {
				return o1.getUnderShelfTime().compareTo(o2.getUnderShelfTime());
			}
		});
		int newStart;
		if (start == null) {
			start = DEFAULT_START;
		}
		newStart = start * OWN_GOODS_PAGE_SIZE;
		int end = newStart + OWN_GOODS_PAGE_SIZE;
		List<UnderShelfGoodsView> view = new ArrayList<UnderShelfGoodsView>();
		for (int i = newStart; i < count && i < end; i++) {
			view.add(new UnderShelfGoodsView(list.get(i)));
		}
    	return new PaginationView<UnderShelfGoodsView>(start, count / OWN_GOODS_PAGE_SIZE + 1, view);
	}
    
	/**
	 * 我的收藏列表
	 */
	public PaginationView<SimpleGoodsView> ownCollect(User user, Integer start) {
		Set<Goods> set = user.getCollecteds();
		List<Goods> list = new ArrayList<Goods>();
		int count = set.size();
		list.addAll(set);
		list.sort(new Comparator<Goods>() {

			public int compare(Goods o1, Goods o2) {
				return -o1.getUploadTime().compareTo(o2.getUploadTime());
			}
		});
		int newStart;
		if (start == null) {
			start = DEFAULT_START;
		}
		newStart = start * OWN_GOODS_PAGE_SIZE;
		int end = newStart + OWN_GOODS_PAGE_SIZE;
		List<SimpleGoodsView> view = new ArrayList<SimpleGoodsView>();
		for (int i = newStart; i < count && i < newStart + end; i++) {
			view.add(new SimpleGoodsView(list.get(i)));
		}
		return new PaginationView<SimpleGoodsView>(start, count / OWN_GOODS_PAGE_SIZE + 1, view);
	}
	
	public IUnderShelfGoodsDao getUnderShelfGoodsDao() {
		return underShelfGoodsDao;
	}

	public void setUnderShelfGoodsDao(IUnderShelfGoodsDao underShelfGoodsDao) {
		this.underShelfGoodsDao = underShelfGoodsDao;
	}

}