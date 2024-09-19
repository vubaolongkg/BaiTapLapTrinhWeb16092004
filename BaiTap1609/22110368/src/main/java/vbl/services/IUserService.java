package vbl.services;

import vbl.models.UserModel;

public interface IUserService {
	UserModel login(String username,String password);
	UserModel FindByUserName(String username);
}
