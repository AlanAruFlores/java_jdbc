package ar.com.sistema.secundario.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import ar.com.sistema.secundario.dao.AlumnoMateriaDAO;
import ar.com.sistema.secundario.dao.DAOManager;
import ar.com.sistema.secundario.dao.impl.DAOManagerImpl;
import ar.com.sistema.secundario.exceptions.EntityNotExistsException;
import ar.com.sistema.secundario.modelo.AlumnoMateria;

public class TestAlumnoMateriaDAO {

	@Test
	public void testWeCanInsertAStudentSubject() throws SQLException{
		DAOManager manager = new DAOManagerImpl();
		AlumnoMateriaDAO dao = manager.getAlumnoMateriaDAOImpl();
		
		AlumnoMateria studentSubject = new AlumnoMateria(4433333,4444); 
		
		assertTrue(dao.insertRow(studentSubject));
		manager.closeConnection();
		
		
	}
	
	@Test
	public void testWeCanSeeAllStudentsSubjects()throws SQLException{
		DAOManager manager = new DAOManagerImpl();
		AlumnoMateriaDAO dao = manager.getAlumnoMateriaDAOImpl();
		
		List<AlumnoMateria> listStuSub = dao.getAllRows();
		assertNotNull(listStuSub);
		for(AlumnoMateria ss: listStuSub) {
			System.out.println(ss);
		}
		
		manager.closeConnection();
	}

	@Test
	public void testWeCanSeeAllStudentsSubjects2()throws SQLException{
		DAOManager manager = new DAOManagerImpl();
		AlumnoMateriaDAO dao = manager.getAlumnoMateriaDAOImpl();

		
		String allInformation = dao.showAllRowsComplete();
		assertNotNull(allInformation);
		System.out.println(allInformation);
		manager.closeConnection();
	}
	
	@Test
	public void testWeCanSeeAllSubjectsByStudentID() throws SQLException, EntityNotExistsException{
		DAOManager manager = new DAOManagerImpl();
		AlumnoMateriaDAO dao = manager.getAlumnoMateriaDAOImpl();
		
		String txt = dao.showSubjectsByStudentId(4433333);
		assertNotNull(txt);
		System.out.println(txt);
		manager.closeConnection();
	}
	
	@Test(expected = EntityNotExistsException.class)
	public void testWeCannotSeeAllSubjectsByStudentID() throws SQLException, EntityNotExistsException{
		DAOManager manager = new DAOManagerImpl();
		AlumnoMateriaDAO dao = manager.getAlumnoMateriaDAOImpl();
		
		String txt = dao.showSubjectsByStudentId(4433333);
		assertNotNull(txt);
		System.out.println(txt);
		manager.closeConnection();
	}
	
	@Test
	public void testWeCanDeleteARow() throws SQLException , EntityNotExistsException{
		DAOManager manager = new DAOManagerImpl();
		AlumnoMateriaDAO dao = manager.getAlumnoMateriaDAOImpl();
	
		Integer dniStudentDelete = 4433333;
		assertTrue(dao.deleteRowByStudentId(dniStudentDelete));
		manager.closeConnection();
		
	}
	
	@Test (expected = EntityNotExistsException.class)
	public void testWeCannotDeleteARow() throws SQLException , EntityNotExistsException{
		DAOManager manager = new DAOManagerImpl();
		AlumnoMateriaDAO dao = manager.getAlumnoMateriaDAOImpl();
	
		Integer dniStudentDelete = 231232;
		assertTrue(dao.deleteRowByStudentId(dniStudentDelete));
		manager.closeConnection();
		
	}
	
	@Test
	public void testWeCanSeeStudentsBySubjectId()throws SQLException, EntityNotExistsException{
		DAOManager manager = new DAOManagerImpl();
		AlumnoMateriaDAO dao = manager.getAlumnoMateriaDAOImpl();
		String txt = dao.showAllStudentsBySubjectId(2222);
		assertNotNull(txt);
		System.out.println(txt);
		manager.closeConnection();
	}
	
	@Test(expected = EntityNotExistsException.class)
	public void testWeCannotSeeStudentsBySubjectId()throws SQLException, EntityNotExistsException{
		DAOManager manager = new DAOManagerImpl();
		AlumnoMateriaDAO dao = manager.getAlumnoMateriaDAOImpl();
		String txt = dao.showAllStudentsBySubjectId(4444);
		assertNotNull(txt);
		System.out.println(txt);
		manager.closeConnection();
	}
}
