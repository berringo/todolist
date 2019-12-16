package berringo.service.api;

import java.sql.SQLException;
import java.util.List;

import berringo.domain.Users;

public interface UserService {
	
	public boolean isValidUser(String username, String password) throws SQLException;

	public List<Users> getAllUser() throws SQLException;
	
	public long getUserId(String username, String password) throws SQLException;
	
	public  void addUser(Users u) throws SQLException;

}
