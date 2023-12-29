package ar.com.eccomerce.view;

import java.sql.SQLException;

import ar.com.eccomerce.exceptions.ObjectSQLNotExists;

public class Main {
	public static void main(String[]args) throws SQLException, ObjectSQLNotExists{
		App myApp = new App();
		myApp.startApp();
		myApp.closeApplication();
	}
}
