package edu.swust.goods.service.impl;

import java.util.Date;
import java.util.List;

import edu.swust.goods.domain.ReqCode;
import edu.swust.goods.service.IReqCodeService;

public class ReqCodeServiceImpl extends BaseServiceImpl<ReqCode> implements IReqCodeService {
	private static final String DELETE_HQL = "delete ReqCode code where (? - code.date ) > 300000";
	private static final String SEARCH_HQL = "select code from ReqCode code where code.email = ? and code.verification = ?";
    
	/**
	 * 根据账号得到用户对象
	 */
	public ReqCode getAccount(String account, boolean flag) {
		Object[] objects1 = {new Date().getTime()};
		Integer verification = flag ? 1 : 0;
		Object[] objects2 = {account, verification};
		baseDao.executeUpdateHql(DELETE_HQL, objects1);
		@SuppressWarnings("unchecked")
		List<ReqCode> codes = (List<ReqCode>) baseDao.find(SEARCH_HQL, objects2, null, null);
		ReqCode code = null;
		if (codes != null && codes.size() == 1) {
			code = codes.get(0);
		}
		return code;
	}
	
	

}
