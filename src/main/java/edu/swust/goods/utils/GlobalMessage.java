package edu.swust.goods.utils;
/**
 * 全局变量
 * @author hanpeng
 *
 */
public class GlobalMessage {
	
	public static final String LONGIN_REQ_CODE = "LONGIN_REQ_CODE";
	public static final String USER = "USER";
	public static final String ADMIN = "ADMIN";
    public static final String CURRENT_USER = "CURRENT_USER";
    public static final String[] GOODS_STATUS = {"等待审核", "热卖中", "已经售出", "卖家主动下架", "未通过审核"};
    public static final String ID = "id";
    public static final Long OK_SIGN = 1L;
    public static final Long NO_SIGN = -1L;
    public static final String DEFAULT_HEAD_IMAGE_URL = "http://localhost:8080/unusedgoods/upload/headImgs/default_headImage.jpg";
    public static final String LOGIN_ERROR = "请先登录";
    public static final String GOODS_MISS = "商品不存在";
    public static final String INDEX = "index";
    public static final String ERROR_INFO = "操作失败";
    public static final String SUCCESS_INFO = "操作成功";
    public static final String DATE_ERROR = "数据传输失败";
    public static final String PERMIT_ERROR = "验证码错误";
    public static final String REQ_CODE_ERROR = "验证码错误";
    public static final String PAGE_START = "start";
    public static final String PRICE_FLAG = "flag";
    public static final String WEIXIN = "微信";
    public static final String QQ = "QQ";
    public static final String PHONE = "电话";
    public static final Integer PAGE_SIZE = 16;
    public static final String EXIST_ACCOUNT = "该账号已经存在，请直接登录";
	public static final int DEFAULT_START = 0;
	public static final int DEFAULT_STATUS = 0;//待审核状态 商品
}
