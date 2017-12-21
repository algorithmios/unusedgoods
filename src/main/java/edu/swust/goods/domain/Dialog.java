package edu.swust.goods.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Dialog {
    private Long id;
    private Date date;
    private Set<DialogContent> contents = new HashSet<DialogContent>();
    private User roleA;
    private User roleB;
    private Integer noReadA = 0;
    private Integer noReadB = 0;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Set<DialogContent> getContents() {
		return contents;
	}
	public void setContents(Set<DialogContent> contents) {
		this.contents = contents;
	}
	public User getRoleA() {
		return roleA;
	}
	public void setRoleA(User roleA) {
		this.roleA = roleA;
	}
	public User getRoleB() {
		return roleB;
	}
	public void setRoleB(User roleB) {
		this.roleB = roleB;
	}
	public Integer getNoReadA() {
		return noReadA;
	}
	public void setNoReadA(Integer noReadA) {
		this.noReadA = noReadA;
	}
	public Integer getNoReadB() {
		return noReadB;
	}
	public void setNoReadB(Integer noReadB) {
		this.noReadB = noReadB;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
}
