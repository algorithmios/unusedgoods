package edu.swust.goods.domain;

import java.util.HashSet;
import java.util.Set;

public class User extends BaseUser {
    private String headImageUrl;
    private String weixin;
    private String phone;
    private String qq;
	private Set<Goods> goods = new HashSet<Goods>();
	private Set<Dialog> roleA = new HashSet<Dialog>();
	private Set<Dialog> roleB = new HashSet<Dialog>();
	private Set<UnderShelfGoods> underShelfGoods = new HashSet<UnderShelfGoods>();
	private Set<UnderShelfGoods> collectedUnderShelfGoods = new HashSet<UnderShelfGoods>();
	private Set<Goods> collecteds = new HashSet<Goods>();
	public String getHeadImageUrl() {
		return headImageUrl;
	}
	public void setHeadImageUrl(String headImageUrl) {
		this.headImageUrl = headImageUrl;
	}
	public Set<Goods> getGoods() {
		return goods;
	}
	public void setGoods(Set<Goods> goods) {
		this.goods = goods;
	}
	public Set<UnderShelfGoods> getSaledGoods() {
		return underShelfGoods;
	}
	public void setSaledGoods(Set<UnderShelfGoods> underShelfGoods) {
		this.underShelfGoods = underShelfGoods;
	}
	public Set<Goods> getCollecteds() {
		return collecteds;
	}
	public void setCollecteds(Set<Goods> collecteds) {
		this.collecteds = collecteds;
	}
	public Set<UnderShelfGoods> getUnderShelfGoods() {
		return underShelfGoods;
	}
	public void setUnderShelfGoods(Set<UnderShelfGoods> underShelfGoods) {
		this.underShelfGoods = underShelfGoods;
	}
	public Set<UnderShelfGoods> getCollectedUnderShelfGoods() {
		return collectedUnderShelfGoods;
	}
	public void setCollectedUnderShelfGoods(Set<UnderShelfGoods> collectedUnderShelfGoods) {
		this.collectedUnderShelfGoods = collectedUnderShelfGoods;
	}
	public Set<Dialog> getRoleA() {
		return roleA;
	}
	public void setRoleA(Set<Dialog> roleA) {
		this.roleA = roleA;
	}
	public Set<Dialog> getRoleB() {
		return roleB;
	}
	public void setRoleB(Set<Dialog> roleB) {
		this.roleB = roleB;
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
