package edu.swust.goods.service;

import java.util.List;

import edu.swust.goods.domain.Goods;
import edu.swust.goods.domain.User;
import edu.swust.goods.tempbean.DialogBean;
import edu.swust.goods.view.ContactView;
import edu.swust.goods.view.DialogView;
import edu.swust.goods.view.PaginationView;

public interface IUserService extends IBaseUserService<User> {
	
	List<ContactView> contactsView(User user);
	
	void addCollection(User user, Goods goods);
	
	PaginationView<DialogView> listDialog(User user, Integer start);
	
	int dialogCount(User user) ;
	
	void addDialog(User user, DialogBean bean);
	
	void cancleCollection(User user, Long id);
}
