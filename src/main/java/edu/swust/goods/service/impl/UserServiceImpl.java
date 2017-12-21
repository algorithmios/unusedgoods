package edu.swust.goods.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import edu.swust.goods.domain.Dialog;
import edu.swust.goods.domain.DialogContent;
import edu.swust.goods.domain.Goods;
import edu.swust.goods.domain.UnderShelfGoods;
import edu.swust.goods.domain.User;
import edu.swust.goods.service.IUserService;
import edu.swust.goods.tempbean.DialogBean;
import edu.swust.goods.tempbean.LoginInfoBean;
import edu.swust.goods.tempbean.ReplyItem;
import edu.swust.goods.utils.GlobalMessage;
import edu.swust.goods.view.BaseUserView;
import edu.swust.goods.view.ContactView;
import edu.swust.goods.view.DialogContentView;
import edu.swust.goods.view.DialogView;
import edu.swust.goods.view.PaginationView;
import edu.swust.goods.view.UserView;

public class UserServiceImpl extends BaseServiceImpl<User> implements IUserService {

	private static final String ACCOUNT_HQL = "select user from User user where user.account = ?";
	
	private static final int DIALOG_SIZE = 7;
	
	/**
	 * 联系方式视图
	 */
	public List<ContactView> contactsView(User user) {
		List<ContactView> list = new ArrayList<ContactView>();
        list.add(new ContactView(GlobalMessage.WEIXIN, user.getWeixin()));
        list.add(new ContactView(GlobalMessage.QQ, user.getQq()));
        list.add(new ContactView(GlobalMessage.PHONE, user.getPhone()));
		return list;
	}
	
	/**
	 * 查询登录信息
	 */
	public ReplyItem checkLogin(LoginInfoBean bean) {
		List<User> list = null;
        if (bean != null && bean.getAccount() != null) {
			list = getAccount(bean.getAccount());
		}
        ReplyItem replyItem = new ReplyItem();
        
        if (list != null && list.size() == 1) {
			User user = list.get(0);
			if (bean.getPassword() != null && bean.getPassword().equals(user.getPassword())) {
				replyItem.setUrl(user.getHeadImageUrl());
				replyItem.setId(user.getId());
				replyItem.setValue(user.getNickname());
			} else {
				replyItem.setId(-3L);
			}
		} else {
			replyItem.setId(-2L);
		}
        return replyItem;
	}
	
	/**
	 * 检查账号是否存在
	 */
	public User accountExist(String account) {
		User result = null;
		List<User> list = null;
		if (account != null) {
			list = getAccount(account);
		}
		if (list == null || list.size() == 1) {
			result = list.get(0);
		}
    	return result;
	}
	
	/**
	 * 根据账号获得角色信息
	 * @param account 账号
	 * @return 角色信息
	 */
    @SuppressWarnings("unchecked")
	private List<User> getAccount(String account) {
		Object[] objects = {account};
		return (List<User>) baseDao.find(ACCOUNT_HQL, objects, null, null);
    }
	
    /**
     * 得到基本的用户信息
     */
	public BaseUserView getView(Long id) {
		UserView view = null;
		User user = get(id);
		if (user != null) {
			view = new UserView();
			view.setHeadImageUrl(user.getHeadImageUrl());
			view.setId(user.getId());
			view.setNickname(user.getNickname());
		}
		return view;
	}
	/**
	 * 添加收藏记录
	 */
	public void addCollection(User user, Goods goods) {
		user.getCollecteds().add(goods);
        saveOrUpdate(user);
	}
	
	/**
	 * 取消收藏
	 * @param user 当前用户
	 * @param id 待取消商品ID
	 * @return 是否取消成功
	 */
	public void cancleCollection(User user, Long id) {
		removeUnderShelfCollectionLog(user.getCollectedUnderShelfGoods(), id);
		removeCollectionLog(user.getCollecteds(), id);
		saveOrUpdate(user);
	}

	/**
	 * 移除的收藏记录
	 * @param set 用户所有的记录
	 * @param id 待删除的记录的ID
	 */
	private void removeUnderShelfCollectionLog(Set<UnderShelfGoods> set, Long id) {
		UnderShelfGoods target = null;
		for (UnderShelfGoods baseGood : set) {
			if (baseGood.getId().equals(id)) {
				target = baseGood;
				break;
			}
		}
		if (target != null) {
			set.remove(target);
		}
	}
	
