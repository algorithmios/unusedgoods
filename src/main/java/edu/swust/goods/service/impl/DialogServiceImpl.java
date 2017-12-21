package edu.swust.goods.service.impl;

import java.util.Date;

import edu.swust.goods.domain.Dialog;
import edu.swust.goods.service.IDialogService;

public class DialogServiceImpl extends BaseServiceImpl<Dialog> implements IDialogService {
	/**
	 * 更新时间
	 */
    @Override
    public void saveOrUpdate(Dialog t) {
        t.setDate(new Date());
    	super.saveOrUpdate(t);
    }
}
