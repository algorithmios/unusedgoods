package edu.swust.goods.action;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Random;

import edu.swust.goods.domain.Goods;
import edu.swust.goods.domain.ReqCode;
import edu.swust.goods.domain.User;
import edu.swust.goods.service.IGoodsService;
import edu.swust.goods.service.IReqCodeService;
import edu.swust.goods.service.IUserService;
import edu.swust.goods.tempbean.DialogBean;
import edu.swust.goods.tempbean.LoginInfoBean;
import edu.swust.goods.utils.CurrentUserUtil;
import edu.swust.goods.utils.AjaxDataToStringUtil;
import edu.swust.goods.utils.GlobalMessage;
import edu.swust.goods.utils.ImageUtil;
import edu.swust.goods.utils.JsonUtil;
import edu.swust.goods.utils.MailUtil;
import edu.swust.goods.utils.ReplyUtil;
import edu.swust.goods.view.ContactView;
import edu.swust.goods.view.UserDetailView;
/**
 * 普通用户相关逻辑处理类
 * @author hanpeng
 *
 */
public class UserAction extends BaseAction{
	
	private static final long serialVersionUID = 1L;

	private String nickname;
	private String qq;
	private String weixin;
	private String phone;
	private File headImg;
    private String headImgFileName;  
    private String headImgContentType;
	private IUserService userService;
	private IGoodsService goodsService;
	private IReqCodeService reqCodeService;
	
	private static final int BASE_REQ_CODE = 100000;
	private static final int REQ_CODE_BOUND = 900000;
	private static final String WORRY_ACCOUNT = "账号错误！";
	private static final String WORRY_ACCOUNT_OR_REQCODE = "请输入正确的账号或者验证码";
	private static final String WORRY_REQCODE = "请输入正确的验证码";
	private static final Integer VERIFIED = 1;
	private static final String SEAD_EMAIL_WORRY = "邮件发送错误！";
	private static final String SUCCESS_COLLECTION = "收藏成功";
	/**
	 * 添加
	 */
	public void add() {
        LoginInfoBean bean = getLoginBean();
        if (bean != null) {
        	String account = bean.getAccount();
        	ReqCode code = reqCodeService.getAccount(account, true);
        	if (code != null) {
        		User user = userService.accountExist(account);
        		if (user == null) {
        			user = new User();
                    user.setAccount(bean.getAccount());
				}
        		user.setPassword(bean.getPassword());
                user.setHeadImageUrl(GlobalMessage.DEFAULT_HEAD_IMAGE_URL);
                
			
                userService.saveOrUpdate(user);
                ReplyUtil.replyOK(response, GlobalMessage.SUCCESS_INFO);
			} else {
                ReplyUtil.replyNO(response, WORRY_ACCOUNT_OR_REQCODE);
			}
        	
		} else {
			ReplyUtil.replyNO(response, GlobalMessage.DATE_ERROR);
		}
	}
	/**
	 * 找回密码
	 */
	public void retrievePassword() {
		LoginInfoBean bean = getLoginBean();
		if (bean != null) {
			if (bean.getAccount() != null && userService.accountExist(bean.getAccount()) != null) {
				sendEmail(bean.getAccount());
		    } else {
		    	ReplyUtil.replyNO(response, WORRY_ACCOUNT);
            }
		} else {
            ReplyUtil.replyNO(response, GlobalMessage.DATE_ERROR);
		}
	}
	
	/**
	 * 注册
	 */
	public void registered() {
		LoginInfoBean bean = getLoginBean();
		if (bean != null) {
			if (bean.getAccount() != null ) {
				if (userService.accountExist(bean.getAccount()) == null) {
					sendEmail(bean.getAccount());
				} else {
                    ReplyUtil.replyOK(response, GlobalMessage.EXIST_ACCOUNT);
				}
		    } else {
		    	ReplyUtil.replyNO(response, WORRY_ACCOUNT);
            }
		} else {
			ReplyUtil.replyNO(response, GlobalMessage.DATE_ERROR);
		}
	}
	/**
	 * 发送邮件
	 */
	private void sendEmail(String account) {
		String reqCode = String.valueOf((new Random().nextInt(REQ_CODE_BOUND) + BASE_REQ_CODE));
	    boolean successful = MailUtil.sendEmail(account, reqCode);
	    if (successful) {
	    	ReqCode code = reqCodeService.getAccount(account, true);
	    	if (code == null) {
	    		code = reqCodeService.getAccount(account, false);
	    		if (code == null) {
		    		code = new ReqCode(account);	
				}
			}
	    	code.setReqCode(reqCode);
	    	reqCodeService.saveOrUpdate(code);
			ReplyUtil.replyOK(response, account);
		} else {
			ReplyUtil.replyNO(response, SEAD_EMAIL_WORRY);
		}
	}
	
	private LoginInfoBean getLoginBean() {
		String ajax = AjaxDataToStringUtil.getAjaxString(request);
        return (LoginInfoBean) JsonUtil.stringToObject(ajax, LoginInfoBean.class);
	}
	
