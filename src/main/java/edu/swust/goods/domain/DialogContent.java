package edu.swust.goods.domain;

import java.util.Date;

public class DialogContent implements Comparable<DialogContent> {
	private Long id;
    private Date date;
    private String roleA;
    private String roleB;
    private String content;
    private Dialog dialog;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRoleA() {
		return roleA;
	}
	public void setRoleA(String roleA) {
		this.roleA = roleA;
	}
	public String getRoleB() {
		return roleB;
	}
	public void setRoleB(String roleB) {
		this.roleB = roleB;
	}
	
	public Dialog getDialog() {
		return dialog;
	}
	public void setDialog(Dialog dialog) {
		this.dialog = dialog;
	}
	public int compareTo(DialogContent o) {
		return this.date.compareTo(o.date);
	}

}
