package edu.swust.goods.service.impl;

import java.util.ArrayList;
import java.util.List;

import edu.swust.goods.domain.VerifyLog;
import edu.swust.goods.service.IVerifyLogService;
import edu.swust.goods.utils.GlobalMessage;
import edu.swust.goods.view.VerifyLogView;

public class VerifyLogServiceImpl extends BaseServiceImpl<VerifyLog> implements IVerifyLogService {
	String GET_ALL_HQL = "select log from VerifyLog log order by log.date";
	public List<VerifyLogView> allVerifyLogs(Integer start) {
		
		
		@SuppressWarnings("unchecked")
		List<VerifyLog> logs = (List<VerifyLog>) baseDao.find(GET_ALL_HQL, null, start, GlobalMessage.PAGE_SIZE);
		Integer size = baseDao.find(GET_ALL_HQL, null, start, GlobalMessage.PAGE_SIZE).size();
		List<VerifyLogView> views = new ArrayList<VerifyLogView>();
		if (logs != null) {
			for (VerifyLog log : logs) {
				views.add(new VerifyLogView(log));
			}
		}
		views.add(new VerifyLogView(start, size / GlobalMessage.PAGE_SIZE));
		return views;
	}
	
}
