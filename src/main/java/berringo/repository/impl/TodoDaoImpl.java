package berringo.repository.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import berringo.domain.Todo;
import berringo.repository.api.TodoDao;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class TodoDaoImpl implements TodoDao {

    @PersistenceContext
    private EntityManager entityManager;

    
    public Todo getTodoById(final long id) {
        return entityManager.find(Todo.class, id);
    }

    public List<Todo> getTodoListByUser(final long userId) {
        TypedQuery<Todo> query = entityManager.createNamedQuery("findTodosByUser", Todo.class);
        query.setParameter(1, userId);
        return query.getResultList();
    }

    public List<Todo> getTodoListByUserAndTitle(final long userId, final String title) {
        TypedQuery<Todo> query = entityManager.createNamedQuery("findTodosByTitle", Todo.class);
        query.setParameter(1, userId);
        query.setParameter(2, "%" + title.toUpperCase() + "%");
        return query.getResultList();
    }

    @Transactional
    public Todo update(Todo todo) {
        return entityManager.merge(todo);
    }

    @Transactional
    public Todo create(final Todo todo) {
        entityManager.persist(todo);
        return todo;
    }

    @Transactional
    public void remove(final Todo todo) {
        Todo t = entityManager.find(Todo.class, todo.getId());
        entityManager.remove(t);
    }

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	@Transactional
	public Todo remove(Long id) {
		 Todo t = entityManager.find(Todo.class, id);
	        entityManager.remove(t);
	        return t;
	}

}
