package edu.swust.goods.domain;

import java.util.Date;

public class Suggestion {
    private Long id;
    private String fromName;
    private String content;
    private Date date;
    private Integer isRead;
    public Long getId() {
 	   return id;
    }
    public void setId(Long id) {
        this.id = id;
    } 
    public String getFromName() {
        return fromName;
    }
    public void setFromName(String fromName) {
        this.fromName = fromName;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
	public Integer getIsRead() {
		return isRead;
	}
	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}
	
}
