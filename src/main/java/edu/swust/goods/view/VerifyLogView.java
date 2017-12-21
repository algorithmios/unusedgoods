package edu.swust.goods.view;

import edu.swust.goods.domain.VerifyLog;
import edu.swust.goods.utils.TimeToStringUtil;
/**
 * 审核记录映射类
 * @author hanpeng
 *
 */
public class VerifyLogView {
	private Long id;
    private String goodsName;
    private String adminName;
    private Long adminId;
    private String date;
    private String result;
    
    public VerifyLogView() {
	}

    public VerifyLogView(Integer start,Integer size) {
    	if (start != null) {
			id = start.longValue();
		}
    	if (size != null) {
			adminId = size.longValue();
		}
	}
    
    public VerifyLogView(VerifyLog log) {
		this.id = log.getId();
		this.goodsName = log.getGoodsName();
		this.adminName = log.getAdministrator().getNickname();
		this.adminId = log.getAdministrator().getId();
		this.date = TimeToStringUtil.timeToString(log.getDate());
		this.result = log.getResult();
	}

    
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public Long getAdminId() {
		return adminId;
	}
	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
}
