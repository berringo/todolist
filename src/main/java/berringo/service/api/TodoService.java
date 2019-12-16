package berringo.service.api;

import java.util.List;
import berringo.domain.Todo;

public interface TodoService {

	Todo getTodoById(final long id);

	List<Todo> getTodoListByUser(final long userId);

	List<Todo> searchTodoListByTitle(final long userId, final String title);

	Todo update(Todo todo);

	Todo create(final Todo todo) ;

	void remove(final Todo todo);
	
	void remove(final Long id);

}
