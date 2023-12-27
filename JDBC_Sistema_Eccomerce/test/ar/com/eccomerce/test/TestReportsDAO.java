package ar.com.eccomerce.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import ar.com.eccomerce.dao.IDAOManager;
import ar.com.eccomerce.dao.IReportDAO;
import ar.com.eccomerce.dao.implementation.DAOManager;
import ar.com.eccomerce.exceptions.ObjectSQLNotExists;
import ar.com.eccomerce.model.Report;
import ar.com.eccomerce.model.User;

public class TestReportsDAO {

	@Test
	public void testWeCanDoAReport() throws SQLException{
		IDAOManager manager = new DAOManager();
		IReportDAO dao = manager.getReportDAO();
		Report report = new Report(2,4);
		
		assertTrue(dao.insert(report));
		manager.closeConnection();
	}
	
	@Test
	public void testWeCanGetAllReports() throws SQLException{
		IDAOManager manager = new DAOManager();
		IReportDAO dao = manager.getReportDAO();
		List<Report> list = dao.getAll();
		assertNotNull(list);
		list.forEach((x)->{
			System.out.println(x);
		});
		manager.closeConnection();
	}
	
	
	@Test
	public void testWeCanGetAReportById() throws SQLException, ObjectSQLNotExists{
		IDAOManager manager = new DAOManager();
		IReportDAO dao = manager.getReportDAO();
		
		Report report = dao.getById(new Report(2,3));
		assertNotNull(report);
		System.out.println(report);
		manager.closeConnection();
	}
	
	@Test(expected=ObjectSQLNotExists.class)
	public void testWeCannotGetAReportById() throws SQLException, ObjectSQLNotExists{
		IDAOManager manager = new DAOManager();
		IReportDAO dao = manager.getReportDAO();
		
		Report report = dao.getById(new Report(2000,2000));
		assertNotNull(report);
		System.out.println(report);
		manager.closeConnection();
	}
	
	@Test
	public void testWeCanDeleteAReportById() throws SQLException, ObjectSQLNotExists{
		IDAOManager manager = new DAOManager();
		IReportDAO dao = manager.getReportDAO();		
		assertTrue(dao.delete(new Report(2,3)));
		manager.closeConnection();
	}
	
	@Test(expected = ObjectSQLNotExists.class)
	public void testWeCannotDeleteAReportById() throws SQLException, ObjectSQLNotExists{
		IDAOManager manager = new DAOManager();
		IReportDAO dao = manager.getReportDAO();		
		assertTrue(dao.delete(new Report(2000,2000)));
		manager.closeConnection();
	}
	
	@Test
	public void testWeCanGetHistorialReportOfReceptorId() throws SQLException, ObjectSQLNotExists{
		IDAOManager manager = new DAOManager();
		IReportDAO dao = manager.getReportDAO();		
		String txt = dao.seeAllReportsByReceptorId(new User(2));
		assertNotNull(txt);
		assertTrue(txt!="");
		
		System.out.println(txt);
		
		manager.closeConnection();
	}
	
	@Test(expected = ObjectSQLNotExists.class)
	public void testWeCannotGetHistorialReportOfReceptorId() throws SQLException, ObjectSQLNotExists{
		IDAOManager manager = new DAOManager();
		IReportDAO dao = manager.getReportDAO();		
		String txt = dao.seeAllReportsByReceptorId(new User(2000));
		assertNotNull(txt);
		assertTrue(txt!="");
		
		System.out.println(txt);
		
		manager.closeConnection();
	}
	

}
