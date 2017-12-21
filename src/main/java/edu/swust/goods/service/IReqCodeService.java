package edu.swust.goods.service;

import edu.swust.goods.domain.ReqCode;

public interface IReqCodeService extends IBaseService<ReqCode> {
    ReqCode getAccount(String account, boolean flag);
}
