package ar.com.eccomerce.test;

import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.Test;

import ar.com.eccomerce.dao.IDAOManager;
import ar.com.eccomerce.dao.implementation.DAOManager;

public class TestConnection {

	@Test
	public void testIfWeCanConnectAndCloseToDatabase() throws SQLException {
		IDAOManager manager = new DAOManager();
		Boolean value = manager.verifyIfWeHaveConnection();
		assertTrue(value);
		if(value)
			System.out.println("We have connection");

		Boolean valueClose = manager.closeConnection();
		assertTrue(valueClose);
		if(valueClose)
			System.out.println("The connection to the database is closed now !! ");
	}
	

}
