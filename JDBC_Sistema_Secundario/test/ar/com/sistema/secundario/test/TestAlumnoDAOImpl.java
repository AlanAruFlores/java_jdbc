package ar.com.sistema.secundario.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import ar.com.sistema.secundario.dao.AlumnoDAO;
import ar.com.sistema.secundario.dao.DAOManager;
import ar.com.sistema.secundario.dao.impl.DAOManagerImpl;
import ar.com.sistema.secundario.exceptions.EntityNotExistsException;
import ar.com.sistema.secundario.modelo.Alumno;
import ar.com.sistema.secundario.utils.Nivel;

public class TestAlumnoDAOImpl {

	
	@Test
	public void testWeCanInsertARow() throws SQLException {
		DAOManagerImpl manager = new DAOManagerImpl();
		AlumnoDAO dao = manager.getAlumnoDAO();
		//Data
		Alumno student = new Alumno(2323444, "Ale", "Diaz", 18, Nivel.CUARTO);
		assertNotNull(student);
		assertTrue(dao.insertRow(student));
		manager.closeConnection();
		
	}
	
	@Test
	public void testThatAllowShowMeAllStudents() throws SQLException {
		DAOManagerImpl manager = new DAOManagerImpl();
		AlumnoDAO dao = manager.getAlumnoDAO();
		List<Alumno> listStudents = dao.getAllRows();
		assertNotNull(listStudents);
		for(Alumno alum : listStudents)
			System.out.println(alum.toString());
		manager.closeConnection();

	}
	
	@Test
	public void testAllowMeToGetAStudentByDNI() throws SQLException, EntityNotExistsException {
		DAOManagerImpl manager = new DAOManagerImpl();
		AlumnoDAO dao = manager.getAlumnoDAO();
		Alumno student = dao.getRowSpecificById(2323444);
		assertNotNull(student);
		System.out.println(student);
		manager.closeConnection();
	}
	
	@Test 
	public void testThatAllowMeToDeleteAStudent() throws SQLException, EntityNotExistsException {
		DAOManager manager = new DAOManagerImpl();
		AlumnoDAO dao = manager.getAlumnoDAO();
		
		Integer dniToDelete = 4221223;
		Boolean result = dao.deleteRowById(dniToDelete);
		assertTrue(result);
		manager.closeConnection();
	}
	
	@Test(expected = EntityNotExistsException.class)
	public void testThatGiveMeAExceptionWhenAStudentNotExists() throws SQLException, EntityNotExistsException {
		DAOManager manager = new DAOManagerImpl();
		AlumnoDAO dao = manager.getAlumnoDAO();
		
		Integer dniToDelete = 123;
		dao.deleteRowById(dniToDelete);
		manager.closeConnection();
	}
	
	@Test
	public void testThatAllowMeToUpdateTheDataOfAStudent() throws SQLException, EntityNotExistsException {
		DAOManager manager = new DAOManagerImpl();
		AlumnoDAO dao = manager.getAlumnoDAO();
		
		Alumno newData = new Alumno(4232323,"Milton","Lorenzo",24,Nivel.CUARTO);
		assertTrue(dao.updateRowById(newData));
		manager.closeConnection();
	}
	
	@Test(expected = EntityNotExistsException.class)
	public void testThatNOTAllowMeToUpdateTheDataOfAStudentIfNotExist() throws SQLException, EntityNotExistsException {
		DAOManager manager = new DAOManagerImpl();
		AlumnoDAO dao = manager.getAlumnoDAO();
		
		Alumno newData = new Alumno(123,"Milton","Lorenzo",24,Nivel.CUARTO);
		assertTrue(dao.updateRowById(newData));
		manager.closeConnection();
	}
}
