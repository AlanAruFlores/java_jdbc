package ar.com.sistema.secundario.test;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import ar.com.sistema.secundario.dao.impl.DAOManagerImpl;

public class TestConnection {

	
	/**
	 * Testeamos la conexion 
	 * @throws SQLException
	 */
	@Test
	public void testingTheConnectionToTheDatabase() throws SQLException {
		DAOManagerImpl manager = new DAOManagerImpl();
		assertNotNull(manager.getConnection());
	}
	
	/**
	 * Testeamos la conexion y que se pueda cerrar
	 * @throws SQLException
	 */
	@Test
	public void testingThatTheConnectionCanBeClosed() throws SQLException {
		DAOManagerImpl manager = new DAOManagerImpl();
		assertNotNull(manager.getConnection());
		manager.closeConnection();
	}
	

}

