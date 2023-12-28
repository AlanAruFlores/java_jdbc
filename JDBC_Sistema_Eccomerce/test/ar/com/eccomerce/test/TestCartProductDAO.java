package ar.com.eccomerce.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import ar.com.eccomerce.dao.ICartProductDAO;
import ar.com.eccomerce.dao.IDAOManager;
import ar.com.eccomerce.dao.implementation.DAOManager;
import ar.com.eccomerce.exceptions.ObjectSQLNotExists;
import ar.com.eccomerce.model.CartProduct;

public class TestCartProductDAO {

	@Test
	public void testWeCanInsertAProductInTheCart () throws SQLException{
		IDAOManager manager = new DAOManager();
		ICartProductDAO dao  = manager.getCartProductDAO();
	
		CartProduct cp =new CartProduct(2,3);
		
		assertTrue(dao.insert(cp));
		
		manager.closeConnection();
		
	}
	
	@Test
	public void testWeGetAllCartsAnProducts () throws SQLException{
		IDAOManager manager = new DAOManager();
		ICartProductDAO dao  = manager.getCartProductDAO();
	
		List<CartProduct> list = dao.getAll();
		assertNotNull(list);
		list.forEach(x-> System.out.println(x));
	
		manager.closeConnection();
	}
	
	@Test
	public void testWeCanGetCartAnProductById () throws SQLException,ObjectSQLNotExists{
		IDAOManager manager = new DAOManager();
		ICartProductDAO dao  = manager.getCartProductDAO();
	
		CartProduct obj = dao.getById(new CartProduct(2,3));
		assertNotNull(obj);
		System.err.println(obj);
		manager.closeConnection();
	}
	
	@Test(expected = ObjectSQLNotExists.class)
	public void testWeCannotGetCartAnProductById () throws SQLException,ObjectSQLNotExists{
		IDAOManager manager = new DAOManager();
		ICartProductDAO dao  = manager.getCartProductDAO();
	
		CartProduct obj = dao.getById(new CartProduct(1000,1000));
		assertNotNull(obj);
		System.err.println(obj);
		manager.closeConnection();
	}
	
	
	@Test
	public void testWeCanDeleteByCartAndProductId () throws SQLException,ObjectSQLNotExists{
		IDAOManager manager = new DAOManager();
		ICartProductDAO dao  = manager.getCartProductDAO();
	
		assertTrue(dao.delete(new CartProduct(2,3)));
		manager.closeConnection();
	}
	
	@Test(expected = ObjectSQLNotExists.class)
	public void testWeCannotDeleteByCartAndProductId () throws SQLException,ObjectSQLNotExists{
		IDAOManager manager = new DAOManager();
		ICartProductDAO dao  = manager.getCartProductDAO();
	
		assertTrue(dao.delete(new CartProduct(1000,1000)));
		manager.closeConnection();
	}
	
	
	@Test
	public void testAllowMeShowProductsSavedInTheCart () throws SQLException,ObjectSQLNotExists{
		IDAOManager manager = new DAOManager();
		ICartProductDAO dao  = manager.getCartProductDAO();
	
		String txt = dao.showProductsSavedInTheCart(new CartProduct(2));
		assertNotNull(txt);
		assertTrue(txt != "");
		System.out.println(txt);
		manager.closeConnection();
	}
	
	@Test (expected = ObjectSQLNotExists.class)
	public void testNotAllowMeShowProductsSavedInTheCart () throws SQLException,ObjectSQLNotExists{
		IDAOManager manager = new DAOManager();
		ICartProductDAO dao  = manager.getCartProductDAO();
	
		String txt = dao.showProductsSavedInTheCart(new CartProduct(1000));
		assertNotNull(txt);
		assertTrue(txt != "");
		System.out.println(txt);
		manager.closeConnection();
	}
	
}
	
