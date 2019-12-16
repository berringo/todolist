package berringo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import berringo.domain.Todo;
import berringo.repository.api.TodoDao;
import berringo.service.api.TodoService;


@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoDao todoDao;
    
    @Autowired
    private Sender kafkaSender;
 
    public Todo getTodoById(final long id) {
        return todoDao.getTodoById(id);
    }
  
    public List<Todo> getTodoListByUser(final long userId) {
        return todoDao.getTodoListByUser(userId);
    }

    public List<Todo> searchTodoListByTitle(final long userId, final String title) {
        return todoDao.getTodoListByUserAndTitle(userId, title);
    }

	public TodoDao getTodoDao() {
		return todoDao;
	}

	public void setTodoDao(TodoDao todoDao) {
		this.todoDao = todoDao;
	}
  
    public Todo update(Todo todo) {
    	Todo t=todoDao.update(todo);
    	 kafkaSender.send(t,"update");
        return t;
    }
   
    public Todo create(final Todo todo){
       Todo t= todoDao.create(todo);
       kafkaSender.send(t,"insert");
       return t;
    }
   
    public void remove(final Todo todo) {
    	todoDao.remove(todo);
        kafkaSender.send(todo, "delete");
    }

	@Override
	public void remove(Long id) {
		Todo todo = todoDao.remove(id);
		kafkaSender.send(todo, "delete");
	}
}
