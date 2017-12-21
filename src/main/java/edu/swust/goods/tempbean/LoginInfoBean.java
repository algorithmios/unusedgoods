package edu.swust.goods.tempbean;
/**
 * 接收登录信息
 * @author hanpeng
 *
 */
public class LoginInfoBean {
    private String account;
    private String password;
    private String reqcode;
    public LoginInfoBean() {
	}

    public LoginInfoBean(String account, String password, String reqcode) {
		this.account = account;
		this.password = password;
		this.reqcode = reqcode;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getReqcode() {
		return reqcode;
	}
	public void setReqcode(String reqcode) {
		this.reqcode = reqcode;
	}

	@Override
	public String toString() {
		return "LoginInfoBean [account=" + account + ", password=" + password + ", reqcode=" + reqcode + "]";
	}

}
