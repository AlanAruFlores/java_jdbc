package ar.com.eccomerce.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import ar.com.eccomerce.dao.ICommentDAO;
import ar.com.eccomerce.dao.IDAOManager;
import ar.com.eccomerce.dao.implementation.DAOManager;
import ar.com.eccomerce.exceptions.ObjectSQLNotExists;
import ar.com.eccomerce.model.Comment;

public class TestCommentDAO {

	@Test
	public void testWeCanDoACommentToPost() throws SQLException{
		IDAOManager manager = new DAOManager();
		ICommentDAO dao  = manager.getCommentDAO();
		
		Comment comment = new Comment(2,"LO PROBE Y ES UNA MRD !! ",3);
		assertTrue(dao.insert(comment));
		
		manager.closeConnection();
	}
	
	@Test
	public void testWeCanGetAllComment() throws SQLException{
		IDAOManager manager = new DAOManager();
		ICommentDAO dao  = manager.getCommentDAO();
		
		List<Comment> list = dao.getAll();
		assertNotNull(list);
		list.forEach(x->System.out.println(x));
		
		manager.closeConnection();
	}
	
	@Test
	public void testWeCanGetACommentById() throws SQLException,ObjectSQLNotExists{
		IDAOManager manager = new DAOManager();
		ICommentDAO dao  = manager.getCommentDAO();
		
		Comment c = dao.getById(new Comment(1));
		
		assertNotNull(c);
		System.out.println(c);
		manager.closeConnection();
	}
	
	@Test(expected = ObjectSQLNotExists.class)
	public void testWeCannotGetACommentById() throws SQLException,ObjectSQLNotExists{
		IDAOManager manager = new DAOManager();
		ICommentDAO dao  = manager.getCommentDAO();
		
		Comment c = dao.getById(new Comment(1000));
		
		assertNotNull(c);
		System.out.println(c);
		manager.closeConnection();
	}
	
	@Test
	public void testWeCanUpdateACommentById() throws SQLException,ObjectSQLNotExists{
		IDAOManager manager = new DAOManager();
		ICommentDAO dao  = manager.getCommentDAO();
		
		Comment c = new Comment(1,2,"ESTA BUENARDA!!",3);
		assertTrue(dao.update(c));
		manager.closeConnection();
	}
	
	@Test(expected = ObjectSQLNotExists.class)
	public void testWeCannotUpdateACommentById() throws SQLException,ObjectSQLNotExists{
		IDAOManager manager = new DAOManager();
		ICommentDAO dao  = manager.getCommentDAO();
		
		Comment c = new Comment(14,2123,"ESTA BUENARDA!!",3);
		assertTrue(dao.update(c));
		manager.closeConnection();
	}
	
	
	@Test
	public void testWeCanDeleteACommentById() throws SQLException,ObjectSQLNotExists{
		IDAOManager manager = new DAOManager();
		ICommentDAO dao  = manager.getCommentDAO();
		
		Comment c = new Comment();
		c.setCodCom(2);
		c.setCodPost(2);
		
		assertTrue(dao.delete(c));
		manager.closeConnection();
	}
	
	@Test(expected = ObjectSQLNotExists.class)
	public void testWeCannotDeleteACommentById() throws SQLException,ObjectSQLNotExists{
		IDAOManager manager = new DAOManager();
		ICommentDAO dao  = manager.getCommentDAO();
		
		Comment c = new Comment();
		c.setCodCom(24442);
		c.setCodPost(24424);
		
		assertTrue(dao.delete(c));
		manager.closeConnection();
	}
}