	/**
	 * 邮箱验证
	 */
	public void verification() {
		LoginInfoBean bean = getLoginBean();
		if (bean != null) {
			String account = bean.getAccount();
			String reqCode = bean.getReqcode();
			if (account != null ) {
				ReqCode code = reqCodeService.getAccount(account, false);
				if (code != null) {
					if (reqCode != null && reqCode.equals(code.getReqCode())) {
						code.setDate(new Date().getTime());
						code.setVerification(VERIFIED);
						reqCodeService.saveOrUpdate(code);
						ReplyUtil.replyOK(response, account);	
					} else {
						ReplyUtil.replyNO(response, WORRY_REQCODE);
					}
					
				} else {
					ReplyUtil.replyNO(response, WORRY_ACCOUNT);
				}
				
		    } else {
		    	ReplyUtil.replyNO(response, WORRY_ACCOUNT);
            }
		} else {
			ReplyUtil.replyNO(response, GlobalMessage.DATE_ERROR);
		}
	}
	/**
	 * 用户详情
	 */
	public void userDetail() {
		User user = CurrentUserUtil.getCurrentUser(userService, request);
        if (user != null) {
            ReplyUtil.reply(response, JsonUtil.objectToJson(new UserDetailView(user)));
		} else {
            ReplyUtil.replyNO(response, GlobalMessage.LOGIN_ERROR);
		}
	}
	/**
	 * 更新用户信息
	 */
	public void update() {
        User user = CurrentUserUtil.getCurrentUser(userService, request);
        if (user != null) {
            user.setWeixin(weixin);
            user.setQq(qq);
            user.setPhone(phone);
            user.setNickname(nickname);
            String newHeadUrl = null;
            
            if (headImg != null && headImg.length() > 0) {
            	newHeadUrl = ImageUtil.saveImage(headImg, headImgFileName, 0);
            }
            if (newHeadUrl != null) {
            	if (!GlobalMessage.DEFAULT_HEAD_IMAGE_URL.equals(user.getHeadImageUrl())) {
            		ImageUtil.deleteImage(user.getHeadImageUrl());
				}
				user.setHeadImageUrl(newHeadUrl);
			}
            userService.saveOrUpdate(user);
            ReplyUtil.replyOK(response, GlobalMessage.SUCCESS_INFO);
		} else {
            ReplyUtil.replyNO(response, GlobalMessage.LOGIN_ERROR);
		}
	}
	/**
	 * 当前用户联系方式
	 */
	public void currentUserContact() {
		User user = CurrentUserUtil.getCurrentUser(userService, request);
		if (user != null) {
			List<ContactView> views = userService.contactsView(user);
			ReplyUtil.reply(response, JsonUtil.listToString(views));
		} else {
            ReplyUtil.replyNO(response, GlobalMessage.LOGIN_ERROR);
		}
	}
    /**
     * 收藏商品
     */
    public void collection() {
    	User user = CurrentUserUtil.getCurrentUser(userService, request);
		if (user != null) {
            Goods goods = null;
            try {
                Long goodsId = Long.valueOf(request.getParameter(GlobalMessage.ID));
                goods = goodsService.get(goodsId);
			} catch (Exception e) {
			}
            if (goods != null) {
			    userService.addCollection(user, goods);
			    ReplyUtil.replyOK(response, SUCCESS_COLLECTION);
            } else {
            	ReplyUtil.replyNO(response, GlobalMessage.GOODS_MISS);
			}
		} else {
			ReplyUtil.replyNO(response, GlobalMessage.LOGIN_ERROR);
		}
	}
    /**
     * 添加对话
     */
    public void addDialog() {
		User user = CurrentUserUtil.getCurrentUser(userService, request);
		if (user != null) {
			String ajax = AjaxDataToStringUtil.getAjaxString(request);
			DialogBean bean = (DialogBean) JsonUtil.stringToObject(ajax, DialogBean.class);
			userService.addDialog(user, bean);
			ReplyUtil.replyOK(response, GlobalMessage.SUCCESS_INFO);
		} else {
			ReplyUtil.replyNO(response, GlobalMessage.LOGIN_ERROR);
		}
	}
    /**
     * 对话条数
     */
    public void dialogCount() {
    	User user = CurrentUserUtil.getCurrentUser(userService, request);
		if (user != null) {
			int count = userService.dialogCount(user);
			ReplyUtil.replyOK(response, String.valueOf(count));
		} else {
			ReplyUtil.replyNO(response, GlobalMessage.LOGIN_ERROR);
		}
	}
    /**
     * 对话列表
     */
    public void listDialog() {
    	User user = CurrentUserUtil.getCurrentUser(userService, request);
		if (user != null) {
			Integer start = null;
			try {
				start = Integer.valueOf(request.getParameter(GlobalMessage.PAGE_START));
			} catch (Exception e) {
				// TODO: handle exception
			}
			ReplyUtil.reply(response, JsonUtil.objectToJson(userService.listDialog(user, start)));
		} else {
			ReplyUtil.replyNO(response, GlobalMessage.LOGIN_ERROR);
		}
	}
    
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getWeixin() {
		return weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public File getHeadImg() {
		return headImg;
	}

	public void setHeadImg(File headImg) {
		this.headImg = headImg;
	}

	public String getHeadImgFileName() {
		return headImgFileName;
	}

	public void setHeadImgFileName(String headImgFileName) {
		this.headImgFileName = headImgFileName;
	}

	public String getHeadImgContentType() {
		return headImgContentType;
	}

	public void setHeadImgContentType(String headImgContentType) {
		this.headImgContentType = headImgContentType;
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public IGoodsService getGoodsService() {
		return goodsService;
	}

	public void setGoodsService(IGoodsService goodsService) {
		this.goodsService = goodsService;
	}
	public IReqCodeService getReqCodeService() {
		return reqCodeService;
	}
	public void setReqCodeService(IReqCodeService reqCodeService) {
		this.reqCodeService = reqCodeService;
	}

}
