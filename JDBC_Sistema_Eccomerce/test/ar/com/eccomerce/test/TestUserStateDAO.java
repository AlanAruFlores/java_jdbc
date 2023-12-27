package ar.com.eccomerce.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import ar.com.eccomerce.dao.IDAOManager;
import ar.com.eccomerce.dao.IUserStateDAO;
import ar.com.eccomerce.dao.implementation.DAOManager;
import ar.com.eccomerce.exceptions.ObjectSQLNotExists;
import ar.com.eccomerce.model.UserState;

public class TestUserStateDAO {

	@Test
	public void testIfWeCanCreateAUserStateInDB() throws SQLException{
		IDAOManager manager = new DAOManager();
		IUserStateDAO dao = manager.getUserStateDAO();
		
		UserState obj = new UserState("BLOQUEADO");
		assertTrue(dao.insert(obj));
		manager.closeConnection();
		
	}
	
	@Test
	public void testWeCanGetAllRows()throws SQLException{
		IDAOManager manager = new DAOManager();
		IUserStateDAO dao = manager.getUserStateDAO();
		
		List<UserState> list = dao.getAll();
		assertNotNull(list);
		list.forEach((x)->{
			System.out.println(x);
		});
		manager.closeConnection();
	}
	
	@Test
	public void testWeCanGetAStateById()throws SQLException, ObjectSQLNotExists{
		IDAOManager manager = new DAOManager();
		IUserStateDAO dao = manager.getUserStateDAO();
		
		UserState obj = dao.getById(new UserState(1));
		assertNotNull(obj);
		System.out.println(obj);
		manager.closeConnection();
	}
	
	@Test(expected = ObjectSQLNotExists.class)
	public void testWeCannotGetAStateById()throws SQLException, ObjectSQLNotExists{
		IDAOManager manager = new DAOManager();
		IUserStateDAO dao = manager.getUserStateDAO();
		
		UserState obj = dao.getById(new UserState(40));
		assertNotNull(obj);
		System.out.println(obj);
		manager.closeConnection();
	}
	
	@Test
	public void testWeCanUpdateAStateById()throws SQLException, ObjectSQLNotExists{
		IDAOManager manager = new DAOManager();
		IUserStateDAO dao = manager.getUserStateDAO();
		
		assertTrue(dao.update(new UserState(4,"SUSPENDIDO")));
		manager.closeConnection();
	}
	
	@Test(expected = ObjectSQLNotExists.class)
	public void testWeCannotUpdateAStateById()throws SQLException, ObjectSQLNotExists{
		IDAOManager manager = new DAOManager();
		IUserStateDAO dao = manager.getUserStateDAO();
		
		assertTrue(dao.update(new UserState(10,"SUSPENDIDO")));
		manager.closeConnection();
	}
	
	@Test
	public void testWeCanDeleteAStateById()throws SQLException, ObjectSQLNotExists{
		IDAOManager manager = new DAOManager();
		IUserStateDAO dao = manager.getUserStateDAO();
		
		assertTrue(dao.delete(new UserState(4)));
		manager.closeConnection();
	}
	
	@Test(expected = ObjectSQLNotExists.class)
	public void testWeCannotDeleteAStateById()throws SQLException, ObjectSQLNotExists{
		IDAOManager manager = new DAOManager();
		IUserStateDAO dao = manager.getUserStateDAO();
		
		assertTrue(dao.delete(new UserState(40)));
		manager.closeConnection();
	}
}
