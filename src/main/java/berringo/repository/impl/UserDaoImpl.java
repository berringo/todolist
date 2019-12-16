package berringo.repository.impl;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import berringo.domain.Users;
import berringo.repository.api.UserDao;

@Repository
public class UserDaoImpl implements UserDao {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Users> getValidUser(String username, String password) throws SQLException {
		TypedQuery<Users> query = entityManager.createNamedQuery("validUser", Users.class);
		query.setParameter(1, username);
        query.setParameter(2, password);
		return query.getResultList();
	}

	@Override
	public List<Users> getAllUser() throws SQLException {
		TypedQuery<Users> query = entityManager.createNamedQuery("findAllUsers", Users.class);
	    return query.getResultList();
	}

	@Override
	@Transactional
	public void addUser(Users u) throws SQLException {
		entityManager.persist(u);
	}
}