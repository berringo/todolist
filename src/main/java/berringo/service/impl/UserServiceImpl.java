package berringo.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import berringo.domain.Users;
import berringo.repository.api.UserDao;
import berringo.service.api.UserService;

@Service
public class UserServiceImpl implements UserService {
		
	@Autowired
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public boolean isValidUser(String username, String password) throws SQLException {
		List<Users> users = userDao.getValidUser(username, password);
		 return users.size()==1;
	}
	
	@Override
	public List<Users> getAllUser() throws SQLException {
		return userDao.getAllUser();
	}

	@Override
	public long getUserId(String username, String password) throws SQLException {
		// TODO Auto-generated method stub
		return userDao.getValidUser(username, password).get(0).getId();
	}

	@Override
	public void addUser(final Users u) throws SQLException {
		userDao.addUser(u);
		
	}

}
