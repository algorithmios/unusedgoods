package edu.swust.goods.view;

import edu.swust.goods.domain.Administrator;
/**
 * 管理员的映射类
 * @author hanpeng
 *
 */
public class AdminView extends BaseUserView {
	private Integer permit;       ///<权限
    public AdminView() {
	}
    
    public AdminView(Administrator admin) {
    	this.id = admin.getId();
    	this.nickname = admin.getNickname();
    	this.permit = admin.getPermit();
	}
    
	public AdminView(Long id, String nickname, Integer permit) {
		this.id = id;
		this.nickname = nickname;
		this.permit = permit;
	}
	public Integer getPermit() {
		return permit;
	}
	public void setPermit(Integer permit) {
		this.permit = permit;
	}

	@Override
	public String toString() {
		return "AdminView [permit=" + permit + ", id=" + id + ", nickname=" + nickname + "]";
	}
}
