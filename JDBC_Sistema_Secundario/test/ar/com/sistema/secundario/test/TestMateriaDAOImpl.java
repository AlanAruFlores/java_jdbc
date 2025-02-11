package ar.com.sistema.secundario.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import ar.com.sistema.secundario.dao.DAOManager;
import ar.com.sistema.secundario.dao.impl.DAOManagerImpl;
import ar.com.sistema.secundario.dao.impl.MateriaDAOImpl;
import ar.com.sistema.secundario.exceptions.EntityNotExistsException;
import ar.com.sistema.secundario.modelo.Materia;

public class TestMateriaDAOImpl {

	@Test
	public void testWeCanInsertASubject() throws SQLException{
		DAOManager manager = new DAOManagerImpl();
		MateriaDAOImpl dao = manager.getMateriaDAOImpl();
		
		
		Materia materia =  new Materia(4444,"Matematica",1234);
		assertTrue(dao.insertRow(materia));
		
		manager.closeConnection();
	}
	
	@Test
	public void testWeCanShowAllSubjects() throws SQLException{
		DAOManager manager = new DAOManagerImpl();
		MateriaDAOImpl dao = manager.getMateriaDAOImpl();
		
		List<Materia> listSubjects  = dao.getAllRows();
		assertNotNull(listSubjects);
		for(Materia s : listSubjects) {
			System.out.println(s);
		}
		manager.closeConnection();
		
	}
	
	@Test 
	public void testWeCanGetAnSpecificSubjectById() throws SQLException, EntityNotExistsException{
		DAOManager manager = new DAOManagerImpl();
		MateriaDAOImpl dao = manager.getMateriaDAOImpl();
		
		Materia subject = dao.getRowSpecificById(2222);
		assertNotNull(subject);
		System.out.println(subject);
		manager.closeConnection();
	}

	@Test(expected = EntityNotExistsException.class)
	public void testWeCannotGetAnSpecificSubjectById() throws SQLException, EntityNotExistsException{
		DAOManager manager = new DAOManagerImpl();
		MateriaDAOImpl dao = manager.getMateriaDAOImpl();
		
		Materia subject = dao.getRowSpecificById(3333);
		assertNotNull(subject);
		System.out.println(subject);
		manager.closeConnection();
	}
	
	@Test
	public void testWeCanUpdateASubjectById() throws SQLException, EntityNotExistsException{
		DAOManager manager = new DAOManagerImpl();
		MateriaDAOImpl dao = manager.getMateriaDAOImpl();
		
		Materia newData = new Materia(2222,"Programacion",1234);
		assertTrue(dao.updateRowById(newData));
		
		manager.closeConnection();
	}
	
	@Test(expected=EntityNotExistsException.class)
	public void testWeCannotUpdateASubjectById() throws SQLException, EntityNotExistsException{
		DAOManager manager = new DAOManagerImpl();
		MateriaDAOImpl dao = manager.getMateriaDAOImpl();
		
		Materia newData = new Materia(3333,"Literatura",1234);
		assertTrue(dao.updateRowById(newData));
		
		manager.closeConnection();
	}
	
	@Test
	public void testWeCanDeleteASubjectById() throws SQLException, EntityNotExistsException{
		DAOManager manager = new DAOManagerImpl();
		MateriaDAOImpl dao = manager.getMateriaDAOImpl();
		
		assertTrue(dao.deleteRowById(4444));
		manager.closeConnection();
	}
		
	
	@Test(expected = EntityNotExistsException.class)
	public void testWeCannotDeleteASubjectById() throws SQLException, EntityNotExistsException{
		DAOManager manager = new DAOManagerImpl();
		MateriaDAOImpl dao = manager.getMateriaDAOImpl();
		
		assertTrue(dao.deleteRowById(3333));
		manager.closeConnection();
	}
}
