package berringo.web;

import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import berringo.domain.Priority;
import berringo.domain.Todo;
import berringo.service.api.TodoService;
import berringo.web.loginService.SessionUtils;

@ManagedBean
@Component
@ViewScoped
public class AddTask {
	
	@Autowired
	TodoService todoService;

	private String title;
	private Date dueDate;
	private Long userId;
	private String selectedPriority;
	private List<Priority> priority=Arrays.asList(Priority.values());
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getDueDate() {
 		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public void addTodo() {
		HttpSession session = SessionUtils.getSession();
		this.userId=(long) session.getAttribute("userId");
		Todo todo=new Todo();
		todo.setUserId(userId);
		todo.setDone(false);
		todo.setTitle(title);
		todo.setPriority(Priority.valueOf(selectedPriority));
		todo.setDueDate(dueDate);
		todoService.create(todo);
		resetFields();
	}
  
	public void updateTodo(Todo todo) {
		todoService.update(todo);	
		
	}
	public List<Priority> getPriority() {
		return priority;
	}
	public void setPriority(List<Priority> priority) {
		this.priority = priority;
	}
	public String getSelectedPriority() {
		return selectedPriority;
	}
	public void setSelectedPriority(String selectedPriority) {
		this.selectedPriority = selectedPriority;
	}
	
	public void resetFields() {
		setTitle(null);
		setDueDate(null);
		setSelectedPriority(null) ;
	}
	
	public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        setDueDate((Date) event.getObject());
//        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
    }
 
	
	
}
