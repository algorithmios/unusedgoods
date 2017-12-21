package edu.swust.goods.action;

import java.io.File;
import java.util.Date;
import java.util.List;

import edu.swust.goods.domain.Administrator;
import edu.swust.goods.domain.Goods;
import edu.swust.goods.domain.GoodsImage;
import edu.swust.goods.domain.User;
import edu.swust.goods.service.IAdminService;
import edu.swust.goods.service.IGoodsService;
import edu.swust.goods.service.IUnderShelfGoodsService;
import edu.swust.goods.service.IUserService;
import edu.swust.goods.tempbean.QueryBean;
import edu.swust.goods.utils.CurrentUserUtil;
import edu.swust.goods.utils.AjaxDataToStringUtil;
import edu.swust.goods.utils.GlobalMessage;
import edu.swust.goods.utils.ImageUtil;
import edu.swust.goods.utils.JsonUtil;
import edu.swust.goods.utils.ReplyUtil;
import edu.swust.goods.view.ContactView;
import edu.swust.goods.view.GoodsView;
import edu.swust.goods.view.SimpleGoodsView;
/**
 * 商品相关逻辑处理类
 * @author hanpeng
 *
 */
public class GoodsAction extends BaseAction {

	private static final long serialVersionUID = 1L;

    private IGoodsService goodsService;
    private IUserService userService;
    private IAdminService adminService;
    private IUnderShelfGoodsService underShelfGoodsService;
    private Goods goods; 
    private File[] images;
    private String[] imagesFileName;  
    private String[] imagesContentType;  
    private static final String SUCCESS_INFO = "添加成功";
    private static final String NULL_INFO = "没有内容";
    private static final String CONTENT_ERROR = "图片数量不够";
    private final String REASONS = "卖家下架了该商品";
    private static final long DEFAULT_PAGEVIEWS = 0L;
    /**
     * 添加商品
     */
	public void addGoods() {
		User user = CurrentUserUtil.getCurrentUser(userService, request);
		
		if (user != null) {
			if (goods != null) {
				if (images != null) {
					for (int i = 0; i < images.length; i++) {
						String url = ImageUtil.saveImage(images[i], imagesFileName[i], 1);
						if (url != null) {
							GoodsImage image = new GoodsImage(url);
							image.setGoods(goods);
							goods.getImages().add(image);
						}
					}
				}
				if (goods.getImages().size() > 0) {
					goods.setOwner(user);
					goods.setStatus(GlobalMessage.DEFAULT_STATUS);
					goods.setPageViews(DEFAULT_PAGEVIEWS);
					goods.setUploadTime(new Date());
		            goodsService.saveOrUpdate(goods);
		            ReplyUtil.replyOK(response, SUCCESS_INFO);
				} else {
					ReplyUtil.replyNO(response, CONTENT_ERROR);
				}
			} else {
				ReplyUtil.replyNO(response, NULL_INFO);
			}
			
		} else {
			ReplyUtil.replyNO(response, GlobalMessage.LOGIN_ERROR);
		}
	}
    
    public void underShelfGoods() {
    	User user = CurrentUserUtil.getCurrentUser(userService, request);
    	if (user != null) {
    		try {
                Long id = Long.valueOf(request.getParameter(GlobalMessage.ID));
               
                if (goodsService.delete(id, REASONS, user)) {
					ReplyUtil.replyOK(response, GlobalMessage.SUCCESS_INFO);
				} else {
					ReplyUtil.replyNO(response, GlobalMessage.ERROR_INFO);
				}
			} catch (Exception e) {
				ReplyUtil.replyNO(response, GlobalMessage.ERROR_INFO);
			}
		} else {
			ReplyUtil.replyNO(response, GlobalMessage.LOGIN_ERROR);
		}
	}
    
