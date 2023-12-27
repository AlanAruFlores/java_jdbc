package ar.com.eccomerce.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import ar.com.eccomerce.dao.IDAOManager;
import ar.com.eccomerce.dao.IProductDAO;
import ar.com.eccomerce.dao.implementation.DAOManager;
import ar.com.eccomerce.exceptions.ObjectSQLNotExists;
import ar.com.eccomerce.model.Product;

public class TestProductDAO {

	@Test
	public void testWeCanInsertAProduct() throws SQLException{
		IDAOManager manager = new DAOManager();
		IProductDAO dao = manager.getProductDAO();
		
		Product product = new Product("Coca Cola");
		assertTrue(dao.insert(product));
		
		manager.closeConnection();
		
	}
	
	@Test
	public void testWeCanGetAllProducts() throws SQLException{
		IDAOManager manager = new DAOManager();
		IProductDAO dao = manager.getProductDAO();
		
		List<Product> list = dao.getAll();
		assertNotNull(list);
		
		list.forEach((x)->{
			System.out.println(x);
		});
		manager.closeConnection();
		
	}
	
	@Test
	public void testWeCanGetAProductById() throws SQLException, ObjectSQLNotExists{
		IDAOManager manager = new DAOManager();
		IProductDAO dao = manager.getProductDAO();
		
		Product result = dao.getById(new Product(2));
		
		assertNotNull(result);
		System.out.println(result);
		manager.closeConnection();
		
	}	
	
	@Test(expected = ObjectSQLNotExists.class)
	public void testWeCannotGetAProductById() throws SQLException, ObjectSQLNotExists{
		IDAOManager manager = new DAOManager();
		IProductDAO dao = manager.getProductDAO();
		
		Product result = dao.getById(new Product(200));
		
		assertNotNull(result);
		System.out.println(result);
		manager.closeConnection();
		
	}		
	
	@Test
	public void testWeCanUpdateAProductById() throws SQLException, ObjectSQLNotExists{
		IDAOManager manager = new DAOManager();
		IProductDAO dao = manager.getProductDAO();
		
		Product newData = new Product(1, "Papas Fritas Lays");
		assertTrue(dao.update(newData));
		manager.closeConnection();
		
	}	
	
	@Test(expected =ObjectSQLNotExists.class)
	public void testWeCannotUpdateAProductById() throws SQLException, ObjectSQLNotExists{
		IDAOManager manager = new DAOManager();
		IProductDAO dao = manager.getProductDAO();
		
		Product newData = new Product(1000, "Manaos");
		assertTrue(dao.update(newData));
		manager.closeConnection();
		
	}	
	
	@Test
	public void testWeCanDeleteAProductById() throws SQLException, ObjectSQLNotExists{
		IDAOManager manager = new DAOManager();
		IProductDAO dao = manager.getProductDAO();
		
		Product objDelete = new Product(2);
		assertTrue(dao.delete(objDelete));
		manager.closeConnection();
		
	}	
	
	@Test(expected = ObjectSQLNotExists.class)
	public void testWeCannotDeleteAProductById() throws SQLException, ObjectSQLNotExists{
		IDAOManager manager = new DAOManager();
		IProductDAO dao = manager.getProductDAO();
		
		Product objDelete = new Product(2000);
		assertTrue(dao.delete(objDelete));
		manager.closeConnection();
		
	}
	
	
}
