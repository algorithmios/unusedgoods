package edu.swust.goods.view;


import edu.swust.goods.domain.BaseGood;
import edu.swust.goods.domain.Goods;
import edu.swust.goods.domain.User;
import edu.swust.goods.utils.TimeToStringUtil;
/**
 * 简要商品信息映射类
 * @author hanpeng
 *
 */
public class SimpleGoodsView {
	protected Long id;
	protected String name;
	protected Double price;
	protected String uploadTime;
	protected String ownerName;
	protected Long ownerId;
	protected Double originalPrice;
	protected String imageUrl = "";
	protected String classification;
    private Integer status;
	public SimpleGoodsView() {
	}
	public SimpleGoodsView(Integer start, Integer all) {
		id = start == null? 0 : start.longValue();
		ownerId = all.longValue();
	}
	public SimpleGoodsView(BaseGood goods, String url) {
		init(goods, url);
	}
	public SimpleGoodsView(Goods goods) {
		init(goods, null);
		this.status = goods.getStatus();
	}
	private void init(BaseGood goods, String url) {
		this.id = goods.getId();
		this.name = goods.getName();
		this.price = goods.getPrice();
		this.originalPrice = goods.getOriginalPrice();
		this.uploadTime = TimeToStringUtil.timeToString(goods.getUploadTime());
		User owner = goods.getOwner();
		this.ownerName = owner.getNickname();
		this.ownerId = owner.getId();
		this.classification = goods.getClassification();
		imageUrl = url == null ? imageUrl : url;
		status = 1; 
	}
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public Long getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}
	public String getClassification() {
		return classification;
	}
	public void setClassification(String classification) {
		this.classification = classification;
	}

	public Double getOriginalPrice() {
		return originalPrice;
	}
	public void setOriginalPrice(Double originalPrice) {
		this.originalPrice = originalPrice;
	}
	@Override
	public String toString() {
		return "SimpleGoodsView [id=" + id + ", name=" + name + ", price=" + price + ", uploadTime=" + uploadTime
				+ ", ownerName=" + ownerName + ", ownerId=" + ownerId + ", imageUrl=" + imageUrl + ", classification="
				+ classification + ", status=" + status + "]";
	}
	
}
