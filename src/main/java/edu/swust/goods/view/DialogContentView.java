package edu.swust.goods.view;

import edu.swust.goods.domain.DialogContent;
import edu.swust.goods.utils.TimeToStringUtil;
/**
 * 对话内容映射类
 * @author hanpeng
 *
 */
public class DialogContentView {
    private String date;     ///< 产生时间
    private String roleA;    ///< 发送方
    private String roleB;    ///< 接收方
    private String content;  ///< 内容
    public DialogContentView() {
		// TODO Auto-generated constructor stub
	}
    
    public DialogContentView(DialogContent content) {
	    this.roleA = content.getRoleA();
	    this.roleB = content.getRoleB();
	    this.date = TimeToStringUtil.timeToString(content.getDate());
	    this.content = content.getContent();
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
    
}
