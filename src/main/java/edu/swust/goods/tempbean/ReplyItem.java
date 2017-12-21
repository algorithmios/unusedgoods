package edu.swust.goods.tempbean;
/**
 * 回复消息类
 * @author hanpeng
 *
 */
public class ReplyItem {
    private Long id;
    private String value;
    private String url;
    
    public ReplyItem() {
	}
    
	public ReplyItem(Long id, String value, String url) {
		this.id = id;
		this.value = value;
		this.url = url;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
