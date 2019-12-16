package berringo.web;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import berringo.domain.Users;
import berringo.service.api.UserService;

@Component
@ManagedBean
@ViewScoped
public class UserView {
	@Autowired
	UserService userService;
	private String pwd;
	private String user;
	private String email;
	
	public String createUser() throws Throwable{
		Users u=new Users();
		u.setName(user);
		u.setPassword(pwd);
		u.setEmail(email);
		userService.addUser(u);
		PrimeFaces.current().resetInputs("formRegister:matchGrid");
		resetFields();
		return "login";
	}

	private void resetFields() {
		setEmail("");
		setPwd("");
		setUser("");
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
