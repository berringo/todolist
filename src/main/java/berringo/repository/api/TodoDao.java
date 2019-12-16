package berringo.repository.api;

import java.util.List;

import berringo.domain.Todo;


public interface TodoDao {

    Todo getTodoById(final long id);
    List<Todo> getTodoListByUser(final long userId);
    List<Todo> getTodoListByUserAndTitle(final long userId, final String title);
    Todo create(final Todo todo);
    Todo update(Todo todo);
    void remove(final Todo todo);
    Todo remove(Long id);

}
