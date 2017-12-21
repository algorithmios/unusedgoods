package edu.swust.goods.domain;

import java.util.HashSet;
import java.util.Set;

public class Administrator extends BaseUser {
    private Integer permit;
    private Set<VerifyLog> verifyLogs = new HashSet<VerifyLog>();
	public Set<VerifyLog> getVerifyLogs() {
		return verifyLogs;
	}
	public void setVerifyLogs(Set<VerifyLog> verifyLogs) {
		this.verifyLogs = verifyLogs;
	}
	public Integer getPermit() {
		return permit;
	}
	public void setPermit(Integer permit) {
		this.permit = permit;
	}
}
