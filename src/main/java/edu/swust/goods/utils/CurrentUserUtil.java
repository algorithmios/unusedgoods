package edu.swust.goods.utils;

import javax.servlet.http.HttpServletRequest;

import edu.swust.goods.domain.Administrator;
import edu.swust.goods.domain.User;
import edu.swust.goods.service.IAdminService;
import edu.swust.goods.service.IUserService;
import edu.swust.goods.view.AdminView;
import edu.swust.goods.view.UserView;
/**
 * 获取当前用户
 * @author hanpeng
 *
 */
public class CurrentUserUtil {
	public static User getCurrentUser(IUserService service, HttpServletRequest request) {
		User user = null;
		UserView view = (UserView) request.getSession().getAttribute(GlobalMessage.USER);
		if (view != null) {
            user = service.get(view.getId());
		}
		return user;
	}
	public static Administrator getCurrentAdmin(IAdminService service, HttpServletRequest request) {
		Administrator admin = null;
		AdminView view = (AdminView) request.getSession().getAttribute(GlobalMessage.ADMIN);
		if (view != null) {
			admin = service.get(view.getId());
		}
		return admin;
	}
}
