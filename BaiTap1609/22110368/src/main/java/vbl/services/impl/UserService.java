package vbl.services.impl;

import vbl.dao.IUserDao;
import vbl.dao.impl.UserDaoImpl;
import vbl.models.UserModel;
import vbl.services.IUserService;

public class UserService implements IUserService{
	// lấy toàn bộ hàm trong tầng Dao của user
	IUserDao userDao = new UserDaoImpl();
	
	@Override
	public UserModel login(String username, String password) {
		UserModel user = this.FindByUserName(username);
		if (user != null && password.equals(user.getPassword())) {
		return user;
		}
		return null;
	}

	@Override
	public UserModel FindByUserName(String username) {
		return userDao.findByUserName(username);
	}
	
}
