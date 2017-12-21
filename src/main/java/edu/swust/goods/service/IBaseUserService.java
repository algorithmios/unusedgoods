package edu.swust.goods.service;

import edu.swust.goods.tempbean.LoginInfoBean;
import edu.swust.goods.tempbean.ReplyItem;
import edu.swust.goods.view.BaseUserView;

public interface IBaseUserService<T> extends IBaseService<T> {
	ReplyItem checkLogin(LoginInfoBean bean);
    T accountExist(String account);
    BaseUserView getView(Long id);
}
