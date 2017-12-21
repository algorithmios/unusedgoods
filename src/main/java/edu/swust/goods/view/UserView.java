package edu.swust.goods.view;

import edu.swust.goods.domain.User;
/**
 * 用户信息映射类
 * @author hanpeng
 *
 */
public class UserView extends BaseUserView {
	protected String headImageUrl;

	public UserView() {
	}
    
	public UserView(User user) {
		super(user);
		this.headImageUrl = user.getHeadImageUrl();
	}

	public String getHeadImageUrl() {
		return headImageUrl;
	}

	public void setHeadImageUrl(String headImageUrl) {
		this.headImageUrl = headImageUrl;
	}

	@Override
	public String toString() {
		return "UserView [headImageUrl=" + headImageUrl + ", id=" + id + ", nickname=" + nickname + "]";
	}
	
	public static void main(String[] args) {
		UserView userView = new UserView();
		userView.setHeadImageUrl("99999999999");
		userView.setId(1L);
		BaseUserView baseUserView = userView;
		UserView view = null;
		if (baseUserView instanceof UserView) {
			view = (UserView) baseUserView;
		}
		
		System.out.println(userView);
		System.out.println(baseUserView);
		System.out.println(view);
	}
}
