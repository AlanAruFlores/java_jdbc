package ar.com.sistema.secundario.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import ar.com.sistema.secundario.dao.DAOManager;
import ar.com.sistema.secundario.dao.NotaDAO;
import ar.com.sistema.secundario.dao.impl.DAOManagerImpl;
import ar.com.sistema.secundario.exceptions.EntityNotExistsException;
import ar.com.sistema.secundario.modelo.Nota;

public class TestNotaDAOImpl {

	@Test
	public void testWeCanInsertAMark() throws SQLException{
		DAOManager manager = new DAOManagerImpl();
		NotaDAO dao  = manager.getNotaDAOImpl();
		
		Nota mark = new Nota(1,8,4433333,1234,2222);
		assertTrue(dao.insertRow(mark));
		
		manager.closeConnection();
	}

	@Test
	public void testWeCanSeeAllMarks() throws SQLException{
		DAOManager manager = new DAOManagerImpl();
		NotaDAO dao  = manager.getNotaDAOImpl();
		
		List<Nota> listMarks = dao.getAllRows();
		assertNotNull(listMarks);
		listMarks.forEach((m)->{
			System.out.println(m);
		});
		
		manager.closeConnection();
	}
	
	@Test
	public void testWeCanSeeAnSpecificMarkById() throws SQLException, EntityNotExistsException{
		DAOManager manager = new DAOManagerImpl();
		NotaDAO dao  = manager.getNotaDAOImpl();
		
		Nota mark  = dao.getRowSpecificById(2);
		assertNotNull(mark);
		System.out.println(mark);
		
		manager.closeConnection();
	}
	
	@Test(expected = EntityNotExistsException.class)
	public void testWeCannotSeeAnSpecificMarkById() throws SQLException, EntityNotExistsException{
		DAOManager manager = new DAOManagerImpl();
		NotaDAO dao  = manager.getNotaDAOImpl();
		
		Nota mark  = dao.getRowSpecificById(4);
		assertNotNull(mark);
		System.out.println(mark);
		
		manager.closeConnection();
	}
	
	@Test
	public void testWeCanUpdateAMark () throws SQLException, EntityNotExistsException{
		DAOManager manager = new DAOManagerImpl();
		NotaDAO dao  = manager.getNotaDAOImpl();
		
		Nota newData = new Nota(2,10,4433333,1234,2222);
		assertTrue(dao.updateRowById(newData));
		
		manager.closeConnection();
	}
	
	@Test(expected = EntityNotExistsException.class)
	public void testWeCannotUpdateAMark () throws SQLException, EntityNotExistsException{
		DAOManager manager = new DAOManagerImpl();
		NotaDAO dao  = manager.getNotaDAOImpl();
		
		Nota newData = new Nota(10,8,4433333,1234,2222);
		assertTrue(dao.updateRowById(newData));
		
		manager.closeConnection();
	}
	
	@Test
	public void testWeCanDeleteAMark() throws SQLException, EntityNotExistsException{
		DAOManager manager = new DAOManagerImpl();
		NotaDAO dao  = manager.getNotaDAOImpl();
		
		assertTrue(dao.deleteRowById(1));
		manager.closeConnection();

	}
	
	
	@Test(expected = EntityNotExistsException.class)
	public void testWeCannotDeleteAMark() throws SQLException, EntityNotExistsException{
		DAOManager manager = new DAOManagerImpl();
		NotaDAO dao  = manager.getNotaDAOImpl();
		
		assertTrue(dao.deleteRowById(10));
		manager.closeConnection();

	}
	
	@Test
	public void testWeCanSeeAllMarksByStudentIdAndSubjectId()throws SQLException, EntityNotExistsException{
		DAOManager manager = new DAOManagerImpl();
		NotaDAO dao  = manager.getNotaDAOImpl();
		
		String txt = dao.showInformationAboutTheMarksByStudentIdAndSubjectId(4433333,2222);
		assertNotNull(txt);
		System.out.println(txt);
		manager.closeConnection();
	}
	
	
	@Test (expected = EntityNotExistsException.class)
	public void testWeCannotSeeAllMarksByStudentIdAndSubjectId()throws SQLException, EntityNotExistsException{
		DAOManager manager = new DAOManagerImpl();
		NotaDAO dao  = manager.getNotaDAOImpl();
		
		String txt = dao.showInformationAboutTheMarksByStudentIdAndSubjectId(4433333,2222);
		assertNotNull(txt);
		System.out.println(txt);
		manager.closeConnection();
	}
	
	@Test
	public void testWeCanSeeHistorialOfAStudentById() throws SQLException, EntityNotExistsException{
		DAOManager manager = new DAOManagerImpl();
		NotaDAO dao  = manager.getNotaDAOImpl();
		
		String txt = dao.showHistorialSchoolByStudentId(4433333);
		assertNotNull(txt);
		System.out.println(txt);
		manager.closeConnection();
	}
	
	@Test(expected = EntityNotExistsException.class)
	public void testWeCannotSeeHistorialOfAStudentById() throws SQLException, EntityNotExistsException{
		DAOManager manager = new DAOManagerImpl();
		NotaDAO dao  = manager.getNotaDAOImpl();
		
		String txt = dao.showHistorialSchoolByStudentId(1234);
		assertNotNull(txt);
		System.out.println(txt);
		manager.closeConnection();
	}
	
}
