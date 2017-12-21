package edu.swust.goods.service.impl;

import java.util.ArrayList;
import java.util.List;

import edu.swust.goods.domain.Suggestion;
import edu.swust.goods.service.ISuggestionService;
import edu.swust.goods.utils.GlobalMessage;
import edu.swust.goods.view.SuggestionView;

public class SuggestionServiceImpl extends BaseServiceImpl<Suggestion> implements ISuggestionService {
	private static final String UNREAD_COUNT_HQL = "select count(suggestion) from Suggestion suggestion where suggestion.isRead = 0";
	private static final String UNREAD_LIST_HQL = "select suggestion from Suggestion suggestion "
			+ "where suggestion.isRead = 0 order by suggestion.date";
	private static final String ALL_LIST_HQL = "select suggestion from Suggestion suggestion order by suggestion.date";
	
	/**
	 * 为读取建议数量
	 */
	public long unreadCount() {
        @SuppressWarnings("unchecked")
		List<Long> list = (List<Long>) baseDao.find(UNREAD_COUNT_HQL, null, null, null);
		long result = 0;
        if (list.size() > 0) {
			result = list.get(0);
		}
        return result;
	}

	/**
	 * 未读取建议的列表
	 */
	public List<SuggestionView> unreadList(Integer start) {
		return returnView(UNREAD_LIST_HQL, start);
	}

	
	/**
	 * 所有的建议列表
	 */
	public List<SuggestionView> allList(Integer start) {
		return returnView(ALL_LIST_HQL, start);
	}
    
	/**
	 * 得到建议列表视图
	 * @param hql 查询语句
	 * @param start 开始位置
	 * @return 建议列表视图
	 */
	private List<SuggestionView> returnView(String hql, Integer start) {
		final int READ_FLAG = 1;
		@SuppressWarnings("unchecked")
		List<Suggestion> suggestions = (List<Suggestion>) baseDao.find(hql, null, start, GlobalMessage.PAGE_SIZE);
		int count = baseDao.find(hql, null, null, null).size();
		for (Suggestion suggestion : suggestions) {
			suggestion.setIsRead(READ_FLAG);
			saveOrUpdate(suggestion);
		}
		List<SuggestionView> views = new ArrayList<SuggestionView>();
        for (Suggestion suggestion : suggestions) {
			views.add(new SuggestionView(suggestion));
		}
		views.add(new SuggestionView(start, count / GlobalMessage.PAGE_SIZE));
		return views;
	}
}
