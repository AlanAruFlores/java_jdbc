package ar.com.eccomerce.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import ar.com.eccomerce.dao.IDAOManager;
import ar.com.eccomerce.dao.IUserProductDAO;
import ar.com.eccomerce.dao.implementation.DAOManager;
import ar.com.eccomerce.exceptions.ObjectSQLNotExists;
import ar.com.eccomerce.model.User;
import ar.com.eccomerce.model.UserProduct;

public class TestUserProductDAO {

	@Test
	public void testWeCanInsertProductToUser() throws SQLException{
		IDAOManager manager = new DAOManager();
		IUserProductDAO dao = manager.getUserProductDAO();
		
		UserProduct up = new UserProduct(3,3);
		
		assertTrue(dao.insert(up));
		manager.closeConnection();
	}
	
	@Test
	public void testWeCanGetAllProductsUsers() throws SQLException{
		IDAOManager manager = new DAOManager();
		IUserProductDAO dao = manager.getUserProductDAO();
		
		List<UserProduct> list = dao.getAll();
		assertNotNull(list);
		list.forEach(x->System.out.println(x));
		manager.closeConnection();
	}
	
	
	@Test
	public void testWeCanShowAllProductsByUserId()throws SQLException, ObjectSQLNotExists{
		IDAOManager manager = new DAOManager();
		IUserProductDAO dao = manager.getUserProductDAO();

		String txt = dao.showAllProductsByUserId(new User(3));
		assertTrue(txt != "");
		System.out.println(txt);
		manager.closeConnection();
	}
	
	@Test(expected = ObjectSQLNotExists.class)
	public void testWeCannotShowAllProductsByUserId()throws SQLException, ObjectSQLNotExists{
		IDAOManager manager = new DAOManager();
		IUserProductDAO dao = manager.getUserProductDAO();

		String txt = dao.showAllProductsByUserId(new User(1000));
		assertTrue(txt != "");
		System.out.println(txt);
		manager.closeConnection();
	}

}
