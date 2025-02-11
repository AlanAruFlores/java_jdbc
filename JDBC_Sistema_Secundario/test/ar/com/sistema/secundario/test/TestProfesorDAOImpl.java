package ar.com.sistema.secundario.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import ar.com.sistema.secundario.dao.DAOManager;
import ar.com.sistema.secundario.dao.ProfesorDAO;
import ar.com.sistema.secundario.dao.impl.DAOManagerImpl;
import ar.com.sistema.secundario.exceptions.EntityNotExistsException;
import ar.com.sistema.secundario.modelo.Profesor;

public class TestProfesorDAOImpl {

	@Test
	public void testWeCanInsertANewTeacher() throws SQLException {
		DAOManager manager = new DAOManagerImpl();
		ProfesorDAO dao = manager.getProfesorDAOImpl();

		Profesor teacherToInsert = new Profesor(1235,"Pedro", "Smith");
		assertTrue(dao.insertRow(teacherToInsert));
		manager.closeConnection();
		
	}
	
	@Test
	public void testWeCanGetAllTeachers() throws SQLException{
		DAOManager manager = new DAOManagerImpl();
		ProfesorDAO dao = manager.getProfesorDAOImpl();
		
		List<Profesor> teachers = dao.getAllRows();
		for(Profesor t : teachers) 
			System.out.println(t);
		
		manager.closeConnection();
	}
	
	@Test 
	public void testWeCanGetAnSpecificTeacherByDNI() throws SQLException, EntityNotExistsException{
		DAOManager manager = new DAOManagerImpl();
		ProfesorDAO dao = manager.getProfesorDAOImpl();
		
		Profesor teacher = dao.getRowSpecificById(1235);
		assertNotNull(teacher);
		System.out.println(teacher);
		
		manager.closeConnection();
	}
	
	@Test(expected=EntityNotExistsException.class)
	public void testWeCanNotGetAnSpecificTeacherByDNIIfNotExists() throws SQLException, EntityNotExistsException{
		DAOManager manager = new DAOManagerImpl();
		ProfesorDAO dao = manager.getProfesorDAOImpl();
		
		Profesor teacher = dao.getRowSpecificById(444);
		System.out.println(teacher);		
		manager.closeConnection();
	}
	
	@Test
	public void testWeCanUpdateATeacher() throws SQLException, EntityNotExistsException{
		DAOManager manager  = new DAOManagerImpl();
		ProfesorDAO dao = manager.getProfesorDAOImpl();
		
		Profesor  newData = new Profesor(1234,"Martin","Smith");
		assertTrue(dao.updateRowById(newData));
		manager.closeConnection();
		
	}
	
	@Test(expected = EntityNotExistsException.class)
	public void testWeCanNOTUpdateATeacherIfNotExists() throws SQLException, EntityNotExistsException{
		DAOManager manager  = new DAOManagerImpl();
		ProfesorDAO dao = manager.getProfesorDAOImpl();
		
		Profesor  newData = new Profesor(12324,"Martin","Smith");
		assertTrue(dao.updateRowById(newData));
		manager.closeConnection();	
	}
	
	@Test
	public void testWeCanDeleteATeacherByDNI()throws SQLException, EntityNotExistsException{
		DAOManager manager =  new DAOManagerImpl();
		ProfesorDAO dao = manager.getProfesorDAOImpl();
		
		Integer dniToDelete = 1235;
		assertTrue(dao.deleteRowById(dniToDelete));
		manager.closeConnection();
		
	}
	
	@Test(expected =EntityNotExistsException.class)
	public void testWeCanNOTDeleteATeacherByDNIIfNotExists()throws SQLException, EntityNotExistsException{
		DAOManager manager =  new DAOManagerImpl();
		ProfesorDAO dao = manager.getProfesorDAOImpl();
		
		Integer dniToDelete = 123442;
		dao.deleteRowById(dniToDelete);
		manager.closeConnection();
		
	}
	
}


