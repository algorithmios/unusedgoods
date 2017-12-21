package edu.swust.goods.action;

import java.util.Date;

import edu.swust.goods.domain.Administrator;
import edu.swust.goods.domain.Suggestion;
import edu.swust.goods.domain.User;
import edu.swust.goods.service.IAdminService;
import edu.swust.goods.service.ISuggestionService;
import edu.swust.goods.service.IUserService;
import edu.swust.goods.utils.AjaxDataToStringUtil;
import edu.swust.goods.utils.CurrentUserUtil;
import edu.swust.goods.utils.GlobalMessage;
import edu.swust.goods.utils.JsonUtil;
import edu.swust.goods.utils.ReplyUtil;
/**
 * 意见相关逻辑处理类
 * @author hanpeng
 *
 */
public class SuggestionAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	private ISuggestionService suggestionService;
	private IUserService userService;
	private IAdminService adminService;
	private static final int UNREAD = 0;
	/**
	 * 得到未读建议的数量
	 */
    public void count() {
    	Administrator admin = CurrentUserUtil.getCurrentAdmin(adminService, request);
    	if (admin != null) {
            long count = suggestionService.unreadCount();
            ReplyUtil.replyOK(response, String.valueOf(count));
		} else {
			ReplyUtil.replyNO(response, GlobalMessage.LOGIN_ERROR);
		}
	}
    /**
     * 提交建议
     */
    public void submit() {
    	
    	User user =CurrentUserUtil.getCurrentUser(userService, request);
    	if (user != null) {
            String ajax = AjaxDataToStringUtil.getAjaxString(request);
            Suggestion suggestion = (Suggestion)JsonUtil.stringToObject(ajax, Suggestion.class);
            if (suggestion != null) {
                suggestion.setDate(new Date());
                suggestion.setFromName(user.getNickname());
                suggestion.setIsRead(UNREAD);
                suggestionService.saveOrUpdate(suggestion);
                ReplyUtil.replyOK(response, GlobalMessage.SUCCESS_INFO);
			} else {
				ReplyUtil.replyOK(response, GlobalMessage.DATE_ERROR);
			}
		}  else {
			ReplyUtil.replyNO(response, GlobalMessage.LOGIN_ERROR);
		}
	}
    /**
     * 查看未读建议
     */
    public void unread() {
    	Administrator admin = CurrentUserUtil.getCurrentAdmin(adminService, request);
    	if (admin != null) {
    		Integer start = null;
    		try {
				start = Integer.valueOf(request.getParameter(GlobalMessage.PAGE_START));
			} catch (Exception e) {
				// TODO: handle exception
			}
    		ReplyUtil.reply(response, JsonUtil.listToString(suggestionService.unreadList(start)));
		} else {
			ReplyUtil.replyNO(response, GlobalMessage.LOGIN_ERROR);
		}
	}
    /**
     * 查看全部建议
     */
    public void all() {
    	Administrator admin = CurrentUserUtil.getCurrentAdmin(adminService, request);
    	if (admin != null) {
    		Integer start = null;
    		try {
				start = Integer.valueOf(request.getParameter(GlobalMessage.PAGE_START));
			} catch (Exception e) {
				// TODO: handle exception
			}
    		ReplyUtil.reply(response, JsonUtil.listToString(suggestionService.allList(start)));
		} else {
			ReplyUtil.replyNO(response, GlobalMessage.LOGIN_ERROR);
		}
	}
	public ISuggestionService getSuggestionService() {
		return suggestionService;
	}
	public void setSuggestionService(ISuggestionService suggestionService) {
		this.suggestionService = suggestionService;
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
