package edu.swust.goods.service;

import java.util.List;

import edu.swust.goods.domain.VerifyLog;
import edu.swust.goods.view.VerifyLogView;

public interface IVerifyLogService extends IBaseService<VerifyLog> {
	
	List<VerifyLogView> allVerifyLogs(Integer start);
}
