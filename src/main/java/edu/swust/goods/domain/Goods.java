package edu.swust.goods.domain;

import java.util.HashSet;
import java.util.Set;

public class Goods extends BaseGood implements Comparable<Goods>{
    private String introduction;
    private String address;
    private String onlineOrOffline;
    private Integer status;  ///< 0-->未审批 1-->审批未通过  2-->热卖中  3-->已经下架
    private Long pageViews;
    private Long collectedCount = 0L;
    private Set<GoodsImage> images = new HashSet<GoodsImage>();
    private Set<User> collecters = new HashSet<User>();
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Set<GoodsImage> getImages() {
		return images;
	}
	public void setImages(Set<GoodsImage> images) {
		this.images = images;
	}
	public Set<User> getCollecters() {
		return collecters;
	}
	public void setCollecters(Set<User> collecters) {
		this.collecters = collecters;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Long getPageViews() {
		return pageViews;
	}
	public void setPageViews(Long pageViews) {
		this.pageViews = pageViews;
	}
	public Long getCollectedCount() {
		return collectedCount;
	}
	public void setCollectedCount(Long collectedCount) {
		this.collectedCount = collectedCount;
	}
	public String getOnlineOrOffline() {
		return onlineOrOffline;
	}
	public void setOnlineOrOffline(String onlineOrOffline) {
		this.onlineOrOffline = onlineOrOffline;
	}
	@Override
	public String toString() {
		return "Goods [status=" + status + ", uploadTime=" + uploadTime.getTime() + "]";
	}
	public int compareTo(Goods o) {
		if (!o.status.equals(this.status)) {
			return o.status.compareTo(this.status);
		}
		return o.uploadTime.compareTo(this.uploadTime);
	}
    
}