	/**
	 * 移除的收藏记录
	 * @param set 用户所有的记录
	 * @param id 待删除的记录的ID
	 */
	private void removeCollectionLog(Set<Goods> set, Long id) {
		Goods target = null;
		for (Goods baseGood : set) {
			if (baseGood.getId().equals(id)) {
				target = baseGood;
				break;
			}
		}
		if (target != null) {
			set.remove(target);
		}
	}

	/**
	 * 得到对话列表
	 */
	public PaginationView<DialogView> listDialog(User user, Integer start) {
        List<Dialog> dialogsA = new ArrayList<Dialog>();
        dialogsA.addAll(user.getRoleA());
        List<Dialog> dialogsB = new ArrayList<Dialog>();
        dialogsA.addAll(user.getRoleB());
        List<DialogView> views = new ArrayList<DialogView>();
        System.out.println(dialogsA.size());
        System.out.println(dialogsB.size());
        if (start == null) {
			start = 0;
		}
        int newStart = start * DIALOG_SIZE;
        int all = dialogsA.size() + dialogsB.size();
        int end = all > newStart + DIALOG_SIZE? newStart + DIALOG_SIZE : all;
        if (newStart >= end) {
			newStart = end;
		}
        for (Dialog dialog : dialogsB) {
        	
			List<DialogContentView> list = new ArrayList<DialogContentView>();
			dialog.setNoReadB(0);
			
			List<DialogContent> contents = new ArrayList<DialogContent>();
			contents.addAll(dialog.getContents());
			contents.sort(null);
			for (DialogContent dialogContent : contents) {
				list.add(new DialogContentView(dialogContent));
			}
			views.add(new DialogView(dialog.getRoleA().getId(), list));

		}
        for (Dialog dialog : dialogsA) {
			
			List<DialogContentView> list = new ArrayList<DialogContentView>();
			dialog.setNoReadA(0);
			
			List<DialogContent> contents = new ArrayList<DialogContent>();
			contents.addAll(dialog.getContents());
			contents.sort(null);
			for (DialogContent dialogContent : contents) {
				list.add(new DialogContentView(dialogContent));
			}
			
			views.add(new DialogView(dialog.getRoleA().getId(), list));
			
		}
        
        saveOrUpdate(user);
        views.sort(null);
        views = views.subList(newStart, end);
        
        return new PaginationView<DialogView>(start, all / DIALOG_SIZE + 1, views);
	}
	
	/**
	 * 添加对话
	 */
    public void addDialog(User user, DialogBean bean) {
        Set<Dialog> dialogsA = user.getRoleA();
        Set<Dialog> dialogsB = user.getRoleB();
        
        if (bean != null) {
            String contentStr = bean.getContent();
            String name = null;
            User target = get(bean.getId());
            if (target != null) {
				name = target.getNickname();
			}
            DialogContent content = new DialogContent();
			content.setContent(contentStr);
			content.setDate(new Date());
			content.setRoleA(user.getNickname());
			content.setRoleB(name);
			boolean flag = false;
			System.out.println("A=" + dialogsA.size());
			System.out.println("B=" + dialogsB.size());
            for (Dialog dialog : dialogsA) {
				if (dialog.getRoleB().getId().equals(bean.getId())) {
					dialog.getContents().add(content);
					dialog.setNoReadB(dialog.getNoReadB() + 1);
					content.setDialog(dialog);
					dialog.setDate(new Date());
					flag = true;
				}
			}
            if (!flag) {
            	for (Dialog dialog : dialogsB) {
    				if (dialog.getRoleA().getId().equals(bean.getId())) {
    					dialog.getContents().add(content);
    					dialog.setNoReadA(dialog.getNoReadA() + 1);
    					content.setDialog(dialog);
    					dialog.setDate(new Date());
    					flag = true;
    				}
    			}
			}
            System.out.println("flag = " + flag);
            if (!flag) {
				Dialog dialog = new Dialog();
				dialog.setDate(new Date());
				dialog.setRoleA(user);
				dialog.setRoleB(target);
				dialog.getContents().add(content);
				dialog.setNoReadB(1);
				content.setDialog(dialog);
				user.getRoleA().add(dialog);
				target.getRoleB().add(dialog);
			}
            saveOrUpdate(user);
            saveOrUpdate(target);
		}
    }
    /**
     * 获得对话数量
     */
    public int dialogCount(User user) {
		int sum = 0;
		Set<Dialog> dialogsA = user.getRoleA();
        Set<Dialog> dialogsB = user.getRoleB();
        for (Dialog dialog : dialogsB) {
			sum += dialog.getNoReadB();
		}
        for (Dialog dialog : dialogsA) {
			sum += dialog.getNoReadB();
		}
        return sum;
	}
}
