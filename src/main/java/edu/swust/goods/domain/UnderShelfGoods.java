package edu.swust.goods.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class UnderShelfGoods extends BaseGood implements Comparable<UnderShelfGoods>{
    private Date underShelfTime;
    private String reason;
    private String imageUrl;
    private Set<User> collecters = new HashSet<User>();
    public UnderShelfGoods() {
	}
    
    public UnderShelfGoods(Goods goods, String reason, String imageUrl) {
        this.id = goods.id;
        this.name = goods.name;
        this.price = goods.price;
        this.uploadTime = goods.uploadTime;
        this.owner = goods.owner;
        this.classification = goods.classification;
		this.underShelfTime = new Date();
		this.reason = reason;
		this.imageUrl = imageUrl;
	}



	public Date getUnderShelfTime() {
		return underShelfTime;
	}
	public void setUnderShelfTime(Date underShelfTime) {
		this.underShelfTime = underShelfTime;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public Set<User> getCollecters() {
		return collecters;
	}
	public void setCollecters(Set<User> collecters) {
		this.collecters = collecters;
	}

	public int compareTo(UnderShelfGoods o) {
		return uploadTime.compareTo(o.uploadTime);
	}
    
}
