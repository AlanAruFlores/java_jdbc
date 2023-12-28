package ar.com.eccomerce.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.Test;

import ar.com.eccomerce.dao.ICartDAO;
import ar.com.eccomerce.dao.IDAOManager;
import ar.com.eccomerce.dao.implementation.DAOManager;
import ar.com.eccomerce.exceptions.ObjectSQLNotExists;
import ar.com.eccomerce.model.Cart;

public class TestCartDAO {

	@Test
	public void testWeCanCreateACart() throws SQLException{
		IDAOManager manager = new DAOManager();
		ICartDAO dao = manager.getCartDAO();
		
		Cart newCart = new Cart(4,0.0f);
		assertTrue(dao.insert(newCart));	
		manager.closeConnection();
	}
	

	@Test
	public void testWeCanGetACartByUserId() throws SQLException, ObjectSQLNotExists{
		IDAOManager manager = new DAOManager();
		ICartDAO dao = manager.getCartDAO();
		
		Cart userIdCart  = new Cart(4,false);
		Cart objReceived = dao.getCartByUserId(userIdCart);
		assertNotNull(objReceived);
		System.out.println(objReceived);
		
		manager.closeConnection();
	}	
	
	@Test(expected = ObjectSQLNotExists.class)
	public void testWeCannotGetACartByUserId() throws SQLException, ObjectSQLNotExists{
		IDAOManager manager = new DAOManager();
		ICartDAO dao = manager.getCartDAO();
		
		Cart userIdCart  = new Cart(1000,false);
		Cart objReceived = dao.getCartByUserId(userIdCart);
		assertNotNull(objReceived);
		System.out.println(objReceived);
		
		manager.closeConnection();
	}
	

	@Test
	public void testWeCanGetATotalPriceOfCartByCartId() throws SQLException, ObjectSQLNotExists{
		IDAOManager manager = new DAOManager();
		ICartDAO dao = manager.getCartDAO();
		
		Cart cartId  = new Cart(1,true);
		Float total = dao.getTotalPriceOfCartByCartId(cartId);
		assertNotNull(total);	
		System.out.println(total);
		manager.closeConnection();
	}	

	@Test(expected = ObjectSQLNotExists.class)
	public void testWeCannotGetATotalPriceOfCartByCartId() throws SQLException, ObjectSQLNotExists{
		IDAOManager manager = new DAOManager();
		ICartDAO dao = manager.getCartDAO();
		
		Cart cartId  = new Cart(1000,true);
		Float total = dao.getTotalPriceOfCartByCartId(cartId);
		assertNotNull(total);	
		System.out.println(total);
		manager.closeConnection();
	}	
}
