package ar.com.eccomerce.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import ar.com.eccomerce.dao.IDAOManager;
import ar.com.eccomerce.dao.IPostProductDAO;
import ar.com.eccomerce.dao.implementation.DAOManager;
import ar.com.eccomerce.exceptions.ObjectSQLNotExists;
import ar.com.eccomerce.model.PostProduct;

public class TestPostProductDAO {

	@Test
	public void testWeInsertProductsToPost() throws SQLException{
		IDAOManager manager = new DAOManager();
		IPostProductDAO dao = manager.getPostProductDAO();
		
		PostProduct pp = new PostProduct(2,1);
		
		assertTrue(dao.insert(pp));
		manager.closeConnection();
	}
	
	@Test
	public void testWeCanShowAllProductsAndPostsAscending() throws SQLException{
		IDAOManager manager = new DAOManager();
		IPostProductDAO dao = manager.getPostProductDAO();
		
		List<PostProduct> list =  dao.getAllAscending();
		assertNotNull(list);
		list.forEach((x)->{
			System.err.println(x);
		});
		manager.closeConnection();
	}
	
	@Test
	public void testWeCanShowAllProductsAndPostsDescending() throws SQLException{
		IDAOManager manager = new DAOManager();
		IPostProductDAO dao = manager.getPostProductDAO();
		
		List<PostProduct> list =  dao.getAllDescending();
		assertNotNull(list);
		list.forEach((x)->{
			System.err.println(x);
		});
		manager.closeConnection();
	}

	@Test
	public void testWeCanGetProductAndPostById() throws SQLException, ObjectSQLNotExists{
		IDAOManager manager = new DAOManager();
		IPostProductDAO dao = manager.getPostProductDAO();
		
		PostProduct pp = new PostProduct(2,1);
		PostProduct result = dao.getById(pp);
		
		assertNotNull(result);
		System.out.println(result);
		manager.closeConnection();
	}
	
	@Test(expected = ObjectSQLNotExists.class)
	public void testWeCannotGetProductAndPostById() throws SQLException, ObjectSQLNotExists{
		IDAOManager manager = new DAOManager();
		IPostProductDAO dao = manager.getPostProductDAO();
		
		PostProduct pp = new PostProduct(1000,1000);
		PostProduct result = dao.getById(pp);
		
		assertNotNull(result);
		System.out.println(result);
		manager.closeConnection();
	}
	
	
	@Test
	public void testWeCanDeleteProductAndPostById() throws SQLException, ObjectSQLNotExists{
		IDAOManager manager = new DAOManager();
		IPostProductDAO dao = manager.getPostProductDAO();
		PostProduct pp = new PostProduct(2,1);			
		assertTrue(dao.delete(pp));
		manager.closeConnection();
	}
	
	@Test(expected = ObjectSQLNotExists.class)
	public void testWeCannotDeleteProductAndPostById() throws SQLException, ObjectSQLNotExists{
		IDAOManager manager = new DAOManager();
		IPostProductDAO dao = manager.getPostProductDAO();
		PostProduct pp = new PostProduct(1000,1000);			
		assertTrue(dao.delete(pp));
		manager.closeConnection();
	}
	
	@Test
	public void testWeCanShowDescriptionAboutProductsAndPostById() throws SQLException, ObjectSQLNotExists{
		IDAOManager manager = new DAOManager();
		IPostProductDAO dao = manager.getPostProductDAO();
		PostProduct pp = new PostProduct(2);			
		String txt = dao.showPostDescritionById(pp);
		assertNotNull(txt);
		assertTrue(txt != "");
		System.out.println(txt);
		manager.closeConnection();
	}
	
	
	@Test(expected = ObjectSQLNotExists.class)
	public void testWeCannotShowDescriptionAboutProductsAndPostById() throws SQLException, ObjectSQLNotExists{
		IDAOManager manager = new DAOManager();
		IPostProductDAO dao = manager.getPostProductDAO();
		PostProduct pp = new PostProduct(1000);			
		String txt = dao.showPostDescritionById(pp);
		assertNotNull(txt);
		assertTrue(txt != "");
		System.out.println(txt);
		manager.closeConnection();
	}
}
