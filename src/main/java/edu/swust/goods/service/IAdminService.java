package edu.swust.goods.service;

import java.util.List;

import edu.swust.goods.domain.Administrator;
import edu.swust.goods.view.AdminView;
import edu.swust.goods.view.PaginationView;
import edu.swust.goods.view.VerifyLogView;

public interface IAdminService extends IBaseUserService<Administrator> {
	List<VerifyLogView> myVerifyLog(Administrator admin);
	PaginationView<AdminView> getAllAdmin(Integer start);
}
