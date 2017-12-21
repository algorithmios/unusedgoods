package edu.swust.goods.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import edu.swust.goods.domain.Goods;
import edu.swust.goods.domain.GoodsImage;
/**
 * 商品详情映射类
 * @author hanpeng
 *
 */
public class GoodsView extends SimpleGoodsView {
    private String introduction;
    private String address;
    private Integer collecterCount;
    private Boolean isCollecte;
    private List<String> imageUrls;
    private List<ContactView> contacts;

    public GoodsView() {
	}
    
    public GoodsView(Goods goods) {
		super(goods);
		imageUrls = new ArrayList<String>();
		Set<GoodsImage> images = goods.getImages();
		for (GoodsImage image : images) {
			imageUrls.add(image.getImageUrl());
		}
		this.introduction = goods.getIntroduction();
		this.address = goods.getAddress();
		this.collecterCount = goods.getCollecters().size();
	}
    
	public GoodsView(Goods goods, Boolean isCollecte, List<ContactView> contacts) {

		
		super(goods);
		imageUrls = new ArrayList<String>();
		Set<GoodsImage> images = goods.getImages();
		for (GoodsImage image : images) {
			imageUrls.add(image.getImageUrl());
		}
		this.introduction = goods.getIntroduction();
		this.address = goods.getAddress();
		this.collecterCount = goods.getCollecters().size();
        this.contacts = contacts;
		this.isCollecte = isCollecte;
	}
	
	public List<String> getImageUrls() {
		return imageUrls;
	}

	public void setImageUrls(List<String> imageUrls) {
		this.imageUrls = imageUrls;
	}

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
	public Integer getCollecterCount() {
		return collecterCount;
	}
	public void setCollecterCount(Integer collecterCount) {
		this.collecterCount = collecterCount;
	}
	public Boolean getIsCollecte() {
		return isCollecte;
	}
	public void setIsCollecte(Boolean isCollecte) {
		this.isCollecte = isCollecte;
	}
	public List<ContactView> getContacts() {
		return contacts;
	}
	public void setContacts(List<ContactView> contacts) {
		this.contacts = contacts;
	}
	@Override
	public String toString() {
		return "GoodsView [introduction=" + introduction + ", address=" + address + ", originalPrice=" + originalPrice
			    + ", collecterCount=" + collecterCount + ", isCollecte=" + isCollecte + ", imageUrls=" + imageUrls + ", id=" + id + ", name=" + name
				+ ", price=" + price + ", uploadTime=" + uploadTime + ", ownerName=" + ownerName + ", ownerId="
				+ ownerId + ", imageUrl=" + imageUrl + ", classification=" + classification + "]";
	}
	
}