    /**
     * 删除下架商品
     */
    public void deleteUnderShelfGoods() {
    	User user = CurrentUserUtil.getCurrentUser(userService, request);
    	if (user != null) {
    		try {
                Long id = Long.valueOf(request.getParameter(GlobalMessage.ID));
                if (underShelfGoodsService.delete(id, user)) {
                	ReplyUtil.replyOK(response, GlobalMessage.SUCCESS_INFO);
				} else {
					ReplyUtil.replyNO(response, GlobalMessage.ERROR_INFO);
				}
			} catch (Exception e) {
				ReplyUtil.replyNO(response, GlobalMessage.ERROR_INFO);
			}
		} else {
			ReplyUtil.replyNO(response, GlobalMessage.LOGIN_ERROR);
		}
	}
    
    
    /**
     * 我上架的商品
     */
    public void ownPostList() {
        User user = CurrentUserUtil.getCurrentUser(userService, request);
        if (user != null) {
        	Integer start = null;
        	try {
				start = Integer.valueOf(request.getParameter(GlobalMessage.PAGE_START));
			} catch (Exception e) {
			}
            ReplyUtil.reply(response, JsonUtil.objectToJson(goodsService.ownGoodsList(user, start)));
		} else {
			ReplyUtil.replyNO(response, GlobalMessage.LOGIN_ERROR);
		}
        
	}
    
    /**
     * 取消收藏
     */
    public void cancleCollection() {
    	User user = CurrentUserUtil.getCurrentUser(userService, request);
    	if (user != null) {
    		try {
                Long id = Long.valueOf(request.getParameter(GlobalMessage.ID));
                userService.cancleCollection(user, id);
                ReplyUtil.replyOK(response, GlobalMessage.SUCCESS_INFO);
              
			} catch (Exception e) {
				ReplyUtil.replyNO(response, GlobalMessage.ERROR_INFO);
			}
		} else {
			ReplyUtil.replyNO(response, GlobalMessage.LOGIN_ERROR);
		}
	}
    
    
    /**
     * 下架商品列表
     */
    public void ownUnderShelfList() {
    	User user = CurrentUserUtil.getCurrentUser(userService, request);
        if (user != null) {
        	Integer start = null;
        	try {
				start = Integer.valueOf(request.getParameter(GlobalMessage.PAGE_START));
			} catch (Exception e) {
				// TODO: handle exception
			}
            ReplyUtil.reply(response, JsonUtil.objectToJson(goodsService.ownUnderShelf(user, start)));
		} else {
			ReplyUtil.replyNO(response, GlobalMessage.LOGIN_ERROR);
		}
	}

    public void detail() {
    	User user = CurrentUserUtil.getCurrentUser(userService, request);
    	
        Long id = null;
        try {
			id = Long.valueOf(request.getParameter(GlobalMessage.ID));
		} catch (Exception e) {
		}
        
        GoodsView goodsView = goodsService.goodsDetail(id, user);
   
        if (goodsView != null && goodsView.getStatus() == 0) {
        	Administrator admin = CurrentUserUtil.getCurrentAdmin(adminService, request);
			if (!(user != null && user.getId() == goodsView.getOwnerId()) && admin == null) {
				goodsView = null;
			}
		}
        String string = JsonUtil.objectToJson(goodsView);
        System.out.println(string);
        ReplyUtil.reply(response, string);
	}
    
    private QueryBean searchQuery() {
    	String ajax = AjaxDataToStringUtil.getAjaxString(request);
    	System.out.println("ajax:" + ajax);
		return (QueryBean) JsonUtil.stringToObject(ajax, QueryBean.class);
	}
    
    public void search() {
		QueryBean query = searchQuery();
		ReplyUtil.reply(response, JsonUtil.objectToJson(goodsService.bySynthesis(query, null)));
	}
    /**
     * 推荐
     */
    public void recommend() {
    	Long id = null;
    	try {
			id = Long.valueOf(request.getParameter(GlobalMessage.ID));
		} catch (Exception e) {
		}
		List<SimpleGoodsView> views = goodsService.recommend(id);
		ReplyUtil.reply(response, JsonUtil.listToString(views));
	}
    
