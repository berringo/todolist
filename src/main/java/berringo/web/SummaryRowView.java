package berringo.web;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import berringo.domain.Todo;
import berringo.service.api.TodoService;
import berringo.web.loginService.SessionUtils;

@Component
@ManagedBean
@SessionScoped
public class SummaryRowView{
	
	@Autowired
    private TodoService todoService;
	
	private List<Todo> todoList;
	
	private Todo todo;
	
	private long userId;
	
	private Todo selectedTodo;
    
	public SummaryRowView() {
		
	}
	
    public void init() {
		HttpSession session = SessionUtils.getSession();
		this.userId=(long) session.getAttribute("userId");
		setTodoList(todoService.getTodoListByUser(userId));
    }

	public Todo getTodo() {
		return todo;
	}

	public void setTodo(Todo todo) {
		this.todo = todo;
	}
    
	public int getTotalNumberTasks() {
		return todoService.getTodoListByUser(1).size();
	}
	
	public void removeTodo(long id) {
		todoService.remove(id);
	}
	
	public void updateTodo(Todo todo) {
		todoService.update(todo);
	}
	
	public List<Todo> getTodoList() {
		return todoList;
	}

	public void setTodoList(List<Todo> todoList) {
		this.todoList = todoList;
	}

	public Todo getSelectedTodo() {
		return selectedTodo;
	}

	public void setSelectedTodo(Todo selectedtodo) {
		this.selectedTodo = selectedtodo;
	}
	
	public void updateSelectedTodo(Boolean status) {
		getSelectedTodo().setDone(status);
		updateTodo(getSelectedTodo());
	}
	
}