package edu.swust.goods.action;

import edu.swust.goods.service.IAdminService;
import edu.swust.goods.service.IGoodsImageService;
import edu.swust.goods.service.IGoodsService;
import edu.swust.goods.service.IUserService;
import edu.swust.goods.utils.JsonUtil;
import edu.swust.goods.utils.ReplyUtil;
/**
 * 主页相关逻辑处理类
 * @author hanpeng
 *
 */
public class HomeAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	private IGoodsService goodsService;
    private IUserService userService;
    private IAdminService adminService;
    private IGoodsImageService goodsImageService;
    
    /**
     * 主页信息
     * 综合推荐、发售时间、最实惠
	 * 收藏量、浏览量、其他
	 * 图片 三处
     */
    public void pictures() {
    	ReplyUtil.reply(response, JsonUtil.listToString(goodsImageService.homeImageUrl()));
	}
    
    /**
     * 主页推荐商品
     */
    public void recommend() {
    	ReplyUtil.reply(response, JsonUtil.listToString(goodsService.homeRecommend()));
	}
    
	public IGoodsService getGoodsService() {
		return goodsService;
	}
	public void setGoodsService(IGoodsService goodsService) {
		this.goodsService = goodsService;
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
	public IGoodsImageService getGoodsImageService() {
		return goodsImageService;
	}
	public void setGoodsImageService(IGoodsImageService goodsImageService) {
		this.goodsImageService = goodsImageService;
	}
	
}
