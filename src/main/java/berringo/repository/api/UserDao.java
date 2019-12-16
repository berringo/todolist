package berringo.repository.api;

import java.sql.SQLException;
import java.util.List;

import berringo.domain.Users;

public interface UserDao {
	public List<Users> getValidUser(String username, String password) throws SQLException;

	public List<Users> getAllUser() throws SQLException;
	
//	public Long getUserId(String username, String password) throws SQLException;

	void addUser(Users u) throws SQLException;
}
