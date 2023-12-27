package ar.com.eccomerce.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import ar.com.eccomerce.dao.IDAOManager;
import ar.com.eccomerce.dao.IPostDAO;
import ar.com.eccomerce.dao.implementation.DAOManager;
import ar.com.eccomerce.exceptions.ObjectSQLNotExists;
import ar.com.eccomerce.model.Post;

public class TestPostDAO {

	@Test
	public void testWeCanCreateAPost() throws SQLException{
		IDAOManager manager = new DAOManager();
		IPostDAO dao = manager.getPostDAO();
		
		assertTrue(dao.insert(new Post("COMBO SNACK", "OBTEN UN COMBO SNACK A UN BAJO PRECIO",10.0f, 2)));
		manager.closeConnection();
	}

	
	@Test
	public void testWeCanGetAllPosts() throws SQLException{
		IDAOManager manager = new DAOManager();
		IPostDAO dao = manager.getPostDAO();
		
		List<Post> listPosts = dao.getAll();
		assertNotNull(listPosts);
		
		listPosts.forEach((x)->{
			System.out.println(x);
		});
		manager.closeConnection();
	}
	
	@Test
	public void testWeCanGetAPostById() throws SQLException, ObjectSQLNotExists{
		IDAOManager manager = new DAOManager();
		IPostDAO dao = manager.getPostDAO();
		
		Post post = dao.getById(new Post(1,false));
		assertNotNull(post);
		System.out.println(post);
		manager.closeConnection();
	}
	
	
	@Test(expected = ObjectSQLNotExists.class)
	public void testWeCannotGetAPostById() throws SQLException, ObjectSQLNotExists{
		IDAOManager manager = new DAOManager();
		IPostDAO dao = manager.getPostDAO();
		
		Post post = dao.getById(new Post(1444,false));
		assertNotNull(post);
		System.out.println(post);
		manager.closeConnection();
	}
	
	@Test
	public void testWeCanUpdateAPostById() throws SQLException, ObjectSQLNotExists{
		IDAOManager manager = new DAOManager();
		IPostDAO dao = manager.getPostDAO();
		
		assertTrue(dao.update(new Post(1,"SUPER COMBO","COMBO SNACK A UN BAJO PRECIO !!",20.0f,2)));
		manager.closeConnection();
	}
	
	
	@Test(expected = ObjectSQLNotExists.class)
	public void testWeCannotUpdateAPostById() throws SQLException, ObjectSQLNotExists{
		IDAOManager manager = new DAOManager();
		IPostDAO dao = manager.getPostDAO();
		
		assertTrue(dao.update(new Post(2220,"SUPER COMBO","COMBO SNACK A UN BAJO PRECIO !!",20.0f,2)));
		manager.closeConnection();
	}
	
	@Test
	public void testWeCanDeleteAPostById() throws SQLException, ObjectSQLNotExists{
		IDAOManager manager = new DAOManager();
		IPostDAO dao = manager.getPostDAO();
		
		assertTrue(dao.delete(new Post(1,false)));
		manager.closeConnection();
	}

	@Test(expected = ObjectSQLNotExists.class)
	public void testWeCannotDeleteAPostById() throws SQLException, ObjectSQLNotExists{
		IDAOManager manager = new DAOManager();
		IPostDAO dao = manager.getPostDAO();
		
		assertTrue(dao.delete(new Post(2424,false)));
		manager.closeConnection();
	}
	
	@Test
	public void testWeShowAPostDescriptionById() throws SQLException, ObjectSQLNotExists{
		IDAOManager manager = new DAOManager();
		IPostDAO dao = manager.getPostDAO();
		
		String postDesc = dao.getDescriptionOfPostById(new Post(2,false));
		assertNotNull(postDesc);
		assertTrue(postDesc != "");
		
		System.out.println(postDesc);
		manager.closeConnection();
	}
	
	@Test(expected=ObjectSQLNotExists.class)
	public void testWeCannotShowAPostDescriptionById() throws SQLException, ObjectSQLNotExists{
		IDAOManager manager = new DAOManager();
		IPostDAO dao = manager.getPostDAO();
		
		String postDesc = dao.getDescriptionOfPostById(new Post(1000,false));
		assertNotNull(postDesc);
		assertTrue(postDesc != "");
		
		System.out.println(postDesc);
		manager.closeConnection();
	}


}