	public void byPrice() {
       QueryBean query = searchQuery();
       System.out.println(query);
       int flag = 0;
       try {
    	    flag = Integer.valueOf(request.getParameter(GlobalMessage.PRICE_FLAG));
	   } catch (Exception e) {
	   }
       ReplyUtil.reply(response, JsonUtil.objectToJson(goodsService.byPrice(query, flag, null)));
	}
		
	public void byCollection() {
		QueryBean query = searchQuery();
		System.out.println(query);
        ReplyUtil.reply(response, JsonUtil.objectToJson(goodsService.byCollection(query, null)));
	}
	
	public void byDate() {
		QueryBean query = searchQuery();
		System.out.println(query);
        ReplyUtil.reply(response, JsonUtil.objectToJson(goodsService.byDate(query, null)));
	}
	/**
	 * 待审核商品
	 */
	public void noPass() {
		Administrator admin = CurrentUserUtil.getCurrentAdmin(adminService, request);
		if (admin != null) {
			Integer start = null;
			try {
				start = Integer.valueOf(request.getParameter(GlobalMessage.PAGE_START));
			} catch (Exception e) {
			}
	        ReplyUtil.reply(response, JsonUtil.objectToJson(goodsService.pendingReview(start)));
		} else {
			ReplyUtil.replyNO(response, GlobalMessage.LOGIN_ERROR);
		}
		
	}
	/**
	 * 根据商品获得联系方式
	 */
    public void contact() {
		List<ContactView> views = null;
		try {
			Long id = Long.valueOf(request.getParameter(GlobalMessage.ID));
			if (id != null) {
				views = goodsService.contact(id);
			}
		} catch (Exception e) {
		}
		
		ReplyUtil.reply(response, JsonUtil.listToString(views));
	}
    /**
     * 我的收藏： 在热卖的/已下架的 8
     */
    public void ownCollect() {
    	User user = CurrentUserUtil.getCurrentUser(userService, request);
        if (user != null) {
        	Integer start = null;
        	try {
				start = Integer.valueOf(request.getParameter(GlobalMessage.PAGE_START));
			} catch (Exception e) {
				// TODO: handle exception
			}
            ReplyUtil.reply(response, JsonUtil.objectToJson(goodsService.ownCollect(user, start)));
		} else {
			ReplyUtil.replyNO(response, GlobalMessage.LOGIN_ERROR);
		}
	}
    /**
     * 当前用户收藏的已经下架的商品
     */
    public void ownCollectUnderShelf() {
    	User user = CurrentUserUtil.getCurrentUser(userService, request);
        if (user != null) {
        	Integer start = null;
        	try {
				start = Integer.valueOf(request.getParameter(GlobalMessage.PAGE_START));
			} catch (Exception e) {
				// TODO: handle exception
			}
            ReplyUtil.reply(response, JsonUtil.objectToJson(goodsService.ownCollectUnderShelf(user, start)));
		} else {
			ReplyUtil.replyNO(response, GlobalMessage.LOGIN_ERROR);
		}
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

	public File[] getImages() {
		return images;
	}

	public void setImages(File[] images) {
		this.images = images;
	}

	public String[] getImagesFileName() {
		return imagesFileName;
	}

	public void setImagesFileName(String[] imagesFileName) {
		this.imagesFileName = imagesFileName;
	}

	public String[] getImagesContentType() {
		return imagesContentType;
	}

	public void setImagesContentType(String[] imagesContentType) {
		this.imagesContentType = imagesContentType;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public IAdminService getAdminService() {
		return adminService;
	}

	public void setAdminService(IAdminService adminService) {
		this.adminService = adminService;
	}

	public IUnderShelfGoodsService getUnderShelfGoodsService() {
		return underShelfGoodsService;
	}

	public void setUnderShelfGoodsService(IUnderShelfGoodsService underShelfGoodsService) {
		this.underShelfGoodsService = underShelfGoodsService;
	}
	
}
