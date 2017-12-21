package edu.swust.goods.domain;

import java.util.Date;

public class ReqCode {
    private Long id;
    private String email;
    private String reqCode;
    private Long date;
    private Integer verification;
    public ReqCode() {
	}
    
	public ReqCode(String email, String reqCode) {
		verification = 0;
		this.email = email;
		this.reqCode = reqCode;
		this.date = new Date().getTime();
	}
	public ReqCode(String email) {
	    this.verification = 0;
		this.email = email;
		this.date = new Date().getTime();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getReqCode() {
		return reqCode;
	}
	public void setReqCode(String reqCode) {
		this.reqCode = reqCode;
	}
	public Long getDate() {
		return date;
	}
	public void setDate(Long date) {
		this.date = date;
	}
	public Integer getVerification() {
		return verification;
	}
	public void setVerification(Integer verification) {
		this.verification = verification;
	}
    
    
}
