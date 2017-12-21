package edu.swust.goods.tempbean;
/**
 * 接收对话内容数据
 * @author hanpeng
 *
 */
public class DialogBean {
    private Long id;
	private String content;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "DialogBean [id=" + id + ", content=" + content + "]";
	}
	
}
