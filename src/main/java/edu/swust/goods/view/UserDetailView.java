package edu.swust.goods.view;

import edu.swust.goods.domain.User;
/**
 * 用户详情映射类
 * @author hanpeng
 *
 */
public class UserDetailView extends UserView {
    private String weixin;
    private String phone;
    private String qq;
    
    public UserDetailView() {
	}
    
    public UserDetailView(User user) {
    	super(user);
		qq = user.getQq();
		weixin = user.getWeixin();
		phone = user.getPhone();
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
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
    
    
}
