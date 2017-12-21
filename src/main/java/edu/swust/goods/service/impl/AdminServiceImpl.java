package edu.swust.goods.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import edu.swust.goods.domain.Administrator;
import edu.swust.goods.domain.VerifyLog;
import edu.swust.goods.service.IAdminService;
import edu.swust.goods.tempbean.LoginInfoBean;
import edu.swust.goods.tempbean.ReplyItem;
import edu.swust.goods.view.AdminView;
import edu.swust.goods.view.BaseUserView;
import edu.swust.goods.view.PaginationView;
import edu.swust.goods.view.VerifyLogView;

public class AdminServiceImpl extends BaseServiceImpl<Administrator> implements IAdminService {
	private static final int SIZE = 9;
    private static final String ALL_ADMIN_SQL = "select admin from Administrator admin where admin.permit != 1";
    private static final String ACCOUNT_HQL = "select  admin from Administrator admin where admin.account = ?";
    private static final Long PASSWORD_ERROR = -3L;
    private static final Long ACCOUNT_ERROR = -2L;
    /**
     * 得到所有管理员
     */
    public PaginationView<AdminView> getAllAdmin(Integer start) {
    	
    	if (start == null) {
			start = 0;
		}
        @SuppressWarnings("unchecked")
		List<Administrator> list = (List<Administrator>) baseDao.find(ALL_ADMIN_SQL, null, start * SIZE, SIZE);
        int count = baseDao.find(ALL_ADMIN_SQL, null, null, null).size();
        List<AdminView> views = new ArrayList<AdminView>();
        for (Administrator administrator : list) {
			views.add(new AdminView(administrator));
		}
    	return new PaginationView<AdminView>(start, count / SIZE + 1, views);
    }
    /**
     * 得到当前用户的审核记录
     */
    public List<VerifyLogView> myVerifyLog(Administrator admin) {        
        List<VerifyLogView> views = new ArrayList<VerifyLogView>();
        if (admin != null) {
        	Set<VerifyLog> logs = admin.getVerifyLogs();
        	if (logs != null) {
				for (VerifyLog log : logs) {
					views.add(new VerifyLogView(log));
				}
			}
		}
		return views;
	}
    /**
     * 登录检查
     */
    public ReplyItem checkLogin(LoginInfoBean bean) {
		
		List<Administrator> list = null;
		if (bean.getAccount() != null) {
			list = getAccount(bean.getAccount());
		}
        ReplyItem replyItem = new ReplyItem();
        
        if (list != null && list.size() == 1) {
        	Administrator admin = list.get(0);
			if (bean.getPassword() != null && bean.getPassword().equals(admin.getPassword())) {
				replyItem.setUrl(String.valueOf(admin.getPermit()));
				replyItem.setId(admin.getId());
				replyItem.setValue(admin.getNickname());
			} else {
				replyItem.setId(PASSWORD_ERROR);
			}
		} else {
			replyItem.setId(ACCOUNT_ERROR);
		}
        return replyItem;
	}
    /**
     * 检查当前用户是否存在
     */
    public Administrator accountExist(String account) {
    	Administrator result = null;
		List<Administrator> list = null;
		if (account != null) {
			list = getAccount(account);
		}
		if (list != null && list.size() == 1) {
			result = list.get(0);
		}
    	return result;
	}
    /**
     * 根据账号获得对应的管理员
     * @param account 对查找的管理员账号
     * @return 账号对应的管理员
     */
    @SuppressWarnings("unchecked")
	private List<Administrator> getAccount(String account) {
    	
		Object[] objects = {account};
		return (List<Administrator>) baseDao.find(ACCOUNT_HQL, objects, null, null);
    }

	public BaseUserView getView(Long id) {
		Administrator admin = get(id);
		AdminView view = null;
		if (admin != null) {
			view = new AdminView();
			view.setId(admin.getId());
			view.setNickname(admin.getNickname());
			view.setPermit(admin.getPermit());
		}
		return view;
	}
    
}
