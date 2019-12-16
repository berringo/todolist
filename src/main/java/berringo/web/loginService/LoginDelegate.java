package berringo.web.loginService;

import java.sql.SQLException;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import berringo.domain.Users;
import berringo.service.api.UserService;

@Service
@Component
@ManagedBean
public class LoginDelegate {
	
	@Autowired
	private UserService userService;

	public UserService getUserService() {
		return this.userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public boolean isValidUser(String username, String password) throws SQLException {
		return userService.isValidUser(username, password);
	}
	
	public Long getUserId(String username, String password) throws SQLException {
		return userService.getUserId(username, password);
	}
	
}
