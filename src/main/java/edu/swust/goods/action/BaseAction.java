package edu.swust.goods.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
/**
 * 获得Servlet的API
 * @author hanpeng
 *
 */
public abstract class BaseAction extends ActionSupport {
	
	private static final long serialVersionUID = -5998387165971739050L;
    
	protected HttpServletResponse response;
	
	protected HttpServletRequest request;
    
    public BaseAction() {
    	request = ServletActionContext.getRequest();
	    response = ServletActionContext.getResponse();
	    response.setCharacterEncoding("utf-8");
	}
}
