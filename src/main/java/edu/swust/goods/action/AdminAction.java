package edu.swust.goods.action;


import java.util.Date;

import edu.swust.goods.domain.Administrator;
import edu.swust.goods.domain.Goods;
import edu.swust.goods.domain.VerifyLog;
import edu.swust.goods.service.IAdminService;
import edu.swust.goods.service.IGoodsService;
import edu.swust.goods.service.ISuggestionService;
import edu.swust.goods.service.IUserService;
import edu.swust.goods.service.IVerifyLogService;
import edu.swust.goods.tempbean.LoginInfoBean;
import edu.swust.goods.utils.AjaxDataToStringUtil;
import edu.swust.goods.utils.CurrentUserUtil;
import edu.swust.goods.utils.GlobalMessage;
import edu.swust.goods.utils.JsonUtil;
import edu.swust.goods.utils.ReplyUtil;
/**
 * 管理员相关逻辑处理类
 * @author hanpeng
 *
 */
public class AdminAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	private static final String PERMIT_NOT_ENOUGH = "权限不足";
	private static final String PASS = "通过";
	private static final String REFUSE = "打回";
	private static final String REASON = "审核未通过";
	private static final int PRIVILEGE_CODE = 1;//超级管理员权限
	private static final int ORDINARY_CODE = 1;//普通权限
	private static final int PASS_STATUS = 2;
	private IAdminService adminService;
	private IGoodsService goodsService;
	private IVerifyLogService verifyLogService;
	private IUserService userService;
	private ISuggestionService suggestionService;
	
	private Long id;
	private String nickname;
	/**
	 * 添加普通管理员
	 */
	public void add() {
		String ajax = AjaxDataToStringUtil.getAjaxString(request);
        LoginInfoBean bean = (LoginInfoBean) JsonUtil.stringToObject(ajax, LoginInfoBean.class);
        Administrator administrator = CurrentUserUtil.getCurrentAdmin(adminService, request);
        if (administrator == null || administrator.getPermit() != PRIVILEGE_CODE) {
            ReplyUtil.replyNO(response, GlobalMessage.PERMIT_ERROR);
		} else if (bean != null) {
			if (adminService.accountExist(bean.getAccount()) != null) {
				ReplyUtil.replyNO(response, GlobalMessage.EXIST_ACCOUNT);
			} else {
				Administrator admin = new Administrator();
				admin.setAccount(bean.getAccount());
				admin.setNickname(bean.getAccount());
				admin.setPermit(ORDINARY_CODE);
				admin.setPassword(bean.getPassword());
				adminService.saveOrUpdate(admin);
				ReplyUtil.replyOK(response, GlobalMessage.SUCCESS_INFO);
			}

		} else {
			ReplyUtil.replyNO(response, GlobalMessage.DATE_ERROR);
		}
	}
    /**
     * 审核商品通过
     */
    public void pass() {
		Administrator admin = CurrentUserUtil.getCurrentAdmin(adminService, request);
		
		if (admin != null) {
			System.out.println(admin.getId());
			try {
				System.out.println(request.getParameter(GlobalMessage.ID));
				Long id = Long.valueOf(request.getParameter(GlobalMessage.ID));
				System.out.println(id);
				Goods goods = goodsService.get(id);
				if (goods != null && goods.getStatus() == GlobalMessage.DEFAULT_STATUS) {
					goods.setStatus(PASS_STATUS);
					VerifyLog log = new VerifyLog();
					log.setAdministrator(admin);
					log.setDate(new Date());
					log.setGoodsName(goods.getName());
					log.setResult(PASS);
					verifyLogService.saveOrUpdate(log);
					goodsService.saveOrUpdate(goods);
					ReplyUtil.replyOK(response, GlobalMessage.SUCCESS_INFO);
				} else {
					System.out.println(GlobalMessage.ERROR_INFO);
					ReplyUtil.replyNO(response, GlobalMessage.ERROR_INFO);
				}
			} catch (Exception e) {
                e.printStackTrace();
				ReplyUtil.replyNO(response, GlobalMessage.ERROR_INFO);
			}
		} else {
			System.out.println(GlobalMessage.LOGIN_ERROR);
			ReplyUtil.replyNO(response, GlobalMessage.LOGIN_ERROR);
		}
	}
    /**
     * 审核商品未通过
     */
    public void refuse() {
    	Administrator admin = CurrentUserUtil.getCurrentAdmin(adminService, request);
		
		if (admin != null) {
			try {
				System.out.println(request.getParameter(GlobalMessage.ID));
			    Long id = Long.valueOf(request.getParameter(GlobalMessage.ID));
			    System.out.println(id);
			    Goods goods = goodsService.get(id);
                if (goods != null && goods.getStatus() == GlobalMessage.DEFAULT_STATUS) {
                    VerifyLog log = new VerifyLog();
                    log.setAdministrator(admin);
                    log.setDate(new Date());
                    log.setGoodsName(goods.getName());
                    log.setResult(REFUSE);
					verifyLogService.saveOrUpdate(log);
					goodsService.delete(id, REASON, goods.getOwner());
					ReplyUtil.replyOK(response, GlobalMessage.SUCCESS_INFO);
				} else {
					System.out.println(GlobalMessage.ERROR_INFO);
					ReplyUtil.replyNO(response, GlobalMessage.ERROR_INFO);
				}
			} catch (Exception e) {
				ReplyUtil.replyNO(response, GlobalMessage.ERROR_INFO);
				e.printStackTrace();
			}
		} else {
			ReplyUtil.replyNO(response, GlobalMessage.LOGIN_ERROR);
			System.out.println(GlobalMessage.LOGIN_ERROR);
		}
	}
    /**
     * 超级管理员获得所有的管理员
     */
    public void getAllAdmin() {
    	Administrator admin = CurrentUserUtil.getCurrentAdmin(adminService, request);
    	if (admin != null) {
    		if (admin.getPermit() == PRIVILEGE_CODE) {
    			Integer start = null;
    			try {
					start = Integer.valueOf(request.getParameter(GlobalMessage.PAGE_START));
				} catch (Exception e) {
				}
                ReplyUtil.reply(response, JsonUtil.objectToJson(adminService.getAllAdmin(start)));	
			} else {
				ReplyUtil.replyNO(response, PERMIT_NOT_ENOUGH);
			}
    		
    	} else {
			ReplyUtil.replyNO(response, GlobalMessage.LOGIN_ERROR);
		}
        
	}
    /**
     * 删除管理人员
     */
    public void delete() {
    	Administrator admin = CurrentUserUtil.getCurrentAdmin(adminService, request);
    	if (admin != null) {
    		if (admin.getPermit() == PRIVILEGE_CODE) {
    			try {
    				Long id = Long.valueOf(request.getParameter(GlobalMessage.ID));
    				adminService.delete(id);
    				ReplyUtil.replyOK(response, GlobalMessage.SUCCESS_INFO);
				} catch (Exception e) {
					ReplyUtil.replyNO(response, GlobalMessage.DATE_ERROR);
				}
			} else {
				ReplyUtil.replyNO(response, PERMIT_NOT_ENOUGH);
			}
    	} else {
			ReplyUtil.replyNO(response, GlobalMessage.LOGIN_ERROR);
		}
	}
    /**
     * 超级管理员查看所有的商品审核记录
     */
    public void getAllVerifyLog() {
    	Administrator admin = CurrentUserUtil.getCurrentAdmin(adminService, request);
    	if (admin != null) {
    		if (admin.getPermit() == PRIVILEGE_CODE) {
    			Integer start = null;
    			try {
					start = Integer.valueOf(request.getParameter(GlobalMessage.PAGE_START));
				} catch (Exception e) {
				}
    			ReplyUtil.reply(response, JsonUtil.listToString(verifyLogService.allVerifyLogs(start)));
			} else {
				ReplyUtil.replyNO(response, PERMIT_NOT_ENOUGH);
			}
    	} else {
			ReplyUtil.replyNO(response, GlobalMessage.LOGIN_ERROR);
		}
	}
    /**
     * 查看个人（当前登录用户）商品审核记录
     */
    public void verifyLog() {
    	Administrator admin = CurrentUserUtil.getCurrentAdmin(adminService, request);
    	if (admin != null) {
			ReplyUtil.reply(response, JsonUtil.listToString(adminService.myVerifyLog(admin)));
    	} else {
			ReplyUtil.replyNO(response, GlobalMessage.LOGIN_ERROR);
		}
	}

    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public IAdminService getAdminService() {
		return adminService;
	}

	public void setAdminService(IAdminService adminService) {
		this.adminService = adminService;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public IGoodsService getGoodsService() {
		return goodsService;
	}

	public void setGoodsService(IGoodsService goodsService) {
		this.goodsService = goodsService;
	}

	public IVerifyLogService getVerifyLogService() {
		return verifyLogService;
	}

	public void setVerifyLogService(IVerifyLogService verifyLogService) {
		this.verifyLogService = verifyLogService;
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public ISuggestionService getSuggestionService() {
		return suggestionService;
	}

	public void setSuggestionService(ISuggestionService suggestionService) {
		this.suggestionService = suggestionService;
	}
}
