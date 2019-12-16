package berringo.web;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import berringo.web.loginService.LoginDelegate;
import berringo.web.loginService.SessionUtils;


@ManagedBean
@SessionScoped
@Component
public class Login implements Serializable {

	private static final long serialVersionUID = 1094801825228386363L;

	@Autowired
	private LoginDelegate loginDelegate;
	
	
	private String pwd;
	private String msg;
	private String user;

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	// validate login
	public String validateUsernamePassword() throws Throwable {
		boolean valid = loginDelegate.isValidUser(user, pwd);
		if (valid) {
			HttpSession session = SessionUtils.getSession();
			session.setAttribute("username", user);
			session.setAttribute("userId", loginDelegate.getUserId(user, pwd));
			return "admin";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Incorrect Username and Passowrd", "Please enter correct username and Password"));
			return "login";
		}
	}

	// logout event, invalidate session
	public String logout() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "login";
	}
	
	public String goToRegitsration() {
		return "userView";
	}
	
	public String goToLogin() {
		return "login";
	}
	
	public String goToRegistration() {
		return "UserView";
	}
	
}
