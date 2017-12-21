package edu.swust.goods.view;

import edu.swust.goods.domain.BaseUser;
/**
 * 用户基类
 * @author hanpeng
 *
 */
public abstract class BaseUserView {
    protected Long id;         ///< 变化
    protected String nickname; ///< 昵称
    
    public BaseUserView() {
	}

    public BaseUserView(BaseUser user) {
		this.id = user.getId();
		this.nickname = user.getNickname();
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
}
