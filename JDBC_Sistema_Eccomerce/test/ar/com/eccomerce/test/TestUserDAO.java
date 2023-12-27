package ar.com.eccomerce.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import ar.com.eccomerce.dao.IDAOManager;
import ar.com.eccomerce.dao.IUserDAO;
import ar.com.eccomerce.dao.implementation.DAOManager;
import ar.com.eccomerce.exceptions.ObjectSQLNotExists;
import ar.com.eccomerce.model.User;

public class TestUserDAO {

	@Test
	public void testWeCanInsertAUser() throws SQLException{
		IDAOManager manager = new DAOManager();
		IUserDAO dao = manager.getUserDAO();
		
		User user = new User("Martin","Martin1234",400.0f,false,"Martin@gmail.com",1);
		assertTrue(dao.insert(user));
		manager.closeConnection();
	}
	
	@Test
	public void testWeCanGetAllUsers() throws SQLException{
		IDAOManager manager = new DAOManager();
		IUserDAO dao = manager.getUserDAO();
		
		List<User> users = dao.getAll();
		assertNotNull(users);
		users.forEach(x->{
			System.out.println(x);
		});
		
		manager.closeConnection();
	}

	@Test
	public void testWeCanGetAUserById() throws SQLException,ObjectSQLNotExists{
		IDAOManager manager = new DAOManager();
		IUserDAO dao = manager.getUserDAO();
		
		User user = dao.getById(new User(1));
		assertNotNull(user);
		System.out.println(user);
		
		manager.closeConnection();
	}
	
	@Test(expected = ObjectSQLNotExists.class)
	public void testWeCannotGetAUserById() throws SQLException,ObjectSQLNotExists{
		IDAOManager manager = new DAOManager();
		IUserDAO dao = manager.getUserDAO();
		
		User user = dao.getById(new User(1000));
		assertNotNull(user);
		System.out.println(user);
		
		manager.closeConnection();
	}
	
	@Test
	public void testWeCanUpdateAUserById() throws SQLException,ObjectSQLNotExists{
		IDAOManager manager = new DAOManager();
		IUserDAO dao = manager.getUserDAO();
		
		assertTrue(dao.update(new User(1,"Karl","pepe1234",600.0f,false,"pepe@gmail.com",1)));
		manager.closeConnection();
	}
	
	@Test(expected = ObjectSQLNotExists.class)
	public void testWeCannotUpdateAUserById() throws SQLException,ObjectSQLNotExists{
		IDAOManager manager = new DAOManager();
		IUserDAO dao = manager.getUserDAO();
		
		assertTrue(dao.update(new User(1,"Karl","pepe1234",600.0f,false,"pepe@gmail.com",1)));
		manager.closeConnection();
	}
	
	@Test
	public void testWeCanDeleteAUserById() throws SQLException,ObjectSQLNotExists{
		IDAOManager manager = new DAOManager();
		IUserDAO dao = manager.getUserDAO();
		
		assertTrue(dao.delete(new User(1)));
		manager.closeConnection();
	}
	
	@Test(expected = ObjectSQLNotExists.class)
	public void testWeCannotDeleteAUserById() throws SQLException,ObjectSQLNotExists{
		IDAOManager manager = new DAOManager();
		IUserDAO dao = manager.getUserDAO();
		
		assertTrue(dao.delete(new User(1000)));
		manager.closeConnection();
	}
}
