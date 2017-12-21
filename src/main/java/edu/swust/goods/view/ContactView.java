package edu.swust.goods.view;
/**
 * 联系方式映射类
 * @author hanpeng
 *
 */
public class ContactView {
	private String name;    ///< 联系方式的名称
    private String contact; ///< 具体的联系方式
    
    public ContactView() {
	}
    
	public ContactView(String name, String contact) {
		this.name = name;
		this.contact = contact;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
    
}
