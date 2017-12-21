package edu.swust.goods.view;

import edu.swust.goods.domain.GoodsImage;
/**
 * 首页图片映射类
 * @author hanpeng
 *
 */
public class ImageUrlView {
    private String url;
    private String contact;
    
    public ImageUrlView() {
	}
    
	public ImageUrlView(GoodsImage image) {
		this.url = image.getImageUrl();
		this.contact = "";
	}

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
    
}
