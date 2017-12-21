package edu.swust.goods.action;

import java.io.IOException;

import edu.swust.goods.domain.Administrator;
import edu.swust.goods.domain.User;
import edu.swust.goods.service.IAdminService;
import edu.swust.goods.service.IBaseUserService;
import edu.swust.goods.service.IUserService;
import edu.swust.goods.tempbean.LoginInfoBean;
import edu.swust.goods.tempbean.ReplyItem;
import edu.swust.goods.utils.AjaxDataToStringUtil;
import edu.swust.goods.utils.GlobalMessage;
import edu.swust.goods.utils.JsonUtil;
import edu.swust.goods.utils.ReplyUtil;
import edu.swust.goods.view.AdminView;
import edu.swust.goods.view.BaseUserView;
import edu.swust.goods.view.UserView;
/**
 * 登录相关逻辑处理类
 * @author hanpeng
 *
 */
public class LoginAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private IUserService userService;
    private IAdminService adminService;
    private final String[] replyInfos = {"数据传输错误","验证码错误","用户名错误","密码错误"};
    private static final String OUT_SUCCESS = "退出成功";
    /**
     * 实现登录功能
     * @param service 管理员或者用户的service
     * @return 登录信息
     * @throws IOException
     */
	private ReplyItem login(IBaseUserService<?> service) throws IOException {	
		LoginInfoBean loginBean = getLoginInfo();
		ReplyItem item = null;
		if (loginBean == null) {
            ReplyUtil.reply(response, JsonUtil.objectToJson(new ReplyItem(GlobalMessage.NO_SIGN, replyInfos[0], null)));
		} else  {
			item = service.checkLogin(loginBean);
			if (item.getId() < 0) {
				item.setValue(replyInfos[-item.getId().intValue()]);
				item.setId(GlobalMessage.NO_SIGN);
			} else {
				request.getSession().setAttribute(GlobalMessage.CURRENT_USER, item);
			}
			ReplyUtil.reply(response, JsonUtil.objectToJson(item));
		}
		return item;
	}
	
	/**
	 * 得到传递过来的登录信息
	 * @return 登录信息转化成的LoginInfoBean对象
	 * @throws IOException
	 */
	private LoginInfoBean getLoginInfo() throws IOException {
		String jsonString = AjaxDataToStringUtil.getAjaxString(request);
		return (LoginInfoBean) JsonUtil.stringToObject(jsonString, LoginInfoBean.class);
	}
	/**
	 * 用户登录
	 * @throws Exception
	 */
	public void userIn() throws Exception {
		ReplyItem item = login(userService);
        if (item != null && item.getId() > 0) {
        	BaseUserView view = userService.getView(item.getId());
			if (view != null && view instanceof UserView) {
				request.getSession().setAttribute(GlobalMessage.USER, (UserView)view);
				request.getSession().removeAttribute(GlobalMessage.ADMIN);
			}
		}
        
	}
	/**
	 * 管理员登录
	 * @throws Exception
	 */
	public void adminIn() throws Exception {
		ReplyItem item = login(adminService);
		if (item != null && item.getId() > 0) {
			BaseUserView view = adminService.getView(item.getId());
			if (view != null && view instanceof AdminView) {
				request.getSession().setAttribute(GlobalMessage.ADMIN, (AdminView)view);
				request.getSession().removeAttribute(GlobalMessage.USER);
			}
		}
	}
	/**
	 * 测试接口
	 */
	public void admin() {
		System.out.println("admin");
		ReplyItem item = new ReplyItem();
		
		Administrator administrator = adminService.get(1L);
		item.setId(1L);
		item.setValue(administrator.getNickname());
		item.setUrl(administrator.getPermit().toString());
		BaseUserView view = adminService.getView(item.getId());
		request.getSession().setAttribute(GlobalMessage.ADMIN, (AdminView)view);
		request.getSession().setAttribute(GlobalMessage.CURRENT_USER, item);
	}
	/**
	 * 测试接口
	 */
	public void superIn() {
		ReplyItem item = new ReplyItem();
		
		User user = userService.get(1L);
		item.setId(1L);
		item.setValue(user.getNickname());
		item.setUrl(user.getHeadImageUrl());
		BaseUserView view = userService.getView(item.getId());
		request.getSession().setAttribute(GlobalMessage.USER, (UserView)view);
		request.getSession().setAttribute(GlobalMessage.CURRENT_USER, item);
	}
	
	/**
	 * 注销当前用户
	 * @throws Exception
	 */
	public void out() throws Exception {
		request.getSession().removeAttribute(GlobalMessage.CURRENT_USER);
		request.getSession().removeAttribute(GlobalMessage.ADMIN);
		request.getSession().removeAttribute(GlobalMessage.USER);
		ReplyUtil.reply(response, JsonUtil.objectToJson(new ReplyItem(GlobalMessage.OK_SIGN, OUT_SUCCESS, null)));
	}
	/**
	 * 得到当前登录用户的简要信息
	 */
    public void currentInfo() {
        ReplyItem item = (ReplyItem) request.getSession().getAttribute(GlobalMessage.CURRENT_USER);
        if (item == null) {
			item = new ReplyItem(GlobalMessage.NO_SIGN, GlobalMessage.LOGIN_ERROR, null);
		}
        ReplyUtil.reply(response, JsonUtil.objectToJson(item));
	}
    
	public IUserService getUserService() {
		return userService;
	}
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	public IAdminService getAdminService() {
		return adminService;
	}
	public void setAdminService(IAdminService adminService) {
		this.adminService = adminService;
	}

}